package mods.Hileb.moremomostories.common.world.entity.entity.living;

import mods.Hileb.moremomostories.common.world.advancements.Advancementkeys;
import mods.Hileb.moremomostories.common.world.advancements.ModAdvancementsInit;
import mods.Hileb.moremomostories.common.world.damageSource.ModDamageSources;
import mods.Hileb.moremomostories.common.util.CommonFunctions;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nullable;
import java.util.List;

public class EntityVan extends EntityMob  {//van♂ man
    //public long coldTime;
    public EntityVan(World worldIn) {
        super(worldIn);
        //coldTime=worldIn.getTotalWorldTime();
        this.setHealth(100.0f);
        this.setSize(0.6F, 1.95F);
        CommonFunctions.addToEventBus(this);
    }
    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(9, new EntityAILookIdle(this));
        this.applyEntityAI();
    }
    protected void applyEntityAI()
    {
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[] {EntityZombie.class}));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
    }

    protected void applyEntityAttributes() {

        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.53000000417232513D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(0.00D);
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
        if (!world.isRemote){
            AxisAlignedBB aabb=new AxisAlignedBB(new BlockPos(this.posX-3,this.posY-3,this.posZ-3),new BlockPos(this.posX+3,this.posY+3,this.posZ+3));
            List<EntityPlayer> players=world.getEntitiesWithinAABB(EntityPlayer.class,aabb);
            for(EntityPlayer player:players){
                player.attackEntityFrom(ModDamageSources.VAN,10);
            }
        }
    }
    @SubscribeEvent
    public void onPlayerDeath(LivingDeathEvent event){
        World worldIn=event.getEntityLiving().world;
        if (!worldIn.isRemote){
            if (event.getEntityLiving() instanceof EntityPlayer){
                if (event.getSource() == ModDamageSources.VAN){
                    EntityPlayer player=(EntityPlayer) event.getEntityLiving();
                    ModAdvancementsInit.giveAdvancement(player, Advancementkeys.AD_LX);
                }
            }
        }
    }
}
