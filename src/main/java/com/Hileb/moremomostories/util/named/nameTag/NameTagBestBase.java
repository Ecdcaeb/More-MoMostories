package com.Hileb.moremomostories.util.named.nameTag;

import com.Hileb.moremomostories.IdlFramework;
import com.Hileb.moremomostories.util.named.NameTagBase;
import com.Hileb.moremomostories.util.named.NameTags;
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
        return new ResourceLocation(IdlFramework.MODID,NAME);
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
