package com.tgame.modjam4;

import com.tgame.modjam4.Items.ItemCastingStick;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

/**
 * @since 15/05/14
 * @author tgame14
 */
@Mod(modid = Settings.ID, name = Settings.NAME, version = Settings.VERSION)
public class ExtraordinaryMatter
{
    @Mod.Instance(Settings.ID)
    public static ExtraordinaryMatter instance;

    @SidedProxy(clientSide = Settings.DOMAIN + ".ClientProxy", serverSide = Settings.DOMAIN + ".CommonProxy")
    public static CommonProxy proxy;

    @Mod.Metadata
    public static ModMetadata metadata;

    public static Item itemCastingStick;

    @Mod.EventHandler
    public void preInit (FMLPreInitializationEvent event)
    {
        itemCastingStick = new ItemCastingStick();
        GameRegistry.registerItem(itemCastingStick, itemCastingStick.getClass().getName());

    }

    @Mod.EventHandler
    public void init (FMLInitializationEvent event)
    {
        metadata.authorList.clear();
        metadata.authorList.add("@AUTHOR@");
        metadata.autogenerated = false;
        metadata.credits = "@AUTHOR@, whoever watched the modjam Stream and the Modjam people!";
        metadata.description = "A Mod that follows the only true fact, Everything is made of the Same basic Resource";
        metadata.modId = Settings.ID;
        metadata.name = Settings.NAME;
        metadata.version = Settings.VERSION;
        metadata.url = "tgame-mods.info";
        metadata.updateUrl = "tgame-mods.info";


    }

    @Mod.EventHandler
    public void postInit (FMLPostInitializationEvent event)
    {

    }

}
