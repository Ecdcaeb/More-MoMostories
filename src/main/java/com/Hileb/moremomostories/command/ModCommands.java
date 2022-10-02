package com.Hileb.moremomostories.command;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

public class ModCommands {
    public static void give(EntityPlayer player,ItemStack stack){
        if (player.getHeldItemMainhand().isEmpty()){
            if (!player.addItemStackToInventory(stack))
            {
                player.dropItem(stack, false);
            }
        }
        else player.setItemStackToSlot(EntityEquipmentSlot.MAINHAND,stack);
    }
}
