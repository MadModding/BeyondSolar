package com.madmodding.space.enchant;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.ResourceLocation;

public class ModEnchants {
	public static Enchantment madhat = new EnchantmentMadness(91, new ResourceLocation("madness"), 1);
	public static Enchantment arideus = new EnchantmentDivine(92, new ResourceLocation("arideus"), 1);

	public static void init() {
		Enchantment.addToBookList(madhat);
		Enchantment.addToBookList(arideus);
	}
}
