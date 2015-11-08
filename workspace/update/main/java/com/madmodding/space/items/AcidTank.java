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

public class AcidTank extends Item {
	public AcidTank(String name) {
		super();
		this.setUnlocalizedName(name);
		this.setCreativeTab(Main.aliensTabTech);
		this.setMaxStackSize(1);
	}
	@Override
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

	                if (!worldIn.isBlockModifiable(playerIn, blockpos))
	                {
	                    return itemStackIn;
	                }
	                    IBlockState iblockstate = worldIn.getBlockState(blockpos);
	                    Material material = iblockstate.getBlock().getMaterial();

	                    if (material == ModFluids.acidMat && ((Integer)iblockstate.getValue(BlockLiquid.LEVEL)).intValue() == 0)
	                    {
	                        worldIn.setBlockToAir(blockpos);
	                        return new ItemStack(ModItems.acidTankfull);
	                    }          
	                    else
	                    {

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
			                    worldIn.setBlockState(Upos, ModBlocks.acidContainer.getDefaultState());
			                    --itemStackIn.stackSize;
			                }
			                
			        	}
	            }
	        	
	        } 
	           return itemStackIn;
	   }
    }

