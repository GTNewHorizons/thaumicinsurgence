package thaumicinsurgence.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import thaumcraft.client.lib.UtilsFX;
import thaumicinsurgence.common.containers.ContainerAdvancedWand;

public class GuiAdvancedWand extends GuiContainer {

    private int blockSlot;

    public GuiAdvancedWand(InventoryPlayer player, World world, int x, int y, int z) {
        super(new ContainerAdvancedWand(player, world, x, y, z));
        this.blockSlot = player.currentItem;
        this.xSize = 175;
        this.ySize = 232;

    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        UtilsFX.bindTexture("textures/gui/gui_focuspouch.png");
        float t = this.zLevel;
        this.zLevel = 200.0F;
        GL11.glEnable(3042);
        this.drawTexturedModalRect(8 + this.blockSlot * 18, 209, 240, 0, 16, 16);
        GL11.glDisable(3042);
        this.zLevel = t;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        if (this.mc.thePlayer.inventory.mainInventory[this.blockSlot] == null) {
            this.mc.thePlayer.closeScreen();
        }

        UtilsFX.bindTexture("textures/gui/gui_focuspouch.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        int var5 = (this.width - this.xSize) / 2;
        int var6 = (this.height - this.ySize) / 2;
        GL11.glEnable(3042);
        this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
        GL11.glDisable(3042);
    }
}
