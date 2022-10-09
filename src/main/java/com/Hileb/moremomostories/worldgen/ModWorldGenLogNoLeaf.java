package com.Hileb.moremomostories.worldgen;

import com.Hileb.moremomostories.blocks.ModBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class ModWorldGenLogNoLeaf implements IWorldGenerator {
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {

        long send=world.provider.getSeed();
        int tree_long=random.nextInt()%8+4;
        int z=random.nextInt(1024)%16+chunkX*16;
        int x=random.nextInt(1024)%16+chunkZ*16;
        if (world.provider.getDimension()==0 && random.nextInt(256)<4 ){
            //IdlFramework.LogWarning("ore id in %d,%d,%d",x,i,z);
            //world.setBlockState(new BlockPos(x,i,z), ModBlocks.ID.getDefaultState(),3);
            for (int i=0;i<=120;i++){
                if (world.getBlockState(new BlockPos(x,i,z)).getBlock()== Blocks.GRASS){
                    AxisAlignedBB aabb=new AxisAlignedBB(new BlockPos(x,i+1,z),new BlockPos(x,i+tree_long,z));
                    if (WorldGenHelper.isEmptyWithAABB(world,aabb)){
                        //IdlFramework.LogWarning("in %d,%d,%d",x,i,z);
                        for(int p=1;p<=tree_long;p++){
                            world.setBlockState(new BlockPos(x,i+p,z), ModBlocks.BLOCK_WOOD_NO_LEAF.getDefaultState(),3);
                        }
                    }
                }
            }

        }
    }
}
