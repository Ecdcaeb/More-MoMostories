package com.Hileb.moremomostories.common.world.recipe;

import com.Hileb.moremomostories.common.world.advancements.ModAdvancementsInit;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/8/13 12:09
 **/
public class AdvancementIRecipe<T extends IRecipe>{
    public T recipe;
    public String advancement;
    public AdvancementIRecipe(T recipeIn){
        recipe=recipeIn;
        MinecraftForge.EVENT_BUS.register(this);
    }
    public AdvancementIRecipe<T> setAdvancement(String s){
        advancement=s;
        return this;
    }
    public AdvancementIRecipe<T> register(IForgeRegistry<IRecipe> r){
        r.register(recipe);
        return this;
    }
    @SubscribeEvent
    public void onCraft(PlayerEvent.ItemCraftedEvent event){
        try {
            if (!event.player.world.isRemote) {
                InventoryCrafting inv = (InventoryCrafting)event.craftMatrix;
                if (recipe.matches(inv,event.player.world)){
                    ModAdvancementsInit.giveAdvancement(event.player, advancement);
                }
            }
        }catch (Exception ignored){

        }
    }

}
