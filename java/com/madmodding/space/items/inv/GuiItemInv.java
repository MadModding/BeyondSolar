package com.madmodding.space.items.inv;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiItemInv extends GuiContainer {

	private IInventory playerInv;
	private InventoryItem ii;
	public GuiItemInv(IInventory playerInv, InventoryItem i) {
		super(new ContainerItemInv(playerInv, i));

		this.playerInv = playerInv;
		this.ii = i;

		this.xSize = 176;
		this.ySize = 166;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(new ResourceLocation("space:textures/gui/itemchest.png"));
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String s = this.ii.getDisplayName().getUnformattedText();
		this.fontRendererObj.drawString(s, 88 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752); // #404040
		this.fontRendererObj.drawString(this.playerInv.getDisplayName().getUnformattedText(), 8, 72, 4210752); // #404040
	}
}
