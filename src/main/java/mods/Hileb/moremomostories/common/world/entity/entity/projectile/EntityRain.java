package mods.Hileb.moremomostories.common.world.entity.entity.projectile;

import mods.Hileb.moremomostories.mods.ModLoadingPlugin;
import net.minecraft.block.BlockFire;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.UUID;

public class EntityRain extends EntityThrowable {
    protected EntityPlayer throwerBlade=null;
    protected ItemStack blade;
    public EntityRain(World par1World,ItemStack blade,EntityPlayer throwerIn) {
        super(par1World);
        this.blade = blade;
        throwerBlade=throwerIn;
    }
    public EntityRain(World worldIn)
    {
        super(worldIn);
    }

    public EntityRain(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
    }

    public EntityRain(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (!world.isRemote){
            if (world.getBlockState(this.getPosition()).getBlock()== Blocks.FIRE)world.setBlockToAir(this.getPosition());
        }
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (!world.isRemote){//击中的效果
            if (result.entityHit!=throwerBlade && result.entityHit!=null){
                if (result.getBlockPos()!=null && result.typeOfHit== RayTraceResult.Type.BLOCK){
                    IBlockState state =world.getBlockState(result.getBlockPos());
                    if (state.getBlock() instanceof BlockFire){
                        world.setBlockToAir(result.getBlockPos());
                    }
                    this.setDead();
                }
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
                        for(AttributeModifier ab:blade.getAttributeModifiers(EntityEquipmentSlot.MAINHAND).get("CB3F55D3-645C-4F38-A497-9C13A33DB5CF")){
                            if (ab.getID().equals(UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF")))a+=ab.getAmount();
                        }
                        if (a!=0)a=a/2;
                        else a=1;
                        result.entityHit.attackEntityFrom(DamageSource.causePlayerDamage(throwerBlade),//causePlayerDamage(throwerBlade),
                                (float) a);
                    }
                }
            }
        }
    }
}
