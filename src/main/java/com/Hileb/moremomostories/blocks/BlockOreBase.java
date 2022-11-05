package com.Hileb.moremomostories.blocks;

import com.Hileb.moremomostories.IdlFramework;
import com.Hileb.moremomostories.init.ModCreativeTab;
import com.Hileb.moremomostories.item.ModItems;
import com.Hileb.moremomostories.util.IHasModel;
import net.minecraft.block.BlockOre;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import java.util.Random;

public class BlockOreBase extends BlockOre implements IHasModel {
    private final Item itemDrop;
    private final boolean hasItemDrop;
    public BlockOreBase(String name,MapColor color,Item _itemIn)
    {
        super(color);
        itemDrop=_itemIn;
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(ModCreativeTab.IDL_MISC);

        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));

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
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));

        setHardness(5.0F);
        //setResistance(1000.0F);
        //setHarvestLevel("pickaxe", 1);
        //setLightLevel(1f);
        setLightOpacity(1);
        hasItemDrop=false;
    }
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        if (hasItemDrop)return itemDrop;
        else return Item.getItemFromBlock(this);
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
}
