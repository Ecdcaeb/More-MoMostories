package mods.Hileb.forgedmomo.utils;

import mods.Hileb.moremomostories.MoreMoMoSrories;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;


public class EntityUtil {
    public static boolean ApplyBuff(EntityLivingBase livingBase, Potion potion, int level, float seconds)
    {
        if (livingBase == null || potion == null)
        {
            MoreMoMoSrories.LOGGER.error("Trying to apply illegal potion {} to {}",String.valueOf(potion),String.valueOf(livingBase));
            return false;
        }
        livingBase.addPotionEffect(new PotionEffect(potion, (int) (seconds * 20) + 1, level));
        return true;
    }
}
