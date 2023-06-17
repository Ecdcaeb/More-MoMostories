package com.Hileb.moremomostories.entity.entity;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityItemX extends EntityItem {

    public EntityItemX(World worldIn) {
        super(worldIn);
    }
    public EntityItemX(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    public EntityItemX(World worldIn, double x, double y, double z, ItemStack stack) {
        super(worldIn, x, y, z, stack);
    }
    public EntityItemX(EntityItem entityItem) {
        super(entityItem.world,entityItem.posX,entityItem.posY,entityItem.posZ,entityItem.getItem());
        this.motionX=entityItem.motionX;
        this.motionY=entityItem.motionY;
        this.motionZ=entityItem.motionZ;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (this.world.isRemote || this.isDead) return false; //Forge: Fixes MC-53850
        if (this.isEntityInvulnerable(source))
        {
            return false;
        }
        else if (!this.getItem().isEmpty())
        {
            return false;
        }
        else
        {
            this.setDead();
            return true;
        }
    }
}
