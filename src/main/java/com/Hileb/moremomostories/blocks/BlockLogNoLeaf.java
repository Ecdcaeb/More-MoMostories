package com.Hileb.moremomostories.blocks;

//
//public class BlockLogNoLeaf extends BlockBase {
//    public static final PropertyEnum<EnumFacing.Axis> AXIS = PropertyEnum.<EnumFacing.Axis>create("axis", EnumFacing.Axis.class);
//
//    public BlockLogNoLeaf(String name){
//         super(name, Material.WOOD);
//        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockPlanks.EnumType.OAK).withProperty(LOG_AXIS, BlockLog.EnumAxis.Y));
//
//    }
//    @Override
//    public void registerModels() {
//        IdlFramework.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
//    }
//    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
//    {
//        return this.getStateFromMeta(meta).withProperty(AXIS, BlockLog.EnumAxis.fromFacingAxis(facing.getAxis()));
//    }
//}
