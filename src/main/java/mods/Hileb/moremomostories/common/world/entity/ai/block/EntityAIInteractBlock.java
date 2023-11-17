package mods.Hileb.moremomostories.common.world.entity.ai.block;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.math.BlockPos;

public abstract class EntityAIInteractBlock extends EntityAIBase
{
    protected EntityLiving entity;
    protected BlockPos blockPosition = BlockPos.ORIGIN;
    /** The wooden door block */
    protected Block targetingBlock;
    /** If is true then the Entity has stopped Door Interaction and compoleted the task. */
    boolean hasStoppedBlockInteraction;
    float entityPositionX;
    float entityPositionZ;
    protected final Block target;

    public EntityAIInteractBlock(EntityLiving entityIn,Block targetIn)
    {
        this.entity = entityIn;
        this.target=targetIn;

        if (!(entityIn.getNavigator() instanceof PathNavigateGround))
        {
            throw new IllegalArgumentException("Unsupported mob type for BlockInteractGoal");
        }
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (!this.entity.collidedHorizontally)
        {
            return false;
        }
        else
        {
            PathNavigateGround pathnavigateground = (PathNavigateGround)this.entity.getNavigator();
            Path path = pathnavigateground.getPath();

            if (path != null && !path.isFinished() )
            {
                for (int i = 0; i < Math.min(path.getCurrentPathIndex() + 2, path.getCurrentPathLength()); ++i)
                {
                    PathPoint pathpoint = path.getPathPointFromIndex(i);
                    this.blockPosition = new BlockPos(pathpoint.x, pathpoint.y + 1, pathpoint.z);

                    if (this.entity.getDistanceSq((double)this.blockPosition.getX(), this.entity.posY, (double)this.blockPosition.getZ()) <= 2.25D)
                    {
                        this.targetingBlock = this.getBlock(this.blockPosition);

                        if (this.targetingBlock != null)
                        {
                            return true;
                        }
                    }
                }

                this.blockPosition = (new BlockPos(this.entity)).up();
                this.targetingBlock = this.getBlock(this.blockPosition);
                return this.targetingBlock != null;
            }
            else
            {
                return false;
            }
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean shouldContinueExecuting()
    {
        return !this.hasStoppedBlockInteraction;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.hasStoppedBlockInteraction = false;
        this.entityPositionX = (float)((double)((float)this.blockPosition.getX() + 0.5F) - this.entity.posX);
        this.entityPositionZ = (float)((double)((float)this.blockPosition.getZ() + 0.5F) - this.entity.posZ);
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void updateTask()
    {
        float f = (float)((double)((float)this.blockPosition.getX() + 0.5F) - this.entity.posX);
        float f1 = (float)((double)((float)this.blockPosition.getZ() + 0.5F) - this.entity.posZ);
        float f2 = this.entityPositionX * f + this.entityPositionZ * f1;

        if (f2 < 0.0F)
        {
            this.hasStoppedBlockInteraction = true;
        }
    }

    private Block getBlock(BlockPos pos)
    {
        IBlockState iblockstate = this.entity.world.getBlockState(pos);
        Block block = iblockstate.getBlock();
        return block==target? block : null;
    }
}
