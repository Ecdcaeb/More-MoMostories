package com.Hileb.moremomostories.worldgen;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.structure.StructureBoundingBox;

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
    public static int getAverageGroundLevel(World worldIn,StructureBoundingBox boundingBox, StructureBoundingBox chunkBox)
    {
        int i = 0;
        int j = 0;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for (int k = boundingBox.minZ; k <= boundingBox.maxZ; ++k)
        {
            for (int l = boundingBox.minX; l <= boundingBox.maxX; ++l)
            {
                blockpos$mutableblockpos.setPos(l, 64, k);

                if (chunkBox.isVecInside(blockpos$mutableblockpos))
                {
                    i += Math.max(worldIn.getTopSolidOrLiquidBlock(blockpos$mutableblockpos).getY(), worldIn.provider.getAverageGroundLevel() - 1);
                    ++j;
                }
            }
        }

        if (j == 0)
        {
            return -1;
        }
        else
        {
            return i / j;
        }
    }
    public boolean spawn(World worldIn, StructureBoundingBox structurebb, int x, int y, int z, Entity spawm)
    {
        if (!structurebb.isVecInside(new BlockPos(x,y,z)))
        {
            return false;
        }
        spawm.setLocationAndAngles((double)x + 0.5D, (double)y, (double)z + 0.5D, 0.0F, 0.0F);
        worldIn.spawnEntity(spawm);
        return true;
    }
}
