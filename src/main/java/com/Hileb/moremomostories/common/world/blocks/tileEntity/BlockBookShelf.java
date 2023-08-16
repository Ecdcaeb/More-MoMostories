package com.Hileb.moremomostories.common.world.blocks.tileEntity;


import com.Hileb.moremomostories.MoreMoMoSrories;
import com.Hileb.moremomostories.common.world.blocks.BlockContainerBase;
import com.Hileb.moremomostories.common.world.gui.ModGuiElementLoader;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBookShelf extends BlockContainerBase {
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityBookShelf();
    }
    public BlockBookShelf(String name){
        super(name,Material.WOOD);
    }

    @Override
    public boolean isWood(IBlockAccess world, BlockPos pos) {
        return true;
    }
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (worldIn.isRemote)
        {
            return true;
        }
        else
        {
            playerIn.openGui(MoreMoMoSrories.instance, ModGuiElementLoader.GUI_CHEST,worldIn,pos.getX(),pos.getY(),pos.getZ());
            //playerIn.addStat(StatList.CRAFTING_TABLE_INTERACTION);
            return true;
        }
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }
}
