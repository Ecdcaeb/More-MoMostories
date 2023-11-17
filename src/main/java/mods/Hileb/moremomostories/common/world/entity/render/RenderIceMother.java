package mods.Hileb.moremomostories.common.world.entity.render;

import mods.Hileb.moremomostories.common.world.entity.entity.projectile.EntityIceMother;
import mods.Hileb.moremomostories.common.world.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;

public class RenderIceMother extends RenderSnowball<EntityIceMother> {//投掷物
    //private static final ResourceLocation Entity_TEXTURES = new ResourceLocation("moremomostories:textures/entity/rain.png");
    public RenderIceMother(RenderManager renderManagerIn)
    {
        super(renderManagerIn, ModItems.ITEM_Z, Minecraft.getMinecraft().getRenderItem());
    }

    @Override
    public void doRender(EntityIceMother entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.disableLighting();
        int j = 15728880 % 65536;
        int k = 15728880 / 65536;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)j, (float)k);
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
        GlStateManager.enableLighting();
    }
}
