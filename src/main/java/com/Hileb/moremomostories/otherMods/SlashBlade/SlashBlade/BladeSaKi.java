package com.Hileb.moremomostories.otherMods.SlashBlade.SlashBlade;

import com.Hileb.moremomostories.advancements.Advancementkeys;
import com.Hileb.moremomostories.advancements.ModAdvancementsInit;
import com.Hileb.moremomostories.init.ModConfig;
import com.Hileb.moremomostories.otherMods.SlashBlade.BasicBladeRecipe;
import com.gq2529.momostories.item.ModItems;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class BladeSaKi implements IBlade {
        public static final String NAME = "moremomostories.blandSaki";
        private static final String KEY = "moremomostories.blandSaki";

        public static  IRecipe r=null;


        public BladeSaKi() {
            MinecraftForge.EVENT_BUS.register(this);
        }


        @Override
        public void registerBlade() {
            ItemStack blade = new ItemStack(SlashBlade.bladeNamed, 1, 0);//新建刀Stack
            NBTTagCompound tag = new NBTTagCompound();//新建NBT
            blade.setTagCompound(tag);//绑定NBT
            ItemSlashBladeNamed.CurrentItemName.set(tag, NAME);//设置客户端刀名
            ItemSlashBladeNamed.CustomMaxDamage.set(tag, 42);//设置最大耐久
            ItemSlashBlade.setBaseAttackModifier(tag, Item.ToolMaterial.DIAMOND.getAttackDamage());//初始攻击力
            ItemSlashBlade.TextureName.set(tag, "momo/named/yaohushen");//贴图路径 .npg
            ItemSlashBlade.ModelName.set(tag, "named/sange/sange");//模型路径 .obj
            ItemSlashBlade.SummonedSwordColor.set(tag, 3355647);//颜色
            ItemSlashBlade.SpecialAttackType.set(tag, getSA());//SA类型
            ItemSlashBlade.KillCount.set(tag,1000);
            ItemSlashBlade.ProudSoul.set(tag,26000);
            ItemSlashBlade.RepairCount.set(tag,10);
            //ItemSlashBlade.specialAttacks.put(Integer.valueOf(100),new SlashDimension());
            ItemSlashBlade.StandbyRenderType.set(tag, 2);//绘制类型
            ItemSlashBladeNamed.IsDefaultBewitched.set(tag, true);//是否为妖刀
            ItemSlashBladeNamed.NamedBlades.add(KEY);//添加刀，只有名字
            //blade.addEnchantment(Enchantments.field_185302_k, 10);
            // blade.addEnchantment(Enchantments.field_185307_s, 10);
            SlashBlade.registerCustomItemStack(KEY, blade);//将其注册，绑定刀名
        }

        @Override
        public void registerRecipe() {
            ItemStack mySelf=SlashBlade.getCustomBlade(NAME);
//            //注册刀的配方

            //reqiredBlade.addEnchantment(Enchantments.field_185302_k, 1);
            r=new BasicBladeRecipe(new ResourceLocation("flammpfeil.slashblade", NAME),mySelf, getRequiredBlade(),
                    "QGO",
                    "GBG",
                    "OAQ",
                    'G', "ingotGold",
                    'O', Blocks.IRON_BLOCK,
                    'Q', Blocks.IRON_BLOCK,
                    'B', getRequiredBlade(),
                    'A', new ItemStack(ModItems.DEVILS_BLOOD_BUCKET));
            //将该配方真正添加
            SlashBlade.addRecipe(NAME,r);
        }
        public static ItemStack getRequiredBlade(){
            ItemStack reqiredBlade = SlashBlade.getCustomBlade("slashbladeWood").copy();//从刀名获取刀的Stack：前置刀
            NBTTagCompound regtag = ItemSlashBlade.getItemTagCompound(reqiredBlade);//给前置刀绑定NBT
            ItemSlashBlade.KillCount.set(regtag, 1000);//对nbt进行修改，添加其内容（增加要求）
            return reqiredBlade;
        }
        @Override
        public String getName(){
            return NAME;
        }
        public int getSA(){
            return ModConfig.SlashBlade.SA_BAKIN;
        }
}
