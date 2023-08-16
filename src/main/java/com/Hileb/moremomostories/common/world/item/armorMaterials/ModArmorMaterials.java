package com.Hileb.moremomostories.common.world.item.armorMaterials;

import com.Hileb.moremomostories.common.world.item.ModItems;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

public class ModArmorMaterials {
    public static final ItemArmor.ArmorMaterial QGXMaterial = EnumHelper.addArmorMaterial(
            "item_qgx", "moremomostories:armor_moroon", 120, new int[] {5,10,8,8}, 105, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 5.0F)
            .setRepairItem(new ItemStack(ModItems.ITEM_XE));
}
