package thaumicinsurgence.main.modules.arcana.utils;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import thaumicinsurgence.main.modules.planar_artifice.utils.PlanarItems;
import thaumicinsurgence.main.utils.VersionInfo;

public class TabArcana extends CreativeTabs {

    public static TabArcana tabArcana = new TabArcana();

    public TabArcana() {
        super(getNextID(), VersionInfo.ModID);
    }

    public Item getTabIconItem() {
        return ArcanaItems.goggles;
    }
}
