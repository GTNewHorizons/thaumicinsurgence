package thaumicinsurgence.main.modules.arcana.core.blocks.saplings.untainted;

import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import thaumicinsurgence.main.modules.arcana.Arcana;
import thaumicinsurgence.main.modules.arcana.common.event.WorldGenBigOak;
import thaumicinsurgence.main.modules.arcana.common.event.WorldGenOak;
import thaumicinsurgence.main.modules.arcana.utils.ArcanaBlocks;
import thaumicinsurgence.main.modules.arcana.utils.TabArcana;

import java.util.List;
import java.util.Random;

public class BlockArcanaUntaintedSapling extends BlockSapling {

    public static String saplings[] = {
            "trypophobius", "eucalyptus", "hawthorn", "willow",
            "dair", "dead", "greatwood", "silverwood"};
    IIcon icons[] = new IIcon[saplings.length];

    String blockType = "saplings/untainted/";

    public BlockArcanaUntaintedSapling() {
        super();
        setCreativeTab(TabArcana.tabArcana);
        setBlockName("AR_UntaintedSapling");
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return icons[meta % 8];
    }

    // doGrowTree
    public void func_149878_d(World w, int x, int y, int z, Random rnd) {
        int meta = w.getBlockMetadata(x, y, z) % 8;
        if (!net.minecraftforge.event.terraingen.TerrainGen.saplingGrowTree(w, rnd, x, y, z)) return;
        switch (meta){
            case 1:
                w.setBlockToAir(x, y, z);
                new WorldGenBigOak(true, 6, 1, 6, 3, ArcanaBlocks.blockArcanaLogDeviceOne, ArcanaBlocks.blockArcanaLeafDevice).generate(w, rnd, x, y, z);
                return;
            case 2:
                w.setBlockToAir(x, y, z);
                new WorldGenOak(true, 5, 2, 7, false, ArcanaBlocks.blockArcanaLogDeviceOne, ArcanaBlocks.blockArcanaLeafDevice).generate(w, rnd, x, y, z);
        }
    }

    public int damageDropped(int meta) {
        return meta;
    }


    @Override
    public void registerBlockIcons(IIconRegister reg) {
        for (int i = 0; i < saplings.length; i++) {
            icons[i] = reg.registerIcon(Arcana.arcanaLabel + blockType + saplings[i] + "_sapling");
        }
    }
}
