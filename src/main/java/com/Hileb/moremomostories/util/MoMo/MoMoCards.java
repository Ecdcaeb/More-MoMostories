package com.Hileb.moremomostories.util.MoMo;

import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class MoMoCards {
    private static List<Item> cards= new ArrayList<Item>();
    public static boolean registerCard(Item item){
        if (cards.contains(item)){
        }
        else {
            cards.add(item);
        }
        return  (cards.contains(item));
    }
    public static boolean isCard(Item item){
        return  (cards.contains(item));
    }
    public static Item getCard(int index){
        if (index<cards.size())return cards.get(index);
        else return null;
    }
    public static int getCount(){
        return cards.size();
    }
}
