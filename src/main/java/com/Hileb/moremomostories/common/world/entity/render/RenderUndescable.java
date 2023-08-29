package com.Hileb.moremomostories.common.world.entity.render;

import com.Hileb.moremomostories.common.world.entity.entity.living.boss.EntityBossDisdescable;
import com.Hileb.moremomostories.common.world.item.armorMaterials.ClientModels;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
@SuppressWarnings(value = "FileNotFoundException")
public class RenderUndescable extends RenderLivingBase<EntityBossDisdescable> {
    private static final ResourceLocation Entity_TEXTURES = new ResourceLocation("moremomostories:textures/entity/zq/entity_death.png");

    public RenderUndescable(RenderManager renderManagerIn)
    {
        super(renderManagerIn, ClientModels.modelDesc, 2.0F);
    }
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call RenderLunaBless.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityBossDisdescable entity)
    {
        return Entity_TEXTURES;
    }

}
