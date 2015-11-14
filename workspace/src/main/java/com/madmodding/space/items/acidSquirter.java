package com.madmodding.space.items;

import com.madmodding.space.Main;
import com.madmodding.space.entity.item.EntityAcidAttack;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class acidSquirter extends SpecialItem
{
	public acidSquirter(String name) 
	{
		super(name);
		this.setUnlocalizedName(name);
		this.setCreativeTab(Main.aliensTabTech);
		this.setMaxStackSize(1);
	}
	@Override
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) 
	{
    	worldIn.spawnEntityInWorld(new EntityAcidAttack(worldIn, playerIn));
	     return itemStackIn;
	}
}
