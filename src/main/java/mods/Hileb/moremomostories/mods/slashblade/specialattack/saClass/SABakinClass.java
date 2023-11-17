package mods.Hileb.moremomostories.mods.slashblade.specialattack.saClass;

import mods.Hileb.moremomostories.common.world.potion.ModPotions;
import mods.Hileb.moremomostories.common.world.potion.myBuff.PotionDayTime;
import mods.Hileb.moremomostories.common.util.EntityUtil;
import mods.flammpfeil.slashblade.specialattack.SpecialAttackBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class SABakinClass extends SpecialAttackBase {
    public SABakinClass(){
        super();
    }
    public String toString(){
        return "momo.sa_bakin";
    }

    public void doSpacialAttack(ItemStack var1, EntityPlayer var2){
        if (!var2.world.isRemote){
            for(PotionEffect effect:var2.getActivePotionEffects()){
                if (effect.getPotion().isBadEffect()){var2.removeActivePotionEffect(effect.getPotion());}
            }

            var2.addPotionEffect(PotionDayTime.getEffectShort());
            removeEffect(var2, ModPotions.BAKIN);

            EntityUtil.ApplyBuff(var2, MobEffects.SPEED,1,30);
            EntityUtil.ApplyBuff(var2, MobEffects.STRENGTH,1,30);
        }
    }
    private void removeEffect(EntityLivingBase livingBase,Potion potion){
        if (hasPotion(livingBase,potion))livingBase.removePotionEffect(potion);
    }
    private boolean hasPotion(EntityLivingBase living, Potion potion){
        for (PotionEffect effect: living.getActivePotionEffects()){
            if (effect.getPotion()== potion){
                return true;
            }
        }
        return false;
    }
}
