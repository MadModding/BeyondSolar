package com.madmodding.space.items;

import java.util.List;
import java.util.Random;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
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

public class ItemSwordMaterial extends ItemSword implements IFirstTick {

	public static final double[] hard = new double[] { 0, 0, 0.6, 5.5, 9.5, 1.5, 0, 0, 0, 0, 0.5, 2.5, 3, 6.5, 0, 2.0,
			0, 0, 0.4, 1.5, 0, 6.0, 7.0, 8.5, 6.0, 4.0, 5.0, 4.0, 3.0, 2.5, 1.5, 6.0, 3.5, 2.0, 0, 0, 0.3, 1.5, 0, 5.0,
			6.0, 5.5, 0, 6.5, 6.0, 5.0, 2.5, 2.0, 1.0, 1.5, 3.0, 2.0, 0, 0, 0.2, 1.0, 2.5, 2.5, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 5.5, 6.5, 7.5, 7.0, 7.0, 6.5, 3.5, 2.5, 0, 1.0, 1.5, 2.5, 0, 0, 0, 0, 0, 0, 3.0, 0, 6.0, 0,
			0, 0, 0, 0, 0, 0, 0, 11, 0 };
	private double attackDamage;

	@SideOnly(Side.CLIENT)
	public void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {
		for (int i = 0; i < 101; i++) {
			if (hard[i] != 0 && ItemRefined.types[i] == 1) {
				subItems.add(new ItemStack(itemIn, 1, i));
				subItems.add(new ItemStack(itemIn, 1, i + 101));
				subItems.add(new ItemStack(itemIn, 1, i + 202));
				subItems.add(new ItemStack(itemIn, 1, i + 303));
				}
		}
	}

	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return false;
	}

	public ItemSwordMaterial(String string, ToolMaterial material) {
		super(material);
		this.setUnlocalizedName(string);
		this.attackDamage = 4.0F + material.getDamageVsEntity();
	}

	public Multimap getAttributeModifiers(ItemStack stack) {
		if (!stack.hasTagCompound())
			onFirstTick(stack);
		Multimap multimap = HashMultimap.create();
		multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(
				itemModifierUUID, "Weapon modifier", (double) stack.getTagCompound().getDouble("Damage"), 0));

		return multimap;
	}

	public double getAttackDamage() {
		return attackDamage;
	}

	public String getItemStackDisplayName(ItemStack stack) {
		if (!stack.hasTagCompound())
			onFirstTick(stack);
		if (!stack.hasTagCompound() || !stack.getTagCompound().hasKey("Name")
				|| stack.getTagCompound().getString("Name").contains("Hidden"))
			return ("" + StatCollector.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
		else {
			return stack.getTagCompound().getString("Name") + " Sword";
		}
	}

	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}

	public int getColor(long time) {
		double t = (int) ((time / 8) % 300);
		int clr = 0;
		int r = 0;
		int g = 0;
		int b = 0;
		if (t > 250 || t < 150) { // 000 - 100
			if (t > 250)
				r = (int) ((t - 250d) / 50d * 255d);
			else if (t < 150 && t > 100)
				r = (int) ((150d - t) / 50d * 255d);
			else
				r = 255;
		}
		if (t > 50 && t < 250) { // 100 - 200
			if (t > 200 && t < 250)
				g = (int) (-(t - 250d) / 50d * 255d);
			else if (t < 100 && t > 50)
				g = (int) ((t - 50d) / 50d * 255d);
			else
				g = 255;
		}
		if (t > 150 || t < 50) { // 200 - 300
			if (t < 50 && t > 0)
				b = (int) ((50d - t) / 50d * 255d);
			else if (t > 150 && t < 200)
				b = (int) ((200d - t) / 50d * 255d);
			else
				b = 255;

		}
		clr += r * 65536;
		clr += g * 256;
		clr += b;
		return clr;
	}

	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int renderPass) {
		if (!stack.hasTagCompound())
			onFirstTick(stack);
		double clr = stack.getTagCompound().getInteger("color");
		if (clr == -1)
			clr = getColor(Minecraft.getSystemTime());
		if (stack.getTagCompound().getBoolean("anti")) {
			double r = ((int) clr / 65536d);
			clr -= r * 65536;
			double g = ((int) clr / 256d);
			clr -= g * 256;
			double b = ((int) clr);
			clr -= b;
			r = 255 - r;
			g = 255 - g;
			b = 255 - b;
			clr += r * 65536;
			clr += g * 256;
			clr += b;
		}
		if (renderPass != 3) {
			return (int) clr;
		}
		if (stack.getTagCompound().getBoolean("neg"))
			return 0x111111;
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
			onFirstTick(stack);
	}

	public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player) {
		return false;
	}

	@Override
	public void onFirstTick(ItemStack stack) {

		int i = (int) ((stack.getItemDamage()) / 101) + 1;
		stack.setItemDamage(stack.getItemDamage() - (i - 1) * 101);
		boolean neg = i % 2 == 0;
		boolean anti = i > 2;
		if (!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());
		stack.getTagCompound().setBoolean("neg", neg);
		stack.getTagCompound().setBoolean("anti", anti);
		if (!stack.getTagCompound().hasKey("Name")) {
			String name = "";
			if (stack.getTagCompound().getBoolean("neg"))
				name += "Negative ";
			if (stack.getTagCompound().getBoolean("anti"))
				name += "Anti-";
			name += ItemFragment.names[stack.getItemDamage()];
			name += "";
			stack.getTagCompound().setString("Name", name);
		}
		stack.getTagCompound().setInteger("Mode", 0);
		stack.getTagCompound().setInteger("Color3", 0xC89632);
		stack.getTagCompound().setInteger("color", ItemFragment.colors[stack.getItemDamage()]);
		double dmg = hard[stack.getItemDamage()];
		if (anti)
			dmg *= 2;
		stack.getTagCompound().setDouble("Damage", dmg);
		stack.getTagCompound().setDouble("Speed", 1.2d * hard[stack.getItemDamage()]);
		stack.getTagCompound().setBoolean("Unbreakable", true);
	}
}
