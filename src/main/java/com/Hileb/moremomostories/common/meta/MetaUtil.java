package com.Hileb.moremomostories.common.meta;

import com.Hileb.moremomostories.common.world.item.ModItems;
import com.Hileb.moremomostories.common.world.recipe.RecipePutrid;
import com.Hileb.moremomostories.mods.momo.MoMoCards;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Loader;

public class MetaUtil {
    public static boolean isLoaded_MoreMomoStories = true;



    public static boolean isIDLLoaded = false;



    public static boolean isLoaded_forestry= false;///give @p forestry:decaying_wheat 64
    public static boolean isLoaded_Momostories = false;
    public static boolean isLoaded_manametalmod = false;///give @p manametalmod:rotFood 64
    public static boolean isLoaded_calculator=false; ///give @p calculator:rottenpear 64

    public static boolean isLoaded_MagicCircle= false;

    public static boolean isLoaded_SlashBlade= false;

    public static boolean isLoaded_AddPotion= false;


    static int modListExtraDifficulty = 0;


    public static void loadmodload(){
        MetaUtil.isIDLLoaded = Loader.isModLoaded("idealland");
        MetaUtil.isLoaded_Momostories = Loader.isModLoaded("momostories");
        MetaUtil.isLoaded_forestry = Loader.isModLoaded("forestry");
        MetaUtil.isLoaded_manametalmod = Loader.isModLoaded("manametalmod");
        MetaUtil.isLoaded_calculator = Loader.isModLoaded("calculator");
        MetaUtil.isLoaded_MagicCircle=Loader.isModLoaded("magiccircle");
        MetaUtil.isLoaded_SlashBlade=Loader.isModLoaded("flammpfeil.slashblade");
        MetaUtil.isLoaded_AddPotion=Loader.isModLoaded("add_potion");
    }
    public static void modLoadInit(){
        if( isLoaded_MoreMomoStories ){
            RecipePutrid.registerPutridItem(ModItems.ITEM_PUTRID);
            MoMoCards.registerCard(ModItems.ITEM_DO_FOREVER,MoMoCards.CardType.HILEB);
            MoMoCards.registerCard(ModItems.ITEM_CARD_FIVE,MoMoCards.CardType.HILEB);
        }
        if (isLoaded_forestry){
            RecipePutrid.registerPutridItem(Item.getByNameOrId("forestry:decaying_wheat"));
            RecipePutrid.registerPutridItem(Item.getByNameOrId("forestry:humus"));
        }
        if (isLoaded_Momostories){
            //RecipePutrid.PutridItems.add(Item.getByNameOrId("momostories:the_book_of_manifestation"));
            MoMoCards.cardInit();
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
