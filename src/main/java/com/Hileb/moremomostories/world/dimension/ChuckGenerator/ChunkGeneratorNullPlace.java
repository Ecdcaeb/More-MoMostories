package com.Hileb.moremomostories.world.dimension.ChuckGenerator;

import com.Hileb.moremomostories.blocks.ModBlocks;
import com.Hileb.moremomostories.world.dimension.hexcube.structure.GenCubeBase;
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

import static com.Hileb.moremomostories.util.CommonDef.CHUNK_SIZE;

public class ChunkGeneratorNullPlace implements IChunkGenerator {

    private static final int heightLimit = 255;
    //private static final int yNoGateLimit = 255;

    private final World world;
    private final boolean generateStructures;
    private final Random rand;
    public ChunkGeneratorNullPlace(World world, boolean generate, long seed) {
        this.world = world;
        this.generateStructures = generate;
        this.rand = new Random(seed);
        world.setSeaLevel(63);
    }

    @Override//是否在某结构里
    public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
        //return false;
        return structureName=="nullPlace" && pos.getX()<=8 && pos.getX()>=-8 && pos.getZ()>=-8 && pos.getZ()<=8;
    }

    @Override
    public void recreateStructures(Chunk chunkIn, int x, int z) {
        //nothing to do
    }

    @Nullable
    @Override
    public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
        return null;//do nothing
    }

    @Override
    public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
        return null;//spawn nothing
    }

    @Override
    public boolean generateStructures(Chunk chunkIn, int x, int z) {
        return false;//不绘制结构
    }

    @Override//绘制区块
    public Chunk generateChunk(int x, int z) {
        this.rand.setSeed((long)x * 341873128712L + (long)z * 132897987541L);
        ChunkPrimer chunkprimer = new ChunkPrimer();
        buildChunk(x,z,chunkprimer);

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
    private void buildChunk(int x,int z,ChunkPrimer primer){
        if(x==0 && z==0){
            primer.setBlockState(0,0,0, ModBlocks.BLOCK_HILEB_BLOCK.getDefaultState());
        }
    }

    //真殖民
    @Override
    public void populate(int chunkX, int chunkZ) {
        net.minecraft.block.BlockFalling.fallInstantly = true;
        int x = chunkX * 16;
        int z = chunkZ * 16;

        net.minecraftforge.event.ForgeEventFactory.onChunkPopulate(true, this, this.world, this.rand, chunkX, chunkZ, false);

        for (int y = 0; y < heightLimit; y+=CHUNK_SIZE) {


            setSeedFor(x, y, z);
            boolean hasDoorX = rand.nextBoolean();
            boolean hasDoorY = y != 0 && rand.nextBoolean();//wont fall to void
            boolean hasDoorZ = rand.nextBoolean();

            GenCubeBase gen = new GenCubeRoom1(true);

            if (gen != null)
            {
                gen.setHasDoorXYZ(hasDoorX, hasDoorY, hasDoorZ);
                gen.setHasLightXYZ(true);
                gen.generate(world, rand, new BlockPos(x, y, z));
            }
        }
        net.minecraftforge.event.ForgeEventFactory.onChunkPopulate(false, this, this.world, this.rand, chunkX, chunkZ, false);
        net.minecraft.block.BlockFalling.fallInstantly = false;
    }
    void setSeedFor(int x, int y, int z)
    {
        rand.setSeed((long)x * 341873128712L + (long)z * 132897987541L + (long)y * 438951276L);
    }

    //获取世界
    public World getWorld() {
        return world;
    }
}
