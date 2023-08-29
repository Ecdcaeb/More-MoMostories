package com.Hileb.moremomostories.common.world.item.myItems;

import com.Hileb.moremomostories.common.world.damageSource.DamageSourceWither;
import com.Hileb.moremomostories.common.world.item.ItemSwordBase;
import com.Hileb.moremomostories.common.world.item.interfaces.IColorEnchantment;
import com.Hileb.moremomostories.common.world.potion.ModPotions;
import com.Hileb.moremomostories.common.world.potion.myBuff.PotionBlood;
import com.gq2529.momostories.item.ModItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/8/27 10:09
 **/
public class ItemDevilsSword extends ItemSwordBase implements IColorEnchantment {

    public static final ToolMaterial MATERIAL_DEVILS= EnumHelper.addToolMaterial("devils_sword",4,1024,10,-3,30).setRepairItem(new ItemStack(ModItems.DEVILS_BLOOD_BUCKET));
    public ItemDevilsSword(String name) {
        super(name, MATERIAL_DEVILS);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean hasEffect(ItemStack stack) {
        return false;
    }
    @Override
    public boolean hasEnchantmentEffect(ItemStack stack) {
        return true;
    }
    @Override
    public int getEnchantmentColor(ItemStack stack) {
        return 0x880000;//一种红色
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        target.addPotionEffect(new PotionEffect(ModPotions.BLOOD, PotionBlood.MAX_TICK));
        //test
//        if (!attacker.world.isRemote){
//            EntityZombie zombie=new EntityZombie(attacker.world);
//            zombie.setPosition(target.posX,target.posY,target.posZ);
//            attacker.world.spawnEntity(zombie);
//            zombie.setItemStackToSlot(EntityEquipmentSlot.MAINHAND,new ItemStack(com.Hileb.moremomostories.common.world.item.ModItems.ITEM_BLOOD_SWORD));
//        }
        return super.hitEntity(stack, target, attacker);
    }
}
