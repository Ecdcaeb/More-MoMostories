package mods.Hileb.forgedmomo.core;

import com.google.common.eventbus.Subscribe;
import mods.Hileb.forgedmomo.announces.ModPlugin;
import mods.Hileb.forgedmomo.core.ForgedMoMoContainer;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
import net.minecraftforge.fml.common.discovery.asm.ModAnnotation;
import net.minecraftforge.fml.common.event.*;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/11/21 13:05
 **/
public class ModPluginLoader {
    private static final LinkedList<ModPlugin.ModPluginContainer> plugins=new LinkedList<>();
    private static void loadPlugins(ASMDataTable data){
        ASMDataTable.ASMData asmData;
        ModPlugin.ModPluginContainer modPluginContainer = null;
        Iterator<ASMDataTable.ASMData> iterator= data.getAll(ModPlugin.class.getName()).iterator();
        try{
            while (iterator.hasNext()){
                asmData=iterator.next();
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
        //although: CONSTRUCTING should not be used because ModContainer has not been built yet.
        runModPlugins(LoaderState.CONSTRUCTING);
    }
    private static void runModPlugins(LoaderState state){
        Iterator<ModPlugin.ModPluginContainer> iterator=plugins.iterator();
        ModPlugin.ModPluginContainer container=null;
        try{
            while (iterator.hasNext()){
                container=iterator.next();
                if (container.state==state){
                    if (container.couldLoad()){
                        container.load();
                        iterator.remove();
                    }else iterator.remove();
                }
            }
        }catch (Exception e){
            throw new RuntimeException("can not run ModPlugin at state "+state.getPrettyName()+" for "+(container==null?"null":container.plugin),e);
        }
    }
    //FML Events
    @Subscribe
    public void preInit(FMLPreInitializationEvent event){runModPlugins(LoaderState.PREINITIALIZATION);}
    @Subscribe
    public void init(FMLInitializationEvent event){
        runModPlugins(LoaderState.INITIALIZATION);
    }
    @Subscribe
    public void postInit(FMLPostInitializationEvent event){
        runModPlugins(LoaderState.POSTINITIALIZATION);
    }
    @Subscribe
    public void complete(FMLLoadCompleteEvent event){runModPlugins(LoaderState.AVAILABLE);}
    @Subscribe
    public void serverAboutToStart(FMLServerAboutToStartEvent event){runModPlugins(LoaderState.SERVER_ABOUT_TO_START);}
    @Subscribe
    public void serverStarting( FMLServerStartingEvent event){runModPlugins(LoaderState.SERVER_STARTING);}
    @Subscribe
    public void serverStarted( FMLServerStartedEvent event){runModPlugins(LoaderState.SERVER_STARTED);}
    @Subscribe
    public void serverStopping( FMLServerStoppingEvent event){runModPlugins(LoaderState.SERVER_STOPPING);}
    @Subscribe
    public void serverStopped( FMLServerStoppedEvent event){runModPlugins(LoaderState.SERVER_STOPPED);}
    @Subscribe
    public void construction(FMLConstructionEvent event){
        loadPlugins(event.getASMHarvestedData());
        runModPlugins(LoaderState.CONSTRUCTING);
    }
}
