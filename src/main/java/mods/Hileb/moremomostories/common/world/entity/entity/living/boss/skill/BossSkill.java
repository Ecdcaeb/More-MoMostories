package mods.Hileb.moremomostories.common.world.entity.entity.living.boss.skill;

import net.minecraft.entity.EntityLivingBase;

public abstract class BossSkill {
    public BossSkill(){
        BossSkills.skillList.add(this);
    }
    public abstract void doSpacialAttack(EntityLivingBase var2);
    public abstract boolean apply(EntityLivingBase var2);
}
