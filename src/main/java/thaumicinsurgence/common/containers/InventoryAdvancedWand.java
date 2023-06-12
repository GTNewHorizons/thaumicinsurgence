package thaumicinsurgence.common.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class InventoryAdvancedWand implements IInventory {

    public ItemStack[] stackList = new ItemStack[4];
    private Container eventHandler;

    public InventoryAdvancedWand(Container container) {
        this.eventHandler = container;
    }

    @Override
    public int getSizeInventory() {
        return 4;
    }

    @Override
    public ItemStack getStackInSlot(int slotIn) {
        if (this.stackList[slotIn] != null) {
            ItemStack var2 = this.stackList[slotIn];
            this.stackList[slotIn] = null;
            return var2;
        } else {
            return null;
        }
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        if (this.stackList[index] != null) {
            if (this.stackList[index].stackSize <= count) {
                ItemStack var3 = this.stackList[index];
                this.stackList[index] = null;
                this.eventHandler.onCraftMatrixChanged(this);
                return var3;
            } else {
                ItemStack var3 = this.stackList[index].splitStack(count);
                if (this.stackList[index].stackSize == 0) {
                    this.stackList[index] = null;
                }

                this.eventHandler.onCraftMatrixChanged(this);
                return var3;
            }
        } else {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int index) {
        if (this.stackList[index] != null) {
            ItemStack stack = this.stackList[index];
            this.stackList[index] = null;
            return stack;
        } else {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        this.stackList[index] = stack;
        this.eventHandler.onCraftMatrixChanged(this);
    }

    @Override
    public String getInventoryName() {
        return "container.AdvancedWand";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    @Override
    public void markDirty() {

    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return true;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return true;
    }
}
