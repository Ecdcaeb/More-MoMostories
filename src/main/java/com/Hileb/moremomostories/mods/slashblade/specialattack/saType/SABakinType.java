package com.Hileb.moremomostories.mods.slashblade.specialattack.saType;

import com.Hileb.moremomostories.common.init.ModConfig;
import com.Hileb.moremomostories.mods.slashblade.specialattack.SpecialAttackType;
import com.Hileb.moremomostories.mods.slashblade.specialattack.saClass.SABakinClass;
import mods.flammpfeil.slashblade.specialattack.SpecialAttackBase;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/8/13 11:28
 **/
public class SABakinType extends SpecialAttackType {
    public SABakinType(){
        super();
    }
    @Override
    public int getID() {
        return ModConfig.SlashBlade.SA_BAKIN;
    }

    @Override
    public SpecialAttackBase getSpecialAttack() {
        return new SABakinClass();
    }
}
