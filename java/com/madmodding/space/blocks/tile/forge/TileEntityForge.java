package com.madmodding.space.blocks.tile.forge;

import java.util.ArrayList;
import java.util.List;

import com.madmodding.space.items.IFirstTick;
import com.madmodding.space.items.element.ElementLib;
import com.madmodding.space.items.element.ForgeLib;
import com.madmodding.space.items.element.ItemArmorMaterial;
import com.madmodding.space.items.element.ItemRefined;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;

public class TileEntityForge extends TileEntity implements IUpdatePlayerListBox, IInventory {

	private ItemStack[] inventory;
	private int cookTime;
	private int totalCookTime;
	private String customName;

	public void update() {
		boolean flag1 = false;
		// if (!this.worldObj.isRemote) {
		if ((this.worldObj.getBlockState(this.getPos().down()) != null
				&& this.worldObj.getBlockState(this.getPos().down()).getBlock() == Blocks.lava) && this.canSmelt()) {
			++this.cookTime;

			if (this.cookTime >= this.totalCookTime) {
				this.cookTime = 0;
				this.totalCookTime = this.getBurnTime(this.inventory[0]);
				this.smeltItem();
				flag1 = true;
			}
		} else {
			this.cookTime = 0;
		}
		// }

		if (flag1) {
			this.markDirty();
		}
	}

	public int getBurnTime(ItemStack stack) {
		return 200;
	}

	/**
	 * Returns true if the furnace can smelt an item, i.e. has a source item,
	 * destination stack isn't full, etc.
	 */
	private boolean canSmelt() {
		ItemStack[] stacks = ForgeLib.forgeItem(inventory);
		if (stacks.length > 2) {
			ItemStack itemstack = stacks[12];
			int total = 0;
			for (int i = 0; i < ((IForgeable) itemstack.getItem()).getElementRatio(itemstack)[0].length; i++)
				total += ((IForgeable) itemstack.getItem()).getElementRatio(itemstack)[0][i];
			this.totalCookTime = total / 9 * 50;
			if (this.inventory[12] == null)
				return true;
			if (!isItemStackEqual(inventory[12], itemstack))
				return false;

			int result = inventory[12].stackSize + itemstack.stackSize;

			return result <= getInventoryStackLimit() && result <= this.inventory[12].getMaxStackSize(); // Forge
		}
		return false;
	}

	private boolean isItemStackEqual(ItemStack stack1, ItemStack stack2) {
		return stack1.getItem() != stack2.getItem() ? false
				: (stack1.getMetadata() != stack2.getMetadata() ? false
						: (stack1.stackSize > stack1.getMaxStackSize() ? false
								: ItemStack.areItemStackTagsEqual(stack1, stack2)));
	}

	private static boolean canCombine(ItemStack a, ItemStack other) {
		return ((IForgeable) a.getItem()).getElementRatio(a) != ((IForgeable) other.getItem()).getElementRatio(other)
				? false
				: (a.getItem() != other.getItem() ? false
						: (a.getItemDamage() != other.getItemDamage() ? false
								: (a.getTagCompound() == null && other.getTagCompound() != null ? false
										: a.getTagCompound() == null
												|| a.getTagCompound().equals(other.getTagCompound()))));
	}

	/**
	 * Turn one item from the furnace source stack into the appropriate smelted
	 * item in the furnace result stack
	 */
	public void smeltItem() {
		if (this.canSmelt()) {
			ItemStack[] stacks = ForgeLib.forgeItem(inventory);
			if (inventory[12] == null) {
			} else
				stacks[12].stackSize += inventory[12].stackSize;
			inventory = stacks;
			for (int i = 0; i < 9; i++)
				if (inventory[i] != null) {
					inventory[i].stackSize--;
					if (inventory[i].stackSize == 0)
						inventory[i] = null;
				}
		}
	}

	public TileEntityForge() {
		this.inventory = new ItemStack[this.getSizeInventory()];
	}

	@Override
	public String getName() {
		return this.hasCustomName() ? this.customName : "container.tile_entity_forge";
	}

	@Override
	public boolean hasCustomName() {
		return this.customName != null && !this.customName.equals("");
	}

	@Override
	public IChatComponent getDisplayName() {
		return this.hasCustomName() ? new ChatComponentText(this.getName())
				: new ChatComponentTranslation(this.getName());
	}

	@Override
	public int getSizeInventory() {
		return 14;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		if (index < 0 || index >= this.getSizeInventory())
			return null;
		return this.inventory[index];
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		if (this.getStackInSlot(index) != null) {
			ItemStack itemstack;

			if (this.getStackInSlot(index).stackSize <= count) {
				itemstack = this.getStackInSlot(index);
				this.setInventorySlotContents(index, null);
				this.markDirty();
				return itemstack;
			} else {
				itemstack = this.getStackInSlot(index).splitStack(count);

				if (this.getStackInSlot(index).stackSize <= 0) {
					this.setInventorySlotContents(index, null);
				} else {
					// Just to show that changes happened
					this.setInventorySlotContents(index, this.getStackInSlot(index));
				}

				this.markDirty();
				return itemstack;
			}
		} else {
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int index) {
		if (this.inventory[index] != null) {
			ItemStack itemstack = this.inventory[index];
			this.inventory[index] = null;
			return itemstack;
		} else {
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		boolean flag = stack != null && stack.isItemEqual(inventory[index])
				&& ItemStack.areItemStackTagsEqual(stack, inventory[index]);
		if (index < 0 || index >= this.getSizeInventory())
			return;

		if (stack != null && stack.stackSize > this.getInventoryStackLimit())
			stack.stackSize = this.getInventoryStackLimit();

		if (stack != null && stack.stackSize == 0)
			stack = null;

		this.inventory[index] = stack;

		if (index < 12 && !flag) {
			// this.cookTime = 0;
		}
		this.markDirty();

	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return this.worldObj.getTileEntity(this.getPos()) == this
				&& player.getDistanceSq(this.pos.add(0.5, 0.5, 0.5)) <= 64;
	}

	@Override
	public void openInventory(EntityPlayer player) {
	}

	@Override
	public void closeInventory(EntityPlayer player) {
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return true;
	}

	public int getField(int id) {
		switch (id) {
		case 2:
			return this.cookTime;
		case 3:
			return this.totalCookTime;
		default:
			return 0;
		}
	}

	@Override
	public void setField(int id, int value) {
		switch (id) {
		case 2:
			this.cookTime = value;
			break;
		case 3:
			this.totalCookTime = value;
		}
	}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {
		for (int i = 0; i < this.getSizeInventory(); i++)
			this.setInventorySlotContents(i, null);
	}

	public String getCustomName() {
		return this.customName;
	}

	public void setCustomName(String customName) {
		this.customName = customName;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);

		this.cookTime = nbt.getShort("CookTime");
		this.totalCookTime = nbt.getShort("CookTimeTotal");
		NBTTagList list = nbt.getTagList("Items", 10);
		for (int i = 0; i < list.tagCount(); ++i) {
			NBTTagCompound stackTag = list.getCompoundTagAt(i);
			int slot = stackTag.getByte("Slot") & 255;
			this.setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(stackTag));
		}

		if (nbt.hasKey("CustomName", 8)) {
			this.setCustomName(nbt.getString("CustomName"));
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);

		nbt.setShort("CookTime", (short) this.cookTime);
		nbt.setShort("CookTimeTotal", (short) this.totalCookTime);
		NBTTagList list = new NBTTagList();
		for (int i = 0; i < this.getSizeInventory(); ++i) {
			if (this.getStackInSlot(i) != null) {
				NBTTagCompound stackTag = new NBTTagCompound();
				stackTag.setByte("Slot", (byte) i);
				this.getStackInSlot(i).writeToNBT(stackTag);
				list.appendTag(stackTag);
			}
		}
		nbt.setTag("Items", list);

		if (this.hasCustomName()) {
			nbt.setString("CustomName", this.getCustomName());
		}
	}
}
