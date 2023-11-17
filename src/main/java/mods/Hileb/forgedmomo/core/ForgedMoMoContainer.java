package mods.Hileb.forgedmomo.core;

import mods.Hileb.forgedmomo.interfaces.IModelHolder;
import mods.Hileb.moremomostories.MoreMoMoSrories;
import com.google.common.eventbus.EventBus;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.LoadController;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/9/28 18:02
 **/
@SuppressWarnings("unused")
public class ForgedMoMoContainer extends DummyModContainer{
    public static final Side side=FMLCommonHandler.instance().getSide();
    public static final Logger LOGGER= LogManager.getLogger("ForgedMoMo");
    public ForgedMoMoContainer(){
        super(new ModMetadata());
        ModMetadata metadata=this.getMetadata();
        metadata.modId=MoreMoMoStoriesLoadingCore.NAME;
        metadata.name= MoreMoMoStoriesLoadingCore.NAME;
        metadata.description="A mod for MoMoStories.This is the coremod!";
        metadata.version= MoreMoMoSrories.VERSION;
        metadata.url="https://www.mcmod.cn/class/7481.html";
        metadata.updateUrl="https://www.mcmod.cn/class/7481.html";
        metadata.authorList.add("Hileb");
        metadata.credits="\n Idealland - they provided this framework. \n momostories - the mod is very very goooooooood.~";
        metadata.logoFile="assets/moremomostories/index.png";
        metadata.screenshots=new String[]{"assets/moremomostories/index.png"};
    }

    @Override
    public boolean registerBus(EventBus bus, LoadController controller) {
        LOGGER.info("loading ForgedMoMo");
        MinecraftForge.EVENT_BUS.register(IModelHolder.ModelHandler.class);
        MinecraftForge.EVENT_BUS.register(F3MLoadingLateHandler.class);
        bus.register(F3MLoadingLateHandler.class);
        return true;
    }
}
