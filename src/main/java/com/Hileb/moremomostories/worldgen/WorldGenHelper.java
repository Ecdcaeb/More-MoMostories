package com.Hileb.moremomostories.worldgen;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

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
    public static boolean hasAirWithAABB(World world, AxisAlignedBB aabb){
        for(int x=(int)aabb.minX;x<=aabb.maxX;x++){
            for(int y=(int)aabb.minY;y<=aabb.maxY;y++){
                for(int z=(int)aabb.minZ;z<=aabb.maxZ;z++){
                    if (world.getBlockState(new BlockPos(x,y,z)).getBlock()== Blocks.AIR){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static boolean hasWaterWithAABB(World world, AxisAlignedBB aabb){
        for(int x=(int)aabb.minX;x<=aabb.maxX;x++){
            for(int y=(int)aabb.minY;y<=aabb.maxY;y++){
                for(int z=(int)aabb.minZ;z<=aabb.maxZ;z++){
                    if (world.getBlockState(new BlockPos(x,y,z)).getBlock()== Blocks.WATER){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
