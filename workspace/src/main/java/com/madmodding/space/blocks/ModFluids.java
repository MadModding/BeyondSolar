package com.madmodding.space.blocks;

import com.madmodding.space.Main;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModFluids {
	//location of model files
	private static final String FLUID_MODEL_PATH = Main.MODID + ":" + "fluid";
	public static Fluid fluidAcid;
	public static AcidBlock blockAcid;
	//special material to identify acid blocks
	public static Material acidMat = new MaterialLiquid(MapColor.adobeColor);
	public static Fluid fluidNormalGas;
	public static BlockFluidClassic blockNormalGas;

	public static void init() {
		//init the gas and register block
		fluidNormalGas = new Fluid("normalgas", new ResourceLocation("space:blocks/fluid_normalGas_still"),new ResourceLocation("space:blocks/fluid_normalGas_flow")).setLuminosity(10).setDensity(-1600).setViscosity(100).setGaseous(true);
		FluidRegistry.registerFluid(fluidNormalGas);
		blockNormalGas = (BlockFluidClassic) new BlockFluidClassic(fluidNormalGas, new MaterialLiquid(MapColor.adobeColor)).setUnlocalizedName("normalgas").setCreativeTab(Main.aliensTabBlock);
		GameRegistry.registerBlock(blockNormalGas, blockNormalGas.getUnlocalizedName());
		registerFluidModel(blockNormalGas);
		//init acid and register block
		fluidAcid = new Fluid("normal", new ResourceLocation("space:blocks/fluid_normal_still"),new ResourceLocation("space:blocks/fluid_normal_flow")).setLuminosity(15).setDensity(200).setViscosity(8000);
		FluidRegistry.registerFluid(fluidAcid);
		blockAcid = new AcidBlock(fluidAcid, acidMat);
		GameRegistry.registerBlock(blockAcid, blockAcid.getUnlocalizedName());
		registerFluidModel(blockAcid);

	}
	//used for registering block model of fluids
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
