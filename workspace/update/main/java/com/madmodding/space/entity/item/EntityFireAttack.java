package com.madmodding.space.entity.item;

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

public class EntityFireAttack extends EntityThrowable {
	
	EntityLivingBase shootingEntity;
    private int explosionRadius = 3;

	public EntityFireAttack(World w) {
		super(w);
	}
	
	public EntityFireAttack(World w, EntityLivingBase l) {
		super(w, l);
	}
	
    public EntityFireAttack(World w, double x, double y, double z) {
        super(w, x, y, z);
    }

	@Override
	protected void onImpact(MovingObjectPosition movingObject) {

        if (!this.worldObj.isRemote) {
            boolean flag;

            if (movingObject.entityHit != null) {
                flag = movingObject.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, shootingEntity), 5.0F);

                if (flag) {
                    //this.(shootingEntity, movingObject.entityHit);

                    if (!movingObject.entityHit.isImmuneToFire()) {
                        movingObject.entityHit.setFire(5);
                    }
                }
            }else{
                flag = true;

                if (shootingEntity != null && shootingEntity instanceof EntityLiving) {
                    flag = this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");
                }
            }

            this.setDead();
        }
	}

}
