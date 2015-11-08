package com.madmodding.space.client.render.items;

import com.madmodding.space.Main;
import com.madmodding.space.blocks.ModBlocks;
import com.madmodding.space.client.models.FluidBlockModel;
import com.madmodding.space.items.ItemFragment;
import com.madmodding.space.items.ItemRefined;
import com.madmodding.space.items.ModItems;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockModelShapes;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

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
		reg(Item.getItemFromBlock(ModBlocks.aluminiumOre));
		reg(Item.getItemFromBlock(ModBlocks.cellBlock));
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
		for (int i = 0; i < ItemFragment.names.length * 4; i++) {
			reg(ModItems.ColorSword, i);
			reg(ModItems.ColorPick, i);
			reg(ModItems.ColorSpade, i);
			reg(ModItems.ColorAxe, i);
			}
		reg(ModItems.dust);
		for (int i = 0; i < 272; i++)
			reg(ModItems.dye, i);
		for (int i = 0; i < ItemFragment.names.length * 4 + ItemFragment.orenames.length; i++)
			reg(ModItems.fragment, i);
		for (int i = 0; i < ItemFragment.names.length * 4; i++) {
			int p = i / (i / ItemFragment.names.length + 1);
			reg(ModItems.ref, i, ItemRefined.typenames[ItemRefined.types[p] - 1]);
		}
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
