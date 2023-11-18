package mods.Hileb.forgedmomo.core;

import com.google.common.collect.Lists;
import com.google.common.io.Files;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import zone.rong.mixinbooter.IEarlyMixinLoader;

import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/8/27 11:00
 **/
@IFMLLoadingPlugin.Name(ForgedMoMoFMLLoadingPlugin.NAME)
@IFMLLoadingPlugin.MCVersion(ForgeVersion.mcVersion)
public class ForgedMoMoFMLLoadingPlugin implements IEarlyMixinLoader, IFMLLoadingPlugin {
    public static final String NAME="forgedmomo";
    public ForgedMoMoFMLLoadingPlugin(){
    }
    @Override
    @SuppressWarnings("all")
    public List<String> getMixinConfigs() {
        try {
            File earlyMixinList=new File(this.getClass().getResource("/META-INF/early-mixin.utf8").toURI());
            return Files.readLines(earlyMixinList,StandardCharsets.UTF_8);
        } catch (URISyntaxException | IOException e) {
            return Lists.newArrayList();
        }
    }
    @Override
    public String[] getASMTransformerClass() {
        return new String[]{
                //"com.Hileb.forgedmomo.core.asmtransformers.DataFixManagerTransformer"
        };
    }

    @Override
    public String getModContainerClass() {
        return "mods.Hileb.forgedmomo.core.ForgedMoMoContainer";
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
