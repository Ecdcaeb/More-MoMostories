package com.Hileb.moremomostories.common.world.blocks;

import com.Hileb.forgedmomo.interfaces.IModelHolder;
import com.Hileb.moremomostories.MoreMoMoSrories;
import com.Hileb.moremomostories.common.init.ModCreativeTab;
import com.Hileb.moremomostories.common.world.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import java.util.Random;

public class BlockBase extends Block implements IModelHolder.IBlock
{
	public BlockBase(String name, Material material)
	{
		super(material);
		setUnlocalizedName(MoreMoMoSrories.MODID+"."+name);
		setRegistryName(name);
		setCreativeTab(ModCreativeTab.IDL_MISC);
		
		ModBlocks.BLOCKS.add(this);
		Item itemBlock=new ItemBlock(this).setRegistryName(this.getRegistryName());
		ModItems.ITEMS.add(itemBlock);
		ModItems.BLOCK_ITEMS.add(itemBlock);

		setHardness(5.0F);
		//setResistance(1000.0F);
		//setHarvestLevel("pickaxe", 1);
		//setLightLevel(1f);
		setLightOpacity(1);
		//getItemBlock();
	}


	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return super.getItemDropped(state, rand, fortune);
	}

	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		//super.getSubBlocks(itemIn, items);
	}

	@Override
	public int quantityDropped(Random rand) {
		return super.quantityDropped(rand);
	}
	


	@Override
	public boolean isBeaconBase(IBlockAccess worldObj, BlockPos pos, BlockPos beacon) {
		return false;
	}
}
