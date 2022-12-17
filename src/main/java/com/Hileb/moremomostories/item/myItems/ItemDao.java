package com.Hileb.moremomostories.item.myItems;

import com.Hileb.moremomostories.item.ItemBase;
import com.Hileb.moremomostories.util.NBTStrDef.IDLNBTUtil;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ItemDao extends ItemBase {
    public ItemDao(String name){
        super(name);
        this.addPropertyOverride(new ResourceLocation("com.hileb.moremomostories.item.dao"), new IItemPropertyGetter()
        {
            @SideOnly(Side.CLIENT)
            double rotation;
            @SideOnly(Side.CLIENT)
            double rota;
            @SideOnly(Side.CLIENT)
            long lastUpdateTick;
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                if(isClosed(stack))return 0f;
                else return 1.0f;
            }
        });
    }
    public static boolean isClosed(ItemStack stack){
        if (!stack.hasTagCompound())return true;
        if (stack.getItem() instanceof ItemDao){
            return (IDLNBTUtil.GetBoolean(stack,"com.hileb.nbt.closed",true));
        }
        else return false;
    }
    public static void setClosed(ItemStack stack,boolean isClosed){
        IDLNBTUtil.SetBoolean(stack,"com.hileb.nbt.closed",isClosed);
    }
    public static void changeState(ItemStack stack){
        setClosed(stack,!isClosed(stack));
    }

//    @Override
//    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
//        if (!worldIn.isRemote){
//            changeState(player.getHeldItem(hand));
//        }
//        return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
//    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand) {
        if (!world.isRemote){
            changeState(player.getHeldItem(hand));
        }
        return super.onItemRightClick(world,player,hand);
    }
}
