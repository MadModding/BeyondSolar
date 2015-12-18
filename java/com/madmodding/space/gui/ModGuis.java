package com.madmodding.space.gui;

import com.madmodding.space.blocks.tile.forge.ContainerForge;
import com.madmodding.space.blocks.tile.forge.GuiForge;
import com.madmodding.space.blocks.tile.forge.TileEntityForge;
import com.madmodding.space.items.inv.ContainerItemInv;
import com.madmodding.space.items.inv.GuiItemInv;
import com.madmodding.space.items.inv.InventoryItem;
import com.madmodding.space.items.inv.quantum.ContainerItemArmor;
import com.madmodding.space.items.inv.quantum.GuiItemArmor;
import com.madmodding.space.items.inv.quantum.InventoryItemArmor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class ModGuis implements IGuiHandler {

	public static final int MOD_FORGE_GUI_ID = 0;
	public static final int MOD_ITEM_CHEST_GUI_ID = 1;
	public static final int MOD_QUANT_ARMOR_GUI_ID = 2;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == MOD_FORGE_GUI_ID)
			return new ContainerForge(player.inventory, (TileEntityForge) world.getTileEntity(new BlockPos(x, y, z)));
		if (ID == MOD_ITEM_CHEST_GUI_ID) {
			InventoryItem iec = new InventoryItem();
			ItemStack stack = player.getHeldItem();
			if (!stack.hasTagCompound()) {
				stack.setTagCompound(new NBTTagCompound());
			} else {
				iec.loadInventoryFromNBT((NBTTagList) stack.getTagCompound().getTag("inv"));
			}
			stack.getTagCompound().setBoolean("open", true);
			return new ContainerItemInv(player.inventory, iec);
		}
		if (ID == MOD_QUANT_ARMOR_GUI_ID) {
			InventoryItemArmor iec = new InventoryItemArmor();
			ItemStack stack = player.getHeldItem();
			if (!stack.hasTagCompound()) {
				stack.setTagCompound(new NBTTagCompound());
			} else {
				iec.loadInventoryFromNBT((NBTTagList) stack.getTagCompound().getTag("inv"));
			}
			stack.getTagCompound().setBoolean("open", true);
			return new ContainerItemArmor(((ItemArmor) player.getHeldItem().getItem()).armorType, player.inventory,
					iec);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == MOD_FORGE_GUI_ID)
			return new GuiForge(player.inventory, (TileEntityForge) world.getTileEntity(new BlockPos(x, y, z)));
		if (ID == MOD_ITEM_CHEST_GUI_ID) {
			InventoryItem iec = new InventoryItem();
			ItemStack stack = player.getHeldItem();
			if (!stack.hasTagCompound()) {
				stack.setTagCompound(new NBTTagCompound());
				player.displayGUIChest(iec);
			} else {
				iec.loadInventoryFromNBT((NBTTagList) stack.getTagCompound().getTag("inv"));
				player.displayGUIChest(iec);
			}
			stack.getTagCompound().setBoolean("open", true);
			return new GuiItemInv(player.inventory, iec);
		}
		if (ID == MOD_QUANT_ARMOR_GUI_ID) {
			InventoryItemArmor iec = new InventoryItemArmor();
			ItemStack stack = player.getHeldItem();
			if (!stack.hasTagCompound()) {
				stack.setTagCompound(new NBTTagCompound());
			} else {
				iec.loadInventoryFromNBT((NBTTagList) stack.getTagCompound().getTag("inv"));
			}
			stack.getTagCompound().setBoolean("open", true);
			return new GuiItemArmor(player.inventory, iec, ((ItemArmor) player.getHeldItem().getItem()).armorType);
		}
		return null;
	}
}
