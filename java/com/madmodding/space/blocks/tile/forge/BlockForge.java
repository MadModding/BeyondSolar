package com.madmodding.space.blocks.tile.forge;

import com.madmodding.space.Main;
import com.madmodding.space.gui.ModGuis;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockForge extends BlockContainer {
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

	public BlockForge(String unlocalizedName) {
		super(Material.iron);
		this.setUnlocalizedName(unlocalizedName);
		this.setHardness(2.0f);
		this.setResistance(6.0f);
		this.setHarvestLevel("pickaxe", 2);
		this.setCreativeTab(Main.aliensTabTech);
		this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		if (stack.hasDisplayName()) {
			TileEntity tileentity = worldIn.getTileEntity(pos);

			if (tileentity instanceof TileEntityForge) {
				((TileEntityForge) tileentity).setCustomName(stack.getDisplayName());
			}
		}
	}
	public boolean isFullCube() {
		return false;
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState blockstate) {
		TileEntityForge te = (TileEntityForge) world.getTileEntity(pos);
		InventoryHelper.dropInventoryItems(world, pos, te);
		super.breakBlock(world, pos, blockstate);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityForge();
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side,
			float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			player.openGui(Main.instance, ModGuis.MOD_TILE_ENTITY_GUI, world, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}

	@Override
	public int getRenderType() {
		return -1;
	}
}
