package com.Hileb.moremomostories.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class ModBlocks {
	public static final List<Block> BLOCKS = new ArrayList<Block>();

	//public static final Block BLOCK_ENCHANTMENT_TABLE=new BlockAppleCake("apple_cake");
	//黑色石砖
	public static final Block BLOCK_BLACK_STONE_BRICK=new BlockBase("blackstonebrick", Material.ROCK);
	public static final Block BLOCK_HILEB_BLOCK=new BlockBase("hilebbrick", Material.ROCK);
	public static final Block BLOCK_WOOD_NO_LEAF=new BlockLogNoLeaf("wood_no_leaf");
	
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
