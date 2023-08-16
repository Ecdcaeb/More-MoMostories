package com.Hileb.moremomostories.common.world.blocks;

import com.Hileb.moremomostories.MoreMoMoSrories;
import com.Hileb.moremomostories.common.init.ModCreativeTab;
import com.Hileb.moremomostories.common.world.item.ModItems;
import com.Hileb.moremomostories.common.util.IHasModel;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

public abstract class BlockContainerBase extends BlockContainer implements IHasModel{
    public final Item itemBlock;
    public BlockContainerBase(String name, Material material){
        super(material);
        this.hasTileEntity = true;
        //BlockBase
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
        //getItemBlock();
    }
    @Override
    public void registerModels() {
        MoreMoMoSrories.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
    public TileEntity createNewTileEntity(World worldIn, int meta){
        return null;
    }
    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        //super.getSubBlocks(itemIn, items);
    }
}
