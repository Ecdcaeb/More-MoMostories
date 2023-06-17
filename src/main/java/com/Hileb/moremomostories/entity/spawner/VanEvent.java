package com.Hileb.moremomostories.entity.spawner;

import com.Hileb.moremomostories.advancements.Advancementkeys;
import com.Hileb.moremomostories.advancements.ModAdvancementsInit;
import com.Hileb.moremomostories.IdlFramework;
import com.Hileb.moremomostories.entity.entity.living.EntityVan;
import com.Hileb.moremomostories.events.PlayerBossEvent;
import com.Hileb.moremomostories.worldgen.WorldGenHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

@Mod.EventBusSubscriber(modid = IdlFramework.MODID)
public class VanEvent {
    @SubscribeEvent
    public static void playerSleep(PlayerSleepInBedEvent event){
        World world=event.getEntityPlayer().world;
        if (!world.isRemote){
            if (!ModAdvancementsInit.hasAdvancement(event.getEntityPlayer(), Advancementkeys.AD_MEETVAN)){
                if (event.getEntityPlayer().getTotalArmorValue()>=15 && new Random((long) event.hashCode()).nextInt(6)<=1){
                    spawnVan(world,event.getEntityPlayer());
                }
            }
        }
    }
    public static void spawnVan(World world, EntityPlayer player){
        EntityLivingBase van=new EntityVan(world);
        Event eventPost=new PlayerBossEvent(player,van);
        if (!MinecraftForge.EVENT_BUS.post(eventPost)){
            Random random=new Random(world.getSeed()+player.getUniqueID().hashCode()+world.getTotalWorldTime());
            int toomore=0;
            while(true){
                BlockPos pos=new BlockPos(player.posX,player.posY,player.posZ).add(random.nextInt(12)-6,random.nextInt(12)-6,random.nextInt(12)-6);
                AxisAlignedBB axisAlignedBB=new AxisAlignedBB(pos,pos.add(0,1,0));
                if (WorldGenHelper.isEmptyWithAABB(world,axisAlignedBB)){
                    van.setPosition(pos.getX(),pos.getY(),pos.getZ());
                    world.spawnEntity(van);
                    ModAdvancementsInit.giveAdvancement(player,Advancementkeys.AD_MEETVAN);
                    //List<EntityPlayer> playersReceivers=world.playerEntities;
                    for(EntityPlayer playerReceive:world.playerEntities){
                        if (playerReceive instanceof EntityPlayerMP){
                            ((EntityPlayerMP)playerReceive).connection.sendPacket(new SPacketChat(new TextComponentString(I18n.format("boss.van.spawn"))));
                        }
                    }
                    break;
                }
                else if (toomore>=100){
                    break;
                }
            }
        }
        else van.setDead();
    }
//    @SubscribeEvent
//    public static void vanEvent(PlayerBossEvent event){
//        World world=event.boss.world;
//        if (!world.isRemote){
//            if(event.boss instanceof EntityVan){
//                if (event.isTrue){
//                    PlayerSleepInBedEvent event1=new PlayerSleepInBedEvent(event.getEntityPlayer(),event.getEntityPlayer().getPosition());
//                    playerSleep(event1);
//                }
//            }
//        }
//    }
}
