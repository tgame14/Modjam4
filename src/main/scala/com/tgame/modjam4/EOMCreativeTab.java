package com.tgame.modjam4;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * @since 15/05/14
 * @author tgame14
 */
public class EOMCreativeTab extends CreativeTabs
{

    public static CreativeTabs INSTANCE = new EOMCreativeTab(CreativeTabs.getNextID(), "EOMTab");

    public EOMCreativeTab (int par1, String par2Str)
    {
        super(par1, par2Str);
    }

    @Override
    public Item getTabIconItem ()
    {
        return ExtraordinaryMatter.itemCastingStick;
    }
}
