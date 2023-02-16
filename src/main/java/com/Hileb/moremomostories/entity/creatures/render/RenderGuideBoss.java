package com.Hileb.moremomostories.entity.creatures.render;

import com.Hileb.moremomostories.entity.boss.EntityGoldenGuideBoss;
import com.Hileb.moremomostories.entity.creatures.model.ModelSakura;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderGuideBoss extends RenderBiped<EntityGoldenGuideBoss>
{
    private static final ResourceLocation Entity_TEXTURES = new ResourceLocation("moremomostories:textures/entity/goldenguide.png");

    public RenderGuideBoss(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelSakura(), 0.5F);
        LayerBipedArmor layerbipedarmor = new LayerBipedArmor(this)
        {
            protected void initArmor()
            {
                this.modelLeggings = new ModelSakura(0.5F, true);
                this.modelArmor = new ModelSakura(1.0F, true);
            }
        };
        this.addLayer(layerbipedarmor);
    }
    @Override
    protected void preRenderCallback(EntityGoldenGuideBoss entity, float partialTickTime)
    {
        GlStateManager.scale(3F, 3F, 3F);
    }



    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityGoldenGuideBoss entity)
    {
        return Entity_TEXTURES;
    }
}