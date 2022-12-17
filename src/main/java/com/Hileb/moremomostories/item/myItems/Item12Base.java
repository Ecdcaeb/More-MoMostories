package com.Hileb.moremomostories.item.myItems;

import com.Hileb.moremomostories.item.ItemBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class Item12Base extends ItemBase {
    public Item12Base(String name){
        super(name);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        if (entity.canBeAttackedWithItem()){
            if (hasSameVec(entity.getLookVec(),player.getLookVec())){
                if (getHowLongItIs(entity.getPosition(),player.getPosition())<=1.5f){
                    entity.attackEntityFrom(DamageSource.causePlayerDamage(player),9999);
                }
            }
        }
        return super.onLeftClickEntity(stack, player, entity);
    }
    double inVec3d(Vec3d v1,Vec3d v2){
        double a=v1.x*v2.x+v1.y+v2.y+v1.z+v2.z;
        double a1= MathHelper.sqrt((v1.x*v1.x)+(v1.y*v1.y)+(v1.z*v1.z));
        double a2=MathHelper.sqrt((v2.x*v2.x)+(v2.y*v2.y)+(v2.z*v2.z));
        double b=a/(a1*a2);
        return Math.acos(b);
    }
    double inVec3dWithDegree(Vec3d v1,Vec3d v2){
        return Math.toDegrees(inVec3d(v1,v2));
    }
    double getHowLongItIs(BlockPos pos1,BlockPos pos2){
        double DOUBLE_PLAYER_ENTITY_X=(pos1.getX())-pos2.getX();
        double DOUBLE_PLAYER_ENTITY_Y=(pos1.getY())-pos2.getY();
        double DOUBLE_PLAYER_ENTITY_Z=(pos1.getZ())-pos2.getZ();
        DOUBLE_PLAYER_ENTITY_X=DOUBLE_PLAYER_ENTITY_X*DOUBLE_PLAYER_ENTITY_X;
        DOUBLE_PLAYER_ENTITY_Y=DOUBLE_PLAYER_ENTITY_Y*DOUBLE_PLAYER_ENTITY_Y;
        DOUBLE_PLAYER_ENTITY_Z=DOUBLE_PLAYER_ENTITY_Z*DOUBLE_PLAYER_ENTITY_Z;
        double DOUBLE_PLAYER_ENTITY_ALL=Math.sqrt((DOUBLE_PLAYER_ENTITY_X+DOUBLE_PLAYER_ENTITY_Y+DOUBLE_PLAYER_ENTITY_Z));
        return DOUBLE_PLAYER_ENTITY_ALL;
    }
    boolean hasSameVec(Vec3d v1,Vec3d v2){
        if (v1.x*v2.x>=0 && v1.y*v2.y>=0 && v1.z*v2.z>=0) return true;
        else return false;
    }
}
