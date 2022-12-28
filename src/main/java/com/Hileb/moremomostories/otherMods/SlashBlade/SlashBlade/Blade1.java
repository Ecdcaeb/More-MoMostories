//package com.Hileb.moremomostories.otherMods.SlashBlade.SlashBlade;
//
//import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
//import mods.flammpfeil.slashblade.SlashBlade;
//import mods.flammpfeil.slashblade.item.ItemSlashBlade;
//import mods.nebula.Innominandum;
//import mods.nebula.blade.RecipeAwakeBladeRandom;
//import net.minecraft.init.Blocks;
//import net.minecraft.item.Item;
//import net.minecraft.item.ItemStack;
//import net.minecraft.nbt.NBTTagCompound;
//
//public class Blade1 {
//    public static final String NAME = "innominandum.blade.BladeNebula";
//    private static final String KEY = "innominandum.BladeNebula";
//
//    public Blade1() {
//    }
//
//    public static void registBlade() {
//        ItemStack blade = new ItemStack(SlashBlade.bladeNamed, 1, 0);//新建刀Stack
//        NBTTagCompound tag = new NBTTagCompound();//新建NBT
//        blade.setTagCompound(tag);//绑定NBT
//        ItemSlashBladeNamed.CurrentItemName.set(tag, "innominandum.blade.BladeNebula");//设置客户端刀名
//        ItemSlashBladeNamed.CustomMaxDamage.set(tag, 42);//设置最大耐久
//        ItemSlashBlade.setBaseAttackModifier(tag, Item.ToolMaterial.DIAMOND.getAttackDamage());//初始攻击力
//        ItemSlashBlade.TextureName.set(tag, "innominandum/nebula/nebula");//贴图路径 .npg
//        ItemSlashBlade.ModelName.set(tag, "innominandum/nebula/nebula");//模型路径 .obj
//        ItemSlashBlade.SummonedSwordColor.set(tag, 3355647);//颜色
//        ItemSlashBlade.SpecialAttackType.set(tag, 33);//SA类型
//        ItemSlashBlade.StandbyRenderType.set(tag, 2);//绘制类型
//        ItemSlashBladeNamed.IsDefaultBewitched.set(tag, true);//是否为妖刀
//        ItemSlashBladeNamed.NamedBlades.add("innominandum.blade.BladeNebula");//添加刀，只有名字
//        //blade.addEnchantment(Enchantments.field_185302_k, 10);
//       // blade.addEnchantment(Enchantments.field_185307_s, 10);
//        SlashBlade.registerCustomItemStack("innominandum.blade.BladeNebula", blade);//将其注册，绑定刀名
//    }
//
//    public static void registRecipe() {
//        //注册刀的配方
//        ItemStack blade = SlashBlade.getCustomBlade("innominandum.blade.BladeNebula");//从刀名获取刀的Stack：自己刀
//        ItemStack reqiredBlade = SlashBlade.getCustomBlade("slashbladeWood");//从刀名获取刀的Stack：前置刀
//        NBTTagCompound regtag = ItemSlashBlade.getItemTagCompound(reqiredBlade);//给前置刀绑定NBT
//        ItemSlashBlade.KillCount.set(regtag, 60);//对nbt进行修改，添加其内容（增加要求）
//        //reqiredBlade.addEnchantment(Enchantments.field_185302_k, 1);
//        //将该配方真正添加
//        SlashBlade.addRecipe("innominandum.BladeNebula", new RecipeAwakeBladeRandom(Innominandum.RecipeGroup, blade, reqiredBlade, new Object[]{"QGO", "GBG", "OGQ", 'G', "ingotGold", 'O', Blocks.field_150343_Z, 'Q', Blocks.field_150368_y, 'B', reqiredBlade}));
//    }
//}
