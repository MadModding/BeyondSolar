package com.madmodding.space.items.element;

import java.util.List;

import com.madmodding.space.items.BasicItem;
import com.madmodding.space.items.IFirstTick;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemFragment extends BasicItem implements IFirstTick {

	@SideOnly(Side.CLIENT)
	public void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {
		for (int i = 0; i < ElementLib.NonResElements.length; i++) {
			for (int t = 0; t < 4; t++) {
				ItemStack stack = new ItemStack(itemIn, 1, i + (t * ElementLib.Elements.length));
				this.onFirstTick(stack);
				subItems.add(stack);
			}
		}
		if (ElementLib.isSpecial(Minecraft.getMinecraft().thePlayer.getName()) == 1) {
			subItems.add(new ItemStack(itemIn, 1, 102));
			subItems.add(new ItemStack(itemIn, 1, 103));
		}
		if (ElementLib.isSpecial(Minecraft.getMinecraft().thePlayer.getName()) == 2) {
			subItems.add(new ItemStack(itemIn, 1, 102));
			subItems.add(new ItemStack(itemIn, 1, 104));
		}
		if (ElementLib.isSpecial(Minecraft.getMinecraft().thePlayer.getName()) == 3) {
			subItems.add(new ItemStack(itemIn, 1, 102));
			subItems.add(new ItemStack(itemIn, 1, 105));
		}
		if (ElementLib.isSpecial(Minecraft.getMinecraft().thePlayer.getName()) == 4) {
			subItems.add(new ItemStack(itemIn, 1, 102));
			subItems.add(new ItemStack(itemIn, 1, 106));
		}
		if (ElementLib.isSpecial(Minecraft.getMinecraft().thePlayer.getName()) == 6) {
			subItems.add(new ItemStack(itemIn, 1, 102));
			subItems.add(new ItemStack(itemIn, 1, 107));
		}
		if (ElementLib.isSpecial(Minecraft.getMinecraft().thePlayer.getName()) == 5) {
			subItems.add(new ItemStack(itemIn, 1, 102));
		}
	}

	public ItemFragment(String unlocalizedName) {
		super(unlocalizedName);
		this.setHasSubtypes(true);
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(CreativeTabs.tabMaterials);
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
			if (!stack.getTagCompound().getBoolean("ore")) {
				info.add("Element Name: " + stack.getTagCompound().getString("Name"));
				if (stack.getItemDamage() < 100)
					info.add("Atomic Number: " + (stack.getItemDamage() + 1));
				else
					info.add("Atomic Number: \u00A7kgg");
				String mass = Double.toString(stack.getTagCompound().getDouble("amass"));
				if (stack.getTagCompound().getDouble("amass") == -1)
					mass = "i";
				if (stack.getTagCompound().getBoolean("neg"))
					info.add("Atomic Mass: -" + mass);
				else
					info.add("Atomic Mass: " + mass);
			}
		}
	}

	public String getItemStackDisplayName(ItemStack stack) {
		if (!stack.hasTagCompound())
			onFirstTick(stack);
		if (!stack.hasTagCompound())
			return ("" + StatCollector.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
		String name = stack.getTagCompound().getString("Name");
		name += " Fragment";
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
