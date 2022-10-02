package com.Hileb.moremomostories.entity;

import com.Hileb.moremomostories.Advancements.Advancementkeys;
import com.Hileb.moremomostories.Advancements.ModAdvancementsInit;
import com.Hileb.moremomostories.util.CommonFunctions;
import com.Hileb.moremomostories.util.NBTStrDef.IDLNBTUtil;
import com.gq2529.momostories.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nullable;
import java.util.UUID;

public class EntityZFP extends EntityAnimal {
    public UUID uuid;

    public EntityZFP(World worldIn) {
        super(worldIn);
        this.setHealth(100.0f);
        this.setSize(0.6F, 1.95F);
        CommonFunctions.addToEventBus(this);
    }

    @Override
    public EnumActionResult applyPlayerInteraction(EntityPlayer player, Vec3d vec, EnumHand hand) {
        if (player.getHeldItemMainhand().getItem()==ElectricShakingItem()){
            IDLNBTUtil.SetBoolean(this,"isElectricShaking",true);
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.FAIL;
    }

    @Nullable
    @Override
    public EntityAgeable createChild(EntityAgeable ageable) {
        return null;
    }

    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.applyEntityAI();
    }
    protected void applyEntityAI()
    {
//        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[] {EntityPigZombie.class}));
//        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
//        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityVillager.class, false));
//        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityIronGolem.class, true));
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
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
//    }
//    @SubscribeEvent
//    public  void  PlayerHurt(LivingHurtEvent event){
//        if(!event.getEntity().world.isRemote){
//            if(event.getEntityLiving() instanceof EntityPlayer){
//                EntityPlayer player = (EntityPlayer)event.getEntityLiving();
//                if(event.getSource().getTrueSource() instanceof EntityArrow){
//                    if(player.getUniqueID()==uuid || this.getTags().contains(player.getUniqueID().toString())){
//                        event.isCanceled();
//                        this.attemptTeleport(player.posX,player.posY,player.posZ);
//                    }
//                }
//            }
//        }
//    }
    public boolean isElectricShaking(){
        return IDLNBTUtil.GetBoolean(this,"isElectricShaking",false);
    }
    public Item ElectricShakingItem(){
        return com.Hileb.moremomostories.item.ModItems.ITEM_DO_FOREVER;
    }
    @SubscribeEvent
    public void onAttack(LivingHurtEvent event){
        World world = event.getEntity().world;
        if(!world.isRemote){
            if (event.getEntityLiving() instanceof EntityZFP){
                if (event.getSource().getTrueSource()!=null && event.getSource().getTrueSource() instanceof  EntityPlayer){
                    EntityPlayer player=(EntityPlayer) event.getSource().getTrueSource();
                    if (player.getHeldItemMainhand().getItem() == ModItems.REPLICA_1){
                        ModAdvancementsInit.giveAdvancement(player, Advancementkeys.AD_ZFPHIGH);
                    }
                }
            }
        }
    }
}

