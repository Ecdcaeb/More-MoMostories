package com.Hileb.moremomostories.common.world.entity.spawner;

import com.Hileb.moremomostories.MoreMoMoSrories;
import com.Hileb.moremomostories.common.world.blocks.BlockEndBlockShelf;
import com.Hileb.moremomostories.common.world.entity.ModEntityInit;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.Random;

@Mod.EventBusSubscriber(modid = MoreMoMoSrories.MODID)
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
                    MoreMoMoSrories.LogWarning("ap :%d",player.world.getTotalWorldTime());
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
