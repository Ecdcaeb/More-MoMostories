package mods.Hileb.moremomostories.common.world.recipe;

import mods.Hileb.moremomostories.mods.slashblade.blade.ModBlades;
import mods.Hileb.moremomostories.common.world.item.ModItems;
import mods.Hileb.moremomostories.common.world.item.myItems.ItemEndRainbow;
import mods.Hileb.moremomostories.common.capabilities.nametag.named.NameTagHandler;
import mods.flammpfeil.slashblade.RecipeAwakeBlade;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import java.util.Map;

public class EndBladeRecipeSlashBlade extends RecipeAwakeBlade {
    public EndBladeRecipeSlashBlade(ResourceLocation group) {
        super(group, new ItemStack(ModItems.ITEM_SWOOD_SAKURA_END), SlashBlade.getCustomBlade(ModBlades.TIAN_KI.getName()),
                "RTY",
                "ABC",
                "QEQ",
                'A',new ItemStack(ModItems.ITEM_FIBER_WIRE),
                'B',new ItemStack(ModItems.ITEM_CARD_ZFP),
                'C',new ItemStack(ModItems.ITEM_SWOOD_MEMORY_END),
                'Q',new ItemStack(ModItems.ITEM_XE),
                'R',new ItemStack(com.gq2529.momostories.item.ModItems.REPLICA_1),
                'T',new ItemStack(Items.GOLDEN_SWORD),
                'Y',new ItemStack(com.gq2529.momostories.item.ModItems.THE_BOOK_OF_MANIFESTATION),
                'E',SlashBlade.getCustomBlade(ModBlades.TIAN_KI.getName())
                );
    }
    @Nonnull
    @Override
    public ItemStack getCraftingResult(@Nonnull InventoryCrafting var1) {
            ItemStack result = new ItemStack(ModItems.ITEM_SWOOD_SAKURA_END);

            ItemStack stackBland=var1.getStackInSlot(7);
            int score=0;
            NBTTagCompound tagCompound=stackBland.getTagCompound();
            score+=ItemSlashBlade.RepairCount.get(tagCompound)*100;
            score+=ItemSlashBlade.KillCount.get(tagCompound)*10;
            score+=ItemSlashBlade.ProudSoul.get(tagCompound);
            ItemEndRainbow.addEnergy(result,(double) score);

            Map<Enchantment,Integer> oldItemEnchants = EnchantmentHelper.getEnchantments(stackBland);
            for(Map.Entry<Enchantment,Integer> enchant: oldItemEnchants.entrySet())
            {
                int level = EnchantmentHelper.getEnchantmentLevel(enchant.getKey(),stackBland);
                result.addEnchantment(enchant.getKey(),level);
            }

            NameTagHandler.randomApply(result);
            return result;
    }
}
