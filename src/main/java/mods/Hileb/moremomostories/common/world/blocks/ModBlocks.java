package mods.Hileb.moremomostories.common.world.blocks;

import mods.Hileb.moremomostories.common.world.blocks.tile.bookshelf.BlockBookShelf;
import mods.Hileb.moremomostories.common.world.item.myItems.ItemXe;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

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


	public static final Block BLOCK_ORE_XE_RED=new BlockOreBase("block_stone_red",2,5){
		@Override
		public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune) {
			return 5;
		}

		@Override
		public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
			drops.add(ItemXe.get(ItemXe.XeType.RED_0));
		}
	};
	public static final Block BLOCK_ORE_XE_BLACK =new BlockOreBase("block_stone_yellow",2,5){
		@Override
		public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune) {
			return 5;
		}

		@Override
		public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
			drops.add(ItemXe.get(ItemXe.XeType.BLACK_0));
		}
	};
	public static final Block BLOCK_ORE_XE_BLUE=new BlockOreBase("block_stone_blue",2,5){

		@Override
		public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune) {
			return 5;
		}

		@Override
		public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
			drops.add(ItemXe.get(ItemXe.XeType.BLUE_0));
		}
	};
	//public static final Block BLOCK_BLOCK_CASE=new BlockBookShelf("block_block_book_case",Material.WOOD);
	public static final Block BLOCK_END_BOOK_SHELF=new BlockEndBlockShelf("block_end_book_shelf");
	public static final Block BLOCK_TP_HILEB =new BlockTeleporter("block_tp_hileb",Material.ROCK);

	//public static final Block BLOCK_JUKE_BOX =new BlockJukebo("block_jukebox");
	public static final Block BLOCK_SHELF=new BlockBookShelf("block_book_shelf");

	//public static final Block BLOCK_SHELF_E=new BlockEnchantmentTableHileb("block_book_shelf_inv");
	public static final Block BLOCK_CAKE=new BlockInfinityCake();


	
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
