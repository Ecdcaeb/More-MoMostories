package mods.Hileb.forgedmomo.utils.registry;

import net.minecraft.util.ResourceLocation;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class SimpleRegisterObject<T>{
    public final ResourceLocation resourceLocation;
    public final Supplier<? extends T> supplier;
    public final Predicate<SimpleRegisterObject<? extends T>> predicate;
    public SimpleRegisterObject(ResourceLocation name, Supplier<? extends T> supplierIn, Predicate<SimpleRegisterObject<? extends T>> predicateIn){
        resourceLocation=name;
        supplier=supplierIn;
        predicate=predicateIn;
    }
    public SimpleRegisterObject(ResourceLocation name, Supplier<? extends T> supplierIn){
        this(name,supplierIn,(_null)->true);
    }
}
