package com.madmodding.space;

import com.madmodding.space.blocks.tile.TileEntityAlienCell;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

/**
 * @author DaryBob
 *
 */
public class TeleportEntity {
	
    public static int dimension;
    
    public static void tpMobToDim(Entity entity0, int dimensionId, World worldObj, BlockPos pos) {
    	if(!worldObj.isRemote && !entity0.isDead) {
    		
    		 MinecraftServer minecraftserver = MinecraftServer.getServer();
             int j = dimension;
             WorldServer worldserver = minecraftserver.worldServerForDimension(j);
             WorldServer worldserver1 = minecraftserver.worldServerForDimension(dimensionId);
             dimension = dimensionId;

             if (j == 1 && dimensionId == 1) {
                 worldserver1 = minecraftserver.worldServerForDimension(0);
                 dimension = 0;
             }
             
             worldObj.removeEntity(entity0);
             entity0.isDead = false;
             
             net.minecraft.world.WorldProvider pOld = worldserver.provider;
             net.minecraft.world.WorldProvider pNew = worldserver1.provider;
             double moveFactor = pOld.getMovementFactor() / pNew.getMovementFactor();
             double d0 = entity0.posX * moveFactor;
             double d1 = entity0.posZ * moveFactor;
             double d2 = 8.0D;
             float f = entity0.rotationYaw;

             entity0.setWorld(worldserver1);
             
             Entity entity = EntityList.createEntityByName(EntityList.getEntityString(entity0), worldserver1);

             if (entity != null)
             {
                 entity.copyDataFromOld(entity0);

                 if (j == 1 && dimensionId == 1)
                 {
                     BlockPos blockpos = worldObj.getTopSolidOrLiquidBlock(worldserver1.getSpawnPoint());
                     entity.moveToBlockPosAndAngles(blockpos, entity.rotationYaw, entity.rotationPitch);
                 }

                 worldserver1.spawnEntityInWorld(entity);
                 entity.setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());
                 System.out.println("TeleportEntity Debug:"+"Entity: "+entity.getName()+" at "+pos.getX()+" "+pos.getY()+" "+pos.getZ());
             }
             
             entity0.isDead = true;
             worldserver.resetUpdateEntityTick();
             worldserver1.resetUpdateEntityTick();
    		
    	}
    }

}
