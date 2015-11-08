package com.madmodding.space.items;

import java.util.List;
import java.util.Random;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSwordCustom extends ItemSword implements IColorable {

	private double attackDamage;

	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return false;
	}

	public ItemSwordCustom(String string, ToolMaterial material) {
		super(material);
		this.setUnlocalizedName(string);
		this.attackDamage = 4.0F + material.getDamageVsEntity();
	}

	public Multimap getAttributeModifiers(ItemStack stack) {

		Multimap multimap = HashMultimap.create();
		if (stack.hasTagCompound() && stack.getTagCompound().hasKey("Mode")) {
			if (stack.getTagCompound().getInteger("Mode") == 0)
				multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(
						itemModifierUUID, "Weapon modifier", (double) this.attackDamage * 1.5, 0));
			else if (stack.getTagCompound().getInteger("Mode") == 1)
				multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(),
						new AttributeModifier(itemModifierUUID, "Weapon modifier", (double) this.attackDamage, 0));
			else if (stack.getTagCompound().getInteger("Mode") == 2)
				multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(
						itemModifierUUID, "Weapon modifier", (double) this.attackDamage * 1.2, 0));
			else if (stack.getTagCompound().getInteger("Mode") == 3)
				multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(),
						new AttributeModifier(itemModifierUUID, "Weapon modifier", (double) this.attackDamage * 0.5, 0));
		}
		return multimap;
	}

	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer entity, int timeLeft) {
		if (!entity.isSneaking()) {
			stack.getTagCompound().setInteger("Mode", stack.getTagCompound().getInteger("Mode") + 1);
			if (stack.getTagCompound().getInteger("Mode") > 3)
				stack.getTagCompound().setInteger("Mode", 0);
			Random rand = new Random();
			int l = stack.getTagCompound().getInteger("Color1");
			double d0 = (double) (l >> 16 & 255) / 255.0D;
			double d1 = (double) (l >> 8 & 255) / 255.0D;
			double d2 = (double) (l >> 0 & 255) / 255.0D;
			for (int i = 0; i < 100; i++) {
				world.spawnParticle(EnumParticleTypes.SPELL_MOB, entity.posX + (rand.nextDouble() - 0.5),
						1 + entity.posY + (rand.nextDouble() - 0.5) * 2, entity.posZ + (rand.nextDouble() - 0.5), d0,
						d1, d2, new int[] {});
			}
		}
	}

	public String getItemStackDisplayName(ItemStack stack) {
		if (!stack.hasTagCompound() || !stack.getTagCompound().hasKey("Name")
				|| stack.getTagCompound().getString("Name").contains("Hidden"))
			return ("" + StatCollector.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
		else {
			if (stack.getTagCompound().getInteger("Mode") == 0)
				return stack.getTagCompound().getString("Name") + " Broad Sword";
			else if (stack.getTagCompound().getInteger("Mode") == 1)
				return stack.getTagCompound().getString("Name") + " Long Sword";
			else if (stack.getTagCompound().getInteger("Mode") == 2)
				return stack.getTagCompound().getString("Name") + " Katana";
			else if (stack.getTagCompound().getInteger("Mode") == 3)
				return stack.getTagCompound().getString("Name") + " Blade";
			else
				return stack.getTagCompound().getString("Name") + " Blade";
		}
	}

	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.EPIC;
	}

	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer attacker, Entity target) {
		if (stack.getTagCompound().getInteger("Mode") == 0) {
		} else if (stack.getTagCompound().getInteger("Mode") == 1) {
			List elist = target.worldObj.getEntitiesWithinAABBExcludingEntity(attacker,
					target.getEntityBoundingBox().expand(2, 2, 2));
			for (int i = 0; i < elist.size(); i++) {
				if (elist.get(i) instanceof EntityLivingBase
						&& ((EntityLivingBase) elist.get(i)).hurtResistantTime <= 0) {
					((EntityLivingBase) elist.get(i)).hurtResistantTime = 1;
					onLeftClickEntity(stack, attacker, ((EntityLivingBase) elist.get(i)));
					double x1 = ((EntityLivingBase) elist.get(i)).posX;
					double y1 = ((EntityLivingBase) elist.get(i)).posY;
					double z1 = ((EntityLivingBase) elist.get(i)).posZ;
					double x2 = target.posX;
					double y2 = target.posY;
					double z2 = target.posZ;
					double distance = (Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2) + Math.pow(z1 - z2, 2)));
					int c = (int) (distance * 5);
					Random rand = new Random();
					int l = stack.getTagCompound().getInteger("Color1");
					double d0 = (double) (l >> 16 & 255) / 255.0D;
					double d1 = (double) (l >> 8 & 255) / 255.0D;
					double d2 = (double) (l >> 0 & 255) / 255.0D;
					for (int d = 0; d < c; d++) {
						attacker.worldObj.spawnParticle(EnumParticleTypes.SPELL_MOB,
								target.posX + (x1 - x2) / distance * d / 5,
								1 + target.posY + (y1 - y2) / distance * d / 5,
								target.posZ + (z1 - z2) / distance * d / 5, d0, d1, d2, new int[0]);

					}
				}
			}
		} else if (stack.getTagCompound().getInteger("Mode") == 2) {
			double mx = (double) (-MathHelper.sin(attacker.rotationYaw / 180.0F * (float) Math.PI)
					* MathHelper.cos(attacker.rotationPitch / 180.0F * (float) Math.PI));
			double mz = (double) (MathHelper.cos(attacker.rotationYaw / 180.0F * (float) Math.PI)
					* MathHelper.cos(attacker.rotationPitch / 180.0F * (float) Math.PI));
			double my = (double) (-MathHelper.sin(attacker.rotationPitch / 180.0F * (float) Math.PI));
			double x1 = target.posX;
			double y1 = target.posY;
			double z1 = target.posZ;
			double x2 = attacker.posX;
			double y2 = attacker.posY;
			double z2 = attacker.posZ;
			double distance = (Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2) + Math.pow(z1 - z2, 2)));
			attacker.addVelocity((x1 - x2) / 2, (y1 - y2) / 2, (z1 - z2) / 2);
		} else if (stack.getTagCompound().getInteger("Mode") == 3) {
		}

		return false;
	}

	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int renderPass) {
		if (!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());
		if (!stack.getTagCompound().hasKey("Name"))
			stack.getTagCompound().setString("Name", "Space Age");
		if (!stack.getTagCompound().hasKey("Mode"))
			stack.getTagCompound().setInteger("Mode", 0);
		if (!stack.getTagCompound().hasKey("Color0"))
			stack.getTagCompound().setInteger("Color0", 16777215);
		if (!stack.getTagCompound().hasKey("Color1"))
			stack.getTagCompound().setInteger("Color1", 16777215);
		if (!stack.getTagCompound().hasKey("Color2"))
			stack.getTagCompound().setInteger("Color2", 16777215);
		if (!stack.getTagCompound().hasKey("Color3"))
			stack.getTagCompound().setInteger("Color3", 16777215);
		if (!stack.getTagCompound().hasKey("Unbreakable"))
			stack.getTagCompound().setBoolean("Unbreakable", true);
		return stack.getTagCompound().getInteger("Color" + renderPass);

	}

	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		if (stack.getTagCompound().getInteger("Mode") == 0) {
		} else if (stack.getTagCompound().getInteger("Mode") == 1) {
			List elist = target.worldObj.getEntitiesWithinAABBExcludingEntity(attacker,
					target.getEntityBoundingBox().expand(2, 2, 2));
			for (int i = 0; i < elist.size(); i++) {
				if (elist.get(i) instanceof EntityLivingBase) {
					((EntityPlayer) attacker).attackTargetEntityWithCurrentItem(((EntityLivingBase) elist.get(i)));
				}
			}
		} else if (stack.getTagCompound().getInteger("Mode") == 2) {

			double mx = (double) (-MathHelper.sin(attacker.rotationYaw / 180.0F * (float) Math.PI)
					* MathHelper.cos(attacker.rotationPitch / 180.0F * (float) Math.PI));
			double mz = (double) (MathHelper.cos(attacker.rotationYaw / 180.0F * (float) Math.PI)
					* MathHelper.cos(attacker.rotationPitch / 180.0F * (float) Math.PI));
			double my = (double) (-MathHelper.sin(attacker.rotationPitch / 180.0F * (float) Math.PI));
			double x1 = target.posX;
			double y1 = target.posY;
			double z1 = target.posZ;
			double x2 = attacker.posX;
			double y2 = attacker.posY;
			double z2 = attacker.posZ;
			double distance = (Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2) + Math.pow(z1 - z2, 2)));
			attacker.addVelocity((x1 - x2) / 2, (y1 - y2) / 2, (z1 - z2) / 2);
		} else if (stack.getTagCompound().getInteger("Mode") == 3) {
			List elist = target.worldObj.getEntitiesWithinAABBExcludingEntity(attacker,
					target.getEntityBoundingBox().expand(2, 2, 2));
			for (int i = 0; i < elist.size(); i++) {
				if (elist.get(i) instanceof EntityLivingBase)
					((EntityPlayer) attacker).attackTargetEntityWithCurrentItem(((EntityLivingBase) elist.get(i)));
			}
		}
		return true;
	}

	/**
	 * Called when a Block is destroyed using this Item. Return true to trigger
	 * the "Use Item" statistic.
	 */
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, Block blockIn, BlockPos pos,
			EntityLivingBase playerIn) {
		return true;
	}

	public void onUpdate(ItemStack stack, World worldIn, Entity player, int itemSlot, boolean isHeld) {
		if (!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());
		if (!stack.getTagCompound().hasKey("Name"))
			stack.getTagCompound().setString("Name", "Space Age");
		if (!stack.getTagCompound().hasKey("Mode"))
			stack.getTagCompound().setInteger("Mode", 0);
		if (!stack.getTagCompound().hasKey("Color0"))
			stack.getTagCompound().setInteger("Color0", 16777215);
		if (!stack.getTagCompound().hasKey("Color1"))
			stack.getTagCompound().setInteger("Color1", 16777215);
		if (!stack.getTagCompound().hasKey("Color2"))
			stack.getTagCompound().setInteger("Color2", 16777215);
		if (!stack.getTagCompound().hasKey("Color3"))
			stack.getTagCompound().setInteger("Color3", 16777215);
		if (!stack.getTagCompound().hasKey("Unbreakable"))
			stack.getTagCompound().setBoolean("Unbreakable", true);

	}

	public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player) {
		return false;
	}
}
