package com.madmodding.space;

import com.madmodding.space.blocks.ModBlocks;
import com.madmodding.space.blocks.tile.TileEntityAlienCell;
import com.madmodding.space.items.ItemArmorCustom;
import com.madmodding.space.items.element.ElementLib;
import com.madmodding.space.items.element.ItemArmorMaterial;
import com.madmodding.space.items.element.ItemDyeSpec;
import com.madmodding.space.space.SpaceTeleporter;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventHandler {
	// @SubscribeEvent
	// public void serverLoad(FMLServerStartingEvent event) {
	// event.registerServerCommand(new CommandPush());
	// }

	@SubscribeEvent
	public void onCellPlaced(BlockEvent.PlaceEvent event) {

		if (event.placedBlock == ModBlocks.alienCell.getDefaultState()) {
			TileEntityAlienCell cell = (TileEntityAlienCell) event.world.getTileEntity(event.pos);
			cell.cellID = TileEntityAlienCell.worldID;
			cell.loc.add(cell.getPos());
			TileEntityAlienCell.worldID++;
			TileEntityAlienCell.loc.add(cell.cellID, cell.getPos());
		}

	}

	@SubscribeEvent
	public void onRenderLivingEvent(RenderLivingEvent.Pre event) {
		if (event.entity instanceof EntityPlayer) {

		}
	}

	@SubscribeEvent
	public void onEntityTick(LivingUpdateEvent event) {

		if (event.entityLiving.posY >= 500) {
			if (event.entityLiving.ridingEntity == null && event.entityLiving.riddenByEntity == null
					&& !event.entityLiving.worldObj.isRemote) {
				if (event.entityLiving.dimension != 71) {
					event.entityLiving.setAir(300);
					// event.entityLiving.travelToDimension(71);
					event.entityLiving.posY -= 450;
					if (event.entityLiving instanceof EntityPlayerMP)
						((EntityPlayerMP) event.entityLiving).mcServer.getConfigurationManager()
								.transferPlayerToDimension(((EntityPlayerMP) event.entityLiving), 71,
										new SpaceTeleporter(((EntityPlayerMP) event.entityLiving).mcServer
												.worldServerForDimension(71)));
					event.entityLiving.timeUntilPortal = event.entityLiving.getPortalCooldown();
				}
			}
		}
		if (event.entityLiving.posY <= 0) {
			if (event.entityLiving.ridingEntity == null && event.entityLiving.riddenByEntity == null
					&& !event.entityLiving.worldObj.isRemote) {
				if (event.entityLiving.dimension == 71) {
					event.entityLiving.posY += 490;
					if (event.entityLiving instanceof EntityPlayerMP)
						((EntityPlayerMP) event.entityLiving).mcServer.getConfigurationManager()
								.transferPlayerToDimension(((EntityPlayerMP) event.entityLiving), 0,
										new SpaceTeleporter(((EntityPlayerMP) event.entityLiving).mcServer
												.worldServerForDimension(0)));
					event.entityLiving.timeUntilPortal = event.entityLiving.getPortalCooldown();
					if (event.entity instanceof EntityPlayer) {
						if (!((EntityPlayer) event.entityLiving).capabilities.isCreativeMode) {
							((EntityPlayer) event.entityLiving).capabilities.allowFlying = false;
						}
					}
				}
			}
		}
		if (event.entityLiving.dimension == 71) {

			if (event.entity.worldObj.getTotalWorldTime() % 10 == 0) {
				boolean td = false;
				for (int i = 0; i < 4; i++) {
					if (!(event.entityLiving.getCurrentArmor(i) != null
							&& event.entityLiving.getCurrentArmor(i).getItem() instanceof ItemArmorCustom)) {
						td = true;
					}
				}
				if (td)
					event.entityLiving.attackEntityFrom(Main.inSpace, 5);
			}
			event.entityLiving.motionY += 0.075 / 4 * 3;
			event.entityLiving.jumpMovementFactor = 0.04f;
			int l = 0;
			for (int i = 0; i < 4; i++) {
				if ((event.entityLiving.getCurrentArmor(i) != null
						&& event.entityLiving.getCurrentArmor(i).getItem() instanceof ItemArmorCustom)) {
					l++;
				}
			}
			if (l >= 4)
				if (event.entity instanceof EntityPlayer) {
					if (!((EntityPlayer) event.entityLiving).capabilities.isCreativeMode) {
						((EntityPlayer) event.entityLiving).capabilities.allowFlying = true;
						if (((EntityPlayer) event.entityLiving).capabilities.isFlying) {
							((EntityPlayer) event.entityLiving).capabilities.isFlying = false;
							((EntityPlayer) event.entityLiving).jump();
							for (int i = 0; i < 16; i++)
								event.entity.worldObj.spawnParticle(EnumParticleTypes.FLAME,
										event.entity.posX + (event.entityLiving.getRNG().nextGaussian()) / 3,
										event.entity.posY,
										event.entity.posZ + (event.entityLiving.getRNG().nextGaussian()) / 3, 0, -0.3,
										0, new int[1]);
						}
					}
				}
			if (event.entityLiving instanceof EntityPlayer) {
				if (((EntityPlayer) event.entityLiving).dimension == 71) {
					int p = 0;
					for (int i = 0; i < 4; i++) {
						if ((((EntityPlayer) event.entityLiving).getCurrentArmor(i) != null
								&& ((EntityPlayer) event.entityLiving).getCurrentArmor(i)
										.getItem() instanceof ItemArmorCustom)) {
							l++;
						}
					}
					if (p >= 4) {
						if (((EntityPlayer) event.entityLiving).worldObj.getTotalWorldTime() % 8 == 0)
							((EntityPlayer) event.entityLiving)
									.setAir(((EntityPlayer) event.entityLiving).getAir() - 3);
					} else
						((EntityPlayer) event.entityLiving).setAir(((EntityPlayer) event.entityLiving).getAir() - 3);
				}
			}
		}
		if (event.entityLiving.dimension != 71) {
			int l = 0;
			for (int i = 0; i < 4; i++) {
				if ((event.entityLiving
						.getCurrentArmor(
								i) != null
						&& (event.entityLiving
								.getCurrentArmor(
										i)
								.getItem() instanceof ItemArmorCustom
								|| (event.entityLiving
										.getCurrentArmor(
												i)
										.getItem() instanceof ItemArmorMaterial
										&& !ElementLib.toList(ElementLib.BaseElements)
												.contains(ElementLib.Elements[event.entityLiving.getCurrentArmor(i)
														.getItemDamage() % ElementLib.Elements.length]))))) {
					l++;
				}
			}
			if (l > 0) {
				event.entityLiving.motionY -= 0.01875 * l;
				event.entityLiving.jumpMovementFactor = 0.02f - 0.0025f * l;
			}
		}
	}

	public void drawTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height) {
		float f = 0.00390625F;
		float f1 = 0.00390625F;
		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer worldrenderer = tessellator.getWorldRenderer();
		worldrenderer.startDrawingQuads();
		worldrenderer.addVertexWithUV((double) (x + 0), (double) (y + height), (double) -90f,
				(double) ((float) (textureX + 0) * f), (double) ((float) (textureY + height) * f1));
		worldrenderer.addVertexWithUV((double) (x + width), (double) (y + height), (double) -90f,
				(double) ((float) (textureX + width) * f), (double) ((float) (textureY + height) * f1));
		worldrenderer.addVertexWithUV((double) (x + width), (double) (y + 0), (double) -90f,
				(double) ((float) (textureX + width) * f), (double) ((float) (textureY + 0) * f1));
		worldrenderer.addVertexWithUV((double) (x + 0), (double) (y + 0), (double) -90f,
				(double) ((float) (textureX + 0) * f), (double) ((float) (textureY + 0) * f1));
		tessellator.draw();
	}

	@SubscribeEvent
	public void ShowAir(RenderGameOverlayEvent.Pre event) {
		if (!event.isCancelable() || event.type != ElementType.AIR)
			return;
		event.setCanceled(true);
		int width = event.resolution.getScaledWidth();
		int height = event.resolution.getScaledHeight();
		EntityPlayer player = (EntityPlayer) Minecraft.getMinecraft().getRenderViewEntity();
		GlStateManager.enableBlend();
		int left = width / 2 + 91;
		int top = height - GuiIngameForge.right_height;

		if (player.isInsideOfMaterial(Material.water) || player.dimension == 71) {
			int air = player.getAir();
			int full = MathHelper.ceiling_double_int((double) (air - 2) * 10.0D / 300.0D);
			int partial = MathHelper.ceiling_double_int((double) air * 10.0D / 300.0D) - full;

			for (int i = 0; i < full + partial; ++i) {
				drawTexturedModalRect(left - i * 8 - 9, top, (i < full ? 16 : 25), 18, 9, 9);
			}
			GuiIngameForge.right_height += 10;
		}

		GlStateManager.disableBlend();

	}

	@SubscribeEvent
	public void OnItemInfo(ItemTooltipEvent event) {

		if (event.itemStack != null && event.itemStack.getItem() == ElementLib.Dye
				&& event.itemStack.getItemDamage() > 15) {
			int x = event.itemStack.getItemDamage() - 16;
			int i1 = x / 16;
			int i2 = x - i1 * 16;
			event.toolTip.add(ItemDyeSpec.dyeColorNouns[i1] + " + " + ItemDyeSpec.dyeColorNouns[i2]);
		}
		if (event.itemStack != null && event.itemStack.getItem() == ElementLib.Dye
				&& event.itemStack.getItemDamage() < 16) {
			int x = event.itemStack.getItemDamage();
			event.toolTip.add(ItemDyeSpec.dyeColorNouns[x]);
		}
	}

}
