package com.Hileb.moremomostories.otherMods;

import com.Hileb.moremomostories.IdlFramework;
import com.Hileb.moremomostories.meta.MetaUtil;
import com.gq2529.momostories.events.DamageSource1;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod.EventBusSubscriber(modid = IdlFramework.MODID)
public class MagicCircle {
    @SubscribeEvent
    public static void onUpdate(TickEvent.WorldTickEvent event){
        World world=event.world;
        if (MetaUtil.isLoaded_MagicCircle){
            if (!world.isRemote){
                for(TileEntity tile:world.loadedTileEntityList){
                    if (tile instanceof net.mcreator.magiccircle.block.BlockSixstars.TileEntityCustom){
                        net.mcreator.magiccircle.block.BlockSixstars.TileEntityCustom block=(net.mcreator.magiccircle.block.BlockSixstars.TileEntityCustom)tile;
                        //六星阵
                        //  0
                        //1   2
                        //3   4
                        //  5


                        if(MagicCirclesForMod.Magic_Circle_of_the_fruit_of_the_flowing_years.compare(MagicCircleSixstars.getFromSixstars(block))) {
                            //IdlFramework.LogWarning("this!!!");
                            for (EntityPlayer player : world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(new BlockPos(block.getPos().getX() - 3, block.getPos().getY() - 3, block.getPos().getZ() - 3), new BlockPos(block.getPos().getX() + 3, block.getPos().getY() + 3, block.getPos().getZ() + 3)))) {
                                if (!player.isSneaking()) {
                                    player.attackEntityFrom(DamageSource1.TIME, 5);
                                    //岁月阵
                                }
                            }
                        }
                    }
                    if (tile instanceof net.mcreator.magiccircle.block.BlockDiagramoftheuniverse.TileEntityCustom){
                        net.mcreator.magiccircle.block.BlockDiagramoftheuniverse.TileEntityCustom block=(net.mcreator.magiccircle.block.BlockDiagramoftheuniverse.TileEntityCustom)tile;
                    }
                    if (tile instanceof net.mcreator.magiccircle.block.BlockPurplediagramoftheuniverse.TileEntityCustom){
                        net.mcreator.magiccircle.block.BlockPurplediagramoftheuniverse.TileEntityCustom block=(net.mcreator.magiccircle.block.BlockPurplediagramoftheuniverse.TileEntityCustom)tile;
                    }
                }
            }
        }
    }
}
