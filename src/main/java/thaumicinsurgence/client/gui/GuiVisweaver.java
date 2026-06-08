package thaumicinsurgence.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import thaumicinsurgence.common.container.ContainerVisweaver;
import thaumicinsurgence.tileentity.TileEntityVisweaver;

@SideOnly(Side.CLIENT)
public class GuiVisweaver extends GuiContainer {

    private static final ResourceLocation gui = new ResourceLocation(
            "thaumicinsurgence",
            "textures/gui/guivisweaver.png");
    public TileEntityVisweaver visweaver;
    int x, y;

    public GuiVisweaver(InventoryPlayer inv, TileEntityVisweaver visweaver) {
        super(new ContainerVisweaver(inv, visweaver));
        this.visweaver = visweaver;
    }

    @Override
    public void initGui() {
        super.initGui();

        x = (width - xSize) / 2;
        y = (height - ySize) / 2;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        GL11.glColor4f(1F, 1F, 1F, 1F);
        mc.renderEngine.bindTexture(gui);
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

        GL11.glEnable(GL11.GL_BLEND);

        if (visweaver.isWorking()) {
            int pixels = (int) (67D * ((double) visweaver.getInternalVis() / (double) visweaver.getRequiredVis()));

            // Draw aspect color to fill
            int color = visweaver.getAspect().getColor();
            GL11.glColor3ub((byte) (color >> 16 & 0xFF), (byte) (color >> 8 & 0xFF), (byte) (color & 0xFF));
            drawTexturedModalRect(x + 56, y + 35, 185, 10, pixels, 8);
        }

        // Draw aspect "container" over progressbar
        GL11.glColor3f(1F, 1F, 1F);
        drawTexturedModalRect(x + 49, y + 34, 178, 0, 78, 10);

        GL11.glDisable(GL11.GL_BLEND);
    }
}
