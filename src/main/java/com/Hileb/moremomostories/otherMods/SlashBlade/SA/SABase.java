package com.Hileb.moremomostories.otherMods.SlashBlade.SA;

import mods.flammpfeil.slashblade.specialattack.SpecialAttackBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public abstract class SABase extends SpecialAttackBase {
    public SABase(){
        ModSA.saList.add(this);
    }
    public abstract String toString();

    public abstract void doSpacialAttack(ItemStack var1, EntityPlayer var2);
}
