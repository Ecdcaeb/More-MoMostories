package com.Hileb.moremomostories.util.register;

import com.Hileb.moremomostories.IdlFramework;
import com.Hileb.moremomostories.blocks.tileEntity.TileEntityBookShelf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;
import java.util.List;

public class DefineRegisterClass<T> {
    public final Class<? extends T> registerObj;
    public final ResourceLocation resourceLocation;
    public DefineRegisterClass(String name,Class<? extends T> registerObjIn){
        registerObj=registerObjIn;
        resourceLocation=new ResourceLocation(IdlFramework.MODID,name);
    }
    public T newInstance() throws InstantiationException, IllegalAccessException {
        return registerObj.newInstance();
    }

    public static class DefineTileEntity extends DefineRegisterClass<TileEntity>{
        public static List<DefineTileEntity> REGISTRY =new ArrayList<>();

        public static DefineTileEntity BOOK_SHELF=new DefineTileEntity("tile_entity_book_shelf",TileEntityBookShelf.class);

        public DefineTileEntity(String name, Class<? extends TileEntity> registerObjIn) {
            super(name, registerObjIn);
            REGISTRY.add(this);
        }
        public static void register(){
            for(DefineTileEntity defineTileEntity:REGISTRY){
                GameRegistry.registerTileEntity(defineTileEntity.registerObj,defineTileEntity.resourceLocation);
            }
        }
    }
}
