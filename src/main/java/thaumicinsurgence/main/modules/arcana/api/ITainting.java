package thaumicinsurgence.main.modules.arcana.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;

public interface ITainting {

    public void taintBlocks(Item item, EntityPlayer player);
}
