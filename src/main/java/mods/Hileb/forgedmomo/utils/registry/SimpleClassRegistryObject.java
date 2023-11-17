package mods.Hileb.forgedmomo.utils.registry;

import net.minecraft.util.ResourceLocation;

import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/11/17 18:13
 **/
public class SimpleClassRegistryObject<T>{
    public final ResourceLocation resourceLocation;
    public final Supplier<Class<T>> supplier;
    public final Predicate<SimpleClassRegistryObject<?>> predicate;
    public SimpleClassRegistryObject(ResourceLocation name,Supplier<Class<T>> supplierIn, Predicate<SimpleClassRegistryObject<?>> predicateIn){
        resourceLocation=name;
        supplier=supplierIn;
        predicate=predicateIn;
    }
    public SimpleClassRegistryObject(ResourceLocation name, Supplier<Class<T>> supplierIn){
        this(name,supplierIn,(_null)->true);
    }
}
