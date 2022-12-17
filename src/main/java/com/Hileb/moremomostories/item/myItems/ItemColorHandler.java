package com.Hileb.moremomostories.item.myItems;

import com.Hileb.moremomostories.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ItemColorHandler extends ItemColors{
    private final java.util.Map<net.minecraftforge.registries.IRegistryDelegate<Item>, IItemColor> itemColorMap = com.google.common.collect.Maps.newHashMap();


    public static void init() {
        //一个示例：
        /*
        ItemColors itemcolors = new ItemColors();
        itemcolors.registerItemColorHandler(new IItemColor() {
            public int colorMultiplier(ItemStack stack, int tintIndex) {
                return tintIndex > 0 ? -1 : ((ItemArmor) stack.getItem()).getColor(stack);
            }
        }, Items.LEATHER_HELMET, Items.LEATHER_CHESTPLATE, Items.LEATHER_LEGGINGS, Items.LEATHER_BOOTS);
        */
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new IItemColor() {
            public int colorMultiplier(ItemStack stack, int tintIndex) {
                if (ItemXe.getRGBColor(stack)!=ItemXe.COLOR_NULL && ItemXe.isOther(stack) && ItemXe.getLevel(stack)!=-1){
                    return tintIndex > 0 ? -1 : ItemXe.getRGBColor(stack).color;
                }
                else return -1;
            }

        }, ModItems.ITEM_XE);
    }
}
