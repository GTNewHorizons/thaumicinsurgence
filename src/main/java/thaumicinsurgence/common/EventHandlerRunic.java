package thaumicinsurgence.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import thaumicinsurgence.api.IPurifyingGear;

public class EventHandlerRunic extends thaumcraft.common.lib.events.EventHandlerRunic {

    @SubscribeEvent
    public void tooltipEvent(ItemTooltipEvent event) {
        int purity = getFinalPurity(event.itemStack, event.entityPlayer);
        if (purity > 0) {
            event.toolTip
                    .add(EnumChatFormatting.GOLD + StatCollector.translateToLocal("item.purifying") + " " + purity);
        }

        int charge = getFinalCharge(event.itemStack);
        if (charge > 0) {
            event.toolTip
                    .add(EnumChatFormatting.GOLD + StatCollector.translateToLocal("item.runic.charge") + " +" + charge);
        }
        int warp = getFinalWarp(event.itemStack, event.entityPlayer);
        if (warp > 0) {
            event.toolTip
                    .add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("item.warping") + " " + warp);
        }
    }

    public static int getFinalPurity(ItemStack stack, EntityPlayer player) {
        if (stack != null && stack.getItem() instanceof IPurifyingGear) {
            IPurifyingGear armor = (IPurifyingGear) stack.getItem();
            return armor.getPurity(stack, player);
        } else {
            return 0;
        }
    }
}
