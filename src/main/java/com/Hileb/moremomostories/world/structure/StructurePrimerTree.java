package com.Hileb.moremomostories.world.structure;

import com.Hileb.moremomostories.IdlFramework;
import com.Hileb.moremomostories.worldgen.WorldGenHelper;
import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.*;
import net.minecraft.world.gen.structure.template.TemplateManager;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StructurePrimerTree extends MapGenStructure {
    private static final int MAX_DISTANCE=64;
    private static final int MIN_DISTANCE=8;
    private static final int SEED=20230114;
    private static final int ATTEMPS=1000;
    public static final String NAME="structure_primer_tree";

    public static void register(){
        MapGenStructureIO.registerStructure(StructurePrimerTree.Start.class,NAME+"_start");
        MapGenStructureIO.registerStructureComponent(StructurePrimerTree.Start.StructureComponentTrunk.class,NAME+"_main");
        MapGenStructureIO.registerStructureComponent(StructurePrimerTree.Start.StructureComponentRoot.class,NAME+"_root");
    }

    @Override
    public String getStructureName() {
        return NAME;
    }
    @Nullable
    @Override
    public BlockPos getNearestStructurePos(World worldIn, BlockPos pos, boolean findUnexplored) {
        return findNearestStructurePosBySpacing(worldIn,this,pos,MAX_DISTANCE,MIN_DISTANCE,SEED,false,ATTEMPS,findUnexplored);
    }

    @Override
    protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
        int i = chunkX;
        int j = chunkZ;

        if (chunkX < 0)
        {
            chunkX -= this.MAX_DISTANCE - 1;
        }

        if (chunkZ < 0)
        {
            chunkZ -= MAX_DISTANCE - 1;
        }

        int k = chunkX / MAX_DISTANCE;
        int l = chunkZ / MAX_DISTANCE;
        Random random = this.world.setRandomSeed(k, l, SEED);
        k = k *MAX_DISTANCE;
        l = l * MAX_DISTANCE;
        k = k + random.nextInt(MAX_DISTANCE-MIN_DISTANCE);
        l = l + random.nextInt(MAX_DISTANCE-MIN_DISTANCE);

        if (i == k && j == l)
        {
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

            //IdlFramework.LogWarning("pos in %d %d %d",structurePos.getX(),structurePos.getY(),structurePos.getZ());
            components.add(new StructureComponentTrunk( new BlockPos(chunkXIn*16, worldIn.getSeaLevel(), chunkZIn*16)));
            components.add(new StructureComponentRoot( new BlockPos(chunkXIn*16, worldIn.getSeaLevel(), chunkZIn*16),randomIn));
        }
        /**树干*/
        public static class StructureComponentTrunk extends StructureComponent {
            public BlockPos basePos;
            public StructureComponentTrunk(){}
            public StructureComponentTrunk(BlockPos pos){
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
                BlockPos pos=new BlockPos(tagCompound.getInteger("X"),tagCompound.getInteger("Y"),tagCompound.getInteger("Z"));
                init(pos);
            }

            @Override
            public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand) {
            }
            public void init(BlockPos pos){
                basePos=pos;
                this.boundingBox=new StructureBoundingBox();
                this.boundingBox.maxX=basePos.getX()+16;
                this.boundingBox.minX=basePos.getX()-16;
                this.boundingBox.maxZ=basePos.getZ()+16;
                this.boundingBox.minZ=basePos.getZ()-16;
                this.boundingBox.maxY=200;
                this.boundingBox.minY=basePos.getY();
                this.setCoordBaseMode(EnumFacing.NORTH);
            }

            @Override
            public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) {
                for (int x=basePos.getX()-16;x<=basePos.getX()+16;x++){
                    for (int z=basePos.getZ()-16;z<=basePos.getZ()+16;z++){
                        if(WorldGenHelper.getLong(new BlockPos(x,0,z),new BlockPos(basePos.getX(),0,basePos.getZ()))<=16f){
                            for (int y =worldIn.getSeaLevel(); y<=200; y++){
                                if (structureBoundingBoxIn.isVecInside(new BlockPos(x,y,z))){
                                    worldIn.setBlockState(new BlockPos(x,y,z),(Blocks.LOG.getStateFromMeta(0)),3);
                                    if(WorldGenHelper.getLong(new BlockPos(x,0,z),new BlockPos(basePos.getX(),0,basePos.getZ()))>=14f){
                                        if (randomIn.nextInt(5)==0){
                                            worldIn.setBlockToAir(new BlockPos(x,y,z));
                                        }
                                    }
                                    if(WorldGenHelper.getLong(new BlockPos(x,0,z),new BlockPos(basePos.getX(),0,basePos.getZ()))>=15f){
                                        if (randomIn.nextInt(5)>=3){
                                            worldIn.setBlockToAir(new BlockPos(x,y,z));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                return true;
            }
        }



        /**树根*/
        public static class StructureComponentRoot extends StructureComponent {
            public BlockPos basePos;
            public List<BlockPos> rootPointPos=new ArrayList<>();
            public StructureComponentRoot(){
            }
            public StructureComponentRoot(BlockPos pos,Random random){
                int maxCount=random.nextInt(90)+50;
                for(int i=0;i<maxCount;i++){
                    BlockPos newPos=new BlockPos(pos.getX()+random.nextInt(100)-50,pos.getY()-Math.abs((random.nextInt(50))),pos.getZ()+random.nextInt(100)-50);
                    rootPointPos.add(newPos);
                    IdlFramework.LogWarning("y=%d but yN=%d",pos.getY(),newPos.getY());
                }
                init(pos);
            }


            @Override
            protected void writeStructureToNBT(NBTTagCompound tagCompound) {
                tagCompound.setInteger("X", basePos.getX());
                tagCompound.setInteger("Y", basePos.getY());
                tagCompound.setInteger("Z", basePos.getZ());
                tagCompound.setInteger("count",rootPointPos.size());
                for(int i=0;i<rootPointPos.size();i++){
                    tagCompound.setInteger(String.format("pos%dx",i),rootPointPos.get(i).getX());
                    tagCompound.setInteger(String.format("pos%dy",i),rootPointPos.get(i).getY());
                    tagCompound.setInteger(String.format("pos%dz",i),rootPointPos.get(i).getZ());
                }
            }
            @Override
            protected void readStructureFromNBT (NBTTagCompound tagCompound, TemplateManager p_143011_2_){
                BlockPos pos = new BlockPos(tagCompound.getInteger("X"), tagCompound.getInteger("Y"), tagCompound.getInteger("Z"));
                int count=tagCompound.getInteger("count");
                rootPointPos.clear();
                for(int i=0;i<count;i++){
                    rootPointPos.add(new BlockPos(tagCompound.getInteger(String.format("pos%dx",i)),tagCompound.getInteger(String.format("pos%dy",i)),tagCompound.getInteger(String.format("pos%dz",i))));
                }
                init(pos);
            }


            @Override
            public void buildComponent (StructureComponent componentIn, List < StructureComponent > listIn, Random rand){
            }
            public void init (BlockPos pos){
                basePos = pos;
                this.boundingBox = getBox();
                this.setCoordBaseMode(EnumFacing.NORTH);
            }
            public StructureBoundingBox getBox(){
                StructureBoundingBox box=new StructureBoundingBox();
                box.minZ=rootPointPos.get(0).getZ();
                box.maxZ=rootPointPos.get(0).getZ();
                box.minX=rootPointPos.get(0).getX();
                box.maxX=rootPointPos.get(0).getX();
                box.minY=rootPointPos.get(0).getY();
                box.maxY=rootPointPos.get(0).getY();
                for(BlockPos pos:rootPointPos){
                    box.maxX=Math.max(pos.getX(),box.maxX);
                    box.maxY=Math.max(pos.getY(),box.maxY);
                    box.maxZ=Math.max(pos.getZ(),box.maxZ);

                    box.minX=Math.min(pos.getX(),box.minX);
                    box.minZ=Math.min(pos.getY(),box.minY);
                    box.minZ=Math.min(pos.getZ(),box.minZ);
                }
                return box;
            }
            @Override
            public boolean addComponentParts (World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn){
                for(int i=1;i<(rootPointPos.size()/2);i++){
                    BlockPos posX=rootPointPos.get(randomIn.nextInt(rootPointPos.size()));
                    BlockPos posY=rootPointPos.get(randomIn.nextInt(rootPointPos.size()));
                    buildBetweenTwoPos(posX,posY,worldIn,structureBoundingBoxIn,3);
                }
                for(BlockPos pos:rootPointPos){
                    buildBetweenTwoPos(basePos,pos,worldIn,structureBoundingBoxIn,8);
                }
                return true;
            }
            private void buildBetweenTwoPos(BlockPos posX,BlockPos posY,World worldIn,StructureBoundingBox structureBoundingBoxIn,int r){
                float xValue=(posX.getX()-posY.getX());
                float yValue=(posX.getY()-posY.getY());
                float zValue=(posX.getZ()-posY.getZ());
                float longGet=(float) WorldGenHelper.getLong(posX,posY);
                xValue=xValue/longGet;
                yValue=yValue/longGet;
                zValue=zValue/longGet;

                for (int longValue=0;longValue<=longGet;longValue++){
                    BlockPos createPos=new BlockPos(posX.getX()+((int)longValue*xValue),posX.getY()+((int)longValue*yValue),posX.getZ()+((int)longValue*zValue));
                    List<BlockPos> creatList=new ArrayList<>();
                    float xAbs=Math.abs(xValue);
                    float yAbs=Math.abs(yValue);
                    float zAbs=Math.abs(zValue);

                    BlockLog.EnumAxis face;
                    if (xAbs>yAbs && xAbs>zAbs)face=BlockLog.EnumAxis.X;
                    else if (yAbs>xAbs && yAbs>zAbs)face=BlockLog.EnumAxis.Y;
                    else if (zAbs>xAbs && zAbs>yAbs)face=BlockLog.EnumAxis.Z;
                    else face=BlockLog.EnumAxis.Y;

                    float range=r*(longValue/longGet);

                    if (face==BlockLog.EnumAxis.X){
                        for(int y=(int)(-range);y<=range;y++){
                            for(int z=(int)(-range);z<=range;z++){
                                if (WorldGenHelper.getLong(new BlockPos(0,y,z),new BlockPos(0,createPos.getY(),createPos.getZ()))<=range){
                                    creatList.add(new BlockPos(createPos.getX(),y,z));
                                }
                            }
                        }
                    }
                    else if (face==BlockLog.EnumAxis.Y){
                        for(int x=(int)(-range);x<=range;x++){
                            for(int z=(int)(-range);z<=range;z++){
                                if (WorldGenHelper.getLong(new BlockPos(x,0,z),new BlockPos(createPos.getX(),0,createPos.getZ()))<=range){
                                    creatList.add(new BlockPos(x,createPos.getY(),z));
                                }
                            }
                        }
                    }
                    else if (face==BlockLog.EnumAxis.Z){
                        for(int x=(int)(-range);x<=range;x++){
                            for(int y=(int)(-range);y<=range;y++){
                                if (WorldGenHelper.getLong(new BlockPos(x,y,0),new BlockPos(createPos.getX(),createPos.getY(),0))<=range){
                                    creatList.add(new BlockPos(x,y,createPos.getZ()));
                                }
                            }
                        }
                    }

                    IBlockState state=(Blocks.LOG.getStateFromMeta(0)).withProperty(BlockLog.LOG_AXIS, face);
                    if (structureBoundingBoxIn.isVecInside(createPos))worldIn.setBlockState(createPos,state,3);
                    for(BlockPos posI:creatList){
                        if (structureBoundingBoxIn.isVecInside(posI)){
                            worldIn.setBlockState(posI,state,3);
                            IdlFramework.LogWarning("I pos %d %d %d",posI.getX(),posI.getY(),posI.getZ());
                        }
                    }
                }
            }

        }

        /**树冠*/
        /**房间*/

        //class
        public static class TreePoint{

        }

    } //作者：道家深湖 https://www.bilibili.com/read/cv18536202?spm_id_from=333.999.0.0 出处：bilibili
}
