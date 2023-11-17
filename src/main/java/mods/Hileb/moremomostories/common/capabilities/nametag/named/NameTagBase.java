package mods.Hileb.moremomostories.common.capabilities.nametag.named;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.Random;

public abstract class NameTagBase {
    public NameTagBase(){}
    public abstract ResourceLocation getRegisterName();
    public abstract boolean couldApply(ItemStack stack, Random random);
    public abstract String renderName(ItemStack stack);
}
