package com.Hileb.moremomostories.mixin.momostories.mixin.hooks;

import com.Hileb.moremomostories.mixin.momostories.event.CardHooks;
import com.gq2529.momostories.item.tools.ModTool.TheLampofTiamat_1;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin( TheLampofTiamat_1.class)
public abstract class MixinTheLampofTiamat_1 extends Item {
    @Overwrite
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        CardHooks.TheLampofTiamat_1.onUpdate(stack,worldIn,entityIn,itemSlot,isSelected);
    }
}
