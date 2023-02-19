package com.Hileb.moremomostories.mixin.momostories.mixin.hooks;

import com.Hileb.moremomostories.mixin.momostories.event.CardHooks;
import com.gq2529.momostories.MoMoFramework;
import com.gq2529.momostories.item.tools.ModTool.WhiteFeathers;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(WhiteFeathers.class)
@Mod.EventBusSubscriber(modid = MoMoFramework.MODID)
public abstract class MixinWhiteFeathers extends Item {
    @Overwrite
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
    }
    @SubscribeEvent
    public static void onJump(LivingEvent.LivingJumpEvent event){
        CardHooks.WhiteFeathers.onJump(event);
    }

}
