package mods.Hileb.forgedmomo.core.mixin.momostories.mixin.cardMixins;

import mods.Hileb.forgedmomo.core.mixin.momostories.CardFunction;
import com.gq2529.momostories.item.ModItemStoryboards.FragmentsOfFalseGods;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(FragmentsOfFalseGods.class)
public abstract class MixinFragmentsOfFalseGods extends Item {

    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        return CardFunction.FragmentsOfFalseGods.onItemRightClick(worldIn,playerIn,handIn);
    }
}
