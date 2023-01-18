package com.Hileb.moremomostories.world.dimension;

import com.Hileb.moremomostories.IdlFramework;
import com.Hileb.moremomostories.world.structure.StructurePrimerTree;
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

@Mod.EventBusSubscriber(modid= IdlFramework.MODID)
public class DimensionEdge extends WorldProvider {
    public static final int ID=7899;//这里是数字id，建议使用配置文件
    public static final DimensionType DIM_TYPE = DimensionType.register("edge", "_testdim",ID, DimensionEdge.class, false);

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
            //hilebStructureInit();
            //mcStructureInit();
            structureHashMap.put(structureTest.getStructureName(),structureTest);
        }

        /***这些被注释掉的代码不是必要的**/
//        public void mcStructureInit(){
//            structureHashMap.put(structureTest.getStructureName(),structureTest);
//        }
//        public void hilebStructureInit(){
//            structuresHileb.add(new IHilebStructure() {
//                @Override
//                public String getName() {
//                    return "iup";
//                }
//
//                @Override
//                public boolean doStructure(int x, int z, ChunkPrimer primer) {
//                    Random  random=new Random(x+z+primer.hashCode());
//                    if (random.nextInt(100)<=1){
//                        //IdlFramework.LogWarning("a big Structure in %d  50 %d",x*16,z*16);
//                        WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(0 ,0,0),new BlockPos(15 ,255,15)), Blocks.AIR.getDefaultState());
//
//                        WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(0 ,0,0),new BlockPos(15 ,255,15)), ModBlocks.BLOCK_HILEB_BLOCK.getDefaultState());
//                        WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(1 ,1,1),new BlockPos(14 ,254,14)),Blocks.AIR.getDefaultState());
//                        return true;
//                    }
//                    return false;
//                }
//            });
//        }

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
                //abyte[i] = (byte)Biome.getIdForBiome(InitBiome.BIOME_ONE);
            }

            chunk.resetRelightChecks();
            return chunk;
        }
        private void buildChunk(int x,int z,ChunkPrimer primer){
            //primer阶段的绘制
            /**下方书架的绘制是不必要的**/
//            if (z%3==0){
//                WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(2 ,5,2),new BlockPos(2 ,255,15)),ModBlocks.BLOCK_END_BOOK_SHELF.getDefaultState());
//                WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(7 ,5,2),new BlockPos(7 ,255,15)),ModBlocks.BLOCK_END_BOOK_SHELF.getDefaultState());
//                WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(13,5,2),new BlockPos(13,255,15)),ModBlocks.BLOCK_END_BOOK_SHELF.getDefaultState());
//            }
//            if (z%3==1 || z%3==-1){
//                WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(2 ,5,0),new BlockPos(2 ,255,15)),ModBlocks.BLOCK_END_BOOK_SHELF.getDefaultState());
//                WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(7 ,5,0),new BlockPos(7 ,255,15)),Blocks.BOOKSHELF.getDefaultState());
//                WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(13,5,0),new BlockPos(13,255,15)),Blocks.BOOKSHELF.getDefaultState());
//            }
//            if (z%3==2 || z%3==-2){
//                WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(2 ,5,0),new BlockPos(2 ,255,13)),ModBlocks.BLOCK_END_BOOK_SHELF.getDefaultState());
//                WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(7 ,5,0),new BlockPos(7 ,255,13)),ModBlocks.BLOCK_END_BOOK_SHELF.getDefaultState());
//                WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(13,5,0),new BlockPos(13,255,13)),ModBlocks.BLOCK_END_BOOK_SHELF.getDefaultState());
//            }
//
//
//            WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(0,255,0),new BlockPos(15,255,15)), ModBlocks.BLOCK_HILEB_BLOCK.getDefaultState());
//            WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(0,205,0),new BlockPos(15,200,15)), ModBlocks.BLOCK_HILEB_BLOCK.getDefaultState());
//            WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(0,155,0),new BlockPos(15,155,15)), ModBlocks.BLOCK_HILEB_BLOCK.getDefaultState());
//            WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(0,105,0),new BlockPos(15,105,15)), ModBlocks.BLOCK_HILEB_BLOCK.getDefaultState());
//            WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(0,55 ,0),new BlockPos(15,55 ,15)), ModBlocks.BLOCK_HILEB_BLOCK.getDefaultState());
//            WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(0,5  ,0),new BlockPos(15,5  ,15)), ModBlocks.BLOCK_HILEB_BLOCK.getDefaultState());
//
//            if(x==0 && z==0){
//                WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(0,0,0),new BlockPos(15,15,15)), ModBlocks.BLOCK_HILEB_BLOCK.getDefaultState());
//                WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(1,1,1),new BlockPos(14,14,14)), Blocks.AIR.getDefaultState());
//
//                WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(11,6,15),new BlockPos(8,15,15)), Blocks.AIR.getDefaultState());
//                WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(5,6,15),new BlockPos(3,15,15)), Blocks.AIR.getDefaultState());
//
//                WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(3,5,14),new BlockPos(11,0,14)),  ModBlocks.BLOCK_HILEB_BLOCK.getDefaultState());
//                WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(3,5,13),new BlockPos(11,0,13)),  ModBlocks.BLOCK_HILEB_BLOCK.getDefaultState());
//                WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(3,4,12),new BlockPos(11,0,12)),  ModBlocks.BLOCK_HILEB_BLOCK.getDefaultState());
//                WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(3,3,11),new BlockPos(11,0,11)),  ModBlocks.BLOCK_HILEB_BLOCK.getDefaultState());
//                WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(3,2,10),new BlockPos(11,0,10)),  ModBlocks.BLOCK_HILEB_BLOCK.getDefaultState());
//                WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(3,1,9 ),new BlockPos(11,0,9 )),  ModBlocks.BLOCK_HILEB_BLOCK.getDefaultState());
//                WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(3,0,8 ),new BlockPos(11,0,8 )),  ModBlocks.BLOCK_HILEB_BLOCK.getDefaultState());
//
//                WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(3,5,13),new BlockPos(11,5,13)),((BlockStairs)Blocks.QUARTZ_STAIRS).getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH).withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.STRAIGHT).withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM));
//                WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(3,4,12),new BlockPos(11,4,12)),((BlockStairs)Blocks.QUARTZ_STAIRS).getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH).withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.STRAIGHT).withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM));
//                WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(3,3,11),new BlockPos(11,3,11)),((BlockStairs)Blocks.QUARTZ_STAIRS).getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH).withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.STRAIGHT).withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM));
//                WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(3,2,10),new BlockPos(11,2,10)),((BlockStairs)Blocks.QUARTZ_STAIRS).getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH).withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.STRAIGHT).withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM));
//                WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(3,1,9 ),new BlockPos(11,1,9 )),((BlockStairs)Blocks.QUARTZ_STAIRS).getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH).withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.STRAIGHT).withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM));
//                return;
//                //WorldGenHelper.genRoomWithAABB(primer,new AxisAlignedBB(new BlockPos(3,0,8 ),new BlockPos(11,0,8 )),  ModBlocks.BLOCK_HILEB_BLOCK.getDefaultState());
//            }
//            for (IHilebStructure structure: structuresHileb){
//                //IdlFramework.LogWarning("start!");
//                if (structure.doStructure(x,z,primer))return;
//            }
//
//            //try primer all

            for(int x_=0;x_<16;x_++){
                for (int z_=0;z_<16;z_++){
                    primer.setBlockState(x_,0,z_,Blocks.BEDROCK.getDefaultState());//生成基岩
                    /**primer中的x,y,z是单个区块内部的x,y,z**/
                }
            }
            if (x*x+z*z<=100){//距离原点10个区块内
                for(int x_=0;x_<16;x_++){
                    for (int z_=0;z_<16;z_++){
                        primer.setBlockState(x_,1,z_,Blocks.STONE.getDefaultState());//生成石头
                        primer.setBlockState(x_,2,z_,Blocks.GRASS.getDefaultState());//生成草
                        /**primer中的x,y,z是单个区块内部的x,y,z**/
                    }
                }
            }
            else {
                for(int x_=0;x_<16;x_++){
                    for (int z_=0;z_<16;z_++){
                        primer.setBlockState(x_,1,z_,Blocks.WATER.getDefaultState());//生成水
                        primer.setBlockState(x_,2,z_,Blocks.WATER.getDefaultState());//生成水
                        /**primer中的x,y,z是单个区块内部的x,y,z**/
                    }
                }
            }



            for (MapGenStructure structure:structureHashMap.values()){
                structure.generate(world,x,z,primer);
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

    //传送玩家
//    @SubscribeEvent
//    public static void tpPlayer(LivingEvent.LivingUpdateEvent event){
//        if (event.getEntityLiving() instanceof EntityPlayerMP){
//            EntityPlayerMP playerMP=( EntityPlayerMP)event.getEntityLiving();
//            if (playerMP.dimension==ID){
//                if(((playerMP.getPosition().getX()*playerMP.getPosition().getX())+(playerMP.getPosition().getZ()*playerMP.getPosition().getZ()))>=(16*15*16*15)){
//                    CommonFunctions.teleportToDimension(playerMP,0,0,80,0);
//                }
//            }
//        }
//    }

}
