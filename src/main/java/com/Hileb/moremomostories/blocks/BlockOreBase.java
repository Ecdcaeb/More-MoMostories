package com.Hileb.moremomostories.blocks;

import com.Hileb.moremomostories.IdlFramework;
import com.Hileb.moremomostories.init.ModCreativeTab;
import com.Hileb.moremomostories.item.ModItems;
import com.Hileb.moremomostories.util.IHasModel;
import net.minecraft.block.BlockOre;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockOreBase extends BlockOre implements IHasModel {
    private final Item itemDrop;
    private final boolean hasItemDrop;
    public final Item itemBlock;
    public BlockOreBase(String name,MapColor color,Item _itemIn)
    {
        super(color);
        itemDrop=_itemIn;
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(ModCreativeTab.IDL_MISC);

        ModBlocks.BLOCKS.add(this);
        itemBlock=new ItemBlock(this).setRegistryName(this.getRegistryName());
        ModItems.ITEMS.add(itemBlock);
        ModItems.BLOCK_ITEMS.add(itemBlock);

        setHardness(5.0F);
        //setResistance(1000.0F);
        //setHarvestLevel("pickaxe", 1);
        //setLightLevel(1f);
        setLightOpacity(1);
        hasItemDrop=true;
    }
    public BlockOreBase(String name,MapColor color)
    {
        super(color);
        itemDrop=null;
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(ModCreativeTab.IDL_MISC);

        ModBlocks.BLOCKS.add(this);
        itemBlock=new ItemBlock(this).setRegistryName(this.getRegistryName());
        ModItems.ITEMS.add(itemBlock);
        ModItems.BLOCK_ITEMS.add(itemBlock);

        setHardness(5.0F);
        //setResistance(1000.0F);
        //setHarvestLevel("pickaxe", 1);
        //setLightLevel(1f);
        setLightOpacity(1);
        hasItemDrop=false;
    }

    @Override
    public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state) {
        super.onBlockDestroyedByPlayer(worldIn, pos, state);
        if (!worldIn.isRemote){
            dropXpOnBlockBreak(worldIn,pos,5);
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        if (hasItemDrop)return itemDrop;
        else return Item.getItemFromBlock(this);
    }
    @Override
    public void dropXpOnBlockBreak(World worldIn, BlockPos pos, int amount) {
        //To tell the truth,it is a good way to drop something.
        super.dropXpOnBlockBreak(worldIn, pos, amount);
        dropItemStack(worldIn,pos,amount);

    }
    public void dropItemStack(World worldIn, BlockPos pos, int amount){

    }

    @Override
    public int quantityDropped(Random rand) {
        return super.quantityDropped(rand);
    }

    @Override
    public void registerModels() {
        IdlFramework.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    @Override
    public boolean isBeaconBase(IBlockAccess worldObj, BlockPos pos, BlockPos beacon) {
        return false;
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        //
    }
    public Item getItem() {
        return itemBlock;
    }
}
