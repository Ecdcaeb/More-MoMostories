package com.Hileb.moremomostories.entity.creatures.render;

import com.Hileb.moremomostories.entity.EntityDeathMM;
import com.Hileb.moremomostories.entity.model.ModelDeath;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.util.ResourceLocation;

public class RenderDeath extends RenderLivingBase<EntityDeathMM> {
    private static final ResourceLocation Entity_TEXTURES = new ResourceLocation("moremomostories:textures/entity/zq/entity_death.png");

    public RenderDeath(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelDeath(), 0.5F);
        LayerBipedArmor layerbipedarmor = new LayerBipedArmor(this)
        {
            protected void initArmor()
            {
            }
        };
        this.addLayer(layerbipedarmor);
    }


    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityDeathMM entity)
    {
        return Entity_TEXTURES;
    }
}
