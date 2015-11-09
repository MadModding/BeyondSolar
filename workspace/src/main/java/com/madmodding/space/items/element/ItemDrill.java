package com.madmodding.space.items.element;

import com.madmodding.space.entity.item.EntityDrillLaser;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;

public class ItemDrill extends Item {

	public ItemDrill(String unlocalizedName) {
		super();
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(CreativeTabs.tabTools);
		this.setMaxDamage(50);
		this.setMaxStackSize(1);
	}

	public ItemStack onItemRightClick(ItemStack stack, World p_77659_2_, EntityPlayer player) {
		if (stack.getItemDamage() == 0)
			player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
		return stack;
	}

	public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
		if (count < 0 || count > getMaxItemUseDuration(stack))
			player.stopUsingItem();
		if (count % 3 == 0) {
			EntityDrillLaser entityarrow = new EntityDrillLaser(player.worldObj, player, 4);
			player.worldObj.spawnEntityInWorld(entityarrow);
		}
	}

	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityPlayer playerIn, int timeLeft) {
		stack.setItemDamage(getMaxItemUseDuration(stack) - timeLeft);
	}

	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		return stack;
	}

	public boolean showDurabilityBar(ItemStack stack) {
		return true;
	}

	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (stack.isItemDamaged())
			stack.setItemDamage(stack.getItemDamage() - 1);
	}

	public double getDurabilityForDisplay(ItemStack stack) {
		if (Minecraft.getMinecraft().thePlayer.isUsingItem()
				&& Minecraft.getMinecraft().thePlayer.getHeldItem() == stack)
			return (double) Minecraft.getMinecraft().thePlayer.getItemInUseDuration() / getMaxItemUseDuration(stack);
		else
			return super.getDurabilityForDisplay(stack);
	}

	public int getMaxItemUseDuration(ItemStack stack) {
		return 50;
	}

	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BOW;
	}

}