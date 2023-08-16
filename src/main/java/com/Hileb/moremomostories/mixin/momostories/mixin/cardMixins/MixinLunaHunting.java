package com.Hileb.moremomostories.mixin.momostories.mixin.cardMixins;

import com.Hileb.moremomostories.mixin.momostories.event.CardFunction;
import com.gq2529.momostories.item.bows.LunaHunting;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/8/11 9:46
 **/
@Mixin(LunaHunting.class)
public class MixinLunaHunting{
    @Overwrite
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
        CardFunction.LunaHunting.onPlayerStoppedUsing((LunaHunting)(Object)this,stack,worldIn,entityLiving,timeLeft);
    }
    @SubscribeEvent
    @Overwrite
    public static void luna_hunting(LivingHurtEvent event){
        CardFunction.LunaHunting.luna_hunting(event);
    }
}
