package com.madmodding.space.blocks;

import com.madmodding.space.items.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import scala.tools.nsc.backend.icode.BasicBlocks.BasicBlock;

public final class ModBlocks {
	
	public static final Block meteor = new Meteor(Material.rock).setUnlocalizedName("blockNekmunnitOre");
	public static final Block ferrousOre = new SpaceOre(Material.rock).setUnlocalizedName("ferrousOre").setHardness(15.0F);
	public static final Block nickelOre = new SpaceOre(Material.rock).setUnlocalizedName("nickelOre").setHardness(12.0F);
	public static final Block cobaltOre = new SpaceOre(Material.rock).setUnlocalizedName("cobaltOre").setHardness(8.0F);
	public static final Block magnesiumOre = new SpaceOre(Material.rock).setUnlocalizedName("magnesiumOre").setHardness(1.5F);
	public static final Block siliconOre = new SpaceOre(Material.rock).setUnlocalizedName("siliconOre").setHardness(3.5F);
	public static final Block silverOre = new SpaceOre(Material.rock).setUnlocalizedName("silverOre").setHardness(5.0F);
	public static final Block platinumOre = new SpaceOre(Material.rock).setUnlocalizedName("platinumOre").setHardness(11.5F);
	public static final Block uraniumOre = new SpaceOre(Material.rock).setUnlocalizedName("uraniumOre").setHardness(30.0F);
	public static final Block copperOre = new SpaceOre(Material.rock).setUnlocalizedName("copperOre").setHardness(5.0F);
	public static final Block aluminiumOre = new SpaceOre(Material.rock).setUnlocalizedName("aluminiumOre").setHardness(8.0F);
	public static final Block zincOre = new SpaceOre(Material.rock).setUnlocalizedName("zincOre").setHardness(4.0F);
	public static final Block leadOre = new SpaceOre(Material.rock).setUnlocalizedName("leadOre").setHardness(6.0F);
	public static final Block tinOre = new SpaceOre(Material.rock).setUnlocalizedName("tinOre").setHardness(6.0F);
	public static final Block titaniumOre = new SpaceOre(Material.rock).setUnlocalizedName("titaniumOre").setHardness(30.0F);
	public static final Block bauxiteOre = new SpaceOre(Material.rock).setUnlocalizedName("bauxiteOre").setHardness(12.0F);
	public static final Block rareMetalMeteorite = new SpaceOre(Material.rock).setUnlocalizedName("rareMetalMeteorite").setHardness(12.0F);
	public static final Block platinumMeteorite = new SpaceOre(Material.rock).setUnlocalizedName("platinumMeteorite").setHardness(15.0F);
	public static final Block alienEgg = new AlienEgg(Material.carpet).setUnlocalizedName("alienEgg");
	    
	public static void init() {
		GameRegistry.registerBlock(meteor,meteor.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(ferrousOre, ferrousOre.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(nickelOre, nickelOre.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(cobaltOre, cobaltOre.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(magnesiumOre, magnesiumOre.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(siliconOre, siliconOre.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(silverOre, silverOre.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(platinumOre, platinumOre.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(uraniumOre, uraniumOre.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(copperOre, copperOre.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(aluminiumOre, aluminiumOre.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(zincOre, zincOre.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(leadOre, leadOre.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(tinOre, tinOre.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(titaniumOre, titaniumOre.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(bauxiteOre, bauxiteOre.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(rareMetalMeteorite, rareMetalMeteorite.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(platinumMeteorite, platinumMeteorite.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(alienEgg, alienEgg.getUnlocalizedName().substring(5));
		}

}
