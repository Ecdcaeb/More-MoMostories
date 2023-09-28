package com.Hileb.moremomostories.mixin.core;

import com.Hileb.moremomostories.MoreMoMoSrories;
import com.google.common.eventbus.EventBus;
import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.LoadController;
import net.minecraftforge.fml.common.ModMetadata;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/9/28 18:02
 **/
@SuppressWarnings("unused")
public class ForgedMoMoContainer extends DummyModContainer{
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
        return true;
    }
}
