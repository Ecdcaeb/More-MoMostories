package com.Hileb.moremomostories.common.world.recipe;

import com.Hileb.moremomostories.common.world.item.ModItems;
import com.Hileb.moremomostories.common.util.named.NameTagHandler;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;

import javax.annotation.Nonnull;

public class EndBladeRecipe extends ShapedOreRecipe {

    public EndBladeRecipe(ResourceLocation group) {
        super(group, new ItemStack(ModItems.ITEM_SWOOD_SAKURA_END), getRecipe());
    }
     private static Object[] getRecipe(){
        return new Object[]{
                "RTY",
                "ABC",
                "Q Q",
                'A',new ItemStack(ModItems.ITEM_12_B),
                'B',new ItemStack(ModItems.ITEM_CARD_ZFP),
                'C',new ItemStack(ModItems.ITEM_SWOOD_MEMORY_END),
                'Q',new ItemStack(ModItems.ITEM_XE),
                'R',new ItemStack(com.gq2529.momostories.item.ModItems.REPLICA_1),
                'T',new ItemStack(Items.GOLDEN_SWORD),
                'Y',new ItemStack(com.gq2529.momostories.item.ModItems.THE_BOOK_OF_MANIFESTATION)
        };
     }


    @Override
    public boolean matches(InventoryCrafting inv, World world) {
        if (inv.getSizeInventory()!=9)return false;
        else if (inv.getStackInSlot(0).getItem()!=com.gq2529.momostories.item.ModItems.REPLICA_1)return false;
        else if (inv.getStackInSlot(1).getItem()!=Items.GOLDEN_SWORD)return false;
        else if (inv.getStackInSlot(2).getItem()!=com.gq2529.momostories.item.ModItems.THE_BOOK_OF_MANIFESTATION)return false;
        else if (inv.getStackInSlot(3).getItem()!=ModItems.ITEM_12_B)return false;
        else if (inv.getStackInSlot(4).getItem()!=ModItems.ITEM_CARD_ZFP)return false;
        else if (inv.getStackInSlot(5).getItem()!=ModItems.ITEM_SWOOD_MEMORY_END)return false;
        else if (inv.getStackInSlot(6).getItem()!=ModItems.ITEM_XE)return false;
        else if (inv.getStackInSlot(8).getItem()!=ModItems.ITEM_XE)return false;
        else return true;
    }

    @Nonnull
    public ItemStack getCraftingResult(@Nonnull InventoryCrafting var1) {
        ItemStack result = super.getCraftingResult(var1);


        NameTagHandler.randomApply(result);

        return result;
    }
}
