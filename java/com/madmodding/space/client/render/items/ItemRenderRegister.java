package com.madmodding.space.client.render.items;

import com.madmodding.space.Main;
import com.madmodding.space.blocks.ModBlocks;
import com.madmodding.space.items.ModItems;
import com.madmodding.space.items.element.ElementLib;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;

public final class ItemRenderRegister {

	public static void registerItemRenderer() {

		ItemModelMesher modelRegistry = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
		reg(ModItems.evilstaff);
		reg(ModItems.drill);
		reg(ModItems.ColorBoots);
		reg(ModItems.ColorChest);
		reg(ModItems.ColorHelm);
		reg(ModItems.ColorLegs);
		reg(ModItems.dustcrusher);
		reg(ModItems.enderpouch);
		reg(ModItems.pouch);
		reg(ModItems.acidTank);
		reg(ModItems.acidTankfull);
		reg(ModItems.dust);
		reg(ModItems.acidSquirter);
		reg(Item.getItemFromBlock(ModBlocks.tpBlock));
		reg(Item.getItemFromBlock(ModBlocks.aluminiumOre));
		reg(Item.getItemFromBlock(ModBlocks.bauxiteOre));
		reg(Item.getItemFromBlock(ModBlocks.copperOre));
		reg(Item.getItemFromBlock(ModBlocks.ferrousOre));
		reg(Item.getItemFromBlock(ModBlocks.cobaltOre));
		reg(Item.getItemFromBlock(ModBlocks.leadOre));
		reg(Item.getItemFromBlock(ModBlocks.magnesiumOre));
		reg(Item.getItemFromBlock(ModBlocks.nickelOre));
		reg(Item.getItemFromBlock(ModBlocks.platinumMeteorite));
		reg(Item.getItemFromBlock(ModBlocks.platinumOre));
		reg(Item.getItemFromBlock(ModBlocks.rareMetalMeteorite));
		reg(Item.getItemFromBlock(ModBlocks.siliconOre));
		reg(Item.getItemFromBlock(ModBlocks.silverOre));
		reg(Item.getItemFromBlock(ModBlocks.tinOre));
		reg(Item.getItemFromBlock(ModBlocks.titaniumOre));
		reg(Item.getItemFromBlock(ModBlocks.uraniumOre));
		reg(Item.getItemFromBlock(ModBlocks.zincOre));
		reg(Item.getItemFromBlock(ModBlocks.ice));		
	}

	// ==========================================================================

	public static String modid = Main.MODID;

	public static void reg(Item item, int i, String nameadd) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, i,
				new ModelResourceLocation(modid + ":" + nameadd, "inventory"));
	}

	public static void reg(Item item, int i) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, i,
				new ModelResourceLocation(modid + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}

	public static void reg(Item item) {
		int i = 0;
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, i,
				new ModelResourceLocation(modid + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}

	public static void reg(Item item, String nameext, int i) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, i,
				new ModelResourceLocation(modid + ":" + item.getUnlocalizedName().substring(5) + nameext, "inventory"));
	}

	public static void reg(Block block) {
		int i = 0;
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), i,
				new ModelResourceLocation(modid + ":" + block.getUnlocalizedName().substring(5), "inventory"));
	}
}
