package thaumicinsurgence.main.modules.planar_artifice.core.items;

import baubles.api.BaubleType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import thaumcraft.api.IScribeTools;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.items.baubles.ItemAmuletVis;
import thaumicinsurgence.main.modules.planar_artifice.PlanarArtifice;
import thaumicinsurgence.main.modules.planar_artifice.utils.TabPlanarArtifice;

import java.text.DecimalFormat;
import java.util.List;

public class ItemAlkimiumScribe extends ItemAmuletVis implements IScribeTools {
    public IIcon scribe;
    public static int charge = 250;
    DecimalFormat myFormatter = new DecimalFormat("#######.##");

    public ItemAlkimiumScribe(){
        setHasSubtypes(false);
        setMaxDamage(250);
        setUnlocalizedName("alkimium_scribe");
        setCreativeTab(TabPlanarArtifice.tabPlanarArtifice);
        setNoRepair();
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return getUnlocalizedName();
    }

    public int returnLowestVis(ItemStack stack){
        int lowest = 25000;
        if (lowest > getVis(stack, Aspect.FIRE)) lowest = getVis(stack, Aspect.FIRE);
        if (lowest > getVis(stack, Aspect.AIR)) lowest = getVis(stack, Aspect.AIR);
        if (lowest > getVis(stack, Aspect.WATER)) lowest = getVis(stack, Aspect.WATER);
        if (lowest > getVis(stack, Aspect.EARTH)) lowest = getVis(stack, Aspect.EARTH);
        if (lowest > getVis(stack, Aspect.ORDER)) lowest = getVis(stack, Aspect.ORDER);
        if (lowest > getVis(stack, Aspect.ENTROPY)) lowest = getVis(stack, Aspect.ENTROPY);
        return lowest/100;
    }

    @Override
    public int getDamage(ItemStack stack) {
        return charge - returnLowestVis(stack);
    }

    @Override
    public void setDamage(ItemStack stack, int damage) {
        int currentDamage = stack.getItemDamage();
        if(damage > currentDamage) {
            if (returnLowestVis(stack) >= 1){
                this.addVis(stack, Aspect.FIRE, -1, true);
                this.addVis(stack, Aspect.AIR, -1, true);
                this.addVis(stack, Aspect.WATER, -1, true);
                this.addVis(stack, Aspect.EARTH, -1, true);
                this.addVis(stack, Aspect.ORDER, -1, true);
                this.addVis(stack, Aspect.ENTROPY, -1, true);
            }
            return;
        }
        super.setDamage(stack, damage);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs creativeTab, List list) {
        list.add(new ItemStack(item, 1, charge));
        list.add(new ItemStack(item));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister ir) {
        scribe = ir.registerIcon("planarartifice:alchemical_scribing_tools");
    }

    @Override
    public IIcon getIconFromDamage(int par1) {
        return scribe;
    }

    @Override
    public EnumRarity getRarity(ItemStack itemstack) {
        return PlanarArtifice.planarGreen;
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemstack) {
        return null;
    }

    @Override
    public void onWornTick(ItemStack itemstack, EntityLivingBase player) {}

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        list.add(EnumChatFormatting.GOLD + StatCollector.translateToLocal("item.capacity.text") + " " + this.getMaxVis(stack) / 100);
        if(stack.hasTagCompound()) {
            int tag = 0;
            for(Aspect aspect : Aspect.getPrimalAspects()) {
                if(stack.stackTagCompound.hasKey(aspect.getTag())) {
                    String amount = this.myFormatter.format((double)((float)stack.stackTagCompound.getInteger(aspect.getTag()) / 100.0F));
                    list.add(" §" + aspect.getChatcolor() + aspect.getName() + "§r x " + amount);
                }
                tag++;
            }
        }
    }

    @Override
    public int getMaxVis(ItemStack stack) {
        return 25000;
    } // 250 vis, I might increase it

    @Override
    public boolean canEquip(ItemStack itemstack, EntityLivingBase player) {
        return false;
    }

    public void setDurability(ItemStack stack){

    }

    @Override
    public int addVis(ItemStack stack, Aspect aspect, int amount, boolean doit) {
        if(!aspect.isPrimal()) {
            return 0;
        } else {
            int storeAmount = this.getVis(stack, aspect) + amount * 100;
            int leftover = Math.max(storeAmount - this.getMaxVis(stack), 0);
            if(doit) {
                this.storeVis(stack, aspect, Math.min(storeAmount, this.getMaxVis(stack)));
            }

            return leftover / 100;
        }
    }


}
