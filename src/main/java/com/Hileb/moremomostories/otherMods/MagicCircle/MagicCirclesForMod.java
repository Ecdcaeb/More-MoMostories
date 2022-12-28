package com.Hileb.moremomostories.otherMods.MagicCircle;

import com.Hileb.moremomostories.item.myItems.ItemXe;
import com.gq2529.momostories.events.DamageSource1;
import com.gq2529.momostories.item.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

public class MagicCirclesForMod {
    public static List<MagicCircleSixstars> sixStars= new ArrayList<>();
    public static List<MagicCirclePurplediagramoftheuniverse> purplediagramoftheuniverse= new ArrayList<>();
    public static List<MagicCircleDiagramoftheuniverse> diagramoftheuniverse= new ArrayList<>();
    public static final MagicCircleSixstars Magic_Circle_of_the_fruit_of_the_flowing_years =new MagicCircleSixstars(
            ModItems.THE_FRUIT_OF_THE_FLOWING_YEARS
            ,ModItems.ID_SANDPAPER,ModItems.ID_SAND
            ,ModItems.ID_SAND,ModItems.ID_SANDPAPER
            ,ModItems.THE_FRUIT_OF_THE_FLOWING_YEARS){
        @Override
        public void doCircle(TileEntityLockableLoot block) {
            //TileEntityLockableLoot block=(TileEntityLockableLoot)world.getTileEntity(pos);
            for (EntityPlayer player : block.getWorld().getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(new BlockPos(block.getPos().getX() - 3, block.getPos().getY() - 3, block.getPos().getZ() - 3), new BlockPos(block.getPos().getX() + 3, block.getPos().getY() + 3, block.getPos().getZ() + 3)))) {
                if (!player.isSneaking()) {
                    player.attackEntityFrom(DamageSource1.TIME, 5);
                    //岁月阵
                }
            }
        }
    };

    public static final MagicCircleSixstars Magic_Circle_of_Xe =new MagicCircleSixstars(
            com.Hileb.moremomostories.item.ModItems.ITEM_XE
            ,com.Hileb.moremomostories.item.ModItems.ITEM_XE,com.Hileb.moremomostories.item.ModItems.ITEM_XE
            ,com.Hileb.moremomostories.item.ModItems.ITEM_XE,com.Hileb.moremomostories.item.ModItems.ITEM_XE
            ,com.Hileb.moremomostories.item.ModItems.ITEM_XE){
        @Override
        public void doCircle(TileEntityLockableLoot block) {
            //TileEntityLockableLoot block=(TileEntityLockableLoot)world.getTileEntity(pos);
            //氙石阵：红色风暴
            if (ItemXe.isRed(block.getStackInSlot(0))){
                if (ItemXe.isRed(block.getStackInSlot(1))){
                    if (ItemXe.isRed(block.getStackInSlot(2))){
                        if (ItemXe.isRed(block.getStackInSlot(3))){
                            if (ItemXe.isRed(block.getStackInSlot(4))){
                                if (ItemXe.isRed(block.getStackInSlot(5))){
                                    for (EntityPlayer player : block.getWorld().getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(new BlockPos(block.getPos().getX() - 3, block.getPos().getY() - 3, block.getPos().getZ() - 3), new BlockPos(block.getPos().getX() + 3, block.getPos().getY() + 3, block.getPos().getZ() + 3)))) {
                                        if (!player.isSneaking()) {
                                            player.heal(1);
                                            player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH,20,3));
                                            //红色风暴
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            //氙石阵：蓝色妖姬
            if (ItemXe.isBlue(block.getStackInSlot(0))){
                if (ItemXe.isBlue(block.getStackInSlot(1))){
                    if (ItemXe.isBlue(block.getStackInSlot(2))){
                        if (ItemXe.isBlue(block.getStackInSlot(3))){
                            if (ItemXe.isBlue(block.getStackInSlot(4))){
                                if (ItemXe.isBlue(block.getStackInSlot(5))){
                                    for (EntityPlayer player : block.getWorld().getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(new BlockPos(block.getPos().getX() - 3, block.getPos().getY() - 3, block.getPos().getZ() - 3), new BlockPos(block.getPos().getX() + 3, block.getPos().getY() + 3, block.getPos().getZ() + 3)))) {
                                        if (!player.isSneaking()) {
                                            player.addPotionEffect(new PotionEffect(MobEffects.SPEED,20,5));
                                            //蓝色妖姬
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    };

//    public static final MagicCircleSixstars Big_SixStars=new MagicCircleSixstars(
//
//    )

}
