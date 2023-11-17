package com.Hileb.moremomostories.common.datafix;

import com.Hileb.moremomostories.MoreMoMoSrories;
import com.Hileb.moremomostories.common.datafix.items.DataFixDeepLake;
import com.Hileb.moremomostories.common.datafix.items.DataFixItemNameChanged;
import net.minecraft.util.datafix.FixTypes;
import net.minecraftforge.common.util.ModFixs;
import net.minecraftforge.fml.common.FMLCommonHandler;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/9/28 18:09
 **/

public class DataFixHandler{
    public static final int VERSION=MoreMoMoSrories.BUILD_VERSION;
    public static void register(){
        ModFixs modFixs=FMLCommonHandler.instance().getDataFixer().init(MoreMoMoSrories.MODID,VERSION);
        modFixs.registerFix(FixTypes.ITEM_INSTANCE,new DataFixDeepLake());
        modFixs.registerFix(FixTypes.ITEM_INSTANCE,new DataFixItemNameChanged());
        MoreMoMoSrories.LOGGER.info("register {} data fixer",1);
    }
}
