package mods.Hileb.moremomostories.common.world.entity.render;

import mods.Hileb.moremomostories.common.world.entity.entity.projectile.EntityIce;
import mods.Hileb.moremomostories.common.world.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;

public class RenderIce extends RenderSnowball<EntityIce> {//投掷物
    //private static final ResourceLocation Entity_TEXTURES = new ResourceLocation("moremomostories:textures/entity/rain.png");
    public RenderIce(RenderManager renderManagerIn)
    {
        super(renderManagerIn, ModItems.ITEM_RAIN, Minecraft.getMinecraft().getRenderItem());
    }

    @Override
    public void doRender(EntityIce entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.disableLighting();
        int j = 15728880 % 65536;
        int k = 15728880 / 65536;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)j, (float)k);
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
        GlStateManager.enableLighting();
    }
}
