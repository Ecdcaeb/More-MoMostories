package com.Hileb.moremomostories.mixin.momostories.mixin;

import com.Hileb.moremomostories.mixin.momostories.event.CardHooks;
import com.gq2529.momostories.item.tools.Bleeding.OneType;
import net.minecraft.item.Item;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(OneType.class)
public abstract class MixinOneType extends Item {
    @Overwrite
    public static void One_type(LivingHurtEvent event) {
        CardHooks.OneType.One_type(event);
    }
}
