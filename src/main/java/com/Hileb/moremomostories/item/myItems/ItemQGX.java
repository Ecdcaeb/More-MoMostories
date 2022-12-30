package com.Hileb.moremomostories.item.myItems;

import com.Hileb.moremomostories.item.ItemArmorBase;
import com.Hileb.moremomostories.item.armorMaterials.ModArmorMaterials;
import com.Hileb.moremomostories.item.armorMaterials.QGXModel5;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.block.BlockDispenser;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Bootstrap;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemQGX extends ItemArmorBase {
    public ItemQGX(String name){
        super(name, ModArmorMaterials.QGXMaterial,1, EntityEquipmentSlot.FEET);
        BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(this, new Bootstrap.BehaviorDispenseOptional()
        {
            protected ItemStack dispenseStack(IBlockSource source, ItemStack stack)
            {
                this.successful = true;
                ItemStack itemstack = ItemArmor.dispenseArmor(source, stack);
                if (itemstack.isEmpty())successful=false;
                return stack;
            }
        });
    }

    @Override
    public void registerModels() {
        super.registerModels();
    }

    @Nullable
    @Override
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
        if(itemStack!=null){
            if (!itemStack.isEmpty()){
                if (itemStack.getItem()==this){
                    QGXModel5 model=new QGXModel5();
                    model.bone_all.showModel=armorSlot.equals(EntityEquipmentSlot.FEET);
                    model.bipedLeftLeg=_default.bipedLeftLeg;
                    model.bipedRightLeg=_default.bipedRightLeg;
                    //model.bipedBody=_default.bipedBody;
                    model.isChild=_default.isChild;
                    model.isSneak=_default.isSneak;
                    model.isRiding = _default.isRiding;
                    model.rightArmPose = _default.rightArmPose;
                    model.leftArmPose = _default.leftArmPose;
                    //
                    model.bone_left.rotationPointZ=_default.bipedRightLeg.rotationPointZ;
                    model.bone_left.rotationPointX=_default.bipedRightLeg.rotationPointX;
                    model.bone_left.rotationPointY=_default.bipedRightLeg.rotationPointY;
                    model.bone_left.rotateAngleX=_default.bipedRightLeg.rotateAngleX;
                    model.bone_left.rotateAngleY=_default.bipedRightLeg.rotateAngleY;
                    model.bone_left.rotateAngleZ=_default.bipedRightLeg.rotateAngleZ;

                    model.bone_right.rotationPointZ=_default.bipedLeftLeg.rotationPointZ;
                    model.bone_right.rotationPointX=_default.bipedLeftLeg.rotationPointX;
                    model.bone_right.rotationPointY=_default.bipedLeftLeg.rotationPointY;
                    model.bone_right.rotateAngleX=_default.bipedLeftLeg.rotateAngleX;
                    model.bone_right.rotateAngleY=_default.bipedLeftLeg.rotateAngleY;
                    model.bone_right.rotateAngleZ=_default.bipedLeftLeg.rotateAngleZ;

                    //IdlFramework.LogWarning("model qgx has been returned");
                    return model;
                }
            }
        }
        return null;
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return "moremomostories:textures/entity/goldenguide.png";
    }
    public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot equipmentSlot, ItemStack stack)
    {
        if (ignoreVanillaSystem)
        {
            return HashMultimap.<String, AttributeModifier>create();
        }else {
            return super.getAttributeModifiers(equipmentSlot, stack);
        }
    }
    public AxisAlignedBB getFace(EntityLivingBase entityLivingBase){
        Vec3d vec3d=entityLivingBase.getLookVec();
        double x=vec3d.x>=0?0.5:-0.5;
        double z=vec3d.z>=0?0.5:-0.5;
        AxisAlignedBB aabb=new AxisAlignedBB(new BlockPos(entityLivingBase.posX,entityLivingBase.posY-1,entityLivingBase.posZ),new BlockPos(entityLivingBase.posX+x,entityLivingBase.posY+1f , entityLivingBase.posZ+z));
        return aabb;
    }
    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
        if (!worldIn.isRemote){
            EntityPlayer player=(EntityPlayer)entityIn;
            List<EntityLivingBase> e=worldIn.getEntitiesWithinAABB(EntityLivingBase.class,getFace(player));
            for(EntityLivingBase c:e){
                if (c!=entityIn){
                    c.attackEntityFrom(DamageSource.causeMobDamage(c),0.1f);
                    if (c instanceof EntityPlayer){
                        c.sendMessage(new TextComponentTranslation("say.player.hileb.qgx.say",entityIn.getDisplayName()));
                    }
                }
            }
        }
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {

    }
}