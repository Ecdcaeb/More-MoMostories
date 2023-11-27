package mods.Hileb.moremomostories;

import mods.Hileb.moremomostories.common.capabilities.nametag.CapabilityNameTag;
import mods.Hileb.moremomostories.common.datafix.DataFixHandler;
import mods.Hileb.moremomostories.common.world.gui.ModGuiElementLoader;
import mods.Hileb.moremomostories.common.init.ModOreDic;
import mods.Hileb.moremomostories.common.init.ModRecipes;
import mods.Hileb.moremomostories.common.init.ModSpawn;
import mods.Hileb.moremomostories.common.init.RegistryHandler;
import mods.Hileb.moremomostories.common.world.item.myItems.ItemColorHandler;
import mods.Hileb.moremomostories.modplugins.ModLoadingPlugin;
import mods.Hileb.moremomostories.common.network.NetworkHandler;
import mods.Hileb.moremomostories.proxy.ProxyBase;
import mods.Hileb.moremomostories.common.util.Reference;
import mods.Hileb.moremomostories.common.capabilities.nametag.named.NameTagHandler;
import mods.Hileb.moremomostories.common.capabilities.nametag.named.NameTags;
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

        ModLoadingPlugin.updateModState();
        ModLoadingPlugin.preInit();
    }

    @EventHandler
    public static void Init(FMLInitializationEvent event) {
        ModRecipes.init();
        RegistryHandler.initRegistries(event);
        ModOreDic.init();
        new ModGuiElementLoader();
        if (!proxy.isServer())
        {
            clientInit();
        }
        NetworkHandler.init();

		LOGGER.info("{} has finished its initializations", MODID);

	}
	@SideOnly(Side.CLIENT)
    public static void clientInit(){
        ItemColorHandler.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        ModSpawn.registerSpawnList();
        TrashTalking();

        RegistryHandler.postInitReg();
        NameTags.register();
        NameTagHandler.post();
        ModLoadingPlugin.updateModState();
        ModLoadingPlugin.postInit();
    }

    @EventHandler
    public static void serverInit(FMLServerStartingEvent event) {
        RegistryHandler.serverRegistries(event);
    }


    private void TrashTalking() {
        if (ModLoadingPlugin.isIDLLoaded)
        {
            MoreMoMoSrories.LOGGER.info("Bow to Idealland.");
        }
        else {
            MoreMoMoSrories.LOGGER.info("Made with Idealland Framework.");
        }
    }
}