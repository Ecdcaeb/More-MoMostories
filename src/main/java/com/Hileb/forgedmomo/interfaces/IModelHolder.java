package com.Hileb.forgedmomo.interfaces;

import com.Hileb.forgedmomo.core.ForgedMoMoContainer;
import com.Hileb.moremomostories.MoreMoMoSrories;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/11/17 19:15
 **/
public interface IModelHolder<T> {
    @SuppressWarnings("unchecked")
    default T self(){return (T)this;}
    default void register(){
        if (ForgedMoMoContainer.side== Side.CLIENT){
            registerModels();
        }
    }
    void registerModels();
    interface IItem extends IModelHolder<net.minecraft.item.Item>{
        @SuppressWarnings("all")
        @Override
        default void registerModels(){
            ModelLoader.setCustomModelResourceLocation(self(),0, new ModelResourceLocation(self().getRegistryName(), "inventory"));
        }
    }
    interface IBlock extends IModelHolder<net.minecraft.block.Block>{
        @SuppressWarnings("all")
        @Override
        default void registerModels(){
            ModelLoader.setCustomModelResourceLocation(net.minecraft.item.Item.getItemFromBlock(self()),0, new ModelResourceLocation(self().getRegistryName(), "inventory"));
        }
    }
    public static class ModelHanlder{
        @SideOnly(Side.CLIENT)
        @SubscribeEvent
        public static void onModelRegister(ModelRegistryEvent event)
        {
            for(net.minecraft.item.Item item: ForgeRegistries.ITEMS.getValues()){
                if (item instanceof IModelHolder){
                    ((IModelHolder)item).register();
                }
            }
            for(net.minecraft.block.Block block: ForgeRegistries.BLOCKS.getValues()){
                if (block instanceof IModelHolder){
                    ((IModelHolder)block).register();
                }
            }
        }
    }
}
