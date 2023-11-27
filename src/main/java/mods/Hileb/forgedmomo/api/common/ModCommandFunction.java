package mods.Hileb.forgedmomo.api.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

public class ModCommandFunction {
    public static void give(EntityPlayer player,ItemStack stack){
            if (!player.addItemStackToInventory(stack))
            {
                player.dropItem(stack, false);
            }
    }
    /**
     *
     * @param player 玩家，背包来源
     * @param comparer 比较器，判断对象能不能符合
     * @param count 数目，预计扣除的数目
     * @param simulate 模拟，false时真正扣除
     * @return 如果扣完，true,物品不足,false；
     *
     * example: ModCommandFunction.clean(player,Ingredient.fromItem(Items.DIAMOND),128,false);
     */
    public static boolean clean(EntityPlayer player, Ingredient comparer, int count, boolean simulate){
        int a=count;
        for(int i=0;i<player.inventory.getSizeInventory();i++){
            ItemStack stack=player.inventory.getStackInSlot(i);
            if (comparer.apply(stack)){
                int temp=Math.min(a,stack.getCount());
                if (!simulate)stack.shrink(temp);
                a=a-temp;
            }
        }
        return a==0;
    }
}
