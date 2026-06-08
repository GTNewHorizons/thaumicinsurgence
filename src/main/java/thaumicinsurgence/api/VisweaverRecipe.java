package thaumicinsurgence.api;

import net.minecraft.item.ItemStack;

import com.github.bsideup.jabel.Desugar;

import thaumcraft.api.aspects.Aspect;

@Desugar
public record VisweaverRecipe(Aspect aspect, int cost, ItemStack input, ItemStack output) {}
