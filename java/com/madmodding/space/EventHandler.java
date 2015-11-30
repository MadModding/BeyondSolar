package com.madmodding.space;

import java.io.File;
import java.io.FileInputStream;

import com.madmodding.space.blocks.ModBlocks;
import com.madmodding.space.blocks.tile.TileEntityAlienCell;
import com.madmodding.space.items.ItemArmorCustom;
import com.madmodding.space.items.element.Element;
import com.madmodding.space.items.element.ElementLib;
import com.madmodding.space.items.element.ItemArmorMaterial;
import com.madmodding.space.items.element.ItemDyeSpec;
import com.madmodding.space.space.SpaceTeleporter;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class EventHandler {
	// @SubscribeEvent
	// public void serverLoad(FMLServerStartingEvent event) {
	// event.registerServerCommand(new CommandPush());
	// }

	@SubscribeEvent
	public void playerLoad(PlayerEvent.LoadFromFile event) {
		event.entityPlayer.getAttributeMap().registerAttribute(Main.fireDamage);
		event.entityPlayer.getAttributeMap().registerAttribute(Main.antimatterDamage);
		event.entityPlayer.getAttributeMap().registerAttribute(Main.radiationDamage);
		event.entityPlayer.getAttributeMap().registerAttribute(Main.divineDamage);
		event.entityPlayer.getAttributeMap().registerAttribute(Main.armorPercent);

		NBTTagCompound nbttagcompound = null;

		try {
			File file1 = new File(event.playerDirectory, event.playerUUID + ".dat");

			if (file1.exists() && file1.isFile()) {
				nbttagcompound = CompressedStreamTools.readCompressed(new FileInputStream(file1));
			}
		} catch (Exception exception) {
			System.out.println("Failed to load special Player Data, Missing File");
		}
		if (nbttagcompound != null) {
			if (nbttagcompound.hasKey("Attributes", 9) && event.entityPlayer.worldObj != null
					&& !event.entityPlayer.worldObj.isRemote) {
				SharedMonsterAttributes.func_151475_a(event.entityPlayer.getAttributeMap(),
						nbttagcompound.getTagList("Attributes", 10));
			}
		} else
			System.out.println("Failed to load special Player Data, No Tag");
		System.out.println("Data has been loaded - isRemote = " + event.entity.worldObj.isRemote);
	}

	@SubscribeEvent
	public void onPlayerAttack(AttackEntityEvent event) {
		Entity targetEntity = event.target;
		if (targetEntity.canAttackWithItem()) {
			if (!targetEntity.hitByEntity(event.entityPlayer)) {
				float f = (float) event.entityPlayer.getEntityAttribute(SharedMonsterAttributes.attackDamage)
						.getAttributeValue();
				try {
					f += (float) event.entityPlayer.getEntityAttribute(Main.antimatterDamage).getAttributeValue();
				} catch (NullPointerException exception) {
					System.out.println("Antimatter Data Didnt load, isRemote = " + event.entity.worldObj.isRemote);
				}
				try {

					f += (float) event.entityPlayer.getEntityAttribute(Main.radiationDamage).getAttributeValue() / 10;
				} catch (NullPointerException exception) {
					System.out
							.println("Radiation Damage Data Didnt load, isRemote = " + event.entity.worldObj.isRemote);
				}
				try {
					f += (float) event.entityPlayer.getEntityAttribute(Main.fireDamage).getAttributeValue() / 2;
				} catch (NullPointerException exception) {
					System.out.println("Fire Damage Data Didnt load, isRemote = " + event.entity.worldObj.isRemote);
				}
				try {
					f += (float) event.entityPlayer.getEntityAttribute(Main.divineDamage).getAttributeValue() / 2;
				} catch (NullPointerException exception) {
					System.out.println("Divine Damage Data Didnt load, isRemote = " + event.entity.worldObj.isRemote);
				}
				byte b0 = 0;
				float f1 = 0.0F;
				if (targetEntity instanceof EntityLivingBase) {
					f1 = EnchantmentHelper.func_152377_a(event.entityPlayer.getHeldItem(),
							((EntityLivingBase) targetEntity).getCreatureAttribute());
				} else {
					f1 = EnchantmentHelper.func_152377_a(event.entityPlayer.getHeldItem(),
							EnumCreatureAttribute.UNDEFINED);
				}
				int j = b0 + EnchantmentHelper.getKnockbackModifier(event.entityPlayer);
				if (event.entityPlayer.isSprinting()) {
					++j;
				}
				if (f > 0.0F || f1 > 0.0F) {
					boolean flag = event.entityPlayer.fallDistance > 0.0F && !event.entityPlayer.onGround
							&& !event.entityPlayer.isOnLadder() && !event.entityPlayer.isInWater()
							&& !event.entityPlayer.isPotionActive(Potion.blindness)
							&& event.entityPlayer.ridingEntity == null && targetEntity instanceof EntityLivingBase;
					if (flag && f > 0.0F) {
						f *= 1.5F;
					}
					f += f1;
					boolean flag1 = false;
					int i = EnchantmentHelper.getFireAspectModifier(event.entityPlayer);
					if (targetEntity instanceof EntityLivingBase && i > 0 && !targetEntity.isBurning()) {
						flag1 = true;
						targetEntity.setFire(1);
					}

					double d0 = targetEntity.motionX;
					double d1 = targetEntity.motionY;
					double d2 = targetEntity.motionZ;

					boolean flag2 = targetEntity.attackEntityFrom(DamageSource.causePlayerDamage(event.entityPlayer),
							f);
					if (event.entityPlayer.getEntityAttribute(Main.divineDamage).getAttributeValue() != 0) {
						targetEntity.attackEntityFrom(Main.causeDivinePlayerDamage(event.entityPlayer), f);
					}
					if (flag2) {
						if (j > 0) {
							targetEntity.addVelocity(
									(double) (-MathHelper.sin(event.entityPlayer.rotationYaw * (float) Math.PI / 180.0F)
											* (float) j * 0.5F),
									0.1D,
									(double) (MathHelper.cos(event.entityPlayer.rotationYaw * (float) Math.PI / 180.0F)
											* (float) j * 0.5F));
							event.entityPlayer.motionX *= 0.6D;
							event.entityPlayer.motionZ *= 0.6D;
							event.entityPlayer.setSprinting(false);
						}
						if (targetEntity instanceof EntityPlayerMP && targetEntity.velocityChanged) {
							((EntityPlayerMP) targetEntity).playerNetServerHandler
									.sendPacket(new S12PacketEntityVelocity(targetEntity));
							targetEntity.velocityChanged = false;
							targetEntity.motionX = d0;
							targetEntity.motionY = d1;
							targetEntity.motionZ = d2;
						}
						if (flag) {
							event.entityPlayer.onCriticalHit(targetEntity);
						}
						if (f1 > 0.0F) {
							event.entityPlayer.onEnchantmentCritical(targetEntity);
						}
						if (f >= 18.0F) {
							event.entityPlayer.triggerAchievement(AchievementList.overkill);
						}
						event.entityPlayer.setLastAttacker(targetEntity);
						if (targetEntity instanceof EntityLivingBase) {
							EnchantmentHelper.func_151384_a((EntityLivingBase) targetEntity, event.entityPlayer);
						}
						EnchantmentHelper.func_151385_b(event.entityPlayer, targetEntity);
						ItemStack itemstack = event.entityPlayer.getCurrentEquippedItem();
						Object object = targetEntity;
						if (targetEntity instanceof EntityDragonPart) {
							IEntityMultiPart ientitymultipart = ((EntityDragonPart) targetEntity).entityDragonObj;
							if (ientitymultipart instanceof EntityLivingBase) {
								object = (EntityLivingBase) ientitymultipart;
							}
						}
						if (itemstack != null && object instanceof EntityLivingBase) {
							itemstack.hitEntity((EntityLivingBase) object, event.entityPlayer);
							if (itemstack.stackSize <= 0) {
								event.entityPlayer.destroyCurrentEquippedItem();
							}
						}
						if (targetEntity instanceof EntityLivingBase) {
							event.entityPlayer.addStat(StatList.damageDealtStat, Math.round(f * 10.0F));
							if (i > 0
									|| event.entityPlayer.getEntityAttribute(Main.fireDamage).getAttributeValue() > 0) {
								targetEntity.setFire((int) (i * 4
										+ event.entityPlayer.getEntityAttribute(Main.fireDamage).getAttributeValue()));
							}
							if (event.entityPlayer.getEntityAttribute(Main.radiationDamage).getAttributeValue() > 0)
								((EntityLivingBase) targetEntity)
										.addPotionEffect(new PotionEffect(Potion.poison.getId(),
												(int) (event.entityPlayer.getEntityAttribute(Main.radiationDamage)
														.getAttributeValue()),
										(int) (event.entityPlayer.getEntityAttribute(Main.radiationDamage)
												.getAttributeValue()), false, true));
						}
						if (targetEntity instanceof EntityPlayer && (int) event.entityPlayer
								.getEntityAttribute(Main.antimatterDamage).getAttributeValue() > 0) {
							for (int s = 0; s < 4; s++) {
								if (((EntityPlayer) targetEntity).getCurrentArmor(s) != null) {
									((EntityPlayer) targetEntity).getCurrentArmor(s)
											.attemptDamageItem(
													(int) event.entityPlayer.getEntityAttribute(Main.antimatterDamage)
															.getAttributeValue(),
													((EntityPlayer) targetEntity).getRNG());
								}
							}
						}
						event.entityPlayer.addExhaustion(0.3F);
					} else if (flag1) {
						targetEntity.extinguish();
					}
				}
			}
		}
	}

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
	public void onDevHurtEvent(LivingHurtEvent event) {
		if (event.entityLiving instanceof EntityPlayer) {
			boolean td = false;
			for (int i = 0; i < 4; i++) {
				if (!(event.entityLiving.getCurrentArmor(i) != null
						&& (event.entityLiving.getCurrentArmor(i).getItem() instanceof ItemArmorMaterial
								&& ElementLib.Elements[event.entityLiving.getCurrentArmor(i).getItemDamage()
										% ElementLib.Elements.length] == Element.AR))) {
					td = true;
				}
			}
			if (!td) {
				event.setCanceled(true);
				int lvl = EnchantmentHelper.getEnchantmentLevel(92,
						((EntityPlayer) event.entity).inventory.armorInventory[2]);
				if (event.source.getEntity() != null && lvl > 0) {
					Main.network2.sendTo(new MessageDivinity(event.source.getEntity().getEntityId()),
							((EntityPlayerMP) event.entityLiving));
				}
			}
		}
	}

	@SubscribeEvent
	public void onEntityTick(LivingUpdateEvent event) {

		if (event.entityLiving instanceof EntityPlayer) {
			try {
				((EntityPlayer) event.entityLiving).getEntityAttribute(Main.antimatterDamage).getAttributeValue();
			} catch (NullPointerException exception) {
				((EntityPlayer) event.entityLiving).getAttributeMap().registerAttribute(Main.antimatterDamage);
			}
			try {

				((EntityPlayer) event.entityLiving).getEntityAttribute(Main.radiationDamage).getAttributeValue();
			} catch (NullPointerException exception) {
				((EntityPlayer) event.entityLiving).getAttributeMap().registerAttribute(Main.radiationDamage);
			}
			try {
				((EntityPlayer) event.entityLiving).getEntityAttribute(Main.fireDamage).getAttributeValue();
			} catch (NullPointerException exception) {
				((EntityPlayer) event.entityLiving).getAttributeMap().registerAttribute(Main.fireDamage);
			}
			try {
				((EntityPlayer) event.entityLiving).getEntityAttribute(Main.divineDamage).getAttributeValue();
			} catch (NullPointerException exception) {
				((EntityPlayer) event.entityLiving).getAttributeMap().registerAttribute(Main.divineDamage);
			}
		}
		if (event.entityLiving.posY >= 1000) {
			if (event.entityLiving.ridingEntity == null && event.entityLiving.riddenByEntity == null
					&& !event.entityLiving.worldObj.isRemote) {
				if (event.entityLiving.dimension != 71) {
					event.entityLiving.setAir(300);
					// event.entityLiving.travelToDimension(71);
					event.entityLiving.posY -= 950;
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
					event.entityLiving.posY += 990;
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
							&& (event.entityLiving.getCurrentArmor(i).getItem() instanceof ItemArmorMaterial
									&& !ElementLib.toList(ElementLib.BaseElements).contains(
											ElementLib.Elements[event.entityLiving.getCurrentArmor(i).getItemDamage()
													% ElementLib.Elements.length])))) {
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
								&& (((EntityPlayer) event.entityLiving).getCurrentArmor(i)
										.getItem() instanceof ItemArmorCustom
										|| (event.entityLiving.getCurrentArmor(i).getItem() instanceof ItemArmorMaterial
												&& !ElementLib.toList(ElementLib.BaseElements)
														.contains(ElementLib.Elements[event.entityLiving
																.getCurrentArmor(i).getItemDamage()
																% ElementLib.Elements.length]))))) {
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
				if ((event.entityLiving.getCurrentArmor(i) != null
						&& (event.entityLiving.getCurrentArmor(i).getItem() instanceof ItemArmorCustom))) {
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
