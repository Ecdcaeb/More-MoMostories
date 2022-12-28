package com.Hileb.moremomostories.otherMods.MagicCircle;

import com.Hileb.moremomostories.IdlFramework;
import com.Hileb.moremomostories.meta.MetaUtil;
import net.minecraft.tileentity.TileEntity;
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
                        for (int i=0;i<MagicCirclesForMod.sixStars.size();i++){
                            if (MagicCirclesForMod.sixStars.get(i).compare(MagicCircleSixstars.getFromSixstars(block)))MagicCirclesForMod.sixStars.get(i).doCircle(block);
                        }
                    }
                    if (tile instanceof net.mcreator.magiccircle.block.BlockDiagramoftheuniverse.TileEntityCustom){
                        net.mcreator.magiccircle.block.BlockDiagramoftheuniverse.TileEntityCustom block=(net.mcreator.magiccircle.block.BlockDiagramoftheuniverse.TileEntityCustom)tile;
                        for (int i=0;i<MagicCirclesForMod.diagramoftheuniverse.size();i++){
                            if (MagicCirclesForMod.diagramoftheuniverse.get(i).compare(MagicCircleDiagramoftheuniverse.getFromDiagramoftheuniverse(block)))MagicCirclesForMod.sixStars.get(i).doCircle(block);
                        }

                    }
                    if (tile instanceof net.mcreator.magiccircle.block.BlockPurplediagramoftheuniverse.TileEntityCustom){
                        net.mcreator.magiccircle.block.BlockPurplediagramoftheuniverse.TileEntityCustom block=(net.mcreator.magiccircle.block.BlockPurplediagramoftheuniverse.TileEntityCustom)tile;

                        for (int i=0;i<MagicCirclesForMod.purplediagramoftheuniverse.size();i++){
                            if (MagicCirclesForMod.purplediagramoftheuniverse.get(i).compare(MagicCirclePurplediagramoftheuniverse.getFromPurplediagramoftheuniverse(block)))MagicCirclesForMod.sixStars.get(i).doCircle(block);
                        }
                    }
                }
            }
        }
    }
}
