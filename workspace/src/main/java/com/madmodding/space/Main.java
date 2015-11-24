package com.madmodding.space;

import com.madmodding.space.blocks.ModBlocks;
import com.madmodding.space.cell.BiomeGenCell;
import com.madmodding.space.items.ModItems;
import com.madmodding.space.items.element.ElementLib;
import com.madmodding.space.space.BiomeGenSpace;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

@Mod(modid = Main.MODID, name = Main.MODNAME, version = Main.VERSION)
public class Main {
	public static final IAttribute jump = (new RangedAttribute((IAttribute)null, "generic.jump", 0.0D, 0.0D, Double.MAX_VALUE)).setDescription("Jump").setShouldWatch(true);
    public static final EnumRarity COMMON = EnumRarity.COMMON;
	public static final EnumRarity UNCOMMON = EnumRarity.UNCOMMON;
	public static final EnumRarity RARE = EnumRarity.RARE;
	public static final EnumRarity EPIC = EnumRarity.EPIC;
	public static final EnumRarity ULTRARARE = EnumHelper.addRarity("SpaceRareUltra", EnumChatFormatting.GOLD,
			"Ultra Rare");
	public static final EnumRarity LEGENDARY = EnumHelper.addRarity("SpaceLegend", EnumChatFormatting.DARK_PURPLE,
			"Legendary");
	public static final EnumRarity PLUS = EnumHelper.addRarity("SpaceBeyond", EnumChatFormatting.GRAY,
			"Beyond Legendary");
	public static final int GUI = 0;
	
	public static final String MODID = "space";
	public static final String MODNAME = "Beyond Solar Mod";
	public static final String VERSION = "0.1.8";
	public static SimpleNetworkWrapper network;
	public static SimpleNetworkWrapper network2;
	public static final BiomeGenBase space = (new BiomeGenSpace(71)).setColor(8421631).setBiomeName("Space")
			.setDisableRain();
	public static final BiomeGenBase cell = (new BiomeGenCell(72)).setColor(8421631).setBiomeName("Cell")
			.setDisableRain();
	@Instance
	public static Main instance = new Main();
	public static DamageSource inSpace = (new DamageSource("inSpace")).setDamageBypassesArmor();

	@SidedProxy(clientSide = "com.madmodding.space.ClientProxy", serverSide = "com.madmodding.space.ServerProxy")
	public static CommonProxy proxy;
	public static CreativeTabs aliensTabTech = new CreativeTabs("Aliens1") {
		public ItemStack getIconItemStack() {
			return new ItemStack(ModItems.evilstaff, 1, 0);
		}

		@Override
		public Item getTabIconItem() {
			return Items.apple;
		}
	};
	public static CreativeTabs aliensTabBlock = new CreativeTabs("Aliens2") {
		public Item getTabIconItem() {
			return Item.getItemFromBlock(ModBlocks.meteor);
		}
	};
	public static CreativeTabs aliensTabUnRef = new CreativeTabs("Aliens3") {
		public ItemStack getIconItemStack() {
			return new ItemStack(ElementLib.Fragment, 1, 404);
		}

		@Override
		public Item getTabIconItem() {
			return Items.apple;
		}
	};
	public static CreativeTabs aliensTabRef = new CreativeTabs("Aliens4") {
		public ItemStack getIconItemStack() {
			return new ItemStack(ElementLib.Refined, 1, 404);
		}

		@Override
		public Item getTabIconItem() {
			return Items.apple;
		}
	};

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		proxy.preInit(e);
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
		proxy.init(e);
	}

	@EventHandler
	public void serverLoad(FMLServerStartingEvent event) {
		event.registerServerCommand(new CommandPush());
		event.registerServerCommand(new CommandEmpower());
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		proxy.postInit(e);
	}
}