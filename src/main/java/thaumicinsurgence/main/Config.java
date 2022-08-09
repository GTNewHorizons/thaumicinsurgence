package thaumicinsurgence.main;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import java.io.File;
import java.lang.reflect.Field;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumicinsurgence.block.BlockInfusionFucker;
import thaumicinsurgence.main.utils.VersionInfo;
import thaumicinsurgence.tileentity.TileEntityInfusionFucker;
import thaumicinsurgence.item.ItemMiscResources;
import thaumicinsurgence.main.utils.compat.ThaumcraftHelper;
/**
 * A class to hold some data related to mod state & functions.
 *
 * @author MysteriousAges
 */
public class Config {
    public static final String CATEGORY_GENERAL = "general";
    public static final String CATEGORY_CLIENT = "client";
    public static final String CATEGORY_DEBUG = "debug";
    public static final String CATEGORY_MODULES = "modules";
    public static final String CATEGORY_BOTANIA = "botaniaPlugin";

    public static boolean forestryDebugEnabled;
    public static boolean thaumcraftActive;

    public static ItemMiscResources miscResources;

    public static BlockInfusionFucker infusionIntercepter;

    // ----- Config State info ----------------------------------
    public static Configuration configuration;

    public Config(File configFile) {
        configuration = new Configuration(configFile);
        configuration.load();
        processConfigFile();

        forestryDebugEnabled = (new File("./config/forestry/DEBUG.ON")).exists();
        configuration.save();
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.modID.equalsIgnoreCase(VersionInfo.ModName)) {
            if (configuration.hasChanged()) {
                configuration.save();
            }
        }
    }

    public void saveConfigs() {
        configuration.save();
    }

    public void setupBlocks() {
        //  setupPhialingCabinet();
        setupInfusionFucker();
    }

    public void setupItems() {
        miscResources = new ItemMiscResources();
    }

    private void processConfigFile() {
        // Pull config from Forestry via reflection
        Field f;
        syncConfigs();
    }

    private void syncConfigs() {
        doModuleConfigs();
    }

    private void doModuleConfigs() {
        Property p;

  //      p = configuration.get(CATEGORY_MODULES, "BloodMagic", true);
  //      bloodMagicActive = p.getBoolean();


        p = configuration.get(CATEGORY_MODULES, "Thaumcraft", true);
        thaumcraftActive = p.getBoolean();

   //     p = configuration.get(CATEGORY_MODULES, "Botania", true);
   //     botaniaActive = p.getBoolean();
   //     BotaniaHelper.doBotaniaModuleConfigs(configuration);
    }

    public void setupInfusionFucker() {
        if (ThaumcraftHelper.isActive()){}
        infusionIntercepter = new BlockInfusionFucker();
        GameRegistry.registerBlock(infusionIntercepter, "infusionIntercepter");
        GameRegistry.registerTileEntity(TileEntityInfusionFucker.class, TileEntityInfusionFucker.tileEntityName);
    }
}
