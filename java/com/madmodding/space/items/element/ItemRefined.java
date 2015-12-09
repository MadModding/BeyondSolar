package com.madmodding.space.items.element;

import java.util.List;

import com.madmodding.space.blocks.tile.forge.IForgeable;
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
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemRefined extends BasicItem implements IFirstTick, IForgeable {
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
		info.add("Name: " + stack.getTagCompound().getString("Name"));
		if (stack.getTagCompound().getBoolean("Complex")) {
			int[][] em = getElementRatio(stack);
			for (int i = 0; i < em[0].length; i++) {
				info.add(("" + StatCollector.translateToLocal(
						"element." + ElementLib.Elements[em[1][i] % ElementLib.Elements.length].getName() + ".name"))
								.trim()
						+ ": " + em[0][i]);
			}
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
		ElementLib.setupRefined(stack);
	}

	public static void setBlend(ItemStack stack, int[][] blend) {
		stack.setTagCompound(new NBTTagCompound());
		{
			NBTTagList list = new NBTTagList();
			for (int i = 0; i < blend[0].length; i++) {
				NBTTagCompound tag = new NBTTagCompound();
				tag.setInteger("Amt", blend[0][i]);
				tag.setInteger("Ele", blend[1][i]);
				list.appendTag(tag);
			}
			stack.getTagCompound().setTag("Elements", list);
			stack.getTagCompound().setBoolean("Complex", true);
			}
		int[][] el = ((IForgeable) stack.getItem()).getElementRatio(stack);
		for (int i = 0; i < el[0].length; i++) {
		}
	}

	@Override
	public int[][] getElementRatio(ItemStack stack) {
		int d = stack.getItemDamage();
		if (d < (ElementLib.Elements.length * 4) && !stack.getTagCompound().getBoolean("Complex")) {
			return new int[][] { { 72 }, { d } };
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
