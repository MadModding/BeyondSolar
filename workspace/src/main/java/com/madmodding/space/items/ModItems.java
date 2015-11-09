package com.madmodding.space.items;

import com.madmodding.space.Main;
import com.madmodding.space.items.element.ItemCrusher;
import com.madmodding.space.items.element.ItemDrill;
import com.madmodding.space.items.inv.EnderItem;
import com.madmodding.space.items.inv.ItemBag;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class ModItems {
	public static final Item.ToolMaterial evilStaffToolMaterial = EnumHelper.addToolMaterial("alienStaffMaterial", 0,
			100, 0.0F, 3.0F, 30);
	public static final Item evilstaff = new EvilStaff("evilstaff", evilStaffToolMaterial)
			.setCreativeTab(Main.aliensTabTech);
	public static final Item warp = new WarpStar("warp").setCreativeTab(Main.aliensTabTech);
	public static final Item ColorHelm = new ItemArmorCustom("spacehelm", ArmorMaterial.LEATHER, 0)
			.setCreativeTab(Main.aliensTabTech);
	public static final Item ColorChest = new ItemArmorCustom("spacechest", ArmorMaterial.LEATHER, 1)
			.setCreativeTab(Main.aliensTabTech);
	public static final Item ColorLegs = new ItemArmorCustom("spacelegs", ArmorMaterial.LEATHER, 2)
			.setCreativeTab(Main.aliensTabTech);
	public static final Item ColorBoots = new ItemArmorCustom("spaceboots", ArmorMaterial.LEATHER, 3)
			.setCreativeTab(Main.aliensTabTech);
	public static final Item dust = new Item().setCreativeTab(Main.aliensTabUnRef).setUnlocalizedName("dust");
	public static final Item dustcrusher = new ItemCrusher("dustcrusher").setCreativeTab(Main.aliensTabTech)
			.setMaxStackSize(1);
	public static final Item acidTank = new AcidTank("acidtank");
	public static final Item acidTankfull = new AcidTankfull("acidtankfull");
	public static final Item drill = new ItemDrill("drill").setCreativeTab(Main.aliensTabTech).setMaxStackSize(1);
	public static final Item enderpouch = new EnderItem("enderpouch");
	public static final Item pouch = new ItemBag("pouch");

	public static void init() {
		GameRegistry.registerItem(pouch, "pouch");
		GameRegistry.registerItem(enderpouch, "enderpouch");
		GameRegistry.registerItem(dust, "dust");
		GameRegistry.registerItem(drill, "drill");
		GameRegistry.registerItem(dustcrusher, "dustcrusher");
		GameRegistry.registerItem(ColorHelm, "spacehelm");
		GameRegistry.registerItem(ColorChest, "spacechest");
		GameRegistry.registerItem(ColorLegs, "spacelegs");
		GameRegistry.registerItem(ColorBoots, "spaceboots");
		GameRegistry.registerItem(evilstaff, "evilstaff");
		GameRegistry.registerItem(warp, "warp");
		GameRegistry.registerItem(acidTank, "acidtank");
		GameRegistry.registerItem(acidTankfull, "acidtankfull");

	}

}
