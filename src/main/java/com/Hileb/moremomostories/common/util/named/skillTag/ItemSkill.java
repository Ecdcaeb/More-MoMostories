package com.Hileb.moremomostories.common.util.named.skillTag;

import java.util.ArrayList;
import java.util.List;

public class ItemSkill {
    public List<String> tooltips=new ArrayList<>();
    public final String registerNAME;
    public ItemSkill(String registerName){
        registerNAME=registerName;
        ItemSkillList.REGISTER.put(registerNAME,this);
    }
    public void onTooltip(List<String> strings){
        strings.addAll(tooltips);
    }
}
