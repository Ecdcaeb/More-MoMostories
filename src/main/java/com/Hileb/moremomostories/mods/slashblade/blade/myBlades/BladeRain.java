package com.Hileb.moremomostories.mods.slashblade.blade.myBlades;

import com.Hileb.moremomostories.mods.slashblade.blade.BladeType;
import com.Hileb.moremomostories.mods.slashblade.specialattack.ModSpecialAttack;
import com.Hileb.moremomostories.mods.slashblade.specialattack.SpecialAttackType;
import mods.flammpfeil.slashblade.RecipeAwakeBlade;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/8/13 11:40
 **/
public class BladeRain extends BladeType {
    @Override
    public String getName() {
        return "moremomostories.testBlade1";
    }

    @Override
    public int getKillCount() {
        return 0;
    }

    @Override
    public int getProudSoul() {
        return 0;
    }

    @Override
    public int getRefine() {
        return 0;
    }

    @Override
    public String getModel() {
        return "named/sange/sange";
    }

    @Override
    public String getTexture() {
        return "momo/named/yaohushen";
    }

    @Override
    public SpecialAttackType getSA() {
        return ModSpecialAttack.RAIN;
    }

    @Override
    public float getAttackAmplifier() {
        return 0;
    }

    @Override
    public boolean isBroken() {
        return false;
    }

    @Override
    public int getStandbyRenderType() {
        return 2;
    }

    @Override
    public float getBaseAttackModifier() {
        return Item.ToolMaterial.DIAMOND.getAttackDamage();
    }

    @Override
    public int getMaxDamage() {
        return 42;
    }

    @Override
    public boolean isDefaultBewitched() {
        return true;
    }

    @Override
    public IRecipe getRecipe() {
        //注册刀的配方
        ItemStack thisBlade = SlashBlade.getCustomBlade(getName()).copy();//从刀名获取刀的Stack：自己刀
        ItemStack reqiredBlade = SlashBlade.getCustomBlade("slashbladeWood");//从刀名获取刀的Stack：前置刀
        NBTTagCompound requireBladeTag = ItemSlashBlade.getItemTagCompound(reqiredBlade);//给前置刀绑定NBT
        ItemSlashBlade.KillCount.set(requireBladeTag, 60);//对nbt进行修改，添加其内容（增加要求）
        //reqiredBlade.addEnchantment(Enchantments.field_185302_k, 1);
        //将该配方真正添加
        return new RecipeAwakeBlade(
                new ResourceLocation("flammpfeil.slashblade", getName()),
                thisBlade, reqiredBlade,
                "QGO",
                "GBG",
                "OGQ",
                'G', "ingotGold",
                'O', Blocks.IRON_BLOCK,
                'Q', Items.WATER_BUCKET,
                'B', reqiredBlade).setRegistryName("flammpfeil.slashblade", getName());
    }
}
