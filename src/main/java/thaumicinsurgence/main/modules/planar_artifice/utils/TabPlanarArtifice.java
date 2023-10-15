package thaumicinsurgence.main.modules.planar_artifice.utils;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import tb.core.TBCore;
import tb.init.TBItems;

public class TabPlanarArtifice extends CreativeTabs {

    public static TabPlanarArtifice tabPlanarArtifice = new TabPlanarArtifice();

    public TabPlanarArtifice() {
        super(getNextID(), TBCore.modid);
    }

    public Item getTabIconItem() {
        return TBItems.bloodyBoots;
    }
}
