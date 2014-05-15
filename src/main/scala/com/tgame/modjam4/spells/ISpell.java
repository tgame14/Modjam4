package com.tgame.modjam4.spells;

import net.minecraft.entity.player.EntityPlayer;

/**
 * @since 15/05/14
 * @author tgame14
 */
public interface ISpell
{
    /** should save state */
    void onActived(EntityPlayer player);
}
