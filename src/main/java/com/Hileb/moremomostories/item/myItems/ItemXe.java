package com.Hileb.moremomostories.item.myItems;

import com.Hileb.moremomostories.item.ItemBase;
import com.Hileb.moremomostories.item.ModItems;
import com.Hileb.moremomostories.util.NBTStrDef.IDLNBTUtil;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

//net.minecraft.client.renderer.color.ItemColors//控制颜色
public class ItemXe extends ItemBase {
    public static final RGBColor COLOR_NULL=new RGBColor(0xf000000);
    public static final RGBColor COLOR_EMPTY=new RGBColor(0x000000);
    public static final RGBColor COLOR_RED=new RGBColor(0xff0000);
    public static final RGBColor COLOR_BLUE=new RGBColor(0x0000ff);
    public static final RGBColor COLOR_BLACK=new RGBColor(0xffffff);
    public static final String NBT_XE_LVL="com.hileb.moremomostories.nbttag.XeLvL";
    public static final String NBT_XE_COLOR_R="com.hileb.moremomostories.nbttag.XeColor.red";
    public static final String NBT_XE_COLOR_G="com.hileb.moremomostories.nbttag.XeColor.green";
    public static final String NBT_XE_COLOR_B="com.hileb.moremomostories.nbttag.XeColor.blue";
    public ItemXe(String name){
        super(name);
        this.addPropertyOverride(new ResourceLocation("com.hileb.moremomostories.item.xe.color"), new IItemPropertyGetter()
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
                if (getLevel(stack)==-1){
                    return 0.1f;
                }
                if (getLevel(stack)==0){
                    if (isRed(stack)){ return 0.2f;}
                    if (isBlue(stack)){return 0.3f;}
                    if (isBlack(stack)){return 0.4f;}
                    if (isOther(stack)){return 0.1f;}
                }
                else if(getLevel(stack)>0){
                    if (isRed(stack)){return 0.1f;}
                    if (isBlue(stack)){return 0.1f;}
                    if (isBlack(stack)){return 0.1f;}
                    if (isOther(stack)){return 0.1f;}
                }
                return 0.1f;
            }
        });
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
        changeName(stack);
    }
    public static void changeName(ItemStack stack){
            if (getLevel(stack)==-1){
                stack.setTranslatableName("com.hileb.moremomostories.item.xe.empty.name");
            }
            if (getLevel(stack)==0){
                if (isRed(stack)){ stack.setTranslatableName("com.hileb.moremomostories.item.xe.red.name"); }
                if (isBlack(stack)){stack.setTranslatableName("com.hileb.moremomostories.item.xe.black.name");}
                if (isBlue(stack)){stack.setTranslatableName("com.hileb.moremomostories.item.xe.blue.name");}
                if (isOther(stack)){stack.setTranslatableName("com.hileb.moremomostories.item.xe.other.name");}
                if (ItemXe.getRGBColor(stack).color>=(256*256*256) || ItemXe.getRGBColor(stack).color<0){stack.setTranslatableName("com.hileb.moremomostories.item.xe.unknown.name");}
            }
            else if(getLevel(stack)>0){
                if (isRed(stack)){stack.setTranslatableName("com.hileb.moremomostories.item.xe.stone.red.name");}
                if (isBlack(stack)){stack.setTranslatableName("com.hileb.moremomostories.item.xe.stone.black.name");}
                if (isBlue(stack)){stack.setTranslatableName("com.hileb.moremomostories.item.xe.stone.blue.name");}
                if (isOther(stack)){stack.setTranslatableName("com.hileb.moremomostories.item.xe.stone.other.name");}
                if (ItemXe.getRGBColor(stack).color>=(256*256*256) || ItemXe.getRGBColor(stack).color<0){stack.setTranslatableName("com.hileb.moremomostories.item.xe.stone.unknown.name");}
            }

    }

//    @Override
//    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
//        //super.getSubItems(tab, items);
//        if(tab== ModCreativeTab.IDL_MISC){
//            ItemStack lvl_f=new ItemStack(this);
//            ItemStack lvl_0r=new ItemStack(this);
//            ItemStack lvl_0b=new ItemStack(this);
//            ItemStack lvl_0bx=new ItemStack(this);
//            ItemStack lvl_1r=new ItemStack(this);
//            ItemStack lvl_1b=new ItemStack(this);
//            ItemStack lvl_1bx=new ItemStack(this);
//
//            setLeveL(lvl_f,-1);
//            setRGBColor(lvl_f,COLOR_EMPTY);
//
//            setLeveL(lvl_0r,0);
//            setRGBColor(lvl_0r,COLOR_RED);
//            setLeveL(lvl_0b,0);
//            setRGBColor(lvl_0b,COLOR_BLUE);
//            setLeveL(lvl_0bx,0);
//            setRGBColor(lvl_0bx,COLOR_BLACK);
//
//            setLeveL(lvl_1b,1);
//            setRGBColor(lvl_1b,COLOR_RED);
//            setLeveL(lvl_1r,1);
//            setRGBColor(lvl_1r,COLOR_BLUE);
//            setLeveL(lvl_1bx,1);
//            setRGBColor(lvl_1bx,COLOR_BLACK);
//
//            changeName(lvl_f);
//            changeName(lvl_0r);
//            changeName(lvl_0b);
//            changeName(lvl_0bx);
//            changeName(lvl_1r);
//            changeName(lvl_1b);
//            changeName(lvl_1bx);
//
//            items.add(lvl_f.copy());
//            items.add(lvl_0r.copy());
//            items.add(lvl_0b.copy());
//            items.add(lvl_0bx.copy());
//            items.add(lvl_1r.copy());
//            items.add(lvl_1b.copy());
//            items.add(lvl_1bx.copy());
//        }
////        ItemStack empty_lv0=new ItemStack(this);
////        empty_lv0.setTranslatableName("com.hileb.moremomostories.item.xe.empty.name");
////        IDLNBTUtil.SetInt(empty_lv0,NBT_XE_LVL,-1);
////        IDLNBTUtil.SetInt(empty_lv0,NBT_XE_Color,XeType.XE_EMPTY);
////        items.add(empty_lv0);
//    }
    public static RGBColor getRGBColor(ItemStack stack){
        if (stack.hasTagCompound()){
            if (stack.getTagCompound().hasKey(NBT_XE_COLOR_R) && IDLNBTUtil.GetInt(stack,NBT_XE_COLOR_R)<=255){
                if(stack.getTagCompound().hasKey(NBT_XE_COLOR_G) && IDLNBTUtil.GetInt(stack,NBT_XE_COLOR_G)<=255){
                    if (stack.getTagCompound().hasKey(NBT_XE_COLOR_B) && IDLNBTUtil.GetInt(stack,NBT_XE_COLOR_B)<=255){
                        return new RGBColor(IDLNBTUtil.GetInt(stack,NBT_XE_COLOR_R),IDLNBTUtil.GetInt(stack,NBT_XE_COLOR_G),IDLNBTUtil.GetInt(stack,NBT_XE_COLOR_B));
                    }
                }
            }
        }
        return COLOR_NULL;
    }
    public static void setRGBColor(ItemStack stack,RGBColor color){
        IDLNBTUtil.SetInt(stack,NBT_XE_COLOR_R,color.getRed());
        IDLNBTUtil.SetInt(stack,NBT_XE_COLOR_G,color.getGreen());
        IDLNBTUtil.SetInt(stack,NBT_XE_COLOR_B,color.getBlue());
    }
    public static boolean isRed(ItemStack stack){
        return getRGBColor(stack).color==COLOR_RED.color;
    }
    public static boolean isBlue(ItemStack stack){
        return getRGBColor(stack).color==COLOR_BLUE.color;
    }
    public static boolean isBlack(ItemStack stack){
        return getRGBColor(stack).color==COLOR_BLACK.color;
    }
    public static boolean isOther(ItemStack stack){
        return !(isRed(stack)||isBlue(stack)||isBlack(stack));
    }
    public static int getLevel(ItemStack stack){
        if (stack.hasTagCompound()){
            if (stack.getTagCompound().hasKey(NBT_XE_LVL)){
                if(stack.getTagCompound().hasKey(NBT_XE_COLOR_G)){
                }
                return IDLNBTUtil.GetInt(stack,NBT_XE_LVL);
            }
        }
        return 0;
    }
    public static void setLeveL(ItemStack stack,int lvl){
        IDLNBTUtil.SetInt(stack,NBT_XE_LVL,lvl);
    }

    @Override
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
        super.addInformation(stack, world, tooltip, flag);
        tooltip.add(I18n.format("com.hileb.moremomostories.item.xe.stone.level.name",getLevel(stack)));
        tooltip.add(I18n.format("com.hileb.moremomostories.item.xe.stone.color.name",getRGBColor(stack).color));
    }
    public static class XeType{
        public static final int EMPTY=0;
        public static final int RED_0=1;
        public static final int RED_1=2;
        public static final int BLUE_0=3;
        public static final int BLUE_1=4;
        public static final int BLACK_0=5;
        public static final int BLACK_1=6;
    }
    public static ItemStack get(int xeTypeIn){
        return get(xeTypeIn,ModItems.ITEM_XE);
    }
    public static ItemStack get(int xeTypeIn, Item item){
        ItemStack lvl_f=new ItemStack(item);
        ItemStack lvl_0r=new ItemStack(item);
        ItemStack lvl_0b=new ItemStack(item);
        ItemStack lvl_0bx=new ItemStack(item);
        ItemStack lvl_1r=new ItemStack(item);
        ItemStack lvl_1b=new ItemStack(item);
        ItemStack lvl_1bx=new ItemStack(item);

        setLeveL(lvl_f,-1);
        setRGBColor(lvl_f,COLOR_EMPTY);

        setLeveL(lvl_0r,0);
        setRGBColor(lvl_0r,COLOR_RED);
        setLeveL(lvl_0b,0);
        setRGBColor(lvl_0b,COLOR_BLUE);
        setLeveL(lvl_0bx,0);
        setRGBColor(lvl_0bx,COLOR_BLACK);

        setLeveL(lvl_1b,1);
        setRGBColor(lvl_1b,COLOR_RED);
        setLeveL(lvl_1r,1);
        setRGBColor(lvl_1r,COLOR_BLUE);
        setLeveL(lvl_1bx,1);
        setRGBColor(lvl_1bx,COLOR_BLACK);

        changeName(lvl_f);
        changeName(lvl_0r);
        changeName(lvl_0b);
        changeName(lvl_0bx);
        changeName(lvl_1r);
        changeName(lvl_1b);
        changeName(lvl_1bx);

        switch (xeTypeIn){
            case XeType.EMPTY:
                return  lvl_f.copy();
            case XeType.RED_0:
                return  lvl_0r.copy();
            case XeType.RED_1:
                return  lvl_1r.copy();
            case XeType.BLUE_0:
                return  lvl_0b.copy();
            case XeType.BLUE_1:
                return  lvl_1b.copy();
            case XeType.BLACK_0:
                return  lvl_0bx.copy();
            case XeType.BLACK_1:
                return  lvl_1bx.copy();
                default:
                    return  lvl_f.copy();
        }
    }
}
