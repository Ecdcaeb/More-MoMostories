package mods.Hileb.forgedmomo.api.slashblade;

import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public abstract class BladeType {
    public static List<BladeType> REGISTER=new LinkedList<>();
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
        if (getSA()!=null && getSA().isNull())ItemSlashBladeNamed.SpecialAttackType.set(tagCompound,getSA().getID());//SA类型
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
        REGISTER.add(this);
    }

    public abstract IRecipe getRecipe();
    public static class Builder{
        private Impl impl=new Impl();
        public Builder(String name){
            impl.name=name;
        }
        public Builder killCount(int value){
            impl.killCount=value;
            return this;
        }
        public Builder proudSoul(int value){
            impl.proudSoul=value;
            return this;
        }
        public Builder refine(int value){
            impl.refine=value;
            return this;
        }
        public Builder model(String value){
            impl.model=value;
            return this;
        }
        public Builder texture(String value){
            impl.texture=value;
            return this;
        }
        public Builder specialAttackType(SpecialAttackType specialAttackType){
            impl.specialAttackType=specialAttackType;
            return this;
        }
        public Builder isBroken(boolean value){
            impl.isBroken=value;
            return this;
        }
        public Builder attackAmplifier(float value){
            impl.attackAmplifier=value;
            return this;
        }
        public Builder standbyRenderType(int value){
            impl.standbyRenderType=value;
            return this;
        }
        public Builder isDefaultBewitched(boolean value){
            impl.isDefaultBewitched=value;
            return this;
        }
        public Builder baseAttackModifier(int value){
            impl.baseAttackModifier=value;
            return this;
        }
        public Builder maxDamage(int value){
            impl.maxDamage=value;
            return this;
        }
        public Builder recipe(IRecipe recipe){
            impl.iRecipe=recipe;
            return this;
        }
        public Builder process(Function<ItemStack,ItemStack> value){
            impl.process=value;
            return this;
        }
        public BladeType build(){
            return impl;
        }
        private static class Impl extends BladeType{
            public String name=null;
            public int killCount=0;
            public int proudSoul=0;
            public int refine=0;
            public String model;
            public String texture;
            public SpecialAttackType specialAttackType=SpecialAttackType.NULL;
            public boolean isBroken=false;
            public float attackAmplifier=0;
            public int standbyRenderType=0;
            public boolean isDefaultBewitched=true;
            public int baseAttackModifier=4;
            public int maxDamage=14;
            public IRecipe iRecipe=null;
            public Function<ItemStack,ItemStack> process=(stack)->stack;
            @Override
            public String getName() {
                return name;
            }
            @Override
            public boolean isBroken() {
                return isBroken;
            }
            @Override
            public float getAttackAmplifier() {
                return attackAmplifier;
            }
            @Override
            public int getStandbyRenderType() {
                return standbyRenderType;
            }
            @Override
            public boolean isDefaultBewitched() {
                return isDefaultBewitched;
            }
            @Override
            public float getBaseAttackModifier() {
                return baseAttackModifier;
            }
            @Override
            public int getMaxDamage() {
                return maxDamage;
            }
            @Override
            public SpecialAttackType getSA() {
                return specialAttackType;
            }

            @Override
            public int getKillCount() {
                return killCount;
            }

            @Override
            public int getProudSoul() {
                return proudSoul;
            }

            @Override
            public int getRefine() {
                return refine;
            }

            @Override
            public String getModel() {
                return model;
            }

            @Override
            public String getTexture() {
                return texture;
            }
            @Override
            public IRecipe getRecipe() {
                return iRecipe;
            }

            @Override
            public ItemStack newItemStackInstance() {
                return process.apply(super.newItemStackInstance());
            }
        }
    }
}
