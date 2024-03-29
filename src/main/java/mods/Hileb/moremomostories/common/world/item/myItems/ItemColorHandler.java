package mods.Hileb.moremomostories.common.world.item.myItems;

import mods.Hileb.moremomostories.common.world.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ItemColorHandler extends ItemColors{
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
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> {
            if (ItemXe.getRGBColor(stack)!=ItemXe.COLOR_NULL && ItemXe.isOther(stack) && ItemXe.getLevel(stack)!=-1){
                return tintIndex > 0 ? -1 : ItemXe.getRGBColor(stack).color;
            }
            else return -1;
        }, ModItems.ITEM_XE);
    }
}
