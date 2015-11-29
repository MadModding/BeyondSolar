package com.madmodding.space;

import java.util.Random;

import com.madmodding.space.blocks.ModBlocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockHelper;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenerator implements IWorldGenerator {
	public static final String SPACE_BOX = "spaceLootBox";
	public static final IBlockState[] oretypes = new IBlockState[] { ModBlocks.ferrousOre.getDefaultState(),
			ModBlocks.ferrousOre.getDefaultState(), ModBlocks.ferrousOre.getDefaultState(),
			ModBlocks.ferrousOre.getDefaultState(), ModBlocks.ferrousOre.getDefaultState(),
			ModBlocks.platinumMeteorite.getDefaultState(), ModBlocks.rareMetalMeteorite.getDefaultState(),
			ModBlocks.ice.getDefaultState() };

	public WorldGenerator() {

	}

	@Override
	public void generate(Random RNG, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator,
			IChunkProvider chunkProvider) {

		if (world.provider.getDimensionId() == 71) {
			generateSpace(world, RNG, chunkX * 16, chunkZ * 16);
		}

	}

	private void generateSpace(World world, Random rand, int chunkX, int chunkZ) {

		for (int k = 0; k < 3; k++) {
			int firstBlockXCoord = chunkX + rand.nextInt(16);
			int firstBlockYCoord = rand.nextInt(256);
			int firstBlockZCoord = chunkZ + rand.nextInt(16);

			(new WorldGenMinable(oretypes[rand.nextInt(oretypes.length)], 64, BlockHelper.forBlock(Blocks.air)))
					.generate(world, rand, new BlockPos(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord));
		}
	}
}
