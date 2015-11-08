package com.madmodding.space.items;

import java.util.List;

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

public class ItemFragments extends Item implements IFirstTick {

	public static final String[] names = new String[] { "Hydrogen", "Helium", "Lithium", "Beryllium", "Boron", "Carbon",
			"Nitrogen", "Oxygen", "Fluorine", "Neon", "Sodium", "Magnesium", "Aluminium", "Silicon", "Phosphorus",
			"Sulfur", "Chlorine", "Argon", "Potassium", "Calcium", "Scandium", "Titanium", "Vanadium", "Chromium",
			"Manganese", "Iron", "Cobalt", "Nickel", "Copper", "Zinc", "Gallium", "Germanium", "Arsenic", "Selenium",
			"Bromine", "Krypton", "Rubidium", "Strontium", "Yttrium", "Zirconium", "Niobium", "Molybdenum",
			"Technetium", "Ruthenium", "Rhodium", "Palladium", "Silver", "Cadmium", "Indium", "Tin", "Antimony",
			"Tellurium", "Iodine", "Xenon", "Cesium", "Barium", "Lanthanum", "Cerium", "Praseodymium", "Neodymium",
			"Promethium", "Samarium", "Europium", "Gadolinium", "Terbium", "Dysprosium", "Holmium", "Erbium", "Thulium",
			"Ytterbium", "Lutetium", "Hafnium", "Tantalum", "Tungsten", "Rhenium", "Osmium", "Iridium", "Platinum",
			"Gold", "Mercury", "Thallium", "Lead", "Bismuth", "Polonium", "Astatine", "Radon", "Francium", "Radium",
			"Actinium", "Thorium", "Protactinium", "Uranium", "Neptunium", "Plutonium", "Americium", "Curium",
			"Berkelium", "Californium", "Einsteinium", "Fermium", "Dark Matter", "Exotic Matter" };
	public static final double[] masses = new double[] { 1.0079D, 4.0026D, 6.941D, 9.0122D, 10.811D, 12.0107D, 14.0067D,
			15.9994D, 18.9984D, 20.1797D, 22.9897D, 24.305D, 26.9815D, 28.0855D, 30.9738D, 32.065D, 35.453D, 39.0983D,
			39.948D, 40.078D, 44.9559D, 47.867D, 50.9415D, 51.9961D, 54.938D, 55.845D, 58.6934D, 58.9332D, 63.546D,
			65.39D, 69.723D, 72.64D, 74.9216D, 78.96D, 79.904D, 83.8D, 85.4678D, 87.62D, 88.9059D, 91.224D, 92.9064D,
			95.94D, 98D, 101.07D, 102.9055D, 106.42D, 107.8682D, 112.411D, 114.818D, 118.71D, 121.76D, 126.9045D,
			127.6D, 131.293D, 132.9055D, 137.327D, 138.9055D, 140.116D, 140.9077D, 144.24D, 145D, 150.36D, 151.964D,
			157.25D, 158.9253D, 162.5D, 164.9303D, 167.259D, 168.9342D, 173.04D, 174.967D, 178.49D, 180.9479D, 183.84D,
			186.207D, 190.23D, 192.217D, 195.078D, 196.9665D, 200.59D, 204.3833D, 207.2D, 208.9804D, 209D, 210D, 222D,
			223D, 226D, 227D, 231.0359D, 232.0381D, 237D, 238.0289D, 243D, 244D, 247D, 247D, 251D, 252D, 257D, 10000D,
			-1d };
	public static final int[] colors = new int[] { 0xDCDCFF, 0xFFDCFF, 0xA0A0A0, 0x787878, 0x9696A5, 0x282828, 0xA0A0A0,
			0xF0F0F0, 0x8C8CF0, 0xF02828, 0x969696, 0x969696, 0xAFAFAF, 0x414141, 0xA54141, 0xEBE119, 0xCDD719,
			0xB914CD, 0x9B9BCD, 0xA5A59B, 0xAFB99B, 0xFFFFFF, 0x9B9B9B, 0xC3C3C3, 0xAFAFC3, 0xAFAF9B, 0x4141E1,
			0xB4B4B4, 0xD77300, 0xB4B4B4, 0xB4B4B4, 0xA4A4A4, 0xFFA573, 0xD7D7D7, 0xD7370F, 0xD77373, 0xD75F5F,
			0xD7C39B, 0xA4A4A4, 0xD7D7D7, 0xAFAFAF, 0x878787, 0xA5A5B9, 0x7D7D7D, 0x7D7D7D, 0xA0A0A0, 0xAAAAA0,
			0xA0A0AA, 0x8C8C8C, 0xB4B4B4, 0xB4B4BE, 0x969696, 0x3C3C6E, 0xA03CD2, 0xA0A082, 0xD2D2DC, 0xA0AAA0,
			0xBE7878, 0x788C8C, 0x8C7878, 0x14FF78, 0xAA9696, 0xA9A9A9, 0xFFFFFF, 0xFFFFFF, 0xFFFFFF, 0xFFFFFF,
			0xFFFFFF, 0xFFFFFF, 0xFFFFFF, 0xFFFFFF, 0xFFFFFF, 0xFFFFFF, 0xFFFFFF, 0xFFFFFF, 0xFFFFFF, 0xFFFFFF,
			0xFFFFFF, 0xFFFFFF, 0xFFFFFF, 0xFFFFFF, 0xFFFFFF, 0xFFFFFF, 0xFFFFFF, 0xFFFFFF, 0xFFFFFF, 0xFFFFFF,
			0xFFFFFF, 0xFFFFFF, 0x505050, 0x50FAB4, 0x50FF50, 0x64A0A0, 0xFFB464, 0xB4A064, 0xE68278, 0xE6B482,
			0xE6E682, 0x8282E6, 0xE6E6E6, 0x000000, -1 };

	@SideOnly(Side.CLIENT)
	public void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {
		for (int i = 0; i < ItemFragments.names.length * 4; i++)
			subItems.add(new ItemStack(itemIn, 1, i));
	}

	public ItemFragments(String unlocalizedName) {
		super();
		this.setHasSubtypes(true);
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(CreativeTabs.tabMaterials);
	}

	public int getColor(long time) {
		double t = (int) ((time / 8) % 300);
		int clr = 0;
		int r = 0;
		int g = 0;
		int b = 0;
		if (t > 250 || t < 150) { // 000 - 100
			if (t > 250)
				r = (int) ((t - 250d) / 50d * 255d);
			else if (t < 150 && t > 100)
				r = (int) ((150d - t) / 50d * 255d);
			else
				r = 255;
		}
		if (t > 50 && t < 250) { // 100 - 200
			if (t > 200 && t < 250)
				g = (int) (-(t - 250d) / 50d * 255d);
			else if (t < 100 && t > 50)
				g = (int) ((t - 50d) / 50d * 255d);
			else
				g = 255;
		}
		if (t > 150 || t < 50) { // 200 - 300
			if (t < 50 && t > 0)
				b = (int) ((50d - t) / 50d * 255d);
			else if (t > 150 && t < 200)
				b = (int) ((200d - t) / 50d * 255d);
			else
				b = 255;

		}
		clr += r * 65536;
		clr += g * 256;
		clr += b;
		// System.out.println("Time:" + t + " R:" + r + " G:" + g + " B:" + b);
		return clr;
	}

	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int renderPass) {
		if (!stack.hasTagCompound())
			onFirstTick(stack);
		double clr = stack.getTagCompound().getInteger("color");
		if (clr == -1)
			clr = getColor(Minecraft.getSystemTime());
		if (stack.getTagCompound().getBoolean("anti")) {
			double r = ((int)clr/65536d);
			clr -= r * 65536;
			double g =	((int)clr/256d) ;
			clr -= g * 256;
			double b = ((int)clr);
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
			info.add("Element Name: " + stack.getTagCompound().getString("name"));
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
		name += " Fragment";
		return name;
	}

	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (!stack.hasTagCompound())
			onFirstTick(stack);
	}

	@Override
	public void onFirstTick(ItemStack stack) {
		int i = (int) ((stack.getItemDamage()) / ItemFragments.names.length) + 1;
		stack.setItemDamage(stack.getItemDamage() - (i - 1) * ItemFragments.names.length);
		boolean neg = i % 2 == 0;
		boolean anti = i > 2;
		stack.setTagCompound(new NBTTagCompound());
		stack.getTagCompound().setString("name", names[stack.getItemDamage()]);
		stack.getTagCompound().setDouble("amass", masses[stack.getItemDamage()]);
		stack.getTagCompound().setInteger("color", colors[stack.getItemDamage()]);
		stack.getTagCompound().setBoolean("neg", neg);
		stack.getTagCompound().setBoolean("anti", anti);
	}

}
