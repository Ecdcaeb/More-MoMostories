package com.Hileb.moremomostories.mixin.momostories.mixin.hooks;

import com.Hileb.moremomostories.mixin.momostories.event.CardHooks;
import com.gq2529.momostories.item.ModItemStoryboards.TheSupremeMagicDeepLake;
import net.minecraft.item.Item;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(TheSupremeMagicDeepLake.class)
public abstract class MixinTheSupremeMagicDeepLake extends Item {
    @Overwrite
    @SubscribeEvent
    public static void Deep_lake(LivingHurtEvent event) {
        CardHooks.TheSupremeMagicDeepLake.Deep_lake(event);
    }
}
