package com.Hileb.moremomostories.world.dimension;

import com.Hileb.moremomostories.init.InitBiome;
import com.Hileb.moremomostories.world.dimension.ChuckGenerator.ChunkGeneratorNullPlace;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;

public class DimensionOne extends WorldProvider {

    public DimensionOne() {
        this.biomeProvider = new BiomeProviderSingle(InitBiome.BIOME_BOOK);
        hasSkyLight = false;
    }

    @Override
    public DimensionType getDimensionType() {
        return DimensionType.NETHER;
        //return InitDimension.DIM_ONE;
    }

    @Override
    public IChunkGenerator createChunkGenerator() {
        return new ChunkGeneratorNullPlace( world, true, world.getSeed());
    }

    @Override
    public boolean canRespawnHere() {
        return true;
    }

    @Override
    public boolean isSurfaceWorld() {
        return false;
    }


}
