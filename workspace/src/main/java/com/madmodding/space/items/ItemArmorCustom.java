package com.madmodding.space.items;

import java.util.List;

import com.madmodding.space.Main;
import com.madmodding.space.client.models.ModelSpaceSuit;
import com.madmodding.space.items.element.ElementLib;
import com.madmodding.space.items.element.EnumElement;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemArmorCustom extends ItemArmor implements ISpecialArmor, IColorable {

	public ItemArmorCustom(String unlocalizedName, ArmorMaterial material, int slot) {
		super(material, slot, slot);
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(CreativeTabs.tabCombat);
	}

	ModelBiped model1 = null;
	ModelBiped model2 = null;
	ModelBiped model = null;

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {
		int type = ((ItemArmor) itemStack.getItem()).armorType;
		if (this.model1 == null) {
			this.model1 = new ModelSpaceSuit(1f, true, true, false, true);
		}
		if (this.model2 == null) {
			this.model2 = new ModelSpaceSuit(0.5f, false, false, true, false);
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
			this.model.isSneak = entityLiving.isSneaking();
			this.model.swingProgress = entityLiving.swingProgress;
			this.model.isRiding = entityLiving.isRiding();
			this.model.isChild = entityLiving.isChild();

			this.model.aimedBow = false;
			this.model.heldItemRight = (entityLiving.getHeldItem() != null ? 1 : 0);

			if ((entityLiving instanceof EntityPlayer)) {
				if (((EntityPlayer) entityLiving).getItemInUseDuration() > 0) {
					EnumAction enumaction = ((EntityPlayer) entityLiving).getItemInUse().getItemUseAction();
					if (enumaction == EnumAction.BLOCK) {
						this.model.heldItemRight = 3;
					} else if (enumaction == EnumAction.BOW) {
						this.model.aimedBow = true;
					}
				}
			}
		}

		return model;

	}

	/**
	 * Return whether the specified armor ItemStack has a color.
	 */
	public boolean hasColor(ItemStack stack) {
		if (!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());
		if (!stack.getTagCompound().hasKey("Name"))
			stack.getTagCompound().setString("Name", "Space Suit");
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
			stack.setTagCompound(new NBTTagCompound());
		if (!stack.getTagCompound().hasKey("Name"))
			stack.getTagCompound().setString("Name", "Space Suit");
		if (!stack.getTagCompound().hasKey("Mode"))
			stack.getTagCompound().setInteger("Mode", 0);
		if (!stack.getTagCompound().hasKey("Color0"))
			stack.getTagCompound().setInteger("Color0", 16777215);
		if (!stack.getTagCompound().hasKey("Unbreakable"))
			stack.getTagCompound().setBoolean("Unbreakable", true);
		return stack.getTagCompound().getInteger("Color");
	}

	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int renderPass) {
		if (!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());
		if (!stack.getTagCompound().hasKey("Name"))
			stack.getTagCompound().setString("Name", "Space Suit");
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

		if (!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());
		if (!stack.getTagCompound().hasKey("Name"))
			stack.getTagCompound().setString("Name", "Space Suit");
		if (!stack.getTagCompound().hasKey("Mode"))
			stack.getTagCompound().setInteger("Mode", 0);
		if (!stack.getTagCompound().hasKey("Color0"))
			stack.getTagCompound().setInteger("Color0", 16777215);
		if (!stack.getTagCompound().hasKey("Unbreakable"))
			stack.getTagCompound().setBoolean("Unbreakable", true);
		stack.getTagCompound().setInteger("Color", color);

	}

	public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
		if (!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());
		if (!stack.getTagCompound().hasKey("Color0"))
			stack.getTagCompound().setInteger("Color0", 16777215);
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
			stack.setTagCompound(new NBTTagCompound());
		if (!stack.getTagCompound().hasKey("Unbreakable"))
			stack.getTagCompound().setBoolean("Unbreakable", true);
	}

	public void onCreated(ItemStack stack, World world, EntityPlayer player) {

	}

	public String getArmorTexture(ItemStack Stack, Entity Entity, int slot, String type) {
		if (type != "overlay") {
			return "space:textures/models/armor/SpacePNG.png";
		} else {
			return "space:textures/models/armor/custom_layer_overlay.png";
		}
		// if (slot != 2)
		// return "space:textures/models/armor/custom_layer_1.png";
		// return "space:textures/models/armor/custom_layer_2.png";
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.EPIC;
	}

	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack stack, DamageSource source, double damage,
			int slot) {
		if (stack.getItem() == ModItems.ColorBoots && source == DamageSource.fall)
			return new ArmorProperties(1, 1, Integer.MAX_VALUE);
		if (source == Main.inSpace)
			return new ArmorProperties(1, 0.3, Integer.MAX_VALUE);

		return new ArmorProperties(1, 0.2, 100);
	}

	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
		return 0;
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {

	}
}
