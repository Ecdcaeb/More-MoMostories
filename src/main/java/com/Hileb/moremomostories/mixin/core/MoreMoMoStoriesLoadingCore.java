package com.Hileb.moremomostories.mixin.core;

import com.Hileb.moremomostories.MoreMoMoSrories;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import zone.rong.mixinbooter.IEarlyMixinLoader;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/8/27 11:00
 **/
@IFMLLoadingPlugin.Name(MoreMoMoStoriesLoadingCore.NAME)
@IFMLLoadingPlugin.MCVersion(ForgeVersion.mcVersion)
public class MoreMoMoStoriesLoadingCore implements IEarlyMixinLoader, IFMLLoadingPlugin {
    public static final String NAME="ForgedMoMo";
    public MoreMoMoStoriesLoadingCore(){
    }
    @Override
    public List<String> getMixinConfigs() {
        List<String> strings=new ArrayList<>();
        strings.add("mixin.3m.early.minecraft.mixin.json");
        return strings;
    }
    @Override
    public String[] getASMTransformerClass() {
        return new String[]{
                //"com.Hileb.moremomostories.mixin.core.asmtransformers.DataFixManagerTransformer"
        };
    }

    @Override
    public String getModContainerClass() {
        return "com.Hileb.moremomostories.mixin.core.ForgedMoMoContainer";
    }

    @Nullable
    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {

    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
