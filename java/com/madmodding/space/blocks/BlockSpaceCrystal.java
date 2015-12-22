package com.madmodding.space.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.madmodding.space.Main;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockSpaceCrystal extends Block {
	private static final String __OBFID = "CL_00000283";

	public BlockSpaceCrystal() {
		super(Material.rock);
		this.setCreativeTab(Main.aliensTabBlock);
		this.setTickRandomly(true);
	}

	public int tickRate(World worldIn) {
		if (worldIn.provider.getDimensionId() == 71)
			return 10;
		else
			return 1000;
	}

	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		List coords = new ArrayList();
		for (int x = -1; x < 2; x++)
			for (int y = -1; y < 2; y++)
				for (int z = -1; z < 2; z++) {
					BlockPos pos2 = new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
					if (worldIn.getBlockState(pos2).getBlock() != ModBlocks.crystal
							&& worldIn.getBlockState(pos2).getBlock() != ModBlocks.crystalhard)
						coords.add(new int[] { x, y, z });
				}
		if (coords.size() > 0) {
			boolean hardened = rand.nextInt(6) == 0;
			if (!hardened) {
				worldIn.setBlockState(new BlockPos(pos.getX() + rand.nextInt(3) - 1, pos.getY() + rand.nextInt(3) - 1,
						pos.getZ() + rand.nextInt(3) - 1), ModBlocks.crystalhard.getDefaultState());
				worldIn.setBlockState(new BlockPos(pos.getX() + rand.nextInt(3) - 1, pos.getY() + rand.nextInt(3) - 1,
						pos.getZ() + rand.nextInt(3) - 1), ModBlocks.crystalhard.getDefaultState());
				worldIn.setBlockState(new BlockPos(pos.getX() + rand.nextInt(3) - 1, pos.getY() + rand.nextInt(3) - 1,
						pos.getZ() + rand.nextInt(3) - 1), ModBlocks.crystalhard.getDefaultState());
				worldIn.setBlockState(pos, ModBlocks.crystal.getDefaultState());
			} else {
				int i = rand.nextInt(coords.size());
				BlockPos pos2 = new BlockPos(pos.getX() + ((int[]) coords.get(i))[0],
						pos.getY() + ((int[]) coords.get(i))[1], pos.getZ() + ((int[]) coords.get(i))[2]);
				worldIn.setBlockState(pos2, state);
				worldIn.setBlockState(pos, ModBlocks.crystalhard.getDefaultState());
				worldIn.forceBlockUpdateTick(this, pos2, rand);
			}
		} else {
			worldIn.setBlockState(new BlockPos(pos.getX() + rand.nextInt(3) - 1, pos.getY() + rand.nextInt(3) - 1,
					pos.getZ() + rand.nextInt(3) - 1), ModBlocks.crystal.getDefaultState());
			worldIn.setBlockState(pos, ModBlocks.crystalhard.getDefaultState());
		}
	}

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	public int quantityDropped(Random random) {
		return 1;
	}
}