package com.Hileb.moremomostories.mixin.momostories.mixin.hooks;

import com.Hileb.moremomostories.mixin.momostories.event.CardHooks;
import com.gq2529.momostories.item.tools.ModTool.LucyAxe;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(LucyAxe.class)
public abstract class MixinLucyAxe extends Item {
    @Overwrite
    @SubscribeEvent
    public static void onLogin(PlayerEvent.PlayerLoggedInEvent event){
        CardHooks.LucyAxe.onLogin(event);
    }
}
