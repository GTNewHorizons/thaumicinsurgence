package thaumicinsurgence.main.modules.planar_artifice.utils;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.util.EnumHelper;
import thaumicinsurgence.main.utils.VersionInfo;

public class TabPlanarArtifice extends CreativeTabs {

    public static TabPlanarArtifice tabPlanarArtifice = new TabPlanarArtifice();

    public TabPlanarArtifice() {
        super(getNextID(), "Planar Artifice");
    }

    public Item getTabIconItem() {
        return PlanarItems.logo;
    }
}
