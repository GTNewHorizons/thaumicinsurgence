package thaumicinsurgence.main.utils;

// import magicbees.item.types.ResourceType;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import thaumcraft.common.Thaumcraft;

public class TabThaumicInsurgence extends CreativeTabs {
    public static TabThaumicInsurgence tabThaumicInsurgence = new TabThaumicInsurgence();

    public TabThaumicInsurgence() {
        super(getNextID(), "Thaumic Insurgence");
    }

    public Item getTabIconItem() {
        return Thaumcraft.tabTC.getTabIconItem();
    }
}
