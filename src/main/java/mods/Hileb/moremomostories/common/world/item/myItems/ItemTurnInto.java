package mods.Hileb.moremomostories.common.world.item.myItems;

import mods.Hileb.moremomostories.common.world.command.ModCommands;
import mods.Hileb.moremomostories.common.world.item.ItemBase;
import mods.Hileb.moremomostories.common.world.item.ModItems;
import mods.Hileb.forgedmomo.api.momostories.MoMoCards;
import com.gq2529.momostories.potion.effect.ModPotions;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemTurnInto extends ItemBase {
    public ItemTurnInto(String name){//化神奇为腐朽。
        super(name);//new ItemInformationAdder("item.item_turn_into.tip")
        setDesc("item.item_turn_into.tip","item.item_turn_into.tip");
        MinecraftForge.EVENT_BUS.register(this);
        MoMoCards.registerCard(this,MoMoCards.CardType.HILEB);
    }

    @SubscribeEvent
    public void onUse(PlayerInteractEvent.RightClickItem event){
        World world=event.getWorld();
        if (!world.isRemote){
            if (event.getEntityPlayer()!=null){
                if (event.getEntityPlayer().getHeldItemOffhand().getItem()==this){
                    EntityPlayer player=event.getEntityPlayer();
                    if (!player.getHeldItemMainhand().isEmpty()){
                        if (player.getHeldItemMainhand().getItem() instanceof ItemFood && !player.getHeldItemMainhand().isEmpty()){
                            player.getHeldItemMainhand().shrink(1);
                            ModCommands.give(player,new ItemStack(ModItems.ITEM_PUTRID));
                        }
                    }
                }
                else if (event.getEntityPlayer().getHeldItemMainhand().getItem()==ModItems.ITEM_NO_CAN_HIT_IT){
                        event.getEntityPlayer().addPotionEffect(new PotionEffect(ModPotions.HEAVY_ARMOR,20*60,10000));
                        event.getEntityPlayer().addPotionEffect(new PotionEffect(MobEffects.HEALTH_BOOST,20*60,2));
                        event.getItemStack().setCount(event.getItemStack().getCount()-1);
                }
                else if (event.getEntityPlayer().getHeldItemMainhand().getItem()==ModItems.ITEM_CARD_FIVE){

//                    EntityUtil.resetRotionPlayer(event.getEntityPlayer(),event.getEntityPlayer().rotationYaw+Math.PI/2,event.getEntityPlayer().rotationPitch+=Math.PI/2);
//                    //event.getEntityPlayer().addPotionEffect(new PotionEffect(com.Hileb.moremomostories.common.world.potion.ModPotions.BAKIN,100));
                }
            }
        }
//        if(world.isRemote){
//            if (event.getEntityPlayer().getHeldItemMainhand().getItem()==ModItems.ITEM_CARD_FIVE){
//
////                player.prevRotationYaw=yaw;
////                    EntityUtil.resetRotionPlayer(event.getEntityPlayer(),event.getEntityPlayer().rotationYaw+Math.PI/2,event.getEntityPlayer().rotationPitch+=Math.PI/2);
////                    //event.getEntityPlayer().addPotionEffect(new PotionEffect(com.Hileb.moremomostories.common.world.potion.ModPotions.BAKIN,100));
//            }
//        }
    }
//    @SubscribeEvent
//    public void oncraft(PlayerEvent.ItemCraftedEvent event){
//        World world=event.player.world;
//        if (!world.isRemote){
//
//        }
//    }
}
