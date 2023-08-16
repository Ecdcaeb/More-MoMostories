package com.Hileb.moremomostories.common.world.level.structure;

import com.Hileb.moremomostories.common.world.blocks.ModBlocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.*;
import net.minecraft.world.gen.structure.template.TemplateManager;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class StructureTest extends MapGenStructure {
    private static final int MAX_DISTANCE=32;
    private static final int SEED=20230101;
    private static final int ATTEMPS=100;

    public static void register(){
        MapGenStructureIO.registerStructure(StructureTest.Start.class,StructureTest.getStructureNameS()+"_start");
        MapGenStructureIO.registerStructureComponent(StructureTest.Start.StructureComponentTest.class,StructureTest.getStructureNameS()+"_component");
    }

    public static String getStructureNameS() {
        return "structure_test";
    }
    @Override
    public String getStructureName() {
        return "structure_test";
    }
    @Nullable
    @Override
    public BlockPos getNearestStructurePos(World worldIn, BlockPos pos, boolean findUnexplored) {
        return findNearestStructurePosBySpacing(worldIn,this,pos,MAX_DISTANCE,8,SEED,false,ATTEMPS,findUnexplored);
    }

    @Override
    protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
        Random random=new Random(getStructureName().hashCode()+ATTEMPS+chunkX+chunkZ+world.getSeed());
        if (random.nextInt(100)==99){
            return true;
        }
        return false;
    }
    @Override
    protected StructureStart getStructureStart(int chunkX, int chunkZ) {
        return new Start(world,rand,chunkX,chunkZ);
    }

    public static class Start extends StructureStart {
        protected Random random;
        public BlockPos structurePos;
        public Start(){
            //摆烂
        }
        public Start(World worldIn,Random randomIn,int chunkXIn,int chunkZIn){
            super(chunkXIn,chunkZIn);
            random=randomIn;
            init(worldIn,randomIn,chunkXIn,chunkZIn);

            updateBoundingBox();
        }
        private void init(World worldIn,Random randomIn,int chunkXIn,int chunkZIn){
            structurePos = new BlockPos(chunkXIn*16, worldIn.getSeaLevel(), chunkZIn*16);

            components.add(new StructureComponentTest(structurePos));
        }
        public static class StructureComponentTest extends StructureComponent {
            public BlockPos basePos;
            public StructureComponentTest(){}
            public StructureComponentTest(BlockPos pos){
                init(pos);
            }


            @Override
            protected void writeStructureToNBT(NBTTagCompound tagCompound) {
                tagCompound.setInteger("X",basePos.getX());
                tagCompound.setInteger("Y",basePos.getY());
                tagCompound.setInteger("Z",basePos.getZ());
            }

            @Override
            protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager p_143011_2_) {
                init(new BlockPos(tagCompound.getInteger("X"),tagCompound.getInteger("Y"),tagCompound.getInteger("Z")));
            }

            @Override
            public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand) {
            }
            public void init(BlockPos pos){
                basePos=pos;
                this.boundingBox=new StructureBoundingBox();
                this.boundingBox.maxX=basePos.getX()+32;
                this.boundingBox.minX=basePos.getX()-32;
                this.boundingBox.maxZ=basePos.getZ()+32;
                this.boundingBox.minZ=basePos.getZ()-32;
                this.boundingBox.maxY=basePos.getY()+32;
                this.boundingBox.minY=basePos.getY()-32;
            }

            @Override
            public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) {
                //MoreMoMoSrories.LogWarning("this box %d %d %d - %d %d %d",this.boundingBox.minX,this.boundingBox.minY,this.boundingBox.minZ,this.boundingBox.maxX,this.boundingBox.maxY,this.boundingBox.maxZ);
                //MoreMoMoSrories.LogWarning("and box %d %d %d - %d %d %d",structureBoundingBoxIn.minX,structureBoundingBoxIn.minY,structureBoundingBoxIn.minZ,structureBoundingBoxIn.maxX,structureBoundingBoxIn.maxY,structureBoundingBoxIn.maxZ);
                for (int x=structureBoundingBoxIn.minX;x<=structureBoundingBoxIn.maxX;x++){
                    for (int y=this.boundingBox.minY;y<=this.boundingBox.maxY;y++){
                        for (int z=structureBoundingBoxIn.minZ;z<=structureBoundingBoxIn.maxZ;z++){
                            setBlockState(worldIn, ModBlocks.BLOCK_BLACK_STONE_BRICK.getDefaultState(),x,y,z,structureBoundingBoxIn);
                        }
                    }
                }
                return true;
            }
        }

    } //作者：道家深湖 https://www.bilibili.com/read/cv18536202?spm_id_from=333.999.0.0 出处：bilibili
}
