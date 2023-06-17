package com.Hileb.moremomostories.mixin.momostories.mixin.cardMixins;

import com.Hileb.moremomostories.mixin.momostories.event.CardFunction;
import com.gq2529.momostories.item.ModItemStoryboards.FourWayTrip;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(FourWayTrip.class)
public abstract class MixinFourWayTrip extends Item {

    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        CardFunction.FourWayTrip.onUpdate(stack,worldIn,entityIn,itemSlot,isSelected);
    }
}
