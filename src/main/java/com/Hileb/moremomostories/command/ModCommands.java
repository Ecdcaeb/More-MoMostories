package com.Hileb.moremomostories.command;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ModCommands {
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
     */
    public static boolean clean(EntityPlayer player,IItemComparer comparer,int count,boolean simulate){
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
    /*
           ModCommands.clean(player,new ModCommands.ItemEquals(Items.DIAMOND),128,false);
     */
    ////--判断物品时不总是判断它是否item，也同时可能需要判断itemStack，所以这里使用判断器进行判断
    ////这里预设了一个判断器，当然，效果和==没区别，你可以新建一个自己需要的判断器
    /**
    public interface IItemComparer {
    boolean apply(ItemStack stack);
     }
     **/
    public static class ItemEquals implements IItemComparer{
        private final Item item;
        public ItemEquals(Item itemIn){
            item=itemIn;
        }

        @Override
        public boolean apply(ItemStack stack) {
            return stack.getItem()==item;
        }
    }
}
