package mods.Hileb.moremomostories.client.resource;

import mods.Hileb.forgedmomo.api.client.resource.ResourceGenI18nChannel;
import mods.Hileb.forgedmomo.api.client.resource.ResourceGenI18nChannel.Language;
import mods.Hileb.forgedmomo.api.client.resource.ResourceGenerateEvent;
import mods.Hileb.moremomostories.MoreMoMoSrories;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/12/2 13:21
 **/
@Mod.EventBusSubscriber(modid= MoreMoMoSrories.MODID)
public class RCGI18n {
    @SubscribeEvent
    public static void onGeneral(ResourceGenerateEvent event){
        ResourceGenI18nChannel i18nChannel=new ResourceGenI18nChannel();
        //define lang
        Language zh_cn= i18nChannel.of("zh_cn");
        Language en_us= i18nChannel.of("en_us");

        //start text
        i18nChannel.commit(MoreMoMoSrories.MODID);
        i18nChannel.commit("Generate Data:"+new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
        i18nChannel.commit("");
        //
        i18nChannel.pop("testKey");
        zh_cn.push("我是中文");
        en_us.push("I am English");

        //push and gen
        try {
            i18nChannel.save(event.root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
