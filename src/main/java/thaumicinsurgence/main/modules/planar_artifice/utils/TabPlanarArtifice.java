package thaumicinsurgence.main.modules.planar_artifice.utils;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import thaumicinsurgence.main.Config;
import thaumicinsurgence.main.utils.VersionInfo;

public class TabPlanarArtifice extends CreativeTabs {

    public static TabPlanarArtifice tabPlanarArtifice = new TabPlanarArtifice();

    public TabPlanarArtifice() {
        super(getNextID(), VersionInfo.ModID);
    }

    public Item getTabIconItem() {
        return Config.alastorsWand;
    }
}
