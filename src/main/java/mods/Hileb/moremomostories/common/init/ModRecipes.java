package mods.Hileb.moremomostories.common.init;

import mods.Hileb.moremomostories.MoreMoMoSrories;
import mods.Hileb.moremomostories.common.world.advancements.Advancementkeys;
import mods.Hileb.moremomostories.common.world.blocks.ModBlocks;
import mods.Hileb.moremomostories.common.world.recipe.*;
import mods.Hileb.moremomostories.mods.ModLoadingPlugin;
import mods.Hileb.moremomostories.common.util.Reference;
import com.gq2529.momostories.item.ModItems;
import mods.flammpfeil.slashblade.SlashBlade;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class ModRecipes {
	public static IForgeRegistry<IRecipe> recipesRegister;
	
	
	public static void init() {
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
				GameRegistry.addSmelting(new ItemStack(mods.Hileb.moremomostories.common.world.item.ModItems.ITEM_DUCK_COOKED), new ItemStack(mods.Hileb.moremomostories.common.world.item.ModItems.ITEM_DUCK_KAO), 0.1f);
	}
	
	@SubscribeEvent
	public static void registerRecipes(RegistryEvent.Register<IRecipe> evt) {
		IForgeRegistry<IRecipe> r = evt.getRegistry();
		ModLoadingPlugin.updateModState();
		ModLoadingPlugin.recipeInit();
//		//Example
//		r.register(new GobletFill().setRegistryName(new ResourceLocation(Reference.MOD_ID, "goblet_fill")));
//
//		//
		//使用腐烂配方当作两个物品的无序配方
		r.register(new RecipePutrid(ModItems.THE_BOOK_OF_MANIFESTATION, mods.Hileb.moremomostories.common.world.item.ModItems.ITEM_PAPER_IDONOTWANTTODIE, mods.Hileb.moremomostories.common.world.item.ModItems.ITEM_SCENE_1).setRegistryName(new ResourceLocation(Reference.MOD_ID, String.format("recipe_emp_scene1"))));



		//氙石粉末机器配方
		r.register(new XeRecipesProduce().setRegistryName(new ResourceLocation(Reference.MOD_ID, String.format("recipe_xe"))));
		//氙石粉末混合配方
		r.register(new XeDustRecipe().setRegistryName(new ResourceLocation(Reference.MOD_ID, String.format("recipe_shapeless_mix_xe"))));
		//核心配方
		r.register(new MainRecipes("moremomostories.main",new ItemStack(mods.Hileb.moremomostories.common.world.item.ModItems.ITEM_MAIN_NULL)).setRegistryName(Reference.MOD_ID,"main_r"));
		//通行符节
		r.register(new FJRecipes("moremomostories.fj.recipe",new ItemStack(ModBlocks.BLOCK_TP_HILEB)).setRegistryName(Reference.MOD_ID,"tp_block_hileb_r"));
		//欺诈配方

		new AdvancementIRecipe<>(new FakeDiamondBottleRecipe().setRegistryName(new ResourceLocation("moremomostories", "recipe_diamond_bottle"))).setAdvancement(Advancementkeys.AD_GOODLUCK_MASTER).register(r);
		//r.register(new FakeDiamondBottleRecipe().setRegistryName(new ResourceLocation("moremomostories", "recipe_diamond_bottle")));
		//终极配方
		if (Loader.isModLoaded(SlashBlade.modid))r.register(new EndBladeRecipeSlashBlade(new ResourceLocation(MoreMoMoSrories.MODID,"moremomostories.fj.recipe")).setRegistryName(MoreMoMoSrories.MODID,"moremomostories.fj.recipe"));
		else r.register(new EndBladeRecipe(new ResourceLocation(MoreMoMoSrories.MODID,"moremomostories.fj.recipe")).setRegistryName(MoreMoMoSrories.MODID,"moremomostories.fj.recipe"));


		//批量注册的无序配方
		for(int i=0;i<RecipePutrid.PutridItems.size();i++){
			MoreMoMoSrories.LOGGER.info("{} is Putrid",RecipePutrid.PutridItems.get(i).getUnlocalizedName());
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
