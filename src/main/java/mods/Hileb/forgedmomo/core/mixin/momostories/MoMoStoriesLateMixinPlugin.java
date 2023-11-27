package mods.Hileb.forgedmomo.core.mixin.momostories;

import zone.rong.mixinbooter.ILateMixinLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/11/25 18:16
 **/
@SuppressWarnings("unused")
public class MoMoStoriesLateMixinPlugin implements ILateMixinLoader {
    @Override
    public List<String> getMixinConfigs() {
        List<String> strings=new ArrayList<>();
        strings.add("mixin.3m.card.mixin.json");
        strings.add("mixin.3m.fixbug.mixin.json");
        return strings;
    }
}
