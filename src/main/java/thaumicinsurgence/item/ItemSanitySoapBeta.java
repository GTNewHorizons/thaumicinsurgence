package thaumicinsurgence.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.items.ItemSanitySoap;
import thaumicinsurgence.main.utils.TabThaumicInsurgence;

public class ItemSanitySoapBeta extends ItemSanitySoap {

    @SideOnly(Side.CLIENT)
    public IIcon icon;

    public ItemSanitySoapBeta() {
        this.setCreativeTab(TabThaumicInsurgence.tabThaumicInsurgence);
        this.setHasSubtypes(false);
        setUnlocalizedName("ItemSanitySoapBeta");
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister ir) {
        this.icon = ir.registerIcon("thaumicinsurgence:soap_beta_4");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int par1) {
        return this.icon;
    }

    public int getMaxItemUseDuration(ItemStack p_77626_1_) {
        return 200;
    }

    public EnumAction getItemUseAction(ItemStack p_77661_1_) {
        return EnumAction.block;
    }

    public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
        p_77659_3_.setItemInUse(p_77659_1_, this.getMaxItemUseDuration(p_77659_1_));
        return p_77659_1_;
    }

    public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
        int ticks = this.getMaxItemUseDuration(stack) - count;
        if (ticks > 40) {
            player.stopUsingItem();
        }

        if (player.worldObj.isRemote) {
            if (player.worldObj.rand.nextFloat() < 0.2F) {
                player.worldObj.playSound(
                        player.posX,
                        player.posY,
                        player.posZ,
                        "thaumcraft:roots",
                        0.1F,
                        1.5F + player.worldObj.rand.nextFloat() * 0.2F,
                        false);
            }

            for (int a = 0; a < Thaumcraft.proxy.particleCount(5); ++a) {
                Thaumcraft.proxy.crucibleBubble(
                        Thaumcraft.proxy.getClientWorld(),
                        (float) player.posX - 0.5F + player.worldObj.rand.nextFloat(),
                        (float) player.boundingBox.minY + player.worldObj.rand.nextFloat() * player.height,
                        (float) player.posZ - 0.5F + player.worldObj.rand.nextFloat(),
                        1.0F,
                        0.8F,
                        0.9F);
            }
        }
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int par4) {
        int qq = this.getMaxItemUseDuration(stack) - par4;
        if (qq > 40) {
            --stack.stackSize;
            if (!world.isRemote) {
                float chance = 0.10F;
                if (player.isPotionActive(Config.potionWarpWardID)) {
                    chance += 0.15F;
                }

                int i = MathHelper.floor_double(player.posX);
                int j = MathHelper.floor_double(player.posY);
                int k = MathHelper.floor_double(player.posZ);
                if (world.getBlock(i, j, k) == ConfigBlocks.blockFluidPure) {
                    chance += 0.15F;
                }

                if (world.rand.nextFloat() < chance
                        && Thaumcraft.proxy.getPlayerKnowledge().getWarpPerm(player.getCommandSenderName()) > 0) {
                    Thaumcraft.proxy.getPlayerKnowledge().addWarpPerm(player.getCommandSenderName(), -1);
                    Thaumcraft.addStickyWarpToPlayer(player, 2);
                } else if (Thaumcraft.proxy.getPlayerKnowledge().getWarpPerm(player.getCommandSenderName()) > 0) {
                    Thaumcraft.addWarpToPlayer(player, 2, true);
                }
                
            } else {
                player.worldObj
                        .playSound(player.posX, player.posY, player.posZ, "thaumcraft:craftstart", 0.25F, 1.0F, false);

                for (int a = 0; a < Thaumcraft.proxy.particleCount(20); ++a) {
                    Thaumcraft.proxy.crucibleBubble(
                            Thaumcraft.proxy.getClientWorld(),
                            (float) player.posX - 0.5F + player.worldObj.rand.nextFloat() * 1.5F,
                            (float) player.boundingBox.minY + player.worldObj.rand.nextFloat() * player.height,
                            (float) player.posZ - 0.5F + player.worldObj.rand.nextFloat() * 1.5F,
                            1.0F,
                            0.7F,
                            0.9F);
                }
            }
        }
    }
}
