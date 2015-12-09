package com.madmodding.space.blocks.tile.forge;

import net.minecraft.item.ItemStack;

public interface IForgeable {
	public int[][] getElementRatio(ItemStack stack);
	public double getMB(ItemStack stack);
	
}
