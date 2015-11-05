package com.madmodding.space.space;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.google.common.collect.Lists;

import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockHelper;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.FlatGeneratorInfo;
import net.minecraft.world.gen.FlatLayerInfo;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.feature.WorldGenDungeons;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraft.world.gen.structure.StructureOceanMonument;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;

public class ChunkProviderSpace implements IChunkProvider
{
    private World worldObj;
    private Random random;
    private final IBlockState[] cachedBlockIDs = new IBlockState[256];
    private final List structureGenerators = Lists.newArrayList();
    private static final String __OBFID = "CL_00000391";

    public ChunkProviderSpace(World worldIn, long p_i2004_2_)
    {
        this.worldObj = worldIn;
        this.random = new Random(p_i2004_2_);
        boolean flag1 = true;
        this.cachedBlockIDs[1] = Blocks.barrier.getDefaultState();
 }

    /**
     * Will return back a chunk, if it doesn't exist and its not a MP client it will generates all the blocks for the
     * specified chunk from the map seed and chunk seed
     */
    public Chunk provideChunk(int x, int z)
    {
        ChunkPrimer chunkprimer = new ChunkPrimer();
        int i1;

        for (int k = 0; k < this.cachedBlockIDs.length; ++k)
        {
            IBlockState iblockstate = this.cachedBlockIDs[k];

            if (iblockstate != null)
            {
                for (int l = 0; l < 16; ++l)
                {
                    for (i1 = 0; i1 < 16; ++i1)
                    {
                        chunkprimer.setBlockState(l, k, i1, iblockstate);
                    }
                }
            }
        }
        Chunk chunk = new Chunk(this.worldObj, chunkprimer, x, z);
        BiomeGenBase[] abiomegenbase = this.worldObj.getWorldChunkManager().loadBlockGeneratorData((BiomeGenBase[])null, x * 16, z * 16, 16, 16);
        byte[] abyte = chunk.getBiomeArray();
        
        for (i1 = 0; i1 < abyte.length; ++i1)
        {
            abyte[i1] = (byte)abiomegenbase[i1].biomeID;
        }

        chunk.generateSkylightMap();
        return chunk;
    }
    private void generateSpace(World world, Random rand, int chunkX, int chunkZ) {
        for(int k = 0; k < 10; k++){
        	int firstBlockXCoord = chunkX + rand.nextInt(16);
        	int firstBlockYCoord = rand.nextInt(256);
        	int firstBlockZCoord = chunkZ + rand.nextInt(16);
        	
        	(new WorldGenMinable(Blocks.iron_ore.getDefaultState(), 48, BlockHelper.forBlock(Blocks.air))).generate(world, rand, new BlockPos(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord));
        }
        for(int k = 0; k < 10; k++){
        	int firstBlockXCoord = chunkX + rand.nextInt(16);
        	int firstBlockYCoord = rand.nextInt(256);
        	int firstBlockZCoord = chunkZ + rand.nextInt(16);
        	
        	(new WorldGenMinable(Blocks.gold_ore.getDefaultState(), 48, BlockHelper.forBlock(Blocks.air))).generate(world, rand, new BlockPos(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord));
        }
        for(int k = 0; k < 10; k++){
        	int firstBlockXCoord = chunkX + rand.nextInt(16);
        	int firstBlockYCoord = rand.nextInt(256);
        	int firstBlockZCoord = chunkZ + rand.nextInt(16);
        	
        	(new WorldGenMinable(Blocks.ice.getDefaultState(), 48, BlockHelper.forBlock(Blocks.air))).generate(world, rand, new BlockPos(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord));
        }
	}
    /**
     * Checks to see if a chunk exists at x, z
     */
    public boolean chunkExists(int x, int z)
    {
        return true;
    }

    /**
     * Populates chunk with ores etc etc
     */
    public void populate(IChunkProvider p_73153_1_, int p_73153_2_, int p_73153_3_)
    {
    	MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Pre(p_73153_1_, worldObj, worldObj.rand, p_73153_2_, p_73153_3_, false));
        int k = p_73153_2_ * 16;
        int l = p_73153_3_ * 16;
        BlockPos blockpos = new BlockPos(k, 0, l);
        BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(new BlockPos(k + 16, 0, l + 16));
        boolean flag = false;
        this.random.setSeed(this.worldObj.getSeed());
        long i1 = this.random.nextLong() / 2L * 2L + 1L;
        long j1 = this.random.nextLong() / 2L * 2L + 1L;
        this.random.setSeed((long)p_73153_2_ * i1 + (long)p_73153_3_ * j1 ^ this.worldObj.getSeed());
        ChunkCoordIntPair chunkcoordintpair = new ChunkCoordIntPair(p_73153_2_, p_73153_3_);
        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Post(p_73153_1_, worldObj, worldObj.rand, p_73153_2_, p_73153_3_, false));

    }

    public boolean func_177460_a(IChunkProvider p_177460_1_, Chunk p_177460_2_, int p_177460_3_, int p_177460_4_)
    {
        return false;
    }

    /**
     * Two modes of operation: if passed true, save all Chunks in one go.  If passed false, save up to two chunks.
     * Return true if all chunks have been saved.
     */
    public boolean saveChunks(boolean p_73151_1_, IProgressUpdate p_73151_2_)
    {
        return true;
    }

    /**
     * Save extra data not associated with any Chunk.  Not saved during autosave, only during world unload.  Currently
     * unimplemented.
     */
    public void saveExtraData() {}

    /**
     * Unloads chunks that are marked to be unloaded. This is not guaranteed to unload every such chunk.
     */
    public boolean unloadQueuedChunks()
    {
        return false;
    }

    /**
     * Returns if the IChunkProvider supports saving.
     */
    public boolean canSave()
    {
        return true;
    }

    /**
     * Converts the instance data to a readable string.
     */
    public String makeString()
    {
        return "FlatLevelSource";
    }

    public List func_177458_a(EnumCreatureType p_177458_1_, BlockPos p_177458_2_)
    {
        BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(p_177458_2_);
        return biomegenbase.getSpawnableList(p_177458_1_);
    }

    public BlockPos getStrongholdGen(World worldIn, String p_180513_2_, BlockPos p_180513_3_)
    {
        if ("Stronghold".equals(p_180513_2_))
        {
            Iterator iterator = this.structureGenerators.iterator();

            while (iterator.hasNext())
            {
                MapGenStructure mapgenstructure = (MapGenStructure)iterator.next();

                if (mapgenstructure instanceof MapGenStronghold)
                {
                    return mapgenstructure.getClosestStrongholdPos(worldIn, p_180513_3_);
                }
            }
        }

        return null;
    }

    public int getLoadedChunkCount()
    {
        return 0;
    }

    public void recreateStructures(Chunk p_180514_1_, int p_180514_2_, int p_180514_3_)
    {
        Iterator iterator = this.structureGenerators.iterator();

        while (iterator.hasNext())
        {
            MapGenStructure mapgenstructure = (MapGenStructure)iterator.next();
            mapgenstructure.func_175792_a(this, this.worldObj, p_180514_2_, p_180514_3_, (ChunkPrimer)null);
        }
    }

    public Chunk provideChunk(BlockPos blockPosIn)
    {
        return this.provideChunk(blockPosIn.getX() >> 4, blockPosIn.getZ() >> 4);
    }
}
