package com.madmodding.space.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.*;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.madmodding.space.Main;
import com.madmodding.space.items.ModItems;

public class ModFluids {
	private static final String FLUID_MODEL_PATH = Main.MODID + ":" + "fluid";
	public static Fluid fluidNormal;
	public static BlockFluidClassic blockNormal;
	public static Material acidMat = new MaterialLiquid(MapColor.adobeColor);

	public static void init() {

		fluidNormal = new Fluid("normal", new ResourceLocation("space:blocks/fluid_normal_still"),
				new ResourceLocation("space:blocks/fluid_normal_flow")).setLuminosity(50).setDensity(200)
						.setViscosity(8000);
		FluidRegistry.registerFluid(fluidNormal);

		blockNormal = new AcidBlock(fluidNormal, acidMat);
		GameRegistry.registerBlock(blockNormal, blockNormal.getUnlocalizedName());

		registerFluidModel(blockNormal);

	}

	private static void registerFluidModel(IFluidBlock fluidBlock) {
		Item item = Item.getItemFromBlock((Block) fluidBlock);

		ModelBakery.addVariantName(item);

		final ModelResourceLocation modelResourceLocation = new ModelResourceLocation(FLUID_MODEL_PATH,
				fluidBlock.getFluid().getName());

		ModelLoader.setCustomStateMapper((Block) fluidBlock, new StateMapperBase() {
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState p_178132_1_) {
				return modelResourceLocation;
			}
		});
	}

}
