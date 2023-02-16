package com.Hileb.moremomostories.mixin.momostories.mixin;

import com.Hileb.moremomostories.mixin.momostories.event.CardHooks;
import com.gq2529.momostories.item.tools.ModTool.BlueCalidan;
import net.minecraft.item.Item;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(BlueCalidan.class)
public abstract class MixinBlueCalidan extends Item {
    @Overwrite
    @SubscribeEvent
    public static void luna_hunting(LivingHurtEvent event) {
        CardHooks.BlueCalidan.luna_hunting(event);
    }
}
