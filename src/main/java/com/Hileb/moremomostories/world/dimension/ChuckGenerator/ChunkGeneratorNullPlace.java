package com.Hileb.moremomostories.world.dimension.ChuckGenerator;

import com.Hileb.moremomostories.blocks.ModBlocks;
import com.Hileb.moremomostories.init.InitBiome;
import com.Hileb.moremomostories.world.structure.IHilebStructure;
import com.Hileb.moremomostories.worldgen.WorldGenHelper;
import net.minecraft.block.BlockStairs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChunkGeneratorNullPlace implements IChunkGenerator {

    private static final int heightLimit = 255;
    //private static final int yNoGateLimit = 255;

    public List<IHilebStructure> structures=new ArrayList<>();
    private final World world;
    private final boolean generateStructures;
    private final Random rand;
    public ChunkGeneratorNullPlace(World world, boolean generate, long seed) {
        this.world = world;
        this.generateStructures = generate;
        this.rand = new Random(seed);
        world.setSeaLevel(63);
        hilebStructureInit();
    }
    public void hilebStructureInit(){
        structures.add(new IHilebStructure() {
            @Override
            public String getName() {
                return "iup";
            }

            @Override
            public boolean doStructure(int x, int z, ChunkPrimer primer) {
                Random  random=new Random(x+z+primer.hashCode());
                if (random.nextInt(100)<=1){
                    //IdlFramework.LogWarning("a big Structure in %d  50 %d",x*16,z*16);
                    WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(0 ,0,0),new BlockPos(15 ,255,15)),Blocks.AIR.getDefaultState());

                    WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(0 ,0,0),new BlockPos(15 ,255,15)),ModBlocks.BLOCK_HILEB_BLOCK.getDefaultState());
                    WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(1 ,1,1),new BlockPos(14 ,254,14)),Blocks.AIR.getDefaultState());
                    return true;
                }
                return false;
            }
        });
    }

    @Override//是否在某结构里
    public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
        return  false;
        //return structureName=="nullPlace" && pos.getX()<=8 && pos.getX()>=-8 && pos.getZ()>=-8 && pos.getZ()<=8;
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
        return InitBiome.BIOME_BOOK.getSpawnableList(creatureType);
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
            abyte[i] = (byte)Biome.getIdForBiome(InitBiome.BIOME_BOOK);
            //abyte[i] = (byte)Biome.getIdForBiome(InitBiome.BIOME_ONE);
        }

        chunk.resetRelightChecks();
        return chunk;
    }
    private void buildChunk(int x,int z,ChunkPrimer primer){
        if (z%3==0){
            WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(2 ,5,2),new BlockPos(2 ,255,15)),ModBlocks.BLOCK_END_BOOK_SHELF.getDefaultState());
            WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(7 ,5,2),new BlockPos(7 ,255,15)),ModBlocks.BLOCK_END_BOOK_SHELF.getDefaultState());
            WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(13,5,2),new BlockPos(13,255,15)),ModBlocks.BLOCK_END_BOOK_SHELF.getDefaultState());
        }
        if (z%3==1 || z%3==-1){
            WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(2 ,5,0),new BlockPos(2 ,255,15)),ModBlocks.BLOCK_END_BOOK_SHELF.getDefaultState());
            WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(7 ,5,0),new BlockPos(7 ,255,15)),Blocks.BOOKSHELF.getDefaultState());
            WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(13,5,0),new BlockPos(13,255,15)),Blocks.BOOKSHELF.getDefaultState());
        }
        if (z%3==2 || z%3==-2){
            WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(2 ,5,0),new BlockPos(2 ,255,13)),ModBlocks.BLOCK_END_BOOK_SHELF.getDefaultState());
            WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(7 ,5,0),new BlockPos(7 ,255,13)),ModBlocks.BLOCK_END_BOOK_SHELF.getDefaultState());
            WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(13,5,0),new BlockPos(13,255,13)),ModBlocks.BLOCK_END_BOOK_SHELF.getDefaultState());
        }


        WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(0,255,0),new BlockPos(15,255,15)), ModBlocks.BLOCK_HILEB_BLOCK.getDefaultState());
        WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(0,205,0),new BlockPos(15,200,15)), ModBlocks.BLOCK_HILEB_BLOCK.getDefaultState());
        WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(0,155,0),new BlockPos(15,155,15)), ModBlocks.BLOCK_HILEB_BLOCK.getDefaultState());
        WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(0,105,0),new BlockPos(15,105,15)), ModBlocks.BLOCK_HILEB_BLOCK.getDefaultState());
        WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(0,55 ,0),new BlockPos(15,55 ,15)), ModBlocks.BLOCK_HILEB_BLOCK.getDefaultState());
        WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(0,5  ,0),new BlockPos(15,5  ,15)), ModBlocks.BLOCK_HILEB_BLOCK.getDefaultState());

        if(x==0 && z==0){
            WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(0,0,0),new BlockPos(15,15,15)), ModBlocks.BLOCK_HILEB_BLOCK.getDefaultState());
            WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(1,1,1),new BlockPos(14,14,14)), Blocks.AIR.getDefaultState());

            WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(11,6,15),new BlockPos(8,15,15)), Blocks.AIR.getDefaultState());
            WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(5,6,15),new BlockPos(3,15,15)), Blocks.AIR.getDefaultState());

            WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(3,5,14),new BlockPos(11,0,14)),  ModBlocks.BLOCK_HILEB_BLOCK.getDefaultState());
            WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(3,5,13),new BlockPos(11,0,13)),  ModBlocks.BLOCK_HILEB_BLOCK.getDefaultState());
            WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(3,4,12),new BlockPos(11,0,12)),  ModBlocks.BLOCK_HILEB_BLOCK.getDefaultState());
            WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(3,3,11),new BlockPos(11,0,11)),  ModBlocks.BLOCK_HILEB_BLOCK.getDefaultState());
            WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(3,2,10),new BlockPos(11,0,10)),  ModBlocks.BLOCK_HILEB_BLOCK.getDefaultState());
            WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(3,1,9 ),new BlockPos(11,0,9 )),  ModBlocks.BLOCK_HILEB_BLOCK.getDefaultState());
            WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(3,0,8 ),new BlockPos(11,0,8 )),  ModBlocks.BLOCK_HILEB_BLOCK.getDefaultState());

            WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(3,5,13),new BlockPos(11,5,13)),((BlockStairs)Blocks.QUARTZ_STAIRS).getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH).withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.STRAIGHT).withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM));
            WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(3,4,12),new BlockPos(11,4,12)),((BlockStairs)Blocks.QUARTZ_STAIRS).getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH).withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.STRAIGHT).withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM));
            WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(3,3,11),new BlockPos(11,3,11)),((BlockStairs)Blocks.QUARTZ_STAIRS).getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH).withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.STRAIGHT).withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM));
            WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(3,2,10),new BlockPos(11,2,10)),((BlockStairs)Blocks.QUARTZ_STAIRS).getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH).withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.STRAIGHT).withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM));
            WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(3,1,9 ),new BlockPos(11,1,9 )),((BlockStairs)Blocks.QUARTZ_STAIRS).getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH).withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.STRAIGHT).withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM));
            return;
            //WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(3,0,8 ),new BlockPos(11,0,8 )),  ModBlocks.BLOCK_HILEB_BLOCK.getDefaultState());
        }
        for (IHilebStructure structure:structures){
            //IdlFramework.LogWarning("start!");
            if (structure.doStructure(x,z,primer))return;
        }
    }

    //真殖民
    @Override
    public void populate(int chunkX, int chunkZ) {
        net.minecraft.block.BlockFalling.fallInstantly = true;
        int x = chunkX * 16;
        int z = chunkZ * 16;

        net.minecraftforge.event.ForgeEventFactory.onChunkPopulate(true, this, this.world, this.rand, chunkX, chunkZ, false);

//        for (int y = 0; y < heightLimit; y+=CHUNK_SIZE) {
//
//
//            setSeedFor(x, y, z);
//            boolean hasDoorX = rand.nextBoolean();
//            boolean hasDoorY = y != 0 && rand.nextBoolean();//wont fall to void
//            boolean hasDoorZ = rand.nextBoolean();
//
//            GenCubeBase gen = new GenCubeRoom1(true);
//
//            if (gen != null)
//            {
//                gen.setHasDoorXYZ(hasDoorX, hasDoorY, hasDoorZ);
//                gen.setHasLightXYZ(true);
//                gen.generate(world, rand, new BlockPos(x, y, z));
//            }
//        }
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
