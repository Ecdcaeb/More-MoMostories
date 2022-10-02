package com.Hileb.moremomostories.entity;

import com.Hileb.moremomostories.command.ModCommands;
import com.Hileb.moremomostories.item.ModItems;
import com.Hileb.moremomostories.util.CommonFunctions;
import net.minecraft.block.*;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

import javax.annotation.Nullable;
import java.util.List;

public class EntityZQ extends EntityAnimal {
    long time=0;
    public EntityZQ(World worldIn) {
        super(worldIn);
        this.setHealth(100.0f);
        this.setSize(0.6F, 1.95F);
        CommonFunctions.addToEventBus(this);
    }
    @Nullable
    @Override
    public EntityAgeable createChild(EntityAgeable ageable) {
        return null;
    }
    protected void initEntityAI()
    {
    }
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
    }

    @Override
    public boolean canBeAttackedWithItem() {
        return false;
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

    @Override
    public boolean attackable() {
        return false;
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
    protected void dropFewItems(boolean arg1, int arg2)
    {
        if (arg1==true) {
        }
    }

    @Override
    public EnumActionResult applyPlayerInteraction(EntityPlayer player, Vec3d vec, EnumHand hand) {
        if (!player.world.isRemote){
            if (player.getHeldItemMainhand().isEmpty() && time>=50){
                this.isDead=true;
                ModCommands.give(player,new ItemStack(ModItems.ITEM_ADD_ENTITYZQ));
            }
        }
        return super.applyPlayerInteraction(player, vec, hand);
    }
    @Override
    public void onUpdate()
    {
        if (!this.world.isRemote)time++;
        if (this.world.getTotalWorldTime() % 80L == 0L)
        {
            updateEntityZFP();
        }
        if (!this.world.isRemote){
            Block block=this.world.getBlockState(new BlockPos(this.posX,this.posY-1,this.posZ)).getBlock();
            if((block instanceof BlockAir)){
                this.isDead=true;
                this.world.spawnEntity(new EntityItem(this.world,this.posX,this.posY,this.posZ,new ItemStack(ModItems.ITEM_ADD_ENTITYZQ)));
            }
        }
    }
    public void updateEntityZFP(){
        if (!this.world.isRemote){
            List<EntityPlayer> players=world.playerEntities;
            for(int i=0;i<players.size();i++){
                EntityPlayer player=players.get(i);
                double DOUBLE_PLAYER_ENTITY_X=(player.posX-this.posX);
                double DOUBLE_PLAYER_ENTITY_Y=(player.posY-this.posY);
                double DOUBLE_PLAYER_ENTITY_Z=(player.posZ-this.posZ);
                DOUBLE_PLAYER_ENTITY_X=DOUBLE_PLAYER_ENTITY_X*DOUBLE_PLAYER_ENTITY_X;
                DOUBLE_PLAYER_ENTITY_Y=DOUBLE_PLAYER_ENTITY_Y*DOUBLE_PLAYER_ENTITY_Y;
                DOUBLE_PLAYER_ENTITY_Z=DOUBLE_PLAYER_ENTITY_Z*DOUBLE_PLAYER_ENTITY_Z;
                double DOUBLE_PLAYER_ENTITY_ALL=Math.sqrt((DOUBLE_PLAYER_ENTITY_X+DOUBLE_PLAYER_ENTITY_Y+DOUBLE_PLAYER_ENTITY_Z));
                if(DOUBLE_PLAYER_ENTITY_ALL<=20.0f){
                    player.addPotionEffect(new PotionEffect(MobEffects.SPEED,100,1));
                    player.addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH,100,1));
                }
            }
        }
    }
}
