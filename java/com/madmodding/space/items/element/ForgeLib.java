package com.madmodding.space.items.element;

import java.util.ArrayList;
import java.util.List;

import com.madmodding.space.blocks.tile.forge.IForgeable;
import com.madmodding.space.items.IFirstTick;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ForgeLib {
	public static ItemStack[] forgeItem(ItemStack[] inv) {
		ItemStack[] inventory = new ItemStack[13];
		for (int i = 0; i < 13; i++)
			if (inv[i] != null)
				inventory[i] = inv[i].copy();
		try {
			ItemStack itemstack = null;
			int type = 0;
			int count = 0;
			int last = 0;
			for (int i = 0; i < 9; i++) {
				if (inventory[i] != null && !(inventory[i].getItem() instanceof IForgeable)) {
					return new ItemStack[0];
				}
				if (inventory[i] != null && inventory[i].getItem() instanceof IForgeable) {
					count++;
					last = i;
				}
			}
			if (count == 0)
				return new ItemStack[0];

			if (count == 1 && inventory[last].getItem() == ElementLib.Fragment
					&& ElementLib.Elements[inventory[last].getItemDamage() % ElementLib.Elements.length]
							.getType() == 2) {
				type = 1;
			}
			if (count == 1 && inventory[last].getItem() == ElementLib.Fragment
					&& ElementLib.Elements[inventory[last].getItemDamage() % ElementLib.Elements.length]
							.getType() == 3) {
				type = 2;
			}
			//System.out.println("Forge Type " + type);
			if (type == 1) {// Dust Purification
				itemstack = Type1(inventory, last, itemstack);
				if (itemstack == null)
					return new ItemStack[0];
			} else if (type == 2) {// Liquid/Gas Purification
				itemstack = Type2(inventory, last, itemstack);
				if (itemstack == null)
					return new ItemStack[0];
			} else {
				itemstack = Type0(inventory, itemstack);
				if (itemstack == null)
					return new ItemStack[0];
			}
			//System.out.println("Forge Item Created");
			if (inventory[12] == null)
				inventory[12] = itemstack.copy();
			else
				inventory[12].stackSize += itemstack.stackSize;
			for (int i = 0; i < 9; i++)
				if (inventory[i] != null) {
					inventory[i].stackSize--;
					if (inventory[i].stackSize == 0)
						inventory[i] = null;
				}
			//System.out.println("Forge Returning Item");
			return inventory;
		} catch (Exception ex) {
			//System.out.println("Forge Exception");
			//ex.printStackTrace(System.out);
			return new ItemStack[0];
		}
	}

	private static ItemStack Type2(ItemStack[] inventory, int last, ItemStack itemstack) {
		//System.out.println("Forge Type 2 Creating Item");
		itemstack = new ItemStack(ElementLib.Refined, 1, inventory[last].getItemDamage());
		int[][] metals = new int[2][1];
		metals[0][0] = ((IForgeable) inventory[last].getItem()).getElementRatio(inventory[last])[0][0] / 2;
		metals[1][0] = ((IForgeable) inventory[last].getItem()).getElementRatio(inventory[last])[1][0];
		//System.out.println("Forge Type 2 Setting Blend");
		ItemRefined.setBlend(itemstack, metals);
		((IFirstTick) itemstack.getItem()).onFirstTick(itemstack);
		if (inventory[11] == null) {
			//System.out.println("Forge Type 2 No Glass Bottles");
			return null;
		}
		if (inventory[11].getItem() != Items.glass_bottle) {
			//System.out.println("Forge Type 2 Item not Glass Bottle");
			return null;
		}
		inventory[11].stackSize--;
		if (inventory[11].stackSize == 0) {
			inventory[11] = null;
		}
		if (inventory[12] == null) {
			//System.out.println("Forge Type 2 Slot Empty");
			return itemstack;
		}
		if (!(ItemStack.areItemStackTagsEqual(inventory[12], itemstack)
				&& ItemStack.areItemsEqual(inventory[12], itemstack))) {
			//System.out.println("Forge Type 2 Items dont match");
			return null;
		}
		int result = inventory[12].stackSize + itemstack.stackSize;
		if (result <= 64 && result <= inventory[12].getMaxStackSize())
			return itemstack;
		return null;
	}

	private static ItemStack Type1(ItemStack[] inventory, int last, ItemStack itemstack) {
		itemstack = new ItemStack(ElementLib.Refined, 1, inventory[last].getItemDamage());
		int[][] metals = new int[2][1];
		metals[0][0] = ((IForgeable) inventory[last].getItem()).getElementRatio(inventory[last])[0][0] / 2;
		metals[1][0] = ((IForgeable) inventory[last].getItem()).getElementRatio(inventory[last])[1][0];
		ItemRefined.setBlend(itemstack, metals);
		((IFirstTick) itemstack.getItem()).onFirstTick(itemstack);
		if (inventory[12] == null)
			return itemstack;
		if (!(ItemStack.areItemStackTagsEqual(inventory[12], itemstack)
				&& ItemStack.areItemsEqual(inventory[12], itemstack)))
			return null;
		int result = inventory[12].stackSize + itemstack.stackSize;
		if (result <= 64 && result <= inventory[12].getMaxStackSize())
			return itemstack;
		return null;
	}

	private static ItemStack Type0(ItemStack[] inventory, ItemStack itemstack) {
		int[][] metals;
		List elements = new ArrayList();
		for (int i = 0; i < 9; i++)
			if (inventory[i] != null) {
				int[][] o = ((IForgeable) inventory[i].getItem()).getElementRatio(inventory[i]);
				for (int l = 0; l < o[1].length; l++) {
					if (!elements.contains(o[1][l])) {
						elements.add(o[1][l]);
					}
				}
			}
		int[][][] ing = new int[9][2][elements.size()];
		for (int i = 0; i < 9; i++)
			if (inventory[i] != null) {
				ing[i] = ((IForgeable) inventory[i].getItem()).getElementRatio(inventory[i]);
			}

		metals = new int[2][elements.size()];
		{
			for (int i = 0; i < metals[1].length; i++) {
				metals[1][i] = (Integer) elements.get(i);
			}
			for (int i = 0; i < metals[0].length; i++) {
				for (int l = 0; l < 9; l++) {
					for (int q = 0; q < ing[l][0].length; q++)
						if (ing[l][1][q] == metals[1][i]) {
							metals[0][i] += ing[l][0][q];
						}
				}
			}
		}
		if (inventory[9] == null) {
			itemstack = new ItemStack(ElementLib.Refined, 1, 25);
			ItemRefined.setBlend(itemstack, metals);
			((IFirstTick) itemstack.getItem()).onFirstTick(itemstack);
		} else {
			if (inventory[9].getItem() == ElementLib.cast) {
				int t = inventory[9].getItemDamage();
				int d = 0;
				switch (t) {
				case 0:
					d = 13;
					break;
				case 1:
					d = 12;
					break;
				case 2:
					d = 10;
					break;
				case 3:
					d = 11;
					break;
				case 8:
					d = 9;
					break;
				case 9:
					d = 8;
					break;
				case 10:
					d = 2;
					break;
				case 11:
					d = 3;
					break;
				case 12:
					d = 1;
					break;
				case 13:
					d = 0;
					break;
				}
				if (t != 5 && t != 6 && t != 7 && t != 4)
					itemstack = new ItemStack(ElementLib.cast, 1, d);
				else if (t == 4)
					itemstack = new ItemStack(ElementLib.ElementHelm, 1, 25);
				else if (t == 5)
					itemstack = new ItemStack(ElementLib.ElementChest, 1, 25);
				else if (t == 6)
					itemstack = new ItemStack(ElementLib.ElementLegs, 1, 25);
				else
					itemstack = new ItemStack(ElementLib.ElementBoots, 1, 25);

				int temp1 = 0;
				for (int i = 0; i < metals[1].length; i++) {
					if (temp1 < ElementLib.Elements[metals[1][i] % ElementLib.Elements.length].getBoilingPoint()) {
						temp1 = ElementLib.Elements[metals[1][i] % ElementLib.Elements.length].getBoilingPoint();
					}
				}
				int temp2 = 0;
				for (int i = 0; i < ((IForgeable) inventory[9].getItem())
						.getElementRatio(inventory[9])[1].length; i++) {
					if (temp2 < ElementLib.Elements[((IForgeable) inventory[9].getItem())
							.getElementRatio(inventory[9])[1][i] % ElementLib.Elements.length].getBoilingPoint()) {
						temp2 = ElementLib.Elements[((IForgeable) inventory[9].getItem())
								.getElementRatio(inventory[9])[1][i] % ElementLib.Elements.length].getBoilingPoint();
					}
				}
				if (temp1 > temp2) {
					metals = mergeIntArrays(metals,
							((IForgeable) inventory[9].getItem()).getElementRatio(inventory[9]));
					inventory[9] = null;
				}
				ItemRefined.setBlend(itemstack, metals);
				((IFirstTick) itemstack.getItem()).onFirstTick(itemstack);
			} else if (inventory[9].getItem() instanceof ItemArmorMaterial) {
				if (inventory[9].getItem() == ElementLib.ElementHelm)
					itemstack = new ItemStack(ElementLib.cast, 1, 4);
				else if (inventory[9].getItem() == ElementLib.ElementChest)
					itemstack = new ItemStack(ElementLib.cast, 1, 5);
				else if (inventory[9].getItem() == ElementLib.ElementLegs)
					itemstack = new ItemStack(ElementLib.cast, 1, 6);
				else
					itemstack = new ItemStack(ElementLib.cast, 1, 7);
				int temp1 = 0;
				for (int i = 0; i < metals[1].length; i++) {
					if (temp1 < ElementLib.Elements[metals[1][i] % ElementLib.Elements.length].getBoilingPoint()) {
						temp1 = ElementLib.Elements[metals[1][i] % ElementLib.Elements.length].getBoilingPoint();
					}
				}
				int temp2 = 0;
				for (int i = 0; i < ((IForgeable) inventory[9].getItem())
						.getElementRatio(inventory[9])[1].length; i++) {
					if (temp2 < ElementLib.Elements[((IForgeable) inventory[9].getItem())
							.getElementRatio(inventory[9])[1][i] % ElementLib.Elements.length].getBoilingPoint()) {
						temp2 = ElementLib.Elements[((IForgeable) inventory[9].getItem())
								.getElementRatio(inventory[9])[1][i] % ElementLib.Elements.length].getBoilingPoint();
					}
				}
				if (temp1 > temp2) {
					//System.out.println("Melting Cast");
					metals = mergeIntArrays(metals,
							((IForgeable) inventory[9].getItem()).getElementRatio(inventory[9]));
					inventory[9] = null;
				}
				ItemRefined.setBlend(itemstack, metals);
				((IFirstTick) itemstack.getItem()).onFirstTick(itemstack);
			}
		}
		return itemstack;
	}

	private static int[][] mergeIntArrays(int[][] one, int[][] two) {
		List stuff = new ArrayList();
		for (int i = 0; i < one[0].length; i++) {
			if (!stuff.contains(one[1][i])) {
				//System.out.println("Adding " + one[1][i]);
				stuff.add(one[1][i]);
			}
		}
		for (int i = 0; i < two[0].length; i++) {
			if (!stuff.contains(two[1][i])) {
				//System.out.println("Adding " + two[1][i]);
				stuff.add(two[1][i]);
			}
		}
		int[][] array = new int[2][stuff.size()];
		for (int x = 0; x < 2; x++) {
			for (int z = 0; z < two[0].length; z++) {
				//System.out.println(x + "-" + z + "now is " + two[x][z]);
				array[x][z] = two[x][z];
			}
		}
		for (int x = 0; x < one[0].length; x++) {
			for (int i = 0; i < two[0].length; i++) {
				if (array[1] == one[1]) {
					//System.out.println(0 + "-" + i + "+=" + one[0][x]);
					array[0][i] += one[0][x];
					one[0][x] = -1;
					one[1][x] = -1;
				}
			}
		}
		for (int i = two[0].length; i < array[1].length; i++) {
			for (int l = 0; l < one[0].length; l++) {
				if (array[1][i] == 0 && one[1][l] != -1) {
					//System.out.println(1 + "-" + i + "=" + one[1][l]);
					//System.out.println(0 + "-" + i + "=" + one[0][l]);
					array[1][i] = one[1][l];
					array[0][i] = one[0][l];
					one[0][l] = -1;
					one[1][l] = -1;
				}
			}
		}
		return array;
	}
}
