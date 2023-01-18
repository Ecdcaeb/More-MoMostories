package com.Hileb.moremomostories.util.named;

import com.Hileb.moremomostories.IdlFramework;
import com.Hileb.moremomostories.util.NBTStrDef.IDLNBTUtil;
import com.sun.istack.internal.NotNull;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Mod.EventBusSubscriber(modid= IdlFramework.MODID)
public class NameTagHandler {
    private static HashMap<ResourceLocation,NameTagBase> TAG_REGISTERS=new HashMap<>();
    public static final String NBT_TAG="com.hileb.momo.nbt.tag";
    public static boolean hasAnyTag(ItemStack stack){
        String key=IDLNBTUtil.GetString(stack,NBT_TAG,"null");
        if (key!=null && !(key.equals("null"))){
            if (TAG_REGISTERS.containsKey(new ResourceLocation(key)) && TAG_REGISTERS.get(new ResourceLocation(key))!=null){return true;}
        }
        return false;
    }
    public static boolean hasTheTag(ItemStack stack,NameTagBase tag){
        if (hasAnyTag(stack)){
            String key=IDLNBTUtil.GetString(stack,NBT_TAG,"null");
            if (tag==TAG_REGISTERS.get(new ResourceLocation(key)))return true;
        }
        return false;
    }
    public static NameTagBase getTag(ItemStack stack){
        if (hasAnyTag(stack)){
            String key=IDLNBTUtil.GetString(stack,NBT_TAG,"null");
            return TAG_REGISTERS.get(new ResourceLocation(key));
        }
        return null;
    }
    public static void apply(@NotNull ItemStack stack,@NotNull NameTagBase key){
        IDLNBTUtil.SetString(stack,NBT_TAG,key.getRegisterName().toString());
    }

    public static void randomApply(ItemStack stack){
        Random random=new Random(stack.hashCode());
        for(NameTagBase tag:TAG_REGISTERS.values()){
            if (tag.couldApply(stack,random)){
                apply(stack,tag);
                return;
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onToolTip(ItemTooltipEvent event){
        if (hasAnyTag(event.getItemStack())){
            String name=event.getToolTip().get(0);
            String trueName=getTag(event.getItemStack()).renderName(event.getItemStack())+" "+name;
            event.getToolTip().set(0,trueName);
        }
    }
    public static void register(NameTagBase tag){
        IdlFramework.Log("register nameTag: %s",tag.getRegisterName().toString());
        TAG_REGISTERS.put(tag.getRegisterName(),tag);
    }
    public static void registerAll(List<NameTagBase> value){
        for(NameTagBase tag:value){
            register(tag);
        }
    }
    public static void post(){
        IdlFramework.LogWarning("register %d nameTag",TAG_REGISTERS.size());
    }

}
