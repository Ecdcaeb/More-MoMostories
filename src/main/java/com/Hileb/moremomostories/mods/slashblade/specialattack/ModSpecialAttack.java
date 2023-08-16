package com.Hileb.moremomostories.mods.slashblade.specialattack;

import com.Hileb.moremomostories.mods.slashblade.specialattack.saType.SABakinType;
import com.Hileb.moremomostories.mods.slashblade.specialattack.saType.SABossBakinType;
import com.Hileb.moremomostories.mods.slashblade.specialattack.saType.SAFireType;
import com.Hileb.moremomostories.mods.slashblade.specialattack.saType.SARainType;

import java.util.LinkedList;
import java.util.List;

public class ModSpecialAttack {
    public static List<SpecialAttackType> REGISTER=new LinkedList<>();

    public static SpecialAttackType RAIN=new SARainType();
    public static SpecialAttackType FIRE=new SAFireType();
    public static SpecialAttackType BAKIN=new SABakinType();
    public static SpecialAttackType BOSS_BAKIN=new SABossBakinType();
}
