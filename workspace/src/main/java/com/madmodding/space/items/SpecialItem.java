package com.madmodding.space.items;

import com.madmodding.space.Main;
import com.madmodding.space.items.element.ElementLib;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SpecialItem extends Item {

	public SpecialItem(String unlocalizedName) {
		super();
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(Main.aliensTabTech);
	}

	public ItemStack onItemRightClick(ItemStack stack, World p_77659_2_, EntityPlayer player) {
		return stack;
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return Main.PLUS;
	}
}
