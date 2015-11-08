package com.madmodding.space.items;

import com.madmodding.space.Main;
import com.madmodding.space.items.inv.ItemBag;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class ModItems {
	public static final Item.ToolMaterial evilStaffToolMaterial = EnumHelper.addToolMaterial("alienStaffMaterial", 0,
			100, 0.0F, 3.0F, 30);
	public static final ToolMaterial material = EnumHelper.addToolMaterial("SpaceMaterial", 4, 18054, 20.0f,
			16.0f, 0);
	public static final Item evilstaff = new EvilStaff("evilstaff", evilStaffToolMaterial)
			.setCreativeTab(Main.aliensTabTech);
	public static final Item warp = new WarpStar("warp").setCreativeTab(Main.aliensTabTech);
	public static final Item fragment = new ItemFragment("fragment").setCreativeTab(Main.aliensTabUnRef);
	public static final Item ref = new ItemRefined("blankingot").setCreativeTab(Main.aliensTabRef);
	public static final Item ColorHelm = new ItemArmorCustom("colorhelm", ArmorMaterial.LEATHER, 0)
			.setCreativeTab(Main.aliensTabTech);
	public static final Item ColorChest = new ItemArmorCustom("colorchest", ArmorMaterial.LEATHER, 1)
			.setCreativeTab(Main.aliensTabTech);
	public static final Item ColorLegs = new ItemArmorCustom("colorlegs", ArmorMaterial.LEATHER, 2)
			.setCreativeTab(Main.aliensTabTech);
	public static final Item ColorBoots = new ItemArmorCustom("colorboots", ArmorMaterial.LEATHER, 3)
			.setCreativeTab(Main.aliensTabTech);
	public static final Item ColorSword = new ItemSwordMaterial("colorsword", material)
			.setCreativeTab(Main.aliensTabTech);
	public static final Item ColorAxe = new ItemAxeMaterial("coloraxe", material)
			.setCreativeTab(Main.aliensTabTech);
	public static final Item ColorSpade = new ItemSpadeMaterial("colorspade", material)
			.setCreativeTab(Main.aliensTabTech);
	public static final Item ColorPick = new ItemPickMaterial("colorpick", material)
			.setCreativeTab(Main.aliensTabTech);
	public static final Item dye = new ItemDyeSpec().setCreativeTab(Main.aliensTabRef);
	public static final Item dust = new Item().setCreativeTab(Main.aliensTabUnRef).setUnlocalizedName("dust");
	public static final Item dustcrusher = new ItemCrusher("dustcrusher").setCreativeTab(Main.aliensTabTech);
	public static final Item acidTank = new AcidTank("acidtank");
	public static final Item acidTankfull = new AcidTankfull("acidtankfull");
	public static final Item drill = new ItemDrill("drill").setCreativeTab(Main.aliensTabTech);
	public static final Item enderpouch = new EnderItem("enderpouch");
	public static final Item pouch = new ItemBag("pouch");
	
	public static void init() {
		GameRegistry.registerItem(pouch, "pouch");
		GameRegistry.registerItem(enderpouch, "enderpouch");
		GameRegistry.registerItem(dust, "dust");
		GameRegistry.registerItem(drill, "drill");
		GameRegistry.registerItem(dustcrusher, "dustcrusher");
		GameRegistry.registerItem(ref, "blankingot");
		GameRegistry.registerItem(dye, "spec_dye");
		GameRegistry.registerItem(ColorHelm, "colorhelm");
		GameRegistry.registerItem(ColorChest, "colorchest");
		GameRegistry.registerItem(ColorLegs, "colorlegs");
		GameRegistry.registerItem(ColorBoots, "colorboots");
		GameRegistry.registerItem(ColorSword, "colorsword");
		GameRegistry.registerItem(ColorAxe, "coloraxe");
		GameRegistry.registerItem(ColorSpade, "colorspade");
		GameRegistry.registerItem(ColorPick, "colorpick");
		GameRegistry.registerItem(evilstaff, "evilstaff");
		GameRegistry.registerItem(warp, "warp");
		GameRegistry.registerItem(fragment, "fragment");
	}

}
