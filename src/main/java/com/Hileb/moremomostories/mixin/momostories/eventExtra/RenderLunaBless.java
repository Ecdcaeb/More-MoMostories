package com.Hileb.moremomostories.mixin.momostories.eventExtra;

import com.Hileb.moremomostories.MoreMoMoSrories;
import com.Hileb.moremomostories.common.util.helper.Finder;
import com.gq2529.momostories.MoMoFramework;
import com.gq2529.momostories.item.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/8/29 15:56
 **/
@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(value = Side.CLIENT,modid= MoMoFramework.MODID)
public class RenderLunaBless {
    @SubscribeEvent
    public static void onPlayerRender(RenderPlayerEvent.Pre event){
        MoreMoMoSrories.LOGGER.error("a2");
        EntityPlayer player=event.getEntityPlayer();
        if (player!=null){
            final long time = player.world.getWorldTime() + 24000L;
            boolean flagNight = (time % 24000L > 13850L && time % 24000L < 23000L);
            if (flagNight){
                MoreMoMoSrories.LOGGER.error("aa");
                Finder.findPlayerInventory(player,(stack)->stack.getItem()==ModItems.LUNA_BLESSING,(player1, stack) -> event.setCanceled(true));
            }
        }
    }
}
