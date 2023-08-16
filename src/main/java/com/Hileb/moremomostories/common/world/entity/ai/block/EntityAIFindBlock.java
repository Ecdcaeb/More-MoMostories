package com.Hileb.moremomostories.common.world.entity.ai.block;

import com.Hileb.moremomostories.common.world.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.chunk.Chunk;

public class EntityAIFindBlock extends EntityAIBase {
    protected EntityLiving entityLiving;
    protected Block block;
    private boolean haveDone;
    public EntityAIFindBlock(EntityLiving living,Block blockIn){
        entityLiving=living;
        block=blockIn;
        haveDone=false;
    }
    public boolean shouldExecute(){
        Chunk chunk=this.entityLiving.world.getChunkFromBlockCoords(this.entityLiving.getPosition());
        if (!this.entityLiving.world.isRemote){
            if (getNerstBlock(ModBlocks.BLOCK_END_BOOK_SHELF,chunk)!=null){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean shouldContinueExecuting() {
        return this.shouldExecute()&& haveDone;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void resetTask()
    {
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void updateTask()
    {
        onUpdate();
    }
    public void onUpdate() {
        Chunk chunk=this.entityLiving.world.getChunkFromBlockCoords(this.entityLiving.getPosition());
        if (!this.entityLiving.world.isRemote){
            if (getNerstBlock(ModBlocks.BLOCK_END_BOOK_SHELF,chunk)!=null){
                BlockPos pos=getNerstBlock(ModBlocks.BLOCK_END_BOOK_SHELF,chunk);
                this.entityLiving.getNavigator().setPath(this.entityLiving.getNavigator().getPathToPos(pos),1.2F);
            }
        }
    }
    private BlockPos getNerstBlock(Block block, Chunk chunk){
        BlockPos pos=null;
        double limit=6000;
        for(int y=0;y<=255;y++){
            for(int x=0;x<16;x++){
                for(int z=0;z<16;z++){
                    if (chunk.getBlockState(x,y,z).getBlock()==block){
                        BlockPos posTemp=new BlockPos(x,y,z);
                        if (this.canPosBeSeen(posTemp)){
                            if (getLong(posTemp,this.entityLiving.getPosition())<limit){
                                pos=posTemp;
                                limit=getLong(posTemp,this.entityLiving.getPosition());
                            }
                        }
                    }
                }
            }
        }
        return pos;
    }
    private double getLong(BlockPos pos1,BlockPos pos2){
        return Math.sqrt(((pos1.getX()-pos2.getX())*(pos1.getX()-pos2.getX()))+((pos1.getY()-pos2.getY())*(pos1.getY()-pos2.getY()))+((pos1.getZ()-pos2.getZ())*(pos1.getZ()-pos2.getZ())));
    }
    private boolean canPosBeSeen(BlockPos pos)
    {
        return this.entityLiving.world.rayTraceBlocks(new Vec3d(this.entityLiving.posX, this.entityLiving.posY + (double)this.entityLiving.getEyeHeight(), this.entityLiving.posZ), new Vec3d(pos.getX(), pos.getY() , pos.getZ()), false, true, false) == null;
    }
}
