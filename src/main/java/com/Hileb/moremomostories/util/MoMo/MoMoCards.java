package com.Hileb.moremomostories.util.MoMo;

import com.gq2529.momostories.item.ModItems;
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
    public static void cardInit(){
        registerCard(ModItems.UNKOWN_CARDS);
        registerCard(ModItems.LUNA_CHURCH);
        registerCard( ModItems.CHURCH_OF_THE_SUN_GOD);
        registerCard( ModItems.THE_SUPREME_MAGI_DEEPLAKE);
        registerCard( ModItems.FORGOTTEN_ANCIENT_MIRRORS);
        registerCard( ModItems.CENTRAL_ACADEMY_OF_SCIENCES);
        registerCard( ModItems.FRESHMAN);
        registerCard( ModItems.CONSCRIPTION_ORDER);
        registerCard( ModItems.REED);
        registerCard( ModItems.GARLANFAA_REDEMTION);
        registerCard( ModItems.FORT_SLIM);
        registerCard( ModItems.DAYTIME);
        registerCard( ModItems.LEYDEN_JAR);
        registerCard(  ModItems.BLOOD_COOLORED_CALIDAN);
        registerCard(  ModItems.DEVILS_BLOOD);
        registerCard(  ModItems.LUCY_THE_AXE_CARD);
        registerCard(  ModItems.SOUL_PUPPETS);
        registerCard( ModItems.THE_BOOK_OF_FORGERY);
        registerCard( ModItems.AI_LING_WISHES);
        registerCard(  ModItems.NIGHT);
        registerCard(  ModItems.ABYSS_CLOISTER);
        registerCard(  ModItems.OHAM_HEAVY_CAVALRY_REGIMENT);
        registerCard(  ModItems.ADJUDICATOR_BALANCE);
        registerCard( ModItems.PLAGUE_DOCTOR);
        registerCard(  ModItems.FRAUDULENT_BOTTLES);
        registerCard(  ModItems.THE_FRUIT_OF_THE_FLOWING_YEARS_CARD);
        registerCard(  ModItems.SCAVENGERS);
        registerCard(  ModItems.TWIST);
        registerCard( ModItems.FINE);
        registerCard(  ModItems.DARK_CLOUDS);
        registerCard(  ModItems.ESTES);
        registerCard( ModItems.SIRIN);
        registerCard(  ModItems.SPLIT);
        registerCard( ModItems.MISER);
         registerCard(  ModItems.MISER);
        registerCard(  ModItems.FOUR_WAY_TRIP);
        registerCard(  ModItems.INTERNAL_STRIIFE);
    }
}
