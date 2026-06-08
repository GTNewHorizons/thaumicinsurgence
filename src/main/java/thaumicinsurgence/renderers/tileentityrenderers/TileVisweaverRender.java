package thaumicinsurgence.renderers.tileentityrenderers;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import thaumcraft.client.lib.UtilsFX;
import thaumicinsurgence.model.ModelVisweaver;
import thaumicinsurgence.tileentity.TileEntityVisweaver;

public class TileVisweaverRender extends TileEntitySpecialRenderer {

    private static final ResourceLocation modelTex = new ResourceLocation(
            "thaumicinsurgence",
            "textures/models/visweaver.png");
    ModelVisweaver model = new ModelVisweaver();

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {

        TileEntityVisweaver visweaver = (TileEntityVisweaver) tileentity;

        GL11.glPushMatrix();
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glColor4f(1F, 1F, 1F, 1F);
        GL11.glTranslatef((float) x, (float) y, (float) z);

        UtilsFX.bindTexture(modelTex);

        GL11.glTranslatef(0.5F, 1.5F, 0.5F);
        GL11.glScalef(1F, -1F, -1F);

        model.render(visweaver.isWorking(), visweaver.getTickCounter(), visweaver.getAspect());

        GL11.glRotatef(90F, 1F, 0F, 0F);
        GL11.glTranslatef(0F, 0F, -0.6F);

        GL11.glEnable(GL12.GL_RESCALE_NORMAL);

        GL11.glPopMatrix();
    }
}
