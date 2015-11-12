package com.madmodding.space.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BasicItem extends Item {

	public BasicItem(String unlocalizedName) {
		super();
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(CreativeTabs.tabMaterials);
	}
	public boolean onEntityItemUpdate(EntityItem entityItem)
    {
		if(entityItem.dimension == 71){
		
		}
        return false;
    }
	public ItemStack onItemRightClick(ItemStack stack, World p_77659_2_, EntityPlayer player) {
		return stack;
	}
}
