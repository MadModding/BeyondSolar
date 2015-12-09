package com.madmodding.space.blocks.tile.forge;

import org.lwjgl.opengl.GL11;

import com.madmodding.space.client.models.ModelAcidContainer;
import com.madmodding.space.client.models.ModelAlienEgg;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class RenderForge extends TileEntitySpecialRenderer {

	private static final ResourceLocation textures = new ResourceLocation("space:models/ForgeFurnace.png");
	private ModelForge model;

	@Override
	public void renderTileEntityAt(TileEntity p_147500_1_, double x, double y, double z, float p_147500_8_,
			int p_180535_9_) {
		model = new ModelForge();
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5f, (float) y + 1.3f, (float) z + 0.5f);
		GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
		GL11.glScalef(.2F, .2F, .2F);
		this.bindTexture(textures);
		GL11.glPushMatrix();
		this.model.renderModel(0.3F);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}

}