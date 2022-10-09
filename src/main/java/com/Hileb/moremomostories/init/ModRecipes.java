package com.Hileb.moremomostories.init;

import com.Hileb.moremomostories.IdlFramework;
import com.Hileb.moremomostories.blocks.ModBlocks;
import com.Hileb.moremomostories.meta.MetaUtil;
import com.Hileb.moremomostories.recipe.RecipePutrid;
import com.Hileb.moremomostories.util.Reference;
import com.gq2529.momostories.item.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class ModRecipes {
	public static IForgeRegistry<IRecipe> recipesRegister;
	
	
	public static void Init() {
		registerRecipe();
		registerSmelting();
	}
	private static void registerRecipe(){

	}
	private static void  registerSmelting(){
//		Only smelting recipes
//		GameRegistry.addSmelting(ModItems.PURE_INGOT,
//				new ItemStack(ModItems.WEAPON_PEARL),
//				0.1f);
	}
	
	@SubscribeEvent
	public static void registerRecipes(RegistryEvent.Register<IRecipe> evt) {
		IForgeRegistry<IRecipe> r = evt.getRegistry();
		MetaUtil.loadmodload();
		MetaUtil.modLoadInit();
//		//Example
//		r.register(new GobletFill().setRegistryName(new ResourceLocation(Reference.MOD_ID, "goblet_fill")));
//
//		//
		r.register(new RecipePutrid(ModItems.THE_BOOK_OF_MANIFESTATION,com.Hileb.moremomostories.item.ModItems.ITEM_PAPER_IDONOTWANTTODIE,com.Hileb.moremomostories.item.ModItems.ITEM_SCENE_1).setRegistryName(new ResourceLocation(Reference.MOD_ID, String.format("recipe_emp_scene1"))));
		for(int i=0;i<RecipePutrid.PutridItems.size();i++){
			IdlFramework.LogWarning("%s is Putrid",RecipePutrid.PutridItems.get(i).getUnlocalizedName());
			r.register(new RecipePutrid(ModItems.SCAVENGERS,RecipePutrid.PutridItems.get(i),ModItems.MYSTERIOUS_MEATBALLS).setRegistryName(new ResourceLocation(Reference.MOD_ID, String.format("recipe_putrid_%d",i))));
		}
		//r.register(new ShapelessOreRecipe("for_id_paper_6151", ModItems.Id_SANDPAPER, OreDictionary.getOres("dustId"),OreDictionary.getOres("logNoLeaf")));
	}
	@SubscribeEvent
	public static void getVanillaFurnaceFuelValue(FurnaceFuelBurnTimeEvent event) {
		if(event.getItemStack().getItem() == Item.getItemFromBlock(ModBlocks.BLOCK_WOOD_NO_LEAF)){
			event.setBurnTime(200);
		}
	}
}
