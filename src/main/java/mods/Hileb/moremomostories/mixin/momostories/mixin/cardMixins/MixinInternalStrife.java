package mods.Hileb.moremomostories.mixin.momostories.mixin.cardMixins;

import mods.Hileb.moremomostories.mixin.momostories.event.CardFunction;
import com.gq2529.momostories.item.ModItemStoryboards.InternalStrife;
import net.minecraft.item.Item;
import net.minecraftforge.event.entity.living.LivingEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(InternalStrife.class)
public abstract class MixinInternalStrife extends Item {
    @Overwrite
    public static void doTwilightCloakCheck(LivingEvent event) {
        CardFunction.InternalStrife.doTwilightCloakCheck(event);
    }
}
