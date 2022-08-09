package thaumicinsurgence.main.utils;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemInterface {
    public static ItemStack getItemStack(String item) {
        return GameRegistry.findItemStack("Forestry", item, 1);
    }

    public static ItemStack getItemStack(String modId, String item) {
        return GameRegistry.findItemStack(modId, item, 1);
    }

    public static ItemStack getItemStack(String modId, String item, int stackSize) {
        return GameRegistry.findItemStack(modId, item, stackSize);
    }

    public static Item getItem(String item) {
        Item i;
        i = GameRegistry.findItem("Forestry", item);
        if (i == null) {
            LogHelper.warn("Trying to get Item " + item + " from Forestry which does not exist!");
        }
        return i;
    }

    public static Item getItem(String modId, String item) {
        Item i;
        i = GameRegistry.findItem(modId, item);
        if (i == null) {
            LogHelper.warn("Trying to get Item " + item + " from " + modId + " which does not exist!");
        }
        return i;
    }
}
