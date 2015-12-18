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
import com.madmodding.space.items.element.ElementLib;

public class SpaceOre extends Block {

	public SpaceOre(Material materialIn) {
		super(materialIn);
		this.setCreativeTab(Main.aliensTabUnRef);
		this.setStepSound(soundTypeStone);
		this.setHarvestLevel("pickaxe", 35);
	}

	public Block setHardness(float hardness) {
		this.blockHardness = hardness / 10;
		
		if (this.blockResistance < hardness / 2.0F) {
			this.blockResistance = hardness / 2.0F;
		}
		
		return this;
	}
	// ----------------------------------OREDROPS---------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------

	@Override
	public ArrayList getDrops(IBlockAccess world, BlockPos pos, IBlockState blockstate, int fortune) {
		ArrayList drops = new ArrayList();
		Random rand = world instanceof World ? ((World) world).rand : RANDOM;
		int chance = rand.nextInt(100) + 1;

		if (blockstate.getBlock() == ModBlocks.ferrousOre) {
			if (chance > 95) {
				ElementLib.addDrop(drops, 27);
			} else if (chance > 80) {
				ElementLib.addDrop(drops, 28);
			} else if (chance > 10) {
				ElementLib.addDrop(drops, 25);
			}
			drops.add(new ItemStack(ModItems.dust, 3));
		}
		if (blockstate.getBlock() == ModBlocks.nickelOre) {
			if (chance > 95) {
				ElementLib.addDrop(drops, 25);
				ElementLib.addDrop(drops, 78);
			} else if (chance > 10) {
				ElementLib.addDrop(drops, 27);
			}
			drops.add(new ItemStack(ModItems.dust, 3));
		}
		if (blockstate.getBlock() == ModBlocks.cobaltOre) {
			if (chance > 95) {
				ElementLib.addDrop(drops, 26);
			} else if (chance > 10) {
				drops.add(new ItemStack(Items.dye, 1, 4));
			}
			drops.add(new ItemStack(ModItems.dust, 3));
		}
		if (blockstate.getBlock() == ModBlocks.magnesiumOre) {
			if (chance > 30) {
				drops.add(new ItemStack(Blocks.air, 1));
			} else if (chance > 10) {
				ElementLib.addDrop(drops, 10);
			} else if (chance > 1) {
				ElementLib.addDrop(drops, 11);
			}
			drops.add(new ItemStack(ModItems.dust, 3));
		}
		if (blockstate.getBlock() == ModBlocks.siliconOre) {
			if (chance > 80) {
				ElementLib.addDrop(drops, 17);
			} else if (chance > 50) {
				ElementLib.addDrop(drops, 16);
			} else if (chance > 20) {
				ElementLib.addDrop(drops, 15);
			} else if (chance > 10) {
				ElementLib.addDrop(drops, 14);
			} else if (chance > 1) {
				ElementLib.addDrop(drops, 13);
			}
			drops.add(new ItemStack(ModItems.dust, 3));
		}
		if (blockstate.getBlock() == ModBlocks.silverOre) {
			if (chance > 10) {
				ElementLib.addDrop(drops, 46);
			} else if (chance > 1) {
				ElementLib.addDrop(drops, 77);
			}
			drops.add(new ItemStack(ModItems.dust, 3));
		}
		if (blockstate.getBlock() == ModBlocks.platinumOre) {
			if (chance > 50) {
				ElementLib.addDrop(drops, 77);
			} else {
				ElementLib.addDrop(drops, 46);
			}
			drops.add(new ItemStack(ModItems.dust, 3));
		}
		if (blockstate.getBlock() == ModBlocks.uraniumOre) {
			if (chance > 50) {
				ElementLib.addDrop(drops, 91);
			} else {
				drops.add(new ItemStack(Items.blaze_powder));
			}
			drops.add(new ItemStack(ModItems.dust, 3));
		}
		if (blockstate.getBlock() == ModBlocks.copperOre) {
			if (chance > 10) {
				ElementLib.addDrop(drops, 27);
			} else if (chance > 1) {
				ElementLib.addDrop(drops, 28);
			}
			drops.add(new ItemStack(ModItems.dust, 3));
		}
		if (blockstate.getBlock() == ModBlocks.aluminiumOre) {
			if (chance > 50) {
				ElementLib.addDrop(drops, 12);
			} else if (chance < 50) {
				ElementLib.addDrop(drops, 13);
			}
			drops.add(new ItemStack(ModItems.dust, 3));
		}
		if (blockstate.getBlock() == ModBlocks.zincOre) {
			if (chance > 10) {
				ElementLib.addDrop(drops, 27);
			} else if (chance > 1) {
				ElementLib.addDrop(drops, 29);
			}
			drops.add(new ItemStack(ModItems.dust, 3));
		}
		if (blockstate.getBlock() == ModBlocks.leadOre) {
			if (chance > 10) {
				drops.add(new ItemStack(Blocks.cobblestone, 1));
			} else if (chance > 1) {
				ElementLib.addDrop(drops, 81);

			}
			drops.add(new ItemStack(ModItems.dust, 3));
		}
		if (blockstate.getBlock() == ModBlocks.tinOre) {
			if (chance > 20) {
				ElementLib.addDrop(drops, 49);
			} else if (chance > 10) {
				ElementLib.addDrop(drops, 46);
			} else if (chance > 1) {
				ElementLib.addDrop(drops, 48);
			}
			drops.add(new ItemStack(ModItems.dust, 3));
		}
		if (blockstate.getBlock() == ModBlocks.bauxiteOre) {
			if (chance > 10) {
				ElementLib.addDrop(drops, 21);
			} else if (chance > 1) {
				ElementLib.addDrop(drops, 75);
			}
			drops.add(new ItemStack(ModItems.dust, 3));
		}
		if (blockstate.getBlock() == ModBlocks.titaniumOre) {
			if (chance > 50) {
				ElementLib.addDrop(drops, 21);
			} else {
				drops.add(new ItemStack(Items.flint, 1));
			}
			drops.add(new ItemStack(ModItems.dust, 3));
		}
		if (blockstate.getBlock() == ModBlocks.rareMetalMeteorite) {
			if (chance > 75) {
				ElementLib.addDrop(drops, 61);
			} else if (chance > 50) {
				ElementLib.addDrop(drops, 59);
			} else if (chance > 25) {
				ElementLib.addDrop(drops, 56);
			} else {
				drops.add(new ItemStack(Blocks.air, 1));
			}
			drops.add(new ItemStack(ModItems.dust, 3));
		}
		if (blockstate.getBlock() == ModBlocks.platinumMeteorite) {
			if (chance > 80) {
				ElementLib.addDrop(drops, 76);
			} else if (chance > 60) {
				ElementLib.addDrop(drops, 77);
			} else if (chance > 40) {
				ElementLib.addDrop(drops, 45);
			} else if (chance > 20) {
				ElementLib.addDrop(drops, 44);
			} else if (chance > 10) {
				ElementLib.addDrop(drops, 75);
			} else {
				ElementLib.addDrop(drops, 43);
			}
			drops.add(new ItemStack(ModItems.dust, 3));
		}

		return drops;
	}

}
