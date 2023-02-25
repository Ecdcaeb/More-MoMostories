package com.Hileb.moremomostories.item.myItems;

import com.Hileb.moremomostories.item.ItemArmorBase;
import com.Hileb.moremomostories.item.armorMaterials.ModArmorMaterials;
import com.Hileb.moremomostories.item.armorMaterials.ModelShiningSilverBreastplate;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class ItemShiningSilverBreastplate  extends ItemArmorBase {

    public ItemShiningSilverBreastplate(String name){
        super(name, ModArmorMaterials.QGXMaterial,1, EntityEquipmentSlot.CHEST);
    }
    @SideOnly(Side.CLIENT)
    @Nullable
    @Override
    public net.minecraft.client.model.ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, net.minecraft.client.model.ModelBiped _default) {
        if(itemStack!=null){
            if (!itemStack.isEmpty()){
                if (itemStack.getItem()==this){
                    ModelShiningSilverBreastplate shiningSilverBreastplate=new ModelShiningSilverBreastplate();
                    shiningSilverBreastplate.all.showModel= armorSlot==EntityEquipmentSlot.CHEST;

                    shiningSilverBreastplate.isChild=_default.isChild;
                    shiningSilverBreastplate.isSneak=_default.isSneak;
                    shiningSilverBreastplate.isRiding = _default.isRiding;
                    shiningSilverBreastplate.rightArmPose = _default.rightArmPose;
                    shiningSilverBreastplate.leftArmPose = _default.leftArmPose;


                    return shiningSilverBreastplate;
                }
            }
        }
        return null;
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return "moremomostories:textures/models/armor/shining_silver_breastplate_texture.png";
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

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {

    }
}
