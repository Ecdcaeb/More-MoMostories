package com.Hileb.moremomostories.common.capabilities.nametag;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/9/8 22:33
 **/
public abstract class CapNameTag implements INBTSerializable<NBTTagCompound> {
    public static final CapNameTag EMPTY=new CapNameTag() {
        @Override
        public String getTagTranslateKey() {
            return "";
        }

        @Override
        public void setTagTranslateKey(String key) {

        }

        @Override
        public NBTTagCompound serializeNBT() {
            return new NBTTagCompound();
        }

        @Override
        public void deserializeNBT(NBTTagCompound nbt) {

        }
    };
    public CapNameTag(){}
    public abstract String getTagTranslateKey();
    public abstract void setTagTranslateKey(String key);
    public abstract NBTTagCompound serializeNBT();
    public abstract void deserializeNBT(NBTTagCompound nbt);

}
