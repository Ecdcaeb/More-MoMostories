package com.Hileb.moremomostories.common.world.entity.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityFire extends EntityThrowable {
    protected EntityPlayer throwerBlade=null;
    protected ItemStack blade;
    public EntityFire(World par1World, ItemStack blade, EntityPlayer throwerIn) {
        super(par1World);
        this.blade = blade;
        throwerBlade=throwerIn;

    }
    public EntityFire(World worldIn)
    {
        super(worldIn);
    }

    public EntityFire(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
    }

    public EntityFire(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (!world.isRemote){//击中的效果
            if (result.entityHit!=null){//entity
                if (throwerBlade!=null){
                    result.entityHit.attackEntityFrom(DamageSource.causePlayerDamage(throwerBlade),//causePlayerDamage(throwerBlade),
                            4);

                }
                else {
                    result.entityHit.attackEntityFrom(DamageSource.ON_FIRE,//causePlayerDamage(throwerBlade),
                            4);

                }
            }
            else {//block
                onItemUseSetUpAFire(world,result.getBlockPos(),result.sideHit);
            }
        }
    }
    public void onItemUseSetUpAFire( World worldIn, BlockPos pos, EnumFacing facing)
    {
        pos = pos.offset(facing);

            if (worldIn.isAirBlock(pos))
            {
                //worldIn.playSound(player, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
                worldIn.setBlockState(pos, Blocks.FIRE.getDefaultState(), 11);
            }

    }

    @Override
    public void onUpdate() {
        this.world.spawnParticle(EnumParticleTypes.FIREWORKS_SPARK, this.posX, this.posY, this.posZ, this.rand.nextGaussian() * 0.05D, -this.motionY * 0.5D, this.rand.nextGaussian() * 0.05D);
        super.onUpdate();
    }
}
