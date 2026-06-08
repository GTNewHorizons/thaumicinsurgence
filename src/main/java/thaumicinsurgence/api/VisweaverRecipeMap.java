package thaumicinsurgence.api;

import java.util.Collection;
import java.util.Collections;

import net.minecraft.item.ItemStack;

import com.gtnewhorizon.gtnhlib.util.data.ItemId;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenCustomHashMap;
import thaumcraft.api.aspects.Aspect;

public class VisweaverRecipeMap {

    /*
     * Hashmap using a hashing strategy suited to comparing items
     */
    private static final Object2ObjectOpenCustomHashMap<ItemStack, VisweaverRecipe> recipeMap = new Object2ObjectOpenCustomHashMap<>(
            ItemId.STACK_ITEM_META_STRATEGY);

    public static VisweaverRecipe lookup(ItemStack input) {
        return recipeMap.get(input);
    }

    public static void putRecipe(Aspect aspect, int cost, ItemStack input, ItemStack output) {
        VisweaverRecipe recipe = new VisweaverRecipe(aspect, cost, input, output);
        recipeMap.put(input, recipe);
    }

    public static Collection<VisweaverRecipe> getAllRecipes() {
        return Collections.unmodifiableCollection(recipeMap.values());
    }
}
