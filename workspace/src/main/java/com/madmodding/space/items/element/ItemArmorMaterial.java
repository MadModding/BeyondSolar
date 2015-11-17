package com.madmodding.space.items.element;

import java.util.List;

import com.madmodding.space.client.models.ModelSpaceSuit;
import com.madmodding.space.items.IFirstTick;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemArmorMaterial extends ItemArmor implements ISpecialArmor, IFirstTick {

	public ItemArmorMaterial(String unlocalizedName, ArmorMaterial material, int slot) {
		super(material, slot, slot);
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(CreativeTabs.tabCombat);
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
		NBTTagCompound nbttagcompound1 = stack.getTagCompound().getCompoundTag("display");
		if (!stack.getTagCompound().hasKey("display"))
			stack.getTagCompound().setTag("display", nbttagcompound1);
		nbttagcompound1.setInteger("color", clr);
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
		NBTTagCompound nbttagcompound1 = stack.getTagCompound().getCompoundTag("display");
		if (!stack.getTagCompound().hasKey("display"))
			stack.getTagCompound().setTag("display", nbttagcompound1);
		nbttagcompound1.setInteger("color", stack.getTagCompound().getInteger("Color0"));
		if (ElementLib.isSpecial(player.getName()) == 0 || ElementLib.isSpecial(player.getName()) == 5) {
			if (!ElementLib.toList(ElementLib.NonResElements).contains(ElementLib.Elements[stack.getItemDamage()])) {
				if (ElementLib.Elements[stack.getItemDamage()] != EnumElement.SN
						|| ElementLib.isSpecial(player.getName()) != 5)
					stack.stackSize--;
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

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer p_77624_2_, List list, boolean p_77624_4_) {

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
			if (ElementLib.Elements[stack.getItemDamage() % ElementLib.Elements.length] == EnumElement.MD) {
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
		if (ElementLib.Elements[stack.getItemDamage()] == EnumElement.MD) {
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
		stack.setTagCompound(new NBTTagCompound());
		int i = (int) ((stack.getItemDamage()) / ElementLib.Elements.length) + 1;
		stack.setItemDamage(stack.getItemDamage() - (i - 1) * ElementLib.Elements.length);
		boolean neg = i % 2 == 0;
		boolean anti = i > 2;
		if (!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());
		stack.getTagCompound().setBoolean("neg", neg);
		stack.getTagCompound().setBoolean("anti", anti);
		{
			stack.getTagCompound().setString("Name", ElementLib.getName(stack));
		}
		stack.getTagCompound().setInteger("Color0", ElementLib.Elements[stack.getItemDamage()].getColor());
		stack.getTagCompound().setInteger("Color1", 16777215);
		stack.getTagCompound().setInteger("Type", ElementLib.Elements[stack.getItemDamage()].getNumber());
		stack.getTagCompound().setInteger("color", ElementLib.Elements[stack.getItemDamage()].getColor());
		double prot = ElementLib.Elements[stack.getItemDamage()].getHardness();
		stack.getTagCompound().setDouble("Prot", prot);
		stack.getTagCompound().setBoolean("Unbreakable", true);
	}

	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack stack, DamageSource source, double damage,
			int slot) {

		return new ArmorProperties(1,
				(stack.getTagCompound().getDouble("Prot") - 1) / stack.getTagCompound().getDouble("Prot") / 4,
				(int) (stack.getTagCompound().getDouble("Prot") * 10));
	}

	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
		return 0;
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {

	}
}
