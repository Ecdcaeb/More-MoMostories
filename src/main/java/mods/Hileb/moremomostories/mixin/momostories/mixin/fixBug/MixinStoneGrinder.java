package mods.Hileb.moremomostories.mixin.momostories.mixin.fixBug;

import mods.Hileb.moremomostories.mixin.momostories.eventExtra.MoMoStoriesMixinContainer;
import com.gq2529.momostories.MoMoFramework;
import com.gq2529.momostories.blocks.ModBlock.StoneGrinder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

import javax.annotation.Nullable;
import java.util.List;
/*
* TODO:使用mixin将一个方块改为多state方块*/
@SuppressWarnings("all")
@Mixin(StoneGrinder.class)
public abstract class MixinStoneGrinder extends Block{

    public MixinStoneGrinder(Material materialIn) {
        super(materialIn);
    }

    @Override
    public BlockStateContainer createBlockState() {
        MoMoFramework.LogWarning("moremomostories : fix bug variety of stoneGrinder");
        return new BlockStateContainer(this, BlockHorizontal.FACING);
    }


    //mixin bug in building? use
    public AxisAlignedBB func_185496_a(IBlockState state, IBlockAccess source, BlockPos pos) {
        if (state.getValue(BlockHorizontal.FACING)==EnumFacing.NORTH || state.getValue(BlockHorizontal.FACING)==EnumFacing.SOUTH)return MoMoStoriesMixinContainer.AABB_BLOCK1;
        else return MoMoStoriesMixinContainer.AABB_BLOCK2;
    }

    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }


    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return BlockFaceShape.UNDEFINED;
    }

    @Override
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState) {
        addCollisionBoxToList(pos, entityBox, collidingBoxes, state.getCollisionBoundingBox(worldIn, pos));
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        EnumFacing enumfacing = EnumFacing.getHorizontal(MathHelper.floor((double) (placer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3).getOpposite();
        state = state.withProperty(BlockHorizontal.FACING, enumfacing);
        super.onBlockPlacedBy(worldIn,pos,state,placer,stack);
    }
    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getStateFromMeta(meta).withProperty(BlockHorizontal.FACING, placer.getHorizontalFacing());
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing facing=EnumFacing.getHorizontal(meta);
        return getDefaultState().withProperty(BlockHorizontal.FACING,facing);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int key=state.getValue(BlockHorizontal.FACING).getHorizontalIndex();
        if (key==-1){
            return 0;
        }
        else return key;
    }




    /*
    file: stone_grinder.json

    {
    "variants": {
        "facing=east": { "model": "momostories:stone_grinder","y":90 ,"uvlock":true},
        "facing=north": { "model": "momostories:stone_grinder","y":0 ,"uvlock":true},
        "facing=south": { "model": "momostories:stone_grinder","y":180,"uvlock":true },
        "facing=west": { "model": "momostories:stone_grinder" ,"y":270,"uvlock":true}
        }
    }



     */


}
