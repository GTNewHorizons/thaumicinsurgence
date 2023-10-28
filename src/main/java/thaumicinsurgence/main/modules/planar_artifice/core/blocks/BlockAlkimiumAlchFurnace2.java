package thaumicinsurgence.main.modules.planar_artifice.core.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.common.lib.utils.InventoryUtils;
import thaumicinsurgence.main.ThaumicInsurgence;
import thaumicinsurgence.main.modules.planar_artifice.core.blocks.tiles.TileAlkimiumAlchemicalFurnace;
import thaumicinsurgence.main.modules.planar_artifice.utils.TabPlanarArtifice;

import java.util.Random;

public class BlockAlkimiumAlchFurnace2 extends BlockContainer {

    public static String blocks[] = {"basic", "thaumium", "void"};

    public IIcon[][] icons = new IIcon[blocks.length][6];

    public BlockAlkimiumAlchFurnace2() {
        super(Material.rock);
        this.setCreativeTab(TabPlanarArtifice.tabPlanarArtifice);
        this.setHardness(2);
        this.setResistance(3);
        this.setBlockName("PA_FurnaceDevice");
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister ir) {
        for (int i = 0; i < blocks.length; i++) {
            icons[i][0] = ir.registerIcon("planarartifice:furnaces/alkimium_smeltery_" + blocks[i] + "_side");
            icons[i][1] = ir.registerIcon("planarartifice:furnaces/alkimium_smeltery_top");
            icons[i][2] = ir.registerIcon("planarartifice:furnaces/alkimium_smeltery_top");
            icons[i][3] = ir.registerIcon("planarartifice:furnaces/alkimium_smeltery_" + blocks[i] + "_front");
            icons[i][4] = ir.registerIcon("planarartifice:furnaces/alkimium_smeltery_" + blocks[i] + "_front_on");
            if (i == 0) {icons[i][5] = ir.registerIcon("thaumcraft:al_furnace_side");}
            else {icons[i][5] = ir.registerIcon("planarartifice:bottom_" + blocks[i]);}
        }
    }

    @Override
    public TileEntity createNewTileEntity(World w, int meta) {
        switch (meta) {
            default:
                return new TileAlkimiumAlchemicalFurnace();
        }
    }

    public IIcon getIcon(int side, int md) {
        return side == 0 || side == 1 ? icons[0][1] : side == 3 ? icons[0][3] : icons[0][0];
    }

    public IIcon getIcon(IBlockAccess w, int x, int y, int z, int side) {
        int meta = 0;
        TileAlkimiumAlchemicalFurnace tile = (TileAlkimiumAlchemicalFurnace) w.getTileEntity(x, y, z);
        if (side == w.getBlockMetadata(x, y, z)) {
            if (tile.burnRemaining()) {
                return icons[meta][4];
            } else {
                return icons[meta][3];
            }
        }

        if (side == 0) {
            return icons[meta][5];
        }

        // I've done plenty of testing, this entire set is glitchy as heck.
        // Both in vanilla as well as my code, it's really inconsistent.
        // Sometimes it's on when it shouldn't be, sometimes it's off when it shouldn't be
        // perhaps I should look into this eventually?
        if (side == 1) {
            // I tried messing with it, it seems like there's
            // some jank going on with the vis variable.
            // Best to leave it as is for now.
            if (tile.vis > 0) {
                // The first trigger is when the alembics are all full
                // (or at least, it can't add more to them)
                // that's probably the first place I should start looking.
                return icons[meta][2];
            } else {
                return icons[meta][1];
            }
        }
        return icons[meta][0];
    }

    public int getLightValue(IBlockAccess w, int x, int y, int z) {
        TileAlkimiumAlchemicalFurnace tile = (TileAlkimiumAlchemicalFurnace) w.getTileEntity(x, y, z);
        return tile.burnRemaining() ? 12 : 0;
    }

    public boolean hasComparatorInputOverride() {
        return true;
    }

    public int getComparatorInputOverride(World w, int x, int y, int z, int rs) {
        return Container.calcRedstoneFromInventory((IInventory) w.getTileEntity(x, y, z));
    }

    public void breakBlock(World w, int x, int y, int z, Block b, int meta) {
        InventoryUtils.dropItems(w, x, y, z);
        TileEntity tileEntity = w.getTileEntity(x, y, z);
        super.breakBlock(w, x, y, z, b, meta);
    }

    public void onNeighborBlockChange(World w, int x, int y, int z, Block changed) {
        ((TileAlkimiumAlchemicalFurnace) w.getTileEntity(x, y, z)).getBellows();
    }

    public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer player, int side, float par7, float par8,
            float par9) {
        if (w.isRemote) return true;
        TileEntity tileEntity = w.getTileEntity(x, y, z);

        if (!player.isSneaking() && tileEntity instanceof TileAlkimiumAlchemicalFurnace) {
            player.openGui(ThaumicInsurgence.instance, 9, w, x, y, z);
            return true;
        }

        return false;
    }

    public void onBlockAdded(World w, int x, int y, int z) {
        super.onBlockAdded(w, x, y, z);
        this.determineOrientation(w, x, y, z);
    }

    private void determineOrientation(World w, int x, int y, int z) {
        if (!w.isRemote) {
            Block block = w.getBlock(x, y, z - 1);
            Block block1 = w.getBlock(x, y, z + 1);
            Block block2 = w.getBlock(x - 1, y, z);
            Block block3 = w.getBlock(x + 1, y, z);
            byte b0 = 3;

            if (block.func_149730_j() && !block1.func_149730_j()) {
                b0 = 3;
            }

            if (block1.func_149730_j() && !block.func_149730_j()) {
                b0 = 2;
            }

            if (block2.func_149730_j() && !block3.func_149730_j()) {
                b0 = 5;
            }

            if (block3.func_149730_j() && !block2.func_149730_j()) {
                b0 = 4;
            }

            w.setBlockMetadataWithNotify(x, y, z, b0, 2);
            // this is the part that is both necessary and what is fucking me
        }
    }



    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(final World w, final int x, final int y, final int z, final Random r) {
        final TileEntity te = w.getTileEntity(x, y, z);
        if (te instanceof TileAlkimiumAlchemicalFurnace
                && ((TileAlkimiumAlchemicalFurnace) te).burnRemaining()) {
            final float f = x + 0.5f;
            final float f2 = y + 0.2f + r.nextFloat() * 5.0f / 16.0f;
            final float f3 = z + 0.5f;
            final float f4 = 0.52f;
            final float f5 = r.nextFloat() * 0.5f - 0.25f;

            if (w.getBlockMetadata(x, y, z) == 4) {
                w.spawnParticle("smoke", (double) (f - f4), (double) f2, (double) (f3 + f5), 0.0, 0.0, 0.0);
                w.spawnParticle("flame", (double) (f - f4), (double) f2, (double) (f3 + f5), 0.0, 0.0, 0.0);
            }
            if (w.getBlockMetadata(x, y, z) == 5) {
                w.spawnParticle("smoke", (double) (f + f4), (double) f2, (double) (f3 + f5), 0.0, 0.0, 0.0);
                w.spawnParticle("flame", (double) (f + f4), (double) f2, (double) (f3 + f5), 0.0, 0.0, 0.0);
            }
            if (w.getBlockMetadata(x, y, z) == 2) {
                w.spawnParticle("smoke", (double) (f + f5), (double) f2, (double) (f3 - f4), 0.0, 0.0, 0.0);
                w.spawnParticle("flame", (double) (f + f5), (double) f2, (double) (f3 - f4), 0.0, 0.0, 0.0);
            }
            if (w.getBlockMetadata(x, y, z) == 3) {
                w.spawnParticle("smoke", (double) (f + f5), (double) f2, (double) (f3 + f4), 0.0, 0.0, 0.0);
                w.spawnParticle("flame", (double) (f + f5), (double) f2, (double) (f3 + f4), 0.0, 0.0, 0.0);
            }
        }
    }
}
