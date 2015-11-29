package com.madmodding.space.enchant;

import java.util.Random;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class EnchantmentMadness extends Enchantment {
	private static final String __OBFID = "CL_00000103";

	protected EnchantmentMadness(int enchID, ResourceLocation enchName, int enchWeight) {
		super(enchID, enchName, enchWeight, EnumEnchantmentType.BREAKABLE);
		this.setName("madness");
	}

	/**
	 * Returns the minimal value of enchantability needed on the enchantment
	 * level passed.
	 */
	public int getMinEnchantability(int enchantmentLevel) {
		return 5 + (enchantmentLevel - 1) * 8;
	}

	/**
	 * Returns the maximum value of enchantability nedded on the enchantment
	 * level passed.
	 */
	public int getMaxEnchantability(int enchantmentLevel) {
		return super.getMinEnchantability(enchantmentLevel) + 50;
	}

	/**
	 * Returns the maximum level that the enchantment can have.
	 */
	public int getMaxLevel() {
		return 10;
	}

	public static boolean func_92094_a(int p_92094_0_, Random p_92094_1_) {
		return p_92094_0_ <= 0 ? false : p_92094_1_.nextFloat() < 0.15F * (float) p_92094_0_;
	}

	public static int func_92095_b(int p_92095_0_, Random p_92095_1_) {
		return p_92095_0_ > 10 ? p_92095_0_ - 10 : 1 + p_92095_1_.nextInt(4);
	}

	public void onUserHurt(EntityLivingBase user, Entity attacker, int level) {
		Random random = user.getRNG();
		ItemStack itemstack = EnchantmentHelper.getEnchantedItem(ModEnchants.madhat, user);

		if (func_92094_a(getTrueLevel(level), random)) {
			attacker.attackEntityFrom(DamageSource.causeThornsDamage(user),
					(float) func_92095_b(getTrueLevel(level), random));
			attacker.playSound("damage.thorns", 0.5F, 1.0F);

		}
	}

	public static int getTrueLevel(int level) {
		return (int) Math.sqrt(level);
	}

	public int calcModifierDamage(int level, DamageSource source) {
		if (source.canHarmInCreative()) {
			return 0;
		} else {
			float f = (float) (6 + getTrueLevel(level) * getTrueLevel(level)) / 3.0F;
			return MathHelper.floor_float(f * 1.5F);
		}
	}

	/**
	 * Determines if this enchantment can be applied to a specific ItemStack.
	 * 
	 * @param stack
	 *            The ItemStack that is attempting to become enchanted with with
	 *            enchantment.
	 */
	public boolean canApply(ItemStack stack) {
		return false;
	}

	/**
	 * Used by ItemStack.attemptDamageItem. Randomly determines if a point of
	 * damage should be negated using the enchantment level (par1). If the
	 * ItemStack is Armor then there is a flat 60% chance for damage to be
	 * negated no matter the enchantment level, otherwise there is a 1-(par/1)
	 * chance for damage to be negated.
	 */
	public static boolean negateDamage(ItemStack p_92097_0_, int level, Random p_92097_2_) {
		return p_92097_0_.getItem() instanceof ItemArmor && p_92097_2_.nextFloat() < 0.6F ? false
				: p_92097_2_.nextInt(getTrueLevel(level) + 1) > 0;
	}
}
