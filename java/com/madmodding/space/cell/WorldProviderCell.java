package com.madmodding.space.cell;

import com.madmodding.space.Main;

import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldProviderCell extends WorldProvider {

    /**
     * creates a new world chunk manager for WorldProvider
     */
    public void registerWorldChunkManager() {
        this.worldChunkMgr = new WorldChunkManagerHell(Main.cell, 0.0F);
        this.dimensionId = -10;
        this.hasNoSky = true;
    }

    /**
     * Returns a new chunk provider which generates chunks for this world
     */
    public IChunkProvider createChunkGenerator() {
        return new ChunkProviderCell(this.worldObj, this.worldObj.getSeed());
    }

    /**
     * Calculates the angle of sun and moon in the sky relative to a specified time (usually worldTime)
     */
    public float calculateCelestialAngle(long p_76563_1_, float p_76563_3_) {
        return 0.0F;
    }

    /**
     * Returns array with sunrise/sunset colors
     */
    @SideOnly(Side.CLIENT)
    public float[] calcSunriseSunsetColors(float celestialAngle, float partialTicks) {
        return null;
    }

    @SideOnly(Side.CLIENT)
    public boolean isSkyColored()
    {
        return false;
    }

    /**
     * True if the player can respawn in this dimension (true = overworld, false = nether).
     */
    public boolean canRespawnHere()
    {
        return false;
    }

    /**
     * Returns 'true' if in the "main surface world", but 'false' if in the Nether or End dimensions.
     */
    public boolean isSurfaceWorld()
    {
        return false;
    }

    /**
     * the y level at which clouds are rendered.
     */
    @SideOnly(Side.CLIENT)
    public float getCloudHeight()
    {
        return 8.0F;
    }

    /**
     * Will check if the x, z position specified is alright to be set as the map spawn point
     */
    public boolean canCoordinateBeSpawn(int x, int z)
    {
        return this.worldObj.getGroundAboveSeaLevel(new BlockPos(x, 0, z)).getMaterial().blocksMovement();
    }

    public int getAverageGroundLevel()
    {
        return 50;
    }

    /**
     * Returns true if the given X,Z coordinate should show environmental fog.
     */
    @SideOnly(Side.CLIENT)
    public boolean doesXZShowFog(int x, int z)
    {
        return true;
    }

    /**
     * Returns the dimension's name, e.g. "The End", "Nether", or "Overworld".
     */
    public String getDimensionName()
    {
        return "Cell";
    }

    public String getInternalNameSuffix()
    {
        return "_cell";
    }
}