package com.Hileb.moremomostories.common.capabilities.nametag;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/9/8 22:55
 **/
@SuppressWarnings("all")
public class CapabilityNameTagProvider implements ICapabilitySerializable<NBTTagCompound> {
    public CapNameTag capNameTag;
    public CapabilityNameTagProvider(CapNameTag cap){
        capNameTag=cap;
    }
    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability==CapabilityNameTag.CAPABILITY;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return capability==CapabilityNameTag.CAPABILITY?(T)capNameTag:null;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        return capNameTag.serializeNBT();
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        capNameTag.deserializeNBT(nbt);
    }
}
