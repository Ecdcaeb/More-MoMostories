package mods.Hileb.moremomostories.common.capabilities.nametag.named.nameTag;

import mods.Hileb.moremomostories.MoreMoMoSrories;
import mods.Hileb.moremomostories.common.capabilities.nametag.named.NameTagBase;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.Random;

public class NameTagBestBase extends NameTagBase {
    public final String NAME;
    public NameTagBestBase(String nameIn){
        super();
        NAME=nameIn;
    }
    @Override
    public ResourceLocation getRegisterName() {
        return new ResourceLocation(MoreMoMoSrories.MODID,NAME);
    }

    @Override
    public boolean couldApply(ItemStack stack, Random random) {
        return random.nextInt(100)>=50;
    }

    @Override
    public String renderName(ItemStack stack) {
        return "§e"+I18n.format("tag"+NAME+".name")+"§f";
    }
}
