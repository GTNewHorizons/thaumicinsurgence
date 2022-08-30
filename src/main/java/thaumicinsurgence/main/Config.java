package thaumicinsurgence.main;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import java.io.File;
import net.minecraftforge.common.config.Configuration;
import thaumicinsurgence.block.BlockInfusionFucker;
import thaumicinsurgence.item.ItemMiscResources;
import thaumicinsurgence.main.utils.VersionInfo;
import thaumicinsurgence.tileentity.TileEntityInfusionFucker;

/**
 * A class to hold some data related to mod state & functions.
 *
 * @author MysteriousAges
 */
public class Config {
    public static final String CATEGORY_MODULES = "modules";

    public static boolean thaumcraftActive;

    public static ItemMiscResources miscResources;

    public static BlockInfusionFucker infusionIntercepter;

    // ----- Config State info ----------------------------------
    public static Configuration configuration;
    private static Config instance = null;

    public static void Init(File configFile) {
        if (instance != null) return;
        instance = new Config();
        FMLCommonHandler.instance().bus().register(instance);
        configuration = new Configuration(configFile);
        configuration.load();
        processConfigFile();

        configuration.save();
    }

    @SuppressWarnings("unused")
    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.modID.equalsIgnoreCase(VersionInfo.ModName)) {
            if (configuration.hasChanged()) {
                configuration.save();
            }
        }
    }

    public static void saveConfigs() {
        configuration.save();
    }

    public static void setupBlocks() {
        setupInfusionFucker();
    }

    public static void setupItems() {
        miscResources = new ItemMiscResources();
    }

    private static void processConfigFile() {
        syncConfigs();
    }

    private static void syncConfigs() {
        doModuleConfigs();
    }

    private static void doModuleConfigs() {
        thaumcraftActive =
                configuration.get(CATEGORY_MODULES, "Thaumcraft", true).getBoolean();
    }

    public static void setupInfusionFucker() {
        infusionIntercepter = new BlockInfusionFucker();
        GameRegistry.registerBlock(infusionIntercepter, "infusionIntercepter");
        GameRegistry.registerTileEntity(
                                        TileEntityInfusionFucker.class, TileEntityInfusionFucker.tileEntityName);
    }
}
