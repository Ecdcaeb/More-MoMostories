package mods.Hileb.moremomostories.mods.slashblade.specialattack;

import mods.Hileb.moremomostories.common.world.entity.entity.living.boss.skill.BossSkills;
import mods.flammpfeil.slashblade.specialattack.SpecialAttackBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class SABossBakin extends SpecialAttackBase {
    public SABossBakin(){
        super();
    }

    @Override
    public String toString() {
        return "momo.sa_sef";
    }

    @Override
    public void doSpacialAttack(ItemStack var1, EntityPlayer var2){
        if (!var2.world.isRemote){
            BossSkills.BAKIN.doSpacialAttack(var2);
        }
    }
}
