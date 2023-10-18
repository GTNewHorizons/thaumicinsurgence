package thaumicinsurgence.main.modules.planar_artifice.utils;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import thaumcraft.api.ThaumcraftApi;
import thaumicinsurgence.main.modules.planar_artifice.PlanarArtifice;
import thaumicinsurgence.main.modules.planar_artifice.core.items.ItemAlkimium;
import thaumicinsurgence.main.modules.planar_artifice.core.items.ItemAlkimiumScribe;
import thaumicinsurgence.main.modules.planar_artifice.core.items.ItemLogo;
import thaumicinsurgence.main.modules.planar_artifice.core.items.armour.ItemAlkimiumGoggles;


//  ConfigResearch.recipes.put("BalancedShard_" + a, ThaumcraftApi.addCrucibleRecipe("CRUCIBLE", new ItemStack(ConfigItems.itemShard, 1, 6), new ItemStack(ConfigItems.itemShard, 1, a), al));
public class PlanarItems {

    public static final Class<PlanarArtifice> core = PlanarArtifice.class;
    public static Item alkimium = new ItemAlkimium();
    public static Item logo = new ItemLogo();
    public static Item goggles = new ItemAlkimiumGoggles(ThaumcraftApi.armorMatSpecial, 4, 0);
    public static Item scribe = new ItemAlkimiumScribe();

    public static void setup() {
        alkimium = new ItemAlkimium();
        GameRegistry.registerItem(new ItemAlkimium(), "Alkimium");

        GameRegistry.registerItem(logo, "logo");

        GameRegistry.registerItem(goggles, goggles.getUnlocalizedName());

        GameRegistry.registerItem(scribe, scribe.getUnlocalizedName());
    }

}
