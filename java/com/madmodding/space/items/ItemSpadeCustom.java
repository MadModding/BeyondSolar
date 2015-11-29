package com.madmodding.space.items;

import java.util.Random;

import com.madmodding.space.Main;

import net.minecraft.block.Block;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSpadeCustom extends ItemSpade implements IColorable {
	/**
	 * returns the action that specifies what animation to play when the items
	 * is being used
	 */
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BLOCK;
	}
	@Override
	public float getDigSpeed(ItemStack stack, net.minecraft.block.state.IBlockState state) {
		for (String type : getToolClasses(stack)) {

			if (state.getBlock().isToolEffective(type, state)) {
				if (stack.getTagCompound().getInteger("Mode") == 0)
					return efficiencyOnProperMaterial / 2;
				else
					return efficiencyOnProperMaterial * 2;
			}
		}
		return super.getDigSpeed(stack, state);
	}

	/**
	 * How long it takes to use or consume an item
	 */
	public int getMaxItemUseDuration(ItemStack stack) {
		return 72000;
	}

	/**
	 * Called whenever this item is equipped and the right mouse button is
	 * pressed. Args: itemStack, world, entityPlayer
	 */
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
		playerIn.setItemInUse(itemStackIn, this.getMaxItemUseDuration(itemStackIn));
		return itemStackIn;
	}

	private double attackDamage;

	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return false;
	}

	public ItemSpadeCustom(String string, ToolMaterial material) {
		super(material);
		this.setUnlocalizedName(string);
	}

	public String getItemStackDisplayName(ItemStack stack) {
		if (!stack.hasTagCompound() || !stack.getTagCompound().hasKey("Name")
				|| stack.getTagCompound().getString("Name").contains("Hidden"))
			return ("" + StatCollector.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
		else {
			if (stack.getTagCompound().getInteger("Mode") == 0)
				return stack.getTagCompound().getString("Name") + " Shovel";
			else if (stack.getTagCompound().getInteger("Mode") == 1)
				return stack.getTagCompound().getString("Name") + " Spade";
			else
				return stack.getTagCompound().getString("Name") + " Axe";
		}
	}

	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer entity, int timeLeft) {
		if (!entity.isSneaking()) {
			stack.getTagCompound().setInteger("Mode", stack.getTagCompound().getInteger("Mode") + 1);
			if (stack.getTagCompound().getInteger("Mode") > 1)
				stack.getTagCompound().setInteger("Mode", 0);
			Random rand = new Random();
			int l = stack.getTagCompound().getInteger("Color0");
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

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.EPIC;
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
		if (!stack.getTagCompound().hasKey("Unbreakable"))
			stack.getTagCompound().setBoolean("Unbreakable", true);
		return stack.getTagCompound().getInteger("Color" + renderPass);

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
		if (!stack.getTagCompound().hasKey("Unbreakable"))
			stack.getTagCompound().setBoolean("Unbreakable", true);

	}

	public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player) {
		return false;
	}
}
