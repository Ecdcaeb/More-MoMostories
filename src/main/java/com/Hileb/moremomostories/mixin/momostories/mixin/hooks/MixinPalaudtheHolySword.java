package com.Hileb.moremomostories.mixin.momostories.mixin.hooks;

import com.Hileb.moremomostories.mixin.momostories.event.CardHooks;
import com.gq2529.momostories.item.tools.ModTool.PalaudtheHolySword;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(PalaudtheHolySword.class)
public abstract class MixinPalaudtheHolySword extends Item {
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        return CardHooks.PalaudtheHolySword.onItemRightClick(worldIn,playerIn,handIn);
    }
    @SubscribeEvent
    @Overwrite
    public static void palaud_the_holy(LivingHurtEvent event) {
        CardHooks.PalaudtheHolySword.palaud_the_holy(event);
    }
}
