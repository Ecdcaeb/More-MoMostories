package com.Hileb.moremomostories.mixin.momostories.mixin.cardMixins;

import com.Hileb.moremomostories.mixin.momostories.event.CardFunction;
import com.gq2529.momostories.item.ModItemStoryboards.EternalKingship;
import net.minecraft.item.Item;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AnvilRepairEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(EternalKingship.class)
public abstract class MixinEternalKingship extends Item {

    @Overwrite
    @SubscribeEvent
    public static void AnvilRepairEvent(AnvilRepairEvent event) {
        CardFunction.EternalKingship.AnvilRepairEvent(event);
    }
    @Overwrite
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        CardFunction.EternalKingship.hurt(event);
    }
}
