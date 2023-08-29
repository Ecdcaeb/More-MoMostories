package com.Hileb.moremomostories.common.world.level.dimension;

import com.Hileb.moremomostories.MoreMoMoSrories;
import com.Hileb.moremomostories.common.init.ModConfig;
import com.Hileb.moremomostories.common.world.level.structure.StructurePrimerTree;
import com.Hileb.moremomostories.common.world.level.worldgen.WorldGenHelper;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Mod.EventBusSubscriber(modid= MoreMoMoSrories.MODID)
public class DimensionEdge extends WorldProvider {
    public static final DimensionType DIM_TYPE = DimensionType.register("edge", "_testdim", ModConfig.dimension.WORLD_GEN_ZFP, DimensionEdge.class, false);

    // DimensionManager.registerDimension(DimensionEdge.ID, DimensionEdge.DIM_TYPE);
    //不要忘记使用上述方法注册维度
    public DimensionEdge() {
        this.biomeProvider = new BiomeProviderSingle(Biomes.VOID);//这里代表使用原版的void群系
        hasSkyLight = false;
    }

    @Override
    public DimensionType getDimensionType() {
        return DIM_TYPE;
    }

    @Override
    public IChunkGenerator createChunkGenerator() {//返回一个该维度的区块构造器，这里使用内部类书写
        return new ChunkGeneratorEdge(world, true, world.getSeed());
    }

    @Override
    public boolean canRespawnHere() {
        return true;
    }

    @Override
    public boolean isSurfaceWorld() {
        return false;
    }

    public static class ChunkGeneratorEdge implements IChunkGenerator {//区块构造器

        public HashMap<String, MapGenStructure> structureHashMap=new HashMap<>();
        public StructurePrimerTree structureTest=new StructurePrimerTree();
        private static final int heightLimit = 255;
        //private static final int yNoGateLimit = 255;

       // public List<IHilebStructure> structuresHileb =new ArrayList<>();
        //public HashMap<String, MapGenStructure> structureHashMap=new HashMap<>();
        //public StructureTest structureTest=new StructureTest();
        private final World world;
        private final boolean generateStructures;
        private final Random rand;

        public ChunkGeneratorEdge(World world, boolean generate, long seed) {
            this.world = world;
            this.generateStructures = generate;
            this.rand = new Random(seed);
            world.setSeaLevel(63);

            structureHashMap.put(structureTest.getStructureName(),structureTest);
        }


        @Override//是否在某结构里
        public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
            if (structureHashMap.containsKey(structureName)){
                return structureHashMap.get(structureName).isInsideStructure(pos);
            }
            return false;
        }

        @Override
        public void recreateStructures(Chunk chunkIn, int x, int z) {
            //建造巨构
            for (MapGenStructure structure:structureHashMap.values()){
                structure.generate(world,x,z,null);
            }
        }

        @Nullable
        @Override
        public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
            //寻找巨构
            //用于locate指令
            if (structureHashMap.containsKey(structureName)){
                return structureHashMap.get(structureName).getNearestStructurePos(worldIn,position,findUnexplored);
            }
            return BlockPos.ORIGIN;
        }

        @Override
        public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
            //指定维度能够生成的生物
            return Biomes.FOREST.getSpawnableList(creatureType);
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
                abyte[i] = (byte)Biome.getIdForBiome(Biomes.FOREST);
                //abyte[i] = (byte)Biome.getIdForBiome(ModBiomes.BIOME_ONE);
            }

            chunk.resetRelightChecks();
            return chunk;
        }
        private void buildChunk(int x,int z,ChunkPrimer primer) {
            for (int x_ = 0; x_ < 16; x_++) {
                for (int z_ = 0; z_ < 16; z_++) {
                    primer.setBlockState(x_, 0, z_, Blocks.BEDROCK.getDefaultState());//生成基岩
                    /**primer中的x,y,z是单个区块内部的x,y,z**/
                }
            }
            if (x * x + z * z <= 100) {//距离原点10个区块内
                for (int x_ = 0; x_ < 16; x_++) {
                    for (int z_ = 0; z_ < 16; z_++) {
                        for (int y_ = 1; y_ <= 60; y_++) {
                            primer.setBlockState(x_, y_, z_, Blocks.STONE.getDefaultState());//生成石头
                        }
                        primer.setBlockState(x_, 61, z_, Blocks.GRASS.getDefaultState());//生成草
                        /**primer中的x,y,z是单个区块内部的x,y,z**/
                    }
                }
            } else {
                for (int x_ = 0; x_ < 16; x_++) {
                    for (int z_ = 0; z_ < 16; z_++) {
                        for (int y_ = 1; y_ <= 50; y_++) {
                            primer.setBlockState(x_, y_, z_, Blocks.STONE.getDefaultState());//生成石头
                        }
                        for (int y_ = 51; y_ <= 54; y_++) {
                                primer.setBlockState(x_, y_, z_, Blocks.GRASS.getDefaultState());//生成石头
                        }
                        for (int y_ = 55; y_ <= 61; y_++) {
                            primer.setBlockState(x_, y_, z_, Blocks.WATER.getDefaultState());//生成石头
                        }
                        /**primer中的x,y,z是单个区块内部的x,y,z**/
                    }
                }
            }


            for (MapGenStructure structure : structureHashMap.values()) {
                structure.generate(world, x, z, primer);
            }



            if (x == 0 && z == 0) {
                for (int x_ = 0; x_ <= 15; x_++) {
                    for (int z_ = 0; z_ <= 15; z_++) {
                        if(WorldGenHelper.getLong(new BlockPos(0,0,0),new BlockPos(x,0,z))<=16f){
                            for (int y_ = world.getSeaLevel(); y_ <= 200; y_++) {
                                primer.setBlockState(x_, y_, z_, (Blocks.LOG.getStateFromMeta(0)));
                            }
                        }
                    }
                }
            }
            if (x == 1 && z == 1) {
                for (int x_ = 0; x_ <= 15; x_++) {
                    for (int z_ = 0; z_ <= 15; z_++) {
                        if(WorldGenHelper.getLong(new BlockPos(0,0,0),new BlockPos(x+16,0,z+16))<=16f){
                            for (int y_ = world.getSeaLevel(); y_ <= 200; y_++) {
                                primer.setBlockState(x_, y_, z_, (Blocks.LOG.getStateFromMeta(0)));
                            }
                        }
                    }
                }
            }
            if (x == 0 && z == 1) {
                for (int x_ = 0; x_ <= 15; x_++) {
                    for (int z_ = 0; z_ <= 15; z_++) {
                        if(WorldGenHelper.getLong(new BlockPos(0,0,0),new BlockPos(x,0,z+16))<=16f){
                            for (int y_ = world.getSeaLevel(); y_ <= 200; y_++) {
                                primer.setBlockState(x_, y_, z_, (Blocks.LOG.getStateFromMeta(0)));
                            }
                        }
                    }
                }
            }
            if (x == 1 && z == 0) {
                for (int x_ = 0; x_ <= 15; x_++) {
                    for (int z_ = 0; z_ <= 15; z_++) {
                        if(WorldGenHelper.getLong(new BlockPos(0,0,0),new BlockPos(x+16,0,z))<=16f){
                            for (int y_ = world.getSeaLevel(); y_ <= 200; y_++) {
                                primer.setBlockState(x_, y_, z_, (Blocks.LOG.getStateFromMeta(0)));
                            }
                        }
                    }
                }
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

//            //try build all
            for (MapGenStructure structure:structureHashMap.values()){
                structure.generateStructure(world,rand,new ChunkPos(chunkX,chunkZ));
            }

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

}
