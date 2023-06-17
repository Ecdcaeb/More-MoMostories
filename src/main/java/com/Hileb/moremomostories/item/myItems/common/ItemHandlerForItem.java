package com.Hileb.moremomostories.item.myItems.common;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ItemHandlerForItem implements ICapabilityProvider {
    public final ItemStackHandler stackHandler;
    public ItemHandlerForItem(int maxCount, NBTTagCompound nbt){
        stackHandler=new ItemStackHandler(maxCount);
        stackHandler.deserializeNBT(nbt);
    }
    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability== CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability==CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(stackHandler);
        }
        else return null;
    }


}
