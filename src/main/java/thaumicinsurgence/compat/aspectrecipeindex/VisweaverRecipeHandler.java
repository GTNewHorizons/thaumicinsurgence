package thaumicinsurgence.compat.aspectrecipeindex;

import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import com.gtnewhorizons.aspectrecipeindex.ModItems;
import com.gtnewhorizons.aspectrecipeindex.common.items.ItemAspect;
import com.gtnewhorizons.aspectrecipeindex.nei.TemplateThaumHandler;
import com.gtnewhorizons.aspectrecipeindex.util.Util;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import thaumicinsurgence.api.VisweaverRecipe;
import thaumicinsurgence.api.VisweaverRecipeMap;

public class VisweaverRecipeHandler extends TemplateThaumHandler {

    public static final String OVERLAY = "thaumicinsurgence.visweaver";

    @Override
    public void loadCraftingRecipes(String outputId, Object... results) {
        boolean visweaverResearched = Util.shouldShowRecipe("visweaver");
        if (outputId.equals(this.getOverlayIdentifier())) {
            for (VisweaverRecipe r : VisweaverRecipeMap.getAllRecipes()) {
                new VisweaverCachedRecipe(r, visweaverResearched);
            }
        } else if (outputId.equals("item")) {
            this.loadCraftingRecipes((ItemStack) results[0]);
        }
    }

    @Override
    public void loadCraftingRecipes(ItemStack result) {
        boolean visweaverResearched = Util.shouldShowRecipe("visweaver");
        for (VisweaverRecipe recipe : VisweaverRecipeMap.getAllRecipes()) {
            if (NEIServerUtils.areStacksSameTypeCraftingWithNBT(recipe.getOutput(), result)) {
                new VisweaverCachedRecipe(recipe, visweaverResearched);
            }
        }
    }

    @Override
    public void loadUsageRecipes(ItemStack ingredient) {
        if (!Util.shouldShowRecipe("visweaver")) return;
        for (VisweaverRecipe recipe : VisweaverRecipeMap.getAllRecipes()) {
            if (NEIServerUtils.areStacksSameTypeCraftingWithNBT(recipe.getInput(), ingredient)) {
                new VisweaverCachedRecipe(recipe, true);
            }
        }
    }

    @Override
    public String getRecipeName() {
        return StatCollector.translateToLocal("thaumicinsurgence.visweaver.title");
    }

    @Override
    protected void drawIngredientBackground() {
        GuiDraw.drawTexturedModalRect(43, 16, 68, 76, 11, 11);
    }

    @Override
    public String getOverlayIdentifier() {
        return OVERLAY;
    }

    protected class VisweaverCachedRecipe extends CachedThaumRecipe {

        public VisweaverCachedRecipe(VisweaverRecipe recipe, boolean shouldShowRecipe) {
            super(shouldShowRecipe);
            AspectList aspect = new AspectList();
            aspect.aspects.put(recipe.getCentivisType(), recipe.getCentivisCost());

            this.setIngredient(recipe.getInput());
            this.setResult(recipe.getOutput());
            this.setAspects(aspect);
            tryAddResearch(ResearchCategories.getResearch("visweaver"));
            this.addAspectToIngredients(aspects);
            addIfValid();
        }

        protected void setIngredient(Object in) {
            if (in != null && NEIServerUtils.extractRecipeItems(in).length > 0) {
                PositionedStack stack = new PositionedStack(in, 52, 31, false);
                stack.setMaxSize(1);
                this.ingredients.add(stack);
            }
        }

        protected void addAspectToIngredients(AspectList aspects) {
            Aspect aspect = aspects.getAspects()[0];
            ItemStack stack = new ItemStack(ModItems.itemAspect, aspects.getAmount(aspect), 1);
            ItemAspect.setAspect(stack, aspect);
            this.ingredients.add(new PositionedStack(stack, 100, 31, false));
        }
    }
}
