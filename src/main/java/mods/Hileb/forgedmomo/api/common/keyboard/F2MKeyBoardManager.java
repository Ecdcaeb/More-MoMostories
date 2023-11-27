package mods.Hileb.forgedmomo.api.common.keyboard;

import mods.Hileb.forgedmomo.announces.ModPlugin;
import mods.Hileb.forgedmomo.api.common.keyboard.network.C2SF2MNetworkKeyMessage;
import mods.Hileb.forgedmomo.api.common.network.F2MNetworkHandler;
import mods.Hileb.forgedmomo.core.ForgedMoMoFMLLoadingPlugin;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/11/24 19:16
 **/
@ModPlugin(state = LoaderState.INITIALIZATION)
public class F2MKeyBoardManager {
    public static final HashMap<ResourceLocation, KeyBindingHolder> KEY_MAP=new HashMap<>();
    static {
        MinecraftForge.EVENT_BUS.post(new F2MKeyRegistryEvent(FMLCommonHandler.instance().getSide()));
    }
    public static final PlayerKeyState.Manager.GlobalManager GLOBAL_MANAGER= PlayerKeyState.Manager.GlobalManager.INSTANCE;
    @SideOnly(Side.CLIENT)
    public static PlayerKeyState.Manager client_current_manager;

    @SideOnly(Side.CLIENT)
    @Mod.EventBusSubscriber(modid=ForgedMoMoFMLLoadingPlugin.NAME,value = Side.CLIENT)
    public static class ClientHandler{
        @SideOnly(Side.CLIENT)
        @SubscribeEvent
        public static void onKeyUpdate(InputEvent.KeyInputEvent event){
            if (client_current_manager!=null){
                for (PlayerKeyState state:client_current_manager.KEY_STATES.values()){
                    if (state.isKeyDown!=state.holder.keyBinding.isKeyDown()){
                        client_current_manager.markKey(state.holder,state.holder.keyBinding.isKeyDown());
                        F2MNetworkHandler.INSTANCE.sendToServer(new C2SF2MNetworkKeyMessage(state.holder,state.isKeyDown));
                    }
                }
            }
        }
    }
}
