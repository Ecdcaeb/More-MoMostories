package mods.Hileb.moremomostories.mods;

import mods.Hileb.moremomostories.common.world.item.ModItems;
import mods.Hileb.moremomostories.common.world.recipe.RecipePutrid;
import mods.Hileb.forgedmomo.api.momostories.MoMoCards;
import mods.Hileb.moremomostories.mods.magiccircle.ModMagicCircles;
import mods.Hileb.moremomostories.mods.slashblade.SlashBladeUtil;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;

public class ModLoadingPlugin {
    public static boolean isLoaded_MoreMomoStories = true;
    public static boolean isIDLLoaded = false;
    public static boolean isLoaded_forestry= false;///give @p forestry:decaying_wheat 64
    public static boolean isLoaded_Momostories = false;
    public static boolean isLoaded_manametalmod = false;///give @p manametalmod:rotFood 64
    public static boolean isLoaded_calculator=false; ///give @p calculator:rottenpear 64
    public static boolean isLoaded_MagicCircle= false;
    public static boolean isLoaded_SlashBlade= false;
    public static boolean isLoaded_AddPotion= false;

    public static void updateModState(){
        ModLoadingPlugin.isIDLLoaded = Loader.isModLoaded("idealland");
        ModLoadingPlugin.isLoaded_Momostories = Loader.isModLoaded("momostories");
        ModLoadingPlugin.isLoaded_forestry = Loader.isModLoaded("forestry");
        ModLoadingPlugin.isLoaded_manametalmod = Loader.isModLoaded("manametalmod");
        ModLoadingPlugin.isLoaded_calculator = Loader.isModLoaded("calculator");
        ModLoadingPlugin.isLoaded_MagicCircle=Loader.isModLoaded("magiccircle");
        ModLoadingPlugin.isLoaded_SlashBlade=Loader.isModLoaded("flammpfeil.slashblade");
        ModLoadingPlugin.isLoaded_AddPotion=Loader.isModLoaded("add_potion");
    }
    public static void recipeInit(){
        if( isLoaded_MoreMomoStories){
            RecipePutrid.registerPutridItem(ModItems.ITEM_PUTRID);
        }
        if (isLoaded_forestry){
            RecipePutrid.registerPutridItem(Item.getByNameOrId("forestry:decaying_wheat"));
            RecipePutrid.registerPutridItem(Item.getByNameOrId("forestry:humus"));
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
    public static void postInit(){
        if(isLoaded_MoreMomoStories){
            RecipePutrid.registerPutridItem(ModItems.ITEM_PUTRID);
        }
    }
    public static void preInit(){
    }
}
