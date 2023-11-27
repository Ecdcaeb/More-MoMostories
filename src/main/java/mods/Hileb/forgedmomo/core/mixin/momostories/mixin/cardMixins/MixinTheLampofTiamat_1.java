package mods.Hileb.forgedmomo.core.mixin.momostories.mixin.cardMixins;

import mods.Hileb.forgedmomo.core.mixin.momostories.CardFunction;
import com.gq2529.momostories.item.tools.ModTool.TheLampofTiamat_1;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin( TheLampofTiamat_1.class)
public abstract class MixinTheLampofTiamat_1 extends Item {

    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        CardFunction.TheLampofTiamat_1.onUpdate(stack,worldIn,entityIn,itemSlot,isSelected);
    }
}
