package mods.Hileb.forgedmomo.core.mixin.momostories.mixin.cardMixins;

import mods.Hileb.forgedmomo.core.mixin.momostories.CardFunction;
import com.gq2529.momostories.item.bows.LunaHunting;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/8/11 9:46
 **/
@Mixin(LunaHunting.class)
public abstract class MixinLunaHunting extends Item {
    /**
     * @author
     * @reason
     */
    @Overwrite
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
        CardFunction.LunaHunting.onPlayerStoppedUsing((LunaHunting)(Object)this,stack,worldIn,entityLiving,timeLeft);
    }
    /**
     * @author
     * @reason
     */
    @SubscribeEvent
    @Overwrite
    public static void luna_hunting(LivingHurtEvent event){
        CardFunction.LunaHunting.luna_hunting(event);
    }

    @Override
    public void onUsingTick(ItemStack stack, EntityLivingBase player, int count) {
        super.onUsingTick(stack, player, count);
    }
}
