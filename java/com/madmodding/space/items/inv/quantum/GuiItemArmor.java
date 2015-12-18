package com.madmodding.space.items.inv.quantum;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiItemArmor extends GuiContainer {

	private IInventory playerInv;
	private InventoryItemArmor ii;
	private int type;

	public GuiItemArmor(IInventory playerInv, InventoryItemArmor i, int type) {
		super(new ContainerItemArmor(type, playerInv, i));

		this.playerInv = playerInv;
		this.ii = i;
		this.type = type;
		this.xSize = 176;
		this.ySize = 166;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		switch (type) {
		case 0:
			this.mc.getTextureManager().bindTexture(new ResourceLocation("space:textures/gui/quahelm.png"));
			break;
		case 1:
			this.mc.getTextureManager().bindTexture(new ResourceLocation("space:textures/gui/quachest.png"));
			break;
		case 2:
			this.mc.getTextureManager().bindTexture(new ResourceLocation("space:textures/gui/qualegs.png"));
			break;
		case 3:
			this.mc.getTextureManager().bindTexture(new ResourceLocation("space:textures/gui/quaboots.png"));
			break;
		}
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String s = this.ii.getDisplayName().getUnformattedText();
		this.fontRendererObj.drawString(s, 88 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752); // #404040
		this.fontRendererObj.drawString(this.playerInv.getDisplayName().getUnformattedText(), 8, 41, 4210752); // #404040
	}
}
