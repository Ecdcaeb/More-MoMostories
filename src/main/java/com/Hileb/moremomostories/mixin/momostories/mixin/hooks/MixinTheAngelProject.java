package com.Hileb.moremomostories.mixin.momostories.mixin.hooks;

import com.Hileb.moremomostories.mixin.momostories.event.CardHooks;
import com.gq2529.momostories.item.ModItemStoryboards.TheAngelProject;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(TheAngelProject.class)
public abstract class MixinTheAngelProject extends Item {
    @Overwrite
    @SubscribeEvent
    public static void onJump(LivingEvent.LivingJumpEvent event) {
        CardHooks.TheAngelProject.onJump(event);
    }
    @Overwrite
    @SubscribeEvent
    public static void fist(LivingHurtEvent event) {
        CardHooks.TheAngelProject.fist(event);
    }

    @Overwrite
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        CardHooks.TheAngelProject.onUpdate(stack,worldIn,entityIn,itemSlot,isSelected);
    }
}
