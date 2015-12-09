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

public class ItemCast extends BasicItem implements IFirstTick, IForgeable {
	public static final int slots = 16;
	public static final int areCast = 9;

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return ElementLib.getRarity(stack);
	}

	private void addSpec(List subItems, Item itemIn, int i) {
		for (int t = 0; t < ElementLib.castnames.length; t++) {
			ItemStack stack2 = new ItemStack(itemIn, 1, i + t * 4 * ElementLib.Elements.length);
			this.onFirstTick(stack2);
			subItems.add(stack2);
		}
	}

	@SideOnly(Side.CLIENT)
	public void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {
		for (int i = 0; i < ElementLib.NonResElements.length; i++) {
			if (ElementLib.Elements[i].getType() == 1)
				for (int t = 0; t < 4 * ElementLib.castnames.length; t++) {
					ItemStack stack = new ItemStack(itemIn, 1, i + t * ElementLib.Elements.length);
					this.onFirstTick(stack);
					subItems.add(stack);

				}
		}
		if (ElementLib.isSpecial(Minecraft.getMinecraft().thePlayer.getName()) == 1) {
			addSpec(subItems, itemIn, 102);
			addSpec(subItems, itemIn, 103);
		}
		if (ElementLib.isSpecial(Minecraft.getMinecraft().thePlayer.getName()) == 2) {
			addSpec(subItems, itemIn, 102);
			addSpec(subItems, itemIn, 104);
		}
		if (ElementLib.isSpecial(Minecraft.getMinecraft().thePlayer.getName()) == 3) {
			addSpec(subItems, itemIn, 102);
			addSpec(subItems, itemIn, 105);
		}
		if (ElementLib.isSpecial(Minecraft.getMinecraft().thePlayer.getName()) == 4) {
			addSpec(subItems, itemIn, 102);
			addSpec(subItems, itemIn, 106);
		}
		if (ElementLib.isSpecial(Minecraft.getMinecraft().thePlayer.getName()) == 6) {
			addSpec(subItems, itemIn, 102);
			addSpec(subItems, itemIn, 107);
		}
		if (ElementLib.isSpecial(Minecraft.getMinecraft().thePlayer.getName()) == 5) {
			addSpec(subItems, itemIn, 102);
		}
	}

	public ItemCast(String unlocalizedName) {
		super(unlocalizedName);
		this.setHasSubtypes(true);
		this.setUnlocalizedName(unlocalizedName);
	}

	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int renderPass) {
		return ElementLib.getColorFromItemStack(stack, renderPass, 1);
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List info, boolean advanced) {
		if (!stack.hasTagCompound())
			onFirstTick(stack);
		int type = stack.getTagCompound().getInteger("type");
		switch (type) {
		case 0:
			info.add("Type: Rod");
			break;
		case 1:
			info.add("Type: Pickaxe Head");
			break;
		case 2:
			info.add("Type: Spade Head");
			break;
		case 3:
			info.add("Type: Axe Head");
			break;
		case 4:
			info.add("Type: Helmet");
			break;
		case 5:
			info.add("Type: Chestplate");
			break;
		case 6:
			info.add("Type: Leggings");
			break;
		case 7:
			info.add("Type: Boots");
			break;
		case 8:
			info.add("Type: Sword");
			break;
		}
		if (stack.getTagCompound().getBoolean("Complex")) {
			int[][] em = getElementRatio(stack);
			for (int i = 0; i < em[0].length; i++) {
				info.add(("" + StatCollector.translateToLocal(
						"element." + ElementLib.Elements[em[1][i] % ElementLib.Elements.length].getName() + ".name"))
								.trim()
						+ ": " + em[0][i]);
			}
		} else if (type < areCast)
			info.add("Melting Point: "
					+ ElementLib.Elements[stack.getTagCompound().getInteger("elem") % ElementLib.Elements.length]
							.getMeltingPoint());

	}

	public String getItemStackDisplayName(ItemStack stack) {
		if (!stack.hasTagCompound())
			onFirstTick(stack);
		if (!stack.hasTagCompound())
			return ("" + StatCollector.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
		String name = stack.getTagCompound().getString("Name");
		switch (stack.getTagCompound().getInteger("type")) {
		case 0:
			name += (" Rod Cast");
			break;
		case 1:
			name += (" Pickaxe Head Cast");
			break;
		case 2:
			name += (" Spade Head Cast");
			break;
		case 3:
			name += (" Axe Head Cast");
			break;
		case 4:
			name += (" Helmet Cast");
			break;
		case 5:
			name += (" Chestplate Cast");
			break;
		case 6:
			name += (" Leggings Cast");
			break;
		case 7:
			name += (" Boots Cast");
			break;
		case 8:
			name += (" Sword Cast");
			break;
		case 9:
			name += (" Blade and Hilt Piece");
			break;
		case 10:
			name += (" Spade Head Piece");
			break;
		case 11:
			name += (" Axe Head Piece");
			break;
		case 12:
			name += (" Pick Head Piece");
			break;
		case 13:
			name += (" Rod Piece");
			break;
		case 14:
			name += ("");
			break;
		case 15:
			name += ("");
			break;
		}
		name += "";
		return name;
	}

	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (!stack.hasTagCompound())
			onFirstTick(stack);
	}

	@Override
	public void onFirstTick(ItemStack stack) {
		ElementLib.setupCast(stack);
	}

	@Override
	public int[][] getElementRatio(ItemStack stack) {
		int d = stack.getTagCompound().getInteger("elem");
		if (d < (ElementLib.Elements.length * 8 * slots) && !stack.getTagCompound().getBoolean("Complex")) {
			return new int[][] { { 72 }, { d % ElementLib.Elements.length } };
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
