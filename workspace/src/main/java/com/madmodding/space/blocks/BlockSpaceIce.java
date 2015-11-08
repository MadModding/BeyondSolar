package com.madmodding.space.blocks;

import java.util.Random;

import com.madmodding.space.Main;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockSpaceIce extends Block {
	private static final String __OBFID = "CL_00000283";

	public BlockSpaceIce() {
		super(Material.packedIce);
		this.slipperiness = 1.10F;
		this.setCreativeTab(Main.aliensTabBlock);
		this.setTickRandomly(true);
	}

	public int tickRate(World worldIn) {

		return 20;
	}

	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		for (int x = -1; x < 2; x++)
			for (int y = -1; y < 2; y++)
				for (int z = -1; z < 2; z++) {
					BlockPos pos2 = new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
					if (worldIn.getBlockState(pos2) != null && (worldIn.getBlockState(pos2).getBlock() == Blocks.water
							|| worldIn.getBlockState(pos2).getBlock() == Blocks.flowing_water)) {
						worldIn.setBlockState(pos2, state);
					}
				}
	}

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	public int quantityDropped(Random random) {
		return 0;
	}
}