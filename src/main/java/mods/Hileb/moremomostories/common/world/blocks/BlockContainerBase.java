package mods.Hileb.moremomostories.common.world.blocks;

import mods.Hileb.forgedmomo.interfaces.IModelHolder;
import mods.Hileb.moremomostories.MoreMoMoSrories;
import mods.Hileb.moremomostories.common.init.ModCreativeTab;
import mods.Hileb.moremomostories.common.world.item.ModItems;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

public abstract class BlockContainerBase extends BlockContainer implements IModelHolder.IBlock {
    public final Item itemBlock;
    public BlockContainerBase(String name, Material material){
        super(material);
        this.hasTileEntity = true;
        //BlockBase
        setUnlocalizedName(MoreMoMoSrories.MODID+"."+name);
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
    public TileEntity createNewTileEntity(World worldIn, int meta){
        return null;
    }
    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        //super.getSubBlocks(itemIn, items);
    }
}
