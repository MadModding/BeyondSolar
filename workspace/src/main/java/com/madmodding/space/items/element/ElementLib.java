package com.madmodding.space.items.element;

import java.util.ArrayList;
import java.util.List;

import com.madmodding.space.Main;
import com.madmodding.space.items.IFirstTick;
import com.madmodding.space.items.ItemArmorCustom;
import com.madmodding.space.items.ModItems;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ElementLib {
	// A File that will eventually be used for all of Arideus' Code and its
	// implementation
	protected static final int[] types = new int[] { 3, 3, 3, 2, 2, 2, 3, 3, 3, 3, 3, 2, 1, 2, 3, 2, 3, 3, 3, 2, 2, 1, 3,
			1, 1, 1, 1, 1, 1, 2, 2, 3, 3, 3, 3, 3, 3, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 2, 2, 2, 2,
			2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 3, 2, 1, 2, 2, 3, 3, 3, 2, 3, 3, 3, 3, 3, 3, 3,
			3, 3, 3, 3, 3, 1, 1, 1 };
	protected static final String[] typenames = new String[] { "blankingot", "blankdust", "blankpot" };
	protected static final double[] hard = new double[] { 0, 0, 0.6, 5.5, 9.5, 1.5, 0, 0, 0, 0, 0.5, 2.5, 3, 6.5, 0, 2.0,
			0, 0, 0.4, 1.5, 0, 6.0, 7.0, 8.5, 6.0, 4.0, 5.0, 4.0, 3.0, 2.5, 1.5, 6.0, 3.5, 2.0, 0, 0, 0.3, 1.5, 0, 5.0,
			6.0, 5.5, 0, 6.5, 6.0, 5.0, 2.5, 2.0, 1.0, 1.5, 3.0, 2.0, 0, 0, 0.2, 1.0, 2.5, 2.5, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 5.5, 6.5, 7.5, 7.0, 7.0, 6.5, 3.5, 2.5, 0, 1.0, 1.5, 2.5, 0, 0, 0, 0, 0, 0, 3.0, 0, 6.0, 0,
			0, 0, 0, 0, 0, 0, 0, 11, 20 };
	protected static final String[] names = new String[] { "Hydrogen", "Helium", "Lithium", "Beryllium", "Boron", "Carbon",
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
	protected static final double[] masses = new double[] { 1.0079D, 4.0026D, 6.941D, 9.0122D, 10.811D, 12.0107D, 14.0067D,
			15.9994D, 18.9984D, 20.1797D, 22.9897D, 24.305D, 26.9815D, 28.0855D, 30.9738D, 32.065D, 35.453D, 39.0983D,
			39.948D, 40.078D, 44.9559D, 47.867D, 50.9415D, 51.9961D, 54.938D, 55.845D, 58.6934D, 58.9332D, 63.546D,
			65.39D, 69.723D, 72.64D, 74.9216D, 78.96D, 79.904D, 83.8D, 85.4678D, 87.62D, 88.9059D, 91.224D, 92.9064D,
			95.94D, 98D, 101.07D, 102.9055D, 106.42D, 107.8682D, 112.411D, 114.818D, 118.71D, 121.76D, 126.9045D,
			127.6D, 131.293D, 132.9055D, 137.327D, 138.9055D, 140.116D, 140.9077D, 144.24D, 145D, 150.36D, 151.964D,
			157.25D, 158.9253D, 162.5D, 164.9303D, 167.259D, 168.9342D, 173.04D, 174.967D, 178.49D, 180.9479D, 183.84D,
			186.207D, 190.23D, 192.217D, 195.078D, 196.9665D, 200.59D, 204.3833D, 207.2D, 208.9804D, 209D, 210D, 222D,
			223D, 226D, 227D, 231.0359D, 232.0381D, 237D, 238.0289D, 243D, 244D, 247D, 247D, 251D, 252D, 257D, 10000D,
			-1d };
	protected static final int[] colors = new int[] { 0xDCDCFF, 0xFFDCFF, 0xA0A0A0, 0x787878, 0x9696A5, 0x282828, 0xA0A0A0,
			0xF0F0F0, 0x8C8CF0, 0xF02828, 0x969696, 0x969696, 0xAFAFAF, 0x414141, 0xA54141, 0xEBE119, 0xCDD719,
			0xB914CD, 0x9B9BCD, 0xA5A59B, 0xAFB99B, 0xFFFFFF, 0x9B9B9B, 0xC3C3C3, 0xAFAFC3, 0xAFAF9B, 0x4141E1,
			0xB4B4B4, 0xD77300, 0xB4B4B4, 0xB4B4B4, 0xA4A4A4, 0xFFA573, 0xD7D7D7, 0xD7370F, 0xD77373, 0xD75F5F,
			0xD7C39B, 0xA4A4A4, 0xD7D7D7, 0xAFAFAF, 0x878787, 0xA5A5B9, 0x7D7D7D, 0x7D7D7D, 0xA0A0A0, 0xAAAAA0,
			0xA0A0AA, 0x8C8C8C, 0xB4B4B4, 0xB4B4BE, 0x969696, 0x3C3C6E, 0xA03CD2, 0xA0A082, 0xD2D2DC, 0xA0AAA0,
			0xBE7878, 0x788C8C, 0x8C7878, 0x14FF78, 0xAA9696, 0xA9A9A9, 0x808080, 0x6C6C6C, 0x303030, 0x949494,
			0x949494, 0x9E9494, 0xB29E8A, 0xA89EB2, 0xA8A8A8, 0xA8A8A8, 0xBCADA8, 0x1C1C1C, 0x8A809E, 0xFFFFFF,
			0x9E8A80, 0xC6BC1C, 0x808080, 0xA88A80, 0x80808A, 0x80BC80, 0xC6BC44, 0x44BC1C, 0xA88080, 0xBC8058,
			0x00FFFF, 0xEBEBEB, 0x505050, 0x50FAB4, 0x50FF50, 0x64A0A0, 0xFFB464, 0xB4A064, 0xE68278, 0xE6B482,
			0xE6E682, 0x8282E6, 0xE6E6E6, 0x000000, -1 };
	protected static final String[] orenames = new String[] { "Ferrous", "Rare Metal", "Platinum Metal" };
	protected static final double[][][] orecontains = new double[][][] { { { 90, 25 }, { 5, 26 }, { 5, 27 } },
			{ { 1d / 30d * 100d, 56 }, { 1d / 30d * 100d, 57 }, { 1d / 30d * 100d, 58 }, { 1d / 30d * 100d, 59 },
					{ 1d / 30d * 100d, 60 }, { 1d / 30d * 100d, 61 }, { 1d / 30d * 100d, 62 }, { 1d / 30d * 100d, 63 },
					{ 1d / 30d * 100d, 64 }, { 1d / 30d * 100d, 65 }, { 1d / 30d * 100d, 66 }, { 1d / 30d * 100d, 67 },
					{ 1d / 30d * 100d, 68 }, { 1d / 30d * 100d, 69 }, { 1d / 30d * 100d, 70 },
					{ 1d / 30d * 100d, 56 + 32 }, { 1d / 30d * 100d, 57 + 32 }, { 1d / 30d * 100d, 58 + 32 },
					{ 1d / 30d * 100d, 59 + 32 }, { 1d / 30d * 100d, 60 + 32 }, { 1d / 30d * 100d, 61 + 32 },
					{ 1d / 30d * 100d, 62 + 32 }, { 1d / 30d * 100d, 63 + 32 }, { 1d / 30d * 100d, 64 + 32 },
					{ 1d / 30d * 100d, 65 + 32 }, { 1d / 30d * 100d, 66 + 32 }, { 1d / 30d * 100d, 67 + 32 } },
			{ { 1d / 6d * 100d, 43 }, { 1d / 6d * 100d, 44 }, { 1d / 6d * 100d, 45 }, { 1d / 6d * 100d, 75 },
					{ 1d / 6d * 100d, 76 }, { 1d / 6d * 100d, 77 } } };

					protected static final ToolMaterial material = EnumHelper.addToolMaterial("SpaceMaterial", 4, 18054, 20.0f, 16.0f, 0);
	public static final Item ElementSword = new ItemSwordMaterial("colorsword", material)
			.setCreativeTab(Main.aliensTabTech);
	public static final Item ElementAxe = new ItemAxeMaterial("coloraxe", material).setCreativeTab(Main.aliensTabTech);
	public static final Item ElementSpade = new ItemSpadeMaterial("colorspade", material)
			.setCreativeTab(Main.aliensTabTech);
	public static final Item ElementPick = new ItemPickMaterial("colorpick", material)
			.setCreativeTab(Main.aliensTabTech);
	public static final Item Fragment = new ItemFragment("fragment").setCreativeTab(Main.aliensTabUnRef);
	public static final Item Refined = new ItemRefined("blankingot").setCreativeTab(Main.aliensTabRef);
	public static final Item Dye = new ItemDyeSpec().setCreativeTab(Main.aliensTabRef);
	public static final Item ElementHelm = new ItemArmorMaterial("colorhelm", ArmorMaterial.LEATHER, 0)
			.setCreativeTab(Main.aliensTabTech);
	public static final Item ElementChest = new ItemArmorMaterial("colorchest", ArmorMaterial.LEATHER, 1)
			.setCreativeTab(Main.aliensTabTech);
	public static final Item ElementLegs = new ItemArmorMaterial("colorlegs", ArmorMaterial.LEATHER, 2)
			.setCreativeTab(Main.aliensTabTech);
	public static final Item ElementBoots = new ItemArmorMaterial("colorboots", ArmorMaterial.LEATHER, 3)
			.setCreativeTab(Main.aliensTabTech);
	
	public static void initCommon() {
		{
			GameRegistry.registerItem(Dye, "spec_dye");
			GameRegistry.registerItem(Refined, "blankingot");
			GameRegistry.registerItem(Fragment, "fragment");
			GameRegistry.registerItem(ElementSword, "colorsword");
			GameRegistry.registerItem(ElementAxe, "coloraxe");
			GameRegistry.registerItem(ElementSpade, "colorspade");
			GameRegistry.registerItem(ElementPick, "colorpick");
			GameRegistry.registerItem(ElementHelm, "colorhelm");
			GameRegistry.registerItem(ElementChest, "colorchest");
			GameRegistry.registerItem(ElementLegs, "colorlegs");
			GameRegistry.registerItem(ElementBoots, "colorboots");
		}
		{
			ModelBakery.addVariantName(Refined,
					new String[] { Main.MODID + ":blankingot", Main.MODID + ":blankdust", Main.MODID + ":blankpot" });
		}
	}

	public static void initClient() {
		for (int i = 0; i < ElementLib.names.length * 4; i++) {
			reg(ElementSword, i);
			reg(ElementPick, i);
			reg(ElementSpade, i);
			reg(ElementAxe, i);
			reg(ElementHelm, i);
			reg(ElementChest, i);
			reg(ElementLegs, i);
			reg(ElementBoots, i);
		}
		for (int i = 0; i < 272; i++)
			reg(Dye, i);
		for (int i = 0; i < ElementLib.names.length * 4 + ElementLib.orenames.length; i++)
			reg(Fragment, i);
		for (int i = 0; i < ElementLib.names.length * 4; i++) {
			int p = i / (i / ElementLib.names.length + 1);
			reg(Refined, i, ElementLib.typenames[ElementLib.types[p] - 1]);
		}
	}
	public static void addDrop(ArrayList drops, int meta) {
		drops.add(new ItemStack(Fragment, 1, meta));
	}
	protected static void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {
		for (int i = 0; i < ElementLib.names.length; i++) {
			if (ElementLib.hard[i] != 0 && ElementLib.types[i] == 1) {
				for (int t = 0; t < 4; t++) {
					ItemStack stack = new ItemStack(itemIn, 1, i + (t * ElementLib.names.length));
					((IFirstTick) itemIn).onFirstTick(stack);
					subItems.add(stack);
				}
			}
		}
	}

	protected static int getColorFromItemStack(ItemStack stack, int renderPass, int ignorepass) {
		if (!stack.hasTagCompound())
			((IFirstTick) stack.getItem()).onFirstTick(stack);
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
		if (renderPass != ignorepass) {
			return (int) clr;
		}
		if (stack.getTagCompound().getBoolean("neg"))
			return 0x111111;
		return stack.getTagCompound().getInteger("Color" + renderPass);
	}

	protected static int getColor(long time) {
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
				b = (int) ((t - 150d) / 50d * 255d);
			else
				b = 255;

		}
		clr += r * 65536;
		clr += g * 256;
		clr += b;
		return clr;
	}

	private static String modid = Main.MODID;

	private static void reg(Item item, int i, String nameadd) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, i,
				new ModelResourceLocation(modid + ":" + nameadd, "inventory"));
	}

	private static void reg(Item item, int i) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, i,
				new ModelResourceLocation(modid + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}

	private static void reg(Item item) {
		int i = 0;
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, i,
				new ModelResourceLocation(modid + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}

	private static void reg(Item item, String nameext, int i) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, i,
				new ModelResourceLocation(modid + ":" + item.getUnlocalizedName().substring(5) + nameext, "inventory"));
	}

	private static void reg(Block block) {
		int i = 0;
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), i,
				new ModelResourceLocation(modid + ":" + block.getUnlocalizedName().substring(5), "inventory"));
	}
}
