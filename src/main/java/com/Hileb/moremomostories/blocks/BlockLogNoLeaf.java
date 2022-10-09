package com.Hileb.moremomostories.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockLogNoLeaf extends BlockBase {
    //public static final PropertyEnum<BlockLogNoLeaf.EnumAxis> LOG_AXIS = PropertyEnum.<BlockLogNoLeaf.EnumAxis>create("axis", BlockLogNoLeaf.EnumAxis.class);
    public BlockLogNoLeaf(String name){
        super(name, Material.WOOD);
        this.setHardness(2.0F);
        this.setSoundType(SoundType.WOOD);
    }
//    /**
//     * Called serverside after this block is replaced with another in Chunk, but before the Tile Entity is updated
//     */
//    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
//    {
//        int i = 4;
//        int j = 5;
//
//        if (worldIn.isAreaLoaded(pos.add(-5, -5, -5), pos.add(5, 5, 5)))
//        {
//            for (BlockPos blockpos : BlockPos.getAllInBox(pos.add(-4, -4, -4), pos.add(4, 4, 4)))
//            {
//                IBlockState iblockstate = worldIn.getBlockState(blockpos);
//
//                if (iblockstate.getBlock().isLeaves(iblockstate, worldIn, blockpos))
//                {
//                    iblockstate.getBlock().beginLeavesDecay(iblockstate, worldIn, blockpos);
//                }
//            }
//        }
//    }
//
//    /**
//     * Called by ItemBlocks just before a block is actually set in the world, to allow for adjustments to the
//     * IBlockstate
//     */
//    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
//    {
//        return this.getStateFromMeta(meta).withProperty(LOG_AXIS, BlockLogNoLeaf.EnumAxis.fromFacingAxis(facing.getAxis()));
//    }
//
//    /**
//     * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
//     * blockstate.
//     */
//    public IBlockState withRotation(IBlockState state, Rotation rot)
//    {
//        switch (rot)
//        {
//            case COUNTERCLOCKWISE_90:
//            case CLOCKWISE_90:
//
//                switch ((BlockLogNoLeaf.EnumAxis)state.getValue(LOG_AXIS))
//                {
//                    case X:
//                        return state.withProperty(LOG_AXIS, BlockLogNoLeaf.EnumAxis.Z);
//                    case Z:
//                        return state.withProperty(LOG_AXIS, BlockLogNoLeaf.EnumAxis.X);
//                    default:
//                        return state;
//                }
//
//            default:
//                return state;
//        }
//    }
//
//    @Override public boolean canSustainLeaves(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos){ return true; }
//    @Override public boolean isWood(net.minecraft.world.IBlockAccess world, BlockPos pos){ return true; }
//
//    public static enum EnumAxis implements IStringSerializable
//    {
//        X("x"),
//        Y("y"),
//        Z("z"),
//        NONE("none");
//
//        private final String name;
//
//        private EnumAxis(String name)
//        {
//            this.name = name;
//        }
//
//        public String toString()
//        {
//            return this.name;
//        }
//
//        public static BlockLogNoLeaf.EnumAxis fromFacingAxis(EnumFacing.Axis axis)
//        {
//            switch (axis)
//            {
//                case X:
//                    return X;
//                case Y:
//                    return Y;
//                case Z:
//                    return Z;
//                default:
//                    return NONE;
//            }
//        }
//
//        public String getName()
//        {
//            return this.name;
//        }
//    }
}
