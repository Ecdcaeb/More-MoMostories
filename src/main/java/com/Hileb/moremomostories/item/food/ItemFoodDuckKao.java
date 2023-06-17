package com.Hileb.moremomostories.item.food;

import com.Hileb.moremomostories.entity.entity.living.EntityVan;
import com.Hileb.moremomostories.entity.spawner.VanEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemFoodDuckKao extends ItemFoodBase{
    public ItemFoodDuckKao(String name){
        super(name,6,6,true);
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        super.onFoodEaten(stack, worldIn, player);
        if(!worldIn.isRemote){
            EntityVan van=new EntityVan(worldIn);
            VanEvent.spawnVan(worldIn,player);
        }
    }
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);

        if (true)
        {
            playerIn.setActiveHand(handIn);
            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
        }
        else
        {
            return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
        }
    }
}
