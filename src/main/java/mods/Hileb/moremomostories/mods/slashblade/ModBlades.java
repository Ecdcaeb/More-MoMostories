package mods.Hileb.moremomostories.mods.slashblade;

import com.gq2529.momostories.item.ModItems;
import mods.Hileb.forgedmomo.api.slashblade.BladeType;
import mods.Hileb.moremomostories.MoreMoMoSrories;
import mods.flammpfeil.slashblade.RecipeAwakeBlade;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

public class ModBlades {
    public static final BladeType SAKI=new BladeType.Builder("moremomostories.blandSaki")
            .killCount(1000)
            .proudSoul(26000)
            .refine(10)
            .model("named/sange/sange")
            .texture("momo/named/yaohushen")
            .specialAttackType(ModSpecialAttack.BOSS_BAKIN)
            .standbyRenderType(2)
            .maxDamage(42)
            .recipe(ModBlades::createSakiRecipe).register().build();
    public static final BladeType RAIN=new BladeType.Builder( "moremomostories.testBlade1")
            .model("named/sange/sange")
            .texture("momo/named/yaohushen")
            .specialAttackType(ModSpecialAttack.RAIN)
            .standbyRenderType(2)
            .baseAttackModifier(3)
            .maxDamage(42)
            .recipe(ModBlades::createRainRecipe).register().build();
    public static final BladeType TIAN_KI=new BladeType.Builder("moremomostories.blandTianki")
            .killCount(1000)
            .proudSoul(26000)
            .refine(10)
            .model("named/sange/sange")
            .texture("momo/named/yaohushen")
            .specialAttackType(ModSpecialAttack.BAKIN)
            .standbyRenderType(2)
            .maxDamage(42)
            .recipe(ModBlades::createTianKiRecipe).register().build();
    public static void init(){}

    private static IRecipe createRainRecipe(BladeType bladeType) {
        //注册刀的配方
        ItemStack thisBlade = SlashBlade.getCustomBlade(bladeType.getName()).copy();//从刀名获取刀的Stack：自己刀
        ItemStack reqiredBlade = SlashBlade.getCustomBlade("slashbladeWood").copy();//从刀名获取刀的Stack：前置刀
        NBTTagCompound requireBladeTag = ItemSlashBlade.getItemTagCompound(reqiredBlade);//给前置刀绑定NBT
        ItemSlashBlade.KillCount.set(requireBladeTag, 60);//对nbt进行修改，添加其内容（增加要求）
        //reqiredBlade.addEnchantment(Enchantments.field_185302_k, 1);
        //将该配方真正添加
        return new RecipeAwakeBlade(
                new ResourceLocation("flammpfeil.slashblade", bladeType.getName()),
                thisBlade, reqiredBlade,
                "QGO",
                "GBG",
                "OGQ",
                'G', "ingotGold",
                'O', Blocks.IRON_BLOCK,
                'Q', Items.WATER_BUCKET,
                'B', reqiredBlade).setRegistryName("flammpfeil.slashblade", bladeType.getName());
    }
    private static IRecipe createSakiRecipe(BladeType bladeType) {
        //注册刀的配方
        ItemStack thisBlade = SlashBlade.getCustomBlade(bladeType.getName()).copy();//从刀名获取刀的Stack：自己刀
        ItemStack reqiredBlade = SlashBlade.getCustomBlade("slashbladeWood").copy();//从刀名获取刀的Stack：前置刀

        NBTTagCompound requireBladeTag = ItemSlashBlade.getItemTagCompound(reqiredBlade);//给前置刀绑定NBT
        ItemSlashBlade.KillCount.set(requireBladeTag, 1000);//对nbt进行修改，添加其内容（增加要求）
        //reqiredBlade.addEnchantment(Enchantments.field_185302_k, 1);
        //将该配方真正添加
        return new RecipeAwakeBlade(new ResourceLocation(MoreMoMoSrories.MODID, bladeType.getName()),thisBlade, reqiredBlade,
                "QGO",
                "GBG",
                "OAQ",
                'G', "ingotGold",
                'O', Blocks.IRON_BLOCK,
                'Q', Blocks.IRON_BLOCK,
                'B', reqiredBlade,
                'A', new ItemStack(ModItems.DEVILS_BLOOD_BUCKET)).setRegistryName("flammpfeil.slashblade", bladeType.getName());
    }
    private static IRecipe createTianKiRecipe(BladeType bladeType) {
        ItemStack thisBlade = SlashBlade.getCustomBlade(bladeType.getName()).copy();//从刀名获取刀的Stack：自己刀
        ItemStack reqiredBlade = SlashBlade.getCustomBlade(ModBlades.SAKI.getName()).copy();//从刀名获取刀的Stack：前置刀

        NBTTagCompound requireBladeTag = ItemSlashBlade.getItemTagCompound(reqiredBlade);//给前置刀绑定NBT
        ItemSlashBlade.RepairCount.set(requireBladeTag, 15);//对nbt进行修改，添加其内容（增加要求）

        reqiredBlade.addEnchantment(Enchantments.SHARPNESS,3);

        //将该配方真正添加
        return new RecipeAwakeBlade(new ResourceLocation("flammpfeil.slashblade", bladeType.getName()),thisBlade, reqiredBlade,
                "QCO",
                "GBG",
                "OAQ",
                'C', mods.Hileb.moremomostories.common.world.item.ModItems.ITEM_CARD_MI_ITEM,
                'G', "ingotGold",
                'O', Blocks.IRON_BLOCK,
                'Q', Blocks.IRON_BLOCK,
                'B', reqiredBlade,
                'A', new ItemStack(mods.Hileb.moremomostories.common.world.item.ModItems.ITEM_MAGATAMA)).setRegistryName("flammpfeil.slashblade", bladeType.getName());
    }
}
