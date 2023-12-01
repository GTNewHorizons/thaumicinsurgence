package thaumicinsurgence.main.modules.arcana.core.blocks.saplings.tainted;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import thaumicinsurgence.main.modules.arcana.core.blocks.saplings.untainted.BlockArcanaUntaintedSapling;

import java.util.List;

public class ItemBlockArcanaTaintedSapling extends ItemBlock {
    public ItemBlockArcanaTaintedSapling(Block block) {
        super(block);
        setHasSubtypes(true);

    }

    public int getMetadata(int meta) {
        return meta;
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        // love the creative naming schemes mojang, thx XOXO
        for (int i = 0; i < BlockArcanaUntaintedSapling.saplings.length; i++) {
            list.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack item) {
        return getUnlocalizedName() + "." + item.getItemDamage();
    }
}
