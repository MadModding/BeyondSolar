package com.madmodding.space.blocks;

import com.madmodding.space.Main;
import com.madmodding.space.blocks.tile.TileEntityAlienEgg;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class AlienEgg extends BlockContainer {
	
	public AlienEgg(Material mat) { 
		super(mat);
		this.setCreativeTab(Main.aliensTabBlock);
	}
	
	public int getRenderType() {
		return -1;
	}

    public boolean isOpaqueCube() {
    	return false;
    }
    
    public boolean renderAsNormalBlock() {
    	return false;
    }
    
    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
    	return new TileEntityAlienEgg();
    }
}
