package thaumicinsurgence.main.utils;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class ItemStackUtils {

    /**
     * Attempts to add all items to the inventory, starting at startSlot and considering slotCount item slots.
     * @return Number of items successfully added to the inventory.
     */
    public static int tryAddItemStacksToInvenotry(
            IInventory inventory, ItemStack[] items, int startSlot, int slotCount) {
        int itemsAdded = 0;

        for (ItemStack item : items) {
            itemsAdded += addItemToInventory(inventory, item, startSlot, slotCount);
        }

        return itemsAdded;
    }

    /**
     * Attempts to add all items to the inventory, starting at startSlot and considering slotCount item slots.
     * @return true if and only if all items were successfully added to the inventory.
     */
    public static boolean tryAddAllItemStacksToInventory(
            IInventory inventory, ItemStack[] items, int startSlot, int slotCount) {
        int itemsToAdd = 0;
        int itemsAdded = 0;

        for (ItemStack item : items) {
            itemsToAdd += item.stackSize;
            itemsAdded += addItemToInventory(inventory, item, startSlot, slotCount);
        }

        return itemsToAdd == itemsAdded;
    }

    /**
     * Attempts to add an item to an inventory, starting at startSlot and considering slotCount item slots.
     * @return number of items in the stack successfully added to the inventory.
     */
    public static int addItemToInventory(IInventory inventory, ItemStack item, int startSlot, int slotCount) {
        int itemsAdded = 0;

        itemsAdded = addToExistingSlot(inventory, item, startSlot, slotCount);
        if (itemsAdded < item.stackSize) {
            item.stackSize -= itemsAdded;
            itemsAdded += addToEmptySlot(inventory, item, startSlot, slotCount);
        }

        return itemsAdded;
    }

    private static int addToExistingSlot(IInventory inventory, ItemStack item, int startSlot, int slotCount) {
        int itemsAdded = 0;

        for (int slotIndex = startSlot; slotIndex < (startSlot + slotCount); ++slotIndex) {
            ItemStack slotStack = inventory.getStackInSlot(slotIndex);

            // Skip empty slots.
            if (slotStack != null && areItemsEqualNBT(item, slotStack)) {
                int spaceAvailable = slotStack.getMaxStackSize() - slotStack.stackSize;

                if (spaceAvailable > item.stackSize) {
                    slotStack.stackSize += item.stackSize;
                    inventory.setInventorySlotContents(slotIndex, slotStack);
                    itemsAdded += item.stackSize;
                    item.stackSize = 0;
                } else if (spaceAvailable > 0) {
                    slotStack.stackSize = slotStack.getMaxStackSize();
                    inventory.setInventorySlotContents(slotIndex, slotStack);
                    itemsAdded += spaceAvailable;
                    item.stackSize -= spaceAvailable;
                }
            }

            // Don't add beyond what we have.
            if (item.stackSize == 0) {
                break;
            }
        }

        return itemsAdded;
    }

    private static int addToEmptySlot(IInventory inventory, ItemStack item, int startSlot, int slotCount) {
        for (int slotIndex = startSlot; slotIndex < (startSlot + slotCount); ++slotIndex) {
            ItemStack slotStack = inventory.getStackInSlot(slotIndex);

            if (slotStack == null) {
                inventory.setInventorySlotContents(slotIndex, item);
                return item.stackSize;
            }
        }

        return 0;
    }

    public static boolean areItemsEqualNBT(ItemStack item, ItemStack otherItem) {
        return item.isItemEqual(otherItem) && ItemStack.areItemStackTagsEqual(item, otherItem);
    }
}
