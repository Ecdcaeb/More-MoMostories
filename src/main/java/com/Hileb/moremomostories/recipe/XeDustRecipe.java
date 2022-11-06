package com.Hileb.moremomostories.recipe;

import com.Hileb.moremomostories.item.ModItems;
import com.Hileb.moremomostories.item.myItems.ItemXe;
import com.Hileb.moremomostories.item.myItems.RGBColor;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import java.util.Iterator;
import java.util.List;

public class XeDustRecipe extends ShapelessOreRecipe {
    public static final String RepairCountStr = "RepairCount";
    public static int RepairProudSoulCount = 2;

    public XeDustRecipe() {
        super(new ResourceLocation("moremomostories", "recipe_do_xe"), new ItemStack(ModItems.ITEM_XE, 1, 0), new Object[]{new ItemStack(ModItems.ITEM_XE, 1, 0),new ItemStack(ModItems.ITEM_XE, 1, 0)});

    }

    public static boolean containsMatch(boolean strict, List<ItemStack> inputs, ItemStack... targets) {
        Iterator var3 = inputs.iterator();

        while(var3.hasNext()) {
            ItemStack input = (ItemStack)var3.next();
            ItemStack[] var5 = targets;
            int var6 = targets.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                ItemStack target = var5[var7];
                if (OreDictionary.itemMatches(target, input, strict)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean matches(InventoryCrafting cInv, World par2World) {
        ItemStack dust1=ItemStack.EMPTY;
        ItemStack dust2=ItemStack.EMPTY;
        int count=0;
        for(int i=0;i<9;i++){
            if(!cInv.getStackInSlot(i).isEmpty()){
                count++;
                if (count==1){
                    dust1=cInv.getStackInSlot(i);
                }
                if(count==2){
                    dust2=cInv.getStackInSlot(i);
                }
                if(count>2){
                    return false;
                }
            }
        }
        if (dust1.getItem() instanceof ItemXe){
            if (dust2.getItem() instanceof ItemXe){//是氙石
                if (ItemXe.getLevel(dust1)==0){
                    if (ItemXe.getLevel(dust1)==0){//是粉末
                        if (ItemXe.getRGBColor(dust1)!=ItemXe.COLOR_EMPTY && ItemXe.getRGBColor(dust1)!=ItemXe.COLOR_NULL){
                            if (ItemXe.getRGBColor(dust2)!=ItemXe.COLOR_EMPTY && ItemXe.getRGBColor(dust2)!=ItemXe.COLOR_NULL){
                                //非空，颜色正常
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public ItemStack getCraftingResult(InventoryCrafting cInv) {
        ItemStack dust1=ItemStack.EMPTY;
        ItemStack dust2=ItemStack.EMPTY;
        int count=0;
        for(int i=0;i<9;i++){
            if(!cInv.getStackInSlot(i).isEmpty()){
                count++;
                if (count==1){
                    dust1=cInv.getStackInSlot(i);
                }
                if(count==2){
                    dust2=cInv.getStackInSlot(i);
                }
                if(count>2){
                    return ItemStack.EMPTY;
                }
            }
        }
        ItemStack target=output.copy();

        int red=(int)(ItemXe.getRGBColor(dust1).getRed()+ItemXe.getRGBColor(dust2).getRed())/2;
        int green=(int)(ItemXe.getRGBColor(dust1).getGreen()+ItemXe.getRGBColor(dust2).getGreen())/2;
        int blue=(int)(ItemXe.getRGBColor(dust1).getBlue()+ItemXe.getRGBColor(dust2).getBlue())/2;
        //算数平均数
        ItemXe.setRGBColor(target,new RGBColor(red,green,blue));
        ItemXe.changeName(target);
        return target;
    }

//    @SubscribeEvent
//    public void onCrafting(PlayerEvent.ItemCraftedEvent event) {
//        EntityPlayer player = event.player;
//        ItemStack item = event.crafting;
//        IInventory craftMatrix = event.craftMatrix;
//        if (!item.func_190926_b() && item.func_77973_b() instanceof ItemSlashBlade && item.func_77942_o()) {
//            NBTTagCompound tag = item.func_77978_p();
//            if (tag.func_74764_b("RepairCount")) {
//                int repair = tag.func_74762_e("RepairCount");
//                tag.func_82580_o("RepairCount");
//
//                try {
//                    ItemStack stone = craftMatrix.func_70301_a(1);
//                    if (!stone.func_190926_b()) {
//                        List<ItemStack> ores = OreDictionary.getOres("cobblestone");
//                        boolean hasGrindstone = containsMatch(false, ores, new ItemStack(Blocks.field_150347_e));
//                        if (hasGrindstone) {
//                            if (stone.func_190916_E() < repair) {
//                                int overDamage = repair - stone.func_190916_E();
//                                item.func_77964_b(item.func_77952_i() + overDamage);
//                                stone.func_190920_e(0);
//                            } else {
//                                stone.func_190918_g(repair);
//                            }
//                        }
//                    }
//                } catch (Throwable var11) {
//                }
//            }
//        }
//
//    }
}
