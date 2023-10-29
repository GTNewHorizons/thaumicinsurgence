package thaumicinsurgence.main.modules.arcana.utils;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import thaumcraft.api.ThaumcraftApi;
import thaumicinsurgence.main.modules.arcana.core.items.ItemArcanaLogo;
import thaumicinsurgence.main.modules.arcana.core.items.armour.ItemArcaniumGoggles;
import thaumicinsurgence.main.modules.planar_artifice.PlanarArtifice;
import thaumicinsurgence.main.modules.planar_artifice.core.items.ItemAlkimium;
import thaumicinsurgence.main.modules.planar_artifice.core.items.ItemAlkimiumScribe;
import thaumicinsurgence.main.modules.planar_artifice.core.items.ItemLogo;
import thaumicinsurgence.main.modules.planar_artifice.core.items.armour.ItemAlkimiumGoggles;


//  ConfigResearch.recipes.put("BalancedShard_" + a, ThaumcraftApi.addCrucibleRecipe("CRUCIBLE", new ItemStack(ConfigItems.itemShard, 1, 6), new ItemStack(ConfigItems.itemShard, 1, a), al));
public class ArcanaItems {

    public static final Class<PlanarArtifice> core = PlanarArtifice.class;
    public static Item goggles = new ItemArcaniumGoggles(ThaumcraftApi.armorMatSpecial, 4, 0);
    public static Item arcanaLogo = new ItemArcanaLogo();

    public static void setup() {
        GameRegistry.registerItem(arcanaLogo, "arcanaLogo");

        GameRegistry.registerItem(goggles, goggles.getUnlocalizedName());
    }

}
