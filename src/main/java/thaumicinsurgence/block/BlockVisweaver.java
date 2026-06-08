package thaumicinsurgence.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumicinsurgence.api.VisweaverRecipeMap;
import thaumicinsurgence.main.Config;
import thaumicinsurgence.main.ThaumicInsurgence;
import thaumicinsurgence.main.utils.compat.ThaumcraftHelper;
import thaumicinsurgence.tileentity.TileEntityVisweaver;

public class BlockVisweaver extends BlockContainer {

    public BlockVisweaver() {
        super(Material.wood);
        setHardness(5F);
        setResistance(10F);

        this.setBlockName("visweaver");
        this.setBlockTextureName("ThaumicInsurgence:visweaver");

        VisweaverRecipeMap.putRecipe(Aspect.FIRE, 25, new ItemStack(Items.bed), new ItemStack(Items.spider_eye));
        VisweaverRecipeMap.putRecipe(Aspect.EARTH, 50, new ItemStack(Items.brick), new ItemStack(Items.netherbrick));
    }

    public boolean onBlockActivated(final World world, final int x, final int y, final int z, final EntityPlayer player,
            final int side, final float subX, final float subY, final float subZ) {
        if (ThaumcraftApiHelper.isResearchComplete(player.getCommandSenderName(), "visweaver")) {
            player.openGui(ThaumicInsurgence.instance, 0, world, x, y, z);
        }
        return true;
    }

    public void breakBlock(final World world, final int x, final int y, final int z, final Block block, final int md) {
        final TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof final TileEntityVisweaver tile) {
            ItemStack input = tile.getStackInSlot(0);
            ItemStack output = tile.getStackInSlot(1);
            if (input != null) {
                world.spawnEntityInWorld(new EntityItem(world, x, y, z, input));
            }
            if (output != null) {
                world.spawnEntityInWorld(new EntityItem(world, x, y, z, output));
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return ThaumcraftHelper.wooden.getIcon(1, 6);
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata) {
        return createNewTileEntity(world, metadata);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int var2) {
        return new TileEntityVisweaver();
    }

    @Override
    public int getRenderType() {
        return Config.visweaverRI;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }
}
