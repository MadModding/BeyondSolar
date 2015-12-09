package com.madmodding.space.items.element;

import java.util.List;
import java.util.Random;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.madmodding.space.Main;
import com.madmodding.space.items.IFirstTick;

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

	private double attackDamage;

	@SideOnly(Side.CLIENT)
	public void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {
		ElementLib.getSubItems(itemIn, tab, subItems);
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
		if (stack.getTagCompound().getBoolean("anti"))
			multimap.put(Main.antimatterDamage.getAttributeUnlocalizedName(), new AttributeModifier(itemModifierUUID,
					"Weapon modifier", (double) stack.getTagCompound().getDouble("Damage"), 0));
		else if (stack.getItemDamage() == 42 || stack.getItemDamage() == 60
				|| (stack.getItemDamage() >= 83 && stack.getItemDamage() <= 99))
			multimap.put(Main.radiationDamage.getAttributeUnlocalizedName(), new AttributeModifier(itemModifierUUID,
					"Weapon modifier", (double) stack.getTagCompound().getDouble("Damage"), 0));
		else if (ElementLib.Elements[stack.getItemDamage()] == Element.SN)
			multimap.put(Main.fireDamage.getAttributeUnlocalizedName(), new AttributeModifier(itemModifierUUID,
					"Weapon modifier", (double) stack.getTagCompound().getDouble("Damage"), 0));
		else if (!ElementLib.toList(ElementLib.BaseElements).contains(ElementLib.Elements[stack.getItemDamage()])) {
			multimap.put(Main.divineDamage.getAttributeUnlocalizedName(), new AttributeModifier(itemModifierUUID,
					"Weapon modifier", (double) stack.getTagCompound().getDouble("Damage"), 0));
		} else
			multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(
					itemModifierUUID, "Weapon modifier", (double) stack.getTagCompound().getDouble("Damage"), 0));

		if (ElementLib.Elements[stack.getItemDamage()] == Element.EM) {
			multimap.put(Main.radiationDamage.getAttributeUnlocalizedName(), new AttributeModifier(itemModifierUUID,
					"Weapon modifier", (double) stack.getTagCompound().getDouble("Damage") / 4, 0));
		}
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

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return ElementLib.getRarity(stack);
	}

	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int renderPass) {
		return ElementLib.getColorFromItemStack(stack, renderPass, 3);
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

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer p_77624_2_, List list, boolean p_77624_4_) {
		list.add(ElementLib.getRarity(stack).rarityName);
		list.add("Made of Pure " + stack.getTagCompound().getString("Name"));
	}

	@Override
	public void onFirstTick(ItemStack stack) {
		ElementLib.setupSword(stack);
	}
}