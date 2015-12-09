package com.madmodding.space.enchant;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.util.ResourceLocation;

public class ModEnchants {
	public static Enchantment madhat;
	public static Enchantment arideus;
	public static Enchantment gungho;

	public static void init() {
		madhat = new EnchantmentMadness(191, new ResourceLocation("madness"), 1);
		arideus = new EnchantmentDivine(192, new ResourceLocation("arideus"), 1);
		gungho = new CustomEnchantment(Enchantment.knockback, "gungho", 13, 32, 1, 193, new ResourceLocation("gungho"),
				7, EnumEnchantmentType.WEAPON);
		Enchantment.addToBookList(gungho);

	}
}
