package com.madmodding.space.items.element;

import java.util.List;

import com.madmodding.space.items.BasicItem;
import com.madmodding.space.items.IFirstTick;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemRefined extends BasicItem implements IFirstTick {
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return ElementLib.getRarity(stack);
	}

	@SideOnly(Side.CLIENT)
	public void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {
		for (int i = 0; i < ElementLib.NonResElements.length; i++) {
			for (int t = 0; t < 4; t++) {
				ItemStack stack = new ItemStack(itemIn, 1, i + t * ElementLib.Elements.length);
				this.onFirstTick(stack);
				subItems.add(stack);
			}
		}
		if (ElementLib.isSpecial(Minecraft.getMinecraft().thePlayer.getName()) == 1)
			subItems.add(new ItemStack(itemIn, 1, 103));
		if (ElementLib.isSpecial(Minecraft.getMinecraft().thePlayer.getName()) == 2)
			subItems.add(new ItemStack(itemIn, 1, 104));
		if (ElementLib.isSpecial(Minecraft.getMinecraft().thePlayer.getName()) == 3)
			subItems.add(new ItemStack(itemIn, 1, 105));
		if (ElementLib.isSpecial(Minecraft.getMinecraft().thePlayer.getName()) == 4)
			subItems.add(new ItemStack(itemIn, 1, 106));
		if (ElementLib.isSpecial(Minecraft.getMinecraft().thePlayer.getName()) == 5)
			subItems.add(new ItemStack(itemIn, 1, 102));
	}

	public ItemRefined(String unlocalizedName) {
		super(unlocalizedName);
		this.setHasSubtypes(true);
		this.setUnlocalizedName(unlocalizedName);
	}

	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int renderPass) {
		return ElementLib.getColorFromItemStack(stack, renderPass, 0);
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List info, boolean advanced) {
		if (!stack.hasTagCompound())
			onFirstTick(stack);
		if (stack.hasTagCompound()) {
			info.add("Name: " + stack.getTagCompound().getString("Name"));
		}
	}

	public String getItemStackDisplayName(ItemStack stack) {
		if (!stack.hasTagCompound())
			onFirstTick(stack);
		if (!stack.hasTagCompound())
			return ("" + StatCollector.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
		String name = stack.getTagCompound().getString("Name");
		name += "";
		return name;
	}

	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (!stack.hasTagCompound())
			onFirstTick(stack);
	}

	@Override
	public void onFirstTick(ItemStack stack) {
		int d = stack.getItemDamage();
		stack.setTagCompound(new NBTTagCompound());
		if (d < (ElementLib.Elements.length * 4)) {
			int i = (int) ((stack.getItemDamage()) / ElementLib.Elements.length) + 1;
			stack.setItemDamage(stack.getItemDamage() - (i - 1) * ElementLib.Elements.length);
			boolean neg = i % 2 == 0;
			boolean anti = i > 2;
			stack.getTagCompound().setDouble("amass", ElementLib.Elements[stack.getItemDamage()].getMass());
			stack.getTagCompound().setInteger("color", ElementLib.Elements[stack.getItemDamage()].getColor());
			stack.getTagCompound().setBoolean("neg", neg);
			stack.getTagCompound().setBoolean("anti", anti);
			{
				stack.getTagCompound().setString("Name", ElementLib.getName(stack));
			}
		}
	}

}
