package com.madmodding.space.items;

import com.madmodding.space.Main;
import com.madmodding.space.entity.item.EntityAcidAttack;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class acidSquirter extends SpecialItem {
	public acidSquirter(String name) {
		super(name);
		this.setUnlocalizedName(name);
		this.setCreativeTab(Main.aliensTabTech);
		this.setMaxStackSize(1);
	}

	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BOW;
	}

	public ItemStack onItemRightClick(ItemStack stack, World p_77659_2_, EntityPlayer player) {
		player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
		return stack;
	}

	public int getMaxItemUseDuration(ItemStack stack) {
		return 72000;
	}

	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityPlayer playerIn, int timeLeft) {
		worldIn.spawnEntityInWorld(new EntityAcidAttack(worldIn, playerIn));
	}
}
