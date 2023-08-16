package com.Hileb.moremomostories.mods.add_potion;

import com.Hileb.add_potion.event.APCraftEvent;
import com.Hileb.add_potion.util.potion.APotion;
import com.Hileb.add_potion.util.potion.PotionUtil;
import com.Hileb.moremomostories.common.world.advancements.Advancementkeys;
import com.Hileb.moremomostories.common.world.advancements.ModAdvancementsInit;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

@Mod.EventBusSubscriber(modid=com.Hileb.add_potion.IdlFramework.MODID)
public class APMain {

    @Optional.Method(modid= com.Hileb.add_potion.IdlFramework.MODID)
    @SubscribeEvent
    public static void onPlayerAddPotion(APCraftEvent.Pre event){
        if (event.player instanceof EntityPlayerMP){
            ItemStack stack=event.potionStack;
            List<APotion> effects= PotionUtil.getAllEffect(stack);
            for(APotion aPotion:effects){
                if (aPotion.potion== MobEffects.POISON){
                    ModAdvancementsInit.giveAdvancement(event.player, Advancementkeys.AD_OMOD_AP);
                }
            }
        }
    }
}
