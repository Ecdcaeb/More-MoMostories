package com.Hileb.moremomostories.mixin.momostories.mixin.cardMixins;

import com.Hileb.moremomostories.mixin.momostories.event.CardFunction;
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
        CardFunction.TheAngelProject.onJump(event);
    }
    @Overwrite
    @SubscribeEvent
    public static void fist(LivingHurtEvent event) {
        CardFunction.TheAngelProject.fist(event);
    }


    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        CardFunction.TheAngelProject.onUpdate(stack,worldIn,entityIn,itemSlot,isSelected);
    }
}
