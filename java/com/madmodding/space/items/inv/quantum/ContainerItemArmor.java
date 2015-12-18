package com.madmodding.space.items.inv.quantum;

import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ContainerItemArmor extends Container {
	public void onContainerClosed(EntityPlayer playerIn) {
		InventoryPlayer inventoryplayer = playerIn.inventory;

		if (inventoryplayer.getItemStack() != null) {
			playerIn.dropPlayerItemWithRandomChoice(inventoryplayer.getItemStack(), false);
			inventoryplayer.setItemStack((ItemStack) null);
		}
		if (playerIn.getHeldItem() == null || !(playerIn.getHeldItem().getItem() instanceof ItemArmorQuantum
				&& ((ItemArmor) playerIn.getHeldItem().getItem()).armorType == i)) {
			if (!playerIn.worldObj.isRemote) {
				for (int i = 0; i < ii.getSizeInventory(); ++i) {
					ItemStack itemstack = ii.getStackInSlot(i);

					if (itemstack != null) {
						spawnItemStack(playerIn.getRNG(), playerIn.worldObj, playerIn.posX, playerIn.posY,
								playerIn.posZ, itemstack);
					}
				}
			}
		} else {
			if (!playerIn.getHeldItem().hasTagCompound())
				playerIn.getHeldItem().setTagCompound(new NBTTagCompound());
			playerIn.getHeldItem().getTagCompound().setTag("inv", ii.saveInventoryToNBT());
			playerIn.getHeldItem().getTagCompound().setBoolean("open", false);
		}
	}

	private static void spawnItemStack(Random RANDOM, World worldIn, double p_180173_1_, double p_180173_3_,
			double p_180173_5_, ItemStack p_180173_7_) {
		float f = RANDOM.nextFloat() * 0.8F + 0.1F;
		float f1 = RANDOM.nextFloat() * 0.8F + 0.1F;
		float f2 = RANDOM.nextFloat() * 0.8F + 0.1F;

		while (p_180173_7_.stackSize > 0) {
			int i = RANDOM.nextInt(21) + 10;

			if (i > p_180173_7_.stackSize) {
				i = p_180173_7_.stackSize;
			}

			p_180173_7_.stackSize -= i;
			EntityItem entityitem = new EntityItem(worldIn, p_180173_1_ + (double) f, p_180173_3_ + (double) f1,
					p_180173_5_ + (double) f2, new ItemStack(p_180173_7_.getItem(), i, p_180173_7_.getMetadata()));

			if (p_180173_7_.hasTagCompound()) {
				entityitem.getEntityItem().setTagCompound((NBTTagCompound) p_180173_7_.getTagCompound().copy());
			}

			float f3 = 0.05F;
			entityitem.motionX = RANDOM.nextGaussian() * (double) f3;
			entityitem.motionY = RANDOM.nextGaussian() * (double) f3 + 0.20000000298023224D;
			entityitem.motionZ = RANDOM.nextGaussian() * (double) f3;
			worldIn.spawnEntityInWorld(entityitem);
		}
	}

	private InventoryItemArmor ii;
	private int i;

	public ContainerItemArmor(int type, IInventory playerInv, InventoryItemArmor ii) {
		int x;
		int y;
		this.ii = ii;
		this.i = type;
		this.addSlotToContainer(new SlotItemInvArmor(type, ii, 0, 71, 24));
		this.addSlotToContainer(new SlotItemInvArmor(type, ii, 1, 89, 24));

		for (y = 0; y < 3; ++y) {
			for (x = 0; x < 9; ++x) {
				this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, 8 + x * 18, 53 + y * 18));
			}
		}

		for (x = 0; x < 9; ++x) {
			this.addSlotToContainer(new Slot(playerInv, x, 8 + x * 18, 111));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
		ItemStack previous = null;
		Slot slot = (Slot) this.inventorySlots.get(fromSlot);

		if (slot != null && slot.getHasStack()) {
			ItemStack current = slot.getStack();
			previous = current.copy();

			if (fromSlot < 14) {
				// From TE Inventory to Player Inventory
				if (!this.mergeItemStack(current, 9, 0, true))
					return null;
			} else {
				// From Player Inventory to TE Inventory
				if (!this.mergeItemStack(current, 0, 9, false))
					return null;
			}

			if (current.stackSize == 0)
				slot.putStack((ItemStack) null);
			else
				slot.onSlotChanged();

			if (current.stackSize == previous.stackSize)
				return null;
			slot.onPickupFromSlot(playerIn, current);
		}

		return previous;
	}
}
