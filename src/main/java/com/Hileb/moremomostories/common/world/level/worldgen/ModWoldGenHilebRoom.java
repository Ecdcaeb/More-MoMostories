package com.Hileb.moremomostories.common.world.level.worldgen;

import com.Hileb.moremomostories.common.world.blocks.ModBlocks;
import com.Hileb.moremomostories.common.world.entity.entity.living.EntityGoldenGuide;
import com.Hileb.moremomostories.common.world.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fluids.BlockFluidBase;
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
            //MoreMoMoSrories.LogWarning("try to build hileb room");
            //world.setBlockState(new BlockPos(x,i,z), ModBlocks.ID.getDefaultState(),3);
            for( int i = 0; i <= 120; i++){
                if (world.getBlockState(new BlockPos(x, i, z)).getBlock() == Blocks.AIR) {
                    AxisAlignedBB aabb = new AxisAlignedBB(new BlockPos(x, i , z), new BlockPos(x+RoomX, i+RoomY , z+RoomZ));
                    if (WorldGenHelper.isEmptyWithAABB(world, aabb)) {
                        GenRoom(world,new BlockPos(x,i,z));
                        //MoreMoMoSrories.LogWarning("room in %d,%d,%d",x,i,z);
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
        BlockPos blockPoschest=new BlockPos((pos.getX()+(RoomX/2)),pos.getY()+1,pos.getZ()+(RoomZ/2));
        EntityGoldenGuide goldenGuide=new EntityGoldenGuide(world);
        world.spawnEntity(goldenGuide);
        goldenGuide.setPosition(blockPoschest.getX(),blockPoschest.getY()+1,blockPoschest.getZ());
        //MoreMoMoSrories.LogWarning("chest in %d %d %d",blockPoschest.getX(),blockPoschest.getY(),blockPoschest.getZ());
        world.setBlockState(blockPoschest,Blocks.CHEST.getDefaultState(),3);
        TileEntityChest chest=(TileEntityChest) world.getTileEntity(blockPoschest);
        chest.setInventorySlotContents(0,new ItemStack(ModItems.ITEM_11_A));
        chest.setInventorySlotContents(1,new ItemStack(Items.DIAMOND,new Random().nextInt(4)));
        chest.setInventorySlotContents(2,new ItemStack(ModItems.ITEM_PAPER_IDONOTWANTTODIE));
        chest.setInventorySlotContents(3,new ItemStack(ModItems.ITEM_MAIN_XK,2));
        chest.setInventorySlotContents(4,new ItemStack(ModItems.ITEM_FIBER_WIRE,1));
        chest.setInventorySlotContents(5,new ItemStack(ModItems.ITEM_MAGATAMA,1));
        chest.setInventorySlotContents(6,new ItemStack(ModItems.ITEM_CARD_ZFP,3));
        chest.setInventorySlotContents(7,new ItemStack(com.gq2529.momostories.item.ModItems.THE_BOOK_OF_MANIFESTATION,1));
        chest.setInventorySlotContents(8,new ItemStack(ModItems.ITEM_ZFP_HEADSET,1));
        chest.setInventorySlotContents(9,new ItemStack(ModItems.ITEM_PAPER_HILEB_A,1));


        world.setBlockState(new BlockPos(blockPoschest.getX()+1,pos.getY()+1,blockPoschest.getZ()+2),Blocks.CRAFTING_TABLE.getDefaultState(),3);
        world.setBlockState(new BlockPos(blockPoschest.getX()+2,pos.getY()+1,blockPoschest.getZ()+2),Blocks.PRISMARINE.getDefaultState(),3);
        world.setBlockState(new BlockPos(blockPoschest.getX()+3,pos.getY()+1,blockPoschest.getZ()+2),Blocks.BOOKSHELF.getDefaultState(),3);
        world.setBlockState(new BlockPos(blockPoschest.getX()+4,pos.getY()+1,blockPoschest.getZ()+2),Blocks.BED.getBlockState().getValidStates().get(0),3);
        world.setBlockState(new BlockPos(blockPoschest.getX()+5,pos.getY()+1,blockPoschest.getZ()+2),Blocks.BED.getBlockState().getValidStates().get(1),3);

//        world.setBlockState(new BlockPos(((pos.getX()+RoomX)/2),pos.getY()+1,((pos.getZ()+RoomZ)/2)),Blocks.OAK_DOOR.getDefaultState(),3);
//        world.getTileEntity(new BlockPos(((pos.getX()+RoomX)/2),pos.getY()+1,((pos.getZ()+RoomZ)/2))).
    }
    public void GenGround(World world,BlockPos pos){
//        for(int y=pos.getY();y>=0;y--){
//            if (WorldGenHelper.hasAirWithAABB(world,new AxisAlignedBB(new BlockPos(pos.getX(),y,pos.getZ()),new BlockPos(pos.getX()+RoomX,y,pos.getZ()+RoomZ))) || WorldGenHelper.hasWaterWithAABB(world,new AxisAlignedBB(new BlockPos(pos.getX(),y,pos.getZ()),new BlockPos(pos.getX()+RoomX,y,pos.getZ()+RoomZ)))){
//                for(int a=0;a<=RoomX;a++){
//                    for(int b=0;b<=RoomZ;b++){
//                        if (world.getBlockState(new BlockPos(pos.getX()+a,y,pos.getZ()+b)).getBlock()==Blocks.AIR){
//                            world.setBlockState(new BlockPos(pos.getX()+a,y,pos.getZ()+b),MainBlock.getDefaultState(),3);
//                        }
//                        if (world.getBlockState(new BlockPos(pos.getX()+a,y,pos.getZ()+b)).getBlock()==Blocks.WATER){
//                            world.setBlockState(new BlockPos(pos.getX()+a,y,pos.getZ()+b),MainBlock.getDefaultState(),3);
//                        }
//                    }
//                }
//            }
//        }
        //MoreMoMoSrories.LogWarning("try a");
        for(int a=0;a<=RoomX;a++){
            //MoreMoMoSrories.LogWarning("try a ing");
            for(int b=0;b<=RoomZ;b++){
                //MoreMoMoSrories.LogWarning("try b ing");
                for(int y=pos.getY()-1;y>=0;y--){
                    //MoreMoMoSrories.LogWarning("try y ing");

                    BlockPos pos1=new BlockPos(pos.getX()+a,y,pos.getZ()+b);
                    if(world.getBlockState(pos1).getBlock()==Blocks.AIR || world.getBlockState(pos1).getBlock() instanceof BlockFluidBase || world.getBlockState(pos1).getBlock()==Blocks.LAVA || world.getBlockState(pos1).getBlock()==Blocks.WATER){
                        world.setBlockState(pos1,MainBlock.getDefaultState(),3);
                        //MoreMoMoSrories.LogWarning("try y successful");
                    }
                    else{
                        //MoreMoMoSrories.LogWarning("try y error in %d %d %d && it is %s",pos1.getX(),pos1.getY(),pos1.getZ(),world.getBlockState(pos1).getBlock().getUnlocalizedName());
                        break;
                    }
                }
            }
        }
    }
}

