package com.Hileb.moremomostories.events;

import com.Hileb.moremomostories.advancements.Advancementkeys;
import com.Hileb.moremomostories.advancements.ModAdvancementsInit;
import com.Hileb.moremomostories.meta.MetaUtil;
import com.Hileb.moremomostories.util.IDLNBT;
import com.Hileb.moremomostories.util.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import static com.Hileb.moremomostories.util.IDLNBT.getPlayerIdeallandIntSafe;
import static com.Hileb.moremomostories.util.NBTStrDef.IDLNBTDef.CUR_STARTER_KIT_VERSION;
import static com.Hileb.moremomostories.util.NBTStrDef.IDLNBTDef.STARTER_KIT_VERSION_TAG;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class ModStarterEvents {
	  @SubscribeEvent
	  public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {//玩家登入
		  EntityPlayer player = event.player;
		  //IdlFramework.Log(getPlyrIdlTagSafe(player).toString());
		  int lastStarterVer = getPlayerIdeallandIntSafe(player, STARTER_KIT_VERSION_TAG);
		  if(lastStarterVer < CUR_STARTER_KIT_VERSION) {
			  IDLNBT.setPlayerIdeallandTagSafe(player, STARTER_KIT_VERSION_TAG, CUR_STARTER_KIT_VERSION);
			  //第一次登入。
//
//			  ItemStack scry = new ItemStack(Items.QUARTZ);
//			  player.addItemStackToInventory(scry);
//
//			  if (player instanceof EntityPlayerMP) {
//				  CommonFunctions.SendMsgToPlayerStyled((EntityPlayerMP)player, "moremomostories.msg.starter_kit_given", TextFormatting.AQUA);
//			  }
//			  IdlFramework.Log(String.format("Given starter items to player %s, ver %d", player.getDisplayNameString(), CUR_STARTER_KIT_VERSION));
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
