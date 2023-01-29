package thaumicinsurgence.renderers.blockrenderers;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import thaumcraft.client.renderers.block.BlockRenderer;
import thaumicinsurgence.main.Config;
import thaumicinsurgence.tileentity.TileEntityInfusionPillarAlpha;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BlockPillarAlphaRenderer extends BlockRenderer implements ISimpleBlockRenderingHandler {

    @SideOnly(Side.CLIENT)
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        TileEntityRendererDispatcher.instance
                .renderTileEntityAt(new TileEntityInfusionPillarAlpha(), 0.0D, 0.0D, 0.0D, 0.0F);
    }

    @SideOnly(Side.CLIENT)
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
            RenderBlocks renderer) {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public int getRenderId() {
        return Config.blockStoneDeviceTwoRI;
    }
}
