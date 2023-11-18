package mods.Hileb.moremomostories.mods.slashblade;


import mods.Hileb.forgedmomo.announces.ModPlugin;
import mods.Hileb.forgedmomo.api.slashblade.F3MSlashBladeSetupEvent;
import mods.Hileb.forgedmomo.core.F3MFMLLoadingHandler;
import mods.flammpfeil.slashblade.SlashBlade;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@ModPlugin(state = LoaderState.CONSTRUCTING,modid = SlashBlade.modid,method = "init")
public class SlashBladeUtil {
    public static void init(){
        F3MFMLLoadingHandler.addToBus(SlashBladeUtil.class);
    }
    @SubscribeEvent
    public static void onLoadInit(F3MSlashBladeSetupEvent event){
        ModBlades.init();
        ModSpecialAttack.init();
        MinecraftForge.EVENT_BUS.unregister(SlashBladeUtil.class);
    }
}
