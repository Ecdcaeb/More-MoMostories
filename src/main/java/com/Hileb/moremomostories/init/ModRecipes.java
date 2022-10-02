package com.Hileb.moremomostories.init;

import com.Hileb.moremomostories.IdlFramework;
import com.Hileb.moremomostories.meta.MetaUtil;
import com.Hileb.moremomostories.recipe.RecipePutrid;
import com.Hileb.moremomostories.util.Reference;
import com.gq2529.momostories.item.ModItems;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class ModRecipes {
	
	
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
		for(int i=0;i<RecipePutrid.PutridItems.size();i++){
			IdlFramework.LogWarning("%s is Putrid",RecipePutrid.PutridItems.get(i).getUnlocalizedName());
			r.register(new RecipePutrid(ModItems.SCAVENGERS,RecipePutrid.PutridItems.get(i),ModItems.MYSTERIOUS_MEATBALLS).setRegistryName(new ResourceLocation(Reference.MOD_ID, String.format("recipe_putrid_%d",i))));
		}

	}
}
