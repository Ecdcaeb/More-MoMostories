package com.Hileb.moremomostories.otherMods.SlashBlade.SA.Entity;

import com.Hileb.moremomostories.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;

public class RenderFire extends RenderSnowball<EntityFire> {//投掷物
    //private static final ResourceLocation Entity_TEXTURES = new ResourceLocation("moremomostories:textures/entity/rain.png");
    public RenderFire(RenderManager renderManagerIn)
    {
        super(renderManagerIn, ModItems.ITEM_FIRE, Minecraft.getMinecraft().getRenderItem());
    }

    @Override
    public void doRender(EntityFire entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.disableLighting();
        int j = 15728880 % 65536;
        int k = 15728880 / 65536;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)j, (float)k);
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
        GlStateManager.enableLighting();
    }
}
