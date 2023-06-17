package com.Hileb.moremomostories.util;

import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundEvent;

import java.util.ArrayList;
import java.util.List;

public class YTXSYSounds {
    private static List<SoundEvent> soundEventList=new ArrayList<>();
    public static void  register(int id,SoundEvent soundEvent){
        soundEventList.add(id,soundEvent);
    }
    public static void init(){//添加一些原版的声音
        register(0, SoundEvents.AMBIENT_CAVE);
        register(1, SoundEvents.BLOCK_ANVIL_BREAK);
        register(2, SoundEvents.BLOCK_ANVIL_DESTROY);
        register(3, SoundEvents.BLOCK_ANVIL_LAND);
        register(4, SoundEvents.BLOCK_CLOTH_BREAK);
        register(5, SoundEvents.BLOCK_DISPENSER_FAIL);
        register(6, SoundEvents.BLOCK_GRASS_STEP);
        register(7, SoundEvents.RECORD_MALL);
        register(8, SoundEvents.ITEM_SHIELD_BLOCK);
        register(9, SoundEvents.ITEM_SHOVEL_FLATTEN);
        register(10, SoundEvents.ITEM_FIRECHARGE_USE);
        register(11, SoundEvents.RECORD_WAIT);
        register(12, SoundEvents.EVOCATION_FANGS_ATTACK);

    }
    public static SoundEvent get(int index){
        return soundEventList.get(index);
    }
    public static int getLimit(){
        return soundEventList.size();
    }

}
