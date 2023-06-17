package com.Hileb.moremomostories.item.myItems;

import com.Hileb.moremomostories.init.ModConfig;
import com.Hileb.moremomostories.item.IEntityItemX;
import com.Hileb.moremomostories.item.ItemBase;
import com.Hileb.moremomostories.util.NBTStrDef.IDLNBTUtil;
import com.Hileb.moremomostories.util.Teleport;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.List;

public class ItemTP extends ItemBase implements IEntityItemX {
    public static final String NBT_X="tpX";
    public static final String NBT_Y="tpY";
    public static final String NBT_Z="tpZ";
    public static final String NBT_DIM="tpD";

    public ItemTP(String name) {
        super(name);
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand) {
        ItemStack stack=player.getHeldItem(hand);
        if (!world.isRemote){


            int x;
            int y;
            int z;
            x=(int) player.posX;
            y=(int) player.posY;
            z=(int) player.posZ;
            int dim=player.world.provider.getDimension();

            int tx= IDLNBTUtil.GetInt(stack,NBT_X,32);
            int tz= IDLNBTUtil.GetInt(stack,NBT_Z,32);
            int ty= IDLNBTUtil.GetInt(stack,NBT_Y,65);
            int td= IDLNBTUtil.GetInt(stack,NBT_DIM,ModConfig.dimension.WORLD_GEN_ZFP);

            IDLNBTUtil.SetInt(stack,NBT_X,x);
            IDLNBTUtil.SetInt(stack,NBT_Y,y);
            IDLNBTUtil.SetInt(stack,NBT_Z,z);
            IDLNBTUtil.SetInt(stack,NBT_DIM,dim);

            try {
                Teleport.teleportToDim(player,td,tx,ty,tz);
                return ActionResult.newResult(EnumActionResult.SUCCESS,stack);
            }catch (Exception e){
                player.sendMessage(new TextComponentString(e.toString()).setStyle(new Style().setColor(TextFormatting.RED)));
                return ActionResult.newResult(EnumActionResult.PASS,stack);
            }
        }
        return ActionResult.newResult(EnumActionResult.PASS,stack);
    }

    @Override
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
        super.addInformation(stack, world, tooltip, flag);
        int tx= IDLNBTUtil.GetInt(stack,NBT_X,32);
        int tz= IDLNBTUtil.GetInt(stack,NBT_Z,32);
        int ty= IDLNBTUtil.GetInt(stack,NBT_Y,65);
        int td= IDLNBTUtil.GetInt(stack,NBT_DIM,ModConfig.dimension.WORLD_GEN_ZFP);

        tooltip.add(String.format("  X:%d",tx));
        tooltip.add(String.format("  Y:%d",ty));
        tooltip.add(String.format("  Z:%d",tz));
        tooltip.add(String.format("DIM:%d",td));
    }
}
