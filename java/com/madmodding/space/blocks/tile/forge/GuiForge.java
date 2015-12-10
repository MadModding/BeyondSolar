package com.madmodding.space.blocks.tile.forge;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiForge extends GuiContainer {

	private IInventory playerInv;
	private TileEntityForge te;

	public GuiForge(IInventory playerInv, TileEntityForge te) {
		super(new ContainerForge(playerInv, te));

		this.playerInv = playerInv;
		this.te = te;

		this.xSize = 176;
		this.ySize = 166;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(new ResourceLocation("space:textures/gui/forge.png"));
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		int i1 = this.func_175381_h(24);
		this.drawTexturedModalRect(k + 81, l + 34, 176, 14, i1 + 1, 16);
	}

	private int func_175381_h(int p_175381_1_) {
		int j = this.te.getField(2);
		int k = this.te.getField(3);
		return k != 0 && j != 0 ? j * p_175381_1_ / k : 0;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String s = this.te.getDisplayName().getUnformattedText();
		this.fontRendererObj.drawString(s, 88 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752); // #404040
		this.fontRendererObj.drawString(this.playerInv.getDisplayName().getUnformattedText(), 8, 72, 4210752); // #404040
	}
}
