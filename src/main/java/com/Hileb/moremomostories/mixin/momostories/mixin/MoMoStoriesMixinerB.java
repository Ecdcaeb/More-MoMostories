package com.Hileb.moremomostories.mixin.momostories.mixin;

import zone.rong.mixinbooter.IEarlyMixinLoader;
import zone.rong.mixinbooter.ILateMixinLoader;

import java.util.ArrayList;
import java.util.List;


public class MoMoStoriesMixinerB implements ILateMixinLoader {
    @Override
    public List<String> getMixinConfigs() {
        List<String> strings=new ArrayList<>();
        strings.add("mixin.3m.fixbug.mixin.json");
        return strings;
    }

}
