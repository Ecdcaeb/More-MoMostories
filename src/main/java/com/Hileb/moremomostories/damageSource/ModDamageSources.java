package com.Hileb.moremomostories.damageSource;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;

public class ModDamageSources {
    public static final DamageSource VAN =new DamageSourceBase("van_damage").setMagicDamage();

    public static DamageSource causeAnvilDamage(EntityPlayer player)
    {
        return new EntityDamageSource("playerAnvil", player);
    }
}
