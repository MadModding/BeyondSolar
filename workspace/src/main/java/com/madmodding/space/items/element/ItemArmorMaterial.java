package com.madmodding.space.items.element;

import java.util.List;

import com.madmodding.space.Main;
import com.madmodding.space.items.IColorable;
import com.madmodding.space.items.IFirstTick;
import com.madmodding.space.items.ModItems;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemArmorMaterial extends ItemArmor implements ISpecialArmor, IFirstTick, IColorable {

	public ItemArmorMaterial(String unlocalizedName, ArmorMaterial material, int slot) {
		super(material, slot, slot);
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(CreativeTabs.tabCombat);
	}

	ModelBiped model1 = null;
	ModelBiped model2 = null;
	ModelBiped model = null;

	/**
	 * Return whether the specified armor ItemStack has a color.
	 */
	public boolean hasColor(ItemStack stack) {
		if (!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());
		if (!stack.getTagCompound().hasKey("Name"))
			stack.getTagCompound().setString("Name", "Space Age");
		if (!stack.getTagCompound().hasKey("Mode"))
			stack.getTagCompound().setInteger("Mode", 0);
		if (!stack.getTagCompound().hasKey("Color0"))
			stack.getTagCompound().setInteger("Color0", 16777215);
		if (!stack.getTagCompound().hasKey("Unbreakable"))
			stack.getTagCompound().setBoolean("Unbreakable", true);
		return true;
	}

	/**
	 * Return the color for the specified armor ItemStack.
	 */
	public int getColor(ItemStack stack) {
		if (!stack.hasTagCompound())
			onFirstTick(stack);
		return stack.getTagCompound().getInteger("Color0");
	}

	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int renderPass) {
		if (!stack.hasTagCompound())
			onFirstTick(stack);
		return stack.getTagCompound().getInteger("Color" + renderPass);
	}

	/**
	 * Remove the color from the specified armor ItemStack.
	 */
	public void removeColor(ItemStack stack) {
		NBTTagCompound nbttagcompound = stack.getTagCompound();
		if (nbttagcompound != null) {
			if (nbttagcompound.hasKey("Color")) {
				nbttagcompound.removeTag("Color");
			}
		}

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
		NBTTagCompound nbttagcompound1 = stack.getTagCompound().getCompoundTag("display");
		if (!stack.getTagCompound().hasKey("display"))
			stack.getTagCompound().setTag("display", nbttagcompound1);
		nbttagcompound1.setInteger("color", stack.getTagCompound().getInteger("Color0"));

	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer p_77624_2_, List list, boolean p_77624_4_) {

	}

	public void onUpdate(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
		if (!stack.hasTagCompound())
			onFirstTick(stack);
	}

	public void onCreated(ItemStack stack, World world, EntityPlayer player) {

	}

	public String getArmorTexture(ItemStack Stack, Entity Entity, int slot, String type) {
		if (slot != 2)
			return "space:textures/models/armor/custom_layer_1.png";
		return "space:textures/models/armor/custom_layer_2.png";
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.EPIC;
	}

	@Override
	public void onFirstTick(ItemStack stack) {
		stack.setTagCompound(new NBTTagCompound());
		stack.getTagCompound().setString("Name", "Space Age");
		stack.getTagCompound().setInteger("Mode", 0);
		stack.getTagCompound().setInteger("Color0", 16777215);
		stack.getTagCompound().setInteger("Color1", 16777215);
		stack.getTagCompound().setBoolean("Unbreakable", true);
	}

	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack stack, DamageSource source, double damage,
			int slot) {
		return new ArmorProperties(1, 0.25, 100);
	}

	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
		return 0;
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {

	}
}
