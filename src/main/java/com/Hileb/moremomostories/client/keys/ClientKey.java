package com.Hileb.moremomostories.client.keys;

import com.Hileb.moremomostories.MoreMoMoSrories;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid= MoreMoMoSrories.MODID)
public class ClientKey {
    public static List<String> KEY_REGISTER=new ArrayList<>();
    public static void init(){
        KEY_REGISTER.add(ServerKeyBoardManager.Server.keyBoard.keyBindForward);
        KEY_REGISTER.add(ServerKeyBoardManager.Server.keyBoard.keyBindBack);
        KEY_REGISTER.add(ServerKeyBoardManager.Server.keyBoard.keyBindLeft);
        KEY_REGISTER.add(ServerKeyBoardManager.Server.keyBoard.keyBindRight);
        KEY_REGISTER.add(ServerKeyBoardManager.Server.keyBoard.keyBindJump);
    }
    @SubscribeEvent
    public static void onKey(InputEvent.KeyInputEvent event){

//        try {
//            if (KEY_REGISTER != null) {
//                for (String keyName : KEY_REGISTER) {
//                    if (keyName != null) {
//                        for (KeyBinding key : Minecraft.getMinecraft().gameSettings.keyBindings) {
//                            if (key.getKeyDescription().equals(keyName)) {
//                                ServerKeyBoardManager.Client.PackKeyReturn packKeyReturn = new ServerKeyBoardManager.Client.PackKeyReturn();
//                                packKeyReturn.isDown = key.isPressed();
//                                packKeyReturn.key = key.getKeyDescription();
//                                packKeyReturn.uuid = Minecraft.getMinecraft().player.getUniqueID().toString();
//                                NetworkHandler.sendToServer(packKeyReturn);
//                            }
//                        }
//                    }
//                }
//            }
//        }catch (Exception ignored){
//
//        }
    }
}
