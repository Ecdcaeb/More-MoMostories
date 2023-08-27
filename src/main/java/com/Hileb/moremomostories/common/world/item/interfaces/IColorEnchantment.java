package com.Hileb.moremomostories.common.world.item.interfaces;

import net.minecraft.item.ItemStack;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/8/27 10:34
 **/
public interface IColorEnchantment {
    boolean hasEnchantmentEffect(ItemStack stack);
    int getEnchantmentColor(ItemStack stack);
}
