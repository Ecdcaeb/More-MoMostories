package mods.Hileb.forgedmomo.api.common.network;

import mods.Hileb.forgedmomo.announces.ModPlugin;
import mods.Hileb.forgedmomo.api.common.keyboard.network.C2SF2MNetworkKeyMessage;
import mods.Hileb.forgedmomo.core.ForgedMoMoFMLLoadingPlugin;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/11/24 19:40
 **/
@ModPlugin(state = LoaderState.INITIALIZATION,method = "init")
public class F2MNetworkHandler {
    public static final SimpleNetworkWrapper INSTANCE= NetworkRegistry.INSTANCE.newSimpleChannel(ForgedMoMoFMLLoadingPlugin.NAME);
    public static void init(){

    }
    static {
        int id=0;
        INSTANCE.registerMessage(C2SF2MNetworkKeyMessage.Handler.class,C2SF2MNetworkKeyMessage.class,id++, Side.SERVER);
    }
}
