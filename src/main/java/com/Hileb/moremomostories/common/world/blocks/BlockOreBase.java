package com.Hileb.moremomostories.common.world.blocks;

import com.Hileb.moremomostories.MoreMoMoSrories;
import com.Hileb.moremomostories.common.init.ModCreativeTab;
import com.Hileb.moremomostories.common.util.IHasModel;
import com.Hileb.moremomostories.common.world.item.ModItems;
import com.Hileb.moremomostories.common.world.item.myItems.ItemXe;
import net.minecraft.block.BlockOre;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/9/10 22:33
 **/
public abstract class BlockOreBase extends BlockOre implements IHasModel {
    public BlockOreBase(String name,int levelIn,int hardness){
        super();
        setUnlocalizedName(name);
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

    public void registerModels() {
        MoreMoMoSrories.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}
