package com.madmodding.space.blocks.tile;

import com.madmodding.space.EventHandler;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityAlienCell extends TileEntity {
	
	public int cellID = 0;
	public static int worldID = 0;

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		
		compound.getShort("CellID");
		compound.getShort("WorldID");
	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		
		compound.setShort("WorldID", (short)worldID);
        compound.setShort("CellID", (short)cellID);
	}
	
}
