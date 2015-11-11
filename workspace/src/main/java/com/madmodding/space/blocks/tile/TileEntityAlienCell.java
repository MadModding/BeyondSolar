package com.madmodding.space.blocks.tile;

import java.util.ArrayList;
import java.util.List;

import com.madmodding.space.EventHandler;

import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;

public class TileEntityAlienCell extends TileEntity implements ITickable {
	private BlockPos orgin=new BlockPos(0,0,0);
	private boolean init=true;
	public int cellID = 0;
	public static int worldID = 0;
	public static List<BlockPos> loc=new ArrayList<BlockPos>();
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		cellID=compound.getInteger("CellID");
		worldID=compound.getInteger("WorldID");
		if(init)
		{
			for(int i=0;i<(worldID);i++)
			{
				loc.add(orgin);
			}
			init=false;
		}
		loc.set(cellID, getPos());
	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		
		compound.setInteger("WorldID",worldID);
        compound.setInteger("CellID", cellID);
        
	}

	@Override
	public void tick() {
		
	}	
}
