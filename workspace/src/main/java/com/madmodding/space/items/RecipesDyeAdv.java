package com.madmodding.space.items;

import java.util.ArrayList;

import com.google.common.collect.Lists;

import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class RecipesDyeAdv implements IRecipe {
	private static final String __OBFID = "CL_00000079";

	/**
	 * Used to check if a recipe matches current crafting inventory
	 */
	public boolean matches(InventoryCrafting p_77569_1_, World worldIn) {
		ItemStack itemstack = null;
		ArrayList arraylist = Lists.newArrayList();

		for (int i = 0; i < p_77569_1_.getSizeInventory(); ++i) {
			ItemStack itemstack1 = p_77569_1_.getStackInSlot(i);

			if (itemstack1 != null) {
				if (i == 0 && itemstack1.getItem() != Items.dye) {
					return false;
				}
				if (i == 1 && itemstack1.getItem() != Items.dye) {
					return false;
				}
				if (i == 2 && itemstack1.getItem() != ModItems.fragment&& itemstack1.getItemDamage() == 101) {
					return false;
				}
				if (i == 3 && itemstack1.getItem() != Items.dye) {
					return false;
				}
				if (i == 4 && itemstack1.getItem() != Items.dye) {
					return false;
				}
				if (i == 5 && !(itemstack1.getItem() instanceof IColorable)) {
					return false;
				} else if (i == 5 && itemstack1.getItem() instanceof IColorable) {
					itemstack = itemstack1;
				}
				if (i == 6 && itemstack1.getItem() != Items.dye) {
					return false;
				}
				if (i == 7 && itemstack1.getItem() != Items.dye) {
					return false;
				}
			} else {
				if (i != 8) {
					return false;
				}
			}

		}

		return true;
	}

	/**
	 * Returns an Item that is the result of this recipe
	 */
	public ItemStack getCraftingResult(InventoryCrafting p_77572_1_) {
		ItemStack itemstack = null;
		int[] aint = new int[3];
		IColorable itemsword = null;
		int l1 = 0;
		int r1 = 0;
		int g1 = 0;
		int b1 = 0;

		for (int k = 0; k < p_77572_1_.getSizeInventory(); ++k) {
			ItemStack itemstack1 = p_77572_1_.getStackInSlot(k);

			if (itemstack1 != null) {
				if (itemstack1.getItem() instanceof IColorable) {
					itemsword = (IColorable) itemstack1.getItem();

					itemstack = itemstack1.copy();
					itemstack.stackSize = 1;
				} else if (itemstack1.getItem() == Items.dye) {
					if (k == 0) {
						r1 += itemstack1.getItemDamage() * 16;
					} else if (k == 1) {
						r1 += itemstack1.getItemDamage();
					} else if (k == 3) {
						g1 += itemstack1.getItemDamage() * 16;
					} else if (k == 4) {
						g1 += itemstack1.getItemDamage();
					} else if (k == 6) {
						b1 += itemstack1.getItemDamage() * 16;
					} else if (k == 7) {
						b1 += itemstack1.getItemDamage();
					}
				}

			}
		}
		l1 = r1 * 65536 + g1 * 256 + b1;
		if (itemsword == null) {
			return null;
		} else {
			itemstack.getTagCompound().setInteger("Color" + itemstack.getTagCompound().getInteger("Mode"), l1);
			return itemstack;
		}
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