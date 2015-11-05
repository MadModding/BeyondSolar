package com.madmodding.space;

import com.madmodding.space.blocks.ModBlocks;
import com.madmodding.space.items.ModItems;
import com.madmodding.space.space.WorldProviderSpace;

import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent e) {
		ModItems.init();
		ModBlocks.init();
		}

	public void init(FMLInitializationEvent e) {
		GameRegistry.registerWorldGenerator(new WorldGenerator(), 0);
		DimensionManager.registerProviderType(71, WorldProviderSpace.class, true);
    	DimensionManager.registerDimension(71, 71);
    	}

	public void postInit(FMLPostInitializationEvent e) {
		MinecraftForge.EVENT_BUS.register(new EventHandler());
		}
}
