package thaumicinsurgence.main.modules.planar_artifice.core.items.armour;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import thaumcraft.api.*;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.nodes.IRevealer;
import thaumcraft.common.items.armor.ItemGoggles;
import thaumicinsurgence.main.modules.planar_artifice.PlanarArtifice;
import thaumicinsurgence.main.modules.planar_artifice.utils.TabPlanarArtifice;
import thaumicinsurgence.main.utils.TabThaumicInsurgence;

public class ItemAlkimiumGoggles extends ItemGoggles
        implements IRepairable, IVisDiscountGear, IRevealer, IGoggles, IRunicArmor {

    public IIcon icon;

    public ItemAlkimiumGoggles(final ArmorMaterial material, final int j, final int k) {
        super(material, j, k);
        setCreativeTab(TabPlanarArtifice.tabPlanarArtifice);
        setUnlocalizedName("alkimium_goggles");

        setMaxDamage(3575);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(final IIconRegister ir) {
        icon = ir.registerIcon("planarartifice:alkimium/alkimium_goggles");
    }

    public EnumRarity getRarity(ItemStack itemstack) {
        return PlanarArtifice.planarGreen;
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int damage) {
        return this.icon;
    }

    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return "planarartifice:models/alkimium_goggles.png";
    }

    public int getVisDiscount(ItemStack stack, EntityPlayer player, Aspect aspect) {
        return 6;
    }
}
