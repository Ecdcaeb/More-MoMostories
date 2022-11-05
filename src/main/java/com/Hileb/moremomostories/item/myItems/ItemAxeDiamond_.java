package com.Hileb.moremomostories.item.myItems;

import com.Hileb.moremomostories.Advancements.Advancementkeys;
import com.Hileb.moremomostories.Advancements.ModAdvancementsInit;
import com.Hileb.moremomostories.init.ModCreativeTab;
import com.Hileb.moremomostories.item.ItemPickaxeBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.BlockRedstoneOre;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemAxeDiamond_ extends ItemPickaxeBase {
    public ItemAxeDiamond_(){
        super("diamond_pickaxe", ToolMaterial.DIAMOND, ModCreativeTab.IDL_MISC);
        MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public void inbreakblock(BlockEvent.BreakEvent event){
        World world=event.getWorld();
        if(!world.isRemote){
            if(event.getPlayer()!=null){
                EntityPlayer player=event.getPlayer();
                if(player.getHeldItemMainhand().getItem()==this){
                    ItemStack stack=player.getHeldItemMainhand();
                    Block block=world.getBlockState(event.getPos()).getBlock();
                    if((block instanceof BlockOre) || (block instanceof BlockRedstoneOre) ||(block.getMaterial(event.getState())==Material.IRON) ||(block.getMaterial(event.getState())==Material.GLASS)){
                        event.setCanceled(true);
                        world.setBlockToAir(event.getPos());
                        if (!player.isCreative()){
                            stack.getItem().setDamage(stack, stack.getItem().getDamage(stack) + 1);
                            ModAdvancementsInit.giveAdvancement(player, Advancementkeys.AD_CARD_DOUBLEHIT);
                        }
                    }
                }
            }
        }
    }
//    @SubscribeEvent
//    public void inbreakblock_(BlockEvent.BreakEvent event){
//        World world=event.getWorld();
//        if(!world.isRemote){
//            if(event.getPlayer()!=null){
//                EntityPlayer player=event.getPlayer();
//                if(player.getHeldItemMainhand().isEmpty()){
//                    ItemStack stack=player.getHeldItemMainhand();
//                    Block block=world.getBlockState(event.getPos()).getBlock();
//                    if((block.isWood())){
//                        event.setCanceled(true);
//                        world.setBlockToAir(event.getPos());
////                        if (!player.isCreative()){
////                            stack.getItem().setDamage(stack, stack.getItem().getDamage(stack) + 1);
////                            ModAdvancementsInit.giveAdvancement(player, Advancementkeys.AD_CARD_DOUBLEHIT);
////                        }
//                    }
//                }
//            }
//        }
//    }
}
