package com.tgame.modjam4;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.ModMetadata;

/**
 * @since 15/05/14
 * @author tgame14
 */
@Mod(modid = Settings.ID, name = Settings.NAME, version = Settings.VERSION)
public class ExtraordinaryMatter
{
    @Mod.Instance(Settings.ID)
    public static ExtraordinaryMatter instance;

    @Mod.Metadata
    public static ModMetadata metadata;
}
