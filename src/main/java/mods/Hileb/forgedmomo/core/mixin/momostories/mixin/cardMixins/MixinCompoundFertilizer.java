package mods.Hileb.forgedmomo.core.mixin.momostories.mixin.cardMixins;

import mods.Hileb.forgedmomo.core.mixin.momostories.CardFunction;
import com.gq2529.momostories.item.tools.ModTool.CompoundFertilizer;
import net.minecraft.item.Item;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(CompoundFertilizer.class)
public abstract class MixinCompoundFertilizer extends Item {

    @Overwrite
    @SubscribeEvent
    public static void onRightUse(PlayerInteractEvent.RightClickBlock event) {
        CardFunction.CompoundFertilizer.onRightUse(event);
    }
}
