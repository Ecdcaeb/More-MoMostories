package com.Hileb.moremomostories.common.init;

import com.Hileb.moremomostories.common.util.ModSoundHandler;
import com.Hileb.moremomostories.common.world.blocks.ModBlocks;
import com.Hileb.moremomostories.common.world.blocks.tile.ModTileEntities;
import com.Hileb.moremomostories.common.world.enchantments.ModEnchantmentInit;
import com.Hileb.moremomostories.common.world.entity.ModEntityInit;
import com.Hileb.moremomostories.common.world.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class RegistryHandler {


	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
	}
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
		ModTileEntities.REGISTRY.registerAll();
	}

	@SubscribeEvent
	public static void onEnchantmentRegister(RegistryEvent.Register<Enchantment> event)
	{
		ModEnchantmentInit.BeforeRegister();
		event.getRegistry().registerAll(ModEnchantmentInit.ENCHANTMENT_LIST.toArray(new Enchantment[0]));
	}

//	@SideOnly(Side.CLIENT)
//	@SubscribeEvent
//	public static void onModelRegister(ModelRegistryEvent event)
//	{
//		for(Item item : ModItems.ITEMS)
//		{
//			if (item instanceof IHasModel)
//			{
//				((IHasModel)item).registerModelsDeprecated();
//			}
//		}
//
//		for(Block block : ModBlocks.BLOCKS)
//		{
//			if (block instanceof IHasModel)
//			{
//				((IHasModel)block).registerModelsDeprecated();
//			}
//		}
//		RenderHandler.registerEntityRenders();
//	}

	public static void preInitRegistries(FMLPreInitializationEvent event)
	{
		ModGenInit.init(event);
		ModBiomes.registerBiomes();
		ModDimensions.registerDimensions();

		ModEntityInit.registerEntities();
	}

	public static void postInitReg()
	{
		//WorldType TYPE_ONE = new WorldTypeOne();
	}

	public static void initRegistries(FMLInitializationEvent event)
	{
		ModSoundHandler.soundRegister();
	}

	public static void serverRegistries(FMLServerStartingEvent event)
    {
        //event.registerServerCommand(new CommandDimTeleport());
    }
}
