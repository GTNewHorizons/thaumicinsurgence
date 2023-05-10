package thaumicinsurgence.item.baubles;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

import baubles.common.items.ItemRing;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import thaumicinsurgence.main.utils.TabThaumicInsurgence;

public class ItemPactRing extends ItemRing {

    public String storedPlayerName = "Alastor";

    public String outputStoredPlayername() {
        return storedPlayerName;
    }

    public ItemPactRing() {
        this.setMaxStackSize(1);
        this.setCreativeTab(TabThaumicInsurgence.tabThaumicInsurgence);
        this.setUnlocalizedName("ItemPactRing");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister ir) {
        this.icon = ir.registerIcon("thaumicinsurgence:ringSpamton");
    }

    @Override
    public void onWornTick(ItemStack itemstack, EntityLivingBase player) {}

    @Override
    public String getUnlocalizedName(ItemStack par1ItemStack) {
        return this.getUnlocalizedName();
    }

    // This controls the enchantment
    @Override
    public boolean hasEffect(ItemStack par1ItemStack, int a) {
        return false;
    }

    @Override
    public boolean canUnequip(ItemStack itemstack, EntityLivingBase player) {
        return false;
    }

    @Override
    public void onEquipped(ItemStack itemstack, EntityLivingBase player) {
        // EntityPlayer playerOne = playerOne. player.getUniqueID();
        // storedPlayerName = player.getCommandSenderName();
        // playerOne.addChatMessage(new ChatComponentText(String.valueOf(playerOne.getDisplayName())));
    }

    /*
     * @Override public int getVisDiscount(ItemStack var1, EntityPlayer var2, Aspect var3) { return 1; }
     */

}
