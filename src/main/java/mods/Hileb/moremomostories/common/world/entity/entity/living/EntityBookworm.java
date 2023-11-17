package mods.Hileb.moremomostories.common.world.entity.entity.living;

import mods.Hileb.moremomostories.common.world.blocks.ModBlocks;
import mods.Hileb.moremomostories.common.util.CommonFunctions;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

import javax.annotation.Nullable;
import java.util.Random;

public class EntityBookworm extends EntityMob {
    public boolean isFindingBlock=false;
    public EntityBookworm (World worldIn) {
        super(worldIn);
        this.setHealth(100.0f);
        this.setSize(0.6F, 1.95F);
        CommonFunctions.addToEventBus(this);
    }


    protected void initEntityAI()
    {
        this.tasks.addTask(3, new AIBreakBlock(this));
        this.tasks.addTask(4, new EntityAISwimming(this));
        this.tasks.addTask(5, new EntityAIAttackMelee(this, 1.0D, false));
        this.tasks.addTask(6, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(9, new EntityAILookIdle(this));
        this.applyEntityAI();
    }
    protected void applyEntityAI()
    {
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[] {EntityBookworm.class}));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityGoldenGuide.class, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
    }

    protected void applyEntityAttributes() {

        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.53000000417232513D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
    }

    protected SoundEvent getAmbientSound()
    {
        return null;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return null;
    }

    protected SoundEvent getDeathSound()
    {
        return null;
    }

    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(SoundEvents.ENTITY_COW_STEP, 0.15F, 1.0F);
    }

    /**
     * Returns the volume for the sounds this mob makes.
     */
    protected float getSoundVolume()
    {
        return 0.4F;
    }

    @Nullable
    protected ResourceLocation getLootTable()
    {
        return LootTableList.EMPTY;
    }


    public float getEyeHeight()
    {
        return this.isChild() ? this.height : 1.3F;
    }

    @Override//寻路：方块权重
    public float getBlockPathWeight(BlockPos pos)
    {
        return this.world.getBlockState(pos).getBlock() == ModBlocks.BLOCK_END_BOOK_SHELF ? 10.0F : super.getBlockPathWeight(pos);
    }
    public static boolean canBreak(Block block){
        return block==ModBlocks.BLOCK_END_BOOK_SHELF;
    }
    public static class AIBreakBlock extends EntityAIWander {
        private EnumFacing facing;
        private boolean doMerge;

        public AIBreakBlock(EntityCreature silverfishIn) {
            super(silverfishIn, 1.0D, 10);
            this.setMutexBits(1);
        }
        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute()
        {
            if (this.entity.getAttackTarget() != null)
            {
                return false;
            }
            else if (!this.entity.getNavigator().noPath())
            {
                return false;
            }
            else
            {
                Random random = this.entity.getRNG();

                if (net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.entity.world, this.entity) && random.nextInt(10) == 0)
                {
                    this.facing = EnumFacing.random(random);
                    BlockPos blockpos = (new BlockPos(this.entity.posX, this.entity.posY, this.entity.posZ)).offset(this.facing);
                    IBlockState iblockstate = this.entity.world.getBlockState(blockpos);

                    if (canBreak(iblockstate.getBlock()))
                    {
                        this.doMerge = true;
                        return true;
                    }
                }

                this.doMerge = false;
                return super.shouldExecute();
            }
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting()
        {
            return !this.doMerge && super.shouldContinueExecuting();
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting()
        {
            if (!this.doMerge)
            {
                super.startExecuting();
            }
            else
            {
                World world = this.entity.world;
                BlockPos blockpos = (new BlockPos(this.entity.posX, this.entity.posY, this.entity.posZ)).offset(this.facing);
                IBlockState iblockstate = world.getBlockState(blockpos);

                if (canBreak(iblockstate.getBlock()))
                {
                    world.destroyBlock(blockpos,true);
                    this.entity.spawnExplosionParticle();
                }
            }
        }
    }
}
