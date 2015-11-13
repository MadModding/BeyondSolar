package com.madmodding.space;

import java.util.ArrayList;
import java.util.List;

import com.madmodding.space.enchant.ModEnchants;
import com.madmodding.space.items.element.ElementLib;
import com.madmodding.space.items.element.ItemArmorMaterial;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class CommandEmpower implements ICommand {
	private final List aliases;

	public CommandEmpower() {
		aliases = new ArrayList();
		aliases.add("empower");
		aliases.add("enrage");
	}

	@Override
	public int compareTo(Object o) {
		return 0;
	}

	@Override
	public String getName() {
		return "empower";
	}

	@Override
	public String getCommandUsage(ICommandSender var1) {
		return "empower <level>";
	}

	@Override
	public List getAliases() {
		return this.aliases;
	}

	@Override
	public void execute(ICommandSender sender, String[] args) throws CommandException {

		World world = sender.getEntityWorld();

		if (world.isRemote) {
		} else {
			try {
				int i = 1;
				if (args.length > 0) {
					i = Integer.parseInt(args[0]);
					boolean f = i < 0;
					if (f) {
						i *= -1;
					}
				}
				if (ElementLib.isSpecial((((EntityPlayer) sender)).getName()) == 1) {
					if (i == 1)
						equip(((EntityPlayer) sender), 103, i);
					if (i == 2)
						equip(((EntityPlayer) sender), 107, i);
					if (i == 3)
						equip(((EntityPlayer) sender), 105, i);
					if (i == 4)
						equip(((EntityPlayer) sender), 106, i);
					if (i == 5)
						equip(((EntityPlayer) sender), 102, i);
					if (i >= 6)
						equip(((EntityPlayer) sender), 104, i - 5);
					if (i == 0) {
						equip(((EntityPlayer) sender), 0, i);
					}
				}
				if (ElementLib.isSpecial((((EntityPlayer) sender)).getName()) == 2) {
					equip(((EntityPlayer) sender), 104, i);
					if (i == 0) {
						equip(((EntityPlayer) sender), 0, i);
					}
				}
				if (ElementLib.isSpecial((((EntityPlayer) sender)).getName()) == 3) {
					equip(((EntityPlayer) sender), 105, i);
					if (i == 0) {
						equip(((EntityPlayer) sender), 0, i);
					}
				}
				if (ElementLib.isSpecial((((EntityPlayer) sender)).getName()) == 4) {
					equip(((EntityPlayer) sender), 106, i);
					if (i == 0) {
						equip(((EntityPlayer) sender), 0, i);
					}
				}
				if (ElementLib.isSpecial((((EntityPlayer) sender)).getName()) == 5) {
					equip(((EntityPlayer) sender), 102, i);
					if (i == 0) {
						equip(((EntityPlayer) sender), 0, i);
					}
				}

			} catch (NumberFormatException e) {
				throw new WrongUsageException("commands.empower.usage", new Object[0]);
			}
		}
	}

	private static void equip(EntityPlayer player, int meta, int level) {
		(player).inventory.armorInventory[3] = new ItemStack(ElementLib.ElementHelm, 1, meta);
		(player).inventory.armorInventory[2] = new ItemStack(ElementLib.ElementChest, 1, meta);
		(player).inventory.armorInventory[1] = new ItemStack(ElementLib.ElementLegs, 1, meta);
		(player).inventory.armorInventory[0] = new ItemStack(ElementLib.ElementBoots, 1, meta);
		if (meta == 104 && level > 1) {
			if (level >= 32768)
				level = 32768;
			if (level >= 1000)
				level = 9002;
			((ItemArmorMaterial) player.inventory.armorInventory[3].getItem())
					.onFirstTick(player.inventory.armorInventory[3]);
			((ItemArmorMaterial) player.inventory.armorInventory[2].getItem())
					.onFirstTick(player.inventory.armorInventory[2]);
			((ItemArmorMaterial) player.inventory.armorInventory[1].getItem())
					.onFirstTick(player.inventory.armorInventory[1]);
			((ItemArmorMaterial) player.inventory.armorInventory[0].getItem())
					.onFirstTick(player.inventory.armorInventory[0]);
			addEnchantment((player).inventory.armorInventory[3], ModEnchants.madhat, level - 1);
			addEnchantment((player).inventory.armorInventory[2], ModEnchants.madhat, level - 1);
			addEnchantment((player).inventory.armorInventory[1], ModEnchants.madhat, level - 1);
			addEnchantment((player).inventory.armorInventory[0], ModEnchants.madhat, level - 1);
		}
		if (meta == 103) {
			((ItemArmorMaterial) player.inventory.armorInventory[3].getItem())
					.onFirstTick(player.inventory.armorInventory[3]);
			((ItemArmorMaterial) player.inventory.armorInventory[2].getItem())
					.onFirstTick(player.inventory.armorInventory[2]);
			((ItemArmorMaterial) player.inventory.armorInventory[1].getItem())
					.onFirstTick(player.inventory.armorInventory[1]);
			((ItemArmorMaterial) player.inventory.armorInventory[0].getItem())
					.onFirstTick(player.inventory.armorInventory[0]);
			addEnchantment((player).inventory.armorInventory[3], ModEnchants.arideus, 32767);
			addEnchantment((player).inventory.armorInventory[2], ModEnchants.arideus, 32767);
			addEnchantment((player).inventory.armorInventory[1], ModEnchants.arideus, 32767);
			addEnchantment((player).inventory.armorInventory[0], ModEnchants.arideus, 32767);
		}
	}

	public static void addEnchantment(ItemStack stack, Enchantment ench, int level) {
		if (!stack.hasTagCompound()) {
			stack.setTagCompound(new NBTTagCompound());
		}

		if (!stack.getTagCompound().hasKey("ench", 9)) {
			stack.getTagCompound().setTag("ench", new NBTTagList());
		}

		NBTTagList nbttaglist = stack.getTagCompound().getTagList("ench", 10);
		NBTTagCompound nbttagcompound = new NBTTagCompound();
		nbttagcompound.setShort("id", (short) ench.effectId);
		nbttagcompound.setShort("lvl", (short) (level));
		nbttaglist.appendTag(nbttagcompound);
	}

	@Override
	public boolean canCommandSenderUse(ICommandSender var1) {
		return true;
	}

	@Override
	public List addTabCompletionOptions(ICommandSender var1, String[] var2, BlockPos pos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] var1, int var2) {
		// TODO Auto-generated method stub
		return false;
	}

}