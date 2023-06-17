package com.Hileb.moremomostories.world.dimension;

import com.Hileb.moremomostories.init.InitBiome;
import com.Hileb.moremomostories.init.InitDimension;
import com.Hileb.moremomostories.init.ModConfig;
import com.Hileb.moremomostories.world.dimension.ChuckGenerator.ChunkGeneratorNullPlace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;

public class DimensionOne extends WorldProvider {

    public static final DimensionType DIM_ONE = DimensionType.register("Dim_one", "_testdim", ModConfig.dimension.WORLD_GEN_CONF, DimensionOne.class, false);
    public DimensionOne() {
        this.biomeProvider = new BiomeProviderSingle(InitBiome.BIOME_BOOK);
        hasSkyLight = false;
    }

    @Override
    public DimensionType getDimensionType() {
        return DIM_ONE;
    }

    @Override
    public IChunkGenerator createChunkGenerator() {
        return new ChunkGeneratorNullPlace( world, true, world.getSeed());
    }

    @Override
    public boolean canRespawnHere() {
        return false;
    }

    @Override
    public WorldSleepResult canSleepAt(EntityPlayer player, BlockPos pos) {
        return WorldSleepResult.DENY;
    }


    @Override
    public boolean isSurfaceWorld() {
        return false;
    }


}
