package mods.Hileb.moremomostories.mixin.momostories.mixin.cardMixins;

import mods.Hileb.moremomostories.mixin.momostories.event.CardFunction;
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
        CardFunction.AiLingWishes.erin(event);
    }
}
