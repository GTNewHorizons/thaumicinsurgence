package thaumicinsurgence.main.modules.arcana.utils;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import thaumicinsurgence.main.modules.arcana.Arcana;
import thaumicinsurgence.main.modules.arcana.core.blocks.stone.BlockArcanaStoneDevice;
import thaumicinsurgence.main.modules.arcana.core.blocks.stone.ItemBlockArcanaStoneDevice;
import thaumicinsurgence.main.modules.arcana.core.blocks.wood.BlockArcanaTaintedWoodDevice;
import thaumicinsurgence.main.modules.arcana.core.blocks.wood.BlockArcanaWoodDevice;
import thaumicinsurgence.main.modules.arcana.core.blocks.wood.ItemBlockArcanaTaintedWoodDevice;
import thaumicinsurgence.main.modules.arcana.core.blocks.wood.ItemBlockArcanaWoodDevice;
import thaumicinsurgence.main.modules.arcana.core.blocks.wood.logs.*;

public class ArcanaBlocks {

    public static final Class<Arcana> core = Arcana.class;

    public static Block blockArcanaStoneDevice = new BlockArcanaStoneDevice();
    public static Block blockArcanaWoodDevice = new BlockArcanaWoodDevice();
    public static Block blockArcanaTaintedWoodDevice = new BlockArcanaTaintedWoodDevice();
    public static Block blockArcanaLogDeviceOne = new BlockArcanaLogDeviceOne();
    public static Block blockArcanaLogDeviceTwo = new BlockArcanaLogDeviceTwo();
    public static Block blockArcanaTaintedLogDeviceOne = new BlockArcanaTaintedLogDeviceOne();
    public static Block blockArcanaTaintedLogDeviceTwo = new BlockArcanaTaintedLogDeviceTwo();
    public static Block blockArcanaTaintedLogDeviceThree = new BlockArcanaTaintedLogDeviceThree();

    public static void setup() {
        GameRegistry.registerBlock(
                blockArcanaStoneDevice,
                ItemBlockArcanaStoneDevice.class,
                blockArcanaStoneDevice.getUnlocalizedName()
        );

        GameRegistry.registerBlock(
                blockArcanaWoodDevice,
                ItemBlockArcanaWoodDevice.class,
                blockArcanaWoodDevice.getUnlocalizedName()
        );

        GameRegistry.registerBlock(
                blockArcanaTaintedWoodDevice,
                ItemBlockArcanaTaintedWoodDevice.class,
                blockArcanaTaintedWoodDevice.getUnlocalizedName()
        );

        GameRegistry.registerBlock(
                blockArcanaLogDeviceOne,
                ItemBlockArcanaLogDeviceOne.class,
                blockArcanaLogDeviceOne.getUnlocalizedName()
        );

        GameRegistry.registerBlock(
                blockArcanaLogDeviceTwo,
                ItemBlockArcanaLogDeviceTwo.class,
                blockArcanaLogDeviceTwo.getUnlocalizedName()
        );

        GameRegistry.registerBlock(
                blockArcanaTaintedLogDeviceOne,
                ItemBlockArcanaTaintedLogDeviceOne.class,
                blockArcanaTaintedLogDeviceOne.getUnlocalizedName()
        );

        GameRegistry.registerBlock(
                blockArcanaTaintedLogDeviceTwo,
                ItemBlockArcanaTaintedLogDeviceTwo.class,
                blockArcanaTaintedLogDeviceTwo.getUnlocalizedName()
        );

        GameRegistry.registerBlock(
                blockArcanaTaintedLogDeviceThree,
                ItemBlockArcanaTaintedLogDeviceThree.class,
                blockArcanaTaintedLogDeviceThree.getUnlocalizedName()
        );
    }

}