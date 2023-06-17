package com.Hileb.moremomostories.otherMods.SlashBlade.SA;

import com.Hileb.moremomostories.entity.entity.living.boss.skill.BossSkills;
import com.Hileb.moremomostories.init.ModConfig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

public class SABossBakin extends SpecialAttack {
    public SABossBakin(){
        super();
        MinecraftForge.EVENT_BUS.register(this);
    }
    public String toString(){
        return "sa_sef";
    }

    public void doSpacialAttack(ItemStack var1, EntityPlayer var2){
        if (!var2.world.isRemote){
            BossSkills.BAKIN.doSpacialAttack(var2);
        }
    }
    @Override
    public int getID() {
        return ModConfig.SlashBlade.SA_BOSS_BAKIN;
    }
}
