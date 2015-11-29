package com.madmodding.space.items.inv;

import org.lwjgl.input.Keyboard;

import com.madmodding.space.Main;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBag extends Item {
	public ItemBag(String unlocalizedName) {
		super();
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(Main.aliensTabTech);
		this.setMaxStackSize(1);
	}

	public boolean onDroppedByPlayer(ItemStack stack, EntityPlayer player) {
		if (!stack.hasTagCompound() || !stack.getTagCompound().getBoolean("open"))
			return true;
		return false;
	}

	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if (!stack.hasTagCompound()) {
			stack.setTagCompound(new NBTTagCompound());
			InventoryItem iec = new InventoryItem();
			player.displayGUIChest(iec);
		} else {
			InventoryItem iec = new InventoryItem();
			iec.loadInventoryFromNBT((NBTTagList) stack.getTagCompound().getTag("inv"));
			player.displayGUIChest(iec);
		}
		stack.getTagCompound().setBoolean("open", true);
		return stack;
	}
}