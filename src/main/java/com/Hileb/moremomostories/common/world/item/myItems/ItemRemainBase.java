package com.Hileb.moremomostories.common.world.item.myItems;

import com.Hileb.moremomostories.common.world.item.IEntityItemX;
import com.Hileb.moremomostories.common.world.item.ItemBase;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class ItemRemainBase extends ItemBase implements IEntityItemX {
    public ItemRemainBase(String name){
        super(name);
    }
    @Nonnull
    public ItemStack getContainerItem(ItemStack itemStack) {
        ItemStack stack = itemStack.copy();
        stack.setCount(1);
        return stack;
    }
}
