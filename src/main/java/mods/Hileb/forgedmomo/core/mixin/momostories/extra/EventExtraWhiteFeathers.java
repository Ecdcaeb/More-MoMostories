package mods.Hileb.forgedmomo.core.mixin.momostories.extra;

import mods.Hileb.forgedmomo.core.mixin.momostories.CardFunction;
import com.gq2529.momostories.MoMoFramework;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid= MoMoFramework.MODID)
public class EventExtraWhiteFeathers {
    @SubscribeEvent
    public static void onJump(LivingEvent.LivingJumpEvent event){
        CardFunction.WhiteFeathers.onJump(event);
    }
}
