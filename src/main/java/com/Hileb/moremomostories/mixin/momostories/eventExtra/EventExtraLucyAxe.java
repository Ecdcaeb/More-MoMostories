package com.Hileb.moremomostories.mixin.momostories.eventExtra;

import com.Hileb.moremomostories.mixin.momostories.event.CardFunction;
import com.gq2529.momostories.MoMoFramework;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
@Mod.EventBusSubscriber(modid= MoMoFramework.MODID)
public class EventExtraLucyAxe {
    @SubscribeEvent
    public static void onLogin(PlayerEvent.PlayerLoggedInEvent event){
        CardFunction.LucyAxe.onLogin(event);
    }
}
