package com.Hileb.moremomostories.mods.slashblade;


import com.Hileb.moremomostories.mods.slashblade.blade.BladeType;
import com.Hileb.moremomostories.mods.slashblade.blade.ModBlades;
import com.Hileb.moremomostories.mods.slashblade.specialattack.ModSpecialAttack;
import com.Hileb.moremomostories.mods.slashblade.specialattack.SpecialAttackType;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.event.LoadEvent;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.FMLThrowingEventBus;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;


public class SlashBladeUtil {
    @SubscribeEvent
    public void onLoadInit(LoadEvent.InitEvent event){
        registerSA();
        registerBlade();
        registerRecipe(ForgeRegistries.RECIPES);
    }
    @SubscribeEvent
    public void onLoadPost(LoadEvent.PostInitEvent event){
    }

    public static void registerBlade(){
        for(BladeType bladeType: ModBlades.REGISTER){
            bladeType.registerStack();
        }
    }
    public static void registerSA(){
        for(SpecialAttackType sa: ModSpecialAttack.REGISTER){
            ItemSlashBlade.specialAttacks.put(sa.getID(), sa.getSpecialAttack());
        }
    }
    public static void registerRecipe(IForgeRegistry<IRecipe> r){
        for(BladeType bladeType: ModBlades.REGISTER){
            IRecipe recipe=bladeType.getRecipe();
            if (recipe!=null)r.register(recipe);
        }
    }
}
