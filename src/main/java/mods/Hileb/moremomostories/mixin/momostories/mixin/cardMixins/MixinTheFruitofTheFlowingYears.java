package mods.Hileb.moremomostories.mixin.momostories.mixin.cardMixins;

import mods.Hileb.moremomostories.mixin.momostories.event.CardFunction;
import com.gq2529.momostories.item.ModItemStoryboards.TheFruitofTheFlowingYears;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(TheFruitofTheFlowingYears.class)
public abstract class MixinTheFruitofTheFlowingYears extends Item {

    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        return CardFunction.TheFruitofTheFlowingYears.onItemRightClick(worldIn,playerIn,handIn);
    }

    @Overwrite
    @SubscribeEvent
    public static void onRightUse(PlayerInteractEvent.RightClickBlock event) {
        CardFunction.TheFruitofTheFlowingYears.onRightUse(event);
    }
}
