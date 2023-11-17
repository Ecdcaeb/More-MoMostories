package mods.Hileb.moremomostories.common.world.blocks;

import mods.Hileb.forgedmomo.interfaces.IModelHolder;
import mods.Hileb.moremomostories.MoreMoMoSrories;
import mods.Hileb.moremomostories.common.init.ModCreativeTab;
import mods.Hileb.moremomostories.common.world.gui.ModGuiElementLoader;
import mods.Hileb.moremomostories.common.world.item.ModItems;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockEnchantmentTableHileb extends net.minecraft.block.BlockEnchantmentTable implements IModelHolder.IBlock {
    public BlockEnchantmentTableHileb(String name){
        super();
        setUnlocalizedName(MoreMoMoSrories.MODID+"."+name);
        setRegistryName(name);
        setCreativeTab(ModCreativeTab.IDL_MISC);

        ModBlocks.BLOCKS.add(this);
        Item itemBlock=new ItemBlock(this).setRegistryName(this.getRegistryName());
        ModItems.ITEMS.add(itemBlock);
        ModItems.BLOCK_ITEMS.add(itemBlock);

        setHardness(5.0F);
        setLightOpacity(1);
    }
    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
    }

    @Override
    public int quantityDropped(Random rand) {
        return super.quantityDropped(rand);
    }
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (worldIn.isRemote)
        {
            return true;
        }
        else
        {
            playerIn.openGui(MoreMoMoSrories.instance, ModGuiElementLoader.GUI_ENCH,worldIn,pos.getX(),pos.getY(),pos.getZ());
            //playerIn.addStat(StatList.CRAFTING_TABLE_INTERACTION);
            return true;
        }
    }
}
