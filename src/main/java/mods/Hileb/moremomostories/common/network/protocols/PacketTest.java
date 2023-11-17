package mods.Hileb.moremomostories.common.network.protocols;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;


public class PacketTest implements IMessage {

    int testVal;

    public PacketTest() {
    }


    public PacketTest(int testVal) {
        this.testVal = testVal;
    }

    @Override
    public void fromBytes(ByteBuf buf) {

        testVal = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(testVal);
        // these methods may also be of use for your code:
        // for Itemstacks - ByteBufUtils.writeItemStack()
        // for NBT tags ByteBufUtils.writeTag();
        // for Strings: ByteBufUtils.writeUTF8String();

    }

    public static class Handler implements IMessageHandler<PacketTest, IMessage> {
        public IMessage onMessage(final PacketTest msg, final MessageContext ctx) {
            return null;
        }
    }
}
