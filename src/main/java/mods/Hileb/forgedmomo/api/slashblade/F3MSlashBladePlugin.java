package mods.Hileb.forgedmomo.api.slashblade;

import mods.Hileb.forgedmomo.announces.ModPlugin;
import mods.Hileb.forgedmomo.core.F3MFMLLoadingHandler;
import mods.Hileb.forgedmomo.core.ForgedMoMoContainer;
import mods.flammpfeil.slashblade.SlashBlade;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/11/18 13:29
 **/
@ModPlugin(modid = SlashBlade.modid,state = LoaderState.INITIALIZATION,method = "init")
public class F3MSlashBladePlugin {
    public static void init(){
        if (Loader.isModLoaded(SlashBlade.modid)){
            for(BladeType bladeType:BladeType.REGISTER){
                bladeType.registerStack();
            }
            for(SpecialAttackType specialAttackType:SpecialAttackType.REGISTER){
                specialAttackType.registerSpecialAttack();
            }
        }
    }
    @ModPlugin(modid = SlashBlade.modid,state = LoaderState.CONSTRUCTING,method = "init")
    public static class RecipeLoading{
        public static void init(){
            F3MFMLLoadingHandler.addToBus(RecipeLoading.class);
        }
        @SubscribeEvent
        public static void registerRecipe(RegistryEvent.Register<IRecipe> evt){
            if (Loader.isModLoaded(SlashBlade.modid)){
                IRecipe recipe=null;
                IForgeRegistry<IRecipe> r = evt.getRegistry();
                for(BladeType bladeType:BladeType.REGISTER){
                    recipe=bladeType.getRecipe();
                    if(recipe!=null){
                        r.register(recipe);
                    }
                }
            }
            MinecraftForge.EVENT_BUS.unregister(RecipeLoading.class);
        }
    }
}
