package com.madmodding.space.blocks;

import java.util.Random;

import com.madmodding.space.Main;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidFinite;
import net.minecraftforge.fluids.Fluid;

public class AcidBlock extends BlockFluidFinite {

	public AcidBlock(Fluid fluid, Material material) {
		super(fluid, material);
		String fluidName = getFluid().getUnlocalizedName();
		setUnlocalizedName(fluidName);
		setCreativeTab(Main.aliensTabBlock);
	}
    
	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {

				DamageSource burn=new DamageSource(fluidName);
				entityIn.attackEntityFrom(burn, 1.5F);
			    
		}
	/*
     * Called randomly when setTickRandomly is set to true (used by e.g. crops to grow, etc.)
     */
	@Override
    public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random random)
    {
		IBlockState iblockstate = worldIn.getBlockState(pos.down());
        if ((iblockstate.getBlock().getBlockHardness(worldIn, pos.down())>0F))
        {
    		System.out.println(" AcidFluid Debug Block Destroyed "+iblockstate.getBlock().getUnlocalizedName()+" x:"+pos.getX()+" "+" y:"+(pos.getY()-1)+" z:"+pos.getZ());
            worldIn.setBlockToAir(pos.down());
        }    
		
    }
}
