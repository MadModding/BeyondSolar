package com.madmodding.space.gui;

import com.madmodding.space.blocks.tile.forge.ContainerForge;
import com.madmodding.space.blocks.tile.forge.GuiForge;
import com.madmodding.space.blocks.tile.forge.TileEntityForge;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class ModGuis implements IGuiHandler {

	public static final int MOD_TILE_ENTITY_GUI = 0;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == MOD_TILE_ENTITY_GUI)
			return new ContainerForge(player.inventory, (TileEntityForge) world.getTileEntity(new BlockPos(x, y, z)));

		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == MOD_TILE_ENTITY_GUI)
			return new GuiForge(player.inventory, (TileEntityForge) world.getTileEntity(new BlockPos(x, y, z)));

		return null;
	}
}
