package thaumicinsurgence.main.modules.arcana.utils;

import net.minecraft.item.ItemStack;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.crafting.ShapedArcaneRecipe;
import thaumcraft.common.config.ConfigItems;
import thaumicinsurgence.main.modules.planar_artifice.core.PlanarAspects;
import thaumicinsurgence.main.modules.planar_artifice.utils.PlanarBlocks;
import thaumicinsurgence.main.modules.planar_artifice.utils.PlanarItems;
import thaumicinsurgence.main.utils.compat.ThaumcraftHelper;

public class ArcanaRecipes {

    public static CrucibleRecipe arcaniumGoggles;
    public static CrucibleRecipe tDair, tGW, tWillow, tEuc, tHawthorn;
    public static CrucibleRecipe t
    public static void arcanaAlchemy() {
        arcaniumGoggles = ThaumcraftApi.addCrucibleRecipe(
                "Arcana",
                new ItemStack(ArcanaItems.goggles),
                new ItemStack(ConfigItems.itemGoggles),
                new AspectList().add(Aspect.TAINT, 8));

        taintedSaplings();
    }

    public static void taintedSaplings(){
        tDair = ThaumcraftApi.addCrucibleRecipe(
                "Arcana",
                new ItemStack(ArcanaBlocks.blockTaintedArcanaSaplings, 1, 0),
                new ItemStack(ArcanaBlocks.blockSaplings, 1, 0),
                new AspectList().add(Aspect.TAINT, 1));
        tGW = ThaumcraftApi.addCrucibleRecipe(
                "Arcana",
                new ItemStack(ArcanaBlocks.blockTaintedArcanaSaplings, 1, 1),
                new ItemStack(ArcanaBlocks.blockSaplings, 1, 5),
                new AspectList().add(Aspect.TAINT, 1));
        tWillow = ThaumcraftApi.addCrucibleRecipe(
                "Arcana",
                new ItemStack(ArcanaBlocks.blockTaintedArcanaSaplings, 1, 2),
                new ItemStack(ArcanaBlocks.blockSaplings, 1, 3),
                new AspectList().add(Aspect.TAINT, 1));
        tEuc = ThaumcraftApi.addCrucibleRecipe(
                "Arcana",
                new ItemStack(ArcanaBlocks.blockTaintedArcanaSaplings, 1, 3),
                new ItemStack(ArcanaBlocks.blockSaplings, 1, 1),
                new AspectList().add(Aspect.TAINT, 1));
        tHawthorn = ThaumcraftApi.addCrucibleRecipe(
                "Arcana",
                new ItemStack(ArcanaBlocks.blockTaintedArcanaSaplings, 1, 4),
                new ItemStack(ArcanaBlocks.blockSaplings, 1, 2),
                new AspectList().add(Aspect.TAINT, 1));
    }
    public static void taintedVanillaSaplings(){
        tDair = ThaumcraftApi.addCrucibleRecipe(
                "Arcana",
                new ItemStack(ArcanaBlocks.blockTaintedArcanaSaplings, 1, 0),
                new ItemStack(ArcanaBlocks.blockSaplings, 1, 0),
                new AspectList().add(Aspect.TAINT, 1));
    }

}
