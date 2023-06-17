package com.Hileb.moremomostories.otherMods.SlashBlade;

import com.Hileb.moremomostories.IdlFramework;
import com.Hileb.moremomostories.init.ModConfig;
import com.Hileb.moremomostories.otherMods.SlashBlade.SA.ModSA;
import com.Hileb.moremomostories.otherMods.SlashBlade.SA.SpecialAttack;
import com.Hileb.moremomostories.otherMods.SlashBlade.SlashBlade.BladeSaKi;
import com.Hileb.moremomostories.otherMods.SlashBlade.SlashBlade.BladeTest;
import com.Hileb.moremomostories.otherMods.SlashBlade.SlashBlade.BladeTianKi;
import com.Hileb.moremomostories.otherMods.SlashBlade.SlashBlade.IBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;

import java.util.ArrayList;
import java.util.List;

public class SlashBladeUtil {
    private final List<IBlade> bladeList= new ArrayList<>();
    public SlashBladeUtil(){
        bladeList.add(new BladeTest());//测试刀
        bladeList.add(new BladeTest(){
            @Override
            public int getSA() {
                return ModConfig.SlashBlade.SA_FIRE;
            }
        });
        bladeList.add(new BladeTianKi());
        bladeList.add(new BladeSaKi());
    }
    public void registerBlade(){
        for(int i=0;i<bladeList.size();i++){//批量注册
                IdlFramework.Log("register Blade:%s",bladeList.get(i).getName());
                bladeList.get(i).registerBlade();
                bladeList.get(i).registerRecipe();
        }
    }
    public void registerSA(){
        for(SpecialAttack sa:ModSA.REGISTER){
            ItemSlashBlade.specialAttacks.put(sa.getID(), sa);
            IdlFramework.Log("register sa:%S",sa.toString());
        }
    }//id,sa
}
