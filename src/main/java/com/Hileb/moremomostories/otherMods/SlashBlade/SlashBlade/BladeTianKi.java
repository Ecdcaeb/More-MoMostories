package com.Hileb.moremomostories.otherMods.SlashBlade.SlashBlade;

import com.Hileb.moremomostories.advancements.Advancementkeys;
import com.Hileb.moremomostories.advancements.ModAdvancementsInit;
import com.Hileb.moremomostories.init.ModConfig;
import com.Hileb.moremomostories.item.ModItems;
import com.Hileb.moremomostories.otherMods.SlashBlade.BasicBladeRecipe;
import com.Hileb.moremomostories.recipe.FakeDiamondBottleRecipe;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class BladeTianKi implements IBlade {
        public static final String NAME = "moremomostories.blandTianki";
        private static final String KEY = "moremomostories.blandTianki";

        public static  IRecipe r=null;


        public BladeTianKi() {
            MinecraftForge.EVENT_BUS.register(this);
        }
        @SubscribeEvent
        public void onCraft(PlayerEvent.ItemCraftedEvent event){
            try {
               if (!event.player.world.isRemote) {
                   InventoryCrafting inv = (InventoryCrafting) event.craftMatrix;

                   if (r!=null && r.matches(inv,event.player.world)) {
                       ModAdvancementsInit.giveAdvancement(event.player, Advancementkeys.AD_OMOD_TK);
                   }
               }
            }catch (Exception ignored){
            }
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
                    "QCO",
                    "GBG",
                    "OAQ",
                    'C',ModItems.ITEM_CARD_MI_ITEM,
                    'G', "ingotGold",
                    'O', Blocks.IRON_BLOCK,
                    'Q', Blocks.IRON_BLOCK,
                    'B', getRequiredBlade(),
                    'A', new ItemStack(ModItems.ITEM_MAGATAMA));
            //将该配方真正添加
            SlashBlade.addRecipe("moremomostories.blandTianki",r);
        }
        public static ItemStack getRequiredBlade(){
            ItemStack reqiredBlade = SlashBlade.getCustomBlade(BladeSaKi.NAME).copy();//从刀名获取刀的Stack：前置刀
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
