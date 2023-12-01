package thaumicinsurgence.main.modules.arcana.core.blocks.leaves;

import java.util.ArrayList;

import net.minecraft.block.BlockOldLeaf;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import thaumicinsurgence.main.modules.arcana.Arcana;
import thaumicinsurgence.main.modules.arcana.utils.TabArcana;

public class BlockArcanaLeafDevice extends BlockOldLeaf {

    public static String blocks[] = { "dair", "oblivion", "oblivion_old", "greatwood", "silverwood", "trypophobius",
            "eucalyptus", "hawthorn", "willow" }; // 9
    IIcon icons[] = new IIcon[blocks.length];

    String[] blockNames = { "dair_leaves", "oblivion_leaves", "oblivion_leaves_old", "greatwood_leaves",
            "silverwood_leaves", "trypophobius_leaves", "eucalyptus_leaves", "hawthorn_leaves", "willow_leaves" }; // 9
    String blockType = "leaves/untainted/";

    public BlockArcanaLeafDevice() {
        super();
        setCreativeTab(TabArcana.tabArcana);
        setBlockName("AR_LeafDevice");
    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {
        for (int i = 0; i < blocks.length; i++) {
            icons[i] = reg.registerIcon(Arcana.arcanaLabel + blockType + blockNames[i]);
        }
    }

    @Override
    public int damageDropped(int meta) {
        return meta % 8;
    }

    @Override
    // this is the method that displays it in inventory
    public IIcon getIcon(int side, int meta) {
        return icons[meta % 8];
    }

    @Override
    // this is the method that actually displays it in world
    public IIcon getIcon(IBlockAccess worldIn, int x, int y, int z, int side) {
        int meta = worldIn.getBlockMetadata(x, y, z);
        return icons[meta % 8];
    }

    @SideOnly(Side.CLIENT)
    public int getBlockColor() {
        return 0xffffff;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess worldIn, int x, int y, int z, int meta) {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public int getRenderColor(int meta) {
        return 0xffffff;
    }

    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess worldIn, int x, int y, int z) {
        return 0xffffff;
    }

    // getSaplingDropRate
    protected int func_150123_b(int meta) {
        return 50;
    }

    // dropRareItem
    protected void func_150124_c(World w, int x, int y, int z, int meta, int chance) {
        // nothing yet.
    }

    @Override
    public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
        if (world.getBlockMetadata(x, y, z) % 8 == 1) return new ArrayList<ItemStack>();
        else return super.onSheared(item, world, x, y, z, fortune);
    }
}
