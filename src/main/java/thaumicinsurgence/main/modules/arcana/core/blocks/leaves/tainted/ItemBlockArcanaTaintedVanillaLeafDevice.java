package thaumicinsurgence.main.modules.arcana.core.blocks.leaves.tainted;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import thaumicinsurgence.main.modules.arcana.core.blocks.leaves.ItemLeafBase;
import thaumicinsurgence.main.modules.arcana.core.blocks.leaves.untainted.ItemBlockArcanaLeafDevice;

import java.util.List;

public class ItemBlockArcanaTaintedVanillaLeafDevice extends ItemLeafBase {

    public ItemBlockArcanaTaintedVanillaLeafDevice(Block block) {
        super(block);
        setHasSubtypes(true);
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        // love the creative naming schemes mojang, thx XOXO
        for (int i = 0; i < BlockArcanaTaintedVanillaLeafDevice.blocks.length; i++) {
            list.add(new ItemStack(this, 1, i));
        }
    }
}
