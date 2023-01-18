package com.Hileb.moremomostories.util.named;

import com.Hileb.moremomostories.util.named.nameTag.*;

import java.util.ArrayList;
import java.util.List;

public  class NameTags {
    public static List<NameTagBase> TAGS=new ArrayList<>();

    public static NameTagBase BEST=new NameTagBest();

    public static void register(){
        NameTagHandler.registerAll(TAGS);
    }
}
