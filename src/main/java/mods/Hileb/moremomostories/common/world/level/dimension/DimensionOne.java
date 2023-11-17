package mods.Hileb.moremomostories.common.world.level.dimension;

import mods.Hileb.moremomostories.common.init.ModBiomes;
import mods.Hileb.moremomostories.common.init.ModConfig;
import mods.Hileb.moremomostories.common.world.level.dimension.ChuckGenerator.ChunkGeneratorNullPlace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;

public class DimensionOne extends WorldProvider {

    public static final DimensionType DIM_ONE = DimensionType.register("Dim_one", "_testdim", ModConfig.dimension.WORLD_GEN_CONF, DimensionOne.class, false);
    public DimensionOne() {
        this.biomeProvider = new BiomeProviderSingle(ModBiomes.BIOME_BOOK);
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
