package com.madmodding.space.items;

import com.madmodding.space.Main;
import com.madmodding.space.blocks.ModBlocks;
import com.madmodding.space.blocks.ModFluids;
import com.madmodding.space.blocks.tile.TileEntityAcidContainer;
import com.madmodding.space.entity.item.EntityFireAttack;
import com.madmodding.space.entity.item.EntityIce;

import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.stats.StatList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class AcidTankfull extends Item {
	public AcidTankfull(String name) {
		super();
		this.setUnlocalizedName(name);
		this.setCreativeTab(Main.aliensTabTech);
		this.setMaxStackSize(1);
	}
public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
    	
		MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(worldIn, playerIn, true);

	        if (movingobjectposition == null)
	        {
	            return itemStackIn;
	        }
	        else
	        {
	            
	            if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
	            {
	                BlockPos blockpos = movingobjectposition.getBlockPos();
	                    	int i = movingobjectposition.getBlockPos().getX();
			                int j = movingobjectposition.getBlockPos().getY();
			                int k = movingobjectposition.getBlockPos().getZ();
		
			                switch (movingobjectposition.sideHit)
			                {
			                    case DOWN:
			                        --j;
			                        break;
			                    case UP:
			                        ++j;
			                        break;
			                    case NORTH:
			                        --k;
			                        break;
			                    case SOUTH:
			                        ++k;
			                        break;
			                    case WEST:
			                        --i;
			                        break;
			                    case EAST:
			                        ++i;
			                }
			                BlockPos Upos = new BlockPos(i,j,k);
			                if (worldIn.isAirBlock(Upos))
			                {
			                	if(!playerIn.isSneaking())
			                	{
			                		worldIn.setBlockState(Upos, ModFluids.blockNormal.getDefaultState());
			         	           	return new ItemStack(ModItems.acidTank);

			                	}
			                	else
			                	{
			                		worldIn.setBlockState(Upos, ModBlocks.acidFull.getDefaultState());
			         	           	itemStackIn.stackSize--;
			         	           return  itemStackIn;
			                	}
			                }
			                
			        	}
	            }
			return itemStackIn;	        	
	   }
}

