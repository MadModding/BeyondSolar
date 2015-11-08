package com.madmodding.space.client.render.items;

import org.lwjgl.opengl.GL11;

import com.madmodding.space.client.models.ModelAcidContainer;
import com.madmodding.space.client.models.ModelAlienContainmentCell;
import com.madmodding.space.client.models.ModelAlienEgg;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class RenderAlienCell extends TileEntitySpecialRenderer {
	
	private static final ResourceLocation textures = new ResourceLocation("space:models/AlienContainmentCellTexture.png");
	private ModelAlienContainmentCell model;

	@Override
	public void renderTileEntityAt(TileEntity p_147500_1_, double x, double y, double z, float p_147500_8_, int p_180535_9_) {
	    model = new ModelAlienContainmentCell();
		GL11.glPushMatrix();
	    GL11.glTranslatef((float)x, (float)y+1.5F, (float)z);
	    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
	    GL11.glScalef(1F, 1F, 1F);
	    this.bindTexture(textures);	    
	    GL11.glPushMatrix();
	    this.model.renderModel(0.0625F);
	    GL11.glPopMatrix();
	    GL11.glPopMatrix();
	}

}