package com.Hileb.moremomostories.entity;

import com.Hileb.moremomostories.Advancements.Advancementkeys;
import com.Hileb.moremomostories.Advancements.ModAdvancementsInit;
import com.Hileb.moremomostories.util.NBTStrDef.IDLNBTUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

public class EntityDeathMM extends EntityZFP {
    long time;
    public EntityDeathMM(World worldIn) {
        super(worldIn);
        this.setSize(1.5F, 0.8F);
        time=worldIn.getTotalWorldTime();
    }
    @Override
    public EnumActionResult applyPlayerInteraction(EntityPlayer player, Vec3d vec, EnumHand hand) {
        if (super.applyPlayerInteraction(player,vec,hand)==EnumActionResult.SUCCESS)return EnumActionResult.SUCCESS;
        else {
            if (player.getHeldItemMainhand().getItem()== Items.TOTEM_OF_UNDYING){
                ModAdvancementsInit.giveAdvancement(player, Advancementkeys.AD_DREAM_1);
                this.setDead();
                return EnumActionResult.SUCCESS;
            }
            return EnumActionResult.FAIL;
        }
    }

    @Override
    protected void initEntityAI() {
    }

    @Override
    protected void applyEntityAI() {
    }

    @SubscribeEvent
    public void onDeath(LivingDeathEvent event){
        World world=event.getEntity().world;
        if(!world.isRemote){
            if (event.getEntityLiving() instanceof EntityDeathMM){
                AxisAlignedBB aabb=new AxisAlignedBB(new BlockPos(this.posX-32,this.posY-32,this.posZ-32),new BlockPos(this.posX+32,this.posY+32,this.posZ+32));
                List<EntityPlayer> players=world.getEntitiesWithinAABB(EntityPlayer.class,aabb);
                for (int i=0;i<players.size();i++){
                    players.get(i).sendMessage(new TextComponentTranslation("com.hileb.moremomostories.say.1.say",players.get(i).getName()));
                    ModAdvancementsInit.giveAdvancement(players.get(i), Advancementkeys.AD_AGAIN_1);
                }
            }
        }
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (world.getTotalWorldTime()-time>=100){
            LivingDeathEvent event=new LivingDeathEvent(this, DamageSource.OUT_OF_WORLD);
            onDeath(event);
            this.setDead();
        }
    }
}
