package mods.Hileb.moremomostories.common.world.entity.entity.projectile;

import mods.Hileb.moremomostories.modplugins.ModLoadingPlugin;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityIce extends EntityThrowable {
    protected EntityPlayer throwerBlade=null;
    protected ItemStack blade;
    public EntityIce(World par1World, ItemStack blade, EntityPlayer throwerIn) {
        super(par1World);
        this.blade = blade;
        throwerBlade=throwerIn;
    }
    public EntityIce(World worldIn)
    {
        super(worldIn);
    }

    public EntityIce(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
    }

    public EntityIce(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (!world.isRemote){//击中的效果
            if (result.entityHit!=throwerBlade && result.entityHit!=null){
                if(throwerBlade==null){
                    result.entityHit.attackEntityFrom(DamageSource.OUT_OF_WORLD,1);
                    return;
                }
                if(throwerBlade==null || throwerBlade.world.isRemote || blade.isEmpty()){
                    result.entityHit.attackEntityFrom(DamageSource.causePlayerDamage(throwerBlade),1);
                    return;
                }
                else if (ModLoadingPlugin.isLoaded_SlashBlade && throwerBlade!=null && !blade.isEmpty() && blade.hasTagCompound()){
//                    int a=0;
//                    if (mods.flammpfeil.slashblade.ItemSlashBladeNamed.RepairCount.get(blade.getTagCompound())>throwerBlade.experienceLevel)a=throwerBlade.experienceLevel;
//                    else a=mods.flammpfeil.slashblade.ItemSlashBladeNamed.RepairCount.get(blade.getTagCompound());
//                    result.entityHit.attackEntityFrom(DamageSource.MAGIC,//causePlayerDamage(throwerBlade),
//                            1f);
                    if (result.entityHit!=null){//主要
                        int a=0;
                        if (mods.flammpfeil.slashblade.ItemSlashBladeNamed.RepairCount.get(blade.getTagCompound())>throwerBlade.experienceLevel)a=throwerBlade.experienceLevel;
                        else a=mods.flammpfeil.slashblade.ItemSlashBladeNamed.RepairCount.get(blade.getTagCompound());
                        result.entityHit.attackEntityFrom(DamageSource.causePlayerDamage(throwerBlade),//causePlayerDamage(throwerBlade),
                                (float) a);
                    }
                }
            }
        }
    }

    @Override
    public void onUpdate() {
        this.world.spawnParticle(EnumParticleTypes.FIREWORKS_SPARK, this.posX, this.posY, this.posZ, -this.motionX*0.1f, -this.motionY*0.1f, -this.posZ*0.1f);
        super.onUpdate();
    }
}
