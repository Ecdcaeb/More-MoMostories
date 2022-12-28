package com.Hileb.moremomostories.worldgen;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;

public class WorldGenHelper {
    public static boolean isEmptyWithAABB(World world, AxisAlignedBB aabb){
        for(int x=(int)aabb.minX;x<=aabb.maxX;x++){
            for(int y=(int)aabb.minY;y<=aabb.maxY;y++){
                for(int z=(int)aabb.minZ;z<=aabb.maxZ;z++){
                    if (world.getBlockState(new BlockPos(x,y,z)).getBlock()!= Blocks.AIR){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    public static boolean genRoomWithAABB(ChunkPrimer primer, AxisAlignedBB aabb, IBlockState state){
        for(int x=(int)aabb.minX;x<=aabb.maxX;x++){
            for(int y=(int)aabb.minY;y<=aabb.maxY;y++){
                for(int z=(int)aabb.minZ;z<=aabb.maxZ;z++){

                    //IdlFramework.LogWarning("error with xyz %d %d %d",x,y,z);

                        primer.setBlockState(x,y,z,state);

                }
            }
        }
        for(int x=(int) aabb.minX;x<=aabb.maxX;x++){
            for(int y=(int) aabb.minY;y<=aabb.maxY;y++){
                for(int z=(int) aabb.minZ;z<=aabb.maxZ;z++){

                    if(primer.getBlockState(x,y,z)!=state)return false;
                }
            }
        }
        return true;
    }
}
