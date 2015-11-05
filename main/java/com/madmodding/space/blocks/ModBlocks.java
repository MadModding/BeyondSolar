package com.madmodding.space.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import scala.tools.nsc.backend.icode.BasicBlocks.BasicBlock;

public final class ModBlocks {
	public static final Block meteor = new Meteor(Material.rock).setUnlocalizedName("blockNekmunnitOre");
	public static final Block alienEgg = new AlienEgg(Material.carpet).setUnlocalizedName("alienEgg");
	    
	public static void init() {
		GameRegistry.registerBlock(meteor,meteor.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(alienEgg, alienEgg.getUnlocalizedName().substring(5));
		}

}
