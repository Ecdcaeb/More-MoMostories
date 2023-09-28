package com.Hileb.moremomostories.common.datafix;

import com.Hileb.moremomostories.MoreMoMoSrories;
import com.Hileb.moremomostories.common.datafix.items.DataFixDeepLake;
import com.Hileb.moremomostories.common.events.datafix.DataFixerSetupEvent;
import net.minecraft.util.datafix.FixTypes;
import net.minecraftforge.common.util.ModFixs;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/9/28 18:09
 **/

public class DataFixHandler{
    public static final int VERSION=102000011;
    public static void register(){
        ModFixs modFixs= FMLCommonHandler.instance().getDataFixer().init(MoreMoMoSrories.MODID,VERSION);
        modFixs.registerFix(FixTypes.ITEM_INSTANCE,new DataFixDeepLake());
        MoreMoMoSrories.LOGGER.info("register {} data fixer",1);
    }
}
