package thaumicinsurgence.main.modules.arcana.utils;

import net.minecraft.item.ItemStack;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;

public class ArcanaResearch {

    public static void arcanaResearch() {
        String category = "THAUMICINSURGENCE";
        ResearchItem arcanaPage, taintedSapling, taintedVanilla, arcanaSapling, cursedSapling;
        ResearchPage arcana1, arcana2;
        ResearchPage tainted1, vanilla1, aSapling1, cursed1;

        arcanaPage = new ResearchItem(
                "AR_Base",
                category,
                new AspectList().add(Aspect.MAGIC, 25).add(Aspect.TAINT, 25),
                -2,
                -2,
                0,
                new ItemStack(ArcanaItems.arcanaLogo));

        arcana1 = new ResearchPage("Arcana.1");
        arcana2 = new ResearchPage(ArcanaRecipes.arcaniumGoggles);

        arcanaPage.setPages(arcana1, arcana2);
        arcanaPage.setParents("CRUCIBLE");
        ResearchCategories.addResearch(arcanaPage);

        taintedSapling = new ResearchItem(
                "AR_TaintedSapling",
                category,
                new AspectList().add(Aspect.MAGIC, 25).add(Aspect.TAINT, 25).add(Aspect.TREE, 25),
                -2,
                -4,
                0,
                new ItemStack(ArcanaBlocks.blockTaintedArcanaSaplings, 1, 0));

        tainted1 = new ResearchPage("Tainted.1");

        taintedSapling.setPages(tainted1);
        taintedSapling.setParents("AR_Base");
        ResearchCategories.addResearch(taintedSapling);

        taintedVanilla = new ResearchItem(
                "AR_TaintedVanilla",
                category,
                new AspectList().add(Aspect.MAGIC, 25).add(Aspect.TAINT, 25).add(Aspect.TREE, 25),
                -3,
                -4,
                0,
                new ItemStack(ArcanaBlocks.blockTaintedVanillaSaplings, 1, 4));

        vanilla1 = new ResearchPage("Vanilla.1");

        taintedVanilla.setPages(vanilla1);
        taintedVanilla.setParents("AR_Base");
        ResearchCategories.addResearch(taintedVanilla);

        arcanaSapling = new ResearchItem(
                "AR_ArcanaSapling",
                category,
                new AspectList().add(Aspect.MAGIC, 25).add(Aspect.TAINT, 25).add(Aspect.TREE, 25),
                -4,
                -2,
                0,
                new ItemStack(ArcanaBlocks.blockSaplings, 1, 0));

        aSapling1 = new ResearchPage("ASapling.1");

        arcanaSapling.setPages(tainted1);
        arcanaSapling.setParents("AR_Base");
        ResearchCategories.addResearch(arcanaSapling);

        cursedSapling = new ResearchItem(
                "AR_CursedSapling",
                category,
                new AspectList().add(Aspect.MAGIC, 25).add(Aspect.TAINT, 25).add(Aspect.TREE, 25),
                -4,
                -3,
                0,
                new ItemStack(ArcanaBlocks.blockCursedArcanaSaplings, 1, 0));

        cursed1 = new ResearchPage("Cursed.1");

        cursedSapling.setPages(cursed1);
        cursedSapling.setParents("AR_Base");
        ResearchCategories.addResearch(cursedSapling);
    }
}
