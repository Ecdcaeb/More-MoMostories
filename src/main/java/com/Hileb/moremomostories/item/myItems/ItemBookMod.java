package com.Hileb.moremomostories.item.myItems;

import com.Hileb.moremomostories.item.ItemBase;
import com.Hileb.moremomostories.item.ModItems;
import com.Hileb.moremomostories.util.NBTStrDef.IDLNBTUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Random;

public class ItemBookMod extends ItemBase {
    public ItemBookMod(String name){
        super(name);
    }
    public static ItemStack getBook(Item item,int index){
        ItemStack stack=new ItemStack(item);
        changeName(stack,index);
        return stack.copy();
    }
    public static ItemStack getBook(int index){
        return getBook(ModItems.ITEM_BOOK,index);
    }
    public static ItemStack getBook(){
        Random random=new Random();
        random.setSeed(random.nextLong()+random.hashCode());
        return getBook(random.nextInt(28));
    }
    public static ItemStack getBook(Item item){
        Random random=new Random();
        random.setSeed(random.nextLong()+random.hashCode());
        return getBook(item,random.nextInt(28));
    }
    public static void changeName(ItemStack stack,int index){
        stack.setTranslatableName(String.format("book.name.%d.name",index));
        IDLNBTUtil.SetBoolean(stack,"isModBookAndLvl1",true);
    }
}
