package com.Hileb.moremomostories.mixin.momostories.mixin.hooks;

import com.Hileb.moremomostories.mixin.momostories.event.CardHooks;
import com.gq2529.momostories.item.ModItemStoryboards.DevilsBlood;
import net.minecraft.item.Item;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(DevilsBlood.class)
public abstract class MixinDevilsBlood extends Item {
    @Overwrite
    @SubscribeEvent
    public static void devilsblood(LivingHurtEvent event) {
        CardHooks.DevilsBlood.devilsblood(event);
    }
}
