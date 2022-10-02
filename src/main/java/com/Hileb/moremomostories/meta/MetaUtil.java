package com.Hileb.moremomostories.meta;

import com.Hileb.moremomostories.item.ModItems;
import com.Hileb.moremomostories.recipe.RecipePutrid;
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
            RecipePutrid.PutridItems.add(ModItems.ITEM_PUTRID);
        }
        if (isLoaded_forestry){
            RecipePutrid.PutridItems.add(Item.getByNameOrId("forestry:decaying_wheat"));
            RecipePutrid.PutridItems.add(Item.getByNameOrId("forestry:humus"));
        }
        if (isLoaded_Momostories){
            //RecipePutrid.PutridItems.add(Item.getByNameOrId("momostories:the_book_of_manifestation"));
        }
        if (isLoaded_manametalmod){
            RecipePutrid.PutridItems.add(Item.getByNameOrId("manametalmod:rotFood"));
        }
        if (isIDLLoaded){
            RecipePutrid.PutridItems.add(Item.getByNameOrId("idealland:float_food"));
        }
        if (isLoaded_calculator){
            RecipePutrid.PutridItems.add(Item.getByNameOrId("calculator:rottenpear"));
        }
    }

    public static int GetModCount()
    {
        return Loader.instance().getActiveModList().size();
    }
}
