package thaumicinsurgence.item.tools.wands;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import thaumcraft.api.wands.IWandable;
import thaumcraft.api.wands.WandTriggerRegistry;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.tiles.TileOwned;
import thaumicinsurgence.main.ThaumicInsurgence;
import thaumicinsurgence.main.utils.TabThaumicInsurgence;

public class ItemAdvancedWandCasting extends ItemWandCasting {

    public ItemAdvancedWandCasting() {
        this.maxStackSize = 1;
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        setCreativeTab(TabThaumicInsurgence.tabThaumicInsurgence);
        setUnlocalizedName("ItemAlastorsAdvancedWand");
    }

    @Override
    public boolean onItemUseFirst(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side,
            float hitX, float hitY, float hitZ) {

        if (player.isSneaking()) {
            player.openGui(
                    ThaumicInsurgence.instance,
                    0,
                    world,
                    MathHelper.floor_double(player.posX),
                    MathHelper.floor_double(player.posY),
                    MathHelper.floor_double(player.posZ));

        }

        /*
         * TODO: Terminate this code and replace it so hard, this is from Thaumcraft and needs to be replaced.
         * Replace it or it's your ass my guy.
         */

        Block bi = world.getBlock(x, y, z);
        int md = world.getBlockMetadata(x, y, z);
        boolean result = false;
        ForgeDirection direction = ForgeDirection.getOrientation(side);
        if (bi instanceof IWandable) {
            int ret = ((IWandable) bi).onWandRightClick(world, itemstack, player, x, y, z, side, md);
            if (ret >= 0) {
                return ret == 1;
            }
        }

        TileEntity tile = world.getTileEntity(x, y, z);
        if (tile != null && tile instanceof IWandable) {
            int ret = ((IWandable) tile).onWandRightClick(world, itemstack, player, x, y, z, side, md);
            if (ret >= 0) {
                return ret == 1;
            }
        }

        if (WandTriggerRegistry.hasTrigger(bi, md)) {
            return WandTriggerRegistry.performTrigger(world, itemstack, player, x, y, z, side, bi, md);
        } else {
            if ((bi == ConfigBlocks.blockWoodenDevice && md == 2 || bi == ConfigBlocks.blockCosmeticOpaque && md == 2)
                    && (!Config.wardedStone || tile != null && tile instanceof TileOwned
                            && player.getCommandSenderName().equals(((TileOwned) tile).owner))) {
                if (!world.isRemote) {
                    ((TileOwned) tile).safeToRemove = true;
                    world.spawnEntityInWorld(
                            new EntityItem(
                                    world,
                                    (double) x + 0.5D,
                                    (double) y + 0.5D,
                                    (double) z + 0.5D,
                                    new ItemStack(bi, 1, md)));
                    world.playAuxSFX(2001, x, y, z, Block.getIdFromBlock(bi) + (md << 12));
                    world.setBlockToAir(x, y, z);
                } else {
                    player.swingItem();
                }
            }

            if (bi == ConfigBlocks.blockArcaneDoor && (!Config.wardedStone || tile != null && tile instanceof TileOwned
                    && player.getCommandSenderName().equals(((TileOwned) tile).owner))) {
                if (!world.isRemote) {
                    ((TileOwned) tile).safeToRemove = true;
                    if ((md & 8) == 0) {
                        tile = world.getTileEntity(x, y + 1, z);
                    } else {
                        tile = world.getTileEntity(x, y - 1, z);
                    }

                    if (tile != null && tile instanceof TileOwned) {
                        ((TileOwned) tile).safeToRemove = true;
                    }

                    if (Config.wardedStone || !Config.wardedStone && (md & 8) == 0) {
                        world.spawnEntityInWorld(
                                new EntityItem(
                                        world,
                                        (double) x + 0.5D,
                                        (double) y + 0.5D,
                                        (double) z + 0.5D,
                                        new ItemStack(ConfigItems.itemArcaneDoor)));
                    }

                    world.playAuxSFX(2001, x, y, z, Block.getIdFromBlock(bi) + (md << 12));
                    world.setBlockToAir(x, y, z);
                } else {
                    player.swingItem();
                }
            }

            return result;
        }
    }
}
