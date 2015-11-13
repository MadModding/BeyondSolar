package com.madmodding.space;

import com.madmodding.space.blocks.ModBlocks;
import com.madmodding.space.blocks.ModFluids;
import com.madmodding.space.cell.WorldProviderCell;
import com.madmodding.space.client.render.items.RenderDrillLaser;
import com.madmodding.space.entity.item.EntityAcidAttack;
import com.madmodding.space.entity.item.EntityDrillLaser;
import com.madmodding.space.items.ModItems;
import com.madmodding.space.items.element.ElementLib;
import com.madmodding.space.items.element.RecipesDyeAdv;
import com.madmodding.space.items.element.RecipesDyeCustom;
import com.madmodding.space.items.element.RecipesToolDye;
import com.madmodding.space.space.WorldProviderSpace;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.init.Items;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.oredict.RecipeSorter;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent e) {
		ModItems.init();
		ModBlocks.init();
		ModFluids.init();
		ElementLib.initCommon();
		RecipeSorter.register("space:tooldye", RecipesToolDye.class, RecipeSorter.Category.SHAPELESS,
				"before:minecraft:shapeless");
		RecipeSorter.register("space:advtooldye", RecipesDyeAdv.class, RecipeSorter.Category.SHAPED,
				"before:minecraft:shapeless");
		RecipeSorter.register("space:customdye", RecipesDyeCustom.class, RecipeSorter.Category.SHAPELESS,
				"before:minecraft:shapeless");
		CraftingManager.getInstance().addRecipe(new RecipesToolDye());
		CraftingManager.getInstance().addRecipe(new RecipesDyeAdv());
		CraftingManager.getInstance().addRecipe(new RecipesDyeCustom());

	}

	public void init(FMLInitializationEvent e) {
		Main.network = NetworkRegistry.INSTANCE.newSimpleChannel("SpaceChannelPush");
		int packetId = 0;
		Main.network.registerMessage(MessagePushX.Handler.class, MessagePushX.class, packetId++, Side.CLIENT);
		Main.network.registerMessage(MessagePushY.Handler.class, MessagePushY.class, packetId++, Side.CLIENT);
		Main.network.registerMessage(MessagePushZ.Handler.class, MessagePushZ.class, packetId++, Side.CLIENT);
		Main.network.registerMessage(MessageDivinity.Handler.class, MessageDivinity.class, packetId++, Side.CLIENT);
		GameRegistry.registerWorldGenerator(new WorldGenerator(), 0);
		RenderingRegistry.registerEntityRenderingHandler(EntityDrillLaser.class, new RenderDrillLaser());
		RenderingRegistry.registerEntityRenderingHandler(EntityAcidAttack.class, new RenderSnowball(Minecraft.getMinecraft().getRenderManager(),Items.slime_ball,Minecraft.getMinecraft().getRenderItem()));
		DimensionManager.registerProviderType(71, WorldProviderSpace.class, true);
		DimensionManager.registerDimension(71, 71);
		DimensionManager.registerProviderType(-10, WorldProviderCell.class, true);
		DimensionManager.registerDimension(-10, -10);
	}

	public void postInit(FMLPostInitializationEvent e) {
		MinecraftForge.EVENT_BUS.register(new EventHandler());
	}
}
