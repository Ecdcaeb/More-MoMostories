package com.Hileb.moremomostories.mods.slashblade.specialattack.saType;

import com.Hileb.moremomostories.common.init.ModConfig;
import com.Hileb.moremomostories.mods.slashblade.specialattack.SpecialAttackType;
import com.Hileb.moremomostories.mods.slashblade.specialattack.saClass.SABossBakinClass;
import mods.flammpfeil.slashblade.specialattack.SpecialAttackBase;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/8/13 11:33
 **/
public class SABossBakinType extends SpecialAttackType {
    @Override
    public int getID() {
        return ModConfig.SlashBlade.SA_BOSS_BAKIN;
    }
    @Override
    public SpecialAttackBase getSpecialAttack() {
        return new SABossBakinClass();
    }
}
