package com.Hileb.moremomostories;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;


public class ConfigLoader
{
    private static Configuration config;

    private static Logger logger;

    public static boolean canEntityElectricShaking;

    public static void init(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        config = new Configuration(event.getSuggestedConfigurationFile());

        config.load();
        load();
    }

    public static void load()
    {
        logger.info("Started loading config. ");
        //"How many seconds can a diamond burn in a furnace. "
        canEntityElectricShaking = config.get(Configuration.CATEGORY_GENERAL, "canEntityElectricShaking", true,"Do You Want Entity Electric Shaking").getBoolean();

        config.save();
        logger.info("Finished loading config. ");
    }

    public static Logger logger()
    {
        return logger;
    }
}
