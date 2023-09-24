package com.Hileb.moremomostories.common.capabilities.nametag;

import net.minecraft.nbt.NBTTagCompound;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/9/8 22:34
 **/
public class NameTagContainer extends CapNameTag{
    public String key;
    public NameTagContainer(String keyIn){
        key=keyIn;
    }

    @Override
    public String getTagTranslateKey() {
        return key;
    }

    @Override
    public void setTagTranslateKey(String keyIn) {
        key=keyIn;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt=new NBTTagCompound();
        nbt.setString("key",key);
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        key=nbt.getString("key");
    }
}
