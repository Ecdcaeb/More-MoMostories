package com.Hileb.moremomostories.mods.slashblade.specialattack.saClass;

import com.Hileb.moremomostories.common.world.entity.entity.projectile.EntityFire;
import com.Hileb.moremomostories.common.util.EntityUtil;
import mods.flammpfeil.slashblade.specialattack.SpecialAttackBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;

import java.util.Random;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/8/13 11:36
 **/
public class SAFireClass extends SpecialAttackBase {
    @Override
    public String toString() {
        return "momo.sa_fire";
    }

    @Override
    public void doSpacialAttack(ItemStack itemStack, EntityPlayer entityPlayer) {
        //TO DO
        //entityPlayer.sendMessage(new TextComponentString("this is sa speaking"));
        Random random=new Random(entityPlayer.world.getSeed()+entityPlayer.getUniqueID().hashCode()+(int)entityPlayer.posX);
        if (!entityPlayer.world.isRemote){
            //List<EntityRain> rains=new ArrayList<>();
            for(int y=0;y<=5;y++){//生成雨滴
                for(int i=0;i<=9;i++){
                    for(int j=0;j<=9;j++){
                        for(int t=0;t<=3;t++){
                            EntityFire entity=new EntityFire(entityPlayer.world,itemStack,entityPlayer);
                            entity.setPosition(entityPlayer.posX-4+i+((double) (random.nextInt(10)/10)-0.5f),entityPlayer.posY+y+((double) (random.nextInt(10)/10)-0.5f),entityPlayer.posZ-4+j+((double) (random.nextInt(10)/10)-0.5f));
                            entityPlayer.world.spawnEntity(entity);
                        }
                        //rains.add(entity);
                    }
                }
            }
            EntityUtil.ApplyBuff(entityPlayer, MobEffects.FIRE_RESISTANCE,5,20);
            EntityUtil.ApplyBuff(entityPlayer,MobEffects.HEALTH_BOOST,2,20);
            entityPlayer.world.createExplosion(entityPlayer, entityPlayer.posX, entityPlayer.posY,entityPlayer.posZ, 16.0F, false);
        }
    }
}
