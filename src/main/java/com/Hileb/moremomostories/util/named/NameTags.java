package com.Hileb.moremomostories.util.named;

import com.Hileb.moremomostories.util.named.nameTag.*;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public  class NameTags {
    public static List<NameTagBase> TAGS=new ArrayList<>();

    public static NameTagBase BEST=new NameTagBest();

    public static NameTagBase TAG_1=new NameTagBestBase("1");
    public static NameTagBase TAG_2=new NameTagBestBase("2");
    public static NameTagBase TAG_3=new NameTagBestBase("3");
    public static NameTagBase TAG_4=new NameTagBestBase("4");
    public static NameTagBase TAG_5=new NameTagBestBase("5");
    public static NameTagBase TAG_6=new NameTagBestBase("6");
    public static NameTagBase TAG_7=new NameTagBestBase("7");
    public static NameTagBase TAG_8=new NameTagBestBase("8");
    public static NameTagBase TAG_9=new NameTagBestBase("9");
    public static NameTagBase TAG_10=new NameTagBestBase("10");
    public static NameTagBase TAG_11=new NameTagBestBase("11");
    public static NameTagBase TAG_12=new NameTagBestBase("12");
    public static NameTagBase TAG_13=new NameTagBestBase("13");
    public static NameTagBase TAG_14=new NameTagBestBase("14");


    public static void register(){
        NameTagHandler.registerAll(TAGS);
    }
}
