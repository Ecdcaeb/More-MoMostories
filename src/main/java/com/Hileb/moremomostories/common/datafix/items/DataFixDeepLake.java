package com.Hileb.moremomostories.common.datafix.items;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.datafix.IFixableData;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/9/28 18:10
 **/
public class DataFixDeepLake implements IFixableData {

    @Override
    public int getFixVersion() {
        return 102000011;
    }

    @Override
    public NBTTagCompound fixTagCompound(NBTTagCompound compound) {
        if (compound.hasKey("id", 8))
        {
            String name = compound.getString("id");
            if ("momostories:the_supreme_magi_deep_lake".equals(name)){
                compound.setString("id","momostories:the_supreme_magic_deep_lake");
            }
        }
        return compound;
    }
}
