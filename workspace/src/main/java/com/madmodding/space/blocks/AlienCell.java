package com.madmodding.space.blocks;

import com.madmodding.space.TeleportEntity;
import com.madmodding.space.Main;
import com.madmodding.space.blocks.tile.TileEntityAlienCell;
import com.madmodding.space.cell.CellTeleporter;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class AlienCell extends BlockContainer {
	
	public AlienCell() { 
		super(Material.iron);
		this.setCreativeTab(Main.aliensTabTech);
		this.setHardness(12.0F);
		this.setCellBounds();
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		
		TileEntityAlienCell cell = (TileEntityAlienCell) worldIn.getTileEntity(pos);
		
		if(entityIn.ridingEntity == null && entityIn.riddenByEntity == null) {
			if (entityIn instanceof EntityPlayerMP) {
				worldIn.spawnParticle(EnumParticleTypes.SPELL_MOB, entityIn.posX, entityIn.posY, entityIn.posZ, 0, 0, 0, new int[0]);
				((EntityLivingBase) entityIn).addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + entityIn.getName() + " is entering cell " + cell.cellID));
		        ((EntityPlayerMP) entityIn).mcServer.getConfigurationManager().transferPlayerToDimension(((EntityPlayerMP) entityIn), -10, new CellTeleporter(((EntityPlayerMP) entityIn).mcServer.worldServerForDimension(1)));		  
			    ((EntityPlayerMP) entityIn).setPosition(4+((cell.cellID*16)-16), 1, 4+((cell.cellID*16)-16));
				worldIn.spawnParticle(EnumParticleTypes.SPELL_MOB, entityIn.posX, entityIn.posY, entityIn.posZ, 0, 0, 0, new int[0]);
			}else{ TeleportEntity.tpMobToDim(entityIn, -10, worldIn, new BlockPos(4+((cell.cellID*16)-16), 1, 4+((cell.cellID*16)-16))); }}		    
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {

		TileEntityAlienCell cell = (TileEntityAlienCell) worldIn.getTileEntity(pos);
		
		if (playerIn instanceof EntityPlayerMP) {
			worldIn.spawnParticle(EnumParticleTypes.SPELL_MOB, playerIn.posX, playerIn.posY, playerIn.posZ, 0, 0, 0, new int[0]);
		      ((EntityLivingBase) playerIn).addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + playerIn.getName() + " is entering cell " + cell.cellID));
		      ((EntityPlayerMP) playerIn).mcServer.getConfigurationManager().transferPlayerToDimension(((EntityPlayerMP) playerIn), -10, new CellTeleporter(((EntityPlayerMP) playerIn).mcServer.worldServerForDimension(1)));
		      ((EntityPlayerMP) playerIn).setPosition(4+((cell.cellID*16)-16), 1, 4+((cell.cellID*16)-16));
				worldIn.spawnParticle(EnumParticleTypes.SPELL_MOB, playerIn.posX, playerIn.posY, playerIn.posZ, 0, 0, 0, new int[0]);
		      System.out.println("cell id: " + cell.cellID + " posX:" + pos.getX() + " posY:" + pos.getY() + " posZ:" + pos.getZ());
		}

		return super.onBlockActivated(worldIn, pos, state, playerIn, side, hitX, hitY, hitZ);
	}
	
    public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos) {
        this.setCellBounds();
    }
    
    private void setCellBounds() {
        this.setBlockBounds(-1.064F, 0.0F, -0.56F, 1.06F, 2.85F, 1.25F);
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
    	return new TileEntityAlienCell();
    }

}
