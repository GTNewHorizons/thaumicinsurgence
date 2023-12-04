package thaumicinsurgence.main.modules.arcana.utils;

import net.minecraft.item.ItemStack;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;
import thaumicinsurgence.main.modules.planar_artifice.core.PlanarAspects;
import thaumicinsurgence.main.modules.planar_artifice.utils.PlanarItems;
import thaumicinsurgence.main.modules.planar_artifice.utils.PlanarRecipes;

public class ArcanaResearch {

    public static void arcanaResearch() {
        String category = "THAUMICINSURGENCE";
        ResearchItem alkimiumPage;
        ResearchPage alkimium1, alkimium2, alkimium3, alkimium4, alkimium5, alkimium6, alkimium7, alkimium8;

        alkimiumPage = new ResearchItem(
                "PA_Alkimium",
                category,
                new AspectList().add(Aspect.MAGIC, 25).add(PlanarAspects.ALCHEMY, 25),
                0,
                -3,
                0,
                new ItemStack(PlanarItems.logo));

        alkimium1 = new ResearchPage("Alkimium.1");
        alkimium2 = new ResearchPage(PlanarRecipes.alkimium);
        alkimium3 = new ResearchPage(PlanarRecipes.alkimiumPlates);
        alkimium4 = new ResearchPage(PlanarRecipes.alkimiumGoggles);
        alkimium5 = new ResearchPage(PlanarRecipes.alkimiumBlock);
        alkimium6 = new ResearchPage(PlanarRecipes.alkimiumConstruct);
        alkimium7 = new ResearchPage(PlanarRecipes.alkimiumFurnace);
        alkimium8 = new ResearchPage(PlanarRecipes.alkimiumScribe);

        alkimiumPage.setPages(alkimium1, alkimium2, alkimium3, alkimium4, alkimium5, alkimium6, alkimium7, alkimium8);
        alkimiumPage.setParents("DISTILESSENTIA");
        ResearchCategories.addResearch(alkimiumPage);
    }
}
