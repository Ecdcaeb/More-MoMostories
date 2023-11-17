package com.Hileb.forgedmomo.utils.helper;

import com.Hileb.moremomostories.common.world.item.myItems.ItemCardContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

import java.util.function.Predicate;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/8/29 16:00
 **/
public class Finder {
    @FunctionalInterface
    public interface ConsumerFindPlayerInventory{
        void apply(EntityPlayer player,ItemStack stack);
    }
    public static void findPlayerInventory(EntityPlayer player, Predicate<ItemStack> isOk, ConsumerFindPlayerInventory func){
        for (int i = 0,limit1=player.inventory.getSizeInventory(); i <limit1 ; ++i) {
            ItemStack itemStack = player.inventory.getStackInSlot(i);
            if (isOk.test(itemStack)) {
                func.apply(player,itemStack);
            }
            if (itemStack.getItem()== com.Hileb.moremomostories.common.world.item.ModItems.ITEM_CARD_CONTAINER){
                findInContainerItem(player,itemStack,isOk,func);
            }
        }
    }
    public static void findInContainerItem(EntityPlayer player,ItemStack container,Predicate<ItemStack> isOk, ConsumerFindPlayerInventory func){
        ItemStackHandler stackHandler= ItemCardContainer.getItemStackHandler(container);
        for(int i1=0,limit2=stackHandler.getSlots();i1<limit2;i1++){
            ItemStack stack1=stackHandler.getStackInSlot(i1);
            if (isOk.test(stack1)) {
                func.apply(player,stack1);
            }
        }
        ItemCardContainer.setItemStackHandler(container,stackHandler);
    }
}
