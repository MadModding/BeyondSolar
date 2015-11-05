package com.madmodding.space;

import com.madmodding.space.items.ModItems;
import com.madmodding.space.space.BiomeGenSpace;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
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
	public static final String MODNAME = "Space Mod";
	public static final String VERSION = "0.1.8";
	public static SimpleNetworkWrapper network;
	public static final BiomeGenBase space = (new BiomeGenSpace(71)).setColor(8421631).setBiomeName("Space").setDisableRain();
    @Instance
	public static Main instance = new Main();

	@SidedProxy(clientSide = "com.madmodding.space.ClientProxy", serverSide = "com.madmodding.space.ServerProxy")
	public static CommonProxy proxy;
	public static CreativeTabs aliensTab = new CreativeTabs("Aliens") {
		  public Item getTabIconItem() {
			  return ModItems.evilstaff;
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
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		proxy.postInit(e);
	}
}
