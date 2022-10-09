package com.Hileb.moremomostories.meta;

import com.Hileb.moremomostories.item.ModItems;
import com.Hileb.moremomostories.recipe.RecipePutrid;
import com.Hileb.moremomostories.util.MoMo.MoMoCards;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Loader;

public class MetaUtil {
    public static boolean isLoaded_MoreMomoStories = true;



    public static boolean isIDLLoaded = false;
    public static boolean isLoaded_AOA3 = false;
    public static boolean isLoaded_GOG = false;




    public static boolean isLoaded_forestry= false;///give @p forestry:decaying_wheat 64
    public static boolean isLoaded_Momostories = false;
    public static boolean isLoaded_manametalmod = false;///give @p manametalmod:rotFood 64
    public static boolean isLoaded_calculator=false; ///give @p calculator:rottenpear 64

    //extra difficulty
    public static int HARD_AOA3 = 5;
    public static int HARD_GOG = 4;

    //static int modListDifficulty = 0;
    static int modListExtraDifficulty = 0;

    public static int getModListExtraDifficulty() {
        return modListExtraDifficulty;
    }

    public static void CalcModListDifficulty()
    {
       //modListDifficulty = CommonFunctions.GetModCount();
    }
    public static void loadmodload(){
        MetaUtil.isIDLLoaded = Loader.isModLoaded("idealland");
        MetaUtil.isLoaded_Momostories = Loader.isModLoaded("momostories");
        MetaUtil.isLoaded_forestry = Loader.isModLoaded("forestry");
        MetaUtil.isLoaded_manametalmod = Loader.isModLoaded("manametalmod");
        MetaUtil.isLoaded_calculator = Loader.isModLoaded("calculator");
    }
    public static void modLoadInit(){
        if( isLoaded_MoreMomoStories ){
            RecipePutrid.registerPutridItem(ModItems.ITEM_PUTRID);
            MoMoCards.registerCard(ModItems.ITEM_DO_FOREVER);
            MoMoCards.registerCard(ModItems.ITEM_CARD_FIVE);
        }
        if (isLoaded_forestry){
            RecipePutrid.registerPutridItem(Item.getByNameOrId("forestry:decaying_wheat"));
            RecipePutrid.registerPutridItem(Item.getByNameOrId("forestry:humus"));
        }
        if (isLoaded_Momostories){
            //RecipePutrid.PutridItems.add(Item.getByNameOrId("momostories:the_book_of_manifestation"));
            for(int i=0;i<com.gq2529.momostories.item.ModItems.ITEMS.size();i++){//给其添加卡牌
                if(com.gq2529.momostories.item.ModItems.ITEMS.get(i) instanceof com.gq2529.momostories.item.tools.CardBase){
                    MoMoCards.registerCard(com.gq2529.momostories.item.ModItems.ITEMS.get(i));
                }
                if(com.gq2529.momostories.item.ModItems.ITEMS.get(i) instanceof com.gq2529.momostories.item.ModItemStoryboards.LucyAxeCard){
                    MoMoCards.registerCard(com.gq2529.momostories.item.ModItems.ITEMS.get(i));
                }
                if(com.gq2529.momostories.item.ModItems.ITEMS.get(i) instanceof com.gq2529.momostories.item.tools.Replica.CardJump){
                    MoMoCards.registerCard(com.gq2529.momostories.item.ModItems.ITEMS.get(i));
                }
            }
        }
        if (isLoaded_manametalmod){
            RecipePutrid.registerPutridItem(Item.getByNameOrId("manametalmod:rotFood"));
        }
        if (isIDLLoaded){
            RecipePutrid.registerPutridItem("idealland:float_food");
        }
        if (isLoaded_calculator){
            RecipePutrid.registerPutridItem("calculator:rottenpear");
        }
    }

    public static int GetModCount()
    {
        return Loader.instance().getActiveModList().size();
    }
}
