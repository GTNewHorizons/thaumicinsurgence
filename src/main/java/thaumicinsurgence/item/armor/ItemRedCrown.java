package thaumicinsurgence.item.armor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import thaumcraft.api.IGoggles;
import thaumcraft.api.IRepairable;
import thaumcraft.api.IRunicArmor;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.nodes.IRevealer;
import thaumcraft.common.items.armor.ItemGoggles;
import thaumicinsurgence.main.utils.TabThaumicInsurgence;
import thaumicinsurgence.main.utils.VersionInfo;

public class ItemRedCrown extends ItemGoggles
        implements IRepairable, IVisDiscountGear, IRevealer, IGoggles, IRunicArmor {
    public IIcon icon;

    public ItemRedCrown(final ArmorMaterial material, final int j, final int k) {
        super(material, j, k);
        setCreativeTab(TabThaumicInsurgence.tabThaumicInsurgence);
        setUnlocalizedName("ItemRedCrown");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(final IIconRegister ir) {
        icon = ir.registerIcon(VersionInfo.ModID + ":goggles_of_revealing");
    }

    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return "thaumicinsurgence:textures/model/goggles_of_revealing_layer_1.png";
    }

    @Override
    public int getVisDiscount(ItemStack stack, EntityPlayer player, Aspect aspect) {
        return 15;
    }

    @Override
    public boolean showNodes(ItemStack itemstack, EntityLivingBase player) {
        return true;
    }

    public void onUpdate(final ItemStack stack, final World world, final Entity entity, final int i, final boolean b) {
        super.onUpdate(stack, world, entity, i, b);
        if (!world.isRemote
                && stack.isItemDamaged()
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
