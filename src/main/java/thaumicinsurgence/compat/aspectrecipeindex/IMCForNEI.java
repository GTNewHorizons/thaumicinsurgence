package thaumicinsurgence.compat.aspectrecipeindex;

import net.minecraft.nbt.NBTTagCompound;

import cpw.mods.fml.common.event.FMLInterModComms;

public class IMCForNEI {

    public static void IMCSender() {
        registerHandlerInfo(VisweaverRecipeHandler.class.getName(), "thaumicinsurgence:tile.visweaver", 136);
        registerCatalystInfo(new VisweaverRecipeHandler().getOverlayIdentifier(), "thaumicinsurgence:tile.visweaver");
    }

    private static void registerHandlerInfo(String name, String stack, int height) {
        NBTTagCompound NBT = new NBTTagCompound();
        NBT.setString("handler", name);
        NBT.setString("modName", "Thaumcraft");
        NBT.setString("modId", "Thaumcraft");
        NBT.setBoolean("modRequired", true);
        NBT.setString("itemName", stack);
        NBT.setInteger("handlerHeight", height);
        NBT.setInteger("maxRecipesPerPage", 2);
        FMLInterModComms.sendMessage("NotEnoughItems", "registerHandlerInfo", NBT);
    }

    private static void registerCatalystInfo(String handlerName, String stack) {
        NBTTagCompound aNBT = new NBTTagCompound();
        aNBT.setString("handlerID", handlerName);
        aNBT.setString("itemName", stack);
        aNBT.setInteger("priority", 0);
        FMLInterModComms.sendMessage("NotEnoughItems", "registerCatalystInfo", aNBT);
    }
}
