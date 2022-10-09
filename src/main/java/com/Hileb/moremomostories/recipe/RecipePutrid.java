package com.Hileb.moremomostories.recipe;

import com.Hileb.moremomostories.init.ModRecipes;
import com.Hileb.moremomostories.util.Reference;
import com.gq2529.momostories.item.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class RecipePutrid extends ShapelessRecipes {
    public static List<Item> PutridItems=new ArrayList<Item>();
    public RecipePutrid(Item item ,Item card, Item result) {
        super("suit",new ItemStack(result),getlist(card,item));

        //setRegistryName(Objects.requireNonNull(result.getRegistryName()));
    }
    public static NonNullList<Ingredient> getlist(Item card,Item result){
        NonNullList<Ingredient> list=NonNullList.create();
        list.add(Ingredient.fromItem(card));
        list.add(Ingredient.fromItem(result));
        return list;
    }
    public static boolean registerPutridItem(Item item){
        if (!PutridItems.contains(item)){PutridItems.add(item);}
        return PutridItems.contains(item);
    }
    public static boolean registerPutridItem(String name){
        if (Item.getByNameOrId(name)==null)return false;
        if (!PutridItems.contains(Item.getByNameOrId(name))){PutridItems.add(Item.getByNameOrId(name));}
        return PutridItems.contains(Item.getByNameOrId(name));
    }
    public static boolean registerPutridItemForAfterMod(Item item){
        if (!PutridItems.contains(item)){PutridItems.add(item);}
        ModRecipes.recipesRegister.register(new RecipePutrid(ModItems.SCAVENGERS,item,ModItems.MYSTERIOUS_MEATBALLS).setRegistryName(new ResourceLocation(Reference.MOD_ID, String.format("recipe_putrid_forMod_%s",item.getUnlocalizedName()))));
        return PutridItems.contains(item);
    }
    public static boolean registerPutridItemForAfterMod(String name){
        if (Item.getByNameOrId(name)==null)return false;
        return registerPutridItemForAfterMod(Item.getByNameOrId(name));
    }
}
