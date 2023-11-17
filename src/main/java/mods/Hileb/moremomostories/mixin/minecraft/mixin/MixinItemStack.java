package mods.Hileb.moremomostories.mixin.minecraft.mixin;

import mods.Hileb.moremomostories.mixin.minecraft.effect.ExtraMixinRenderItem;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/8/27 15:43
 **/
@Mixin(ItemStack.class)
public class MixinItemStack{
    @Inject(method = "hasEffect",at=@At("HEAD"),cancellable = true)
    public void onHasEffect(CallbackInfoReturnable<Boolean> cir){
        if (ExtraMixinRenderItem.getColor((ItemStack) (Object)this)!=null){
            cir.setReturnValue(false);
        }
    }
}
