package com.madmodding.space.items.inv;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotItemInv extends Slot
{
	public SlotItemInv(IInventory par1iInventory, int par2, int par3, int par4)
	{
		super(par1iInventory, par2, par3, par4);
	}
	/**
	 * Check if the stack is a valid item for this slot.
	 */
	@Override
	public boolean isItemValid(ItemStack itemstack) {
		return true;
	}
}
