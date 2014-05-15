package com.tgame.modjam4.Items;

import com.tgame.modjam4.EOMCreativeTab;
import com.tgame.modjam4.Settings;
import com.tgame.modjam4.spells.ISpell;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @since 15/05/14
 * @author tgame14
 */
public class ItemCastingStick extends Item
{
    protected ISpell spell;

    public ItemCastingStick ()
    {
        this.setUnlocalizedName(this.getClass().getSimpleName());
        this.setCreativeTab(EOMCreativeTab.INSTANCE);
        this.setTextureName(Settings.RESOURCE_LOCATION + this.getClass().getSimpleName());

    }

    @Override
    public ItemStack onItemRightClick (ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        if (spell == null)
        {

        }
    }
}
