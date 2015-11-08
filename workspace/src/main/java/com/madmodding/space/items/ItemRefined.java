package com.madmodding.space.items;

import java.util.List;

import com.madmodding.space.ElementLib;

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

	public static final int[] types = new int[] { 3, 3, 3, 2, 2, 2, 3, 3, 3, 3, 3, 2, 1, 2, 3, 2, 3, 3, 3, 2, 2, 1, 3,
			1, 1, 1, 1, 1, 1, 2, 2, 3, 3, 3, 3, 3, 3, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 2, 2, 2, 2,
			2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 3, 2, 1, 2, 2, 3, 3, 3, 2, 3, 3, 3, 3, 3, 3, 3,
			3, 3, 3, 3, 3, 1, 1, 1 };
	public static final String[] typenames = new String[] { "blankingot", "blankdust", "blankpot" };

	@SideOnly(Side.CLIENT)
	public void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {
		for (int i = 0; i < ItemFragment.names.length; i++) {
			for (int t = 0; t < 4; t++) {
				ItemStack stack = new ItemStack(itemIn, 1, i + t * ItemFragment.names.length);
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
		if (!stack.hasTagCompound())
			onFirstTick(stack);
		double clr = stack.getTagCompound().getInteger("color");
		if (clr == -1)
			clr = ElementLib.getColor(Minecraft.getSystemTime());
		if (stack.getTagCompound().getBoolean("anti")) {
			double r = ((int) clr / 65536d);
			clr -= r * 65536;
			double g = ((int) clr / 256d);
			clr -= g * 256;
			double b = ((int) clr);
			clr -= b;
			r = 255 - r;
			g = 255 - g;
			b = 255 - b;
			clr += r * 65536;
			clr += g * 256;
			clr += b;
		}
		if (renderPass == 1) {
			return (int) clr;
		}
		if (stack.getTagCompound().getBoolean("neg"))
			return 0x111111;
		return 0xFFFFFF;
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
		if (d < (ItemFragment.names.length * 4)) {
			int i = (int) ((stack.getItemDamage()) / ItemFragment.names.length) + 1;
			stack.setItemDamage(stack.getItemDamage() - (i - 1) * ItemFragment.names.length);
			boolean neg = i % 2 == 0;
			boolean anti = i > 2;
			stack.getTagCompound().setString("name", ItemFragment.names[stack.getItemDamage()]);
			stack.getTagCompound().setDouble("amass", ItemFragment.masses[stack.getItemDamage()]);
			stack.getTagCompound().setInteger("color", ItemFragment.colors[stack.getItemDamage()]);
			stack.getTagCompound().setBoolean("neg", neg);
			stack.getTagCompound().setBoolean("anti", anti);
			stack.getTagCompound().setBoolean("ore", false);
		} else {
			d -= ItemFragment.names.length * 4;
			stack.setItemDamage(d);
			stack.getTagCompound().setString("name", ItemFragment.orenames[d]);
			stack.getTagCompound().setInteger("id", d);
			stack.getTagCompound().setBoolean("ore", true);
		}
	}

}
