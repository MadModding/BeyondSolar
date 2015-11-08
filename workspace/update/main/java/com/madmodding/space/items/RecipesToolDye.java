package com.madmodding.space.items;

import java.util.ArrayList;

import com.google.common.collect.Lists;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class RecipesToolDye implements IRecipe {
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
				if (itemstack1.getItem() instanceof IColorable) {
					IColorable itemsword = (IColorable) itemstack1.getItem();

					itemstack = itemstack1;
				} else {
					if (itemstack1.getItem() != ModItems.dye) {
						return false;
					}

					arraylist.add(itemstack1);
				}
			}
		}

		return itemstack != null && !arraylist.isEmpty();
	}

	/**
	 * Returns an Item that is the result of this recipe
	 */
	public ItemStack getCraftingResult(InventoryCrafting p_77572_1_) {
		ItemStack itemstack = null;
		int[] aint = new int[3];
		int i = 0;
		int j = 0;
		IColorable itemsword = null;
		int k;
		int l;
		float f;
		float f1;
		int l1;

		for (k = 0; k < p_77572_1_.getSizeInventory(); ++k) {
			ItemStack itemstack1 = p_77572_1_.getStackInSlot(k);

			if (itemstack1 != null) {
				if (itemstack1.getItem() instanceof IColorable) {
					itemsword = (IColorable) itemstack1.getItem();

					itemstack = itemstack1.copy();
					itemstack.stackSize = 1;

					if (itemstack1.getTagCompound().hasKey("Color" + itemstack1.getTagCompound().getInteger("Mode"))) {
						l = itemstack1.getTagCompound()
								.getInteger("Color" + itemstack1.getTagCompound().getInteger("Mode"));
						f = (float) (l >> 16 & 255) / 255.0F;
						f1 = (float) (l >> 8 & 255) / 255.0F;
						float f2 = (float) (l & 255) / 255.0F;
						i = (int) ((float) i + Math.max(f, Math.max(f1, f2)) * 255.0F);
						aint[0] = (int) ((float) aint[0] + f * 255.0F);
						aint[1] = (int) ((float) aint[1] + f1 * 255.0F);
						aint[2] = (int) ((float) aint[2] + f2 * 255.0F);
						++j;
					}
				} else {
					if (itemstack1.getItem() != ModItems.dye) {
						return null;
					}
					int r = 0;
					int g = 0;
					int b = 0;
					int[] dyeColors = new int[] { 1973019, 11743532, 3887386, 5320730, 2437522, 8073150, 2651799,
							11250603, 4408131, 14188952, 4312372, 14602026, 6719955, 12801229, 15435844, 15790320 };
					if (itemstack1.getItemDamage() < 16) {
						int i1 = itemstack1.getItemDamage();
						r = dyeColors[i1] / 65536;
						g = (dyeColors[i1] - r * 65536) / 256;
						b = (dyeColors[i1] - g * 256 - r * 65536);

					} else if (itemstack1.getItemDamage() < 256) {
						int x = itemstack1.getItemDamage()-16;
						int i1 = x / 16;
						int i2 = x - i1 * 16;
						int r1 = dyeColors[i1] / 65536;
						int g1 = (dyeColors[i1] - r1 * 65536) / 256;
						int b1 = (dyeColors[i1] - g1 * 256 - r1 * 65536);
						int r2 = dyeColors[i2] / 65536;
						int g2 = (dyeColors[i2] - r2 * 65536) / 256;
						int b2 = (dyeColors[i2] - g2 * 256 - r2 * 65536);
						 r = (r1 + r2) / 2;
						 g = (g1 + g2) / 2;
						 b = (b1 + b2) / 2;
					}
					int j1 = r;
					int k1 = g;
					l1 = b;
					i += Math.max(j1, Math.max(k1, l1));
					aint[0] += j1;
					aint[1] += k1;
					aint[2] += l1;
					++j;
				}
			}
		}

		if (itemsword == null) {
			return null;
		} else {
			k = aint[0] / j;
			int i1 = aint[1] / j;
			l = aint[2] / j;
			f = (float) i / (float) j;
			f1 = (float) Math.max(k, Math.max(i1, l));
			k = (int) ((float) k * f / f1);
			i1 = (int) ((float) i1 * f / f1);
			l = (int) ((float) l * f / f1);
			l1 = (k << 8) + i1;
			l1 = (l1 << 8) + l;
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