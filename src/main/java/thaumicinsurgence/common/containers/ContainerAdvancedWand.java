package thaumicinsurgence.common.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import thaumcraft.api.wands.ItemFocusBasic;
import thaumcraft.common.container.SlotLimitedByClass;

public class ContainerAdvancedWand extends Container {

    private World worldObj;
    private int posX;
    private int posY;
    private int posZ;
    private int blockSlot;
    public IInventory input = new InventoryAdvancedWand(this);
    ItemStack item = null;
    EntityPlayer player = null;

    public ContainerAdvancedWand(InventoryPlayer inventory, World world, int x, int y, int z) {
        this.worldObj = world;
        this.posX = x;
        this.posY = y;
        this.posZ = z;
        this.player = inventory.player;
        this.item = inventory.getCurrentItem();
        this.blockSlot = inventory.currentItem + 45; // replace I don't know what this does.

        for (int a = 0; a < 4; ++a) {
            this.addSlotToContainer(new SlotLimitedByClass(ItemFocusBasic.class, this.input, a, 37 + a % 4 * 18, 51));
        }

    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }
}
