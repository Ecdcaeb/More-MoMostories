package com.Hileb.moremomostories.common.world.item.interfaces;

import com.Hileb.moremomostories.mixin.minecraft.effect.ExtraMixinRenderItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/8/27 10:34
 **/
public interface IColorEnchantment {
    @SideOnly(Side.CLIENT)
    default void onPreRender(ItemStack stack, RenderItem renderItem, IBakedModel model, int color) {

    }
    @SideOnly(Side.CLIENT)
    default void onRender(ItemStack stack, RenderItem renderItem, IBakedModel model, int color){
        ExtraMixinRenderItem.renderEffect(renderItem,model,color);
    }

    @SideOnly(Side.CLIENT)
    default void onPostRender(ItemStack stack, RenderItem renderItem, IBakedModel model, int color) {
    }

    boolean hasEnchantmentEffect(ItemStack stack);
    int getEnchantmentColor(ItemStack stack);
}
