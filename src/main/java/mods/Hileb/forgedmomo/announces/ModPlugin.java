package mods.Hileb.forgedmomo.announces;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.eventhandler.Event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/11/17 22:03
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ModPlugin {
    LoaderState state();
    /**
     * public static void method(){} , or "" static.
     * */
    String method() default "<null>";

    String modid();

    class ModPluginContainer {
        public String modid;
        public LoaderState state;
        public String method;
        public String plugin;
        public ModPluginContainer(String modidIn, String className, LoaderState state, String method){
            this.modid=modidIn;
            this.plugin=className;
            this.state=state;
            this.method=method;
        }
        public boolean couldLoad(){
            return Loader.isModLoaded(modid);
        }
        public void load() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
            if (!"<null>".equals(method) && method!=null && !method.isEmpty()){
                Class.forName(plugin).getMethod(method).invoke(null);
            }else Class.forName(plugin);
        }
    }
}
