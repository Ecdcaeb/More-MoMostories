package com.Hileb.moremomostories.common.util;

import com.Hileb.moremomostories.MoreMoMoSrories;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import static com.Hileb.moremomostories.common.util.CommonDef.TICK_PER_SECOND;


public class EntityUtil {
    public static boolean ApplyBuff(EntityLivingBase livingBase, Potion potion, int level, float seconds)
    {
        if (livingBase == null || potion == null)
        {
            MoreMoMoSrories.LogWarning("Trying to apply illegal potion");
            return false;
        }
        livingBase.addPotionEffect(new PotionEffect(potion, (int) (seconds * TICK_PER_SECOND) + 1, level));
        return true;
    }
}
