package com.Hileb.moremomostories.item.myItems;

import com.Hileb.moremomostories.item.ItemArmorBase;
import com.Hileb.moremomostories.item.armorMaterials.ModArmorMaterials;
import com.Hileb.moremomostories.item.armorMaterials.QGXModel2;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

public class ItemQGX extends ItemArmorBase {
    public ItemQGX(String name){
        super(name, ModArmorMaterials.QGXMaterial,1, EntityEquipmentSlot.FEET);
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
                if (itemStack.getItem() instanceof ItemArmor){
                    QGXModel2 model=new QGXModel2();
                    model.bone_all.showModel=armorSlot.equals(EntityEquipmentSlot.FEET);
                    model.bipedLeftLeg=_default.bipedLeftLeg;
                    model.bipedRightLeg=_default.bipedRightLeg;
                    //model.bipedBody=_default.bipedBody;
                    model.isChild=_default.isChild;
                    model.isSneak=_default.isSneak;
                    model.isRiding = _default.isRiding;
                    model.rightArmPose = _default.rightArmPose;
                    model.leftArmPose = _default.leftArmPose;
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
        return super.getArmorTexture(stack, entity, slot, type);
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
}
