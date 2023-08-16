package com.Hileb.moremomostories.common.world.entity.entity.living.boss;

import com.Hileb.moremomostories.common.world.item.ModItems;
import com.Hileb.moremomostories.common.util.CommonFunctions;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

import javax.annotation.Nullable;
import java.util.Random;

public class EntityGoldenGuideBoss extends EntityBossBase {

    public EntityGoldenGuideBoss(World worldIn) {
        super(worldIn);
        this.setHealth(getMaxHealth());
        this.setSize(1.8F, 5.85F);
        CommonFunctions.addToEventBus(this);
    }


    @Override
    protected void aiTaskInit() {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, false));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
    }

    @Override
    protected void aiTargetTaskInit() {
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[] {EntityZombie.class}));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityVillager.class, false));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityIronGolem.class, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityMob.class, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityDragon.class, true));
    }

    @Override
    protected void initSkill() {

    }

    @Override
    public boolean canDestroyBlock(Block blockIn) {
        return false;
    }
    protected void applyEntityAttributes() {

        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(200D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.53000000417232513D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
    }

    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.ENTITY_IRONGOLEM_ATTACK;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundEvents.ENTITY_IRONGOLEM_HURT;
    }

    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_IRONGOLEM_HURT;
    }

    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(SoundEvents.ENTITY_IRONGOLEM_STEP, 0.15F, 1.0F);
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
    public void onDeath(DamageSource cause) {
        if (cause.damageType.equals("player")){
            Random random=new Random();
            random.setSeed(cause.hashCode());
            if (random.nextInt(1000)<1){
                net.minecraftforge.common.ForgeHooks.onPlayerTossEvent((EntityPlayer)cause.getTrueSource(), new ItemStack(ModItems.ITEM_Z).copy(), false);
            }
            if (random.nextInt(1000)<250){
                net.minecraftforge.common.ForgeHooks.onPlayerTossEvent((EntityPlayer)cause.getTrueSource(), new ItemStack(ModItems.ITEM_ARROM_XE).copy(), false);
            }
            if (random.nextInt(1000)<250){
                net.minecraftforge.common.ForgeHooks.onPlayerTossEvent((EntityPlayer)cause.getTrueSource(), new ItemStack(ModItems.ITEM_ZFP_HEADSET).copy(), false);
            }
            if (random.nextInt(1000)<250){
                net.minecraftforge.common.ForgeHooks.onPlayerTossEvent((EntityPlayer)cause.getTrueSource(), new ItemStack(ModItems.ITEM_SILVER_CHEST).copy(), false);
            }
            if (random.nextInt(1000)<250){
                net.minecraftforge.common.ForgeHooks.onPlayerTossEvent((EntityPlayer)cause.getTrueSource(), new ItemStack(ModItems.ITEM_VAN_CHEST).copy(), false);
            }
        }
        super.onDeath(cause);

        if (cause.getTrueSource() instanceof EntityPlayer){
            EntityBossDisdescable entityBossDisdescable=new EntityBossDisdescable(world);
            entityBossDisdescable.setPosition(posX,posY,posZ);
            world.spawnEntity(entityBossDisdescable);
        }
    }
}

