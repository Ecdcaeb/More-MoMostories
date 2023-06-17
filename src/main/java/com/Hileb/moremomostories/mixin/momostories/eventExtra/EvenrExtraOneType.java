package com.Hileb.moremomostories.mixin.momostories.eventExtra;

import com.Hileb.moremomostories.mixin.momostories.event.CardFunction;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EvenrExtraOneType {
    @SubscribeEvent
    public static void One_type(LivingHurtEvent event) {
        CardFunction.OneType.One_type(event);
    }
}
