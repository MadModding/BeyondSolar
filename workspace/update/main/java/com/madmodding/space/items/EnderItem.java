package com.madmodding.space.items;

import com.madmodding.space.Main;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.world.World;

public class EnderItem extends Item {

	public EnderItem(String unlocalizedName) {
		super();
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(Main.aliensTabTech);
		this.setMaxStackSize(1);
	}

	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		InventoryEnderChest iec = player.getInventoryEnderChest();
		iec.setChestTileEntity(new TileEntityEnderChest() {
			public boolean func_145971_a(EntityPlayer p_145971_1_) {
				return true;
			}

			public void func_145970_b() {
			}

			public void func_145969_a() {
			}
		});
		player.displayGUIChest(iec);
		return stack;
	}
}
