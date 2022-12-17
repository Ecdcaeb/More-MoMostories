package com.Hileb.moremomostories.recipe;

import com.Hileb.moremomostories.item.ModItems;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

public class MainRecipes  extends ShapedRecipes {
    private final ItemStack ore1;
    private final ItemStack ore2;
    private final ItemStack ore3;
    private final ItemStack ore4;
    public MainRecipes(String group,ItemStack ore1In,ItemStack ore2In,ItemStack ore3In,ItemStack ore4In, ItemStack result)
    {
        super(group,3,3,getList(),result);
        ore1=ore1In;
        ore2=ore2In;
        ore3=ore3In;
        ore4=ore4In;
    }
    public static NonNullList<Ingredient> getList(){
        NonNullList<Ingredient> list=NonNullList.create();

        list.add(Ingredient.fromItem(Items.AIR));
        list.add(Ingredient.fromItem(ModItems.ITEM_XE));
        list.add(Ingredient.fromItem(Items.AIR));

        list.add(Ingredient.fromItem(ModItems.ITEM_XE));
        list.add(Ingredient.fromItem(ModItems.ITEM_11_A));
        list.add(Ingredient.fromItem(ModItems.ITEM_XE));

        list.add(Ingredient.fromItem(Items.AIR));
        list.add(Ingredient.fromItem(ModItems.ITEM_XE));
        list.add(Ingredient.fromItem(Items.AIR));
        return list;
    }
    public boolean matches(InventoryCrafting inv, World worldIn)
    {
        if (super.matches(inv,worldIn)){
            return true;
        }
        return false;
    }

}
