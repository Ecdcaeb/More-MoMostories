package com.Hileb.moremomostories.mixin.momostories.mixin;

import com.Hileb.moremomostories.mixin.momostories.event.CardHooks;
import com.gq2529.momostories.item.tools.ModTool.LunaBlessing;
import net.minecraft.item.Item;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(LunaBlessing.class)
public abstract class MixinLunaBlessing extends Item {
    @Overwrite
    @SubscribeEvent
    public void onEntityTargetedEvent(LivingSetAttackTargetEvent event) {
        CardHooks.LunaBlessing.doTwilightCloakCheck(event);
    }

    @Overwrite
    @SubscribeEvent
    public static void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
        CardHooks.LunaBlessing.doTwilightCloakCheck(event);
    }
}
