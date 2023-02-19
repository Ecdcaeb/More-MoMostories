package com.Hileb.moremomostories.mixin.momostories.mixin.hooks;

import com.Hileb.moremomostories.mixin.momostories.event.CardHooks;
import com.gq2529.momostories.item.ModItemStoryboards.FragmentsOfFalseGods;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(FragmentsOfFalseGods.class)
public abstract class MixinFragmentsOfFalseGods extends Item {
    @Overwrite
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        return CardHooks.FragmentsOfFalseGods.onItemRightClick(worldIn,playerIn,handIn);
    }
}
