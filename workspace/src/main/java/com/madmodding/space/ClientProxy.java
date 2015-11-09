package com.madmodding.space;

import com.madmodding.space.blocks.BlockRenderRegister;
import com.madmodding.space.blocks.tile.TileEntityAcidContainer;
import com.madmodding.space.blocks.tile.TileEntityAcidFull;
import com.madmodding.space.blocks.tile.TileEntityAlienCell;
import com.madmodding.space.blocks.tile.TileEntityAlienEgg;
import com.madmodding.space.client.render.items.ItemRenderRegister;
import com.madmodding.space.client.render.items.RenderAcidContainer;
import com.madmodding.space.client.render.items.RenderAcidFull;
import com.madmodding.space.client.render.items.RenderAlienCell;
import com.madmodding.space.client.render.items.RenderAlienEgg;
import com.madmodding.space.items.element.ElementLib;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

	@Override
	public void preInit(FMLPreInitializationEvent e) {
		super.preInit(e);
		}

	@Override
	public void init(FMLInitializationEvent e) {
		super.init(e);
		ElementLib.initClient();
		ItemRenderRegister.registerItemRenderer();
	    BlockRenderRegister.registerBlockRenderer();
		ClientRegistry.registerTileEntity(TileEntityAlienEgg.class, "space:alienegg",new RenderAlienEgg());
		ClientRegistry.registerTileEntity(TileEntityAcidContainer.class, "space:acidcontainer",new RenderAcidContainer());
		ClientRegistry.registerTileEntity(TileEntityAcidFull.class, "space:acidfull",new RenderAcidFull());

		ClientRegistry.registerTileEntity(TileEntityAlienCell.class, "space:aliencell", new RenderAlienCell());
		MinecraftForge.EVENT_BUS.register(new ItemRenderRegister());	
	}

	@Override
	public void postInit(FMLPostInitializationEvent e) {
		super.postInit(e);
	}

}
