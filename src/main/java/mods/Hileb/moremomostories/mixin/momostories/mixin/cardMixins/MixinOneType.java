package mods.Hileb.moremomostories.mixin.momostories.mixin.cardMixins;

import com.gq2529.momostories.item.tools.Bleeding.OneType;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(OneType.class)
public abstract class MixinOneType extends Item {
//    @Overwrite
//    @SubscribeEvent
//    public static void One_type(LivingHurtEvent event) {
//        CardFunction.OneType.One_type(event);
//    }
}
