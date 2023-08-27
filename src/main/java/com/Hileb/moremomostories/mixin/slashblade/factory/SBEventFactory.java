package com.Hileb.moremomostories.mixin.slashblade.factory;

import mods.flammpfeil.slashblade.specialattack.IJustSpecialAttack;
import mods.flammpfeil.slashblade.specialattack.SpecialAttackBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/8/27 12:05
 **/
public class SBEventFactory {
    public static boolean onDoSpacialAttack(ItemStack stack, EntityPlayer par3EntityPlayer, boolean isJust){
        System.out.println("factory event");
        return false;
    }
}
