package thaumicinsurgence.main.modules.thaumic_rings.core;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import thaumcraft.api.IRunicArmor;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumicinsurgence.main.modules.thaumic_rings.utils.TabThaumicRings;
import thaumicinsurgence.main.utils.TabThaumicInsurgence;

import java.util.List;
public class ItemGeneralVisRings extends Item implements IBauble, IVisDiscountGear, IRunicArmor {
    public String iconName;
    public IIcon icon;
    public AspectList aspects;
    public String module = "thaumicrings";

    public ItemGeneralVisRings(String icon, AspectList ringAspects) {
        setCreativeTab(TabThaumicRings.tabThaumicRings);
        iconName = icon;
        setUnlocalizedName("TR_Rings");
        aspects = ringAspects;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister ir) {
        this.icon = ir.registerIcon(module + ":" + iconName);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta) {
        return this.icon;
    }

    public String func_77667_c(ItemStack itemstack) {
        return super.getUnlocalizedName() + "." + itemstack.getItemDamage();
    }

    /*@SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs creativeTab, List list) {
        for(int a = 0; a < 7; a++) {
            list.add(new ItemStack(this, 1, a));
        }

    }*/

    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.RING;
    }

    public int getVisDiscount(ItemStack stack, EntityPlayer player, Aspect aspect) {
        int aspectAmount = aspects.getAmount(aspect);
        if (aspectAmount != 0) {
            return aspectAmount;
        }
        return 0;
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        String discount = StatCollector.translateToLocal("tc.discount");
        if (getVisDiscount(stack, player, Aspect.AIR ) != 0) {
            list.add(
                    EnumChatFormatting.YELLOW + StatCollector.translateToLocal(Aspect.AIR.getName() + " " + discount)
                            + ": "
                            + getVisDiscount(stack, player, Aspect.AIR)
                            + "%");
        }
        if (getVisDiscount(stack, player, Aspect.EARTH ) != 0) {
            list.add(
                    EnumChatFormatting.DARK_GREEN + StatCollector.translateToLocal(Aspect.EARTH.getName() + " " + discount)
                            + ": "
                            + getVisDiscount(stack, player, Aspect.EARTH)
                            + "%");
        }
        if (getVisDiscount(stack, player, Aspect.FIRE ) != 0) {
            list.add(
                    EnumChatFormatting.RED + StatCollector.translateToLocal(Aspect.FIRE.getName() + " " + discount)
                            + ": "
                            + getVisDiscount(stack, player, Aspect.FIRE)
                            + "%");
        }
        if (getVisDiscount(stack, player, Aspect.WATER) != 0) {
            list.add(
                    EnumChatFormatting.BLUE + StatCollector.translateToLocal(Aspect.WATER.getName() + " " + discount)
                            + ": "
                            + getVisDiscount(stack, player, Aspect.WATER)
                            + "%");
        }
        if (getVisDiscount(stack, player, Aspect.ORDER ) != 0) {
            list.add(
                    EnumChatFormatting.WHITE + StatCollector.translateToLocal(Aspect.ORDER.getName() + " " + discount)
                            + ": "
                            + getVisDiscount(stack, player, Aspect.ORDER)
                            + "%");
        }
        if (getVisDiscount(stack, player, Aspect.ENTROPY) != 0) {
            list.add(
                    EnumChatFormatting.DARK_GRAY + StatCollector.translateToLocal(Aspect.ENTROPY.getName() + " " + discount)
                            + ": "
                            + getVisDiscount(stack, player, Aspect.ENTROPY)
                            + "%");
        }
        super.addInformation(stack, player, list, par4);
    }

    public int getRunicCharge(ItemStack itemstack) {
        return itemstack.getItemDamage() == 6 ? 4 : 0;
    }





    // unused
    public void onWornTick(ItemStack itemstack, EntityLivingBase player) {}

    public void onEquipped(ItemStack itemstack, EntityLivingBase player) {}

    public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {}

    public boolean canEquip(ItemStack itemstack, EntityLivingBase player) {
        return true;
    }

    public boolean canUnequip(ItemStack itemstack, EntityLivingBase player) {
        return true;
    }

}