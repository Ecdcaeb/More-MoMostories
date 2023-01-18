package com.Hileb.moremomostories.util.named.nameTag;

import com.Hileb.moremomostories.IdlFramework;
import com.Hileb.moremomostories.util.named.NameTagBase;
import com.Hileb.moremomostories.util.named.NameTags;
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
        return new ResourceLocation(IdlFramework.MODID,"tag_best");
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
