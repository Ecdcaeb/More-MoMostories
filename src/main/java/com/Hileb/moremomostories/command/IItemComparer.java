package com.Hileb.moremomostories.command;

import net.minecraft.item.ItemStack;

public interface IItemComparer {
    boolean apply(ItemStack stack);
}
