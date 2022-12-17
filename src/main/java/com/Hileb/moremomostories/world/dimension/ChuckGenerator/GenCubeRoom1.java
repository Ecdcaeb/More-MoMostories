package com.Hileb.moremomostories.world.dimension.ChuckGenerator;

import com.Hileb.moremomostories.IdlFramework;
import com.Hileb.moremomostories.blocks.ModBlocks;
import com.Hileb.moremomostories.entity.EntityGoldenGuide;
import com.Hileb.moremomostories.item.ModItems;
import com.Hileb.moremomostories.world.dimension.hexcube.structure.GenCubeBase;
import com.Hileb.moremomostories.worldgen.WorldGenHelper;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidBase;

import java.util.Random;

public class GenCubeRoom1 extends GenCubeBase {
    public final int RoomX_=20;
    public final int RoomY_=6;
    public final int RoomZ_=20;
    public final Block MainBlock_= ModBlocks.BLOCK_HILEB_BLOCK;
    public GenCubeRoom1(boolean notify) {
        super(notify);
    }
    public GenCubeRoom1(boolean notify, int xSize, int ySize, int zSize) {
        super(notify, xSize, ySize, zSize);
    }
    @Override
    public boolean generate(World world, Random rand, BlockPos positionOrigin) {
        long send = world.provider.getSeed();
        int z =  positionOrigin.getZ();
        int x =  positionOrigin.getX();
        if ( rand.nextInt(100000) < 4 ) {
            //IdlFramework.LogWarning("try to build hileb room");
            //world.setBlockState(new BlockPos(x,i,z), ModBlocks.ID.getDefaultState(),3);
            for( int i = 0; i <= 120; i++){
                if (world.getBlockState(new BlockPos(x, i, z)).getBlock() == Blocks.AIR) {
                    AxisAlignedBB aabb = new AxisAlignedBB(new BlockPos(x, i , z), new BlockPos(x+RoomX_, i+RoomY_ , z+RoomZ_));
                    if (WorldGenHelper.isEmptyWithAABB(world, aabb)) {
                        GenRoom(world,new BlockPos(x,i,z),RoomX_,RoomY_,RoomZ_,MainBlock_);
                        //IdlFramework.LogWarning("room in %d,%d,%d",x,i,z);
                        GenGround(world,new BlockPos(x,i,z),RoomX_,RoomZ_,MainBlock_);

                        return true;
                    }
                }
            }

        }
        return false;
    }
    public void GenRoom(World world,BlockPos pos,int RoomX,int RoomY,int RoomZ,Block MainBlock){
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
        IdlFramework.LogWarning("chest in %d %d %d",blockPoschest.getX(),blockPoschest.getY(),blockPoschest.getZ());
        world.setBlockState(blockPoschest,Blocks.CHEST.getDefaultState(),3);
        TileEntityChest chest=(TileEntityChest) world.getTileEntity(blockPoschest);
        chest.setInventorySlotContents(0,new ItemStack(ModItems.ITEM_11_A));
        chest.setInventorySlotContents(1,new ItemStack(Items.DIAMOND,new Random().nextInt(4)));
        chest.setInventorySlotContents(2,new ItemStack(ModItems.ITEM_PAPER_IDONOTWANTTODIE));
        chest.setInventorySlotContents(3,new ItemStack(ModItems.ITEM_MAIN_XK,2));
        chest.setInventorySlotContents(4,new ItemStack(ModItems.ITEM_12_B,1));


        world.setBlockState(new BlockPos(blockPoschest.getX()+1,pos.getY()+1,blockPoschest.getZ()+2),Blocks.CRAFTING_TABLE.getDefaultState(),3);
        world.setBlockState(new BlockPos(blockPoschest.getX()+2,pos.getY()+1,blockPoschest.getZ()+2),Blocks.PRISMARINE.getDefaultState(),3);
        world.setBlockState(new BlockPos(blockPoschest.getX()+3,pos.getY()+1,blockPoschest.getZ()+2),Blocks.BOOKSHELF.getDefaultState(),3);
        world.setBlockState(new BlockPos(blockPoschest.getX()+4,pos.getY()+1,blockPoschest.getZ()+2),Blocks.BED.getBlockState().getValidStates().get(0),3);
        world.setBlockState(new BlockPos(blockPoschest.getX()+5,pos.getY()+1,blockPoschest.getZ()+2),Blocks.BED.getBlockState().getValidStates().get(1),3);

//        world.setBlockState(new BlockPos(((pos.getX()+RoomX)/2),pos.getY()+1,((pos.getZ()+RoomZ)/2)),Blocks.OAK_DOOR.getDefaultState(),3);
//        world.getTileEntity(new BlockPos(((pos.getX()+RoomX)/2),pos.getY()+1,((pos.getZ()+RoomZ)/2))).
    }
    public void GenGround(World world, BlockPos pos, int RoomX, int RoomZ, Block MainBlock){
        for(int a=0;a<=RoomX;a++){
            for(int b=0;b<=RoomZ;b++){
                for(int y=pos.getY()-1;y>=0;y--){
                    BlockPos pos1=new BlockPos(pos.getX()+a,y,pos.getZ()+b);
                    if(world.getBlockState(pos1).getBlock()==Blocks.AIR || world.getBlockState(pos1).getBlock() instanceof BlockFluidBase || world.getBlockState(pos1).getBlock()==Blocks.LAVA || world.getBlockState(pos1).getBlock()==Blocks.WATER){
                        world.setBlockState(pos1,MainBlock.getDefaultState(),3);
                    }
                    else{
                        break;
                    }
                }
            }
        }
    }
}
