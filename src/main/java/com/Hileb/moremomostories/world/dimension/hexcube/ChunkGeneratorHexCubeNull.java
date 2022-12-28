package com.Hileb.moremomostories.world.dimension.hexcube;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class ChunkGeneratorHexCubeNull implements IChunkGenerator {

    private static final int heightLimit = 255;
    //private static final int yNoGateLimit = 255;

    private final World world;
    private final boolean generateStructures;
    private final Random rand;


    public ChunkGeneratorHexCubeNull(World world, boolean generate, long seed) {
        this.world = world;
        this.generateStructures = generate;
        this.rand = new Random(seed);
        world.setSeaLevel(63);
    }
    //infrastructure

    @Override
    public Chunk generateChunk(int x, int z) {
        this.rand.setSeed((long)x * 341873128712L + (long)z * 132897987541L);
        ChunkPrimer chunkprimer = new ChunkPrimer();
        //buildChunk(x,z,chunkprimer);

        Chunk chunk = new Chunk(this.world, chunkprimer, x, z);
        byte[] abyte = chunk.getBiomeArray();

        for (int i = 0; i < abyte.length; ++i)
        {
            abyte[i] = (byte)Biome.getIdForBiome(Biomes.EXTREME_HILLS);
            //abyte[i] = (byte)Biome.getIdForBiome(InitBiome.BIOME_ONE);
        }

        chunk.resetRelightChecks();
        return chunk;
    }

//    enum EnumRoomType{
//        EMPTY,
//        TREASURE,
//        SOIL,
//        WOOD,
//        TORCH
//    }
//

        //return EnumRoomType.EMPTY;
 //   }


    @Override
    public void populate(int chunkX, int chunkZ) {

    }

    @Override
    public boolean generateStructures(Chunk chunkIn, int x, int z) {
        return false;
    }

    @Override
    public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
        Biome biome = this.world.getBiome(pos);
        return biome.getSpawnableList(creatureType);
    }

    @Nullable
    @Override
    public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
        return null;
    }

    @Override
    public void recreateStructures(Chunk chunkIn, int x, int z) {

    }

    @Override
    public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
        return false;
    }
}
