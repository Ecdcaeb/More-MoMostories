package com.Hileb.moremomostories.mixin.momostories.mixin.cardMixins;

import com.Hileb.moremomostories.mixin.momostories.event.CardFunction;
import com.gq2529.momostories.item.ModItemStoryboards.TheSupremeMagicDeepLake;
import net.minecraft.item.Item;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(TheSupremeMagicDeepLake.class)
public abstract class MixinTheSupremeMagicDeepLake extends Item {

    @Overwrite
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void deep_lake(LivingHurtEvent event) {
        CardFunction.TheSupremeMagicDeepLake.Deep_lake(event);
    }
}
