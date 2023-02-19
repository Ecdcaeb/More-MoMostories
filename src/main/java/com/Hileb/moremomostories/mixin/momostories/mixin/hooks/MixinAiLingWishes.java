package com.Hileb.moremomostories.mixin.momostories.mixin.hooks;

import com.Hileb.moremomostories.IdlFramework;
import com.Hileb.moremomostories.mixin.momostories.event.CardHooks;
import com.gq2529.momostories.item.ModItemStoryboards.AiLingWishes;
import net.minecraft.item.Item;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(AiLingWishes.class)
public abstract class MixinAiLingWishes extends Item {
    @Overwrite
    @SubscribeEvent
    public static void erin(LivingDeathEvent event) {
        CardHooks.AiLingWishes.erin(event);
    }
}
