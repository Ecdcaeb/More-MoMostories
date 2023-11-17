package mods.Hileb.moremomostories.common.capabilities.nametag.named.nameTag;

import mods.Hileb.moremomostories.MoreMoMoSrories;
import mods.Hileb.moremomostories.common.capabilities.nametag.named.NameTagBase;
import mods.Hileb.moremomostories.common.capabilities.nametag.named.NameTags;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.Random;

public class NameTagBest extends NameTagBase {
    public NameTagBest(){
        NameTags.TAGS.add(this);
    }
    @Override
    public ResourceLocation getRegisterName() {
        return new ResourceLocation(MoreMoMoSrories.MODID,"tag_best");
    }

    @Override
    public boolean couldApply(ItemStack stack, Random random) {
        return random.nextInt(100)>=50;
    }

    @Override
    public String renderName(ItemStack stack) {
        return "§e"+I18n.format("tag.tag_best.name")+"§f";
    }
}
