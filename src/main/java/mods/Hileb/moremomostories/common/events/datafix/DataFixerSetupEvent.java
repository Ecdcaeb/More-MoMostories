//package com.Hileb.moremomostories.common.events.datafix;
//
//import net.minecraftforge.common.MinecraftForge;
//import net.minecraftforge.common.util.CompoundDataFixer;
//import net.minecraftforge.common.util.ModFixs;
//import net.minecraftforge.fml.common.eventhandler.Event;
//
///**
// * @Project More-MoMostories
// * @Author Hileb
// * @Date 2023/9/28 17:30
// **/
//public final class DataFixerSetupEvent extends Event {
//    public final CompoundDataFixer fixer;
//    @SuppressWarnings("unused")
//    public static void fireSetUpDataFixEvent(CompoundDataFixer dataFixer){
//        MinecraftForge.EVENT_BUS.post(new DataFixerSetupEvent(dataFixer));
//    }
//    public DataFixerSetupEvent(CompoundDataFixer dataFixer){
//        fixer=dataFixer;
//    }
//    public ModFixs setUpFixer(String modid,int version){
//        return fixer.init(modid,version);
//    }
//
//    @Override
//    public boolean isCancelable() {
//        return false;
//    }
//
//    @Override
//    public boolean hasResult() {
//        return false;
//    }
//}
