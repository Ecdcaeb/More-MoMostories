package mods.Hileb.forgedmomo.eventhandler;

import mods.Hileb.forgedmomo.api.common.keyboard.F2MKeyBoardManager;
import mods.Hileb.forgedmomo.api.common.keyboard.PlayerKeyState;
import mods.Hileb.forgedmomo.core.ForgedMoMoFMLLoadingPlugin;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.relauncher.Side;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/11/25 13:10
 **/
@Mod.EventBusSubscriber(modid = ForgedMoMoFMLLoadingPlugin.NAME)
public class F2MCommonEventHandler {
    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event){
        PlayerKeyState.Manager.GlobalManager.INSTANCE.onPlayerLogin(event);
        if (FMLCommonHandler.instance().getSide()== Side.CLIENT){
            F2MKeyBoardManager.client_current_manager=F2MKeyBoardManager.GLOBAL_MANAGER.UUID_2_MANAGER.get(Minecraft.getMinecraft().player.getUniqueID());
        }
    }
    @SubscribeEvent
    public static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event){
        PlayerKeyState.Manager.GlobalManager.INSTANCE.onPlayerLogout(event);
        if (FMLCommonHandler.instance().getSide()== Side.CLIENT){
            F2MKeyBoardManager.client_current_manager=null;
        }
    }
}
