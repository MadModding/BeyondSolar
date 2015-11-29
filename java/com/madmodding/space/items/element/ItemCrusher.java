package com.madmodding.space.items.element;

import com.madmodding.space.items.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemCrusher extends Item {

	public ItemCrusher(String unlocalizedName) {
		super();
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(CreativeTabs.tabMaterials);
	}

	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if (player.inventory.hasItem(ModItems.dust)) {
			boolean crushing = true;
			int l = player.getRNG().nextInt(32) + 33;
			for (int i = 0; i < l; i++) {
				if (!player.inventory.consumeInventoryItem(ModItems.dust))
					crushing = false;
			}
			if (crushing) {
				player.inventory
						.addItemStackToInventory(new ItemStack(ElementLib.Fragment, player.getRNG().nextInt(4)+1, player.getRNG().nextInt(61) + 19));
			}
		}
		return stack;
	}
}
