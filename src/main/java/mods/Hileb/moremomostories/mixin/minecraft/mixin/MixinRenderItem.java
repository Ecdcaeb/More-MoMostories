package mods.Hileb.moremomostories.mixin.minecraft.mixin;

import mods.Hileb.moremomostories.mixin.minecraft.effect.ExtraMixinRenderItem;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/8/27 11:02
 **/
@Mixin(RenderItem.class)
public class MixinRenderItem{
    @Inject(method = "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/renderer/block/model/IBakedModel;)V",at=@At("RETURN"))
    public void onRenderEffect(ItemStack stack, IBakedModel model, CallbackInfo ci){
        ExtraMixinRenderItem.onRender((RenderItem)(Object)this,stack,model);
    }
    //出现Bug，原有的附魔会与之叠加。
}
