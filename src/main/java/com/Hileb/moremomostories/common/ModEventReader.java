package com.Hileb.moremomostories.common;

import com.Hileb.add_potion.event.APCraftEvent;
import com.Hileb.add_potion.util.potion.APotion;
import com.Hileb.add_potion.util.potion.PotionUtil;
import com.Hileb.moremomostories.IdlFramework;
import com.Hileb.moremomostories.advancements.Advancementkeys;
import com.Hileb.moremomostories.advancements.ModAdvancementsInit;
import com.Hileb.moremomostories.entity.entity.living.EntityGoldenGuide;
import com.Hileb.moremomostories.entity.entity.living.boss.EntityGoldenGuideBoss;
import com.Hileb.moremomostories.meta.MetaUtil;
import com.Hileb.moremomostories.worldgen.WorldGenHelper;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import java.util.List;

@Mod.EventBusSubscriber(modid= IdlFramework.MODID)
public class ModEventReader {

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event){
        if (!event.player.world.isRemote){
            if (MetaUtil.isLoaded_SlashBlade || MetaUtil.isLoaded_MagicCircle || MetaUtil.isLoaded_AddPotion){
                ModAdvancementsInit.giveAdvancement(event.player, Advancementkeys.AD_OMOD_ROOT);
            }
        }
    }
    @Optional.Method(modid= com.Hileb.add_potion.IdlFramework.MODID)
    @SubscribeEvent
    public static void onPlayerAddPotion(APCraftEvent.Pre event){
        if (event.player instanceof EntityPlayerMP){

            ItemStack stack=event.potionStack;
            List<APotion> effects=PotionUtil.getAllEffect(stack);
            for(APotion aPotion:effects){
                if (aPotion.potion== MobEffects.POISON){
                    IdlFramework.LogWarning(String.valueOf(ModAdvancementsInit.giveAdvancement(event.player, Advancementkeys.AD_OMOD_AP)));

                }
            }
        }
    }
    @SubscribeEvent
    public static void onBuildPumpkin(BlockEvent.PlaceEvent event){
        World world=event.getWorld();
        if (!world.isRemote){
            BlockPos pos=event.getPos();
            if (world.getBlockState(pos.down()).getBlock()== Blocks.GOLD_BLOCK){
                if (world.getBlockState(pos.down(2)).getBlock()== Blocks.GOLD_BLOCK){
                    if (world.getBlockState(pos).getBlock()==Blocks.PUMPKIN){
                        BlockPos pos1=pos.down(2);
                        EntityGoldenGuide entityGoldenGuide=new EntityGoldenGuide(world);
                        entityGoldenGuide.setPosition(pos1.getX(),pos1.getY(),pos1.getZ());
                        world.spawnEntity(entityGoldenGuide);

                        world.setBlockState(pos,Blocks.AIR.getDefaultState(),3);
                        world.setBlockState(pos.down(),Blocks.AIR.getDefaultState(),3);
                        world.setBlockState(pos.down(2),Blocks.AIR.getDefaultState(),3);
                    }
                    if (world.getBlockState(pos).getBlock()==Blocks.SKULL){
                        BlockPos pos1=pos.down(2);
                        EntityGoldenGuideBoss entityGoldenGuide=new EntityGoldenGuideBoss(world);
                        entityGoldenGuide.setPosition(pos1.getX(),pos1.getY(),pos1.getZ());
                        world.spawnEntity(entityGoldenGuide);

                        world.setBlockState(pos,Blocks.AIR.getDefaultState(),3);
                        world.setBlockState(pos.down(),Blocks.AIR.getDefaultState(),3);
                        world.setBlockState(pos.down(2),Blocks.AIR.getDefaultState(),3);
                    }
                }
            }
        }
    }
}
