package com.Hileb.moremomostories.entity;

import com.Hileb.moremomostories.Advancements.Advancementkeys;
import com.Hileb.moremomostories.Advancements.ModAdvancementsInit;
import com.Hileb.moremomostories.command.ModCommands;
import com.Hileb.moremomostories.item.myItems.ItemDao;
import com.Hileb.moremomostories.util.CommonFunctions;
import com.Hileb.moremomostories.util.NBTStrDef.IDLNBTUtil;
import com.gq2529.momostories.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class EntityZFP extends EntityAnimal {
    public static final String NBT_VALUE_OF_ZFP="com.hileb.nbt.valueOfZFP";
    public boolean isNearPlayer=false;

    public EntityZFP(World worldIn) {
        super(worldIn);
        this.setHealth(100.0f);
        this.setSize(0.6F, 1.95F);
        CommonFunctions.addToEventBus(this);
        this.setHeldItem(EnumHand.MAIN_HAND,new ItemStack(com.Hileb.moremomostories.item.ModItems.ITEM_DAO));
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
                //煮饭婆举高高
                if (event.getSource().getTrueSource()!=null && event.getSource().getTrueSource() instanceof  EntityPlayer){
                    EntityPlayer player=(EntityPlayer) event.getSource().getTrueSource();
                    if (player.getHeldItemMainhand().getItem() == ModItems.REPLICA_1){
                        ModAdvancementsInit.giveAdvancement(player, Advancementkeys.AD_ZFPHIGH);
                        player.sendMessage(new TextComponentString(I18n.format("say.zfp.11.say")));
                    }
                }
                //举高值
                if (event.getSource() == DamageSource.FALL){
                    event.setCanceled(true);
                    this.setValueOfZFP(this.getValueOfZFP()+(int) event.getAmount());
                }
            }
        }
    }
    public int getValueOfZFP(){
        return IDLNBTUtil.GetInt(this,NBT_VALUE_OF_ZFP,0);
    }
    public void setValueOfZFP(int amount){
        IDLNBTUtil.SetInt(this,NBT_VALUE_OF_ZFP,amount);
    }
    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event){
        World world=event.player.world;
        if(!world.isRemote){
            if (this.getValueOfZFP()>=50){
                ModCommands.give(event.player,new ItemStack(com.Hileb.moremomostories.item.ModItems.ITEM_DUCK_COOKED));
            }
        }
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (!world.isRemote){
            AxisAlignedBB aabb=new AxisAlignedBB(new BlockPos(posX-3,posY-3,posZ-3),new BlockPos(posX+3,posY+3,posZ+3));
            if (world.getEntitiesWithinAABB(EntityPlayer.class,aabb).size()!=0 && !isNearPlayer){
                //ItemDao.setClosed(this.getHeldItemMainhand(),false);
                tellStories(world.getEntitiesWithinAABB(EntityPlayer.class,aabb));
                //do something
            }
            if (world.getEntitiesWithinAABB(EntityPlayer.class,aabb).size()==0){
                ItemDao.setClosed(this.getHeldItemMainhand(),true);
            }
            if (world.getEntitiesWithinAABB(EntityPlayer.class,aabb).size()!=0){
                isNearPlayer=true;
            }
            else isNearPlayer=false;

        }
    }
    public void tellStories(List<EntityPlayer> players){
        for(int i=0;i<players.size();i++){
            players.get(i).sendMessage(new TextComponentString(I18n.format(String.format("say.zfp.%d.say",new Random().nextInt(19)))));
        }
        ItemDao.setClosed(this.getHeldItemMainhand(),false);
    }
}

