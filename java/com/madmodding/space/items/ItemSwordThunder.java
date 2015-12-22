package com.madmodding.space.items;

import java.util.List;
import java.util.Random;

import com.madmodding.space.Main;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class ItemSwordThunder extends ItemSword {
	public void onUpdate(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
		if (!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());
		if (!stack.getTagCompound().hasKey("Name"))
			stack.getTagCompound().setString("Name", stack.getDisplayName());
		if (!stack.getTagCompound().hasKey("Unbreakable"))
			stack.getTagCompound().setBoolean("Unbreakable", true);
	}

	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer attacker, Entity target) {
		List elist = target.worldObj.getEntitiesWithinAABBExcludingEntity(attacker,
				target.getEntityBoundingBox().expand(2, 2, 2));
		for (int i = 0; i < elist.size(); i++) {
			if (elist.get(i) instanceof EntityLivingBase && ((EntityLivingBase) elist.get(i)).hurtResistantTime <= 0) {
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
				int l = 16776960;
				double R = (double) (l >> 16 & 255) / 255.0D;
				double G = (double) (l >> 8 & 255) / 255.0D;
				double B = (double) (l >> 0 & 255) / 255.0D;
				for (int d = 0; d < c; d++) {
					attacker.worldObj.spawnParticle(EnumParticleTypes.SPELL_MOB,
							target.posX + (x1 - x2) / distance * d / 5, target.height/2 + target.posY + (y1 - y2) / distance * d / 5,
							target.posZ + (z1 - z2) / distance * d / 5, R, G, B, new int[] {(int) R,(int) G,(int) B});
				}
			}
		}
		return false;
	}

	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		List elist = target.worldObj.getEntitiesWithinAABBExcludingEntity(attacker,
				target.getEntityBoundingBox().expand(2, 2, 2));
		for (int i = 0; i < elist.size(); i++) {
			if (elist.get(i) instanceof EntityLivingBase) {
				((EntityPlayer) attacker).attackTargetEntityWithCurrentItem(((EntityLivingBase) elist.get(i)));
			}
		}
		return true;
	}

	public boolean onBlockDestroyed(ItemStack stack, World worldIn, Block blockIn, BlockPos pos,
			EntityLivingBase playerIn) {
		return true;
	}

	public ItemSwordThunder(String string, ToolMaterial material) {
		super(material);
		this.setUnlocalizedName(string);
	}

	public EnumRarity getRarity(ItemStack stack) {
		return Main.PLUS;
	}
}
