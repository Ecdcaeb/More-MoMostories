package com.Hileb.moremomostories.otherMods.SlashBlade;

import com.Hileb.moremomostories.IdlFramework;
import com.Hileb.moremomostories.init.ModConfig;
import com.Hileb.moremomostories.otherMods.SlashBlade.SA.ModSA;
import com.Hileb.moremomostories.otherMods.SlashBlade.SlashBlade.BladeTest;
import com.Hileb.moremomostories.otherMods.SlashBlade.SlashBlade.BladeTianKi;
import com.Hileb.moremomostories.otherMods.SlashBlade.SlashBlade.IBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;

import java.util.ArrayList;
import java.util.List;

public class SlashBladeUtil {
    private final List<Object> bladeList= new ArrayList<>();
    public SlashBladeUtil(){
        bladeList.add(new BladeTest());//测试刀
        bladeList.add(new BladeTest(){
            @Override
            public int getSA() {
                return 82;
            }
        });
        bladeList.add(new BladeTianKi());
    }
    public void registerBlade(){
        for(int i=0;i<bladeList.size();i++){//批量注册
            if(bladeList.get(i) instanceof IBlade){
                IdlFramework.Log("register Blade:%s",((IBlade)(bladeList.get(i))).getName());
                ((IBlade)(bladeList.get(i))).registerBlade();
                ((IBlade)(bladeList.get(i))).registerRecipe();
            }
        }
    }
    public void registerSA(){
        ItemSlashBlade.specialAttacks.put(ModConfig.SlashBlade.SA_RAIN, ModSA.sa1);
        IdlFramework.Log("register sa:%S",ModSA.sa1.toString());
        ItemSlashBlade.specialAttacks.put(ModConfig.SlashBlade.SA_WORLD, ModSA.saUpAndDownWorld);
        IdlFramework.Log("register sa:%S",ModSA.saUpAndDownWorld.toString());
        ItemSlashBlade.specialAttacks.put(ModConfig.SlashBlade.SA_FIRE, ModSA.SA_FIRE);
        IdlFramework.Log("register sa:%S",ModSA.SA_FIRE.toString());
        ItemSlashBlade.specialAttacks.put(ModConfig.SlashBlade.SA_BAKIN, ModSA.saBakin);
        IdlFramework.Log("register sa:%S",ModSA.saBakin.toString());
    }//id,sa
}
