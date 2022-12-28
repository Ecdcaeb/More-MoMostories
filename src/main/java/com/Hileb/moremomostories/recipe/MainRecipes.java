package com.Hileb.moremomostories.recipe;

import com.Hileb.moremomostories.item.ModItems;
import com.Hileb.moremomostories.item.myItems.ItemXe;
import com.Hileb.moremomostories.util.Reference;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class MainRecipes  extends ShapedOreRecipe {
//    private final ItemStack ore1;
//    private final ItemStack ore2;
//    private final ItemStack ore3;
//    private final ItemStack ore4;
    public MainRecipes(String group, ItemStack result)
    {
        super(new ResourceLocation(Reference.MOD_ID,group),result,
                new Object[]{
                        " R ",
                        "GOB",
                        " R ",
                        'R', ItemXe.get(ItemXe.XeType.RED_0),
                        'G', ItemXe.get(ItemXe.XeType.BLACK_0),
                        'B', ItemXe.get(ItemXe.XeType.BLUE_0),
                        'O', new ItemStack(ModItems.ITEM_11_A)}
                );
    }
    public boolean matches(InventoryCrafting inv, World worldIn)
    {
        if (super.matches(inv,worldIn)){
            if (inv.getSizeInventory()==9){
                if (!inv.getStackInSlot(1).isEmpty()){
                    if (!inv.getStackInSlot(3).isEmpty()){
                        if (!inv.getStackInSlot(5).isEmpty()){
                            if (!inv.getStackInSlot(7).isEmpty()){
                                if (ItemXe.getLevel(inv.getStackInSlot(1))==0){
                                    if (ItemXe.getLevel(inv.getStackInSlot(3))==0){

                                        if (ItemXe.getLevel(inv.getStackInSlot(5))==0){
                                            if (ItemXe.getLevel(inv.getStackInSlot(7))==0){

                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

}
