package com.Hileb.moremomostories.common.events;

import com.Hileb.moremomostories.common.world.advancements.Advancementkeys;
import com.Hileb.moremomostories.common.world.advancements.ModAdvancementsInit;
import com.Hileb.moremomostories.common.meta.MetaUtil;
import com.Hileb.moremomostories.common.util.IDLNBT;
import com.Hileb.moremomostories.common.util.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import static com.Hileb.moremomostories.common.util.IDLNBT.getPlayerIdeallandIntSafe;
import static com.Hileb.moremomostories.common.util.NBTStrDef.IDLNBTDef.CUR_STARTER_KIT_VERSION;
import static com.Hileb.moremomostories.common.util.NBTStrDef.IDLNBTDef.STARTER_KIT_VERSION_TAG;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class ModStarterEvents {
	  @SubscribeEvent
	  public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {//玩家登入
		  EntityPlayer player = event.player;
		  //MoreMoMoSrories.Log(getPlyrIdlTagSafe(player).toString());
		  int lastStarterVer = getPlayerIdeallandIntSafe(player, STARTER_KIT_VERSION_TAG);
		  if(lastStarterVer < CUR_STARTER_KIT_VERSION) {
			  IDLNBT.setPlayerIdeallandTagSafe(player, STARTER_KIT_VERSION_TAG, CUR_STARTER_KIT_VERSION);
			  //第一次登入。
		  }
		  World world = event.player.world;
		  if (!world.isRemote) {
			  EntityPlayer _player_=event.player;
			  if(_player_ instanceof EntityPlayerMP){
			  	EntityPlayerMP playerMP=(EntityPlayerMP)_player_;
			  	if (MetaUtil.isLoaded_SlashBlade || MetaUtil.isLoaded_MagicCircle || MetaUtil.isLoaded_AddPotion){
					ModAdvancementsInit.giveAdvancement(playerMP, Advancementkeys.AD_OMOD_ROOT);
				}
			  }
		  }
	  }
}
