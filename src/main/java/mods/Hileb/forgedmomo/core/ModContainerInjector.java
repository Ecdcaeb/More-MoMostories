package mods.Hileb.forgedmomo.core;

import com.google.common.eventbus.EventBus;
import net.minecraftforge.fml.common.*;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/11/23 12:14
 **/
public class ModContainerInjector {
    public static void inject() {
        try{
            File source= new File(ModContainerInjector.class.getProtectionDomain().getCodeSource().getLocation().getFile());
            Field mods=Loader.class.getDeclaredField("mods");
            mods.setAccessible(true);
            List<ModContainer> modContainers= (List<ModContainer>) mods.get(Loader.instance());
            //add your modContainers
//            for(int i=0;i<50;i++){
//                String name=String.format("mod_%d",i);
//                DummyModContainer dummyModContainer=new DummyModContainer(new ModMetadata()){
//                    @Override
//                    public boolean registerBus(EventBus bus, LoadController controller) {
//                        return true;
//                    }
//                };
//                ModMetadata metadata=dummyModContainer.getMetadata();
//                metadata.modId=name;
//                metadata.name=name;
//                metadata.authorList.add(name);
//                InjectedModContainer container=new InjectedModContainer(dummyModContainer,source);
//                modContainers.add(container);
//            }
        }catch (Exception ignored){

        }
    }
}
