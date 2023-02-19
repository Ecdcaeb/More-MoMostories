package com.Hileb.moremomostories;

import com.Hileb.moremomostories.blocks.tileEntity.TileEntityBookShelf;
import com.Hileb.moremomostories.gui.ModGuiElementLoader;
import com.Hileb.moremomostories.init.ModOreDic;
import com.Hileb.moremomostories.init.ModRecipes;
import com.Hileb.moremomostories.init.ModSpawn;
import com.Hileb.moremomostories.init.RegistryHandler;
import com.Hileb.moremomostories.item.myItems.ItemColorHandler;
import com.Hileb.moremomostories.keys.KeyboardManager;
import com.Hileb.moremomostories.meta.MetaUtil;
import com.Hileb.moremomostories.network.NetworkHandler;
import com.Hileb.moremomostories.otherMods.SlashBlade.SlashBladeUtil;
import com.Hileb.moremomostories.proxy.ProxyBase;
import com.Hileb.moremomostories.util.Reference;
import com.Hileb.moremomostories.util.named.NameTagHandler;
import com.Hileb.moremomostories.util.named.NameTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Logger;

import static com.Hileb.moremomostories.init.RegistryHandler.initRegistries;

/*
为了加腐烂的食物
请RecipePutrid.PutridItems.add(Item.getByNameOrId();
 */
//To let the player be a traveling god who plays yin-yang magic.

@Mod(modid = IdlFramework.MODID, name = IdlFramework.NAME, version = IdlFramework.VERSION,dependencies="required-after:momostories;required-after:mixinbooter@[4.2,);after:idealland;after:forestry;after:manametalmod;after:calculator;after:ic2;after:flammpfeil.slashblade")//dependencies = "required-after:Forge@[14.23.5.2705,)"
public class IdlFramework {
    public static final String MODID = "moremomostories";
    public static final String NAME = "More MoMoStories";
    public static final String VERSION = "1.0.1.10";
    public static Logger logger;

    public static final boolean SHOW_WARN = true;

    @Mod.Instance
    public static IdlFramework instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static ProxyBase proxy;


    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();


        RegistryHandler.preInitRegistries(event);

    }

    @EventHandler
    public static void Init(FMLInitializationEvent event) {
        ModRecipes.Init();
        RegisterTileEntity();
        initRegistries(event);
        ModOreDic.init();
        new ModGuiElementLoader();
        if (!proxy.isServer())
        {
            clientInit();
            KeyboardManager.init();
        }
        if (MetaUtil.isLoaded_SlashBlade){//联动拔刀剑//
            SlashBladeUtil slashBladeUtil=new SlashBladeUtil();//拔刀剑联动主类
            slashBladeUtil.registerBlade();//注册拔刀剑
            slashBladeUtil.registerSA();//注册SA
        }
        NetworkHandler.init();

		LogWarning("%s has finished its initializations", MODID);

	}
	@SideOnly(Side.CLIENT)
    public static void clientInit(){
        ItemColorHandler.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        // Moved Spawning registry to last since forge doesn't auto-generate sub
        // "M' biomes until late
//        if (ModConfig.SPAWN_CONF.SPAWN) {
//            ModSpawn.registerSpawnList();
//        }

        ModSpawn.registerSpawnList();
        TrashTalking();

        RegistryHandler.postInitReg();
        NameTags.register();
        NameTagHandler.post();
    }

    @EventHandler
    public static void serverInit(FMLServerStartingEvent event) {
        RegistryHandler.serverRegistries(event);
    }


    private void TrashTalking() {
        if (MetaUtil.isIDLLoaded)
        {
            IdlFramework.Log("[Idealland Framework] Bow to Idealland.");
        }
        else {
            IdlFramework.Log("[Idealland Framework] Made with Idealland Framework.");
        }
    }

    private static void RegisterTileEntity() {
//        GameRegistry.registerTileEntity(TileEntityDeBoomOrb.class, new ResourceLocation(MODID, "deboom_orb_basic"));

        //GameRegistry.registerTileEntity(TileEntityBuilderFarm.class, new ResourceLocation(MODID, "builder_farm_basic"));
        GameRegistry.registerTileEntity(TileEntityBookShelf.class, new ResourceLocation(MODID, "tile_entity_book_shelf"));
    }

    public static void LogWarning(String str, Object... args) {
        if (SHOW_WARN) {
            logger.warn(String.format(str, args));
        }
    }

    public static void LogWarning(String str) {
        if (SHOW_WARN) {
            logger.warn(str);
        }
    }

    public static void Log(String str) {
//        if (ModConfig.GeneralConf.LOG_ON)
//        {
        logger.info(str);
//        }
    }

    public static void Log(String str, Object... args) {
//        if (ModConfig.GeneralConf.LOG_ON)
//        {
        logger.info(String.format(str, args));
//        }
    }
    public static String after_mod(String modid){
        return String.format(";after:%s", modid);
    }
}