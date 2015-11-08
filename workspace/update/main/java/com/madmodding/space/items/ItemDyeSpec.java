package com.madmodding.space.items;

import java.util.List;

import com.madmodding.space.Main;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemDyeSpec extends Item {
	public static final int[] dyeColors = new int[] { 1973019, 11743532, 3887386, 5320730, 2437522, 8073150, 2651799,
			11250603, 4408131, 14188952, 4312372, 14602026, 6719955, 12801229, 15435844, 15790320 };
	public static final int[] dyeColorsUltra = new int[] { 1973019, 11743532, 3887386, 5320730, 2437522, 8073150,
			2651799, 11250603, 4408131, 14188952, 4312372, 14602026, 6719955, 12801229, 15435844, 15790320 };
	public static final String[] dyeColorNouns = new String[] { "Black", "Red", "Green", "Brown", "Blue", "Purple",
			"Cyan", "Light-Gray", "Gray", "Pink", "Lime", "Yellow", "Light-Blue", "Magenta", "Orange", "White" };
	public static final String[] dyeColorAdjs = new String[] { "Dark", "Red", "Green", "Brown", "Blue", "Purple",
			"Blue-Green", "Light-Gray", "Gray", "Pink", "Light-Green", "Yellow", "Light-Blue", "Purple-Red", "Orange",
			"Light" };
	private static final String __OBFID = "CL_00000022";

	public String getItemStackDisplayName(ItemStack stack) {
		int x = stack.getItemDamage() - 16;
		int i1 = x / 16;
		int i2 = x - i1 * 16;
		if (stack.getItemDamage() < 16 || stack.getItemDamage() == 255) {
			return super.getItemStackDisplayName(stack);
		} else if (i1 == i2) {
			switch (i1) {
			case 0:
				return "Ultra-Dark Dye";
			case 1:
				return "Fire-Red Dye";
			case 2:
				return "Leaf-Green Dye";
			case 3:
				return "Mud-Brown Dye";
			case 4:
				return "Ocean-Blue Dye";
			case 5:
				return "Amethyst Dye";
			case 6:
				return "Blue-Green Dye";
			case 7:
				return "Simple Dye I";
			case 8:
				return "Simple Dye II";
			case 9:
				return "Aureylian's Dye";
			case 10:
				return "Emerald Dye";
			case 11:
				return "Lightning-Yellow Dye";
			case 12:
				return "Sky-Blue Dye";
			case 13:
				return "Purple-Red Dye";
			case 14:
				return "Garnet Dye";
			case 15:
				return "Compound TiO2";
			default:
				return "Ultra Dye";

			}
		} else {
			return "Customized " + dyeColorAdjs[i1] + "-" + dyeColorNouns[i2] + " Dye";
		}
	}

	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int renderPass) {
		if (stack.getItemDamage() < 16)
			return dyeColors[stack.getItemDamage()];
		else if (stack.getItemDamage() < 272) {
			int i = 0;
			int x = stack.getItemDamage() - 16;
			int i1 = x / 16;
			int i2 = x - i1 * 16;
			int r1 = dyeColors[i1] / 65536;
			int g1 = (dyeColors[i1] - r1 * 65536) / 256;
			int b1 = (dyeColors[i1] - g1 * 256 - r1 * 65536);
			int r2 = dyeColors[i2] / 65536;
			int g2 = (dyeColors[i2] - r2 * 65536) / 256;
			int b2 = (dyeColors[i2] - g2 * 256 - r2 * 65536);
			int r = (r1 + r2) / 2;
			int g = (g1 + g2) / 2;
			int b = (b1 + b2) / 2;
			i = r * 65536 + g * 256 + b;
			return i;
		} else
			return 0;
	}

	public ItemDyeSpec() {
		this.setHasSubtypes(true);
		this.setUnlocalizedName("spec_dye");
		this.setMaxDamage(0);
		this.setCreativeTab(Main.aliensTabRef);
	}

	/**
	 * Returns the unlocalized name of this item. This version accepts an
	 * ItemStack so different stacks can have different names based on their
	 * damage or NBT.
	 */
	public String getUnlocalizedName(ItemStack stack) {
		int i = stack.getMetadata();
		if (i < 16)
			return super.getUnlocalizedName() + "_" + EnumDyeColor.byDyeDamage(i).getUnlocalizedName();
		else if (i >= 271)
			return super.getUnlocalizedName() + "_ultra";
		else
			return super.getUnlocalizedName();
	}

	/**
	 * Called when a Block is right-clicked with this Item
	 * 
	 * @param pos
	 *            The block being right-clicked
	 * @param side
	 *            The side being right-clicked
	 */
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side,
			float hitX, float hitY, float hitZ) {
		if (!playerIn.canPlayerEdit(pos.offset(side), side, stack)) {
			return false;
		} else {

			return false;
		}
	}

	/**
	 * returns a list of items with the same ID, but different meta (eg: dye
	 * returns 16 items)
	 * 
	 * @param subItems
	 *            The List of sub-items. This is a List of ItemStacks.
	 */
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {
		for (int i = 0; i < 272; ++i) {
			subItems.add(new ItemStack(itemIn, 1, i));
		}
	}
}