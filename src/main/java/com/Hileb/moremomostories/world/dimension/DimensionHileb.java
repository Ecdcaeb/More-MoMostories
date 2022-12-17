package com.Hileb.moremomostories.world.dimension;

import com.Hileb.moremomostories.init.InitDimension;
import com.Hileb.moremomostories.init.ModConfig;
import com.Hileb.moremomostories.world.dimension.hexcube.ChunkGeneratorHexCube16;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.gen.IChunkGenerator;

public class DimensionHileb  extends WorldProviderSurface {
    public DimensionHileb(){
        this.setDimension(ModConfig.dimension.WORLD_GEN_CONF);
    }

    @Override
    public void init() {
        NBTTagCompound data = InitDimension.getDimensionData(world);
        //seed = data.hasKey(SEED_KEY, Constants.NBT.TAG_LONG) ? data.getLong(SEED_KEY) : loadSeed();
        //hasSkyLight = isSkylightEnabled(data);
       // biomeProvider = new PeriodicBiomeProvider(world);
        hasSkyLight = false;
    }

//    @Override
//    public IChunkGenerator createChunkGenerator() {
//        return new ChunkGenUniversal(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled(), world.getWorldInfo().getGeneratorOptions());
//    }

    //----------trivial
    @Override
    public int getAverageGroundLevel() {
        return 128;
    }

    @Override
    public DimensionType getDimensionType() {
        return InitDimension.DIM_UNIV;
    }

//    @Override
//    public boolean canRespawnHere() {
//        // lie about this until the world is initialized
//        // otherwise the server will try to generate enough terrain for a spawn point and that's annoying
//        return world.getWorldInfo().isInitialized();
//    }

    @Override
    public float[] calcSunriseSunsetColors(float celestialAngle, float f1) {
        return null;
    }

    @Override
    public float calculateCelestialAngle(long worldTime, float partialTicks) {
        int i = (int)(worldTime % 24000L);
        float f = ((float)i + partialTicks) / 24000.0F - 0.25F;

        if (f < 0.0F)
        {
            ++f;
        }

        if (f > 1.0F)
        {
            --f;
        }

        float f1 = 1.0F - (float)((Math.cos((double)f * Math.PI) + 1.0D) / 2.0D);
        f = f + (f1 - f) / 3.0F;
        return f;
    }



    //    @Nullable
//    @Override
//    @SideOnly(Side.CLIENT)
//    public MusicTicker.MusicType getMusicType() {
//        return TFClientProxy.TFMUSICTYPE;
//    }
    @Override
    public IChunkGenerator createChunkGenerator() {
        return new ChunkGeneratorHexCube16( world, true, world.getSeed());
    }

    @Override
    public boolean canRespawnHere() {
        return false;
    }

    @Override
    public boolean isSurfaceWorld() {
        return false;
    }
}
