package com.madmodding.space.cell;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;
import com.madmodding.space.SpaceTeleporter;

import net.minecraft.block.BlockPortal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.LongHashMap;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class CellTeleporter extends Teleporter {
	
    private final WorldServer worldServerInstance;
    /** A private Random() function in Teleporter */
    private final Random random;
    /** Stores successful portal placement locations for rapid lookup. */
    private final LongHashMap destinationCoordinateCache = new LongHashMap();
    /**
     * A list of valid keys for the destinationCoordainteCache. These are based on the X & Z of the players initial
     * location.
     */
    private final List destinationCoordinateKeys = Lists.newArrayList();

	public CellTeleporter(WorldServer worldIn) {
		super(worldIn);
        this.worldServerInstance = worldIn;
        this.random = new Random(worldIn.getSeed());
	}
	
	public void placeInPortal(Entity entityIn, float rotationYaw) {
		if (this.worldServerInstance.provider.getDimensionId() != 1){
			if (!this.placeInExistingPortal(entityIn, rotationYaw)) { 
				this.makePortal(entityIn);
	            this.placeInExistingPortal(entityIn, rotationYaw);
	        }
		}else{
	            int i = MathHelper.floor_double(entityIn.posX);
	            int j = MathHelper.floor_double(entityIn.posY) - 1;
	            int k = MathHelper.floor_double(entityIn.posZ);
	            byte b0 = 1;
	            byte b1 = 0;

	            for (int l = -2; l <= 2; ++l)
	            {
	                for (int i1 = -2; i1 <= 2; ++i1)
	                {
	                    for (int j1 = -1; j1 < 3; ++j1)
	                    {
	                        int k1 = i + i1 * b0 + l * b1;
	                        int l1 = j + j1;
	                        int i2 = k + i1 * b1 - l * b0;
	                        boolean flag = j1 < 0;
	                        //this.worldServerInstance.setBlockState(new BlockPos(k1, l1, i2), flag ? Blocks.obsidian.getDefaultState() : Blocks.air.getDefaultState());
	                    }
	                }
	            }

	            entityIn.setLocationAndAngles((double)i, (double)j, (double)k, entityIn.rotationYaw, 0.0F);
	           // entityIn.motionX = entityIn.motionY = entityIn.motionZ = 0.0D;
	        }
	    }

	    public boolean placeInExistingPortal(Entity entityIn, float p_180620_2_)
	    {
	        boolean flag = true;
	        double d0 = -1.0D;
	        int i = MathHelper.floor_double(entityIn.posX);
	        int j = MathHelper.floor_double(entityIn.posZ);
	        boolean flag1 = true;
	        Object object = BlockPos.ORIGIN;
	        long k = ChunkCoordIntPair.chunkXZ2Int(i, j);

	        if (this.destinationCoordinateCache.containsItem(k))
	        {
	            SpaceTeleporter.PortalPosition portalposition = (SpaceTeleporter.PortalPosition)this.destinationCoordinateCache.getValueByKey(k);
	            d0 = 0.0D;
	            object = portalposition;
	            portalposition.lastUpdateTime = this.worldServerInstance.getTotalWorldTime();
	            flag1 = false;
	        }
	        else
	        {
	            BlockPos blockpos4 = new BlockPos(entityIn);

	            for (int l = -128; l <= 128; ++l)
	            {
	                BlockPos blockpos1;

	                for (int i1 = -128; i1 <= 128; ++i1)
	                {
	                    for (BlockPos blockpos = blockpos4.add(l, this.worldServerInstance.getActualHeight() - 1 - blockpos4.getY(), i1); blockpos.getY() >= 0; blockpos = blockpos1)
	                    {
	                        blockpos1 = blockpos.down();

	                        if (this.worldServerInstance.getBlockState(blockpos).getBlock() == Blocks.portal)
	                        {
	                            while (this.worldServerInstance.getBlockState(blockpos1 = blockpos.down()).getBlock() == Blocks.portal)
	                            {
	                                blockpos = blockpos1;
	                            }

	                            double d1 = blockpos.distanceSq(blockpos4);

	                            if (d0 < 0.0D || d1 < d0)
	                            {
	                                d0 = d1;
	                                object = blockpos;
	                            }
	                        }
	                    }
	                }
	            }
	        }

	        if (d0 >= 0.0D)
	        {
	            if (flag1)
	            {
	                this.destinationCoordinateCache.add(k, new CellTeleporter.PortalPosition((BlockPos)object, this.worldServerInstance.getTotalWorldTime()));
	                this.destinationCoordinateKeys.add(Long.valueOf(k));
	            }

	            double d4 = (double)((BlockPos)object).getX() + 0.5D;
	            double d5 = (double)((BlockPos)object).getY() + 0.5D;
	            double d6 = (double)((BlockPos)object).getZ() + 0.5D;
	            EnumFacing enumfacing = null;

	            if (this.worldServerInstance.getBlockState(((BlockPos)object).west()).getBlock() == Blocks.portal)
	            {
	                enumfacing = EnumFacing.NORTH;
	            }

	            if (this.worldServerInstance.getBlockState(((BlockPos)object).east()).getBlock() == Blocks.portal)
	            {
	                enumfacing = EnumFacing.SOUTH;
	            }

	            if (this.worldServerInstance.getBlockState(((BlockPos)object).north()).getBlock() == Blocks.portal)
	            {
	                enumfacing = EnumFacing.EAST;
	            }

	            if (this.worldServerInstance.getBlockState(((BlockPos)object).south()).getBlock() == Blocks.portal)
	            {
	                enumfacing = EnumFacing.WEST;
	            }

	            EnumFacing enumfacing1 = EnumFacing.getHorizontal(entityIn.getTeleportDirection());

	            if (enumfacing != null)
	            {
	                EnumFacing enumfacing2 = enumfacing.rotateYCCW();
	                BlockPos blockpos2 = ((BlockPos)object).offset(enumfacing);
	                boolean flag2 = this.func_180265_a(blockpos2);
	                boolean flag3 = this.func_180265_a(blockpos2.offset(enumfacing2));

	                if (flag3 && flag2)
	                {
	                    object = ((BlockPos)object).offset(enumfacing2);
	                    enumfacing = enumfacing.getOpposite();
	                    enumfacing2 = enumfacing2.getOpposite();
	                    BlockPos blockpos3 = ((BlockPos)object).offset(enumfacing);
	                    flag2 = this.func_180265_a(blockpos3);
	                    flag3 = this.func_180265_a(blockpos3.offset(enumfacing2));
	                }

	                float f6 = 0.5F;
	                float f1 = 0.5F;

	                if (!flag3 && flag2)
	                {
	                    f6 = 1.0F;
	                }
	                else if (flag3 && !flag2)
	                {
	                    f6 = 0.0F;
	                }
	                else if (flag3)
	                {
	                    f1 = 0.0F;
	                }

	                d4 = (double)((BlockPos)object).getX() + 0.5D;
	                d5 = (double)((BlockPos)object).getY() + 0.5D;
	                d6 = (double)((BlockPos)object).getZ() + 0.5D;
	                d4 += (double)((float)enumfacing2.getFrontOffsetX() * f6 + (float)enumfacing.getFrontOffsetX() * f1);
	                d6 += (double)((float)enumfacing2.getFrontOffsetZ() * f6 + (float)enumfacing.getFrontOffsetZ() * f1);
	                float f2 = 0.0F;
	                float f3 = 0.0F;
	                float f4 = 0.0F;
	                float f5 = 0.0F;

	                if (enumfacing == enumfacing1)
	                {
	                    f2 = 1.0F;
	                    f3 = 1.0F;
	                }
	                else if (enumfacing == enumfacing1.getOpposite())
	                {
	                    f2 = -1.0F;
	                    f3 = -1.0F;
	                }
	                else if (enumfacing == enumfacing1.rotateY())
	                {
	                    f4 = 1.0F;
	                    f5 = -1.0F;
	                }
	                else
	                {
	                    f4 = -1.0F;
	                    f5 = 1.0F;
	                }

	                double d2 = entityIn.motionX;
	                double d3 = entityIn.motionZ;
	                //entityIn.motionX = d2 * (double)f2 + d3 * (double)f5;
	                //entityIn.motionZ = d2 * (double)f4 + d3 * (double)f3;
	                entityIn.rotationYaw = p_180620_2_ - (float)(enumfacing1.getHorizontalIndex() * 90) + (float)(enumfacing.getHorizontalIndex() * 90);
	            }
	            else
	            {
	                //entityIn.motionX = entityIn.motionY = entityIn.motionZ = 0.0D;
	            }

	            entityIn.setLocationAndAngles(d4, d5, d6, entityIn.rotationYaw, entityIn.rotationPitch);
	            return true;
	        }
	        else
	        {
	            return false;
	        }
	    }

	    private boolean func_180265_a(BlockPos p_180265_1_)
	    {
	        return !this.worldServerInstance.isAirBlock(p_180265_1_) || !this.worldServerInstance.isAirBlock(p_180265_1_.up());
	    }

	    public boolean makePortal(Entity p_85188_1_)
	    {
	        byte b0 = 16;
	        double d0 = -1.0D;
	        int i = MathHelper.floor_double(p_85188_1_.posX);
	        int j = MathHelper.floor_double(p_85188_1_.posY);
	        int k = MathHelper.floor_double(p_85188_1_.posZ);
	        int l = i;
	        int i1 = j;
	        int j1 = k;
	        int k1 = 0;
	        int l1 = this.random.nextInt(4);
	        int i2;
	        double d1;
	        int k2;
	        double d2;
	        int i3;
	        int j3;
	        int k3;
	        int l3;
	        int i4;
	        int j4;
	        int k4;
	        int l4;
	        int i5;
	        double d3;
	        double d4;

	        for (i2 = i - b0; i2 <= i + b0; ++i2)
	        {
	            d1 = (double)i2 + 0.5D - p_85188_1_.posX;

	            for (k2 = k - b0; k2 <= k + b0; ++k2)
	            {
	                d2 = (double)k2 + 0.5D - p_85188_1_.posZ;
	                label271:

	                for (i3 = this.worldServerInstance.getActualHeight() - 1; i3 >= 0; --i3)
	                {
	                    if (this.worldServerInstance.isAirBlock(new BlockPos(i2, i3, k2)))
	                    {
	                        while (i3 > 0 && this.worldServerInstance.isAirBlock(new BlockPos(i2, i3 - 1, k2)))
	                        {
	                            --i3;
	                        }

	                        for (j3 = l1; j3 < l1 + 4; ++j3)
	                        {
	                            k3 = j3 % 2;
	                            l3 = 1 - k3;

	                            if (j3 % 4 >= 2)
	                            {
	                                k3 = -k3;
	                                l3 = -l3;
	                            }

	                            for (i4 = 0; i4 < 3; ++i4)
	                            {
	                                for (j4 = 0; j4 < 4; ++j4)
	                                {
	                                    for (k4 = -1; k4 < 4; ++k4)
	                                    {
	                                        l4 = i2 + (j4 - 1) * k3 + i4 * l3;
	                                        i5 = i3 + k4;
	                                        int j5 = k2 + (j4 - 1) * l3 - i4 * k3;

	                                        if (k4 < 0 && !this.worldServerInstance.getBlockState(new BlockPos(l4, i5, j5)).getBlock().getMaterial().isSolid() || k4 >= 0 && !this.worldServerInstance.isAirBlock(new BlockPos(l4, i5, j5)))
	                                        {
	                                            continue label271;
	                                        }
	                                    }
	                                }
	                            }

	                            d3 = (double)i3 + 0.5D - p_85188_1_.posY;
	                            d4 = d1 * d1 + d3 * d3 + d2 * d2;

	                            if (d0 < 0.0D || d4 < d0)
	                            {
	                                d0 = d4;
	                                l = i2;
	                                i1 = i3;
	                                j1 = k2;
	                                k1 = j3 % 4;
	                            }
	                        }
	                    }
	                }
	            }
	        }

	        if (d0 < 0.0D)
	        {
	            for (i2 = i - b0; i2 <= i + b0; ++i2)
	            {
	                d1 = (double)i2 + 0.5D - p_85188_1_.posX;

	                for (k2 = k - b0; k2 <= k + b0; ++k2)
	                {
	                    d2 = (double)k2 + 0.5D - p_85188_1_.posZ;
	                    label219:

	                    for (i3 = this.worldServerInstance.getActualHeight() - 1; i3 >= 0; --i3)
	                    {
	                        if (this.worldServerInstance.isAirBlock(new BlockPos(i2, i3, k2)))
	                        {
	                            while (i3 > 0 && this.worldServerInstance.isAirBlock(new BlockPos(i2, i3 - 1, k2)))
	                            {
	                                --i3;
	                            }

	                            for (j3 = l1; j3 < l1 + 2; ++j3)
	                            {
	                                k3 = j3 % 2;
	                                l3 = 1 - k3;

	                                for (i4 = 0; i4 < 4; ++i4)
	                                {
	                                    for (j4 = -1; j4 < 4; ++j4)
	                                    {
	                                        k4 = i2 + (i4 - 1) * k3;
	                                        l4 = i3 + j4;
	                                        i5 = k2 + (i4 - 1) * l3;

	                                        if (j4 < 0 && !this.worldServerInstance.getBlockState(new BlockPos(k4, l4, i5)).getBlock().getMaterial().isSolid() || j4 >= 0 && !this.worldServerInstance.isAirBlock(new BlockPos(k4, l4, i5)))
	                                        {
	                                            continue label219;
	                                        }
	                                    }
	                                }

	                                d3 = (double)i3 + 0.5D - p_85188_1_.posY;
	                                d4 = d1 * d1 + d3 * d3 + d2 * d2;

	                                if (d0 < 0.0D || d4 < d0)
	                                {
	                                    d0 = d4;
	                                    l = i2;
	                                    i1 = i3;
	                                    j1 = k2;
	                                    k1 = j3 % 2;
	                                }
	                            }
	                        }
	                    }
	                }
	            }
	        }

	        int k5 = l;
	        int j2 = i1;
	        k2 = j1;
	        int l5 = k1 % 2;
	        int l2 = 1 - l5;

	        if (k1 % 4 >= 2)
	        {
	            l5 = -l5;
	            l2 = -l2;
	        }

	        if (d0 < 0.0D)
	        {
	            i1 = MathHelper.clamp_int(i1, 70, this.worldServerInstance.getActualHeight() - 10);
	            j2 = i1;

	            for (i3 = -1; i3 <= 1; ++i3)
	            {
	                for (j3 = 1; j3 < 3; ++j3)
	                {
	                    for (k3 = -1; k3 < 3; ++k3)
	                    {
	                        l3 = k5 + (j3 - 1) * l5 + i3 * l2;
	                        i4 = j2 + k3;
	                        j4 = k2 + (j3 - 1) * l2 - i3 * l5;
	                        boolean flag = k3 < 0;
	                        //this.worldServerInstance.setBlockState(new BlockPos(l3, i4, j4), flag ? Blocks.obsidian.getDefaultState() : Blocks.air.getDefaultState());
	                    }
	                }
	            }
	        }

	        IBlockState iblockstate = Blocks.portal.getDefaultState().withProperty(BlockPortal.AXIS, l5 != 0 ? EnumFacing.Axis.X : EnumFacing.Axis.Z);

	        for (j3 = 0; j3 < 4; ++j3)
	        {
	            for (k3 = 0; k3 < 4; ++k3)
	            {
	                for (l3 = -1; l3 < 4; ++l3)
	                {
	                    i4 = k5 + (k3 - 1) * l5;
	                    j4 = j2 + l3;
	                    k4 = k2 + (k3 - 1) * l2;
	                    boolean flag1 = k3 == 0 || k3 == 3 || l3 == -1 || l3 == 3;
	                    //this.worldServerInstance.setBlockState(new BlockPos(i4, j4, k4), flag1 ? Blocks.obsidian.getDefaultState() : iblockstate, 2);
	                }
	            }

	            for (k3 = 0; k3 < 4; ++k3)
	            {
	                for (l3 = -1; l3 < 4; ++l3)
	                {
	                    i4 = k5 + (k3 - 1) * l5;
	                    j4 = j2 + l3;
	                    k4 = k2 + (k3 - 1) * l2;
	                    this.worldServerInstance.notifyNeighborsOfStateChange(new BlockPos(i4, j4, k4), this.worldServerInstance.getBlockState(new BlockPos(i4, j4, k4)).getBlock());
	                }
	            }
	        }

	        return true;
	    }

	    /**
	     * called periodically to remove out-of-date portal locations from the cache list. Argument par1 is a
	     * WorldServer.getTotalWorldTime() value.
	     */
	    public void removeStalePortalLocations(long p_85189_1_)
	    {
	        if (p_85189_1_ % 100L == 0L)
	        {
	            Iterator iterator = this.destinationCoordinateKeys.iterator();
	            long j = p_85189_1_ - 600L;

	            while (iterator.hasNext())
	            {
	                Long olong = (Long)iterator.next();
	                CellTeleporter.PortalPosition portalposition = (CellTeleporter.PortalPosition)this.destinationCoordinateCache.getValueByKey(olong.longValue());

	                if (portalposition == null || portalposition.lastUpdateTime < j)
	                {
	                    iterator.remove();
	                    this.destinationCoordinateCache.remove(olong.longValue());
	                }
	            }
	        }
	    }

	    public class PortalPosition extends BlockPos
	    {
	        /** The worldtime at which this PortalPosition was last verified */
	        public long lastUpdateTime;
	        private static final String __OBFID = "CL_00000154";

	        public PortalPosition(BlockPos pos, long p_i45747_3_)
	        {
	            super(pos.getX(), pos.getY(), pos.getZ());
	            this.lastUpdateTime = p_i45747_3_;
	        }
	    }

}
