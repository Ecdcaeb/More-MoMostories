package mods.Hileb.forgedmomo.core;

import com.google.common.eventbus.Subscribe;
import mods.Hileb.forgedmomo.announces.IModPlugin;
import mods.Hileb.forgedmomo.api.slashblade.BladeType;
import mods.Hileb.forgedmomo.api.slashblade.SpecialAttackType;
import mods.flammpfeil.slashblade.SlashBlade;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModClassLoader;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import zone.rong.mixinbooter.ILateMixinLoader;
import zone.rong.mixinbooter.MixinBooterPlugin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/11/17 20:20
 **/
public class F3MLoadingLateHandler {
    //--fml
    @Subscribe
    public static void preInit(FMLPreInitializationEvent event){
        try{
            for(Class clazz:plugins){
                clazz.newInstance();
            }
        }catch (Exception e){
            throw new RuntimeException("can not new instance for IModPlugin",e);
        }
    }
    @Subscribe
    public static void init(FMLInitializationEvent event){
        if (Loader.isModLoaded(SlashBlade.modid)){
            BladeType.REGISTER.forEach(BladeType::registerStack);
            SpecialAttackType.REGISTER.forEach(SpecialAttackType::getID);
        }
    }
    @Subscribe
    public static void postInit(FMLPostInitializationEvent event){

    }
    @Subscribe
    public static void server(FMLServerStartingEvent event){}

    public static final List<Class> plugins=new ArrayList<>();
    public static void construction(FMLConstructionEvent event){
        ASMDataTable asmDataTable=event.getASMHarvestedData();
        ModClassLoader modClassLoader=event.getModClassLoader();
        ASMDataTable.ASMData asmData=null;
        Class pluginClass=null;
        Iterator<ASMDataTable.ASMData> iterator=asmDataTable.getAll(IModPlugin.class.getName().replace('.', '/')).iterator();
        try{
            if (iterator.hasNext()){
                asmData=iterator.next();
                modClassLoader.addFile(asmData.getCandidate().getModContainer());
                plugins.add(Class.forName(asmData.getClassName()));
            }
        }catch (Exception e){
            throw new RuntimeException("IModPlugin loading error",e);
        }
    }
    //--forge
    @SubscribeEvent
    public static void registerRecipe(RegistryEvent.Register<IRecipe> evt){
        if (Loader.isModLoaded(SlashBlade.modid)){
            IRecipe recipe=null;
            IForgeRegistry<IRecipe> r = evt.getRegistry();
            for(BladeType bladeType:BladeType.REGISTER){
                recipe=bladeType.getRecipe();
                if(recipe!=null){
                    r.register(recipe);
                }
            }
        }
    }
}
