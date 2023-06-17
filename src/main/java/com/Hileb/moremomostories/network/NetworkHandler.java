package com.Hileb.moremomostories.network;

import com.Hileb.moremomostories.IdlFramework;
import com.Hileb.moremomostories.keys.ServerKeyBoardManager;
import com.Hileb.moremomostories.network.protocols.PacketEntityNBT;
import com.Hileb.moremomostories.network.protocols.PacketTest;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkHandler {
    public static final ResourceLocation MSG_RESOURCE = new ResourceLocation(IdlFramework.MODID, "msg");

    public static final SimpleNetworkWrapper channel = NetworkRegistry.INSTANCE.newSimpleChannel(IdlFramework.MODID);

    static int id = 0;
    public static void init()
    {
        //C2S
        channel.registerMessage(PacketTest.Handler.class, PacketTest.class, id++, Side.SERVER);


        channel.registerMessage(ServerKeyBoardManager.Server.Handler.class,ServerKeyBoardManager.Client.PackKeyReturn.class, id++, Side.SERVER);

        //just call SendToServer
        channel.registerMessage(PacketEntityNBT.Handler.class,PacketEntityNBT.class, id++, Side.CLIENT);

        //S2C
        //PacketUtil.network.sendTo(new PacketRevenge(cap.isRevengeActive()), (EntityPlayerMP)e.player);
    }

    public static void sendToServer(IMessage packet)
    {
        channel.sendToServer(packet);
    }
    public static void sendToAllPlayer(IMessage packet)
    {
        channel.sendToAll(packet);
    }
    public static void sendToPlayer(EntityPlayerMP entityPlayerMP,IMessage packet)
    {
        channel.sendTo(packet,entityPlayerMP);
    }

    public static void updateEntityData(Entity entity){
        if (entity.getEntityData().hasKey(PacketEntityNBT.TAG)){
            NBTTagCompound tagCompound=entity.getEntityData().getCompoundTag(PacketEntityNBT.TAG);
            PacketEntityNBT packetEntityNBT=new PacketEntityNBT();
            packetEntityNBT.nbtTagCompound=tagCompound;
            packetEntityNBT.entityId=entity.getEntityId();

            sendToAllPlayer(packetEntityNBT);
        }
    }
}
