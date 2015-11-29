package com.madmodding.space.entity.item;

import com.madmodding.space.blocks.ModFluids;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityAcidAttack extends EntityThrowable {
	
	public EntityAcidAttack(World worldIn) {
		super(worldIn);
	}
	public EntityAcidAttack(World w, EntityLivingBase l) {
		super(w, l);
	}
	
    public EntityAcidAttack(World w, double x, double y, double z) {
        super(w, x, y, z);
    }
	
	@Override
	protected void onImpact(MovingObjectPosition mo) {

        if (!this.worldObj.isRemote) 
        {
           if(mo.getBlockPos()!=null)
           {
        	   this.worldObj.setBlockState(mo.getBlockPos(), ModFluids.blockAcid.getDefaultState());
           }
           if(mo.entityHit!=null)
           {
        	   this.worldObj.setBlockState(mo.entityHit.getPosition(), ModFluids.blockAcid.getDefaultState());
           }
           this.setDead();
        }
	}

}
