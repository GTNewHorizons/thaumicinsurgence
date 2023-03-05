package thaumicinsurgence.item.baubles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.aspects.Aspect;
import baubles.common.items.ItemRing;

public class SilverRing extends ItemRing implements IVisDiscountGear {

    @Override
    public String getUnlocalizedName(ItemStack item) {
        return this.getUnlocalizedName() + ":" + item.getItemDamage();
    }

    @Override
    public int getVisDiscount(ItemStack var1, EntityPlayer var2, Aspect var3) {
        return 4;
    }
    // Argentumancer's Silver Ring
}
