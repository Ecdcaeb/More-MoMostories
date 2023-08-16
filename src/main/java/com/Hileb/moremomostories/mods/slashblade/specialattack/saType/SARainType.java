package com.Hileb.moremomostories.mods.slashblade.specialattack.saType;

import com.Hileb.moremomostories.common.init.ModConfig;
import com.Hileb.moremomostories.mods.slashblade.specialattack.SpecialAttackType;
import com.Hileb.moremomostories.mods.slashblade.specialattack.saClass.SARainClass;
import mods.flammpfeil.slashblade.specialattack.SpecialAttackBase;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/8/13 11:44
 **/
public class SARainType extends SpecialAttackType {
    @Override
    public int getID() {
        return ModConfig.SlashBlade.SA_RAIN;
    }

    @Override
    public SpecialAttackBase getSpecialAttack() {
        return new SARainClass();
    }
}
