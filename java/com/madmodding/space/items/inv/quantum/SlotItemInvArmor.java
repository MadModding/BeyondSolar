package com.madmodding.space.items.inv.quantum;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotItemInvArmor extends Slot {
	private int type;

	public SlotItemInvArmor(int type, IInventory par1iInventory, int par2, int par3, int par4) {
		super(par1iInventory, par2, par3, par4);
		this.type = type;
	}

	/**
	 * Check if the stack is a valid item for this slot.
	 */
	@Override
	public boolean isItemValid(ItemStack itemstack) {
		if (itemstack.getItem().isValidArmor(itemstack, type, null))
			return true;
		return false;
	}
}
