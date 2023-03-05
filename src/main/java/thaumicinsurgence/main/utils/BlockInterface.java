package thaumicinsurgence.main.utils;

import net.minecraft.block.Block;

import cpw.mods.fml.common.registry.GameRegistry;

public class BlockInterface {

    public static Block getBlock(String item) {
        return GameRegistry.findBlock("Forestry", item);
    }

    public static Block getBlock(String modId, String item) {
        return GameRegistry.findBlock(modId, item);
    }
}
