package com.Hileb.moremomostories.entity.spawner;

import com.Hileb.moremomostories.entity.entity.living.boss.EntityBossDisdescable;
import com.Hileb.moremomostories.entity.entity.living.boss.EntityGoldenGuideBoss;
import com.Hileb.moremomostories.events.PlayerBossEvent;
import com.Hileb.moremomostories.worldgen.WorldGenHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;

import java.util.Random;

public class RandomSpawn {
    public static void spawnBossDesc(World world, EntityPlayer player){
        EntityLivingBase van=new EntityBossDisdescable(world);
        Event eventPost=new PlayerBossEvent(player,van);
        if (!MinecraftForge.EVENT_BUS.post(eventPost)){
            Random random=new Random(world.getSeed()+player.getUniqueID().hashCode()+world.getTotalWorldTime());
            int toomore=0;
            while(true){
                BlockPos pos=new BlockPos(player.posX,player.posY,player.posZ).add(random.nextInt(12)-6,random.nextInt(12)-6,random.nextInt(12)-6);
                AxisAlignedBB axisAlignedBB=van.getEntityBoundingBox();
                axisAlignedBB.offset(van.posX, van.posY, van.posZ);
                if (WorldGenHelper.isEmptyWithAABB(world,axisAlignedBB)){
                    van.setPosition(pos.getX(),pos.getY(),pos.getZ());
                    world.spawnEntity(van);
                    for(EntityPlayer playerReceive:world.playerEntities){
                        if (playerReceive instanceof EntityPlayerMP){
                            ((EntityPlayerMP)playerReceive).connection.sendPacket(new SPacketChat(new TextComponentTranslation(van.getName())));
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
    public static void spawnGoldGuide(World world, EntityPlayer player){
        EntityLivingBase van=new EntityGoldenGuideBoss(world);
        Event eventPost=new PlayerBossEvent(player,van);
        if (!MinecraftForge.EVENT_BUS.post(eventPost)){
            Random random=new Random(world.getSeed()+player.getUniqueID().hashCode()+world.getTotalWorldTime());
            int toomore=0;
            while(true){
                BlockPos pos=new BlockPos(player.posX,player.posY,player.posZ).add(random.nextInt(12)-6,random.nextInt(12)-6,random.nextInt(12)-6);
                AxisAlignedBB axisAlignedBB=van.getEntityBoundingBox();
                axisAlignedBB.offset(van.posX, van.posY, van.posZ);
                if (WorldGenHelper.isEmptyWithAABB(world,axisAlignedBB)){
                    van.setPosition(pos.getX(),pos.getY(),pos.getZ());
                    world.spawnEntity(van);
                    for(EntityPlayer playerReceive:world.playerEntities){
                        if (playerReceive instanceof EntityPlayerMP){
                            ((EntityPlayerMP)playerReceive).connection.sendPacket(new SPacketChat(new TextComponentTranslation(van.getName())));
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
    public static void spawn(EntityLivingBase van, World world, EntityPlayer player){
        Event eventPost=new PlayerBossEvent(player,van);
        if (!MinecraftForge.EVENT_BUS.post(eventPost)){
            Random random=new Random(world.getSeed()+player.getUniqueID().hashCode()+world.getTotalWorldTime());
            int toomore=0;
            while(true){
                BlockPos pos=new BlockPos(player.posX,player.posY,player.posZ).add(random.nextInt(12)-6,random.nextInt(12)-6,random.nextInt(12)-6);
                AxisAlignedBB axisAlignedBB=van.getEntityBoundingBox();
                axisAlignedBB.offset(van.posX, van.posY, van.posZ);
                if (WorldGenHelper.isEmptyWithAABB(world,axisAlignedBB)){
                    van.setPosition(pos.getX(),pos.getY(),pos.getZ());
                    world.spawnEntity(van);
                    for(EntityPlayer playerReceive:world.playerEntities){
                        if (playerReceive instanceof EntityPlayerMP){
                            ((EntityPlayerMP)playerReceive).connection.sendPacket(new SPacketChat(new TextComponentTranslation(van.getName())));
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
}
