package mods.Hileb.forgedmomo.core.mixin.momostories.extra;

import mods.Hileb.forgedmomo.core.mixin.momostories.CardFunction;
import com.gq2529.momostories.MoMoFramework;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid= MoMoFramework.MODID)
public class EvenrExtraOneType {
    @SubscribeEvent
    public static void One_type(LivingHurtEvent event) {
        CardFunction.OneType.One_type(event);
    }
}
