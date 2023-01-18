package com.Hileb.moremomostories.entity;

import com.Hileb.moremomostories.IdlFramework;
import com.Hileb.moremomostories.blocks.ModBlocks;
import com.Hileb.moremomostories.entity.ai.block.EntityAIBreakBlock;
import com.Hileb.moremomostories.util.CommonFunctions;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
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
        this.tasks.addTask(3, new EntityAIBreakBlock(this, ModBlocks.BLOCK_END_BOOK_SHELF,30));
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
    @Override
    protected void dropFewItems(boolean arg1, int arg2) {
        if (arg1 == true) {
        }
    }


    public boolean attackEntityAsMob(Entity entityIn)
    {
        boolean flag = super.attackEntityAsMob(entityIn);

        if (flag)
        {
            float f = this.world.getDifficultyForLocation(new BlockPos(this)).getAdditionalDifficulty();

            if (this.getHeldItemMainhand().isEmpty() && this.isBurning() && this.rand.nextFloat() < f * 0.3F)
            {
                entityIn.setFire(2 * (int)f);
            }
        }

        return flag;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (!isFindingBlock){
            Chunk chunk=this.world.getChunkFromBlockCoords(this.getPosition());
            if (!world.isRemote){
                if (getNerstBlock(ModBlocks.BLOCK_END_BOOK_SHELF,chunk)!=null){
                    BlockPos posT=getNerstBlock(ModBlocks.BLOCK_END_BOOK_SHELF,chunk);
                    BlockPos pos=new BlockPos(this.world.getChunkFromBlockCoords(this.getPosition()).x*16+posT.getX(),posT.getY(),this.world.getChunkFromBlockCoords(this.getPosition()).z*16+posT.getZ());
                    this.getNavigator().setPath(this.getNavigator().getPathToPos(pos),1.2F);
                    IdlFramework.LogWarning("target in %d %d %d == %s",pos.getX(),pos.getY(),pos.getZ(),world.getBlockState(pos).getBlock().getUnlocalizedName());
                    this.isFindingBlock=true;
                }
            }
        }
    }
    private BlockPos getNerstBlock(Block block,Chunk chunk){
        BlockPos pos=null;
        double limit=6000;
        for(int y=0;y<=255;y++){
            for(int x=0;x<16;x++){
                for(int z=0;z<16;z++){
                    if (chunk.getBlockState(x,y,z).getBlock()==block){
                        BlockPos posTemp=new BlockPos(x,y,z);
                        IdlFramework.LogWarning("on 1 in %d %d %d %s",posTemp.getX(),posTemp.getY(),posTemp.getZ(),chunk.getBlockState(posTemp).getBlock().getUnlocalizedName());
                        //if (this.canPosBeSeen(posTemp)){
                            IdlFramework.LogWarning("on 2");
                            if (getLong(posTemp,this.getPosition())<limit){
                                IdlFramework.LogWarning("on 3");
                                pos=posTemp;
                                limit=getLong(posTemp,this.getPosition());
                            }
                        //}
                    }
                }
            }
        }
        return pos;
    }
    private double getLong(BlockPos pos1,BlockPos pos2){
        return Math.sqrt(((pos1.getX()-pos2.getX())*(pos1.getX()-pos2.getX()))+((pos1.getY()-pos2.getY())*(pos1.getY()-pos2.getY()))+((pos1.getZ()-pos2.getZ())*(pos1.getZ()-pos2.getZ())));
    }
    private boolean canPosBeSeen(BlockPos blockpos){
        Random random = this.getRNG();
        int i = MathHelper.floor(this.posX - 2.0D + random.nextDouble() * 4.0D);
        int j = MathHelper.floor(this.posY + random.nextDouble() * 3.0D);
        int k = MathHelper.floor(this.posZ - 2.0D + random.nextDouble() * 4.0D);
        RayTraceResult raytraceresult = world.rayTraceBlocks(new Vec3d((double)((float) MathHelper.floor(this.posX) + 0.5F), (double)((float)j + 0.5F), (double)((float)MathHelper.floor(this.posZ) + 0.5F)),new Vec3d(blockpos.getX(),blockpos.getY(),blockpos.getZ()), false, true, false);
        return raytraceresult != null && raytraceresult.getBlockPos().equals(blockpos);
    }


    @Override
    public boolean canEntityBeSeen(Entity entityIn) {
        return super.canEntityBeSeen(entityIn);
    }
}
