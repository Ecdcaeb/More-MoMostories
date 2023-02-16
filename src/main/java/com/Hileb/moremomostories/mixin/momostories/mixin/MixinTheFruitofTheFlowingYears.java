package com.Hileb.moremomostories.mixin.momostories.mixin;

import com.Hileb.moremomostories.mixin.momostories.event.CardHooks;
import com.gq2529.momostories.MoMoFramework;
import com.gq2529.momostories.item.ModItemStoryboards.TheFruitofTheFlowingYears;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mod.EventBusSubscriber(modid = MoMoFramework.MODID)
@Mixin(TheFruitofTheFlowingYears.class)
public abstract class MixinTheFruitofTheFlowingYears extends Item {
    @Overwrite
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        return CardHooks.TheFruitofTheFlowingYears.onItemRightClick(worldIn,playerIn,handIn);
    }

    @SubscribeEvent
    public static void onRightUse(PlayerInteractEvent.RightClickBlock event) {
        CardHooks.TheFruitofTheFlowingYears.onRightUse(event);
    }
}
