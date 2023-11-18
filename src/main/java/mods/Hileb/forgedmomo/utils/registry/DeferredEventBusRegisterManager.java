package mods.Hileb.forgedmomo.utils.registry;

import net.minecraftforge.common.MinecraftForge;

import java.util.ArrayList;
import java.util.List;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/11/18 16:50
 **/
/**
 * register handler at pre init but set advanced.
 * Forge Event Bus的注册不早于Pre-init否则无ModContainer注册失效
 * **/
public class DeferredEventBusRegisterManager {
    public static List<Object> advancedRegisterObject=new ArrayList<>();
    public static void registerAll(){
        if (advancedRegisterObject!=null){
            for (Object o:advancedRegisterObject){
                MinecraftForge.EVENT_BUS.register(o);
            }
            advancedRegisterObject=null;
        }
    }
    public static void put(Object object){
        if (advancedRegisterObject!=null){
            advancedRegisterObject.add(object);
        }else MinecraftForge.EVENT_BUS.register(object);
    }
}
