package thaumicinsurgence.block;

// brought from Thaum's BlockMetalDevice, just temp

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.tiles.TileArcaneLampFertility;
import thaumcraft.common.tiles.TileArcaneLampGrowth;
import thaumicinsurgence.main.utils.TabThaumicInsurgence;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockInsurgentDevice extends BlockContainer {

    public IIcon[] icon = new IIcon[23];
    public IIcon iconGlow;
    private int delay = 0;

    public BlockInsurgentDevice() {
        super(Material.iron);
        this.setHardness(3.0F);
        this.setResistance(17.0F);
        this.setStepSound(Block.soundTypeMetal);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        this.setCreativeTab(TabThaumicInsurgence.tabThaumicInsurgence);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister ir) {
        this.icon[0] = ir.registerIcon("thaumcraft:metalbase");

        for (int a = 1; a <= 6; ++a) {
            this.icon[a] = ir.registerIcon("thaumcraft:crucible" + a);
        }

        this.icon[7] = ir.registerIcon("thaumcraft:goldbase");
        this.icon[8] = ir.registerIcon("thaumcraft:grate");
        this.icon[9] = ir.registerIcon("thaumcraft:grate_hatch");
        this.icon[10] = ir.registerIcon("thaumcraft:lamp_side");
        this.icon[11] = ir.registerIcon("thaumcraft:lamp_top");
        this.icon[12] = ir.registerIcon("thaumcraft:lamp_grow_side");
        this.icon[13] = ir.registerIcon("thaumcraft:lamp_grow_top");
        this.icon[14] = ir.registerIcon("thaumcraft:lamp_grow_side_off");
        this.icon[15] = ir.registerIcon("thaumcraft:lamp_grow_top_off");
        this.icon[16] = ir.registerIcon("thaumcraft:alchemyblock");
        this.icon[17] = ir.registerIcon("thaumcraft:brainbox");
        this.icon[18] = ir.registerIcon("thaumcraft:lamp_fert_side");
        this.icon[19] = ir.registerIcon("thaumcraft:lamp_fert_top");
        this.icon[20] = ir.registerIcon("thaumcraft:lamp_fert_side_off");
        this.icon[21] = ir.registerIcon("thaumcraft:lamp_fert_top_off");
        this.icon[22] = ir.registerIcon("thaumcraft:alchemyblockadv");
        this.iconGlow = ir.registerIcon("thaumcraft:animatedglow");
    }

    public IIcon getIcon(int i, int md) {
        return md == 3 ? this.icon[22]
                : (md == 7 ? this.icon[10]
                        : (md == 8 ? this.icon[12]
                                : (md != 10 && md != 9 && md != 11 ? (md == 12 ? this.icon[17]
                                        : (md == 13 ? this.icon[18]
                                                : (md != 14 && md != 2
                                                        ? (md != 0 && md != 1 && md != 5 && md != 6 ? this.icon[7]
                                                                : this.icon[0])
                                                        : this.icon[0])))
                                        : this.icon[16])));
    }

    public IIcon getIcon(IBlockAccess iblockaccess, int i, int j, int k, int side) {
        int metadata = iblockaccess.getBlockMetadata(i, j, k);
        if (metadata != 5 && metadata != 6) {
            if (metadata == 7) {
                return side <= 1 ? this.icon[11] : this.icon[10];
            } else {
                if (metadata == 8) {
                    TileEntity te = iblockaccess.getTileEntity(i, j, k);
                    if (te != null && te instanceof TileArcaneLampGrowth) {
                        if (((TileArcaneLampGrowth) te).charges > 0) {
                            if (side <= 1) {
                                return this.icon[13];
                            }

                            return this.icon[12];
                        }

                        if (side <= 1) {
                            return this.icon[15];
                        }

                        return this.icon[14];
                    }
                } else if (metadata == 13) {
                    TileEntity te = iblockaccess.getTileEntity(i, j, k);
                    if (te != null && te instanceof TileArcaneLampFertility) {
                        if (((TileArcaneLampFertility) te).charges > 0) {
                            if (side <= 1) {
                                return this.icon[19];
                            }

                            return this.icon[18];
                        }

                        if (side <= 1) {
                            return this.icon[21];
                        }

                        return this.icon[20];
                    }
                } else {
                    if (metadata == 10 || metadata == 9 || metadata == 11) {
                        return this.icon[16];
                    }

                    if (metadata == 12) {
                        return this.icon[17];
                    }

                    if (metadata == 3) {
                        return this.icon[22];
                    }
                }

                if (side == 1) {
                    return this.icon[1];
                } else if (side == 0) {
                    return this.icon[2];
                } else {
                    return this.icon[3];
                }
            }
        } else {
            return this.icon[8];
        }
    }

    public int getRenderType() {
        return ConfigBlocks.blockMetalDeviceRI;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return null;
    }
}
