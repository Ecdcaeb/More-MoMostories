package com.Hileb.moremomostories.otherMods.SlashBlade.SA;

import mods.flammpfeil.slashblade.specialattack.SpecialAttackBase;

public abstract class SpecialAttack extends SpecialAttackBase {
    public SpecialAttack() {
        ModSA.REGISTER.add(this);
    }
    public abstract int getID();
}
