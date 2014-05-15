package com.tgame.modjam4.handlers

import scala.collection.parallel.mutable
import net.minecraft.item.{Item, ItemStack}
import net.minecraft.block.Block
import net.minecraft.init.Blocks
import cpw.mods.fml.common.registry.GameData
import net.minecraftforge.oredict.{ShapelessOreRecipe, ShapedOreRecipe, OreDictionary}
import net.minecraft.item.crafting._
import scala.Some
import scala.collection.mutable
import scala.collection.parallel.mutable
import java.util

/**
 * @since 15/05/14
 * @author tgame14
 */
object ItemCostHandler {
  protected val itemMapCache = mutable.Map.empty[ItemStack, Iterable[(ItemStack, Double)]]

  def terminate(item: Item, metadata: Int) = {
    itemMapCache += new ItemStack(item, 1, metadata) -> mutable.Iterable(new ItemStack(item, 1, meta), 1)
  }

  def terminate(block: Block, metadata: Int) = {
    itemMapCache += new ItemStack(block, 1, metadata) -> mutable.Iterable(new ItemStack(block, 1, meta), 1)
  }

  terminate(Blocks.clay)
  for (block <- GameData.getBlockRegistry) {
    terminate(block)
  }

  for (item <- GameData.getItemRegistry) {
    terminate(item)
  }

  protected def computeIngredients(what: ItemStack): Iterable[(ItemStack, Double)] = {
    def deflate(list: Iterable[(ItemStack, Double)]): Iterable[(ItemStack, Double)] = {
      val counts = mutable.Map.empty[ItemStack, Double]
      for ((stack, count) <- list) {
        counts.find {
          case (key, value) => fuzzyEquals(key, stack)
        } match {
          case Some((key, value)) => counts.update(key, value + count)
          case _ => counts += stack -> count
        }
      }
      counts
    }
    def accumulate(input: Any, path: Seq[ItemStack] = Seq.empty): Iterable[(ItemStack, Double)] = input match {
      case stack: ItemStack =>
        cache.find {
          case (key, value) => fuzzyEquals(key, stack)
        } match {
          case Some((_, value)) => value
          case _ =>
            if (path.exists(value => fuzzyEquals(value, stack))) {
              Iterable((stack, 1.0))
            }
            else {
              val recipes = CraftingManager.getInstance.getRecipeList.map(_.asInstanceOf[IRecipe])
              val recipe = recipes.find((recipe: IRecipe) => recipe.getRecipeOutput != null && fuzzyEquals(stack, recipe.getRecipeOutput))
              val (ingredients, output) = recipe match {
                case Some(recipe: ShapedRecipes) => (recipe.recipeItems.flatMap(accumulate(_, path :+ stack)).toIterable, recipe.getRecipeOutput.stackSize)
                case Some(recipe: ShapelessRecipes) => (recipe.recipeItems.flatMap(accumulate(_, path :+ stack)).toIterable, recipe.getRecipeOutput.stackSize)
                case Some(recipe: ShapedOreRecipe) => (recipe.getInput.flatMap(accumulate(_, path :+ stack)).toIterable, recipe.getRecipeOutput.stackSize)
                case Some(recipe: ShapelessOreRecipe) => (recipe.getInput.flatMap(accumulate(_, path :+ stack)).toIterable, recipe.getRecipeOutput.stackSize)
                case _ => FurnaceRecipes.smelting.getSmeltingList.asInstanceOf[util.Map[Integer, ItemStack]].find {
                  case (_, value) => fuzzyEquals(stack, value)
                } match {
                  case Some((blockId, result)) => (accumulate(new ItemStack(blockId, 1, 0), path :+ stack), result.stackSize)
                  case _ => FurnaceRecipes.smelting.getMetaSmeltingList.find {
                    case (_, value) => fuzzyEquals(stack, value)
                  } match {
                    case Some((data, result)) =>
                      val (itemId, metadata) = (data.get(0), data.get(1))
                      (accumulate(new ItemStack(itemId, 1, metadata), path :+ stack), result.stackSize)
                    case _ => (Iterable((stack, 1.0)), 1)
                  }
                }
              }
              val scaled = deflate(ingredients.map {
                case (ingredient, count) => (ingredient.copy(), count / output)
              }).toArray.sortBy(_._1.getUnlocalizedName)
              cache += stack.copy() -> scaled
              scaled
            }
        }
      case list: util.ArrayList[ItemStack]@unchecked if !list.isEmpty =>
        var result = Iterable.empty[(ItemStack, Double)]
        for (stack <- list if result.isEmpty) {
          cache.find {
            case (key, value) => fuzzyEquals(key, stack)
          } match {
            case Some((_, value)) => result = value
            case _ =>
          }
        }
        if (result.isEmpty) {
          result = accumulate(list.get(0), path)
        }
        result
      case _ => Iterable.empty
    }
    accumulate(what)
  }

  def fuzzyEquals(stack1: ItemStack, stack2: ItemStack) = (stack1.isItemEqual(stack2) || (stack1.itemID == stack2.itemID && (stack1.getItemDamage == OreDictionary.WILDCARD_VALUE || stack2.getItemDamage == OreDictionary.WILDCARD_VALUE))) && ItemStack.areItemStackTagsEqual(stack1, stack2)


}
