package mods.Hileb.moremomostories.common.world.entity.entity.living.boss;

import mods.Hileb.moremomostories.common.world.entity.entity.living.EntityGoldenGuide;
import mods.Hileb.moremomostories.common.world.entity.entity.living.boss.skill.BossSkill;
import mods.Hileb.moremomostories.common.world.entity.entity.living.boss.skill.BossSkills;
import mods.Hileb.moremomostories.common.world.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Random;

public class EntityBossDisdescable extends EntityBossBase {
    public EntityBossDisdescable(World world){
        super(world);
        this.setHealth(this.getMaxHealth());
        this.setSize(2.0F, 3.5F);
        this.isImmuneToFire = true;
        ((PathNavigateGround)this.getNavigator()).setCanSwim(true);
        this.experienceValue = 50;
    }
    @Override
    public boolean canDestroyBlock(Block blockIn) {
        return false;
    }

    @Override
    public void initSkill() {
        skills=new ArrayList<>();
        skills.add(BossSkills.BAKIN);
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(500.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.6000000238418579D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(4.0D);
    }
    @Override
    protected void aiTaskInit(){
        //this.tasks.addTask(0, new AIDoNothing());
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackMelee(this,1.0D,false));
        this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
    }
    @Override
    protected void aiTargetTaskInit(){
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityGoldenGuide.class, true));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityLiving.class, true));
        //this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, false, false, NOT_UNDEAD));
    }
    //------------EntityFly---------------//
    @Override
    public void fall(float distance, float damageMultiplier){}
    @Override
    public void addPotionEffect(PotionEffect potioneffectIn){}//不受药水影响
    //------------Boss----------//
    @Override
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (this.isEntityInvulnerable(source))
        {
            return false;
        }
        if (source.getTrueSource() instanceof EntityLivingBase){
            EntityLivingBase living=(EntityLivingBase)source.getTrueSource();
            if (this.getAttackTarget()!=null){
                EntityLivingBase target=this.getAttackTarget();
                if (getS(target.getPosition())<getS(living.getPosition())-4.0D){
                    this.setAttackTarget(living);
                }
            }
        }
        return super.attackEntityFrom(source,amount);
    }
    public double getS(BlockPos pos){
        double x=posX-pos.getX();
        double y=posY-pos.getY();
        double z=posZ-pos.getZ();
        return (double) MathHelper.sqrt((x*x)+(y*y)+(z*z));
    }

    @Override
    public void onLivingUpdate(){
        this.motionY *= 0.6000000238418579D;
        //move ------------//
        if (this.getAttackTarget()==null){
            if (this.posY>this.world.getSeaLevel())this.motionY=-0.6000000238418579D;
            else if (this.posY<this.world.getSeaLevel())this.motionY=+0.6000000238418579D;
            else if(this.posY==this.world.getSeaLevel())this.motionY=0f;
            this.motionX=0.0f;
            this.motionZ=0.0f;
        }
        super.onLivingUpdate();
        if (this.getAttackTarget()!=null){
            EntityLivingBase target=this.getAttackTarget();
            if ((target.posX-this.posX)>0)this.motionX=0.6000000238418579D;
            else if ((target.posX-this.posX)==0)this.motionX=0;
            else if ((target.posX-this.posX)<0)this.motionX=-0.6000000238418579D;

            if ((target.posY-this.posY)>0)this.motionY=0.6000000238418579D;
            else if ((target.posY-this.posY)==0)this.motionY=0;
            else if ((target.posY-this.posY)<0)this.motionY=-0.6000000238418579D;

            if ((target.posZ-this.posZ)>0)this.motionZ=0.6000000238418579D;
            else if ((target.posZ-this.posZ)==0)this.motionZ=0;
            else if ((target.posZ-this.posZ)<0)this.motionZ=-0.6000000238418579D;
        }
        //end move----------------------------//
        //attack@@@@@@@@@@@@@@@@@@@@@@@@@@@@//
        if (!world.isRemote && world.getTotalWorldTime()%15==0){
            if (this.getAttackTarget()!=null){
                if (getS(this.getAttackTarget().getPosition())<=2){
                    attackEntityAsMob(this.getAttackTarget());
                }
            }
        }
        //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@//
        //破坏方块
        //____________________________________//
        if (!world.isRemote){
            int i1 = MathHelper.floor(this.posY);
            int l1 = MathHelper.floor(this.posX);
            int i2 = MathHelper.floor(this.posZ);
            boolean flag = false;
            for (int k2 = -2; k2 <= 2; ++k2)
            {
                for (int l2 = -2; l2 <= 2; ++l2)
                {
                    for (int j = -1; j <= 4; ++j)
                    {
                        int i3 = l1 + k2;
                        int k = i1 + j;
                        int l = i2 + l2;
                        BlockPos blockpos = new BlockPos(i3, k, l);
                        IBlockState iblockstate = this.world.getBlockState(blockpos);
                        Block block = iblockstate.getBlock();

                        if (!block.isAir(iblockstate, this.world, blockpos)  && net.minecraftforge.event.ForgeEventFactory.onEntityDestroyBlock(this, blockpos, iblockstate) && applyBlock(iblockstate))
                        {
                            flag = this.world.destroyBlock(blockpos, true) || flag;
                        }
                    }
                }
            }
            if (flag)
            {
                this.world.playEvent((EntityPlayer)null, 1022, new BlockPos(this), 0);
            }
        }
        //__________________________________________//
        //***********apply skill************************//
        if(!skills.contains(BossSkills.RAIN) && (getHealth()/getMaxHealth()<=0.5f))skills.add(BossSkills.RAIN);
        Random random=new Random(this.hashCode()+world.getBiomeProvider().hashCode()+world.getTotalWorldTime()+world.hashCode()-entityUniqueID.hashCode());
        for(BossSkill skill:skills){
            if (skill.apply(this)){
                skill.doSpacialAttack(this);
            }
        }

    }
    public boolean applyBlock(IBlockState state){
        Block blockIn=state.getBlock();
        return blockIn != Blocks.BEDROCK &&
                blockIn != Blocks.COMMAND_BLOCK &&
                blockIn != Blocks.REPEATING_COMMAND_BLOCK &&
                blockIn != Blocks.CHAIN_COMMAND_BLOCK &&
                blockIn != Blocks.BARRIER &&
                blockIn != Blocks.STRUCTURE_BLOCK &&
                blockIn != Blocks.STRUCTURE_VOID &&
                blockIn != Blocks.PISTON_EXTENSION &&
                blockIn != Blocks.END_GATEWAY &&
                blockIn != net.minecraft.init.Blocks.OBSIDIAN &&
                blockIn != net.minecraft.init.Blocks.IRON_BARS;
    }

    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);
        dropItemWithOffset(ModItems.ITEM_SWOOD_MEMORY_END,1,0).setNoPickupDelay();
        dropItemWithOffset(ModItems.ITEM_TP,1,0).setNoPickupDelay();
    }
}
