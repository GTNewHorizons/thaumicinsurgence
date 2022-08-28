package thaumicinsurgence.main.utils.compat;

import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;
import thaumicinsurgence.block.BlockInfusionFucker;
import thaumicinsurgence.main.CommonProxy;
import thaumicinsurgence.main.Config;
import thaumicinsurgence.main.utils.BlockInterface;
import thaumicinsurgence.main.utils.ItemInterface;
import thaumicinsurgence.main.utils.LocalizationManager;

public class ThaumcraftHelper implements IModHelper {

    public enum MiscResource {
        ALUMENTUM,
        NITOR,
        THAUMIUM,
        QUICKSILVER,
        MAGIC_TALLOW,
        BRAIN_DEPRECATED,
        AMBER,
        ENCHANTED_FABRIC,
        VIS_FILTER,
        KNOWLEDGE_FRAGMENT,
        MIRRORED_GLASS,
        TAINTED_GOO,
        TAINTED_TENDRIL,
        JAR_LABEL,
        SALIS,
        CHARM,
        VOID_INGOT,
        VOID_SEED,
        COIN,
        ;
    }

    public enum NuggetType {
        IRON,
        COPPER,
        TIN,
        SILVER,
        LEAD,
        QUICKSILVER,
        THAUMIUM,
        VOID_METAL,
        _8,
        _9,
        _10,
        _11,
        _12,
        _13,
        _14,
        _15,
        NATIVE_IRON,
        NATIVE_COPPER,
        NATIVE_TIN,
        NATIVE_SILVER,
        NATIVE_LEAD,
        NATIVE_CINNABAR,
        _22,
        _23,
        _24,
        _25,
        _26,
        _27,
        _28,
        _29,
        _30,
        NATIVE_GOLD,
        ;
    }

    public enum ShardType {
        AIR,
        FIRE,
        WATER,
        EARTH,
        ORDER,
        CHAOS,
        BALANCED,
        ;
    }

    public enum MetalDeviceType {
        CRUCIBLE,
        ALEMBIC,
        VIS_CHARGE_RELAY,
        ADVANCED_ALCHEMICAL_CONSTRUCT,
        _4,
        ITEM_GRATE,
        _6,
        ARCANE_LAMP,
        LAMP_OF_GROWTH,
        ALCHEMICAL_CONSTRUCT,
        THAUMATORIUM,
        _11,
        MNEMONIC_MATRIX,
        LAMP_OF_FERTILITY,
        VIS_RELAY,
        ;
    }

    public enum WoodenDeviceType {
        BELLOWS,
        EAR,
        PRESSURE_PLATE,
        PRESSURE_PLATE_B,
        BORE_BASE,
        BORE,
        PLANKS_GREATWOOD,
        PLANKS_SILVERWOOD,
        BANNER,
        ;
    }

    public enum AiryBlockType {
        NODE,
        NITOR,
        _2,
        _3,
        WARDING_STONE_FENCE,
        ENERGIZED_NODE,
        ;
    }

    public enum Entity {
        BRAINY_ZOMBIE("entBrainyZombie", "EntityBrainyZombie"),
        GIANT_BRAINY_ZOMBIE("entGiantBrainyZombie", "EntityGiantBrainyZombie"),
        WISP("entWisp", "EntityWisp"),
        FIREBAT("entFirebat", "EntityFireBat"),
        ;

        private static String packageName = "thaumcraft.common.entities.monster.";

        public String entityID;
        private String className;

        private Entity(String id, String clazz) {
            this.entityID = id;
            this.className = clazz;
        }

        public String getClassName() {
            return packageName + this.className;
        }
    }

    public enum BlockPlant {
        GREATWOOD_SAPLING,
        SILVERWOOD_SAPLING,
        SHIMMERLEAF,
        CINDERPEARL,
        PURIFYING_PLANT,
        VISHROOM,
        ;
    }

    public enum TreeType {
        GREATWOOD,
        SILVERWOOD,
        ;
    }

    public static Block plant;
    public static Block candle;
    public static Block crystal;
    public static Block marker;
    public static Block jar;
    public static Block log;
    public static Block leaf;
    public static Block warded;
    public static Block wooden;
    public static Block metal;
    public static Block airy;
    public static Block fluxGas;
    public static Block fluxGoo;
    public static Block infusionInterceptorBlock = new BlockInfusionFucker();

    public static Item filledJar;
    public static Item miscResource;
    public static Item shard;
    public static Item golem;
    public static Item nuggetMetal;
    public static Item nuggetChicken;
    public static Item nuggetBeef;
    public static Item nuggetPork;
    public static Item zombieBrain;

    public static Class<? extends TileEntity> nodeClass;

    public static final String Name = "Thaumcraft";
    public static boolean isThaumcraftPresent = true;

    public static boolean isActive() {
        return isThaumcraftPresent;
    }

    public void preInit() {
        isThaumcraftPresent = true;
    }

    public void init() {
        getBlocks();
        getItems();
    }

    public void postInit() {
        setupItemAspects();
        setupCrafting();
        setupResearch();
    }

    public static InfusionRecipe infusionInterceptor;

    public static void getBlocks() {
        plant = BlockInterface.getBlock(Name, "blockCustomPlant");
        candle = BlockInterface.getBlock(Name, "blockCandle");
        crystal = BlockInterface.getBlock(Name, "blockCrystal");
        marker = BlockInterface.getBlock(Name, "blockMarker");
        jar = BlockInterface.getBlock(Name, "blockJar");
        log = BlockInterface.getBlock(Name, "blockMagicalLog");
        leaf = BlockInterface.getBlock(Name, "blockMagicalLeaves");
        warded = BlockInterface.getBlock(Name, "blockWarded");
        wooden = BlockInterface.getBlock(Name, "blockWoodenDevice");
        metal = BlockInterface.getBlock(Name, "blockMetalDevice");
        airy = BlockInterface.getBlock(Name, "blockAiry");
    }

    public static void getItems() {
        filledJar = ItemInterface.getItem(Name, "BlockJarFilledItem");
        miscResource = ItemInterface.getItem(Name, "ItemResource");
        shard = ItemInterface.getItem(Name, "ItemShard");
        golem = ItemInterface.getItem(Name, "ItemGolemPlacer");
        nuggetMetal = ItemInterface.getItem(Name, "ItemNugget");
        shard = ItemInterface.getItem(Name, "ItemShard");
        nuggetChicken = ItemInterface.getItem(Name, "ItemNuggetChicken");
        nuggetBeef = ItemInterface.getItem(Name, "ItemNuggetBeef");
        nuggetPork = ItemInterface.getItem(Name, "ItemNuggetPork");
        zombieBrain = ItemInterface.getItem(Name, "ItemZombieBrain");
    }

    public static void setupCrafting() {
        ItemStack input, output;

        input = new ItemStack(Items.ender_pearl);

        infusionInterceptor = ThaumcraftApi.addInfusionCraftingRecipe(
                "TI_InfusionInterceptor",
                new ItemStack(Config.infusionIntercepter),
                0,
                new AspectList()
                        .add(Aspect.MAGIC, 100)
                        .add(Aspect.HUNGER, 100)
                        .add(Aspect.TOOL, 75)
                        .add(Aspect.TRAP, 50)
                        .add(Aspect.MECHANISM, 50)
                        .add(Aspect.EXCHANGE, 25),
                new ItemStack(metal, 1, MetalDeviceType.ALCHEMICAL_CONSTRUCT.ordinal()),
                new ItemStack[] {
                    new ItemStack(metal, 1, MetalDeviceType.ALEMBIC.ordinal()),
                    new ItemStack(metal, 1, MetalDeviceType.ALCHEMICAL_CONSTRUCT.ordinal()),
                    new ItemStack(metal, 1, MetalDeviceType.ALEMBIC.ordinal()),
                    new ItemStack(metal, 1, MetalDeviceType.ALCHEMICAL_CONSTRUCT.ordinal()),
                    new ItemStack(metal, 1, MetalDeviceType.ALEMBIC.ordinal()),
                    new ItemStack(metal, 1, MetalDeviceType.ALCHEMICAL_CONSTRUCT.ordinal()),
                    new ItemStack(metal, 1, MetalDeviceType.ALEMBIC.ordinal()),
                    new ItemStack(metal, 1, MetalDeviceType.ALCHEMICAL_CONSTRUCT.ordinal())
                });
    }

    public static void setupResearch() {

        ArrayList<Object> list;
        ItemStack input;
        IRecipe recipe;
        String category = "THAUMICINSURGENCE";
        ResearchCategories.registerCategory(
                category,
                new ResourceLocation(CommonProxy.DOMAIN, CommonProxy.ITEM_TEXTURE + "silverwood_filter.png"),
                new ResourceLocation("thaumcraft", "textures/gui/gui_researchback.png"));
        ResearchItem infusionInterceptorPage;
        ResearchPage interceptor1, interceptor2;
        infusionInterceptorPage = new ResearchItem(
                "InfusionInterceptor", category, new AspectList(), 0, 0, 0, new ItemStack(Config.infusionIntercepter));
        interceptor1 = new ResearchPage("InfusionInterceptor.1");
        interceptor2 = new ResearchPage((InfusionRecipe) infusionInterceptor);
        infusionInterceptorPage.setPages(new ResearchPage[] {interceptor1, interceptor2});
        infusionInterceptorPage.setAutoUnlock();
        ResearchCategories.addResearch(infusionInterceptorPage);
    }

    public static ResearchPage getResearchPage(String ident) {
        return new ResearchPage(LocalizationManager.getLocalizedString("tc.research_page." + ident));
    }

    public static void setupItemAspects() {
        ItemStack item;
        AspectList list;
    }
}
