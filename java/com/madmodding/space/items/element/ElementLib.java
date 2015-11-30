package com.madmodding.space.items.element;

import java.util.ArrayList;
import java.util.List;

import com.madmodding.space.Main;
import com.madmodding.space.items.IFirstTick;
import com.madmodding.space.items.boost.ItemArmorBoost;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ElementLib {
	private final static String[] typenames = new String[] { "blankingot", "blankdust", "blankpot" };
	// A File that will eventually be used for all of Arideus' Code and its
	// implementation
	public static final Element[] Elements = Element.values();
	public static final Element[] BaseElements = new Element[] { Element.h, Element.he, Element.li, Element.be,
			Element.b, Element.c, Element.n, Element.o, Element.f, Element.ne, Element.na, Element.mg, Element.al,
			Element.si, Element.p, Element.s, Element.cl, Element.ar, Element.k, Element.ca, Element.sc, Element.ti,
			Element.v, Element.cr, Element.mn, Element.fe, Element.co, Element.ni, Element.cu, Element.zn, Element.ga,
			Element.ge, Element.as, Element.se, Element.br, Element.kr, Element.rb, Element.sr, Element.y, Element.zr,
			Element.nb, Element.mo, Element.tc, Element.ru, Element.rh, Element.pd, Element.ag, Element.cd, Element.in,
			Element.sn, Element.sb, Element.te, Element.i, Element.xe, Element.cs, Element.ba, Element.la, Element.ce,
			Element.pr, Element.nd, Element.pm, Element.sm, Element.eu, Element.gd, Element.tb, Element.dy, Element.ho,
			Element.er, Element.tm, Element.yb, Element.lu, Element.hf, Element.ta, Element.w, Element.re, Element.os,
			Element.ir, Element.pt, Element.au, Element.hg, Element.tl, Element.pb, Element.bi, Element.po, Element.at,
			Element.rn, Element.fr, Element.ra, Element.ac, Element.th, Element.pa, Element.u, Element.np, Element.pu,
			Element.am, Element.cm, Element.bk, Element.cf, Element.es, Element.fm };
	public static final Element[] NonResElements = new Element[] { Element.h, Element.he, Element.li, Element.be,
			Element.b, Element.c, Element.n, Element.o, Element.f, Element.ne, Element.na, Element.mg, Element.al,
			Element.si, Element.p, Element.s, Element.cl, Element.ar, Element.k, Element.ca, Element.sc, Element.ti,
			Element.v, Element.cr, Element.mn, Element.fe, Element.co, Element.ni, Element.cu, Element.zn, Element.ga,
			Element.ge, Element.as, Element.se, Element.br, Element.kr, Element.rb, Element.sr, Element.y, Element.zr,
			Element.nb, Element.mo, Element.tc, Element.ru, Element.rh, Element.pd, Element.ag, Element.cd, Element.in,
			Element.sn, Element.sb, Element.te, Element.i, Element.xe, Element.cs, Element.ba, Element.la, Element.ce,
			Element.pr, Element.nd, Element.pm, Element.sm, Element.eu, Element.gd, Element.tb, Element.dy, Element.ho,
			Element.er, Element.tm, Element.yb, Element.lu, Element.hf, Element.ta, Element.w, Element.re, Element.os,
			Element.ir, Element.pt, Element.au, Element.hg, Element.tl, Element.pb, Element.bi, Element.po, Element.at,
			Element.rn, Element.fr, Element.ra, Element.ac, Element.th, Element.pa, Element.u, Element.np, Element.pu,
			Element.am, Element.cm, Element.bk, Element.cf, Element.es, Element.fm, Element.DM, Element.EM };
	protected static final ToolMaterial material = EnumHelper.addToolMaterial("SpaceMaterial", 4, 18054, 20.0f, 16.0f,
			0);
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
	public static final Item AlloySword = new ItemSwordAlloy("alloysword", material).setCreativeTab(Main.aliensTabTech);
	public static final Item AlloyIngot = new ItemRefinedAlloy("alloyingot").setCreativeTab(Main.aliensTabRef);
	public static final Item StrongChest = new ItemArmorBoost("strongchest", 1,
			new double[][] { { 0, -0.2 }, { 1, 0.5 }, { 2, 0.25 }, { 3, 1 }, { 4, 0 } })
					.setCreativeTab(Main.aliensTabTech);
	public static final Item SturdyPants = new ItemArmorBoost("sturdypants", 2,
			new double[][] { { 5, -0.1 }, { 6, -0.1 }, { 7, 1 }, { 8, 0.25 }, { 9, 0 } })
					.setCreativeTab(Main.aliensTabTech);
	public static final Item QuickBoots = new ItemArmorBoost("quickboots", 3,
			new double[][] { { 10, 1.3 }, { 11, 0.3 }, { 12, -0.25 }, { 13, 0.25 }, { 14, 0 } })
					.setCreativeTab(Main.aliensTabTech);

	public static int rarityToInt(EnumRarity er) {
		if (er == Main.COMMON)
			return 0;
		else if (er == Main.UNCOMMON)
			return 1;
		else if (er == Main.RARE)
			return 2;
		else if (er == Main.EPIC)
			return 3;
		else if (er == Main.ULTRARARE)
			return 4;
		else if (er == Main.LEGENDARY)
			return 5;
		else if (er == Main.PLUS)
			return 6;
		else
			return -1;
	}

	public static String getName(ItemStack stack) {
		if (stack.getItem() instanceof IAlloy) {
			String name = ("" + StatCollector
					.translateToLocal("alloy." + EnumAlloy.values()[stack.getItemDamage()].getName() + ".name")).trim();
			if (stack.getItemDamage() >= 0)
				return name;
			else
				return "Alloy";
		}
		String elename = ("" + StatCollector
				.translateToLocal("element." + ElementLib.Elements[stack.getItemDamage()].getName() + ".name")).trim();
		if (elename == "element." + ElementLib.Elements[stack.getItemDamage()].getName() + ".name")
			elename = ElementLib.Elements[stack.getItemDamage()].getName();
		String name = "";
		if (stack.getTagCompound().getBoolean("neg") && stack.getTagCompound().getBoolean("anti")) {
			if (("" + StatCollector.translateToLocal(
					"anti.neg." + "element." + ElementLib.Elements[stack.getItemDamage()].getName() + ".name"))
							.trim() == "anti.neg." + "element." + ElementLib.Elements[stack.getItemDamage()].getName()
									+ ".name") {
				name = ("" + StatCollector.translateToLocal(
						"anti.neg." + "element." + ElementLib.Elements[stack.getItemDamage()].getName() + ".name"))
								.trim();
			} else {
				name += "Negative-Anti-";
				name += elename;
			}
		} else if (stack.getTagCompound().getBoolean("neg")) {
			if (("" + StatCollector.translateToLocal(
					"neg." + "element." + ElementLib.Elements[stack.getItemDamage()].getName() + ".name"))
							.trim() == "neg." + "element." + ElementLib.Elements[stack.getItemDamage()].getName()
									+ ".name") {
				name = ("" + StatCollector.translateToLocal(
						"neg." + "element." + ElementLib.Elements[stack.getItemDamage()].getName() + ".name")).trim();
			} else {
				name += "Negative ";
				name += elename;
			}
		} else if (stack.getTagCompound().getBoolean("anti")) {
			if (("" + StatCollector.translateToLocal(
					"anti." + "element." + ElementLib.Elements[stack.getItemDamage()].getName() + ".name"))
							.trim() == "anti." + "element." + ElementLib.Elements[stack.getItemDamage()].getName()
									+ ".name") {
				name = ("" + StatCollector.translateToLocal(
						"anti." + "element." + ElementLib.Elements[stack.getItemDamage()].getName() + ".name")).trim();
			} else {
				name += "Anti-";
				name += elename;
			}
		} else
			name += elename;
		return name;
	}

	public static EnumRarity intToRarity(int er) {
		if (er == 0)
			return Main.COMMON;
		else if (er == 1)
			return Main.UNCOMMON;
		else if (er == 2)
			return Main.RARE;
		else if (er == 3)
			return Main.EPIC;
		else if (er == 4)
			return Main.ULTRARARE;
		else if (er == 5)
			return Main.LEGENDARY;
		else if (er > 1)
			return Main.PLUS;
		else
			return Main.COMMON;
	}

	protected static EnumRarity getRarity(ItemStack stack) {
		int r = rarityToInt(Elements[stack.getItemDamage() % Elements.length].getRarity());
		if (stack.getTagCompound().getBoolean("anti"))
			r++;
		if (stack.getTagCompound().getBoolean("neg"))
			r++;
		if (stack.getItem() == Fragment)
			r--;
		// return Elements[stack.getItemDamage() % Elements.length].getRarity();
		return intToRarity(r);
	}

	private static List coolpeople = new ArrayList();

	public static void initCommon() {
		coolpeople.add("Arideus");
		coolpeople.add("MadHatInjection");
		coolpeople.add("HarpCode");
		coolpeople.add("Samuel");
		coolpeople.add("JurassicAlien");
		{
			GameRegistry.registerItem(StrongChest, "strongchest");
			GameRegistry.registerItem(SturdyPants, "sturdypants");
			GameRegistry.registerItem(QuickBoots, "quickboots");
			GameRegistry.registerItem(AlloySword, "alloysword");
			GameRegistry.registerItem(AlloyIngot, "alloyingot");
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
		reg(StrongChest);
		reg(SturdyPants);
		reg(QuickBoots);
		for (int i = 0; i < EnumAlloy.values().length; i++) {
			reg(AlloySword, i);
			reg(AlloyIngot, i);
		}
		for (int i = 0; i < Elements.length * 4; i++) {
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
		for (int i = 0; i < Elements.length * 4; i++)
			reg(Fragment, i);
		for (int i = 0; i < Elements.length * 4; i++) {
			int p = i / (i / Elements.length + 1);
			reg(Refined, i, ElementLib.typenames[Elements[p].getType() - 1]);
		}
	}

	public static void addDrop(ArrayList drops, int meta) {
		drops.add(new ItemStack(Fragment, 1, meta));
	}

	protected static void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {
		if (itemIn instanceof IAlloy) {
			for (int i = 0; i < EnumAlloy.values().length; i++) {
				ItemStack stack = new ItemStack(itemIn, 1, i);
				((IFirstTick) itemIn).onFirstTick(stack);
				subItems.add(stack);
			}
			return;
		}
		for (int i = 0; i < NonResElements.length; i++) {
			if (NonResElements[i].getHardness() != 0 && NonResElements[i].getType() == 1) {
				for (int t = 0; t < 4; t++) {
					ItemStack stack = new ItemStack(itemIn, 1, i + (t * Elements.length));
					((IFirstTick) itemIn).onFirstTick(stack);
					subItems.add(stack);
				}
			}
		}
		if (isSpecial(Minecraft.getMinecraft().thePlayer.getName()) == 1) {
			subItems.add(new ItemStack(itemIn, 1, 102));
			subItems.add(new ItemStack(itemIn, 1, 103));
		}
		if (isSpecial(Minecraft.getMinecraft().thePlayer.getName()) == 2) {
			subItems.add(new ItemStack(itemIn, 1, 102));
			subItems.add(new ItemStack(itemIn, 1, 104));
		}
		if (isSpecial(Minecraft.getMinecraft().thePlayer.getName()) == 3) {
			subItems.add(new ItemStack(itemIn, 1, 102));
			subItems.add(new ItemStack(itemIn, 1, 105));
		}
		if (isSpecial(Minecraft.getMinecraft().thePlayer.getName()) == 4) {
			subItems.add(new ItemStack(itemIn, 1, 102));
			subItems.add(new ItemStack(itemIn, 1, 106));
		}
		if (isSpecial(Minecraft.getMinecraft().thePlayer.getName()) == 6) {
			subItems.add(new ItemStack(itemIn, 1, 102));
			subItems.add(new ItemStack(itemIn, 1, 107));
		}
		if (isSpecial(Minecraft.getMinecraft().thePlayer.getName()) == 5) {
			subItems.add(new ItemStack(itemIn, 1, 102));
		}
	}

	protected static int getColorFromItemStack(ItemStack stack, int renderPass, int ignorepass) {
		if (!stack.hasTagCompound())
			((IFirstTick) stack.getItem()).onFirstTick(stack);
		double clr = stack.getTagCompound().getInteger("color");
		if (clr < 0)
			clr = getColor(Minecraft.getSystemTime(), (int) clr);
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

	protected static int getColor(long time, int type) {
		int clr = 0;
		int r = 0;
		int g = 0;
		int b = 0;
		if (type == -1) {
			double t = (int) ((time / 8) % 300);
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
		} else if (type == -2) {// Solar Flare
			double t = (int) ((time / 32) % 100);
			r = 255;

			if (t >= 0 && t < 50)
				g = (int) ((t) / 50d * 127d);
			else if (t >= 50 && t < 100)
				g = (int) (-(t - 100d) / 50d * 127d);
			else
				g = 127;

		} else if (type == -3) {// Arideum
			double t = (int) ((time / 32) % 100);
			r = b = 100;
			if (t >= 0 && t < 50)
				r = b += (int) ((t) / 50d * 100d);
			else if (t >= 50 && t < 100)
				b = r += (int) (-(t - 100d) / 50d * 100d);
			else
				b = r = 200;

		} else if (type == -4) {// Madhatium
			double t = (int) ((time / 32) % 100);
			g = 100;
			if (t >= 0 && t < 50)
				g += (int) ((t) / 50d * 155d);
			else if (t >= 50 && t < 100)
				g += (int) (-(t - 100d) / 50d * 155d);
			else
				g = 255;

		} else if (type == -5) {// Jurassicalienthiumtium
			double t = (int) ((time / 32) % 100);
			if (t >= 0 && t < 50)
				r = (int) ((t) / 50d * 255d);
			else if (t >= 50 && t < 100)
				r = (int) (-(t - 100d) / 50d * 255d);
			else
				r = 255;

		}

		clr += r * 65536;
		clr += g * 256;
		clr += b;
		return clr;
	}

	public static int isSpecial(String name) {
		if (name.equals("Arideus"))
			return 1;
		if (name.equals("MadHatInjection"))
			return 2;
		if (name.equals("HarpCode"))
			return 3;
		if (name.equals("Samuel"))
			return 4;
		if (name.equals("JurassicAlien"))
			return 6;
		if (coolpeople.contains(name))
			return 5;
		return 0;
	}

	public static List toList(Object[] array) {
		List list = new ArrayList();
		for (int i = 0; i < array.length; i++)
			list.add(array[i]);
		return list;
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
