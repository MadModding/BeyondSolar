package com.madmodding.space.items.element;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityFlameFX extends EntityFX {
	/** the scale of the flame FX */
	private float flameScale;
	private static final String __OBFID = "CL_00000907";
	private static final ResourceLocation particleTextures = new ResourceLocation("textures/particle/particles.png");
	private static final ResourceLocation particleTexturesNew = new ResourceLocation(
			"space:textures/particle/particles.png");

	protected EntityFlameFX(World worldIn, double p_i1209_2_, double p_i1209_4_, double p_i1209_6_, double p_i1209_8_,
			double p_i1209_10_, double p_i1209_12_) {
		super(worldIn, p_i1209_2_, p_i1209_4_, p_i1209_6_, p_i1209_8_, p_i1209_10_, p_i1209_12_);
		this.motionX = this.motionX * 0.009999999776482582D + p_i1209_8_;
		this.motionY = this.motionY * 0.009999999776482582D + p_i1209_10_;
		this.motionZ = this.motionZ * 0.009999999776482582D + p_i1209_12_;
		double d6 = p_i1209_2_ + (double) ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.05F);
		d6 = p_i1209_4_ + (double) ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.05F);
		d6 = p_i1209_6_ + (double) ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.05F);
		this.flameScale = this.particleScale;
		this.particleGreen = 0.25F;
		this.particleBlue = this.particleRed = 1.0F;
		this.particleMaxAge = (int) (8.0D / (Math.random() * 0.8D + 0.2D)) + 4;
		this.noClip = true;
		this.setParticleTextureIndex(0);
		this.particleTextureIndexX = 0;
		this.particleTextureIndexY = 0;
	}

	public void func_180434_a(WorldRenderer worldrender, Entity p_180434_2_, float p_180434_3_, float p_180434_4_,
			float p_180434_5_, float p_180434_6_, float p_180434_7_, float p_180434_8_) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(particleTexturesNew);
		float f5 = ((float) this.particleAge + p_180434_3_) / (float) this.particleMaxAge;
		this.particleScale = this.flameScale * (1.0F - f5 * f5 * 0.5F);
		float f6 = (float) this.particleTextureIndexX ;/// 16.0F;
		float f7 = f6 + 0.0624375F;
		float f8 = (float) this.particleTextureIndexY ;/// 16.0F;
		float f9 = f8 + 0.0624375F;
		float f10 = 0.2F * this.particleScale;
		if (this.particleIcon != null) {
			f6 = this.particleIcon.getMinU();
			f7 = this.particleIcon.getMaxU();
			f8 = this.particleIcon.getMinV();
			f9 = this.particleIcon.getMaxV();
		}
		float f11 = (float) (this.prevPosX + (this.posX - this.prevPosX) * (double) p_180434_3_ - interpPosX);
		float f12 = (float) (this.prevPosY + (this.posY - this.prevPosY) * (double) p_180434_3_ - interpPosY);
		float f13 = (float) (this.prevPosZ + (this.posZ - this.prevPosZ) * (double) p_180434_3_ - interpPosZ);
		worldrender.setColorRGBA_F(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha);
		worldrender.addVertexWithUV((double) (f11 - p_180434_4_ * f10 - p_180434_7_ * f10),
				(double) (f12 - p_180434_5_ * f10), (double) (f13 - p_180434_6_ * f10 - p_180434_8_ * f10), (double) f7,
				(double) f9);
		worldrender.addVertexWithUV((double) (f11 - p_180434_4_ * f10 + p_180434_7_ * f10),
				(double) (f12 + p_180434_5_ * f10), (double) (f13 - p_180434_6_ * f10 + p_180434_8_ * f10), (double) f7,
				(double) f8);
		worldrender.addVertexWithUV((double) (f11 + p_180434_4_ * f10 + p_180434_7_ * f10),
				(double) (f12 + p_180434_5_ * f10), (double) (f13 + p_180434_6_ * f10 + p_180434_8_ * f10), (double) f6,
				(double) f8);
		worldrender.addVertexWithUV((double) (f11 + p_180434_4_ * f10 - p_180434_7_ * f10),
				(double) (f12 - p_180434_5_ * f10), (double) (f13 + p_180434_6_ * f10 - p_180434_8_ * f10), (double) f6,
				(double) f9);
		Minecraft.getMinecraft().getTextureManager().bindTexture(particleTextures);
	}

	public void func_180435_a(TextureAtlasSprite p_180435_1_) {
		int i = this.getFXLayer();

		if (i == 1) {
			this.particleIcon = p_180435_1_;
		} else {
			throw new RuntimeException("Invalid call to Particle.setTex, use coordinate methods");
		}
	}

	public int getBrightnessForRender(float p_70070_1_) {
		float f1 = ((float) this.particleAge + p_70070_1_) / (float) this.particleMaxAge;
		f1 = MathHelper.clamp_float(f1, 0.0F, 1.0F);
		int i = super.getBrightnessForRender(p_70070_1_);
		int j = i & 255;
		int k = i >> 16 & 255;
		j += (int) (f1 * 15.0F * 16.0F);

		if (j > 240) {
			j = 240;
		}

		return j | k << 16;
	}

	/**
	 * Gets how bright this entity is.
	 */
	public float getBrightness(float p_70013_1_) {
		float f1 = ((float) this.particleAge + p_70013_1_) / (float) this.particleMaxAge;
		f1 = MathHelper.clamp_float(f1, 0.0F, 1.0F);
		float f2 = super.getBrightness(p_70013_1_);
		return f2 * f1 + (1.0F - f1);
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	public void onUpdate() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;

		if (this.particleAge++ >= this.particleMaxAge) {
			this.setDead();
		}

		this.moveEntity(this.motionX, this.motionY, this.motionZ);
		this.motionX *= 0.9599999785423279D;
		this.motionY *= 0.9599999785423279D;
		this.motionZ *= 0.9599999785423279D;

		if (this.onGround) {
			this.motionX *= 0.699999988079071D;
			this.motionZ *= 0.699999988079071D;
		}
	}

	@SideOnly(Side.CLIENT)
	public static class Factory implements IParticleFactory {
		private static final String __OBFID = "CL_00002602";

		public EntityFX getEntityFX(int p_178902_1_, World worldIn, double p_178902_3_, double p_178902_5_,
				double p_178902_7_, double p_178902_9_, double p_178902_11_, double p_178902_13_, int... p_178902_15_) {
			return new EntityFlameFX(worldIn, p_178902_3_, p_178902_5_, p_178902_7_, p_178902_9_, p_178902_11_,
					p_178902_13_);
		}
	}
}