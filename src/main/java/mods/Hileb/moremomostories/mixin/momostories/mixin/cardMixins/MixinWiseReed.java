package mods.Hileb.moremomostories.mixin.momostories.mixin.cardMixins;

import mods.Hileb.moremomostories.mixin.momostories.event.CardFunction;
import com.gq2529.momostories.item.ModItemStoryboards.WiseReed;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(WiseReed.class)
public abstract class MixinWiseReed extends Item {
    @Overwrite
    @SubscribeEvent
    public static void reed(LivingHurtEvent event) {
        CardFunction.WiseReed.reed(event);
    }


    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        CardFunction.WiseReed.onUpdate(stack,worldIn,entityIn,itemSlot,isSelected);
    }
}
