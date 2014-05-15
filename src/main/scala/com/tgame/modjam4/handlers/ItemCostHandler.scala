package com.tgame.modjam4.handlers

import scala.collection.parallel.mutable
import net.minecraft.item.{Item, ItemStack}
import net.minecraft.block.Block

/**
 * @since 15/05/14
 * @author tgame14
 */
class ItemCostHandler {
  protected val itemMapCache = mutable.Map.empty[ItemStack, Iterable[(ItemStack, Double)]]

  def terminate(item: Item, metadata: Int) = {
    itemMapCache += new ItemStack(item, 1, metadata) -> mutable.Iterable(new ItemStack(item, 1, meta), 1)
  }

  def terminate (block: Block, metadata: Int) = {
    itemMapCache += new ItemStack(block, 1, metadata) -> mutable.Iterable(new ItemStack(block, 1, meta), 1)
  }

  static
  {

  }

}
