package com.madmodding.space.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelAlienEgg extends ModelBase {
	
  ModelRenderer Egg1;
  ModelRenderer Egg2;
  ModelRenderer Egg3;
  ModelRenderer Egg4;
  ModelRenderer Egg5;
  ModelRenderer Egg6;
  ModelRenderer Egg7;
  ModelRenderer Tentacles;
  
  public ModelAlienEgg() {
    this.textureWidth = 64;
    this.textureHeight = 32;
    
    this.Egg1 = new ModelRenderer(this, 0, 0);
    this.Egg1.addBox(-4.5F, -2.0F, -4.5F, 9, 4, 9);
    this.Egg1.setRotationPoint(0.0F, 15.0F, 0.0F);
    this.Egg1.setTextureSize(64, 32);
    this.Egg1.mirror = true;
    setRotation(this.Egg1, 0.0F, 0.0F, 0.0F);
    this.Egg2 = new ModelRenderer(this, 0, 13);
    this.Egg2.addBox(-4.0F, 1.5F, -4.0F, 8, 2, 8);
    this.Egg2.setRotationPoint(0.0F, 15.0F, 0.0F);
    this.Egg2.setTextureSize(64, 32);
    this.Egg2.mirror = true;
    setRotation(this.Egg2, 0.0F, 0.0F, 0.0F);
    this.Egg3 = new ModelRenderer(this, 0, 13);
    this.Egg3.addBox(-4.0F, -3.5F, -4.0F, 8, 2, 8);
    this.Egg3.setRotationPoint(0.0F, 15.0F, 0.0F);
    this.Egg3.setTextureSize(64, 32);
    this.Egg3.mirror = true;
    setRotation(this.Egg3, 0.0F, 0.0F, 0.0F);
    this.Egg4 = new ModelRenderer(this, 0, 23);
    this.Egg4.addBox(-3.5F, -5.0F, -3.5F, 7, 2, 7);
    this.Egg4.setRotationPoint(0.0F, 15.0F, 0.0F);
    this.Egg4.setTextureSize(64, 32);
    this.Egg4.mirror = true;
    setRotation(this.Egg4, 0.0F, 0.0F, 0.0F);
    this.Egg5 = new ModelRenderer(this, 0, 23);
    this.Egg5.addBox(-3.5F, 3.5F, -3.5F, 7, 1, 7);
    this.Egg5.setRotationPoint(0.0F, 15.0F, 0.0F);
    this.Egg5.setTextureSize(64, 32);
    this.Egg5.mirror = true;
    setRotation(this.Egg5, 0.0F, 0.0F, 0.0F);
    this.Egg6 = new ModelRenderer(this, 27, 3);
    this.Egg6.addBox(-3.0F, -6.0F, -3.0F, 6, 1, 6);
    this.Egg6.setRotationPoint(0.0F, 15.0F, 0.0F);
    this.Egg6.setTextureSize(64, 32);
    this.Egg6.mirror = true;
    setRotation(this.Egg6, 0.0F, 0.0F, 0.0F);
    this.Egg7 = new ModelRenderer(this, 44, 3);
    this.Egg7.addBox(-2.5F, -7.0F, -2.5F, 5, 1, 5);
    this.Egg7.setRotationPoint(0.0F, 15.0F, 0.0F);
    this.Egg7.setTextureSize(64, 32);
    this.Egg7.mirror = true;
    setRotation(this.Egg7, 0.0F, 0.0F, 0.0F);
    this.Tentacles = new ModelRenderer(this, 25, 16);
    this.Tentacles.addBox(-3.5F, 4.5F, -3.5F, 7, 5, 7);
    this.Tentacles.setRotationPoint(0.0F, 15.0F, 0.0F);
    this.Tentacles.setTextureSize(64, 32);
    this.Tentacles.mirror = true;
    setRotation(this.Tentacles, 0.0F, 0.0F, 0.0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    this.Egg1.render(f5);
    this.Egg2.render(f5);
    this.Egg3.render(f5);
    this.Egg4.render(f5);
    this.Egg5.render(f5);
    this.Egg6.render(f5);
    this.Egg7.render(f5);
    this.Tentacles.render(f5);
  }
  
  public void renderModel(float f) {
    this.Egg1.render(f);
    this.Egg2.render(f);
    this.Egg3.render(f);
    this.Egg4.render(f);
    this.Egg5.render(f);
    this.Egg6.render(f);
    this.Egg7.render(f);
    this.Tentacles.render(f);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }
}
