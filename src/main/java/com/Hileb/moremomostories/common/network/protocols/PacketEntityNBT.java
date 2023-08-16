package com.Hileb.moremomostories.common.network.protocols;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketEntityNBT implements IMessage {
    public static final String TAG="hilebTag";
    public int entityId;
    public NBTTagCompound nbtTagCompound;

    //JsonToNBT.getTagFromJson(s)
    @Override
    public void fromBytes(ByteBuf buf) {
        entityId=buf.readInt();
        nbtTagCompound= ByteBufUtils.readTag(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(entityId);
        ByteBufUtils.writeTag(buf,nbtTagCompound);
    }

    public static class Handler implements IMessageHandler<PacketEntityNBT, IMessage> {
        @Override
        public IMessage onMessage(PacketEntityNBT message, MessageContext ctx) {
            World world=Minecraft.getMinecraft().world;
            if (Minecraft.getMinecraft().world!=null){
                Entity entity=world.getEntityByID(message.entityId);
                if (entity!=null){
                    entity.getEntityData().setTag(TAG,message.nbtTagCompound);
                }
            }
            return null;
        }
    }
}
