package com.Hileb.moremomostories.mixin.momostories.mixin.hooks;

import com.Hileb.moremomostories.mixin.momostories.event.CardHooks;
import com.gq2529.momostories.item.ModItemStoryboards.OhamHeavyCavalryRegiment;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(OhamHeavyCavalryRegiment.class)
public class MixinOhamHeavyCavalryRegiment extends Item {
    @Overwrite
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        CardHooks.OhamHeavyCavalryRegiment.onUpdate(stack,worldIn,entityIn,itemSlot,isSelected);
    }
}
