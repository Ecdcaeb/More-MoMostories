package mods.Hileb.forgedmomo.core;

import com.google.common.eventbus.Subscribe;
import mods.Hileb.forgedmomo.announces.ModPlugin;
import mods.Hileb.forgedmomo.api.client.resource.ResourceGenerateEvent;
import mods.Hileb.forgedmomo.utils.registry.DeferredEventBusRegisterManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/11/17 20:20
 **/
@SuppressWarnings("unused")
public class F2MFMLLoadingHandler {
    public static final F2MFMLLoadingHandler INSTANCE=new F2MFMLLoadingHandler();
    public static final List<ModPlugin.ModPluginContainer> plugins=new ArrayList<>();
    //internal
    public static void addToBus(Object o){
        DeferredEventBusRegisterManager.put(o);
    }
    // fml events
    @Subscribe
    public void preInit(FMLPreInitializationEvent event){
        DeferredEventBusRegisterManager.registerAll();
    }
    @Subscribe
    public void complete(FMLLoadCompleteEvent event){
        MinecraftForge.EVENT_BUS.post(new ResourceGenerateEvent());
    }
}
