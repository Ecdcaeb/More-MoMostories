package com.Hileb.moremomostories.recipe;

import com.Hileb.moremomostories.IdlFramework;
import com.Hileb.moremomostories.item.ModItems;
import com.Hileb.moremomostories.item.myItems.ItemEndRainbow;
import com.Hileb.moremomostories.meta.MetaUtil;
import com.Hileb.moremomostories.otherMods.SlashBlade.SlashBlade.BladeTianKi;
import com.Hileb.moremomostories.util.named.NameTagHandler;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.TagPropertyAccessor;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.oredict.ShapedOreRecipe;

import javax.annotation.Nonnull;
import java.util.Map;

public class EndBladeRecipe extends ShapedOreRecipe {
    ItemStack bland = new ItemStack(Items.AIR);


    /**
     *
     * @param result 最后得到的刀]
     * @param inheritBlade 被继承的拔刀
     * @param materialBlade 所需拔刀
     * @param recipe 合成
     */
    public EndBladeRecipe(ResourceLocation group) {
        super(group, new ItemStack(ModItems.ITEM_SWOOD_SAKURA_END), getRecipe());
        bland=SlashBlade.getCustomBlade(BladeTianKi.NAME);
    }
     private static Object[] getRecipe(){
            ItemStack bland=SlashBlade.getCustomBlade(BladeTianKi.NAME);
            return new Object[]{
                    "RTY",
                    "ABC",
                    "QEQ",
                    'A',new ItemStack(ModItems.ITEM_12_B),
                    'B',new ItemStack(ModItems.ITEM_CARD_ZFP),
                    'C',new ItemStack(ModItems.ITEM_SWOOD_MEMORY_END),
                    'Q',new ItemStack(ModItems.ITEM_XE),
                    'R',new ItemStack(com.gq2529.momostories.item.ModItems.REPLICA_1),
                    'T',new ItemStack(Items.GOLDEN_SWORD),
                    'Y',new ItemStack(com.gq2529.momostories.item.ModItems.THE_BOOK_OF_MANIFESTATION),
                    'E',bland
            };
     }


    /**
     * @param access 比较的NBT类型
     * @param material 被比较物
     * @param compare 比较物
     * @return 用于比较NBT数值大小,如果compare大于等于material返回true
     */
    @Optional.Method(modid = SlashBlade.modid)
    public boolean compare(TagPropertyAccessor.TagPropertyInteger access, NBTTagCompound material, NBTTagCompound compare){
        return access.get(material) >= access.get(compare);
    }

    @Override
    public boolean matches(InventoryCrafting inv, World world) {
        if (inv.getSizeInventory()!=9)return false;
        else if (inv.getStackInSlot(0).getItem()!=com.gq2529.momostories.item.ModItems.REPLICA_1)return false;
        else if (inv.getStackInSlot(1).getItem()!=Items.GOLDEN_SWORD)return false;
        else if (inv.getStackInSlot(2).getItem()!=com.gq2529.momostories.item.ModItems.THE_BOOK_OF_MANIFESTATION)return false;
        else if (inv.getStackInSlot(3).getItem()!=ModItems.ITEM_12_B)return false;
        else if (inv.getStackInSlot(4).getItem()!=ModItems.ITEM_CARD_ZFP)return false;
        else if (inv.getStackInSlot(5).getItem()!=ModItems.ITEM_SWOOD_MEMORY_END)return false;
        else if (inv.getStackInSlot(6).getItem()!=ModItems.ITEM_XE)return false;
        else if (inv.getStackInSlot(8).getItem()!=ModItems.ITEM_XE)return false;
        else{
            ItemStack stackBland=inv.getStackInSlot(7);
            if (stackBland.isEmpty() || !(stackBland.getItem() instanceof ItemSlashBlade))return false;
            else if (!ItemSlashBladeNamed.CurrentItemName.get(stackBland.getTagCompound()).equals(BladeTianKi.NAME))return false;
            bland=SlashBlade.getCustomBlade(BladeTianKi.NAME);

            if (compare(ItemSlashBlade.ProudSoul, stackBland.getTagCompound(), bland.getTagCompound())){
                if (compare(ItemSlashBlade.KillCount, stackBland.getTagCompound(), bland.getTagCompound())){
                    if (compare(ItemSlashBlade.RepairCount, stackBland.getTagCompound(), bland.getTagCompound())){
                        return true;
                    }
                }
            }
            return false;
        }
    }

    @Nonnull
    public ItemStack getCraftingResult(@Nonnull InventoryCrafting var1) {
        ItemStack result = super.getCraftingResult(var1);


            ItemStack stackBland=var1.getStackInSlot(7);
            int score=0;
            NBTTagCompound tagCompound=stackBland.getTagCompound();
            score+=ItemSlashBlade.RepairCount.get(tagCompound)*100;
            score+=ItemSlashBlade.KillCount.get(tagCompound)*10;
            score+=ItemSlashBlade.ProudSoul.get(tagCompound);
            ItemEndRainbow.addEnergy(result,(double) score);

            Map<Enchantment,Integer> oldItemEnchants = EnchantmentHelper.getEnchantments(stackBland);
            for(Map.Entry<Enchantment,Integer> enchant: oldItemEnchants.entrySet())
            {
                int level = EnchantmentHelper.getEnchantmentLevel(enchant.getKey(),stackBland);
                result.addEnchantment(enchant.getKey(),level);
            }

        NameTagHandler.randomApply(result);

        return result;
    }
}
