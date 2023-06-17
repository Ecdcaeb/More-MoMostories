package com.Hileb.moremomostories.entity.spawner;

import com.Hileb.moremomostories.IdlFramework;
import com.Hileb.moremomostories.blocks.BlockEndBlockShelf;
import com.Hileb.moremomostories.entity.ModEntityInit;
import com.Hileb.moremomostories.entity.entity.living.EntityGoldenGuide;
import com.Hileb.moremomostories.entity.entity.living.boss.EntityGoldenGuideBoss;
import com.Hileb.moremomostories.init.ModConfig;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.Random;

@Mod.EventBusSubscriber(modid = IdlFramework.MODID)
public class Spawner {
    @SubscribeEvent
    public static void worldTick(TickEvent.PlayerTickEvent event){
//        spawnGuide(event);
//        spawnGuideBoss(event);
//        spawnDescBoss(event);
    }
    public static void spawnGuide(TickEvent.PlayerTickEvent event){
        EntityPlayer player=event.player;
        if (!player.world.isRemote){
            if (player.world.provider.getDimension()== 0){
                if (new Random().nextInt(1200)<=2){
                    IdlFramework.LogWarning("ap :%d",player.world.getTotalWorldTime());
                    BlockEndBlockShelf.spawnVan(player.world, player.getPosition());
                }
            }
        }
    }
    public static void spawnGuideBoss(TickEvent.PlayerTickEvent event){
        EntityPlayer player=event.player;
        if (!player.world.isRemote){
            WorldServer server=(WorldServer) event.player.world;
            if (server.provider.getDimension()==0){
                EntityList.EntityEggInfo entityEggInfo1= ForgeRegistries.ENTITIES.getValue(ModEntityInit.GUIDE).getEgg();
                EntityList.EntityEggInfo entityEggInfo2= ForgeRegistries.ENTITIES.getValue(ModEntityInit.GUIDE_BOSS).getEgg();
                int guideCount=((EntityPlayerMP)player).getStatFile().readStat(entityEggInfo1.killEntityStat);
                int bossCount=((EntityPlayerMP)player).getStatFile().readStat(entityEggInfo2.killEntityStat);
                if (guideCount>=10){
                    if (bossCount==0)RandomSpawn.spawnGoldGuide(player.world, player);
                    else {
                        if (player.world.rand.nextInt(50000)<=1){
                            RandomSpawn.spawnGoldGuide(player.world,player);
                        }
                    }
                }
            }
        }
    }
    public static void spawnDescBoss(TickEvent.PlayerTickEvent event){
        EntityPlayer player=event.player;
        if (!player.world.isRemote){
            WorldServer server=(WorldServer) event.player.world;
            if (server.provider.getDimension()==0){
                EntityList.EntityEggInfo entityEggInfo1= ForgeRegistries.ENTITIES.getValue(ModEntityInit.BOSS_DESC).getEgg();
                EntityList.EntityEggInfo entityEggInfo2= ForgeRegistries.ENTITIES.getValue(ModEntityInit.GUIDE_BOSS).getEgg();
                int descCount=((EntityPlayerMP)player).getStatFile().readStat(entityEggInfo1.killEntityStat);
                int guideCount=((EntityPlayerMP)player).getStatFile().readStat(entityEggInfo2.killEntityStat);
                if (guideCount>=1){
                    if (descCount==0)RandomSpawn.spawnGoldGuide(player.world, player);
                    else {
                        if (player.world.rand.nextInt(50000)<=1){
                            RandomSpawn.spawnGoldGuide(player.world,player);
                        }
                    }
                }
            }
        }
    }
}
