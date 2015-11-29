package com.madmodding.space.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class SpaceAir extends Block {
	private static final String __OBFID = "CL_00000190";

	protected SpaceAir() {
		super(Material.air);
	}

	public boolean isAir(IBlockAccess world, BlockPos pos) {
		return true;
	}

	/**
	 * The type of render function that is called for this block
	 */
	public int getRenderType() {
		return -1;
	}

	public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
		return null;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean canCollideCheck(IBlockState state, boolean hitIfLiquid) {
		return false;
	}

	/**
	 * Spawns this Block's drops into the World as EntityItems.
	 * 
	 * @param chance
	 *            The chance that each Item is actually spawned (1.0 = always,
	 *            0.0 = never)
	 * @param fortune
	 *            The player's fortune level
	 */
	public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
	}

	/**
	 * Whether this Block can be replaced directly by other blocks (true for
	 * e.g. tall grass)
	 */
	public boolean isReplaceable(World worldIn, BlockPos pos) {
		return true;
	}
}