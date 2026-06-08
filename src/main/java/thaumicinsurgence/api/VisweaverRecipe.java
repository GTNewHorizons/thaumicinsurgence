package thaumicinsurgence.api;

import net.minecraft.item.ItemStack;

import thaumcraft.api.aspects.Aspect;

public record VisweaverRecipe(Aspect aspect, int cost, ItemStack input, ItemStack output) {}
