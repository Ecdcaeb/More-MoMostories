package com.Hileb.moremomostories.otherMods.SlashBlade.SA;

import com.Hileb.moremomostories.init.ModConfig;
import com.Hileb.moremomostories.otherMods.SlashBlade.SA.Entity.EntityFire;
import com.Hileb.moremomostories.otherMods.SlashBlade.SA.Entity.EntityRain;
import com.Hileb.moremomostories.util.EntityUtil;
import mods.flammpfeil.slashblade.specialattack.SpecialAttackBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ModSA {
    public static List<SpecialAttack> REGISTER = new ArrayList<>();
    public static final SpecialAttackBase sa1=new SpecialAttack() {
        @Override
        public String toString() {
            return "sa1";
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
                            EntityRain entity=new EntityRain(entityPlayer.world,itemStack,entityPlayer);
                            entity.setPosition(entityPlayer.posX-4+i+((double) (random.nextInt(10)/10)-0.5f),entityPlayer.posY+y+((double) (random.nextInt(10)/10)-0.5f),entityPlayer.posZ-4+j+((double) (random.nextInt(10)/10)-0.5f));
                            entityPlayer.world.spawnEntity(entity);
                            //rains.add(entity);
                        }
                    }
                }
            }
        }

        @Override
        public int getID() {
            return ModConfig.SlashBlade.SA_RAIN;
        }
    };
    public static final SpecialAttackBase SA_FIRE=new SpecialAttack() {
        @Override
        public String toString() {
            return "sa_fire";
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
                EntityUtil.ApplyBuff(entityPlayer,MobEffects.FIRE_RESISTANCE,5,20);
                EntityUtil.ApplyBuff(entityPlayer,MobEffects.HEALTH_BOOST,2,20);
                entityPlayer.world.createExplosion(entityPlayer, entityPlayer.posX, entityPlayer.posY,entityPlayer.posZ, 16.0F, false);
            }
        }
        @Override
        public int getID() {
            return ModConfig.SlashBlade.SA_FIRE;
        }
    };
    public final static SpecialAttackBase saUpAndDownWorld=new SA_UpAndDownWorld();
    public final static SpecialAttackBase saBakin=new SABakin();

    public final static SpecialAttackBase saBossBakin=new SABossBakin();
}
