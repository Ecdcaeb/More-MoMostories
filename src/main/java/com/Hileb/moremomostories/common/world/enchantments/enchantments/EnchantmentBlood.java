package com.Hileb.moremomostories.common.world.enchantments.enchantments;

import com.Hileb.moremomostories.common.world.enchantments.EnchantmentBase;
import com.Hileb.moremomostories.common.world.item.interfaces.IColorEnchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemStack;

import static com.Hileb.moremomostories.common.world.enchantments.ModEnchantmentInit.mainHand;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/8/27 15:22
 **/
public class EnchantmentBlood extends EnchantmentBase implements IColorEnchantment {
    public EnchantmentBlood(String name) {
        super(name, Rarity.VERY_RARE, EnumEnchantmentType.ALL//ALL为所有物品均可，
                , mainHand);//生效的物品槽
        setMaxLevel(5);
    }

    @Override
    public boolean hasEnchantmentEffect(ItemStack stack) {
        return true;
    }
    @Override
    public int getEnchantmentColor(ItemStack stack) {
        return 0xFF0000;//改回去
    }
}
