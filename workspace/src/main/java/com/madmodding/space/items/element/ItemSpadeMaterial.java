package com.madmodding.space.items.element;

import java.util.List;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
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
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSpadeMaterial extends ItemSpade implements IFirstTick {

	private double attackDamage;

	@SideOnly(Side.CLIENT)
	public void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {
		ElementLib.getSubItems(itemIn, tab, subItems);
	}

	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return false;
	}

	public ItemSpadeMaterial(String string, ToolMaterial material) {
		super(material);
		this.setUnlocalizedName(string);
	}

	public Multimap getAttributeModifiers(ItemStack stack) {
		if (!stack.hasTagCompound())
			onFirstTick(stack);
		Multimap multimap = HashMultimap.create();
		multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(
				itemModifierUUID, "Weapon modifier", (double) stack.getTagCompound().getDouble("Damage"), 0));

		return multimap;
	}

	@Override
	public float getDigSpeed(ItemStack stack, net.minecraft.block.state.IBlockState state) {
		for (String type : getToolClasses(stack)) {
			if (state.getBlock().isToolEffective(type, state)) {
				return (float) stack.getTagCompound().getDouble("Speed");
			}
		}
		return super.getDigSpeed(stack, state);
	}

	public String getItemStackDisplayName(ItemStack stack) {
		if (!stack.hasTagCompound())
			onFirstTick(stack);
		if (!stack.hasTagCompound() || !stack.getTagCompound().hasKey("Name")
				|| stack.getTagCompound().getString("Name").contains("Hidden"))
			return ("" + StatCollector.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
		else {
			return stack.getTagCompound().getString("Name") + " Shovel";
		}
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return ElementLib.getRarity(stack);
	}

	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int renderPass) {
		return ElementLib.getColorFromItemStack(stack, renderPass, 1);
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
		list.add("Mining Speed: " + ((double) (int) (100 * stack.getTagCompound().getDouble("Speed")))/100);
	}

	@Override
	public void onFirstTick(ItemStack stack) {

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
		stack.getTagCompound().setInteger("Mode", 0);
		if (ElementLib.toList(ElementLib.BaseElements).contains(ElementLib.Elements[stack.getItemDamage()]))
			stack.getTagCompound().setInteger("Color1", 0xC89632);
		else
			stack.getTagCompound().setInteger("Color1", 0x444444);
		stack.getTagCompound().setInteger("color", ElementLib.Elements[stack.getItemDamage()].getColor());
		double dmg = ElementLib.Elements[stack.getItemDamage()].getHardness();
		dmg *= 0.5;
		if (anti)
			dmg *= 2;
		stack.getTagCompound().setDouble("Damage", dmg);
		double spd = ElementLib.Elements[stack.getItemDamage()].getHardness();
		if (anti)
			spd *= 2;
		spd *= 1.8;
		stack.getTagCompound().setDouble("Speed", spd);
		stack.getTagCompound().setBoolean("Unbreakable", true);
	}
}
