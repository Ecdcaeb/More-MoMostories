package com.Hileb.moremomostories.recipe;

import com.google.common.collect.Lists;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

import java.util.List;

public class XeRecipes extends net.minecraftforge.registries.IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {
    //从无序配方摘抄的，暂时如此。
    /** Is the ItemStack that you get when craft the recipe. */
    private final ItemStack recipeOutput;
    /** Is a List of ItemStack that composes the recipe. */
    public final NonNullList< Ingredient> recipeItems;
    private final String group;
    //private final boolean isSimple;

    public XeRecipes(String group, ItemStack output, NonNullList< Ingredient> materials)
    {
        this.group = group;
        this.recipeOutput = output;
        this.recipeItems = materials;
    }

    public String getGroup()
    {
        return this.group;
    }

    public ItemStack getRecipeOutput()
    {
        return this.recipeOutput;
    }

    /**
     * Used to check if a recipe matches current crafting inventory
     */
    public boolean matches(InventoryCrafting inv, World worldIn)
    {
        int ingredientCount = 0;
        net.minecraft.client.util.RecipeItemHelper recipeItemHelper = new net.minecraft.client.util.RecipeItemHelper();
        List<ItemStack> inputs = Lists.newArrayList();

        for (int i = 0; i < inv.getHeight(); ++i)
        {
            for (int j = 0; j < inv.getWidth(); ++j)
            {
                ItemStack itemstack = inv.getStackInRowAndColumn(j, i);

                if (!itemstack.isEmpty())
                {
                    ++ingredientCount;
//                    if (this.isSimple)
//                        recipeItemHelper.accountStack(itemstack, 1);
//                    else
//                        inputs.add(itemstack);
                }
            }
        }

        if (ingredientCount != this.recipeItems.size())
            return false;

//        if (this.isSimple)
//            return recipeItemHelper.canCraft(this, null);

        return net.minecraftforge.common.util.RecipeMatcher.findMatches(inputs, this.recipeItems) != null;
    }

    /**
     * Returns an Item that is the result of this recipe
     */
    public ItemStack getCraftingResult(InventoryCrafting inv)
    {
        return this.recipeOutput.copy();
    }

//    public static XeRecipes deserialize(JsonObject json)
//    {
//        String s = JsonUtils.getString(json, "group", "");
//        NonNullList<Ingredient> nonnulllist = deserializeIngredients(JsonUtils.getJsonArray(json, "ingredients"));
//
//        if (nonnulllist.isEmpty())
//        {
//            throw new JsonParseException("No ingredients for shapeless recipe");
//        }
//        else if (nonnulllist.size() > 9)
//        {
//            throw new JsonParseException("Too many ingredients for shapeless recipe");
//        }
//        else
//        {
//            ItemStack itemstack = ShapedRecipes.deserializeItem(JsonUtils.getJsonObject(json, "result"), true);
//            return new XeRecipes(s, itemstack, nonnulllist);
//        }
//    }

//    private static NonNullList<Ingredient> deserializeIngredients(JsonArray array)
//    {
//        NonNullList<Ingredient> nonnulllist = NonNullList.<Ingredient>create();
//
//        for (int i = 0; i < array.size(); ++i)
//        {
//            Ingredient ingredient = ShapedRecipes.deserializeIngredient(array.get(i));
//
//            if (ingredient != Ingredient.EMPTY)
//            {
//                nonnulllist.add(ingredient);
//            }
//        }
//
//        return nonnulllist;
//    }

    /**
     * Used to determine if this recipe can fit in a grid of the given width/height
     */
    public boolean canFit(int width, int height)
    {
        return width * height >= this.recipeItems.size();
    }
}
