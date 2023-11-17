package com.Hileb.moremomostories;

import com.Hileb.moremomostories.common.capabilities.nametag.CapabilityNameTag;
import com.Hileb.moremomostories.common.datafix.DataFixHandler;
import com.Hileb.moremomostories.common.world.gui.ModGuiElementLoader;
import com.Hileb.moremomostories.common.init.ModOreDic;
import com.Hileb.moremomostories.common.init.ModRecipes;
import com.Hileb.moremomostories.common.init.ModSpawn;
import com.Hileb.moremomostories.common.init.RegistryHandler;
import com.Hileb.moremomostories.common.world.item.myItems.ItemColorHandler;
import com.Hileb.moremomostories.client.keys.ClientKey;
import com.Hileb.moremomostories.client.keys.KeyboardManager;
import com.Hileb.moremomostories.common.meta.MetaUtil;
import com.Hileb.moremomostories.common.network.NetworkHandler;
import com.Hileb.moremomostories.mods.slashblade.SlashBladeUtil;
import com.Hileb.moremomostories.proxy.ProxyBase;
import com.Hileb.moremomostories.common.util.Reference;
import com.Hileb.moremomostories.common.util.named.NameTagHandler;
import com.Hileb.moremomostories.common.util.named.NameTags;
import mods.flammpfeil.slashblade.SlashBlade;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.Hileb.moremomostories.common.init.RegistryHandler.initRegistries;

/*
为了加腐烂的食物
请RecipePutrid.PutridItems.add(Item.getByNameOrId();
 */
//To let the player be a traveling god who plays yin-yang magic.

@Mod(modid = MoreMoMoSrories.MODID, name = MoreMoMoSrories.NAME, version = MoreMoMoSrories.VERSION,dependencies="required-after:momostories;required-after:mixinbooter@[4.2,);after:idealland;after:forestry;after:manametalmod;after:calculator;after:ic2;after:flammpfeil.slashblade")//dependencies = "required-after:Forge@[14.23.5.2705,)"
public class MoreMoMoSrories {
    public static final String MODID = "moremomostories";
    public static final String NAME = "More MoMoStories";
    public static final String VERSION = "1.2.0.11";
    public static final int BUILD_VERSION=11;
    public static Logger LOGGER = LogManager.getLogger(MODID);

    @Mod.Instance
    public static MoreMoMoSrories instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static ProxyBase proxy;

    public MoreMoMoSrories(){
        DataFixHandler.register();
    }
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        RegistryHandler.preInitRegistries(event);
        CapabilityNameTag.register();

        if (Loader.isModLoaded(SlashBlade.modid)){
            SlashBlade.InitEventBus.register(new SlashBladeUtil());
        }
    }

    @EventHandler
    public static void Init(FMLInitializationEvent event) {
        ModRecipes.init();
        initRegistries(event);
        ModOreDic.init();
        new ModGuiElementLoader();
        if (!proxy.isServer())
        {
            clientInit();
            KeyboardManager.init();
        }
        NetworkHandler.init();

		LOGGER.info("{} has finished its initializations", MODID);

	}
	@SideOnly(Side.CLIENT)
    public static void clientInit(){
        ItemColorHandler.init();
        ClientKey.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
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
            MoreMoMoSrories.LOGGER.info("Bow to Idealland.");
        }
        else {
            MoreMoMoSrories.LOGGER.info("Made with Idealland Framework.");
        }
    }
}