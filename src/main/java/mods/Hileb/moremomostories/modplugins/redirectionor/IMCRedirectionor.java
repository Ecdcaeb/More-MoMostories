package mods.Hileb.moremomostories.modplugins.redirectionor;

import mods.Hileb.forgedmomo.announces.ModPlugin;
import mods.Hileb.moremomostories.MoreMoMoSrories;
import net.minecraftforge.fml.common.LoaderState;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/11/18 15:36
 **/
@ModPlugin(modid = "redirectionor",state = LoaderState.PREINITIALIZATION,method = "init")
public class IMCRedirectionor {
    public static void init(){
        MoreMoMoSrories.LOGGER.info("Hello redirectionor!");
    }
}
