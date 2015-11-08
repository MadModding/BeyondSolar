package com.madmodding.space.items;

import java.util.List;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.madmodding.space.ElementLib;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
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
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemAxeMaterial extends ItemAxe implements IFirstTick {

	public static final double[] hard = new double[] { 0, 0, 0.6, 5.5, 9.5, 1.5, 0, 0, 0, 0, 0.5, 2.5, 3, 6.5, 0, 2.0,
			0, 0, 0.4, 1.5, 0, 6.0, 7.0, 8.5, 6.0, 4.0, 5.0, 4.0, 3.0, 2.5, 1.5, 6.0, 3.5, 2.0, 0, 0, 0.3, 1.5, 0, 5.0,
			6.0, 5.5, 0, 6.5, 6.0, 5.0, 2.5, 2.0, 1.0, 1.5, 3.0, 2.0, 0, 0, 0.2, 1.0, 2.5, 2.5, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 5.5, 6.5, 7.5, 7.0, 7.0, 6.5, 3.5, 2.5, 0, 1.0, 1.5, 2.5, 0, 0, 0, 0, 0, 0, 3.0, 0, 6.0, 0,
			0, 0, 0, 0, 0, 0, 0, 11, 20 };
	private double attackDamage;

	@SideOnly(Side.CLIENT)
	public void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {
		for (int i = 0; i < ItemFragment.names.length; i++) {
			if (hard[i] != 0 && ItemRefined.types[i] == 1) {
				for (int t = 0; t < 4; t++) {
					ItemStack stack = new ItemStack(itemIn, 1, i + (t * ItemFragment.names.length));
					this.onFirstTick(stack);
					subItems.add(stack);
				}
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return false;
	}

	public ItemAxeMaterial(String string, ToolMaterial material) {
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
	public float getStrVsBlock(ItemStack stack, Block block)
    {
        return block.getMaterial() != Material.wood && block.getMaterial() != Material.plants && block.getMaterial() != Material.vine ? super.getStrVsBlock(stack, block) : (float) stack.getTagCompound().getDouble("Speed");
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
			return stack.getTagCompound().getString("Name") + " Axe";
		}
	}

	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}

	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int renderPass) {
		if (!stack.hasTagCompound())
			onFirstTick(stack);
		double clr = stack.getTagCompound().getInteger("color");
		if (clr == -1)
			clr = ElementLib.getColor(Minecraft.getSystemTime());
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
		if (renderPass != 1) {
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

		int i = (int) ((stack.getItemDamage()) / ItemFragment.names.length) + 1;
		stack.setItemDamage(stack.getItemDamage() - (i - 1) * ItemFragment.names.length);
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
		stack.getTagCompound().setInteger("Color1", 0xC89632);
		stack.getTagCompound().setInteger("color", ItemFragment.colors[stack.getItemDamage()]);
		double dmg = hard[stack.getItemDamage()];
		dmg *= 0.5;
		if (anti)
			dmg *= 2;
		stack.getTagCompound().setDouble("Damage", dmg);
		double spd = hard[stack.getItemDamage()];
		if (anti)
			spd *= 2;
		spd *= 1.8;
		stack.getTagCompound().setDouble("Speed", spd);
		stack.getTagCompound().setBoolean("Unbreakable", true);
	}
}
