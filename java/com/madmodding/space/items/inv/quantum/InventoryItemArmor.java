package com.madmodding.space.items.inv.quantum;

import com.madmodding.space.items.ModItems;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityEnderChest;

public class InventoryItemArmor extends InventoryBasic {
	private static final String __OBFID = "CL_00001759";

	public InventoryItemArmor() {
		super("container.spacearmor", false, 27);
	}

	public int getSizeInventory() {
		return 2;
	}

	public void loadInventoryFromNBT(NBTTagList list) {
		int i;

		for (i = 0; i < this.getSizeInventory(); ++i) {
			this.setInventorySlotContents(i, (ItemStack) null);
		}

		for (i = 0; i < list.tagCount(); ++i) {
			NBTTagCompound nbttagcompound = list.getCompoundTagAt(i);
			int j = nbttagcompound.getByte("Slot") & 255;

			if (j >= 0 && j < this.getSizeInventory()) {
				this.setInventorySlotContents(j, ItemStack.loadItemStackFromNBT(nbttagcompound));
			}
		}
	}

	public NBTTagList saveInventoryToNBT() {
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.getSizeInventory(); ++i) {
			ItemStack itemstack = this.getStackInSlot(i);

			if (itemstack != null) {
				NBTTagCompound nbttagcompound = new NBTTagCompound();
				nbttagcompound.setByte("Slot", (byte) i);
				itemstack.writeToNBT(nbttagcompound);
				nbttaglist.appendTag(nbttagcompound);
			}
		}

		return nbttaglist;
	}

	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}

	public void closeInventory(EntityPlayer player) {
		if (player.getHeldItem() != null && player.getHeldItem().getItem() instanceof ItemArmorQuantum) {
			if (!player.getHeldItem().hasTagCompound())
				player.getHeldItem().setTagCompound(new NBTTagCompound());
			player.getHeldItem().getTagCompound().setTag("inv", saveInventoryToNBT());
			player.getHeldItem().getTagCompound().setBoolean("open", false);
		}
		super.closeInventory(player);
	}
}