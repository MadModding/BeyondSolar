package com.madmodding.space.blocks;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.madmodding.space.Main;
import com.madmodding.space.items.ModItems;

public class SpaceOre extends Block {

	public SpaceOre(Material materialIn) {
		super(materialIn);
		this.setCreativeTab(Main.aliensTab);
        this.setStepSound(soundTypeStone);
	}
	
	//----------------------------------OREDROPS---------------------------------------------------------
	//---------------------------------------------------------------------------------------------------

	@Override
	public ArrayList getDrops(IBlockAccess world, BlockPos pos, IBlockState blockstate, int fortune) {
	    ArrayList drops = new ArrayList();
	    Random rand = world instanceof World ? ((World)world).rand : RANDOM;
	    int chance = rand.nextInt(100) + 1;
	    
	    if(blockstate.getBlock() == ModBlocks.ferrousOre) {
	    	if(chance>95) { drops.add(new ItemStack(ModItems.fragment, 1, 27)); }
	    	else if(chance>80) { drops.add(new ItemStack(ModItems.fragment, 1, 28)); }
	    	else if(chance>10) { drops.add(new ItemStack(ModItems.fragment, 1, 25)); }
	    	drops.add(new ItemStack(ModItems.dust, 3));
	    }if(blockstate.getBlock() == ModBlocks.nickelOre) {
	    	if(chance>95) { 
	    		drops.add(new ItemStack(ModItems.fragment, 1, 25)); 
	    	    drops.add(new ItemStack(ModItems.fragment, 1, 78));}
	    	else if(chance>10) { drops.add(new ItemStack(ModItems.fragment, 1, 27)); }
	    	drops.add(new ItemStack(ModItems.dust, 3));
	    }if(blockstate.getBlock() == ModBlocks.cobaltOre) {
	    	if(chance>95) { drops.add(new ItemStack(ModItems.fragment, 1, 26)); }
	    	else if(chance>10) { drops.add(new ItemStack(Items.dye, 1, 4)); }
	    	drops.add(new ItemStack(ModItems.dust, 3));
	    }if(blockstate.getBlock() == ModBlocks.magnesiumOre) {
	    	if(chance>1) { drops.add(new ItemStack(ModItems.fragment, 1, 11)); }
	    	else if(chance>10) { drops.add(new ItemStack(ModItems.fragment, 1, 10)); }
	    	else if(chance>30) { drops.add(new ItemStack(Blocks.air, 1)); }
	    	drops.add(new ItemStack(ModItems.dust, 3));
	    }if(blockstate.getBlock() == ModBlocks.siliconOre) {
	    	if(chance>1) { drops.add(new ItemStack(ModItems.fragment, 1, 13)); }
	    	else if(chance>10) { drops.add(new ItemStack(ModItems.fragment, 1, 14)); } 
	    	else if(chance>20) { drops.add(new ItemStack(ModItems.fragment, 1, 15)); }
	    	else if(chance>50) { drops.add(new ItemStack(ModItems.fragment, 1, 16)); }
	    	else if(chance>80) { drops.add(new ItemStack(ModItems.fragment, 1, 17)); }
	    	drops.add(new ItemStack(ModItems.dust, 3));
	    }if(blockstate.getBlock() == ModBlocks.silverOre) {
	    	if(chance>1) { drops.add(new ItemStack(ModItems.fragment, 1, 77)); }
	    	else if(chance>10) { drops.add(new ItemStack(ModItems.fragment, 1, 46)); }
	    	drops.add(new ItemStack(ModItems.dust, 3));
	    }if(blockstate.getBlock() == ModBlocks.platinumOre) {
	    	if(chance>50) { drops.add(new ItemStack(ModItems.fragment, 1, 77)); }
	    	else{ drops.add(new ItemStack(ModItems.fragment, 1, 46)); }
	    	drops.add(new ItemStack(ModItems.dust, 3));
	    }if(blockstate.getBlock() == ModBlocks.uraniumOre) {
	    	if(chance>50) { drops.add(new ItemStack(ModItems.fragment, 1, 91)); }
	    	else{ drops.add(new ItemStack(Items.blaze_powder)); }
	    	drops.add(new ItemStack(ModItems.dust, 3));
	    }if(blockstate.getBlock() == ModBlocks.copperOre) {
	    	if(chance>10) { drops.add(new ItemStack(ModItems.fragment, 1, 27)); }
	    	else if(chance>1) { drops.add(new ItemStack(ModItems.fragment, 1, 28)); }
	    	drops.add(new ItemStack(ModItems.dust, 3));
	    }if(blockstate.getBlock() == ModBlocks.aluminiumOre) {
	    	if(chance>50) { drops.add(new ItemStack(ModItems.fragment, 1, 12)); }
	    	else if(chance<50) { drops.add(new ItemStack(ModItems.fragment, 1, 13)); }
	    	drops.add(new ItemStack(ModItems.dust, 3));
	    }if(blockstate.getBlock() == ModBlocks.zincOre) {
	    	if(chance>1) { drops.add(new ItemStack(ModItems.fragment, 1, 29)); }
	    	else if(chance>10) { drops.add(new ItemStack(ModItems.fragment, 1, 27)); }
	    	drops.add(new ItemStack(ModItems.dust, 3));
	    }if(blockstate.getBlock() == ModBlocks.leadOre) {
	    	if(chance>1) { drops.add(new ItemStack(ModItems.fragment, 1, 81)); }
	    	else if(chance>10) { drops.add(new ItemStack(Blocks.cobblestone, 1)); }
	    	drops.add(new ItemStack(ModItems.dust, 3));
	    }if(blockstate.getBlock() == ModBlocks.tinOre) {
	    	if(chance>20) { drops.add(new ItemStack(ModItems.fragment, 1, 49)); }
	    	else if(chance>10) { drops.add(new ItemStack(ModItems.fragment, 1, 46)); }
	    	else if(chance>1) { drops.add(new ItemStack(ModItems.fragment, 1, 48)); }
	    	drops.add(new ItemStack(ModItems.dust, 3));	    	
	    }if(blockstate.getBlock() == ModBlocks.bauxiteOre) {
	    	if(chance>10) { drops.add(new ItemStack(ModItems.fragment, 1, 21)); }
	    	else if(chance>1) { drops.add(new ItemStack(ModItems.fragment, 1, 75)); }
	    	drops.add(new ItemStack(ModItems.dust, 3));
	    }if(blockstate.getBlock() == ModBlocks.titaniumOre) {
	    	if(chance>50) { drops.add(new ItemStack(ModItems.fragment, 1, 21)); }
	    	else{ drops.add(new ItemStack(Items.flint, 1)); }
	    	drops.add(new ItemStack(ModItems.dust, 3));
	    }if(blockstate.getBlock() == ModBlocks.rareMetalMeteorite) {
	    	if(chance>75) { drops.add(new ItemStack(ModItems.fragment, 1, 61)); }
	    	else if(chance>50){ drops.add(new ItemStack(ModItems.fragment, 1, 59)); }
	    	else if(chance>25){ drops.add(new ItemStack(ModItems.fragment, 1, 56)); }
	    	else{ drops.add(new ItemStack(Blocks.air, 1)); }
	    	drops.add(new ItemStack(ModItems.dust, 3));
	    }if(blockstate.getBlock() == ModBlocks.platinumMeteorite) {
	    	if(chance>10) { drops.add(new ItemStack(ModItems.fragment, 1, 75)); }
	    	else if(chance>20){ drops.add(new ItemStack(ModItems.fragment, 1, 76)); }
	    	else if(chance>40) { drops.add(new ItemStack(ModItems.fragment, 1, 77)); }
	    	else if(chance>60) { drops.add(new ItemStack(ModItems.fragment, 1, 45)); }
	    	else if(chance>80) { drops.add(new ItemStack(ModItems.fragment, 1, 44)); }
	    	else{ drops.add(new ItemStack(ModItems.fragment, 1, 43)); }
	    	drops.add(new ItemStack(ModItems.dust, 3));
	    }

	    return drops;
	}

}
