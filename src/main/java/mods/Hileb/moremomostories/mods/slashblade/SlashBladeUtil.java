package mods.Hileb.moremomostories.mods.slashblade;


import mods.Hileb.forgedmomo.api.slashblade.F3MSlashBladeSetupEvent;
import mods.Hileb.moremomostories.mods.slashblade.blade.ModBlades;
import mods.Hileb.moremomostories.mods.slashblade.specialattack.ModSpecialAttack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class SlashBladeUtil {
    @SubscribeEvent
    public static void onLoadInit(F3MSlashBladeSetupEvent event){
        ModBlades.init();
        ModSpecialAttack.init();
        MinecraftForge.EVENT_BUS.unregister(SlashBladeUtil.class);
    }
}
