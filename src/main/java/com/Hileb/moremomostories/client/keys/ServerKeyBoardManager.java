package com.Hileb.moremomostories.client.keys;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ServerKeyBoardManager {
    public static class Client{
        public static class PackKeyReturn implements IMessage{
            public String uuid;
            public String key;
            public boolean isDown;

            @Override
            public void fromBytes(ByteBuf buf) {
                uuid=ByteBufUtils.readUTF8String(buf);
                key=ByteBufUtils.readUTF8String(buf);
                isDown=buf.readBoolean();
            }

            @Override
            public void toBytes(ByteBuf buf) {
                ByteBufUtils.writeUTF8String(buf,uuid);
                ByteBufUtils.writeUTF8String(buf,key);
                buf.writeBoolean(isDown);
            }
        }

    }
    public static class Server{
        public static class PlayerKeyDownEvent extends Event {
            public final String key;
            public final boolean isDown;
            public final String uuid;
            public PlayerKeyDownEvent(String uuidIn,String keyIn,boolean isDownIn){
                super();
                key=keyIn;
                isDown=isDownIn;
                uuid=uuidIn;
            }

            @Override
            public boolean isCancelable() {
                return false;
            }
        }
        public static class Handler implements IMessageHandler<Client.PackKeyReturn, IMessage> {
            @Override
            public IMessage onMessage(Client.PackKeyReturn message, MessageContext ctx) {
                PlayerKeyDownEvent event=new PlayerKeyDownEvent(message.uuid,message.key,message.isDown);
                if (event.uuid!=null && event.key!=null)MinecraftForge.EVENT_BUS.post(event);
                return null;
            }
        }

        public static class keyBoard{
            public static final String keyBindForward = "key.forward";
            public static final String keyBindLeft = "key.left";
            public static final String keyBindBack = "key.back";
            public static final String keyBindRight = "key.right";
            public static final String keyBindJump = "key.jump";
        }
    }
}
