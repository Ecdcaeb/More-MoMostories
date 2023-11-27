package mods.Hileb.forgedmomo.core.mixin.momostories.mixin.cardMixins;

import mods.Hileb.forgedmomo.core.mixin.momostories.CardFunction;
import com.gq2529.momostories.item.tools.ModTool.BlueCalidan;
import net.minecraft.item.Item;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(BlueCalidan.class)
public abstract class MixinBlueCalidan extends Item {

    @SubscribeEvent @Overwrite
    public static void luna_hunting(LivingHurtEvent event) {
        CardFunction.BlueCalidan.luna_hunting(event);
    }
}
