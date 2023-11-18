package mods.Hileb.forgedmomo.core;

import com.google.common.eventbus.Subscribe;
import mods.Hileb.forgedmomo.announces.ModPlugin;
import mods.Hileb.forgedmomo.api.resource.ResourceGenerateEvent;
import mods.Hileb.forgedmomo.interfaces.IModelHolder;
import mods.Hileb.forgedmomo.utils.registry.DeferredEventBusRegisterManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.ModClassLoader;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
import net.minecraftforge.fml.common.discovery.asm.ModAnnotation;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.relauncher.Side;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/11/17 20:20
 **/
@SuppressWarnings("unused")
public class F3MFMLLoadingHandler {
    public static final F3MFMLLoadingHandler INSTANCE=new F3MFMLLoadingHandler();
    public static final List<ModPlugin.ModPluginContainer> plugins=new ArrayList<>();
    //internal
    private static void loadModPlugins(LoaderState state){
        Iterator<ModPlugin.ModPluginContainer> iterator=plugins.iterator();
        ModPlugin.ModPluginContainer container=null;
        try{
            while (iterator.hasNext()){
                container=iterator.next();
                if (container.state==state){
                    if (container.couldLoad()){
                        container.load();
                        iterator.remove();
                    }
                }
            }
        }catch (Exception e){
            throw new RuntimeException("can not run ModPlugin at state "+state.getPrettyName()+" for "+(container==null?"null":container.plugin),e);
        }
    }
    public static void addToBus(Object o){
        DeferredEventBusRegisterManager.put(o);
    }
    // fml events
    @Subscribe
    public void preInit(FMLPreInitializationEvent event){
        loadModPlugins(LoaderState.PREINITIALIZATION);
        DeferredEventBusRegisterManager.registerAll();
    }
    @Subscribe
    public void init(FMLInitializationEvent event){
        loadModPlugins(LoaderState.INITIALIZATION);
    }
    @Subscribe
    public void postInit(FMLPostInitializationEvent event){
        loadModPlugins(LoaderState.POSTINITIALIZATION);
    }
    @Subscribe
    public void complete(FMLLoadCompleteEvent event){
        loadModPlugins(LoaderState.AVAILABLE);
        MinecraftForge.EVENT_BUS.post(new ResourceGenerateEvent());
    }
    @Subscribe
    public void serverAboutToStart(FMLServerAboutToStartEvent event){
        loadModPlugins(LoaderState.SERVER_ABOUT_TO_START);
    }
    @Subscribe
    public void serverStarting( FMLServerStartingEvent event){
        loadModPlugins(LoaderState.SERVER_STARTING);
    }
    @Subscribe
    public void serverStarted( FMLServerStartedEvent event){
        loadModPlugins(LoaderState.SERVER_STARTED);
    }
    @Subscribe
    public void serverStopping( FMLServerStoppingEvent event){
        loadModPlugins(LoaderState.SERVER_STOPPING);
    }
    @Subscribe
    public void serverStopped( FMLServerStoppedEvent event){
        loadModPlugins(LoaderState.SERVER_STOPPED);
    }
    @Subscribe
    public void construction(FMLConstructionEvent event){
        ASMDataTable asmDataTable=event.getASMHarvestedData();
        //ModClassLoader modClassLoader=event.getModClassLoader();
        ASMDataTable.ASMData asmData;
        ModPlugin.ModPluginContainer modPluginContainer;
        Iterator<ASMDataTable.ASMData> iterator=asmDataTable.getAll(ModPlugin.class.getName()).iterator();
        try{
            while (iterator.hasNext()){
                asmData=iterator.next();
                //modClassLoader.addFile(asmData.getCandidate().getModContainer());
                modPluginContainer=new ModPlugin.ModPluginContainer(
                        (String)asmData.getAnnotationInfo().get("modid"),
                        asmData.getClassName(),
                        LoaderState.valueOf(((ModAnnotation.EnumHolder)asmData.getAnnotationInfo().get("state")).getValue()),
                        (String) asmData.getAnnotationInfo().get("method")
                );
                plugins.add(modPluginContainer);
                ForgedMoMoContainer.LOGGER.info("Loading ModPlugin {} for {} at {}",
                        modPluginContainer.plugin,modPluginContainer.modid,modPluginContainer.state.getPrettyName());
            }
        }catch (Exception e){
            throw new RuntimeException("ModPlugin loading error",e);
        }
        loadModPlugins(LoaderState.CONSTRUCTING);
        if (FMLCommonHandler.instance().getSide()==Side.CLIENT){
            MinecraftForge.EVENT_BUS.register(IModelHolder.ModelHandler.class);
        }
        ForgedMoMoContainer.LOGGER.warn("consTTT");
    }
}
