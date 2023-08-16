package com.Hileb.moremomostories.mods.slashblade.blade;

import com.Hileb.moremomostories.mods.slashblade.specialattack.SpecialAttackType;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;

public abstract class BladeType {
    public final ItemStack thisItemStack;
    public abstract String getName();//刀的名字
    //item. (getName) .name
    public abstract int getKillCount();
    public abstract int getProudSoul();
    public abstract int getRefine();
    public abstract String getModel();
    //flammpfeil.slashblade:model/ getModel .obj
    public abstract String getTexture();
    //flammpfeil.slashblade:model/ getTexture .png
    public abstract SpecialAttackType getSA();

    public abstract float getAttackAmplifier();
    public abstract boolean isBroken();

    public abstract int getStandbyRenderType();

    public abstract float getBaseAttackModifier();

    public abstract int  getMaxDamage();
    public abstract boolean isDefaultBewitched();

    //上面这些是不必须的
    public ItemStack newItemStackInstance(){
        ItemStack stack=new ItemStack(SlashBlade.bladeNamed);//新建刀Stack
        NBTTagCompound tagCompound=new NBTTagCompound();//新建NBT
        stack.setTagCompound(tagCompound);//绑定NBT
        //
        ItemSlashBladeNamed.CurrentItemName.set(tagCompound,getName());//设置客户端刀名
        ItemSlashBladeNamed.CustomMaxDamage.set(tagCompound,getMaxDamage());//设置最大耐久
        if (getSA()!=null)ItemSlashBladeNamed.SpecialAttackType.set(tagCompound,getSA().getID());//SA类型
        ItemSlashBladeNamed.StandbyRenderType.set(tagCompound,getStandbyRenderType());//绘制类型
        ItemSlashBladeNamed.IsBroken.set(tagCompound,isBroken());//是否破损
        ItemSlashBladeNamed.IsDefaultBewitched.set(tagCompound, isDefaultBewitched());//是否为妖刀
        ItemSlashBladeNamed.AttackAmplifier.set(tagCompound,getAttackAmplifier());
        ItemSlashBladeNamed.BaseAttackModifier.set(tagCompound,getBaseAttackModifier());//初始攻击力

        ItemSlashBladeNamed.ProudSoul.set(tagCompound,getProudSoul());
        ItemSlashBladeNamed.KillCount.set(tagCompound,getKillCount());
        ItemSlashBladeNamed.RepairCount.set(tagCompound,getRefine());


        ItemSlashBlade.TextureName.set(tagCompound,getTexture());//贴图路径 .png
        ItemSlashBlade.ModelName.get(tagCompound,getModel());//模型路径 .obj

        return stack;
    }

    public ItemStack getThisItemStack() {
        return thisItemStack;
    }
    public void registerStack(){
        ItemSlashBladeNamed.NamedBlades.add(getName());//添加刀，进入物品栏。
        SlashBlade.registerCustomItemStack(getName(),getThisItemStack());//注册刀
    }
    public BladeType(){
        thisItemStack=newItemStackInstance();
        ModBlades.REGISTER.add(this);
    }

    public abstract IRecipe getRecipe();
}
