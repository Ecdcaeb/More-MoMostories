package com.Hileb.moremomostories.item.armorMaterials;

import com.Hileb.moremomostories.item.ModItems;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

public class ModArmorMaterials {
    public static final ItemArmor.ArmorMaterial QGXMaterial = EnumHelper.addArmorMaterial(
            "item_qgx", "moremomostories:armor_moroon", 120, new int[] {20,40,50,22}, 105, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 15.0F)
            .setRepairItem(new ItemStack(ModItems.ITEM_XE));
}
