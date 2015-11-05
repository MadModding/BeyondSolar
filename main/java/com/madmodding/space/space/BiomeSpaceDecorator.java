package com.madmodding.space.space;

import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.ANDESITE;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.COAL;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.DIAMOND;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.DIORITE;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.DIRT;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.GOLD;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.GRANITE;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.GRAVEL;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.IRON;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.LAPIS;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.REDSTONE;

import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

public class BiomeSpaceDecorator extends BiomeDecorator {
	private static final String __OBFID = "CL_00000188";

	public BiomeSpaceDecorator() {
	}

	protected void generateOres() {
		MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Pre(currentWorld, randomGenerator, field_180294_c));
		if (TerrainGen.generateOre(currentWorld, randomGenerator, dirtGen, field_180294_c, DIRT))
			this.genStandardOre1(this.chunkProviderSettings.dirtCount, this.dirtGen,
					this.chunkProviderSettings.dirtMinHeight, this.chunkProviderSettings.dirtMaxHeight);
		if (TerrainGen.generateOre(currentWorld, randomGenerator, gravelGen, field_180294_c, GRAVEL))
			this.genStandardOre1(this.chunkProviderSettings.gravelCount, this.gravelGen,
					this.chunkProviderSettings.gravelMinHeight, this.chunkProviderSettings.gravelMaxHeight);
		if (TerrainGen.generateOre(currentWorld, randomGenerator, dioriteGen, field_180294_c, DIORITE))
			this.genStandardOre1(this.chunkProviderSettings.dioriteCount, this.dioriteGen,
					this.chunkProviderSettings.dioriteMinHeight, this.chunkProviderSettings.dioriteMaxHeight);
		if (TerrainGen.generateOre(currentWorld, randomGenerator, graniteGen, field_180294_c, GRANITE))
			this.genStandardOre1(this.chunkProviderSettings.graniteCount, this.graniteGen,
					this.chunkProviderSettings.graniteMinHeight, this.chunkProviderSettings.graniteMaxHeight);
		if (TerrainGen.generateOre(currentWorld, randomGenerator, andesiteGen, field_180294_c, ANDESITE))
			this.genStandardOre1(this.chunkProviderSettings.andesiteCount, this.andesiteGen,
					this.chunkProviderSettings.andesiteMinHeight, this.chunkProviderSettings.andesiteMaxHeight);
		if (TerrainGen.generateOre(currentWorld, randomGenerator, coalGen, field_180294_c, COAL))
			this.genStandardOre1(this.chunkProviderSettings.coalCount, this.coalGen,
					this.chunkProviderSettings.coalMinHeight, this.chunkProviderSettings.coalMaxHeight);
		if (TerrainGen.generateOre(currentWorld, randomGenerator, ironGen, field_180294_c, IRON))
			this.genStandardOre1(this.chunkProviderSettings.ironCount, this.ironGen,
					this.chunkProviderSettings.ironMinHeight, this.chunkProviderSettings.ironMaxHeight);
		if (TerrainGen.generateOre(currentWorld, randomGenerator, goldGen, field_180294_c, GOLD))
			this.genStandardOre1(this.chunkProviderSettings.goldCount, this.goldGen,
					this.chunkProviderSettings.goldMinHeight, this.chunkProviderSettings.goldMaxHeight);
		if (TerrainGen.generateOre(currentWorld, randomGenerator, redstoneGen, field_180294_c, REDSTONE))
			this.genStandardOre1(this.chunkProviderSettings.redstoneCount, this.redstoneGen,
					this.chunkProviderSettings.redstoneMinHeight, this.chunkProviderSettings.redstoneMaxHeight);
		if (TerrainGen.generateOre(currentWorld, randomGenerator, diamondGen, field_180294_c, DIAMOND))
			this.genStandardOre1(this.chunkProviderSettings.diamondCount, this.diamondGen,
					this.chunkProviderSettings.diamondMinHeight, this.chunkProviderSettings.diamondMaxHeight);
		if (TerrainGen.generateOre(currentWorld, randomGenerator, lapisGen, field_180294_c, LAPIS))
			this.genStandardOre2(this.chunkProviderSettings.lapisCount, this.lapisGen,
					this.chunkProviderSettings.lapisCenterHeight, this.chunkProviderSettings.lapisSpread);
		MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Post(currentWorld, randomGenerator, field_180294_c));
	}

	protected void genDecorations(BiomeGenBase p_150513_1_) {
		this.generateOres();

		if (this.field_180294_c.getX() == 0 && this.field_180294_c.getZ() == 0) {
			// Center Chunk
		}
	}
}
