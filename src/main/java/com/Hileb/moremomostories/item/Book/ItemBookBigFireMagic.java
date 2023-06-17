package com.Hileb.moremomostories.item.Book;

import com.Hileb.moremomostories.item.ItemBase;

public class ItemBookBigFireMagic extends ItemBase {
    public ItemBookBigFireMagic(String name){
        super(name);
        setDesc("book.name.com.hileb.momo.llf.name","book.name.com.hileb.momo.llf.name");
    }
//    public void setUpAFire(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ)
//    {
//        pos = pos.offset(facing);
//        ItemStack itemstack = player.getHeldItem(hand);
//
//        if (!player.canPlayerEdit(pos, facing, itemstack))
//        {
//            return EnumActionResult.FAIL;
//        }
//        else
//        {
//            if (worldIn.isAirBlock(pos))
//            {
//                worldIn.playSound(player, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
//                worldIn.setBlockState(pos, Blocks.FIRE.getDefaultState(), 11);
//            }
//
//            if (player instanceof EntityPlayerMP)
//            {
//                CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)player, pos, itemstack);
//            }
//
//            itemstack.damageItem(1, player);
//            return EnumActionResult.SUCCESS;
//        }
//    }
}
