package mods.Hileb.moremomostories.mixin.momostories.mixin.cardMixins;

import mods.Hileb.moremomostories.mixin.momostories.event.CardFunction;
import com.gq2529.momostories.item.tools.ModTool.Month;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin( Month.class)
public abstract class MixinMonth extends Item {

    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        return CardFunction.Month.onItemRightClick(worldIn,playerIn,handIn);
    }
}
