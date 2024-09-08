package thaumicinsurgence.main.modules.gregtech.utils;

import cpw.mods.fml.common.registry.GameRegistry;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import net.minecraft.item.ItemStack;
import thaumcraft.api.ItemApi;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;

import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.Thaumcraft;
import thaumicinsurgence.main.Config;

public class GregRecipes {

    public static void gregInfusion() {
        final ItemStack[] recipe_jarthaumiumreinforced = {
                GameRegistry.makeItemStack("Thaumcraft:ItemResource", 15, 1, null),
                GT_OreDictUnificator.get(OrePrefixes.plateDense, Materials.Thaumium, 1),
                new ItemStack(net.minecraft.init.Blocks.glass_pane),
                GT_OreDictUnificator.get(OrePrefixes.plateDense, Materials.Thaumium, 1),
                new ItemStack(net.minecraft.init.Blocks.glass_pane),
                GT_OreDictUnificator.get(OrePrefixes.frameGt, Materials.Titanium, 1),
                GT_OreDictUnificator.get(OrePrefixes.plateDense, Materials.Thaumium, 1),
                new ItemStack(net.minecraft.init.Blocks.glass_pane),
                GT_OreDictUnificator.get(OrePrefixes.plateDense, Materials.Thaumium, 1),
                new ItemStack(net.minecraft.init.Blocks.glass_pane), };
        final AspectList aspects_jarthaumiumreinforced = new AspectList().add(Aspect.ARMOR, 64).add(Aspect.ORDER, 32)
                .add(Aspect.WATER, 32).add(Aspect.GREED, 16).add(Aspect.VOID, 16).add(Aspect.AIR, 8);

        /*ThaumcraftApi.addInfusionCraftingRecipe(
                "TI_THAUMIUMREINFORCEDJAR",
                Config.thaumiumJar
                ); */



        /*ThaumcraftApi.addInfusionCraftingRecipe(
                "JARVOID",
                getModItem(Thaumcraft.ID, "blockJar", 1, 3, missing),
                2,
                new AspectList().add(Aspect.getAspect("vacuos"), 7).add(Aspect.getAspect("praecantatio"), 7)
                        .add(Aspect.getAspect("perditio"), 7).add(Aspect.getAspect("aqua"), 7),
                getModItem(Thaumcraft.ID, "blockJar", 1, 0, missing),
                new ItemStack[] { GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Obsidian, 1L),
                        getModItem(Minecraft.ID, "blaze_powder", 1, 0, missing),
                        GT_OreDictUnificator.get(OrePrefixes.plate, Materials.EnderEye, 1L),
                        getModItem(Thaumcraft.ID, "ItemNugget", 1, 5, missing), }); */

        // Thaumium Reinforced Jar
        /*final ItemStack[] recipe_jarthaumiumreinforced = {
                GameRegistry.makeItemStack("Thaumcraft:ItemResource", 15, 1, null),
                GT_OreDictUnificator.get(OrePrefixes.plateDense, Materials.Thaumium, 1),
                new ItemStack(net.minecraft.init.Blocks.glass_pane),
                GT_OreDictUnificator.get(OrePrefixes.plateDense, Materials.Thaumium, 1),
                new ItemStack(net.minecraft.init.Blocks.glass_pane),
                GT_OreDictUnificator.get(OrePrefixes.frameGt, Materials.Titanium, 1),
                GT_OreDictUnificator.get(OrePrefixes.plateDense, Materials.Thaumium, 1),
                new ItemStack(net.minecraft.init.Blocks.glass_pane),
                GT_OreDictUnificator.get(OrePrefixes.plateDense, Materials.Thaumium, 1),
                new ItemStack(net.minecraft.init.Blocks.glass_pane), };
        final AspectList aspects_jarthaumiumreinforced = new AspectList().add(Aspect.ARMOR, 64).add(Aspect.ORDER, 32)
                .add(Aspect.WATER, 32).add(Aspect.GREED, 16).add(Aspect.VOID, 16).add(Aspect.AIR, 8);
        infusionRecipes.put(
                "THAUMIUMREINFORCEDJAR",
                ThaumcraftApi.addInfusionCraftingRecipe(
                        "THAUMIUMREINFORCEDJAR",
                        new ItemStack(Blocks.jarThaumiumReinforced, 1, 0),
                        5,
                        aspects_jarthaumiumreinforced,
                        ItemApi.getBlock("blockJar", 0),
                        recipe_jarthaumiumreinforced));
        // Thaumium Reinforced Void Jar
        final ItemStack[] recipe_voidjarupgrade = {
                GT_OreDictUnificator.get(OrePrefixes.plateDense, Materials.Obsidian, 1),
                GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Blaze, 1),
                GT_OreDictUnificator.get(OrePrefixes.plate, Materials.EnderEye, 1), ItemApi.getItem("itemNugget", 5) };
        final AspectList aspects_voidjarupgrade = new AspectList().add(Aspect.VOID, 14).add(Aspect.MAGIC, 14)
                .add(Aspect.ENTROPY, 14).add(Aspect.WATER, 14);
        infusionRecipes.put(
                "THAUMIUMREINFORCEDVOIDJAR",
                ThaumcraftApi.addInfusionCraftingRecipe(
                        "THAUMIUMREINFORCEDJAR",
                        new ItemStack(Blocks.jarThaumiumReinforced, 1, 3),
                        2,
                        aspects_voidjarupgrade,
                        new ItemStack(Blocks.jarThaumiumReinforced, 1, 0),
                        recipe_voidjarupgrade));

        final ItemStack[] recipe_jarichor = { GT_ModHandler.getModItem(ThaumicTinkerer.ID, "kamiResource", 1, 0),
                GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Diamond, 1),
                new ItemStack(net.minecraft.init.Blocks.glass_pane),
                GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Osmiridium, 1),
                GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Diamond, 1),
                new ItemStack(net.minecraft.init.Blocks.glass_pane),
                GT_OreDictUnificator.get(OrePrefixes.gemExquisite, Materials.Diamond, 1),
                GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Diamond, 1),
                new ItemStack(net.minecraft.init.Blocks.glass_pane),
                GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Osmiridium, 1),
                GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Diamond, 1),
                new ItemStack(net.minecraft.init.Blocks.glass_pane), };
        final AspectList aspects_jarichor = new AspectList().add(Aspect.ARMOR, 256).add(Aspect.ELDRITCH, 128)
                .add(Aspect.ORDER, 128).add(Aspect.WATER, 128).add(Aspect.GREED, 64).add(Aspect.VOID, 64)
                .add(Aspect.AIR, 32); */
    }
}


