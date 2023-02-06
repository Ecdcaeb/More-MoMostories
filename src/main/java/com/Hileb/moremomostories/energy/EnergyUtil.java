package com.Hileb.moremomostories.energy;

import com.Hileb.moremomostories.util.NBTStrDef.IDLNBTUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;

public class EnergyUtil {
    private static HashMap<ResourceLocation,IEnergy> REGISTERS=new HashMap<>();
    public static void registerEnergy(IEnergy energy){
        REGISTERS.put(energy.getRegisterName(),energy);
    }
    public static void addEnergyToStack(ItemStack stack,IEnergy energy,float amount){
        IDLNBTUtil.SetDouble(stack,energy.getRegisterName().toString(),IDLNBTUtil.GetDouble(stack,energy.getRegisterName().toString(),0)+amount);
    }
}
