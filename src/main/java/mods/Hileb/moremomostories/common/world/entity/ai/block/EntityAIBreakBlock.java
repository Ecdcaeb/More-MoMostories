package mods.Hileb.moremomostories.common.world.entity.ai.block;

import mods.Hileb.moremomostories.common.world.entity.entity.living.EntityBookworm;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;

public class EntityAIBreakBlock extends EntityAIInteractBlock {
    private int breakingTime;
    private final int breakingTimeMax;
    private int previousBreakProgress = -1;

    public EntityAIBreakBlock(EntityLiving entityIn,Block blockIn,int maxTime)
    {
        super(entityIn,blockIn);
        breakingTimeMax=maxTime;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (!super.shouldExecute())
        {
            return false;
        }
        else if (!net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.entity.world, this.entity) || !this.entity.world.getBlockState(this.blockPosition).getBlock().canEntityDestroy(this.entity.world.getBlockState(this.blockPosition), this.entity.world, this.blockPosition, this.entity) || !net.minecraftforge.event.ForgeEventFactory.onEntityDestroyBlock(this.entity, this.blockPosition, this.entity.world.getBlockState(this.blockPosition)))
        {
            return false;
        }
        else
        {
            return targetingBlock==this.target;
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        super.startExecuting();
        this.breakingTime = 0;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean shouldContinueExecuting()
    {
        double d0 = this.entity.getDistanceSq(this.blockPosition);
        boolean flag;

        if (this.breakingTime <= 240)
        {
            Block blockdoor = this.targetingBlock;

            if (true && d0 < 4.0D)
            {
                flag = true;
                return flag;
            }
        }

        flag = false;
        return flag;
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void resetTask()
    {
        super.resetTask();
        this.entity.world.sendBlockBreakProgress(this.entity.getEntityId(), this.blockPosition, -1);
        ((EntityBookworm)entity).isFindingBlock=false;
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void updateTask()
    {
        super.updateTask();

        if (this.entity.getRNG().nextInt(20) == 0)
        {
            this.entity.world.playEvent(1019, this.blockPosition, 0);
        }

        ++this.breakingTime;
        int i = (int)((float)this.breakingTime / breakingTimeMax * 10.0F);

        if (i != this.previousBreakProgress)
        {
            this.entity.world.sendBlockBreakProgress(this.entity.getEntityId(), this.blockPosition, i);
            this.previousBreakProgress = i;
        }

        if (this.breakingTime == breakingTimeMax)
        {
            this.entity.world.setBlockToAir(this.blockPosition);
            this.entity.world.playEvent(1021, this.blockPosition, 0);
            this.entity.world.playEvent(2001, this.blockPosition, Block.getIdFromBlock(this.targetingBlock));
        }
    }
}
