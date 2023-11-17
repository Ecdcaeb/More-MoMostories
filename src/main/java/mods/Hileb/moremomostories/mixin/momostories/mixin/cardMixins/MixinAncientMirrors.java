package mods.Hileb.moremomostories.mixin.momostories.mixin.cardMixins;

import mods.Hileb.moremomostories.mixin.momostories.event.CardFunction;
import com.gq2529.momostories.item.tools.ModTool.AncientMirrors;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(AncientMirrors.class)
public abstract class MixinAncientMirrors extends Item {


    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        return CardFunction.AncientMirrors.onItemRightClick(worldIn,playerIn,handIn);
    }
}
