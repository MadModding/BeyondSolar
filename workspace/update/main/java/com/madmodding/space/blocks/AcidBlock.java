package com.madmodding.space.blocks;

import java.util.Random;

import com.madmodding.space.Main;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class AcidBlock extends BlockFluidClassic {

	public AcidBlock(Fluid fluid, Material material) {
		super(fluid, material);
		String fluidName = getFluid().getUnlocalizedName();
		setUnlocalizedName(fluidName);
		setCreativeTab(Main.aliensTabBlock);
	}
    /**
     * Called randomly when setTickRandomly is set to true (used by e.g. crops to grow, etc.)
     */
	@Override
    public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random random)
    {
		System.out.println("AcidFluid Debug x: "+pos.getX());
		System.out.println("AcidFluid Debug z: "+pos.getZ());
		IBlockState iblockstate = worldIn.getBlockState(pos.down());
        Material material = iblockstate.getBlock().getMaterial();
        if (!(material == ModFluids.acidMat))
        {
            worldIn.setBlockToAir(pos.down());
        }    
		
    }
}
