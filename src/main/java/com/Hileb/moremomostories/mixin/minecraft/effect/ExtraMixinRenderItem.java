package com.Hileb.moremomostories.mixin.minecraft.effect;

import com.Hileb.moremomostories.common.world.item.interfaces.IColorEnchantment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/8/27 11:06
 **/
public class ExtraMixinRenderItem {
    public static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");

    public static IColorEnchantment getColor(ItemStack stack){
        IColorEnchantment iColorEnchantment=null;
        if (stack.getItem() instanceof IColorEnchantment){
            iColorEnchantment=(IColorEnchantment) stack.getItem();
        }else if (stack.isItemEnchanted()){
            for(Enchantment enchantment: EnchantmentHelper.getEnchantments(stack).keySet()){
                if (enchantment instanceof IColorEnchantment){
                    IColorEnchantment i=(IColorEnchantment) enchantment;
                    if (i.hasEnchantmentEffect(stack)){
                        iColorEnchantment=i;
                    }
                }
            }
        }
        return iColorEnchantment;
    }
    public static void onRender(RenderItem renderItem,ItemStack stack, IBakedModel model){
        IColorEnchantment iColorEnchantment=getColor(stack);
        if (iColorEnchantment!=null && iColorEnchantment.hasEnchantmentEffect(stack)){//试着支持附魔，而不是物品本身决定光效。
            int color= iColorEnchantment.getEnchantmentColor(stack);
            if (!model.isBuiltInRenderer()) {
                ExtraMixinRenderItem.renderEffect(renderItem,model,color);
            }
        }
    }

    public static void renderEffect(RenderItem renderItem,IBakedModel model,int color)
    {
        GlStateManager.pushMatrix();
        GlStateManager.translate(-0.5F, -0.5F, -0.5F);
        GlStateManager.depthMask(false);
        GlStateManager.depthFunc(514);
        GlStateManager.disableLighting();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_COLOR, GlStateManager.DestFactor.ONE);
        //at :
        //public+f net.minecraft.client.renderer.RenderItem field_175057_n #textureManager
        renderItem.textureManager.bindTexture(RES_ITEM_GLINT);

        GlStateManager.matrixMode(5890);
        GlStateManager.pushMatrix();
        GlStateManager.scale(8.0F, 8.0F, 8.0F);
        float f = (float)(Minecraft.getSystemTime() % 3000L) / 3000.0F / 8.0F;
        GlStateManager.translate(f, 0.0F, 0.0F);
        GlStateManager.rotate(-50.0F, 0.0F, 0.0F, 1.0F);
        //at:
        //public net.minecraft.client.renderer.RenderItem func_191967_a(Lnet.minecraft.client.renderer.block.model.IBakedModel;ILnet.minecraft.item.ItemStack;)V #net.minecraft.client.renderer.RenderItem.renderModel(Lnet.minecraft.client.renderer.block.model.IBakedModel;ILnet.minecraft.item.ItemStack;)V
        //color = color -#FFFFFF mojang的特殊设计
        renderItem.renderModel(model, color-0xFFFFFF,ItemStack.EMPTY);//因为是光所以是EMPTY
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.scale(8.0F, 8.0F, 8.0F);
        float f1 = (float)(Minecraft.getSystemTime() % 4873L) / 4873.0F / 8.0F;
        GlStateManager.translate(-f1, 0.0F, 0.0F);
        GlStateManager.rotate(10.0F, 0.0F, 0.0F, 1.0F);
        //at:
        //public net.minecraft.client.renderer.RenderItem func_191967_a(Lnet.minecraft.client.renderer.block.model.IBakedModel;ILnet.minecraft.item.ItemStack;)V #net.minecraft.client.renderer.RenderItem.renderModel(Lnet.minecraft.client.renderer.block.model.IBakedModel;ILnet.minecraft.item.ItemStack;)V
        renderItem.renderModel(model, color-0xFFFFFF,ItemStack.EMPTY);
        GlStateManager.popMatrix();
        GlStateManager.matrixMode(5888);
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.enableLighting();
        GlStateManager.depthFunc(515);
        GlStateManager.depthMask(true);
        //at :
        //public+f net.minecraft.client.renderer.RenderItem field_175057_n #textureManager
        renderItem.textureManager.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        GlStateManager.popMatrix();
    }
}
