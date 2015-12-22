package com.madmodding.space.items.element;

import java.util.List;
import java.util.UUID;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.madmodding.space.Main;
import com.madmodding.space.blocks.tile.forge.IForgeable;
import com.madmodding.space.items.IFirstTick;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemArmorMaterial extends ItemArmor implements ISpecialArmor, IFirstTick, IForgeable {
	private final int slot;

	public ItemArmorMaterial(String unlocalizedName, ArmorMaterial material, int slot) {
		super(material, slot, slot);
		this.slot = slot;
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(CreativeTabs.tabCombat);
	}

	public Multimap getAttributeModifiers(ItemStack stack) {
		Multimap multimap = HashMultimap.create();
		if (ElementLib.Elements[stack.getItemDamage() % ElementLib.Elements.length] == Element.SN)
			multimap.put(
					Main.fireDamage
							.getAttributeUnlocalizedName(),
					new AttributeModifier(
							UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5" + slot
									+ Integer.toString((int) ((int) 10 * stack.getTagCompound().getDouble("Prot")))),
					"Armor Modifier 9" + slot, 2, 0));
		multimap.put(
				Main.armorPercent
						.getAttributeUnlocalizedName(),
				new AttributeModifier(
						UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5" + slot
								+ Integer.toString((int) ((int) 10 * stack.getTagCompound().getDouble("Prot")))),
				"Armor Modifier 8" + slot,
				(stack.getTagCompound().getDouble("Prot") - 1) / stack.getTagCompound().getDouble("Prot") / 4 * 100,
				0));
		return multimap;
	}

	@SideOnly(Side.CLIENT)
	public void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {
		ElementLib.getSubItems(itemIn, tab, subItems);
	}

	/**
	 * Return whether the specified armor ItemStack has a color.
	 */
	public boolean hasColor(ItemStack stack) {
		if (!stack.hasTagCompound())
			onFirstTick(stack);
		return true;
	}

	/**
	 * Return the color for the specified armor ItemStack.
	 */
	public int getColor(ItemStack stack) {
		if (!stack.hasTagCompound())
			onFirstTick(stack);
		return getColorFromItemStack(stack, 0);
	}

	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int renderPass) {
		if (!stack.hasTagCompound())
			onFirstTick(stack);
		int clr = ElementLib.getColorFromItemStack(stack, renderPass, 1);
		return clr;
	}

	/**
	 * Sets the color of the specified armor ItemStack
	 */
	public void setColor(ItemStack stack, int color) {
		stack.getTagCompound().setInteger("Color", color);
	}

	public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
		if (!stack.hasTagCompound())
			onFirstTick(stack);
		if (ElementLib.isSpecial(player.getName()) == 0 || ElementLib.isSpecial(player.getName()) == 5) {
			if (!ElementLib.toList(ElementLib.NonResElements).contains(ElementLib.Elements[stack.getItemDamage()])) {
				if (ElementLib.Elements[stack.getItemDamage()] != Element.SN
						|| ElementLib.isSpecial(player.getName()) != 5)
					stack.stackSize--;

			}
		}
		if (!ElementLib.toList(ElementLib.NonResElements).contains(ElementLib.Elements[stack.getItemDamage()])) {
			if (!player.isSneaking() && stack.getItem() == ElementLib.ElementChest
					&& ElementLib.Elements[stack.getItemDamage()] == Element.AR) {
				player.addPotionEffect(new PotionEffect(Potion.moveSpeed.getId(), 5, 1));
				player.addPotionEffect(new PotionEffect(Potion.jump.getId(), 5, 1));
				double ax = Math.abs(player.motionX);
				double az = Math.abs(player.motionZ);
				if (Math.sqrt(Math.pow(ax, 2) + Math.pow(az, 2)) <= 4) {
					player.addVelocity(player.motionX * (0.8 - 1), 0, player.motionZ * (0.8 - 1));
				}
				boolean isMove = (Math.abs(player.motionX) > 0.25 || Math.abs(player.motionZ) > 0.25);
				if (player.dimension == 71) {
					player.motionY -= 0.075 / 4 * 3;
				}
				player.fallDistance = 0;

				float pitch = player.rotationPitch;
				double percent = 0;
				percent = -pitch / 90;
				player.jumpMovementFactor = (float) (0.8 * (1 - Math.abs(percent)));
				if (Math.abs(percent) > 0.15)
					if (isMove)
						player.motionY += percent * 0.15;
				if (percent > -0.25)
					player.motionY += 0.075;
				if (!isMove)
					player.motionY = 0;
				if (!isMove) {
					if (world.isRemote && world.getTotalWorldTime() % 1 == 0 && !player.isSneaking()
							&& (Minecraft.getMinecraft().gameSettings.thirdPersonView != 0
									|| Minecraft.getMinecraft().thePlayer != player)) {
						double dist = 0.25 + (0.25 * Math.abs(percent));// MathHelper.sin(player.rotationPitch)));

						double xp = dist * (double) (-MathHelper.sin(player.rotationYaw / 180.0F * (float) Math.PI)
								* MathHelper.cos(player.rotationPitch / 180.0F * (float) Math.PI));
						double yp = dist * (double) (-MathHelper.sin(player.rotationPitch / 180.0F * (float) Math.PI));
						double zp = dist * (double) (MathHelper.cos(player.rotationYaw / 180.0F * (float) Math.PI)
								* MathHelper.cos(player.rotationPitch / 180.0F * (float) Math.PI));

						double x1 = xp + player.posX + (double) (-MathHelper
								.sin(((player.rotationYaw + 90) % 360) / 180.0F * (float) Math.PI) / 7);
						double y1 = yp + player.posY + 1.6625;
						double z1 = zp + player.posZ
								+ (double) (MathHelper.cos(((player.rotationYaw + 90) % 360) / 180.0F * (float) Math.PI)
										/ 7);
						double x2 = xp + player.posX - 0.625 * (double) (-MathHelper
								.sin(((player.rotationYaw + 90) % 360) / 180.0F * (float) Math.PI) / 7);
						double y2 = yp + player.posY + 1.6625;
						double z2 = zp + player.posZ - 0.625
								* (double) (MathHelper.cos(((player.rotationYaw + 90) % 360) / 180.0F * (float) Math.PI)
										/ 7);
						generateClientsideParticles(x1, y1, z1, 0, 0, 0);
						generateClientsideParticles(x2, y2, z2, 0, 0, 0);
					}
				}
			}

		}
		if (stack.getTagCompound().getInteger("Type") == 1) {
			player.inventory.armorInventory[0] = null;
			player.inventory.armorInventory[1] = null;
			player.inventory.armorInventory[2] = null;
			player.inventory.armorInventory[3] = null;
		}
		for (int i = 0; i < 4; i++)
			if (player.inventory.armorInventory[i] != null && player.inventory.armorInventory[i].stackSize <= 0) {
				player.inventory.armorInventory[i] = null;
			}

	}

	public void generateClientsideParticles(double xCoord, double yCoord, double zCoord, double xOffset, double yOffset,
			double zOffset) {
		double motionX = Minecraft.getMinecraft().thePlayer.worldObj.rand.nextGaussian() * 0.02D;
		double motionY = Minecraft.getMinecraft().thePlayer.worldObj.rand.nextGaussian() * 0.02D;
		double motionZ = Minecraft.getMinecraft().thePlayer.worldObj.rand.nextGaussian() * 0.02D;
		EntityFX particleMysterious = new EntityFlameFX(Minecraft.getMinecraft().thePlayer.worldObj, xCoord, yCoord,
				zCoord, xOffset, yOffset, zOffset);
		float f = Minecraft.getMinecraft().thePlayer.worldObj.rand.nextFloat() * 0.5F + 0.35F;
		particleMysterious.setRBGColorF(1.0F * f, 0.0F * f, 1.0F * f);
		((EntityFlameFX) particleMysterious).setBaseSpellTextureIndex(144);
		Minecraft.getMinecraft().effectRenderer.addEffect(particleMysterious);
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer p_77624_2_, List list, boolean p_77624_4_) {
		list.add(ElementLib.getRarity(stack).rarityName);
		list.add("Name: " + stack.getTagCompound().getString("Name"));
		if (stack.getTagCompound().getBoolean("Complex")) {
			int[][] em = getElementRatio(stack);
			for (int i = 0; i < em[0].length; i++) {
				list.add(("" + StatCollector.translateToLocal(
						"element." + ElementLib.Elements[em[1][i] % ElementLib.Elements.length].getName() + ".name"))
								.trim()
						+ ": " + em[0][i]);
			}
		}
	}

	public void onUpdate(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
		if (!stack.hasTagCompound())
			onFirstTick(stack);
	}

	public void onCreated(ItemStack stack, World world, EntityPlayer player) {

	}

	public String getItemStackDisplayName(ItemStack stack) {
		if (!stack.hasTagCompound())
			onFirstTick(stack);
		if (!stack.hasTagCompound() || !stack.getTagCompound().hasKey("Name")
				|| stack.getTagCompound().getString("Name").contains("Hidden"))
			return ("" + StatCollector.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
		else {
			if (stack.getItem() == ElementLib.ElementHelm)
				return stack.getTagCompound().getString("Name") + " Helmet";
			else if (stack.getItem() == ElementLib.ElementChest)
				return stack.getTagCompound().getString("Name") + " Chestplate";
			else if (stack.getItem() == ElementLib.ElementLegs)
				return stack.getTagCompound().getString("Name") + " Leggings";
			else
				return stack.getTagCompound().getString("Name") + " Boots";
		}
	}

	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		if (type != "overlay") {
			if (ElementLib.Elements[stack.getItemDamage() % ElementLib.Elements.length] == Element.MD) {
				return "space:textures/models/armor/WhiteArmor.png";
			}

			if (slot != 2) {
				if (entity.dimension == 71 && !ElementLib.toList(ElementLib.BaseElements)
						.contains(ElementLib.Elements[stack.getItemDamage() % ElementLib.Elements.length]))
					return "space:textures/models/armor/custom_layer_spec.png";
				return "space:textures/models/armor/custom_layer_1.png";
			}
			return "space:textures/models/armor/custom_layer_2.png";
		} else {
			return "space:textures/models/armor/custom_layer_overlay.png";
		}

	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return ElementLib.getRarity(stack);
	}

	ModelBiped model1 = null;
	ModelBiped model2 = null;
	ModelBiped model = null;

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase player, ItemStack stack, int armorSlot) {
		if (ElementLib.Elements[stack.getItemDamage()] == Element.MD) {
			int type = ((ItemArmor) stack.getItem()).armorType;
			if (this.model1 == null) {
				this.model1 = new MadArmor(1.0f, true, true, false, true);
			}
			if (this.model2 == null) {
				this.model2 = new MadArmor(0.5f, false, false, true, false);
			}

			if (type == 1 || type == 3 || type == 0) {
				this.model = model1;
			} else {
				this.model = model2;
			}

			if (this.model != null) {
				this.model.bipedHead.showModel = (type == 0);
				this.model.bipedHeadwear.showModel = (type == 0);
				this.model.bipedBody.showModel = ((type == 1) || (type == 2));
				this.model.bipedLeftArm.showModel = (type == 1);
				this.model.bipedRightArm.showModel = (type == 1);
				this.model.bipedLeftLeg.showModel = (type == 2 || type == 3);
				this.model.bipedRightLeg.showModel = (type == 2 || type == 3);
				this.model.isSneak = player.isSneaking();
				this.model.swingProgress = player.swingProgress;
				this.model.isRiding = player.isRiding();
				this.model.isChild = player.isChild();

				this.model.aimedBow = false;
				this.model.heldItemRight = (player.getHeldItem() != null ? 1 : 0);

				if ((player instanceof EntityPlayer)) {
					if (((EntityPlayer) player).getItemInUseDuration() > 0) {
						EnumAction enumaction = ((EntityPlayer) player).getItemInUse().getItemUseAction();
						if (enumaction == EnumAction.BLOCK) {
							this.model.heldItemRight = 3;
						} else if (enumaction == EnumAction.BOW) {
							this.model.aimedBow = true;
						}
					}
				}
			}

			return model;
		} else
			return super.getArmorModel(player, stack, armorSlot);
	}

	@Override
	public void onFirstTick(ItemStack stack) {
		ElementLib.setupArmor(stack);
	}

	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
		return 0;
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {

	}

	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage,
			int slot) {
		return new ArmorProperties(0, 0, 0);
	}

	@Override
	public int[][] getElementRatio(ItemStack stack) {
		int d = stack.getItemDamage();
		if (d < (ElementLib.Elements.length * 4) && !stack.getTagCompound().getBoolean("Complex")) {
			return new int[][] { { 576 }, { d % ElementLib.Elements.length } };
		} else {
			NBTTagList list = stack.getTagCompound().getTagList("Elements", 10);
			int[][] elem = new int[2][list.tagCount()];
			for (int i = 0; i < elem[0].length; i++) {
				elem[0][i] = ((NBTTagCompound) list.get(i)).getInteger("Amt");
				elem[1][i] = ((NBTTagCompound) list.get(i)).getInteger("Ele");
			}
			return elem;
		}
	}

	@Override
	public double getMB(ItemStack stack) {
		return 0;
	}
}
