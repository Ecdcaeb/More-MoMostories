package com.Hileb.moremomostories.mixin.momostories.mixin.hooks;

import com.Hileb.moremomostories.mixin.momostories.event.CardHooks;
import com.gq2529.momostories.item.ModItemStoryboards.LeydenJar;
import net.minecraft.item.Item;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(LeydenJar.class)
public abstract class MixinLeydenJar extends Item {
    @Overwrite
    @SubscribeEvent
    public static void luna_hunting(LivingHurtEvent event) {
        CardHooks.LeydenJar.luna_hunting(event);
    }
}
