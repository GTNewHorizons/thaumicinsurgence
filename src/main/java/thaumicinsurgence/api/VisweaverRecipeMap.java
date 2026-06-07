package thaumicinsurgence.api;

import java.util.Collection;
import java.util.Collections;

import net.minecraft.item.ItemStack;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenCustomHashMap;
import thaumcraft.api.aspects.Aspect;
import thaumicinsurgence.common.lib.ItemHashStrategy;

public class VisweaverRecipeMap {

    /*
     * Hashmap using a hashing strategy suited to comparing items
     */
    private static final Object2ObjectOpenCustomHashMap<ItemStack, VisweaverRecipe> recipeMap = new Object2ObjectOpenCustomHashMap<>(
            new ItemHashStrategy());

    private VisweaverRecipeMap() {}

    public static VisweaverRecipe lookup(ItemStack input) {
        return recipeMap.get(input);
    }

    public static void putRecipe(int centivisCost, Aspect centivisType, ItemStack input, ItemStack output) {
        VisweaverRecipe recipe = new VisweaverRecipe(centivisCost, centivisType, input, output);
        recipeMap.put(input, recipe);
    }

    public static Collection<VisweaverRecipe> getAllRecipes() {
        return Collections.unmodifiableCollection(recipeMap.values());
    }
}
