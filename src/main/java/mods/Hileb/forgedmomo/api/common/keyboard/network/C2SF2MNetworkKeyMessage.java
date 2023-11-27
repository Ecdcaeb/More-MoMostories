package mods.Hileb.forgedmomo.api.common.keyboard.network;

import io.netty.buffer.ByteBuf;
import mods.Hileb.forgedmomo.api.common.keyboard.F2MKeyBoardManager;
import mods.Hileb.forgedmomo.api.common.keyboard.KeyBindingHolder;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/11/24 19:48
 **/
public class C2SF2MNetworkKeyMessage implements IMessage {
    public KeyBindingHolder holder;
    public boolean isKeyDown;
    public C2SF2MNetworkKeyMessage(){}
    public C2SF2MNetworkKeyMessage(KeyBindingHolder holder,boolean isKeyDownIn){
        this.holder=holder;
        this.isKeyDown=isKeyDownIn;
    }
    @Override
    public void fromBytes(ByteBuf buf) {
        holder=F2MKeyBoardManager.KEY_MAP.get(new ResourceLocation(ByteBufUtils.readUTF8String(buf)));
        isKeyDown=buf.readBoolean();
    }
    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf,holder.name.toString());
        buf.writeBoolean(isKeyDown);
    }
    public static class Handler implements IMessageHandler<C2SF2MNetworkKeyMessage,IMessage>{
        @Override
        public IMessage onMessage(C2SF2MNetworkKeyMessage message, MessageContext ctx) {
            //assume on server
            EntityPlayerMP playerMP= ctx.getServerHandler().player;
            playerMP.getServerWorld().addScheduledTask(
                    ()->{
                        F2MKeyBoardManager.GLOBAL_MANAGER.handleKeyUpdate(playerMP.getUniqueID(),message.holder,message.isKeyDown);
                        if (message.holder.keyBinding.isPressed()){
                            message.holder.pressedHandler.handle(ctx.getServerHandler().player);
                        }
                    }
            );
            return null;
        }
    }
}
