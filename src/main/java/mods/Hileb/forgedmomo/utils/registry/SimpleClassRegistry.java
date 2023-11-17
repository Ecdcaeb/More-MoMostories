package mods.Hileb.forgedmomo.utils.registry;

import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/11/17 18:12
 **/
public abstract class SimpleClassRegistry<T>{
    public List<SimpleClassRegistryObject<? extends T>> ITEMS=new ArrayList<>();
    public SimpleClassRegistry(){}
    public final void registerAll(Object... args){
        for(SimpleClassRegistryObject<? extends T> object:ITEMS){
            if (object.predicate.test(object)){
                register(object.resourceLocation, object.supplier.get(),args);
            }
        }
    }
    @SuppressWarnings("unchecked")
    public <V extends T> SimpleClassRegistryObject<V> addValue(SimpleClassRegistryObject<V> registerClass){
        ITEMS.add(registerClass);
        return registerClass;
    }
    public abstract void register(ResourceLocation name, Class<? extends T> object, Object... args);
    public static <S> SimpleClassRegistry<S> of(SimpleClassRegistry.RegistryConsumer<S> consumer){
        return new SimpleClassRegistry<S>() {
            @Override
            public void register(ResourceLocation name, Class<? extends S> object, Object... args) {
                consumer.register(object,name,args);
            }
        };
    }
    @FunctionalInterface
    public interface RegistryConsumer<K>{
        void register(Class<? extends K> object,ResourceLocation name,Object... args);
    }
}
