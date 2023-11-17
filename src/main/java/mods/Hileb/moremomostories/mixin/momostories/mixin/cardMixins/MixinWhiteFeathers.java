package mods.Hileb.moremomostories.mixin.momostories.mixin.cardMixins;

import mods.Hileb.moremomostories.mixin.momostories.event.CardFunction;
import com.gq2529.momostories.item.tools.ModTool.WhiteFeathers;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(WhiteFeathers.class)
public abstract class MixinWhiteFeathers extends Item {

    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        CardFunction.WhiteFeathers.onUpdate(stack,worldIn,entityIn,itemSlot,isSelected);
    }

}
