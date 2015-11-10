package com.madmodding.space.items.element;

import java.util.ArrayList;
import java.util.List;

import com.madmodding.space.Main;
import com.madmodding.space.items.IFirstTick;

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
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ElementLib {
	private final static String[] typenames = new String[] { "blankingot", "blankdust", "blankpot" };
	// A File that will eventually be used for all of Arideus' Code and its
	// implementation
	public static final EnumElement[] Elements = EnumElement.values();
	public static final EnumElement[] BaseElements = new EnumElement[] { EnumElement.H, EnumElement.He, EnumElement.Li,
			EnumElement.Be, EnumElement.B, EnumElement.C, EnumElement.N, EnumElement.O, EnumElement.F, EnumElement.Ne,
			EnumElement.Na, EnumElement.Mg, EnumElement.Al, EnumElement.Si, EnumElement.P, EnumElement.S,
			EnumElement.Cl, EnumElement.Ar, EnumElement.K, EnumElement.Ca, EnumElement.Sc, EnumElement.Ti,
			EnumElement.V, EnumElement.Cr, EnumElement.Mn, EnumElement.Fe, EnumElement.Co, EnumElement.Ni,
			EnumElement.Cu, EnumElement.Zn, EnumElement.Ga, EnumElement.Ge, EnumElement.As, EnumElement.Se,
			EnumElement.Br, EnumElement.Kr, EnumElement.Rb, EnumElement.Sr, EnumElement.Y, EnumElement.Zr,
			EnumElement.Nb, EnumElement.Mo, EnumElement.Tc, EnumElement.Ru, EnumElement.Rh, EnumElement.Pd,
			EnumElement.Ag, EnumElement.Cd, EnumElement.In, EnumElement.Sn, EnumElement.Sb, EnumElement.Te,
			EnumElement.I, EnumElement.Xe, EnumElement.Cs, EnumElement.Ba, EnumElement.La, EnumElement.Ce,
			EnumElement.Pr, EnumElement.Nd, EnumElement.Pm, EnumElement.Sm, EnumElement.Eu, EnumElement.Gd,
			EnumElement.Tb, EnumElement.Dy, EnumElement.Ho, EnumElement.Er, EnumElement.Tm, EnumElement.Yb,
			EnumElement.Lu, EnumElement.Hf, EnumElement.Ta, EnumElement.W, EnumElement.Re, EnumElement.Os,
			EnumElement.Ir, EnumElement.Pt, EnumElement.Au, EnumElement.Hg, EnumElement.Tl, EnumElement.Pb,
			EnumElement.Bi, EnumElement.Po, EnumElement.At, EnumElement.Rn, EnumElement.Fr, EnumElement.Ra,
			EnumElement.Ac, EnumElement.Th, EnumElement.Pa, EnumElement.U, EnumElement.Np, EnumElement.Pu,
			EnumElement.Am, EnumElement.Cm, EnumElement.Bk, EnumElement.Cf, EnumElement.Es, EnumElement.Fm };
	public static final EnumElement[] NonResElements = new EnumElement[] { EnumElement.H, EnumElement.He,
			EnumElement.Li, EnumElement.Be, EnumElement.B, EnumElement.C, EnumElement.N, EnumElement.O, EnumElement.F,
			EnumElement.Ne, EnumElement.Na, EnumElement.Mg, EnumElement.Al, EnumElement.Si, EnumElement.P,
			EnumElement.S, EnumElement.Cl, EnumElement.Ar, EnumElement.K, EnumElement.Ca, EnumElement.Sc,
			EnumElement.Ti, EnumElement.V, EnumElement.Cr, EnumElement.Mn, EnumElement.Fe, EnumElement.Co,
			EnumElement.Ni, EnumElement.Cu, EnumElement.Zn, EnumElement.Ga, EnumElement.Ge, EnumElement.As,
			EnumElement.Se, EnumElement.Br, EnumElement.Kr, EnumElement.Rb, EnumElement.Sr, EnumElement.Y,
			EnumElement.Zr, EnumElement.Nb, EnumElement.Mo, EnumElement.Tc, EnumElement.Ru, EnumElement.Rh,
			EnumElement.Pd, EnumElement.Ag, EnumElement.Cd, EnumElement.In, EnumElement.Sn, EnumElement.Sb,
			EnumElement.Te, EnumElement.I, EnumElement.Xe, EnumElement.Cs, EnumElement.Ba, EnumElement.La,
			EnumElement.Ce, EnumElement.Pr, EnumElement.Nd, EnumElement.Pm, EnumElement.Sm, EnumElement.Eu,
			EnumElement.Gd, EnumElement.Tb, EnumElement.Dy, EnumElement.Ho, EnumElement.Er, EnumElement.Tm,
			EnumElement.Yb, EnumElement.Lu, EnumElement.Hf, EnumElement.Ta, EnumElement.W, EnumElement.Re,
			EnumElement.Os, EnumElement.Ir, EnumElement.Pt, EnumElement.Au, EnumElement.Hg, EnumElement.Tl,
			EnumElement.Pb, EnumElement.Bi, EnumElement.Po, EnumElement.At, EnumElement.Rn, EnumElement.Fr,
			EnumElement.Ra, EnumElement.Ac, EnumElement.Th, EnumElement.Pa, EnumElement.U, EnumElement.Np,
			EnumElement.Pu, EnumElement.Am, EnumElement.Cm, EnumElement.Bk, EnumElement.Cf, EnumElement.Es,
			EnumElement.Fm, EnumElement.DM, EnumElement.EM };
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
	public static final EnumRarity COMMON = EnumRarity.COMMON;
	public static final EnumRarity UNCOMMON = EnumRarity.UNCOMMON;
	public static final EnumRarity RARE = EnumRarity.RARE;
	public static final EnumRarity EPIC = EnumRarity.EPIC;
	public static final EnumRarity ULTRARARE = EnumHelper.addRarity("SpaceRareUltra", EnumChatFormatting.GOLD,
			"Ultra Rare");
	public static final EnumRarity LEGENDARY = EnumHelper.addRarity("SpaceLegend", EnumChatFormatting.DARK_PURPLE,
			"Legendary");
	public static final EnumRarity PLUS = EnumHelper.addRarity("SpaceBeyond", EnumChatFormatting.BOLD,
			"Beyond Legendary");

	public static int rarityToInt(EnumRarity er) {
		if (er == EnumRarity.COMMON)
			return 0;
		else if (er == EnumRarity.UNCOMMON)
			return 1;
		else if (er == EnumRarity.RARE)
			return 2;
		else if (er == EnumRarity.EPIC)
			return 3;
		else
			return -1;
	}

	public static EnumRarity intToRarity(int er) {
		if (er == 0)
			return EnumRarity.COMMON;
		else if (er == 1)
			return EnumRarity.UNCOMMON;
		else if (er == 2)
			return EnumRarity.RARE;
		else if (er == 3)
			return EnumRarity.EPIC;
		else if (er == 4)
			return ULTRARARE;
		else if (er == 5)
			return LEGENDARY;
		else if (er > 1)
			return PLUS;
		else
			return EnumRarity.COMMON;
	}

	protected static EnumRarity getRarity(ItemStack stack) {
		int r = rarityToInt(Elements[stack.getItemDamage() % Elements.length].getRarity());
		if (stack.getTagCompound().getBoolean("anti"))
			r++;
		if (stack.getTagCompound().getBoolean("neg"))
			r++;
		if (stack.getItem() == Fragment)
			r--;
		return intToRarity(r);
	}

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
		for (int i = 0; i < NonResElements.length; i++) {
			if (NonResElements[i].getHardness() != 0 && NonResElements[i].getType() == 1) {
				for (int t = 0; t < 4; t++) {
					ItemStack stack = new ItemStack(itemIn, 1, i + (t * Elements.length));
					((IFirstTick) itemIn).onFirstTick(stack);
					subItems.add(stack);
				}
			}
		}
		if (Minecraft.getMinecraft().thePlayer.getName() == "Arideus")
			subItems.add(new ItemStack(itemIn, 1, 103));
		if (Minecraft.getMinecraft().thePlayer.getName() == "MadHatInjection")
			subItems.add(new ItemStack(itemIn, 1, 104));
		if (Minecraft.getMinecraft().thePlayer.getName() == "Harpcode")
			subItems.add(new ItemStack(itemIn, 1, 105));
		if (Minecraft.getMinecraft().thePlayer.getName() == "Arideus")
			subItems.add(new ItemStack(itemIn, 1, 106));
		if (Minecraft.getMinecraft().thePlayer.getName().contains("9"))
			subItems.add(new ItemStack(itemIn, 1, 102));
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
		} else if (type == -2) {
			double t = (int) ((time / 32) % 100);
			r = 255;

			if (t >= 0 && t < 50)
				g = (int) ((t) / 50d * 127d);
			else if (t >= 50 && t < 100)
				g = (int) (-(t - 100d) / 50d * 127d);
			else
				g = 127;

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
