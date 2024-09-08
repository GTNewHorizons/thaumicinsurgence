package thaumicinsurgence.main.modules.thaumic_rings.utils;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import thaumicinsurgence.main.Config;
import thaumicinsurgence.main.modules.planar_artifice.utils.PlanarItems;

public class TabThaumicRings extends CreativeTabs {

    public static TabThaumicRings tabThaumicRings = new TabThaumicRings();

    public TabThaumicRings() {
        super(getNextID(), "Thaumic Rings");
    }

    public Item getTabIconItem() {
        return Config.eightBitRedCrownItem;
    }
}
