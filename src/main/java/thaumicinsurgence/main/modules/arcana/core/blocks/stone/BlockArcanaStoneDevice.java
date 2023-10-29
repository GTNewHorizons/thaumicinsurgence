package thaumicinsurgence.main.modules.arcana.core.blocks.stone;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumicinsurgence.main.modules.arcana.Arcana;
import thaumicinsurgence.main.modules.arcana.utils.TabArcana;
import thaumicinsurgence.main.modules.planar_artifice.utils.TabPlanarArtifice;

public class BlockArcanaStoneDevice extends BlockContainer {


    IIcon icons[] = new IIcon[255];
    //, "bottom_mithrillium", "bottom_adaminite", "bottom_mithminite"
   String blockType = "stone/";


    public BlockArcanaStoneDevice() {
        super(Material.iron);
        setHardness(6.0F);
        setResistance(34.0F);
        setStepSound(Block.soundTypeMetal);
        setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        setCreativeTab(TabArcana.tabArcana);
        setBlockName("AR_StoneDevice");
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return null;
    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {
        icons[0] = reg.registerIcon(Arcana.arcanaLabel + blockType + "arcane_stone_bricks");
        icons[1] = reg.registerIcon(Arcana.arcanaLabel + blockType + "arcane_stone");
        icons[2] = reg.registerIcon(Arcana.arcanaLabel + blockType + "amber_ore");
        icons[3] = reg.registerIcon(Arcana.arcanaLabel + blockType + "cinnabar_ore");
        icons[4] = reg.registerIcon(Arcana.arcanaLabel + blockType + "tainted_rock");
        icons[5] = reg.registerIcon(Arcana.arcanaLabel + blockType + "tainted_gold_ore");
        icons[6] = reg.registerIcon(Arcana.arcanaLabel + blockType + "tainted_iron_ore");
        icons[7] = reg.registerIcon(Arcana.arcanaLabel + blockType + "tainted_coal_ore");
        icons[8] = reg.registerIcon(Arcana.arcanaLabel + blockType + "tainted_lapis_ore");
        icons[9] = reg.registerIcon(Arcana.arcanaLabel + blockType + "tainted_diamond_ore");
        icons[10] = reg.registerIcon(Arcana.arcanaLabel + blockType + "tainted_redstone_ore");
        icons[11] = reg.registerIcon(Arcana.arcanaLabel + blockType + "tainted_emerald_ore");
        icons[12] = reg.registerIcon(Arcana.arcanaLabel + blockType + "tainted_amber_ore");
        icons[13] = reg.registerIcon(Arcana.arcanaLabel + blockType + "tainted_cinnabar_ore");
        icons[14] = reg.registerIcon(Arcana.arcanaLabel + blockType + "tainted_arcanium_ore");
        icons[15] = reg.registerIcon(Arcana.arcanaLabel + blockType + "tainted_destroyed_ore");
    }

    @Override
    public int damageDropped(int meta) {
        return meta;
    }

    @Override
    // this is the method that displays it in inventory
    public IIcon getIcon(int side, int meta) {
        return getMetaIcon(meta, side);
    }

    @Override
    // this is the method that actually displays it in world
    public IIcon getIcon(IBlockAccess worldIn, int x, int y, int z, int side) {
        int meta = worldIn.getBlockMetadata(x, y, z);
        return getMetaIcon(meta, side);
    }

    public IIcon getMetaIcon(int meta, int side){
        int tempMeta = meta;
        if (meta == 14) tempMeta += side;
        return (tempMeta == 0?icons[2]
                :(tempMeta == 1?icons[3]
                :(tempMeta == 2?icons[4]
                :(tempMeta == 3?icons[5]
                :(tempMeta == 4?icons[6]
                :(tempMeta == 5?icons[7]
                :(tempMeta == 6?icons[8]
                :(tempMeta == 7?icons[9]
                :(tempMeta == 8?icons[10]
                :(tempMeta == 9?icons[11]
                :(tempMeta == 10?icons[12]
                :(tempMeta == 11?icons[13]
                :(tempMeta == 12?icons[14]
                :(tempMeta == 13?icons[15]
                :(tempMeta == 14 || tempMeta == 15 || tempMeta == 16)?icons[1]
                :icons[0]))))))))))))));
    }
}
