package com.Hileb.moremomostories.item.myItems;

import com.Hileb.moremomostories.item.ItemArmorBase;
import com.Hileb.moremomostories.item.armorMaterials.ModArmorMaterials;
import com.Hileb.moremomostories.item.armorMaterials.ModelAKGHeadset;
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

public class ItemHeadSet extends ItemArmorBase {

    public ItemHeadSet(String name){
        super(name, ModArmorMaterials.QGXMaterial,1, EntityEquipmentSlot.HEAD);
    }

    @Override
    public void registerModels() {
        super.registerModels();
    }

    @SideOnly(Side.CLIENT)
    @Nullable
    @Override
    public net.minecraft.client.model.ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot,net.minecraft.client.model.ModelBiped _default) {
        if(itemStack!=null){
            if (!itemStack.isEmpty()){
                if (itemStack.getItem()==this){
                    ModelAKGHeadset headset=new ModelAKGHeadset();
                    headset.akg.showModel= armorSlot==EntityEquipmentSlot.HEAD;

                    headset.isChild=_default.isChild;
                    headset.isSneak=_default.isSneak;
                    headset.isRiding = _default.isRiding;
                    headset.rightArmPose = _default.rightArmPose;
                    headset.leftArmPose = _default.leftArmPose;


                    return headset;
                }
            }
        }
        return null;
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return "moremomostories:textures/models/armor/zfpheadset.png";
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
