package com.Hileb.moremomostories.common.world.recipe;

import com.Hileb.moremomostories.common.world.blocks.ModBlocks;
import com.Hileb.moremomostories.common.world.item.ModItems;
import com.Hileb.moremomostories.common.world.item.myItems.ItemXe;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class XeRecipesProduce extends IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {
    @Override
    public boolean matches(InventoryCrafting inv, World worldIn) {
        if (!worldIn.isRemote){
            if (inv.getFieldCount()==3){
                if (inv.getStackInSlot(0).getItem()== ModItems.ITEM_XE){
                    return true;
                }
                if (inv.getStackInSlot(0).getItem()== net.minecraftforge.registries.GameData.getBlockItemMap().get(ModBlocks.BLOCK_ORE_XE_RED)){
                    return true;
                }
            }
        }
        return false;
    }
    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv){
        return ItemXe.get(ItemXe.XeType.BLUE_0);
    }

    @Override
    public ItemStack getRecipeOutput() {
        return ItemXe.get(ItemXe.XeType.BLUE_0);
    }

    @Override
    public String getGroup() {
        return "suit_ciel";
    }

    @Override
    public boolean isDynamic() {
        return true;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list=NonNullList.create();
        list.add(Ingredient.fromItem(getRecipeOutput().getItem()));
        //list.add(Ingredient.fromItem(result));
        return list;
    }

    @Override
    public boolean canFit(int width, int height) {
        return true;
    }


}
