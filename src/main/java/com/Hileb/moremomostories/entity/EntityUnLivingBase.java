package com.Hileb.moremomostories.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHandSide;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class EntityUnLivingBase extends EntityLivingBase {
    private int fire;
    public EntityUnLivingBase(World world){
        super(world);
        fire=-1;
    }

    @Override
    public EnumHandSide getPrimaryHand() {
        return null;
    }

    @Override
    public void setItemStackToSlot(EntityEquipmentSlot slotIn, ItemStack stack) {

    }

    @Override
    public ItemStack getItemStackFromSlot(EntityEquipmentSlot slotIn) {
        return null;
    }

    @Override
    public Iterable<ItemStack> getArmorInventoryList() {
        return null;
    }

    @Override
    public boolean attackable() {
        return false;
    }

    @Override
    public void onUpdate() {
        this.world.profiler.startSection("entityBaseTick");

        if (this.isRiding() && this.getRidingEntity().isDead)
        {
            this.dismountRidingEntity();
        }

        if (this.rideCooldown > 0)
        {
            --this.rideCooldown;
        }

        this.prevDistanceWalkedModified = this.distanceWalkedModified;
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.prevRotationPitch = this.rotationPitch;
        this.prevRotationYaw = this.rotationYaw;

        if (!this.world.isRemote && this.world instanceof WorldServer)
        {
            this.world.profiler.startSection("portal");

            if (this.inPortal)
            {
                MinecraftServer minecraftserver = this.world.getMinecraftServer();

                if (minecraftserver.getAllowNether())
                {
                    if (!this.isRiding())
                    {
                        int i = this.getMaxInPortalTime();

                        if (this.portalCounter++ >= i)
                        {
                            this.portalCounter = i;
                            this.timeUntilPortal = this.getPortalCooldown();
                            int j;

                            if (this.world.provider.getDimensionType().getId() == -1)
                            {
                                j = 0;
                            }
                            else
                            {
                                j = -1;
                            }

                            this.changeDimension(j);
                        }
                    }

                    this.inPortal = false;
                }
            }
            else
            {
                if (this.portalCounter > 0)
                {
                    this.portalCounter -= 4;
                }

                if (this.portalCounter < 0)
                {
                    this.portalCounter = 0;
                }
            }

            this.decrementTimeUntilPortal();
            this.world.profiler.endSection();
        }

        this.spawnRunningParticles();
        this.handleWaterMovement();

        if (this.world.isRemote)
        {
            this.extinguish();
        }
        else if (fire > 0)
        {
            if (this.isImmuneToFire)
            {
                fire= 4;

                if (fire< 0)
                {
                    this.extinguish();
                }
            }
            else
            {
                if (this.fire % 20 == 0)
                {
                    this.attackEntityFrom(DamageSource.ON_FIRE, 1.0F);
                }

                --this.fire;
            }
        }

        if (this.isInLava())
        {
            this.setOnFireFromLava();
            this.fallDistance *= 0.5F;
        }

        if (this.posY < -64.0D)
        {
            this.outOfWorld();
        }

        if (!this.world.isRemote)
        {
            this.setFlag(0, this.fire > 0);
        }

        this.firstUpdate = false;
        this.world.profiler.endSection();
    }

    @Override
    protected void onDeathUpdate() {

    }

    @Override
    protected boolean canDropLoot() {
        return false;
    }

    @Override
    protected int decreaseAirSupply(int air) {
        return 1;
    }
    protected void updatePotionEffects(){}
    protected void updatePotionMetadata(){}

}
