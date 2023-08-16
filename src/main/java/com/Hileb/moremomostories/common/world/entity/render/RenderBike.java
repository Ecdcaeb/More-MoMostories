package com.Hileb.moremomostories.common.world.entity.render;

import com.Hileb.moremomostories.MoreMoMoSrories;
import com.Hileb.moremomostories.common.world.entity.entity.living.EntityBike;
import com.Hileb.moremomostories.common.world.entity.model.ModelEntityBycile;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderBike extends RenderLiving<EntityBike> {
    private static final ResourceLocation Entity_TEXTURES = new ResourceLocation(MoreMoMoSrories.MODID,"textures/entity/bike_texture.png");

    public RenderBike(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelEntityBycile(), 0.5F);
    }

    @Override
    protected void preRenderCallback(EntityBike entitylivingbaseIn, float partialTickTime) {
        GlStateManager.scale(2.5f,2.5f,2.5f);
    }


    protected ResourceLocation getEntityTexture(EntityBike entity)
    {
        return Entity_TEXTURES;
    }
}
