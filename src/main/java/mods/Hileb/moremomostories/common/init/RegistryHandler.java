package mods.Hileb.moremomostories.common.init;

import mods.Hileb.moremomostories.client.sound.ModSoundHandler;
import mods.Hileb.moremomostories.common.world.blocks.ModBlocks;
import mods.Hileb.moremomostories.common.world.blocks.tile.ModTileEntities;
import mods.Hileb.moremomostories.common.world.enchantments.ModEnchantmentInit;
import mods.Hileb.moremomostories.common.world.entity.ModEntityInit;
import mods.Hileb.moremomostories.common.world.item.ModItems;
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
