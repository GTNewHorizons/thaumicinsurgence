package thaumicinsurgence.main;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.WeightedRandom;
import net.minecraftforge.common.config.Configuration;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.items.ItemNuggetEdible;
import thaumicinsurgence.block.BlockArcaneMarble;
import thaumicinsurgence.block.BlockArcaneMarbleBrick;
import thaumicinsurgence.block.BlockInfusionIntercepter;
import thaumicinsurgence.block.BlockInfusionMatrixAlpha;
import thaumicinsurgence.block.BlockInfusionPillarAlpha;
import thaumicinsurgence.block.BlockPedestalAlpha;
import thaumicinsurgence.block.kekztech.BlockIchorJar;
import thaumicinsurgence.block.kekztech.BlockThaumiumReinforcedJar;
import thaumicinsurgence.block.kekztech.ItemBlockIchorJar;
import thaumicinsurgence.block.kekztech.ItemBlockThaumiumReinforcedJar;
import thaumicinsurgence.item.ItemMiscResources;
import thaumicinsurgence.item.ItemSanitySoapAlpha;
import thaumicinsurgence.item.ItemSanitySoapBeta;
import thaumicinsurgence.item.armor.ItemBIGSHOT;
import thaumicinsurgence.item.armor.ItemEightBitRedCrown;
import thaumicinsurgence.item.tools.ItemAlastorsWand;
import thaumicinsurgence.item.tools.ItemThaumicInterfacer;
import thaumicinsurgence.main.modules.thaumic_rings.core.ItemGeneralVisRings;
import thaumicinsurgence.main.utils.VersionInfo;
import thaumicinsurgence.tileentity.TileEntityInfusionIntercepter;
import thaumicinsurgence.tileentity.TileEntityInfusionMatrixAlpha;
import thaumicinsurgence.tileentity.TileEntityInfusionPillarAlpha;
import thaumicinsurgence.tileentity.TileEntityPedestalAlpha;

/**
 * A class to hold some data related to mod state & functions.
 *
 * @author MysteriousAges
 */
public class Config {

    public static final String CATEGORY_MODULES = "modules";

    public static boolean thaumcraftActive;
    public static int blockStoneDeviceRI;
    public static int blockStoneDeviceTwoRI;
    public static int blockStoneDeviceThreeRI;

    public static ItemMiscResources miscResources;
    public static Item eightBitRedCrownItem;
    public static Item thaumicInterfacer;
    public static Item alastorsWand;
    public static Item soapAlpha;
    public static Item soapBetaBitch;
    public static Item hyperLinkBlocked;

    public static Item TAPMasterRing;
    public static Item AIOMasterRing;
    public static Item balancedMasterRing;
    public static Item TAPShardRing;
    public static Item AIOShardRing;
    public static Item balancedShardRing;
    public static Item skullRing;

    public static BlockInfusionIntercepter infusionIntercepter;
    public static BlockInfusionMatrixAlpha matrixAlpha;
    public static BlockInfusionPillarAlpha pillarAlpha;
    public static BlockArcaneMarble arcaneMarble;
    public static BlockArcaneMarbleBrick arcaneMarbleBrick;
    public static BlockPedestalAlpha marblePedestal;
    public static BlockThaumiumReinforcedJar thaumiumJar = new BlockThaumiumReinforcedJar();
    public static BlockIchorJar ichorJar = new BlockIchorJar();

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
        arcaneMarble = new BlockArcaneMarble();
        GameRegistry.registerBlock(arcaneMarble, arcaneMarble.getUnlocalizedName());
        arcaneMarbleBrick = new BlockArcaneMarbleBrick();
        GameRegistry.registerBlock(arcaneMarbleBrick, arcaneMarbleBrick.getUnlocalizedName());

        setupKekztech();
        setupInfusionAlpha();
    }

    public static void setupKekztech(){
        GameRegistry.registerBlock(thaumiumJar, ItemBlockThaumiumReinforcedJar.class, thaumiumJar.getUnlocalizedName());
        GameRegistry.registerBlock(ichorJar, ItemBlockIchorJar.class, ichorJar.getUnlocalizedName());
    }

    public static void setupItems() {
        eightBitRedCrownItem = new ItemEightBitRedCrown(ThaumcraftApi.armorMatSpecial, 4, 0);
        GameRegistry.registerItem(eightBitRedCrownItem, eightBitRedCrownItem.getUnlocalizedName());

        thaumicInterfacer = new ItemThaumicInterfacer();
        GameRegistry.registerItem(thaumicInterfacer, thaumicInterfacer.getUnlocalizedName());

        alastorsWand = new ItemAlastorsWand();
        GameRegistry.registerItem(alastorsWand, alastorsWand.getUnlocalizedName());

        // miscResources = new ItemMiscResources();

        soapAlpha = new ItemSanitySoapAlpha();
        GameRegistry.registerItem(soapAlpha, soapAlpha.getUnlocalizedName());

        soapBetaBitch = new ItemSanitySoapBeta();
        GameRegistry.registerItem(soapBetaBitch, soapBetaBitch.getUnlocalizedName());

        hyperLinkBlocked = new ItemBIGSHOT(ThaumcraftApi.armorMatSpecial, 4, 0);
        GameRegistry.registerItem(hyperLinkBlocked, hyperLinkBlocked.getUnlocalizedName());

        AIOMasterRing = (new ItemGeneralVisRings("goldFireOpal", new AspectList().add(Aspect.AIR, 5).add(Aspect.FIRE, 5).add(Aspect.ORDER, 5))).setUnlocalizedName("AIOMaster");
        GameRegistry.registerItem(AIOMasterRing, "AIOMaster");

        TAPMasterRing = (new ItemGeneralVisRings("silverFireOpal", new AspectList().add(Aspect.EARTH, 5).add(Aspect.WATER, 5).add(Aspect.ENTROPY, 5))).setUnlocalizedName("TAPMaster");
        GameRegistry.registerItem(TAPMasterRing, "TAPMaster");

        balancedMasterRing = (new ItemGeneralVisRings("goldMixedOpal",
                new AspectList().add(Aspect.AIR, 5).add(Aspect.FIRE, 5).add(Aspect.ORDER, 5)
                        .add(Aspect.EARTH, 5).add(Aspect.WATER, 5).add(Aspect.ENTROPY, 5))
                .setUnlocalizedName("balancedMaster"));
        GameRegistry.registerItem(balancedMasterRing, "balancedMaster");
    }

    private static void processConfigFile() {
        syncConfigs();
    }

    private static void syncConfigs() {
        doModuleConfigs();
    }

    private static void doModuleConfigs() {
        thaumcraftActive = configuration.get(CATEGORY_MODULES, "Thaumcraft", true).getBoolean();
    }

    public static void setupInfusionAlpha() {
        infusionIntercepter = new BlockInfusionIntercepter();
        GameRegistry.registerBlock(infusionIntercepter, "infusionIntercepter");
        GameRegistry
                .registerTileEntity(TileEntityInfusionIntercepter.class, TileEntityInfusionIntercepter.tileEntityName);

        matrixAlpha = new BlockInfusionMatrixAlpha();
        GameRegistry.registerBlock(matrixAlpha, "matrixAlpha");
        GameRegistry
                .registerTileEntity(TileEntityInfusionPillarAlpha.class, TileEntityInfusionPillarAlpha.tileEntityName);

        pillarAlpha = new BlockInfusionPillarAlpha();
        GameRegistry.registerBlock(pillarAlpha, "pillarAlpha");
        GameRegistry
                .registerTileEntity(TileEntityInfusionMatrixAlpha.class, TileEntityInfusionMatrixAlpha.tileEntityName);

        marblePedestal = new BlockPedestalAlpha();
        GameRegistry.registerBlock(marblePedestal, "marblePedestal");
        GameRegistry.registerTileEntity(TileEntityPedestalAlpha.class, TileEntityPedestalAlpha.tileEntityName);
    }

    static {
        blockStoneDeviceRI = -1;
        blockStoneDeviceTwoRI = -2;
        blockStoneDeviceThreeRI = -3;
    }
}
