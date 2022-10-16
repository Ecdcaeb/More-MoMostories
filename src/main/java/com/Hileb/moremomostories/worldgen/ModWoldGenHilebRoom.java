package com.Hileb.moremomostories.worldgen;

import com.Hileb.moremomostories.IdlFramework;
import com.Hileb.moremomostories.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class ModWoldGenHilebRoom implements IWorldGenerator {
    public final int RoomX=20;
    public final int RoomY=6;
    public final int RoomZ=20;
    public final Block MainBlock=ModBlocks.BLOCK_HILEB_BLOCK;
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {

        long send = world.provider.getSeed();
        int z = random.nextInt(1024) % 16 + chunkX * 16;
        int x = random.nextInt(1024) % 16 + chunkZ * 16;
        if (world.provider.getDimension() == 0 && random.nextInt(100000) < 4 ) {
            IdlFramework.LogWarning("try to build hileb room");
            //world.setBlockState(new BlockPos(x,i,z), ModBlocks.ID.getDefaultState(),3);
            for( int i = 0; i <= 120; i++){
                if (world.getBlockState(new BlockPos(x, i, z)).getBlock() == Blocks.AIR) {
                    AxisAlignedBB aabb = new AxisAlignedBB(new BlockPos(x, i , z), new BlockPos(x+RoomX, i+RoomY , z+RoomZ));
                    if (WorldGenHelper.isEmptyWithAABB(world, aabb)) {
                        GenRoom(world,new BlockPos(x,i,z));
                        GenRoom(world,new BlockPos(x,i+RoomY,z));
                        GenRoom(world,new BlockPos(x,i+(2*RoomY),z));
                        IdlFramework.LogWarning("room in %d,%d,%d",x,i,z);
                        GenGround(world,new BlockPos(x,i,z));
                        return;
                    }
                }
            }

        }
    }
    public void GenRoom(World world,BlockPos pos){
        for(int a=0;a<=RoomX;a++){
            for(int b=0;b<=RoomZ;b++){
                world.setBlockState(new BlockPos(pos.getX()+a,pos.getY(),pos.getZ()+b),MainBlock.getDefaultState(),3);
            }
        }
        for(int a=0;a<=RoomX;a++){
            for(int b=0;b<=RoomZ;b++){
                world.setBlockState(new BlockPos(pos.getX()+a,pos.getY()+RoomY-1,pos.getZ()+b),Blocks.SEA_LANTERN.getDefaultState(),3);
            }
        }
        for(int y=1;y<RoomY;y++){
            for (int a=0;a<=RoomX;a++){
                world.setBlockState(new BlockPos(pos.getX()+a,pos.getY()+y,pos.getZ()),MainBlock.getDefaultState(),3);
                world.setBlockState(new BlockPos(pos.getX()+a,pos.getY()+y,pos.getZ()+RoomZ),MainBlock.getDefaultState(),3);
            }
            for (int b=0;b<=RoomZ;b++){
                world.setBlockState(new BlockPos(pos.getX(),pos.getY()+y,pos.getZ()+b),MainBlock.getDefaultState(),3);
                world.setBlockState(new BlockPos(pos.getX()+RoomX,pos.getY()+y,pos.getZ()+b),MainBlock.getDefaultState(),3);
            }
        }
        for(int a=0;a<=RoomX;a++){
            for(int b=0;b<=RoomZ;b++){
                world.setBlockState(new BlockPos(pos.getX()+a,pos.getY()+RoomY,pos.getZ()+b),MainBlock.getDefaultState(),3);
            }
        }
//        world.setBlockState(new BlockPos(((pos.getX()+RoomX)/2),pos.getY()+1,((pos.getZ()+RoomZ)/2)),Blocks.OAK_DOOR.getDefaultState(),3);
//        world.getTileEntity(new BlockPos(((pos.getX()+RoomX)/2),pos.getY()+1,((pos.getZ()+RoomZ)/2))).
    }
    public void GenGround(World world,BlockPos pos){
        for(int y=pos.getY();y>=0;y--){
            if (WorldGenHelper.hasAirWithAABB(world,new AxisAlignedBB(new BlockPos(pos.getX(),y,pos.getZ()),new BlockPos(pos.getX()+RoomX,y,pos.getZ()+RoomZ))) || WorldGenHelper.hasWaterWithAABB(world,new AxisAlignedBB(new BlockPos(pos.getX(),y,pos.getZ()),new BlockPos(pos.getX()+RoomX,y,pos.getZ()+RoomZ)))){
                for(int a=0;a<=RoomX;a++){
                    for(int b=0;b<=RoomZ;b++){
                        if (world.getBlockState(new BlockPos(pos.getX()+a,y,pos.getZ()+b)).getBlock()==Blocks.AIR){
                            world.setBlockState(new BlockPos(pos.getX()+a,y,pos.getZ()+b),MainBlock.getDefaultState(),3);
                        }
                        if (world.getBlockState(new BlockPos(pos.getX()+a,y,pos.getZ()+b)).getBlock()==Blocks.WATER){
                            world.setBlockState(new BlockPos(pos.getX()+a,y,pos.getZ()+b),MainBlock.getDefaultState(),3);
                        }
                    }
                }
            }
        }
    }
}

