package thaumicinsurgence.item.armor;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import baubles.common.container.InventoryBaubles;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import thaumcraft.api.*;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.nodes.IRevealer;
import thaumcraft.common.items.armor.ItemGoggles;
import thaumicinsurgence.main.utils.TabThaumicInsurgence;

public class ItemBIGSHOT extends ItemGoggles
        implements IRepairable, IVisDiscountGear, IRevealer, IGoggles, IRunicArmor, IWarpingGear {

    public IIcon icon;

    public ItemBIGSHOT(final ArmorMaterial material, final int j, final int k) {
        super(material, j, k);
        setCreativeTab(TabThaumicInsurgence.tabThaumicInsurgence);
        setUnlocalizedName("ItemBIGSHOT");
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(final IIconRegister ir) {
        icon = ir.registerIcon("thaumicinsurgence:[[big_shot]]");
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.icon;
    }

    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return "thaumicinsurgence:model/[[JACKPOT]].png";
    }

    public int getVisDiscount(ItemStack stack, EntityPlayer player, Aspect aspect) {
        ItemStack[] stacklist = new InventoryBaubles(player).stackList;
        if (stacklist[1] != null) {
            player.addChatMessage(new ChatComponentText(String.valueOf(stacklist.length)));
            player.addChatMessage(new ChatComponentText(stacklist[1].toString()));
            player.addChatMessage(new ChatComponentText("the corn is creamed"));
        }

        return 8;
    }

    @Override
    public int getWarp(ItemStack var1, EntityPlayer var2) {
        return 5;
    }

    public boolean showNodes(ItemStack itemstack, EntityLivingBase player) {
        return true;
    }

    public void onUpdate(final ItemStack stack, final World world, final Entity entity, final int i, final boolean b) {
        super.onUpdate(stack, world, entity, i, b);
        if (!world.isRemote && stack.isItemDamaged()
                && entity.ticksExisted % 20 == 0
                && entity instanceof EntityLivingBase) {
            stack.damageItem(-1, (EntityLivingBase) entity);
        }
    }

    @Override
    public void onArmorTick(final World world, final EntityPlayer player, final ItemStack stack) {
        super.onArmorTick(world, player, stack);
        if (!world.isRemote && stack.isItemDamaged() && player.ticksExisted % 20 == 0) {
            stack.damageItem(-1, player);
        }
    }
}
