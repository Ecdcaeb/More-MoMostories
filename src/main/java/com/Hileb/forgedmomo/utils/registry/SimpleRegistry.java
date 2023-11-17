package com.Hileb.forgedmomo.utils.registry;

import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/11/17 18:01
 **/
public abstract class SimpleRegistry<T>{

    public List<SimpleRegisterObject<T>> ITEMS=new ArrayList<>();
    public SimpleRegistry(){}
    public abstract void register(ResourceLocation name,T object, Object... args);
    public final void registerAll(Object... args){
        for(SimpleRegisterObject<T> object:ITEMS){
            if (object.predicate.test(object)){
                register(object.resourceLocation,object.supplier.get(),args);
            }
        }
    }
    @SuppressWarnings("unchecked")
    public <V extends T> SimpleRegisterObject<V> addValue(SimpleRegisterObject<V> registerClass){
        ITEMS.add((SimpleRegisterObject<T>) registerClass);
        return registerClass;
    }
    public static <S> SimpleRegistry<S> of(RegistryConsumer<S> consumer){
        return new SimpleRegistry<S>() {
            @Override
            public void register(ResourceLocation name, S object, Object... args) {
                consumer.register(object,name,args);
            }
        };
    }
    @FunctionalInterface
    public interface RegistryConsumer<K>{
        <S extends K> void register(S object,ResourceLocation name,Object... args);
    }
}
