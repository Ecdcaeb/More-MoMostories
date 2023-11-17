package mods.Hileb.moremomostories.mods.slashblade.specialattack;

import mods.Hileb.forgedmomo.api.slashblade.SpecialAttackType;
import mods.Hileb.moremomostories.mods.slashblade.specialattack.saType.SABakinType;
import mods.Hileb.moremomostories.mods.slashblade.specialattack.saType.SABossBakinType;
import mods.Hileb.moremomostories.mods.slashblade.specialattack.saType.SAFireType;
import mods.Hileb.moremomostories.mods.slashblade.specialattack.saType.SARainType;

public class ModSpecialAttack {
    public static SpecialAttackType RAIN=new SARainType();
    public static SpecialAttackType FIRE=new SAFireType();
    public static SpecialAttackType BAKIN=new SABakinType();
    public static SpecialAttackType BOSS_BAKIN=new SABossBakinType();
    public static void init(){}
}
