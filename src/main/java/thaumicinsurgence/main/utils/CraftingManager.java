package thaumicinsurgence.main.utils;

public class CraftingManager {

    public static void setupCrafting() {
        // Broken up into seperate sections to make things a bit easier to find.
        setupVanillaCrafting();
    }

    private static void setupVanillaCrafting() {
        // ItemStack input;
        // ItemStack output;

        // Magic capsules
        /*
         * output = new ItemStack(Config.magicCapsule); output.stackSize = 4; GameRegistry.addRecipe(new
         * ShapedOreRecipe(output, "WWW", 'W', "waxMagical" ));
         */
        /*
         * output = output.copy(); output.stackSize = 12; GameRegistry.addRecipe(output, "aaa", "aFa", "aaa", 'F',
         * input, 'a', ItemInterface.getItemStack("ash") );
         */
    }
}
