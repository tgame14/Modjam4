package com.tgame.modjam4.Items;

import com.tgame.modjam4.EOMCreativeTab;
import com.tgame.modjam4.Settings;
import net.minecraft.item.Item;

/**
 * @since 15/05/14
 * @author tgame14
 */
public class ItemCastingStick extends Item
{
    public ItemCastingStick ()
    {
        this.setUnlocalizedName(this.getClass().getSimpleName());
        this.setCreativeTab(EOMCreativeTab.INSTANCE);
        this.setTextureName(Settings.RESOURCE_LOCATION + this.getClass().getSimpleName());

    }


}
