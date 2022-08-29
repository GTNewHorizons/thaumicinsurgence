package thaumicinsurgence.main;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import thaumicinsurgence.main.utils.CraftingManager;
import thaumicinsurgence.main.utils.LogHelper;
import thaumicinsurgence.main.utils.compat.ThaumcraftHelper;

@SuppressWarnings("unused")
public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        LogHelper.info("Preinit started");
        Config.Init(event.getSuggestedConfigurationFile());

        Config.setupBlocks();
        Config.setupItems();

        LogHelper.info("Preinit completed");
    }

    public void init(FMLInitializationEvent event) {
        ThaumcraftHelper.getBlocks();
        ThaumcraftHelper.getItems();
        LogHelper.info("Init completed");
    }

    public void postInit(FMLPostInitializationEvent event) {
        ThaumcraftHelper.setupItemAspects();
        ThaumcraftHelper.setupCrafting();
        ThaumcraftHelper.setupResearch();

        CraftingManager.setupCrafting();

        LogHelper.info("Postinit completed");
    }
}
