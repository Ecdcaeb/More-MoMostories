package mods.Hileb.moremomostories.modplugins.slashblade;

import mods.Hileb.forgedmomo.api.mods.slashblade.SpecialAttackType;
import mods.Hileb.moremomostories.common.init.ModConfig;
import mods.Hileb.moremomostories.modplugins.slashblade.specialattack.SABakin;
import mods.Hileb.moremomostories.modplugins.slashblade.specialattack.SABossBakin;
import mods.Hileb.moremomostories.modplugins.slashblade.specialattack.SAFire;
import mods.Hileb.moremomostories.modplugins.slashblade.specialattack.SARain;

public class ModSpecialAttack {
    public static SpecialAttackType RAIN=new SpecialAttackType.Builder(()-> ModConfig.SlashBlade.SA_RAIN, SARain::new).register().build();
    public static SpecialAttackType FIRE=new SpecialAttackType.Builder(()-> ModConfig.SlashBlade.SA_FIRE, SAFire::new).register().build();
    public static SpecialAttackType BAKIN=new SpecialAttackType.Builder(()-> ModConfig.SlashBlade.SA_BAKIN, SABakin::new).register().build();
    public static SpecialAttackType BOSS_BAKIN=new SpecialAttackType.Builder(()-> ModConfig.SlashBlade.SA_BOSS_BAKIN, SABossBakin::new).build();
    public static void init(){}
}
