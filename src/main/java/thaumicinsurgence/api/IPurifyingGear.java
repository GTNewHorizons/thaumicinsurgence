package thaumicinsurgence.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IPurifyingGear {
    int getPurity(ItemStack var1, EntityPlayer var2);
}
