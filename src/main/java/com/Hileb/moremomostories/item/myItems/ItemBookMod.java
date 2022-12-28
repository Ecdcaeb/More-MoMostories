package com.Hileb.moremomostories.item.myItems;

import com.Hileb.moremomostories.item.ItemBase;
import com.Hileb.moremomostories.item.ModItems;
import com.Hileb.moremomostories.util.NBTStrDef.IDLNBTUtil;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class ItemBookMod extends ItemBase {
    public ItemBookMod(String name){
        super(name);
    }
    public static ItemStack getBook(Item item,int index){
        ItemStack stack=new ItemStack(item);
        changeName(stack,index);
        stack.setCount(1);
        return stack.copy();
    }
    public static ItemStack getBook(int index){
        return getBook(ModItems.ITEM_BOOK,index);
    }
    public static ItemStack getBook(){
        Random random=new Random();
        random.setSeed(random.nextLong()+random.hashCode());
        return getBook(random.nextInt(29));
    }
    public static ItemStack getBook(Item item){
        Random random=new Random();
        random.setSeed(random.nextLong()+random.hashCode());
        return getBook(item,random.nextInt(29));
    }
    public static void changeName(ItemStack stack,int index){
        stack.setTranslatableName(String.format("book.name.%d.name",index));
        IDLNBTUtil.SetBoolean(stack,"isModBookAndLvl1",true);
        IDLNBTUtil.SetInt(stack,"hileb.nbt.book.id",index);
    }

    @Override
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
        super.addInformation(stack, world, tooltip, flag);
        if (stack.hasTagCompound()){
            if (stack.getTagCompound().hasKey("hileb.nbt.book.id")){
                if (IDLNBTUtil.GetInt(stack,"hileb.nbt.book.id",-1)!=-1){
                    tooltip.add(I18n.format(String.format("book.author.%d.name", IDLNBTUtil.GetInt(stack,"hileb.nbt.book.id"))));
                }
            }
        }
    }
}
