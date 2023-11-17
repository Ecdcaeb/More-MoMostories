package mods.Hileb.moremomostories.mods.slashblade.blade.myBlades;

import mods.Hileb.forgedmomo.api.slashblade.BladeType;
import mods.Hileb.moremomostories.mods.slashblade.blade.ModBlades;
import mods.Hileb.moremomostories.mods.slashblade.specialattack.ModSpecialAttack;
import mods.Hileb.forgedmomo.api.slashblade.SpecialAttackType;
import mods.Hileb.moremomostories.common.world.item.ModItems;
import mods.flammpfeil.slashblade.RecipeAwakeBlade;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/8/13 11:46
 **/
public class BladeTianKi extends BladeType {
    @Override
    public String getName() {
        return  "moremomostories.blandTianki";
    }

    @Override
    public int getKillCount() {
        return 1000;
    }

    @Override
    public int getProudSoul() {
        return 26000;
    }

    @Override
    public int getRefine() {
        return 10;
    }

    @Override
    public String getModel() {
        return  "named/sange/sange";
    }

    @Override
    public String getTexture() {
        return "momo/named/yaohushen";
    }

    @Override
    public SpecialAttackType getSA() {
        return ModSpecialAttack.BAKIN;
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
        ItemStack thisBlade = SlashBlade.getCustomBlade(getName()).copy();//从刀名获取刀的Stack：自己刀
        ItemStack reqiredBlade = SlashBlade.getCustomBlade(ModBlades.SAKI.getName()).copy();//从刀名获取刀的Stack：前置刀

        NBTTagCompound requireBladeTag = ItemSlashBlade.getItemTagCompound(reqiredBlade);//给前置刀绑定NBT
        ItemSlashBlade.RepairCount.set(requireBladeTag, 15);//对nbt进行修改，添加其内容（增加要求）

        reqiredBlade.addEnchantment(Enchantments.SHARPNESS,3);

        //将该配方真正添加
        return new RecipeAwakeBlade(new ResourceLocation("flammpfeil.slashblade", getName()),thisBlade, reqiredBlade,
                "QCO",
                "GBG",
                "OAQ",
                'C', ModItems.ITEM_CARD_MI_ITEM,
                'G', "ingotGold",
                'O', Blocks.IRON_BLOCK,
                'Q', Blocks.IRON_BLOCK,
                'B', reqiredBlade,
                'A', new ItemStack(ModItems.ITEM_MAGATAMA)).setRegistryName("flammpfeil.slashblade", getName());
    }
}
