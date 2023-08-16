package com.Hileb.moremomostories.mods.slashblade.blade;

import com.Hileb.moremomostories.mods.slashblade.blade.myBlades.BladeRain;
import com.Hileb.moremomostories.mods.slashblade.blade.myBlades.BladeSaki;
import com.Hileb.moremomostories.mods.slashblade.blade.myBlades.BladeTianKi;

import java.util.LinkedList;
import java.util.List;

public class ModBlades {
    public static List<BladeType> REGISTER=new LinkedList<>();

    public static final BladeType SAKI=new BladeSaki();
    public static final BladeType RAIN=new BladeRain();
    public static final BladeType TIAN_KI=new BladeTianKi();
}
