package com.Hileb.moremomostories.common.world.recipe;

import com.Hileb.moremomostories.common.world.blocks.ModBlocks;
import com.Hileb.moremomostories.common.world.item.myItems.ItemXe;
import com.Hileb.moremomostories.common.util.Reference;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class FJRecipes extends ShapedOreRecipe {
//    private final ItemStack ore1;
//    private final ItemStack ore2;
//    private final ItemStack ore3;
//    private final ItemStack ore4;
    public FJRecipes(String group,ItemStack result)
    {
        super(new ResourceLocation(Reference.MOD_ID,group),result,
                new Object[]{
                        " R ",
                        "GOB",
                        " R ",
                        'R', ItemXe.get(ItemXe.XeType.RED_0),
                        'G', ItemXe.get(ItemXe.XeType.BLACK_0),
                        'B', ItemXe.get(ItemXe.XeType.BLUE_0),
                        'O', new ItemStack(ModBlocks.BLOCK_HILEB_BLOCK)}
        );
    }
}
