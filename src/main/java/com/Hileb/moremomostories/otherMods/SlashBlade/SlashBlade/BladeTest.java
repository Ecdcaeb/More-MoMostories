package com.Hileb.moremomostories.otherMods.SlashBlade.SlashBlade;//package com.Hileb.moremomostories.otherMods.SlashBlade.SlashBlade;

import com.Hileb.moremomostories.IdlFramework;
import com.Hileb.moremomostories.otherMods.SlashBlade.BasicBladeRecipe;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

public class BladeTest implements IBlade{
    public static final String NAME = "moremomostories.testBlade1";
    private static final String KEY = "moremomostories.testBlade1";

    public BladeTest() {
    }

    @Override
    public void registerBlade() {
        ItemStack blade = new ItemStack(SlashBlade.bladeNamed, 1, 0);//新建刀Stack
        NBTTagCompound tag = new NBTTagCompound();//新建NBT
        blade.setTagCompound(tag);//绑定NBT
        ItemSlashBladeNamed.CurrentItemName.set(tag, "moremomostories.testBlade1");//设置客户端刀名
        ItemSlashBladeNamed.CustomMaxDamage.set(tag, 42);//设置最大耐久
        ItemSlashBlade.setBaseAttackModifier(tag, Item.ToolMaterial.DIAMOND.getAttackDamage());//初始攻击力
        ItemSlashBlade.TextureName.set(tag, "innominandum/nebula/nebula");//贴图路径 .npg
        ItemSlashBlade.ModelName.set(tag, "innominandum/nebula/nebula");//模型路径 .obj
        ItemSlashBlade.SummonedSwordColor.set(tag, 3355647);//颜色
        ItemSlashBlade.SpecialAttackType.set(tag, getSA());//SA类型
        //ItemSlashBlade.specialAttacks.put(Integer.valueOf(100),new SlashDimension());
        ItemSlashBlade.StandbyRenderType.set(tag, 2);//绘制类型
        ItemSlashBladeNamed.IsDefaultBewitched.set(tag, true);//是否为妖刀
        ItemSlashBladeNamed.NamedBlades.add("moremomostories.testBlade1");//添加刀，只有名字
        //blade.addEnchantment(Enchantments.field_185302_k, 10);
       // blade.addEnchantment(Enchantments.field_185307_s, 10);
        SlashBlade.registerCustomItemStack("moremomostories.testBlade1", blade);//将其注册，绑定刀名
    }

    @Override
    public void registerRecipe() {
        IdlFramework.LogWarning("regi");
        //注册刀的配方
        ItemStack blade = SlashBlade.getCustomBlade("moremomostories.testBlade1");//从刀名获取刀的Stack：自己刀
        ItemStack reqiredBlade = SlashBlade.getCustomBlade("slashbladeWood");//从刀名获取刀的Stack：前置刀
        NBTTagCompound regtag = ItemSlashBlade.getItemTagCompound(reqiredBlade);//给前置刀绑定NBT
        ItemSlashBlade.KillCount.set(regtag, 60);//对nbt进行修改，添加其内容（增加要求）
        //reqiredBlade.addEnchantment(Enchantments.field_185302_k, 1);
        //将该配方真正添加
        SlashBlade.addRecipe("moremomostories.testBlade1", new BasicBladeRecipe(new ResourceLocation("flammpfeil.slashblade", "testBlade1"), blade, reqiredBlade, new Object[]{"QGO", "GBG", "OGQ", 'G', "ingotGold", 'O', Blocks.IRON_BLOCK, 'Q', Blocks.IRON_BLOCK, 'B', reqiredBlade}));
    }
    @Override
    public String getName(){
        return NAME;
    }
    public int getSA(){
        return 80;
    }
}
