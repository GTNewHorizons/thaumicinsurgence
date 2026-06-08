package thaumicinsurgence.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.ForgeDirection;

import org.apache.commons.lang3.ArrayUtils;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.visnet.VisNetHandler;
import thaumicinsurgence.api.VisweaverRecipe;
import thaumicinsurgence.api.VisweaverRecipeMap;

public class TileEntityVisweaver extends TileEntity implements ISidedInventory {

    private static final String TAG_INTERNAL_VIS = "internalVis";
    private static final String TAG_REQUIRED_VIS = "requiredVis";
    private static final String TAG_WORKING = "working";
    private static final String TAG_ASPECT = "aspect";
    private static final String TAG_INPUT = "currentInput";
    private static final String TAG_OUTPUT = "currentOutput";
    private static final String TAG_TICKS = "tickCounter";

    private int tickCounter = -1;
    private int internalVis = 0;
    private boolean working = false;
    private ItemStack currentOutput;
    private ItemStack currentInput;
    private Aspect aspect;
    private int requiredVis;

    ItemStack[] inventorySlots = new ItemStack[2];
    private static final int[] slotIndices = { 0, 1 };

    @Override
    public void updateEntity() {
        tickCounter++;
        if (worldObj.isRemote) return;
        ItemStack currentStack = getStackInSlot(0);
        if (working) {
            if (currentStack == null || currentInput == null || !currentStack.isItemEqual(currentInput)) {
                flushRecipe();
                worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
            }
        }
        if (tickCounter % 5 != 0) return;
        if (!working) {
            recipeCheck();
        } else {
            // This is guaranteed by the above check but I want the linter to shut up
            if (currentStack != null) {
                internalVis += VisNetHandler.drainVis(
                        this.worldObj,
                        this.xCoord,
                        this.yCoord,
                        this.zCoord,
                        aspect,
                        currentStack.stackSize * requiredVis - internalVis);
            }
            progressRecipe();
            worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        }
    }

    private void progressRecipe() {
        if (internalVis >= requiredVis) {
            internalVis -= requiredVis;
            finishRecipe();
        }
    }

    private void flushRecipe() {
        internalVis = 0;
        requiredVis = 0;
        currentOutput = null;
        currentInput = null;
        aspect = null;
        working = false;
    }

    private void recipeCheck() {
        VisweaverRecipe recipe = VisweaverRecipeMap.lookup(getStackInSlot(0));
        if (recipe != null) {
            ItemStack output = recipe.output();
            if (canAddToSlot(output, 1) <= 0) return;
            aspect = recipe.aspect();
            requiredVis = recipe.cost();
            currentOutput = output;
            currentInput = recipe.input();
            working = true;
        }
    }

    private void finishRecipe() {
        decrStackSize(0, 1);
        addToSlot(currentOutput, 1);
        ItemStack input = getStackInSlot(0);
        if (input != null && currentInput.isItemEqual(input) && canAddToSlot(currentOutput, 1) > 0) {
            progressRecipe();
        } else {
            flushRecipe();
            recipeCheck();
        }
    }

    @Override
    public int getSizeInventory() {
        return inventorySlots.length;
    }

    @Override
    public ItemStack getStackInSlot(int slotIn) {
        return inventorySlots[slotIn];
    }

    @Override
    public ItemStack decrStackSize(int slot, int count) {
        if (inventorySlots[slot] == null) {
            return null;
        }
        ItemStack stackAt;

        if (inventorySlots[slot].stackSize <= count) {
            stackAt = inventorySlots[slot];
            inventorySlots[slot] = null;
        } else {
            stackAt = inventorySlots[slot].splitStack(count);
            if (inventorySlots[slot].stackSize == 0) inventorySlots[slot] = null;
        }
        return stackAt;
    }

    /**
     * Returns the amount of items from the input stack that can be stacked into this slot
     */
    public int canAddToSlot(ItemStack input, int slot) {
        ItemStack currentStack = getStackInSlot(slot);
        if (currentStack == null) return Math.min(64, input.stackSize);
        if (!currentStack.isItemEqual(input)) return 0;
        return Math.min(input.stackSize, 64 - currentStack.stackSize);
    }

    public void addToSlot(ItemStack stack, int slot) {
        int movable = canAddToSlot(stack, slot);
        if (movable == 0) return;
        ItemStack currentStack = getStackInSlot(slot);
        ItemStack newStack = stack.copy();

        if (currentStack == null) {
            newStack.stackSize = movable;
            setInventorySlotContents(slot, newStack);
        } else {
            currentStack.stackSize += movable;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) {
        return getStackInSlot(i);
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack stack) {
        inventorySlots[i] = stack;
    }

    @Override
    public String getInventoryName() {
        return "container.visweaver";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer) {
        return worldObj.getTileEntity(xCoord, yCoord, zCoord) == this
                && entityplayer.getDistanceSq(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D) <= 64;
    }

    @Override
    public void openInventory() {}

    @Override
    public void closeInventory() {}

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        super.onDataPacket(net, pkt);
        readFromNBT(pkt.func_148857_g());
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);

        working = tag.getBoolean(TAG_WORKING);
        internalVis = tag.getInteger(TAG_INTERNAL_VIS);
        requiredVis = tag.getInteger(TAG_REQUIRED_VIS);
        tickCounter = tag.getInteger(TAG_TICKS);
        aspect = Aspect.getAspect(tag.getString(TAG_ASPECT));
        currentInput = ItemStack.loadItemStackFromNBT(tag.getCompoundTag(TAG_INPUT));
        currentOutput = ItemStack.loadItemStackFromNBT(tag.getCompoundTag(TAG_OUTPUT));

        NBTTagList items = tag.getTagList("Items", Constants.NBT.TAG_COMPOUND);
        inventorySlots = new ItemStack[getSizeInventory()];
        for (int i = 0; i < items.tagCount(); ++i) {
            NBTTagCompound item = items.getCompoundTagAt(i);
            byte slot = item.getByte("Slot");
            if (slot >= 0 && slot < inventorySlots.length) inventorySlots[slot] = ItemStack.loadItemStackFromNBT(item);
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);

        NBTTagCompound input = new NBTTagCompound();
        NBTTagCompound output = new NBTTagCompound();

        tag.setBoolean(TAG_WORKING, working);
        tag.setInteger(TAG_INTERNAL_VIS, internalVis);
        tag.setInteger(TAG_REQUIRED_VIS, requiredVis);
        tag.setInteger(TAG_TICKS, tickCounter);

        if (working) {
            tag.setString(TAG_ASPECT, aspect.getTag());
            tag.setTag(TAG_INPUT, currentInput.writeToNBT(input));
            tag.setTag(TAG_OUTPUT, currentOutput.writeToNBT(output));
        }

        NBTTagList items = new NBTTagList();
        for (int i = 0; i < inventorySlots.length; ++i) {
            if (inventorySlots[i] != null) {
                NBTTagCompound item = new NBTTagCompound();
                item.setByte("Slot", (byte) i);
                inventorySlots[i].writeToNBT(item);
                items.appendTag(item);
            }
        }
        tag.setTag("Items", items);
    }

    @Override
    public S35PacketUpdateTileEntity getDescriptionPacket() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        writeToNBT(nbttagcompound);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, -999, nbttagcompound);
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return index == 0;
    }

    public boolean isWorking() {
        return working;
    }

    public int getTickCounter() {
        return tickCounter;
    }

    public Aspect getAspect() {
        return aspect;
    }

    public int getInternalVis() {
        return internalVis;
    }

    public int getRequiredVis() {
        return requiredVis;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side) {
        if (side == ForgeDirection.UP.ordinal()) return ArrayUtils.EMPTY_INT_ARRAY;
        return slotIndices;
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStack, int ordinalSide) {
        return index == 0;
    }

    @Override
    public boolean canExtractItem(int index, ItemStack itemStack, int ordinalSide) {
        return index == 1;
    }
}
