package com.madmodding.space.items.inv.quantum;

import java.util.List;
import java.util.UUID;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.madmodding.space.Main;
import com.madmodding.space.gui.ModGuis;
import com.madmodding.space.items.IFirstTick;
import com.madmodding.space.items.element.Element;
import com.madmodding.space.items.element.ElementLib;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.ISpecialArmor.ArmorProperties;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemArmorQuantum extends ItemArmor implements ISpecialArmor, IFirstTick {
	public ItemArmorQuantum(String unlocalizedName, int type) {
		super(ArmorMaterial.DIAMOND, type, type);
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(Main.aliensTabTech);
		this.setMaxStackSize(1);
	}

	public int getColor(ItemStack stack) {
		return 0xDDDDFF;
	}

	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		if (type != "overlay") {
			return super.getArmorTexture(stack, entity, slot, type);
		} else {
			return "space:textures/models/armor/custom_layer_overlay.png";
		}

	}

	public Multimap getAttributeModifiers(ItemStack stack) {
		if (!stack.hasTagCompound())
			onFirstTick(stack);

		Multimap multimap = HashMultimap.create();
		if (stack.hasTagCompound() && stack.getTagCompound().hasKey("inv")) {
			ItemStack[] stacks = loadInventoryFromNBT((NBTTagList) stack.getTagCompound().getTag("inv"));
			if (stacks[0] != null)
				multimap.putAll(stacks[0].getItem().getAttributeModifiers(stacks[0]));
			if (stacks[1] != null)
				multimap.putAll(stacks[1].getItem().getAttributeModifiers(stacks[1]));
		}
		if (multimap.get(Main.armorPercent.getAttributeUnlocalizedName()) != null) {
			if (multimap.get(Main.armorPercent.getAttributeUnlocalizedName()).size() > 1) {
				float basic = 100;
				// System.out.println("Calculating Armor Percent");
				for (int i = 0; i < multimap.get(Main.armorPercent.getAttributeUnlocalizedName()).size(); i++) {
					float value = (float) ((AttributeModifier) multimap
							.get(Main.armorPercent.getAttributeUnlocalizedName()).toArray()[i]).getAmount();
					// System.out.println("Value " + i + " = " + value);
					value *= 4;
					value /= 100;
					// System.out.println("Value % off total " + i + " = " +
					// value);
					// System.out.println("Old Basic = " + basic);
					basic -= basic * value;
					// System.out.println("New Basic = " + basic);
				}
				UUID id = ((AttributeModifier) multimap.get(Main.armorPercent.getAttributeUnlocalizedName())
						.toArray()[0]).getID();
				multimap.removeAll(Main.armorPercent.getAttributeUnlocalizedName());
				multimap.put(Main.armorPercent.getAttributeUnlocalizedName(),
						new AttributeModifier(id, "Armor Modifier Quantum " + armorType, ((100 - basic) / 4), 0));
			}
		}
		return multimap;
	}

	public static ItemStack[] setInventorySlotContents(ItemStack[] stacks, int index, ItemStack stack) {
		stacks[index] = stack;
		if (stack != null && stack.stackSize > 64) {
			stack.stackSize = 64;
		}
		return stacks;
	}

	public ItemStack[] loadInventoryFromNBT(NBTTagList list) {
		ItemStack[] stacks = new ItemStack[2];
		int i;

		for (i = 0; i < 2; ++i) {
			stacks = setInventorySlotContents(stacks, i, (ItemStack) null);
		}

		for (i = 0; i < list.tagCount(); ++i) {
			NBTTagCompound nbttagcompound = list.getCompoundTagAt(i);
			int j = nbttagcompound.getByte("Slot") & 255;

			if (j >= 0 && j < 2) {
				stacks = setInventorySlotContents(stacks, j, ItemStack.loadItemStackFromNBT(nbttagcompound));
			}
		}
		return stacks;
	}

	public boolean onDroppedByPlayer(ItemStack stack, EntityPlayer player) {
		if (!stack.hasTagCompound() || !stack.getTagCompound().getBoolean("open"))
			return true;
		return false;
	}

	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if (!world.isRemote)
			player.openGui(Main.instance, ModGuis.MOD_QUANT_ARMOR_GUI_ID, world, (int) player.posX, (int) player.posY,
					(int) player.posZ);
		stack.setTagCompound(null);
		return stack;
	}

	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack stack, DamageSource source, double damage,
			int slot) {
		//System.out.println("Taking Damage");
		ArmorProperties properties = new ArmorProperties(0, 0, 0);
		if (!stack.hasTagCompound())
			onFirstTick(stack);
		if (stack.hasTagCompound() && stack.getTagCompound().hasKey("inv")) {
			ItemStack[] stacks = loadInventoryFromNBT((NBTTagList) stack.getTagCompound().getTag("inv"));
			ArmorProperties prop0 = null;
			ArmorProperties prop1 = null;
			if (stacks[0] != null && stacks[0].getItem() instanceof ISpecialArmor) {
				prop0 = ((ISpecialArmor) stacks[0].getItem()).getProperties(player, stack, source, damage, slot);
				//System.out.println("Stack 0: " + prop0.AbsorbRatio);
			} else if (stacks[0] != null && stacks[0].getItem() instanceof ItemArmor) {
				ItemArmor armor = (ItemArmor) stacks[0].getItem();
				prop0 = new ArmorProperties(0, armor.damageReduceAmount / 25D, Integer.MAX_VALUE);
				//System.out.println("Stack 0: " + prop0.AbsorbRatio);
			} // else System.out.println("Stack 0: nope");

			if (stacks[1] != null && stacks[1].getItem() instanceof ISpecialArmor) {
				prop1 = ((ISpecialArmor) stacks[1].getItem()).getProperties(player, stack, source, damage, slot);
				//System.out.println("Stack 1: " + prop1.AbsorbRatio);
			} else if (stacks[1] != null && stacks[1].getItem() instanceof ItemArmor) {
				ItemArmor armor = (ItemArmor) stacks[1].getItem();
				prop1 = new ArmorProperties(0, armor.damageReduceAmount / 25D, Integer.MAX_VALUE);
				//System.out.println("Stack 1: " + prop1.AbsorbRatio);
			} // else System.out.println("Stack 1: nope");

			if (prop0 != null && prop1 == null)
				properties = prop0;
			if (prop1 != null && prop0 == null)
				properties = prop1;
			if (prop0 != null && prop1 != null) {
				double perc0 = prop0.AbsorbRatio;
				double perc1 = prop1.AbsorbRatio;
				properties = new ArmorProperties(1, (1 - (1 - perc0) * (1 - perc1)), Integer.MAX_VALUE);
			}
		}
		return properties;
	}

	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
		return 0;
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
		if (!stack.hasTagCompound())
			onFirstTick(stack);
		if (stack.hasTagCompound() && stack.getTagCompound().hasKey("inv")) {
			ItemStack[] stacks = loadInventoryFromNBT((NBTTagList) stack.getTagCompound().getTag("inv"));
			if (stacks[0] != null && stacks[0].getItem() instanceof ISpecialArmor) {
				((ISpecialArmor) stacks[0].getItem()).damageArmor(entity, stacks[0], source, damage, slot);
			}
			if (stacks[1] != null && stacks[1].getItem() instanceof ISpecialArmor) {
				((ISpecialArmor) stacks[1].getItem()).damageArmor(entity, stacks[1], source, damage, slot);
			}
		}
	}

	public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
		boolean ft = false;
		if (!stack.hasTagCompound())
			ft = true;
		if (stack.hasTagCompound() && stack.getTagCompound().hasKey("inv")) {
			ItemStack[] stacks = loadInventoryFromNBT((NBTTagList) stack.getTagCompound().getTag("inv"));
			if (stacks[0] != null) {
				stacks[0].getItem().onArmorTick(world, player, stacks[0]);
			}
			if (stacks[1] != null) {
				stacks[1].getItem().onArmorTick(world, player, stacks[1]);
			}
		}
		if (ft)
			onFirstTick(stack);
	}

	@Override
	public void onFirstTick(ItemStack stack) {
		if (stack.hasTagCompound() && stack.getTagCompound().hasKey("inv")) {
			ItemStack[] stacks = loadInventoryFromNBT((NBTTagList) stack.getTagCompound().getTag("inv"));
			if (stacks[0] != null && stacks[0].getItem() instanceof IFirstTick) {
				((IFirstTick) stacks[0].getItem()).onFirstTick(stacks[0]);
			}
			if (stacks[1] != null && stacks[1].getItem() instanceof IFirstTick) {
				((IFirstTick) stacks[1].getItem()).onFirstTick(stacks[1]);
			}
		}
	}

	public void onUpdate(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
		if (!stack.hasTagCompound())
			onFirstTick(stack);
		if (stack.hasTagCompound() && stack.getTagCompound().hasKey("inv")) {
			ItemStack[] stacks = loadInventoryFromNBT((NBTTagList) stack.getTagCompound().getTag("inv"));
			if (stacks[0] != null) {
				stacks[0].updateAnimation(world, entity, p_77663_4_, p_77663_5_);
			}
			if (stacks[1] != null) {
				stacks[1].updateAnimation(world, entity, p_77663_4_, p_77663_5_);
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer p_77624_2_, List list, boolean p_77624_4_) {
		if (!stack.hasTagCompound())
			onFirstTick(stack);
	}
}