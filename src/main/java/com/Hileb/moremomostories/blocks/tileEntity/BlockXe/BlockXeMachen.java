package com.Hileb.moremomostories.blocks.tileEntity.BlockXe;


import com.Hileb.moremomostories.blocks.BlockContainerBase;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockXeMachen extends BlockContainerBase {
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityXeMachen();
    }
    public BlockXeMachen(String name, Material material){
        super(name,material);
    }
    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this,FACING);
    }


}
