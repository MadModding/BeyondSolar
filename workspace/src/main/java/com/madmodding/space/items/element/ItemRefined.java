package com.madmodding.space.items.element;

import java.util.List;

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

public class ItemRefined extends Item implements IFirstTick {

	
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {
		for (int i = 0; i < ElementLib.names.length; i++) {
			for (int t = 0; t < 4; t++) {
				ItemStack stack = new ItemStack(itemIn, 1, i + t * ElementLib.names.length);
				this.onFirstTick(stack);
				subItems.add(stack);
			}
		}
	}

	public ItemRefined(String unlocalizedName) {
		super();
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
			info.add("Name: " + stack.getTagCompound().getString("name"));
		}
	}

	public String getItemStackDisplayName(ItemStack stack) {
		if (!stack.hasTagCompound())
			onFirstTick(stack);
		if (!stack.hasTagCompound())
			return ("" + StatCollector.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
		String name = "";
		if (stack.getTagCompound().getBoolean("neg"))
			name += "Negative ";
		if (stack.getTagCompound().getBoolean("anti"))
			name += "Anti-";
		name += stack.getTagCompound().getString("name");
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
		if (d < (ElementLib.names.length * 4)) {
			int i = (int) ((stack.getItemDamage()) / ElementLib.names.length) + 1;
			stack.setItemDamage(stack.getItemDamage() - (i - 1) * ElementLib.names.length);
			boolean neg = i % 2 == 0;
			boolean anti = i > 2;
			stack.getTagCompound().setString("name", ElementLib.names[stack.getItemDamage()]);
			stack.getTagCompound().setDouble("amass", ElementLib.masses[stack.getItemDamage()]);
			stack.getTagCompound().setInteger("color", ElementLib.colors[stack.getItemDamage()]);
			stack.getTagCompound().setBoolean("neg", neg);
			stack.getTagCompound().setBoolean("anti", anti);
			stack.getTagCompound().setBoolean("ore", false);
		} else {
			d -= ElementLib.names.length * 4;
			stack.setItemDamage(d);
			stack.getTagCompound().setString("name", ElementLib.orenames[d]);
			stack.getTagCompound().setInteger("id", d);
			stack.getTagCompound().setBoolean("ore", true);
		}
	}

}
