package com.madmodding.space.blocks;

import com.madmodding.space.Main;
import com.madmodding.space.blocks.tile.TileEntityAlienCell;
import com.madmodding.space.cell.CellTeleporter;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class TpBlock extends Block{

	public TpBlock() {
		super(Material.iron);
		setBlockUnbreakable();
		setUnlocalizedName("TpBlock");
		setCreativeTab(Main.aliensTabBlock);
	}
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {

		int cellid=(pos.getX()-8)/16;
		BlockPos over=TileEntityAlienCell.loc.get(cellid);
		int x=over.getX()+4;
		int y=over.getY()+3;
		int z=over.getZ()+4;
		System.out.println(y);
		if (playerIn instanceof EntityPlayerMP) {
			worldIn.spawnParticle(EnumParticleTypes.SPELL_MOB, playerIn.posX, playerIn.posY, playerIn.posZ, 0, 0, 0, new int[0]);
		      ((EntityPlayerMP) playerIn).mcServer.getConfigurationManager().transferPlayerToDimension(((EntityPlayerMP) playerIn), 0, new CellTeleporter(((EntityPlayerMP) playerIn).mcServer.worldServerForDimension(-10)));
		      ((EntityPlayerMP) playerIn).setPositionAndUpdate(x, y, z);
		}
		return super.onBlockActivated(worldIn, pos, state, playerIn, side, hitX, hitY, hitZ);
	}

}
