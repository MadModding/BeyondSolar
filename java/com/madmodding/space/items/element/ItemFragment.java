package com.madmodding.space.items.element;

import java.util.List;

import com.madmodding.space.blocks.tile.forge.IForgeable;
import com.madmodding.space.items.BasicItem;
import com.madmodding.space.items.IFirstTick;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemFragment extends BasicItem implements IFirstTick, IForgeable {

	@SideOnly(Side.CLIENT)
	public void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {
		ElementLib.fragmentInv(itemIn, subItems);
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
				if (stack.getTagCompound().getShort("Level") == 0)
					info.add("Condition: Poor");
				if (stack.getTagCompound().getShort("Level") == 1)
					info.add("Condition: Fair");
				if (stack.getTagCompound().getShort("Level") == 2)
					info.add("Condition: Rich");
				if (stack.getTagCompound().getShort("Level") == 3)
					info.add("Condition: Very Rich");
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
		ElementLib.setupFragment(stack);
	}
	@Override
	public int[][] getElementRatio(ItemStack stack) {
		int d = stack.getItemDamage();
		if (d < (ElementLib.Elements.length * 4) && !stack.getTagCompound().getBoolean("Complex")) {
			int give = 0;
			int lvl = stack.getTagCompound().getShort("Level");
			if(lvl == 0) give = 36;
			if(lvl == 1) give = 72;
			if(lvl == 2) give = 144;
			if(lvl == 3) give = 288;
			
			return new int[][] { { give }, { d } };
		} else {
			NBTTagList list = stack.getTagCompound().getTagList("Elements", 10);
			int[][] elem = new int[2][list.tagCount()];
			for (int i = 0; i < elem[0].length; i++) {
				elem[0][i] = ((NBTTagCompound) list.get(i)).getInteger("Amt");
				elem[1][i] = ((NBTTagCompound) list.get(i)).getInteger("Ele");
			}
			return elem;
		}
	}

	@Override
	public double getMB(ItemStack stack) {
		return 0;
	}
}
