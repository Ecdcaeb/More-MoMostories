package com.Hileb.moremomostories.common.world.blocks;

import com.Hileb.forgedmomo.interfaces.IModelHolder;
import com.Hileb.moremomostories.MoreMoMoSrories;
import com.Hileb.moremomostories.common.init.ModCreativeTab;
import com.Hileb.moremomostories.common.world.item.ModItems;
import net.minecraft.block.BlockOre;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/9/10 22:33
 **/
public abstract class BlockOreBase extends BlockOre implements IModelHolder.IBlock{
    public BlockOreBase(String name,int levelIn,int hardness){
        super();
        setUnlocalizedName(MoreMoMoSrories.MODID+"."+name);
        setRegistryName(name);
        setCreativeTab(ModCreativeTab.IDL_MISC);

        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));

        setHarvestLevel("pickaxe", levelIn);
        setHardness(hardness);
        setLightOpacity(1);
    }

    @Override
    public abstract int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune);

    @Override
    public abstract void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune);
}
