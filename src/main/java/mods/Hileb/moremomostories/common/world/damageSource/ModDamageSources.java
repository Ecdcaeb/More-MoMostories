package mods.Hileb.moremomostories.common.world.damageSource;

import net.minecraft.util.DamageSource;

public class ModDamageSources {
    public static final DamageSource VAN =new DamageSourceBase("van_damage").setMagicDamage();
    public static final DamageSource SWORD=new DamageSourceBase("sword_damage").setMagicDamage().setDamageBypassesArmor();
}
