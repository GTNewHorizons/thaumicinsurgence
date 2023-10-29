package thaumicinsurgence.main.modules.arcana.core.blocks.stone;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import thaumicinsurgence.main.modules.planar_artifice.core.blocks.BlockPlanarDevice;

import java.util.List;

public class ItemBlockArcanaStoneDevice extends ItemBlock {
    public ItemBlockArcanaStoneDevice(Block block) {
        super(block);
        setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int damageVal) {
        return damageVal;
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        // love the creative naming schemes mojang, thx XOXO
        list.add(new ItemStack(this, 1, 0));
        list.add(new ItemStack(this, 1, 1));
        list.add(new ItemStack(this, 1, 2));
        list.add(new ItemStack(this, 1, 3));
        list.add(new ItemStack(this, 1, 4));
        list.add(new ItemStack(this, 1, 5));
        list.add(new ItemStack(this, 1, 6));
        list.add(new ItemStack(this, 1, 7));
        list.add(new ItemStack(this, 1, 8));
        list.add(new ItemStack(this, 1, 9));
        list.add(new ItemStack(this, 1, 10));
        list.add(new ItemStack(this, 1, 11));
        list.add(new ItemStack(this, 1, 12));
        list.add(new ItemStack(this, 1, 13));
        list.add(new ItemStack(this, 1, 14));
        list.add(new ItemStack(this, 1, 15));
    }

    @Override
    public String getUnlocalizedName(ItemStack item) {
        return getUnlocalizedName() + "." + item.getItemDamage();
    }
}
