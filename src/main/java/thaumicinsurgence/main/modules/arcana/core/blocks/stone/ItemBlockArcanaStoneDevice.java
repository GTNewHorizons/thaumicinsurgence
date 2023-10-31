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
        for (int i = 0; i < BlockArcanaStoneDevice.blocks.length; i++){
            list.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack item) {
        return getUnlocalizedName() + "." + item.getItemDamage();
    }
}
