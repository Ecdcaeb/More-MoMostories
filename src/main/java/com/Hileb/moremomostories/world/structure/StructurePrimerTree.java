package com.Hileb.moremomostories.world.structure;

import com.Hileb.moremomostories.blocks.ModBlocks;
import com.Hileb.moremomostories.worldgen.WorldGenHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.*;
import net.minecraft.world.gen.structure.template.TemplateManager;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class StructurePrimerTree extends MapGenStructure {
    private static final int MAX_DISTANCE=32;
    private static final int SEED=20230114;
    private static final int ATTEMPS=100;
    public static final String NAME="structure_primer_tree";

    public static void register(){
        MapGenStructureIO.registerStructure(StructurePrimerTree.Start.class,NAME+"_start");
        MapGenStructureIO.registerStructureComponent(StructurePrimerTree.Start.StructureComponentTrunk.class,NAME+"_main");
    }

    @Override
    public String getStructureName() {
        return NAME;
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

            components.add(new StructureComponentTrunk(structurePos));
        }
        //
        //
        //
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
                init(new BlockPos(tagCompound.getInteger("X"),tagCompound.getInteger("Y"),tagCompound.getInteger("Z")));
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
                //IdlFramework.LogWarning("this box %d %d %d - %d %d %d",this.boundingBox.minX,this.boundingBox.minY,this.boundingBox.minZ,this.boundingBox.maxX,this.boundingBox.maxY,this.boundingBox.maxZ);
                //IdlFramework.LogWarning("and box %d %d %d - %d %d %d",structureBoundingBoxIn.minX,structureBoundingBoxIn.minY,structureBoundingBoxIn.minZ,structureBoundingBoxIn.maxX,structureBoundingBoxIn.maxY,structureBoundingBoxIn.maxZ);
                for (int x=structureBoundingBoxIn.minX;x<=structureBoundingBoxIn.maxX;x++){
                    for (int y = WorldGenHelper.getAverageGroundLevel(worldIn,this.boundingBox,structureBoundingBoxIn); y<=this.boundingBox.maxY; y++){
                        for (int z=structureBoundingBoxIn.minZ;z<=structureBoundingBoxIn.maxZ;z++){
                            if ((((basePos.getX()-x)*(basePos.getX()-x))+((basePos.getZ()-z)*(basePos.getZ()-z))<=256)){
                                //IdlFramework.LogWarning("pos %d %d %d",x,y,z);
                                setBlockState(worldIn, ModBlocks.BLOCK_WOOD_NO_LEAF.getDefaultState(),x,y,z,structureBoundingBoxIn);
                            }
                        }
                    }
                }
                return true;
            }
        }
        /**树根*/
        /**树冠*/
        /**房间*/

    } //作者：道家深湖 https://www.bilibili.com/read/cv18536202?spm_id_from=333.999.0.0 出处：bilibili
}
