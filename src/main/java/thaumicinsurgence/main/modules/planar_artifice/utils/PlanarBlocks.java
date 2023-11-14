package thaumicinsurgence.main.modules.planar_artifice.utils;

import net.minecraft.block.Block;

import cpw.mods.fml.common.registry.GameRegistry;
import thaumicinsurgence.main.modules.planar_artifice.PlanarArtifice;
import thaumicinsurgence.main.modules.planar_artifice.core.blocks.*;
import thaumicinsurgence.main.modules.planar_artifice.core.blocks.tiles.*;

public class PlanarBlocks {

    public static final Class<PlanarArtifice> core = PlanarArtifice.class;

    public static Block alkimiumAlchFurnace = new BlockAlkimiumAlchFurnace();
    public static Block thaumAlkAlchFurnace = new BlockAlkimiumThaumiumAlchFurnace();
    public static Block voidAlkAlchFurnace = new BlockAlkimiumVoidAlchFurnace();
    public static Block mithrilAlkAlchFurnace = new BlockAlkimiumMithrilAlchFurnace();
    public static Block adamantAlkAlchFurnace = new BlockAlkimiumAdamantAlchFurnace();
    public static Block mithminiteAlkAlchFurnace = new BlockAlkimiumMithminiteAlchFurnace();

    public static Block blockPlanarDevice = new BlockPlanarDevice();
    public static Block blockFurnaceDevice = new BlockAlkimiumAlchFurnace2();

    public static void setup() {
        GameRegistry.registerBlock(alkimiumAlchFurnace, "alkimiumAlchFurnace");
        GameRegistry
                .registerTileEntity(TileAlkimiumAlchemicalFurnace.class, TileAlkimiumAlchemicalFurnace.tileEntityName);

        GameRegistry.registerBlock(thaumAlkAlchFurnace, "thaumAlkAlchFurnace");
        GameRegistry.registerTileEntity(
                TileThaumiumAlkimiumAlchemicalFurnace.class,
                TileThaumiumAlkimiumAlchemicalFurnace.tileEntityName);

        GameRegistry.registerBlock(voidAlkAlchFurnace, "voidAlkAlchFurnace");
        GameRegistry.registerTileEntity(
                TileVoidAlkimiumAlchemicalFurnace.class,
                TileVoidAlkimiumAlchemicalFurnace.tileEntityName);

        GameRegistry.registerBlock(mithrilAlkAlchFurnace, "mithrilAlkAlchFurnace");
        GameRegistry.registerTileEntity(
                TileMithrilAlkimiumAlchemicalFurnace.class,
                TileMithrilAlkimiumAlchemicalFurnace.tileEntityName);

        GameRegistry.registerBlock(adamantAlkAlchFurnace, "adamantAlkAlchFurnace");
        GameRegistry.registerTileEntity(
                TileAdamantAlkimiumAlchemicalFurnace.class,
                TileAdamantAlkimiumAlchemicalFurnace.tileEntityName);

        GameRegistry.registerBlock(mithminiteAlkAlchFurnace, "mithminiteAlkAlchFurnace");
        GameRegistry.registerTileEntity(
                TileMithminiteAlkimiumAlchemicalFurnace.class,
                TileMithminiteAlkimiumAlchemicalFurnace.tileEntityName);

        GameRegistry
                .registerBlock(blockPlanarDevice, ItemBlockPlanarDevice.class, blockPlanarDevice.getUnlocalizedName());

        GameRegistry.registerBlock(
                blockFurnaceDevice,
                ItemBlockFurnaceDevice.class,
                blockFurnaceDevice.getUnlocalizedName());
    }

}
