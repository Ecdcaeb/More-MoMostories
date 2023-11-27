package mods.Hileb.moremomostories.common.events;

import mods.Hileb.moremomostories.common.util.Reference;
import mods.Hileb.moremomostories.common.world.advancements.Advancementkeys;
import mods.Hileb.moremomostories.common.world.advancements.ModAdvancementsInit;
import mods.Hileb.moremomostories.modplugins.ModLoadingPlugin;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class ModStarterEvents {
	  @SubscribeEvent
	  public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {//玩家登入
		  World world = event.player.world;
		  if (!world.isRemote) {
			  EntityPlayer _player_=event.player;
			  if(_player_ instanceof EntityPlayerMP){
			  	EntityPlayerMP playerMP=(EntityPlayerMP)_player_;
			  	if (ModLoadingPlugin.isLoaded_SlashBlade || ModLoadingPlugin.isLoaded_MagicCircle || ModLoadingPlugin.isLoaded_AddPotion){
					ModAdvancementsInit.giveAdvancement(playerMP, Advancementkeys.AD_OMOD_ROOT);
				}
			  }
		  }
	  }
}
