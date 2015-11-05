package com.madmodding.space.items;

import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class ModItems {
	public static final Item.ToolMaterial evilStaffToolMaterial = EnumHelper.addToolMaterial("alienStaffMaterial", 0,
			100, 0.0F, 3.0F, 30);
	public static final Item evilstaff = new EvilStaff("evilstaff", evilStaffToolMaterial);
	public static final Item warp = new WarpStar("warp");
	public static final Item fragment = new ItemFragment("fragment");

	public static void init() {
		GameRegistry.registerItem(evilstaff, "evilstaff");
		GameRegistry.registerItem(warp, "warp");
		GameRegistry.registerItem(fragment, "fragment");
	}

}
