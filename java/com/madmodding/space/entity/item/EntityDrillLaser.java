package com.madmodding.space.entity.item;

import java.util.ArrayList;
import java.util.List;

import com.madmodding.space.items.element.ItemDrill;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityDrillLaser extends Entity implements IProjectile {
	private int xTile = -1;
	private int yTile = -1;
	private int zTile = -1;
	private Block inTile;
	private int inData;
	private boolean inGround;
	/** 1 if the player can pick up the arrow */
	public int canBePickedUp;
	/** Seems to be some sort of timer for animating an arrow. */
	public int arrowShake;
	/** The owner of this arrow. */
	public Entity shootingEntity;
	private int ticksInGround;
	private int ticksInAir;
	private double damage = 2.0D;
	/** The amount of knockback an arrow applies when it hits a mob. */
	private int knockbackStrength;
	private static final String __OBFID = "CL_00001715";

	// Code from TC
	protected void breakExtraBlock(World world, BlockPos pos, int sidehit, EntityPlayer playerEntity,
			IBlockState state) {
		if (world.isAirBlock(pos))
			return;
		if (!(playerEntity instanceof EntityPlayerMP))
			return;
		EntityPlayerMP player = (EntityPlayerMP) playerEntity;
		Block block = world.getBlockState(pos).getBlock();
		int event = ForgeHooks.onBlockBreakEvent(world, player.theItemInWorldManager.getGameType(), player, pos);
		if (player.capabilities.isCreativeMode) {
			block.onBlockHarvested(world, pos, state, player);
			if (block.removedByPlayer(world, pos, player, false))
				block.onBlockDestroyedByPlayer(world, pos, state);

			if (!world.isRemote) {
				player.playerNetServerHandler.sendPacket(new S23PacketBlockChange(world, pos));
			}
			return;
		}
		if (!world.isRemote) {
			block.onBlockHarvested(world, pos, state, player);
			if (block.removedByPlayer(world, pos, player, true)) {
				block.onBlockDestroyedByPlayer(world, pos, state);
				block.harvestBlock(world, player, pos, state, worldObj.getTileEntity(pos));
				block.dropXpOnBlockBreak(world, pos, event);
			}
			player.playerNetServerHandler.sendPacket(new S23PacketBlockChange(world, pos));
		} else {
			world.playAuxSFX(2001, pos, Block.getIdFromBlock(block));
			if (block.removedByPlayer(world, pos, player, true)) {
				block.onBlockDestroyedByPlayer(world, pos, state);
			}
			Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C07PacketPlayerDigging());
		}
	}

	public EntityDrillLaser(World worldIn) {
		super(worldIn);
		this.renderDistanceWeight = 10.0D;
		this.setSize(1.5F, 1.5F);
	}

	public EntityDrillLaser(World worldIn, double x, double y, double z) {
		super(worldIn);
		this.renderDistanceWeight = 10.0D;
		this.setSize(1.5F, 1.5F);
		this.setPosition(x, y, z);
	}

	public EntityDrillLaser(World worldIn, EntityLivingBase shooter, EntityLivingBase p_i1755_3_, float p_i1755_4_,
			float p_i1755_5_) {
		super(worldIn);
		this.renderDistanceWeight = 10.0D;
		this.shootingEntity = shooter;

		if (shooter instanceof EntityPlayer) {
			this.canBePickedUp = 1;
		}

		this.posY = shooter.posY + (double) shooter.getEyeHeight() - 0.10000000149011612D;
		double d0 = p_i1755_3_.posX - shooter.posX;
		double d1 = p_i1755_3_.getEntityBoundingBox().minY + (double) (p_i1755_3_.height / 3.0F) - this.posY;
		double d2 = p_i1755_3_.posZ - shooter.posZ;
		double d3 = (double) MathHelper.sqrt_double(d0 * d0 + d2 * d2);

		if (d3 >= 1.0E-7D) {
			float f2 = (float) (Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
			float f3 = (float) (-(Math.atan2(d1, d3) * 180.0D / Math.PI));
			double d4 = d0 / d3;
			double d5 = d2 / d3;
			this.setLocationAndAngles(shooter.posX + d4, this.posY, shooter.posZ + d5, f2, f3);
			float f4 = (float) (d3 * 0.20000000298023224D);
			this.setThrowableHeading(d0, d1 + (double) f4, d2, p_i1755_4_, p_i1755_5_);
		}
	}

	public EntityDrillLaser(World worldIn, EntityLivingBase shooter, float p_i1756_3_) {
		super(worldIn);
		this.renderDistanceWeight = 10.0D;
		this.shootingEntity = shooter;

		if (shooter instanceof EntityPlayer) {
			this.canBePickedUp = 1;
		}

		this.setSize(1.5F, 1.5F);
		this.setLocationAndAngles(shooter.posX, shooter.posY + (double) shooter.getEyeHeight(), shooter.posZ,
				shooter.rotationYaw, shooter.rotationPitch);
		this.posX -= (double) (MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F);
		this.posY -= 0.10000000149011612D;
		this.posZ -= (double) (MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F);
		this.setPosition(this.posX, this.posY, this.posZ);
		this.motionX = (double) (-MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI)
				* MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI));
		this.motionZ = (double) (MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI)
				* MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI));
		this.motionY = (double) (-MathHelper.sin(this.rotationPitch / 180.0F * (float) Math.PI));
		this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, p_i1756_3_ * 1.5F, 1.0F);
	}

	protected void entityInit() {
		this.dataWatcher.addObject(16, Byte.valueOf((byte) 0));
	}

	/**
	 * Similar to setArrowHeading, it's point the throwable entity to a x, y, z
	 * direction.
	 * 
	 * @param inaccuracy
	 *            Higher means more error.
	 */
	public void setThrowableHeading(double x, double y, double z, float velocity, float inaccuracy) {
		float f2 = MathHelper.sqrt_double(x * x + y * y + z * z);
		x /= (double) f2;
		y /= (double) f2;
		z /= (double) f2;
		x += this.rand.nextGaussian() * (double) (this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D
				* (double) inaccuracy;
		y += this.rand.nextGaussian() * (double) (this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D
				* (double) inaccuracy;
		z += this.rand.nextGaussian() * (double) (this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D
				* (double) inaccuracy;
		x *= (double) velocity;
		y *= (double) velocity;
		z *= (double) velocity;
		this.motionX = x;
		this.motionY = y;
		this.motionZ = z;
		float f3 = MathHelper.sqrt_double(x * x + z * z);
		this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(x, z) * 180.0D / Math.PI);
		this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(y, (double) f3) * 180.0D / Math.PI);
		this.ticksInGround = 0;
	}

	@SideOnly(Side.CLIENT)
	public void func_180426_a(double p_180426_1_, double p_180426_3_, double p_180426_5_, float p_180426_7_,
			float p_180426_8_, int p_180426_9_, boolean p_180426_10_) {
		this.setPosition(p_180426_1_, p_180426_3_, p_180426_5_);
		this.setRotation(p_180426_7_, p_180426_8_);
	}

	/**
	 * Sets the velocity to the args. Args: x, y, z
	 */
	@SideOnly(Side.CLIENT)
	public void setVelocity(double x, double y, double z) {
		this.motionX = x;
		this.motionY = y;
		this.motionZ = z;

		if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
			float f = MathHelper.sqrt_double(x * x + z * z);
			this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(x, z) * 180.0D / Math.PI);
			this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(y, (double) f) * 180.0D / Math.PI);
			this.prevRotationPitch = this.rotationPitch;
			this.prevRotationYaw = this.rotationYaw;
			this.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
			this.ticksInGround = 0;
		}
	}

	public void setDead() {
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	public void onUpdate() {
		super.onUpdate();
		if (!(this.shootingEntity instanceof EntityPlayer && ((EntityPlayer) shootingEntity).isUsingItem()
				&& ((EntityPlayer) shootingEntity).getItemInUse().getItem() instanceof ItemDrill)) {
			isDead = true;
		}
		((EntityPlayer) shootingEntity).addPotionEffect(new PotionEffect(Potion.damageBoost.getId(), 5, 1));
		if (inGround && !((EntityPlayer) shootingEntity).isSneaking()) {
			motionX = 0.25 * (double) (-MathHelper.sin(shootingEntity.rotationYaw / 180.0F * (float) Math.PI)
					* MathHelper.cos(shootingEntity.rotationPitch / 180.0F * (float) Math.PI));
			motionY = 0.25 * (double) (-MathHelper.sin(shootingEntity.rotationPitch / 180.0F * (float) Math.PI));
			motionZ = 0.25 * (double) (MathHelper.cos(shootingEntity.rotationYaw / 180.0F * (float) Math.PI)
					* MathHelper.cos(shootingEntity.rotationPitch / 180.0F * (float) Math.PI));
			this.posX += this.motionX;
			this.posY += this.motionY;
			this.posZ += this.motionZ;
			breakExtraBlock(worldObj, new BlockPos(posX, posY, posZ), 0, (EntityPlayer) shootingEntity,
					worldObj.getBlockState(new BlockPos(posX, posY, posZ)));
		}
		{// Spawns Primary Particles
			double x1 = this.shootingEntity.posX;
			double y1 = this.shootingEntity.posY + this.shootingEntity.getEyeHeight();
			double z1 = this.shootingEntity.posZ;
			y1 += 0.5;
			double x2 = this.posX;
			double y2 = this.posY;
			double z2 = this.posZ;
			double distance = (Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2) + Math.pow(z1 - z2, 2)));
			double wide = 2;
			List sec = new ArrayList();
			int c = (int) (distance * wide);
			sec.add(new double[] { x1, y1, z1 });
			for (int d = 0; d < c; d++) {
				if (c < 64 || worldObj.getTotalWorldTime() % (c - 63) == 0) {
					double x = x2 + (x1 - x2) / distance * d / wide;
					double y = y2 + (y1 - y2) / distance * d / wide;
					double z = z2 + (z1 - z2) / distance * d / wide;
					worldObj.spawnParticle(EnumParticleTypes.FLAME, x, y, z, 0, 0, 0, new int[0]);
					System.out.println("L = " + d);
					if (this.worldObj.getTotalWorldTime() % 3 == 0 && this.rand.nextBoolean()
							&& this.rand.nextBoolean()) {
						sec.add(new double[] { (x + rand.nextDouble() - 0.5), (y + rand.nextDouble() - 0.5),
								(z + rand.nextDouble() - 0.5) });
					}
				}
			}
			sec.add(new double[] { x2, y2, z2 });
			// Spawns Secondary Particles
			if (this.worldObj.getTotalWorldTime() % 3 == 0) {
				wide *= 2;
				for (int i = 1; i < sec.size(); i++) {
					System.out.println("I2 = " + i);
					double xn1 = ((double[]) sec.get(i - 1))[0];
					double yn1 = ((double[]) sec.get(i - 1))[1];
					double zn1 = ((double[]) sec.get(i - 1))[2];
					double xn2 = ((double[]) sec.get(i))[0];
					double yn2 = ((double[]) sec.get(i))[1];
					double zn2 = ((double[]) sec.get(i))[2];
					double distancen = (Math
							.sqrt(Math.pow(xn1 - xn2, 2) + Math.pow(yn1 - yn2, 2) + Math.pow(zn1 - zn2, 2)));
					List secn = new ArrayList();
					int cn = (int) (distancen * wide);
					for (int d = 0; d < cn; d++) {
						System.out.println("DS = " + d);
						double x = xn2 + (xn1 - xn2) / distancen * d / wide;
						double y = yn2 + (yn1 - yn2) / distancen * d / wide;
						double z = zn2 + (zn1 - zn2) / distancen * d / wide;
						worldObj.spawnParticle(EnumParticleTypes.FLAME, x, y, z, 0, 0, 0, new int[0]);
					}
				}
			}
		}

		if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
			float f = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
			this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(this.motionX, this.motionZ) * 180.0D
					/ Math.PI);
			this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(this.motionY, (double) f) * 180.0D
					/ Math.PI);
		}

		BlockPos blockpos = new BlockPos(this.xTile, this.yTile, this.zTile);
		IBlockState iblockstate = this.worldObj.getBlockState(blockpos);
		Block block = iblockstate.getBlock();

		if (block.getMaterial() != Material.air) {
			block.setBlockBoundsBasedOnState(this.worldObj, blockpos);
			AxisAlignedBB axisalignedbb = block.getCollisionBoundingBox(this.worldObj, blockpos, iblockstate);
			breakExtraBlock(worldObj, blockpos, 0, (EntityPlayer) this.shootingEntity,
					worldObj.getBlockState(blockpos));

			if (axisalignedbb != null && axisalignedbb.isVecInside(new Vec3(this.posX, this.posY, this.posZ))) {
				this.inGround = true;
			}
		}
		if (this.arrowShake > 0) {
			--this.arrowShake;
		}
		if (this.inGround) {
			int j = block.getMetaFromState(iblockstate);
			if (block == this.inTile && j == this.inData) {
				
			} else {
			}
		} else {
			++this.ticksInAir;
			Vec3 vec31 = new Vec3(this.posX, this.posY, this.posZ);
			Vec3 vec3 = new Vec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
			MovingObjectPosition movingobjectposition = this.worldObj.rayTraceBlocks(vec31, vec3, false, true, false);
			vec31 = new Vec3(this.posX, this.posY, this.posZ);
			vec3 = new Vec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

			if (movingobjectposition != null) {
				vec3 = new Vec3(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord,
						movingobjectposition.hitVec.zCoord);
			}

			Entity entity = null;
			List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox()
					.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
			double d0 = 0.0D;
			int i;
			float f1;

			for (i = 0; i < list.size(); ++i) {
				Entity entity1 = (Entity) list.get(i);

				if (entity1.canBeCollidedWith() && (entity1 != this.shootingEntity || this.ticksInAir >= 5)) {
					f1 = 0.3F;
					AxisAlignedBB axisalignedbb1 = entity1.getEntityBoundingBox().expand((double) f1, (double) f1,
							(double) f1);
					MovingObjectPosition movingobjectposition1 = axisalignedbb1.calculateIntercept(vec31, vec3);

					if (movingobjectposition1 != null) {
						double d1 = vec31.distanceTo(movingobjectposition1.hitVec);

						if (d1 < d0 || d0 == 0.0D) {
							entity = entity1;
							d0 = d1;
						}
					}
				}
			}

			if (entity != null) {
				movingobjectposition = new MovingObjectPosition(entity);
			}

			if (movingobjectposition != null && movingobjectposition.entityHit != null
					&& movingobjectposition.entityHit instanceof EntityPlayer) {
				EntityPlayer entityplayer = (EntityPlayer) movingobjectposition.entityHit;

				if (entityplayer.capabilities.disableDamage || this.shootingEntity instanceof EntityPlayer
						&& !((EntityPlayer) this.shootingEntity).canAttackPlayer(entityplayer)) {
					movingobjectposition = null;
				}
			}
			float f2;
			float f3;
			float f4;
			if (movingobjectposition != null) {
				if (movingobjectposition.entityHit != null) {
				} else {
					BlockPos blockpos1 = movingobjectposition.getBlockPos();
					this.xTile = blockpos1.getX();
					this.yTile = blockpos1.getY();
					this.zTile = blockpos1.getZ();
					iblockstate = this.worldObj.getBlockState(blockpos1);
					this.inTile = iblockstate.getBlock();
					this.inData = this.inTile.getMetaFromState(iblockstate);
					f3 = MathHelper.sqrt_double(
							this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
					this.playSound("random.bowhit", 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
					this.inGround = true;
					this.arrowShake = 7;
					this.setIsCritical(false);

					if (this.inTile.getMaterial() != Material.air) {
						this.inTile.onEntityCollidedWithBlock(this.worldObj, blockpos1, iblockstate, this);
					}
				}
			}

			if (this.getIsCritical()) {
				for (i = 0; i < 4; ++i) {
					this.worldObj.spawnParticle(EnumParticleTypes.CRIT, this.posX + this.motionX * (double) i / 4.0D,
							this.posY + this.motionY * (double) i / 4.0D, this.posZ + this.motionZ * (double) i / 4.0D,
							-this.motionX, -this.motionY + 0.2D, -this.motionZ, new int[0]);
				}
			}

			if (!inGround) {
				this.posX += this.motionX;
				this.posY += this.motionY;
				this.posZ += this.motionZ;
			}
			f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
			this.rotationYaw = (float) (Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);

			for (this.rotationPitch = (float) (Math.atan2(this.motionY, (double) f2) * 180.0D
					/ Math.PI); this.rotationPitch
							- this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F) {
				;
			}

			while (this.rotationPitch - this.prevRotationPitch >= 180.0F) {
				this.prevRotationPitch += 360.0F;
			}

			while (this.rotationYaw - this.prevRotationYaw < -180.0F) {
				this.prevRotationYaw -= 360.0F;
			}

			while (this.rotationYaw - this.prevRotationYaw >= 180.0F) {
				this.prevRotationYaw += 360.0F;
			}

			this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
			this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
			f3 = 0.99F;
			f1 = 0.05F;

			if (this.isWet()) {
				this.extinguish();
			}

			if (!inGround) {
				this.motionX *= (double) f3;
				this.motionY *= (double) f3;
				this.motionZ *= (double) f3;
				this.motionY -= (double) f1;
			}
			this.doBlockCollisions();
		}
		this.setPosition(this.posX, this.posY, this.posZ);
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	public void writeEntityToNBT(NBTTagCompound tagCompound) {
		tagCompound.setShort("xTile", (short) this.xTile);
		tagCompound.setShort("yTile", (short) this.yTile);
		tagCompound.setShort("zTile", (short) this.zTile);
		tagCompound.setShort("life", (short) this.ticksInGround);
		ResourceLocation resourcelocation = (ResourceLocation) Block.blockRegistry.getNameForObject(this.inTile);
		tagCompound.setString("inTile", resourcelocation == null ? "" : resourcelocation.toString());
		tagCompound.setByte("inData", (byte) this.inData);
		tagCompound.setByte("shake", (byte) this.arrowShake);
		tagCompound.setByte("inGround", (byte) (this.inGround ? 1 : 0));
		tagCompound.setByte("pickup", (byte) this.canBePickedUp);
		tagCompound.setDouble("damage", this.damage);
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readEntityFromNBT(NBTTagCompound tagCompund) {
		this.xTile = tagCompund.getShort("xTile");
		this.yTile = tagCompund.getShort("yTile");
		this.zTile = tagCompund.getShort("zTile");
		this.ticksInGround = tagCompund.getShort("life");

		if (tagCompund.hasKey("inTile", 8)) {
			this.inTile = Block.getBlockFromName(tagCompund.getString("inTile"));
		} else {
			this.inTile = Block.getBlockById(tagCompund.getByte("inTile") & 255);
		}

		this.inData = tagCompund.getByte("inData") & 255;
		this.arrowShake = tagCompund.getByte("shake") & 255;
		this.inGround = tagCompund.getByte("inGround") == 1;

		if (tagCompund.hasKey("damage", 99)) {
			this.damage = tagCompund.getDouble("damage");
		}

		if (tagCompund.hasKey("pickup", 99)) {
			this.canBePickedUp = tagCompund.getByte("pickup");
		} else if (tagCompund.hasKey("player", 99)) {
			this.canBePickedUp = tagCompund.getBoolean("player") ? 1 : 0;
		}
	}

	/**
	 * Called by a player entity when they collide with an entity
	 */
	public void onCollideWithPlayer(EntityPlayer entityIn) {
		if (!this.worldObj.isRemote && this.inGround && this.arrowShake <= 0) {
			boolean flag = this.canBePickedUp == 1 || this.canBePickedUp == 2 && entityIn.capabilities.isCreativeMode;

			if (this.canBePickedUp == 1 && !entityIn.inventory.addItemStackToInventory(new ItemStack(Items.arrow, 1))) {
				flag = false;
			}

			if (flag) {
				this.playSound("random.pop", 0.2F,
						((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
				entityIn.onItemPickup(this, 1);
				this.setDead();
			}
		}
	}

	/**
	 * returns if this entity triggers Block.onEntityWalking on the blocks they
	 * walk on. used for spiders and wolves to prevent them from trampling crops
	 */
	protected boolean canTriggerWalking() {
		return false;
	}

	public void setDamage(double p_70239_1_) {
		this.damage = p_70239_1_;
	}

	public double getDamage() {
		return this.damage;
	}

	/**
	 * Sets the amount of knockback the arrow applies when it hits a mob.
	 */
	public void setKnockbackStrength(int p_70240_1_) {
		this.knockbackStrength = p_70240_1_;
	}

	/**
	 * If returns false, the item will not inflict any damage against entities.
	 */
	public boolean canAttackWithItem() {
		return false;
	}

	/**
	 * Whether the arrow has a stream of critical hit particles flying behind
	 * it.
	 */
	public void setIsCritical(boolean p_70243_1_) {
		byte b0 = this.dataWatcher.getWatchableObjectByte(16);

		if (p_70243_1_) {
			this.dataWatcher.updateObject(16, Byte.valueOf((byte) (b0 | 1)));
		} else {
			this.dataWatcher.updateObject(16, Byte.valueOf((byte) (b0 & -2)));
		}
	}

	/**
	 * Whether the arrow has a stream of critical hit particles flying behind
	 * it.
	 */
	public boolean getIsCritical() {
		byte b0 = this.dataWatcher.getWatchableObjectByte(16);
		return (b0 & 1) != 0;
	}
}