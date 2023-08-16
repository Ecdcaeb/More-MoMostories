package com.Hileb.moremomostories.common.util.ticker;

import com.Hileb.moremomostories.MoreMoMoSrories;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MoreMoMoSrories.MODID)
public class TickHandler {
    private static NonNullList<ITickPack> list=NonNullList.create();
    public static void put(ITickPack pack, World world){
        list.add(pack);
    }
    public static void remove(ITickPack pack,World world){
        list.remove(pack);
    }
    public static void loadSave(){

    }
//    public static void onSave(WorldEvent.Save event){
//        JsonObject json=new JsonObject();
//        JsonArray array=new JsonArray();
//        for(ITickPack pack:list){
//            array.add(pack.toString());
//        }
//        json.add("ITickPacks",array);
//
//    }
}
