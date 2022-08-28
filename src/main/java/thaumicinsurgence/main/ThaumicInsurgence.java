package thaumicinsurgence.main;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLMissingMappingsEvent;
import cpw.mods.fml.common.event.FMLMissingMappingsEvent.MissingMapping;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import thaumicinsurgence.main.utils.CraftingManager;
import thaumicinsurgence.main.utils.LogHelper;
import thaumicinsurgence.main.utils.VersionInfo;
import thaumicinsurgence.main.utils.compat.ThaumcraftHelper;

@Mod(
        modid = VersionInfo.ModName,
        name = "Thaumic Insurgence",
        version = VersionInfo.Version,
        dependencies = VersionInfo.Depends,
        guiFactory = VersionInfo.GUI_FACTORY_CLASS)
public class ThaumicInsurgence {

    @Mod.Instance(VersionInfo.ModName)
    public static thaumicinsurgence.main.ThaumicInsurgence object;

    @SidedProxy(serverSide = "thaumicinsurgence.main.CommonProxy", clientSide = "thaumicinsurgence.main.ClientProxy")
    public static CommonProxy proxy;

    private Config modConfig;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LogHelper.info("Preinit started");
        this.modConfig = new Config(event.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(modConfig);

        // Compatibility Helpers setup time.
        // ModHelperManager.preInit();

        this.modConfig.setupBlocks();
        this.modConfig.setupItems();

        LogHelper.info("Preinit completed");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ThaumcraftHelper.getBlocks();
        ThaumcraftHelper.getItems();
        LogHelper.info("Init completed");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        ThaumcraftHelper.setupItemAspects();
        ThaumcraftHelper.setupCrafting();
        ThaumcraftHelper.setupResearch();

        proxy.registerRenderers();

        this.modConfig.saveConfigs();

        CraftingManager.setupCrafting();

        VersionInfo.doVersionCheck();
        LogHelper.info("Postinit completed");
    }

    @Mod.EventHandler
    public void handleMissingMapping(FMLMissingMappingsEvent event) {
        for (MissingMapping mapping : event.get()) {
            // TODO: ... maybe not this.
            LogHelper.info(String.format("Missing mapping: %s - ignoring.", mapping.name));
            mapping.ignore();
        }
    }
}
