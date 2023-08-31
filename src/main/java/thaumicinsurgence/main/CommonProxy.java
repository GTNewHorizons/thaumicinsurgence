package thaumicinsurgence.main;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import thaumicinsurgence.main.utils.CraftingManager;
import thaumicinsurgence.main.utils.LogHelper;
import thaumicinsurgence.main.utils.compat.ModHelperManager;

@SuppressWarnings("unused")
public class CommonProxy implements IGuiHandler {

    public void preInit(FMLPreInitializationEvent event) {
        LogHelper.info("Preinit started");
        Config.Init(event.getSuggestedConfigurationFile());

        Config.setupBlocks();
        Config.setupItems();

        ModHelperManager.preInit();

        LogHelper.info("Preinit completed");
    }

    public void init(FMLInitializationEvent event) {
        ModHelperManager.init();
        LogHelper.info("Init completed");
    }

    public void postInit(FMLPostInitializationEvent event) {
        ModHelperManager.postInit();

        CraftingManager.setupCrafting();

        LogHelper.info("Postinit completed");
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            // case 0:
            // return new ContainerAdvancedWand(player.inventory, world, x, y, z);
            // bring back when the Advanced Wand is finished
            default:
                break;
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }
}
