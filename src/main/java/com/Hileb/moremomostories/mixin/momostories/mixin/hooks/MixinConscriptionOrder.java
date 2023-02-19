package com.Hileb.moremomostories.mixin.momostories.mixin.hooks;

import com.Hileb.moremomostories.mixin.momostories.event.CardHooks;
import com.gq2529.momostories.item.ModItemStoryboards.ConscriptionOrder;
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

@Mixin(ConscriptionOrder.class)
public abstract class MixinConscriptionOrder extends Item {
    @Overwrite
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        return CardHooks.ConscriptionOrder.onItemRightClick(worldIn,playerIn,handIn);
    }
    @Overwrite
    @SubscribeEvent
    public static void conscriptionOrder(LivingHurtEvent event)
    {
        CardHooks.ConscriptionOrder.conscriptionOrder(event);
    }
}
