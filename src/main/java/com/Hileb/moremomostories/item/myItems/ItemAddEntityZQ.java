package com.Hileb.moremomostories.item.myItems;

import com.Hileb.moremomostories.Advancements.Advancementkeys;
import com.Hileb.moremomostories.Advancements.ModAdvancementsInit;
import com.Hileb.moremomostories.entity.EntityZQ;
import com.Hileb.moremomostories.item.ItemBase;
import com.Hileb.moremomostories.util.CommonFunctions;
import com.Hileb.moremomostories.util.MoMo.MoMoCards;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemAddEntityZQ extends ItemBase {
    public ItemAddEntityZQ(String name){
        super(name, 1);
        CommonFunctions.addToEventBus(this);
        MoMoCards.registerCard(this);
    }

    @SubscribeEvent
    public  void PlayerRightClickBlockEvent(PlayerInteractEvent.RightClickBlock event){
        World world=event.getWorld();
        if(!world.isRemote){
            if(event.getEntityPlayer().getHeldItemMainhand().getItem() == this) {
                //event.getWorld().addWeatherEffect(new EntityLightningBolt(event.getWorld(),event.getPos().getX(),event.getPos().getY(),event.getPos().getZ(),true));
                EntityZQ sakura=new EntityZQ(world);
                sakura.setPosition(event.getPos().getX(),event.getPos().getY()+1,event.getPos().getZ());
                event.getWorld().spawnEntity(sakura);
                sakura.setVelocity(event.getEntityPlayer().getLookVec().x,0,event.getEntityPlayer().getLookVec().z);
                //return super.onItemUse(event.getEntityPlayer(),event.getWorld(),event.getPos(), EnumHand.MAIN_HAND,);
                event.getItemStack().setCount(0);
                ModAdvancementsInit.giveAdvancement(event.getEntityPlayer(), Advancementkeys.AD_FIRETIME);
                //ModAdvancementsInit.giveAdvancement(event.getEntityPlayer(), Advancementkeys.AD_HELLOSAKURA);
            }
        }
    }

}
