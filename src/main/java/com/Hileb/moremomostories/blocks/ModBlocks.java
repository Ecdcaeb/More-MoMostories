package com.Hileb.moremomostories.blocks;

import com.Hileb.moremomostories.blocks.tileEntity.BlockBookShelf;
import com.Hileb.moremomostories.item.myItems.ItemXe;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class ModBlocks {
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	public static final Material Obsidian =new Material(MapColor.BLACK);

	//public static final Block BLOCK_ENCHANTMENT_TABLE=new BlockAppleCake("apple_cake");
	//黑色石砖
	public static final Block BLOCK_BLACK_STONE_BRICK=new BlockBase("blackstonebrick", Material.ROCK);
	public static final Block BLOCK_HILEB_BLOCK=new BlockBase("unbreakable_hileb_block",Obsidian).setHardness(50.0F).setResistance(2000.0F);
	public static final Block BLOCK_WOOD_NO_LEAF=new BlockBase("wood_no_leaf",Material.WOOD){
		{Blocks.FIRE.setFireInfo(this,30,20);}
	};
	//public static final Block BLOCK_WOOD_NO_LEAF=new BlockLogNoLeaf("wood_no_leaf");


	public static final Block BLOCK_ORE_XE_RED=new BlockOreBase("block_stone_red",MapColor.STONE,null){
		@Override
		public void dropItemStack(World worldIn, BlockPos pos, int amount) {
			EntityItem entityitem = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), ItemXe.get(ItemXe.XeType.RED_0));
			entityitem.setPickupDelay(40);
			worldIn.spawnEntity(entityitem);
		}
	};
	public static final Block BLOCK_ORE_XE_BLACK =new BlockOreBase("block_stone_yellow",MapColor.STONE,null){
		@Override
		public void dropItemStack(World worldIn, BlockPos pos, int amount) {
			EntityItem entityitem = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), ItemXe.get(ItemXe.XeType.BLACK_0));
			entityitem.setPickupDelay(40);
			worldIn.spawnEntity(entityitem);
		}
	};
	public static final Block BLOCK_ORE_XE_BLUE=new BlockOreBase("block_stone_blue",MapColor.STONE,null){
		@Override
		public void dropItemStack(World worldIn, BlockPos pos, int amount) {
			EntityItem entityitem = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), ItemXe.get(ItemXe.XeType.BLUE_0));
			entityitem.setPickupDelay(40);
			worldIn.spawnEntity(entityitem);
		}
	};
	//public static final Block BLOCK_BLOCK_CASE=new BlockBookShelf("block_block_book_case",Material.WOOD);
	public static final Block BLOCK_END_BOOK_SHELF=new BlockEndBlockShelf("block_end_book_shelf");
	public static final Block BLOCK_TP_HILEB =new BlockTeleporter("block_tp_hileb",Material.ROCK);

	//public static final Block BLOCK_JUKE_BOX =new BlockJukebox("block_jukebox");
	public static final Block BLOCK_SHELF=new BlockBookShelf("block_book_shelf");

	public static final Block BLOCK_SHELF_E=new BlockEnchantmentTableHileb("block_book_shelf_inv");

	
	/*
	 * To add a block, put a line here,
	 * -Create a json at assets.eo.blockstates
	 * -Create a json at assets.eo.models.block
	 * -Create a json at assets.eo.models.item
	 * -Add corresponding texture png
	 */

	//public static final Block GRID_BLOCK_1 = new
	//public static final Block BLOCK_ENCHANTMENT_TABLE=new BlockEnchantmentTable

}
