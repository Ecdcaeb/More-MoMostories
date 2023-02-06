package com.Hileb.moremomostories.worldgen;

import com.gq2529.momostories.blocks.ModBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class ModWorldGenOreId implements IWorldGenerator {
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {

        long send=world.provider.getSeed();
        int i=random.nextInt(1024)%64+16;
        int z=random.nextInt(1024)%16+chunkX*16;
        int x=random.nextInt(1024)%16+chunkZ*16;
        random.nextInt();
        random.nextInt();
        if (world.provider.getDimension()==0 &&random.nextInt(256)<4 && world.getBlockState(new BlockPos(x,i,z)).getBlock()== Blocks.STONE){
            world.setBlockState(new BlockPos(x,i,z), ModBlocks.ID.getDefaultState(),3);
        }
    }
}
