package com.madmodding.space.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WarpStar extends Item {

	public WarpStar(String unlocalizedName) {
		super();
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(CreativeTabs.tabMaterials);
	}

	public ItemStack onItemRightClick(ItemStack stack, World worldIn, EntityPlayer entityIn) {
		if (entityIn.ridingEntity == null && entityIn.riddenByEntity == null && !worldIn.isRemote) {
			if (entityIn.dimension != 71)
				entityIn.travelToDimension(71);
			entityIn.timeUntilPortal = entityIn.getPortalCooldown();
		}

		entityIn.inventory.consumeInventoryItem(ModItems.warp);
		return stack;
	}
}
