package com.madmodding.space.items.boost;

import java.util.UUID;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.madmodding.space.items.element.ElementLib;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemArmorBoost extends ItemArmor {
	private double[][] stats;
	
	/**
	 * Armor that gives the player a boost! or a negative effect...
	 * 
	 * @param damageOff
	 *            Percent Damage Reduction
	 * @param armorType
	 *            The Type of Armor, 0 - Helmet, 1 - Chestplate, 2 - Leggings, 3
	 *            - Boots
	 * @param stats
	 *            SpeedBoost(Unique Int, Value),DmgBoost(Unique Int,
	 *            Percent),KnockbackResistance(Unique Int, Percent),HealthBoost(Unique Int, Percent),FollowRange(Unique Int, Percent)
	 */
	public ItemArmorBoost(String name, int armorType, double[][] stats) {
		super(ArmorMaterial.LEATHER, armorType, armorType);
		this.stats = stats;
		this.setUnlocalizedName(name);
	}

	public Multimap getAttributeModifiers(ItemStack stack) {
		Multimap multimap = HashMultimap.create();
		
		if(stats[0][1]!=0)
		multimap.put(SharedMonsterAttributes.movementSpeed.getAttributeUnlocalizedName(),
				new AttributeModifier(UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5"+((int)stats[0][0])), "Armor Modifier "+stats[0][0], stats[0][1], 1));
		if(stats[1][1]!=0)
			multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(),
					new AttributeModifier(UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5"+((int)stats[1][0])), "Armor Modifier "+stats[1][0], stats[1][1], 1));
		if(stats[2][1]!=0)
			multimap.put(SharedMonsterAttributes.knockbackResistance.getAttributeUnlocalizedName(),
					new AttributeModifier(UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5"+((int)stats[2][0])), "Armor Modifier "+stats[2][0], stats[2][1], 1));
		if(stats[3][1]!=0)
			multimap.put(SharedMonsterAttributes.maxHealth.getAttributeUnlocalizedName(),
					new AttributeModifier(UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5"+((int)stats[3][0])), "Armor Modifier "+stats[3][0], stats[3][1], 1));
		if(stats[4][1]!=0)
			multimap.put(SharedMonsterAttributes.followRange.getAttributeUnlocalizedName(),
					new AttributeModifier(UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5"+((int)stats[4][0])), "Armor Modifier "+stats[4][0], stats[4][1], 1));
			return multimap;
	}
}
