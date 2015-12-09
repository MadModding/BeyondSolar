package com.madmodding.space.enchant;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class CustomEnchantment extends Enchantment {
	private static final String __OBFID = "CL_00000100";
	private final int minench;
	private final int maxench;
	private final Enchantment prelim;
	private final int maxlevel;

	/**
	 * @param nec
	 *            Enchantment required to add this one, null for none
	 * @param name
	 *            Unlocalized, what the enchantment is called
	 * @param minench
	 *            Base Enchantability <br>
	 *            1 - Protection <br>
	 *            20 - Infinity
	 * @param maxench
	 *            Level Enchantability <br>
	 *            11 - Protection <br>
	 *            50 - Infinity
	 * @param maxlevel
	 *            The highest level the enchantment can be gotten at
	 * @param enchID
	 *            A number less than 256 that represents this enchant
	 * @param enchName
	 *            An even more unlocalized name for this enchantment...
	 * @param enchWeight
	 *            How common it is, basically on a scale of 1 to 10 in practice
	 *            <br>
	 *            1 - Rare (Infinity) <br>
	 *            10 - Very Common (Protection)
	 * @param type
	 *            The type of item it can be attached to
	 */
	public CustomEnchantment(Enchantment nec, String name, int minench, int maxench, int maxlevel, int enchID,
			ResourceLocation enchName, int enchWeight, EnumEnchantmentType type) {
		super(enchID, enchName, enchWeight, type);
		this.setName(name);
		this.prelim = nec;
		this.minench = minench;
		this.maxench = maxench;
		this.maxlevel = maxlevel;
	}

	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		if (prelim != null)
			return EnchantmentHelper.getEnchantmentLevel(prelim.effectId, stack) != 0;
		else
			return true;
	}

	/**
	 * Returns the minimal value of enchantability needed on the enchantment
	 * level passed.
	 */
	public int getMinEnchantability(int enchantmentLevel) {
		return minench;
	}

	/**
	 * Returns the maximum value of enchantability nedded on the enchantment
	 * level passed.
	 */
	public int getMaxEnchantability(int enchantmentLevel) {
		return maxench;
	}

	/**
	 * Returns the maximum level that the enchantment can have.
	 */
	public int getMaxLevel() {
		return maxlevel;
	}
}