package com.Hileb.moremomostories.init;

import com.Hileb.moremomostories.util.Reference;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


@Config(modid = Reference.MOD_ID, category = "")
public class ModConfig {
    @Mod.EventBusSubscriber(modid = Reference.MOD_ID)
    private static class EventHandler {

        private EventHandler() {
        }

        @SubscribeEvent
        public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(Reference.MOD_ID)) {
                ConfigManager.sync(Reference.MOD_ID, Config.Type.INSTANCE);
            }
        }
    }
    @Config.LangKey("canEntityElectricShaking")
    @Config.Comment("canEntityElectricShaking")
    public static final EntityElectricShakingConf entityElectricShakingConf = new EntityElectricShakingConf();

    public static class EntityElectricShakingConf{
          //ConfigLoader.canEntityElectricShaking=true;
        @Config.LangKey("moremomostories.conf.canEntityElectricShaking")
        @Config.Comment("canEntityElectricShaking")
        @Config.RequiresMcRestart
        public boolean iscanEntityElectricShaking=false;
    }
    @Config.LangKey("Dimension")
    @Config.Comment("Dimension")
    public static final BasicDimension dimension = new BasicDimension();

    public static class BasicDimension {
        //ConfigLoader.canEntityElectricShaking=true;
        @Config.LangKey("Dimension Hileb")
        @Config.Comment("Dimension Hileb")
        @Config.RequiresMcRestart
        public int WORLD_GEN_CONF=7481;
    }
    @Config.LangKey("SlashBlade.all")
    @Config.Comment("SlashBlade.all")
    public static final SlashBladeClass SlashBlade = new SlashBladeClass();

    public static class SlashBladeClass {
        //ConfigLoader.canEntityElectricShaking=true;
        @Config.LangKey("SA:RAIN ID")
        @Config.Comment("SA:RAIN ID")
        @Config.RequiresMcRestart
        public int SA_RAIN=81;
        @Config.LangKey("SA:WORLD ID")
        @Config.Comment("SA:WORLD ID")
        @Config.RequiresMcRestart
        public int SA_WORLD=82;
    }
}
