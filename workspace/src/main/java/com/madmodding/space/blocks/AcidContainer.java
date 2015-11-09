package com.madmodding.space.blocks;

import java.util.Random;

import com.madmodding.space.blocks.tile.TileEntityAcidContainer;
import com.madmodding.space.items.ModItems;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class AcidContainer extends BlockContainer {

	protected AcidContainer()
	{
		super(Material.rock);
		setUnlocalizedName("acidContainer");
		//setCreativeTab(Main.aliensTabBlock);
	}
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return ModItems.acidTank;
    }
	@Override
	public int getRenderType() {
		return -1;
		
	}
	@Override
    public boolean isOpaqueCube() {
    	return false;
    }
	@Override
	
	public TileEntity createNewTileEntity(World worldIn, int meta) 
	{
		return new TileEntityAcidContainer();
	}

}
