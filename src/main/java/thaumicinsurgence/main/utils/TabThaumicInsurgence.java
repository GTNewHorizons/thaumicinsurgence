package thaumicinsurgence.main.utils;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import thaumcraft.common.Thaumcraft;

public class TabThaumicInsurgence extends CreativeTabs {

    public static TabThaumicInsurgence tabThaumicInsurgence = new TabThaumicInsurgence();

    public TabThaumicInsurgence() {
        super(getNextID(), VersionInfo.ModName);
    }

    public Item getTabIconItem() {
        return Thaumcraft.tabTC.getTabIconItem();
    }
}
