package mods.Hileb.moremomostories.common.world.entity.render;

import mods.Hileb.moremomostories.common.world.entity.entity.living.EntityVan;
import mods.Hileb.moremomostories.common.world.entity.model.ModelSakura;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.util.ResourceLocation;

public class RenderVan extends RenderLivingBase<EntityVan> {
    private static final ResourceLocation Entity_TEXTURES = new ResourceLocation("moremomostories:textures/entity/entity_van.png");

    public RenderVan(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelSakura(), 0.5F);
        LayerBipedArmor layerbipedarmor = new LayerBipedArmor(this)
        {
            protected void initArmor()
            {
            }
        };
        this.addLayer(layerbipedarmor);
    }


    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call RenderLunaBless.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityVan entity)
    {
        return Entity_TEXTURES;
    }
}
