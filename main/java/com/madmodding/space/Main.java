package com.madmodding.space;

import com.madmodding.space.blocks.ModBlocks;
import com.madmodding.space.cell.BiomeGenCell;
import com.madmodding.space.items.ModItems;
import com.madmodding.space.space.BiomeGenSpace;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenEnd;
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

	public static final String MODID = "space";
	public static final String MODNAME = "Beyond Solar Mod";
	public static final String VERSION = "0.1.8";
	public static SimpleNetworkWrapper network;
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
			return new ItemStack(ModItems.fragment, 1, 404);
		}

		@Override
		public Item getTabIconItem() {
			return Items.apple;
		}
	};
	public static CreativeTabs aliensTabRef = new CreativeTabs("Aliens4") {
		public ItemStack getIconItemStack() {
			return new ItemStack(ModItems.ref, 1, 404);
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
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		proxy.postInit(e);
	}
}