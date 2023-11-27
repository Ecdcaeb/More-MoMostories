package mods.Hileb.moremomostories.common.world.entity.entity.living;

import mods.Hileb.forgedmomo.api.common.keyboard.F2MKeyBoardManager;
import mods.Hileb.forgedmomo.utils.math.MathHelper;
import mods.Hileb.moremomostories.common.keybinding.ModKeyBinding;
import mods.Hileb.moremomostories.common.world.level.worldgen.WorldGenHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nullable;

public class EntityBike extends EntityAnimal {
    public EntityBike(World worldIn) {
        super(worldIn);
        this.setHealth(this.getMaxHealth());
        this.setSize(0.7f,1.4f);

        MinecraftForge.EVENT_BUS.register(this);
    }
    protected void initEntityAI(){
    }
    protected void applyEntityAI()
    {
    }


    protected void applyEntityAttributes() {

        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.53000000417232513D);
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
        return this.height;
    }

    @Override
    protected boolean canBeRidden(Entity entityIn) {
        return true;
    }


    public boolean isLockedLook=false;

    public Vec3d forward=new Vec3d(1,0,1);

    @Override
    public void onUpdate() {

        double vectoryV=Math.sqrt((this.motionX*this.motionX)+(this.motionZ*this.motionZ));

        if (vectoryV>0)forward=new Vec3d(motionX/vectoryV,0,motionZ/vectoryV);

        Vec3d preSpeedVec=new Vec3d(motionX,0,motionZ);

        if (isBeingRidden()){

            if (getPassengers().get(0) instanceof EntityPlayerMP && !world.isRemote){
                EntityPlayerMP playerMP=(EntityPlayerMP)getPassengers().get(0);

                this.setHealth(this.getMaxHealth());
                playerMP.setHealth(playerMP.getMaxHealth());




                if (isLockedLook  && Math.abs(MathHelper.getYawAngle(Vec3d.fromPitchYaw(rotationPitch,rotationYaw),Vec3d.fromPitchYaw(playerMP.rotationPitch,playerMP.rotationYaw)))<=(float)Math.PI) {
                    this.rotationYaw = playerMP.rotationYaw;//+ (float) (Math.PI)*3/4 ; //+ (float) (Math.PI / 2f);//-playerMP.rotationYawHead;
                    this.renderYawOffset = this.rotationYaw;
                    this.prevRotationYaw = this.rotationYaw;
//                    this.rotationPitch = playerMP.rotationPitch - (float) (Math.PI / 2f);
//                    this.rotationYawHead=playerMP.rotationYawHead;

                    this.setRotation(this.rotationYaw, this.rotationPitch);
                }



                double primerX=motionX/vectoryV;
                double primerZ=motionZ/vectoryV;

                if(F2MKeyBoardManager.GLOBAL_MANAGER.isKeyDown(playerMP.getUniqueID(), ModKeyBinding.forward) && this.onGround){

                    if (vectoryV!=0){
                        this.motionX+=primerX/20;
                        this.motionZ+=primerZ/20;
                    }
                    else {
                        this.motionX+=playerMP.getLookVec().x/20;
                        this.motionZ+=playerMP.getLookVec().z/20;
                    }
                }
                if(F2MKeyBoardManager.GLOBAL_MANAGER.isKeyDown(playerMP.getUniqueID(), ModKeyBinding.back) && this.onGround){
                    this.motionX=this.motionX/1.17f;
                    this.motionZ=this.motionZ/1.17f;
                }
                if(F2MKeyBoardManager.GLOBAL_MANAGER.isKeyDown(playerMP.getUniqueID(), ModKeyBinding.jump) && this.onGround){
                    if (vectoryV!=0){
                        this.motionY+=0.8*(vectoryV);
                    }
                    else motionY+=0.6;
                }





                double selfLong=3;
                double angle= MathHelper.getYawAngle(preSpeedVec,Vec3d.fromPitchYaw(rotationPitch, rotationYaw));

                double r=(1/ net.minecraft.util.math.MathHelper.sin((float) angle/2f))*(selfLong/2);
                r=Math.abs(r);

                double a=vectoryV*vectoryV/r;

                if (onGround && vectoryV>=0.5 && a!=0  && !Double.isNaN(a) && !Double.isNaN(primerX) && !Double.isNaN(primerZ)){
                    float extraX=(float) (primerZ*a*(angle>=0?1:-1));
                    float extraZ=(float) (primerX*a*(angle>=0?-1:1));

                    this.motionX+=extraX;
                    this.motionZ+=extraZ;
                }
            }
        }


        double angle4=MathHelper.getYawAngle(preSpeedVec,Vec3d.fromPitchYaw(rotationPitch, rotationYaw));


        if (motionX>4)motionX=4;
        if (motionX<-4)motionX=-4;
        if (motionZ>4)motionZ=4;
        if (motionZ<-4)motionZ=-4;
        //move start


        BlockPos preMovePos=this.getPosition();
        super.onUpdate();
        BlockPos postMovePos=this.getPosition();
        float moveAtPreTick= (float) WorldGenHelper.getLong(preMovePos,postMovePos);


        if (!world.isRemote){
            for(EntityLivingBase livingBase:world.getEntitiesWithinAABB(EntityLivingBase.class,new AxisAlignedBB(postMovePos,preMovePos)))
            {
               livingBase.attackEntityFrom(DamageSource.causeMobDamage(this),moveAtPreTick/4f);

            }

        }
        if (onGround){
            motionX=motionX/0.9f/0.98f/0.8f/0.8f;
            motionZ=motionZ/0.9f/0.98f/0.8f/0.8f;
        }
        else {
            motionX=motionX/0.9f/0.98f;
            motionZ=motionZ/0.9f/0.98f;
        }
        //move end

        Vec3d postSpeedVec=new Vec3d(motionX,0,motionZ);
        double angle5=MathHelper.getYawAngle(postSpeedVec,Vec3d.fromPitchYaw(rotationPitch, rotationYaw));
        double angle3= angle4-angle5;




    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        return (!isRiding() && !isBeingRidden())&&player.startRiding(this);
    }

    @Nullable
    @Override
    public EntityAgeable createChild(EntityAgeable ageable) {
        return null;
    }

    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);
        if (this.dead){
            MinecraftForge.EVENT_BUS.unregister(this);
        }
    }
}
