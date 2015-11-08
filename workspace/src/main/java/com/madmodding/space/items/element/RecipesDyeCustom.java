package com.madmodding.space.items.element;

import java.util.ArrayList;
import java.util.Random;

import com.google.common.collect.Lists;
import com.madmodding.space.items.ModItems;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class RecipesDyeCustom implements IRecipe {
	private static final String __OBFID = "CL_00000079";
	
	/**
	 * Used to check if a recipe matches current crafting inventory
	 */
	public boolean matches(InventoryCrafting p_77569_1_, World worldIn) {
		ItemStack itemstack = null;
		ArrayList arraylist = Lists.newArrayList();
		int c = 0;
		for (int i = 0; i < p_77569_1_.getSizeInventory(); ++i) {
			ItemStack itemstack1 = p_77569_1_.getStackInSlot(i);

			if (itemstack1 != null) {
				if (itemstack1.getItem() instanceof ItemDyeSpec)
					c++;
			}

		}

		return c == 2;
	}

	/**
	 * Returns an Item that is the result of this recipe
	 */
	public ItemStack getCraftingResult(InventoryCrafting p_77572_1_) {
		ItemStack stack = new ItemStack(ElementLib.Dye, 2);
		ItemStack dye1 = null;
		ItemStack dye2 = null;
		for (int k = 0; k < p_77572_1_.getSizeInventory(); ++k) {
			if (p_77572_1_.getStackInSlot(k) != null) {
				if (dye1 == null)
					dye1 = p_77572_1_.getStackInSlot(k).copy();
				else
					dye2 = p_77572_1_.getStackInSlot(k).copy();
			}
		}
		Random rand = new Random();
		if (dye1.getItemDamage() < 16 && dye2.getItemDamage() < 16) {
			stack.setItemDamage(16+dye1.getItemDamage() + dye2.getItemDamage() * 16);
		}
		else if(dye2.getItemDamage()!=0){
			int i = dye1.getItemDamage()+rand.nextInt(dye2.getItemDamage());
			if(i>271)i = 271;
			stack.setItemDamage(i);
		} else {
			int i = rand.nextInt(271);
			if(i>271)i = 271;
			stack.setItemDamage(i);
		}
		return stack;
	}

	/**
	 * Returns the size of the recipe area
	 */
	public int getRecipeSize() {
		return 10;
	}

	public ItemStack getRecipeOutput() {
		return null;
	}

	public ItemStack[] getRemainingItems(InventoryCrafting p_179532_1_) {
		ItemStack[] aitemstack = new ItemStack[p_179532_1_.getSizeInventory()];

		for (int i = 0; i < aitemstack.length; ++i) {
			ItemStack itemstack = p_179532_1_.getStackInSlot(i);
			aitemstack[i] = net.minecraftforge.common.ForgeHooks.getContainerItem(itemstack);
		}

		return aitemstack;
	}
}