package mods.Hileb.moremomostories.common.world.entity.render;

import mods.Hileb.moremomostories.common.world.entity.entity.living.EntityZFP;
import mods.Hileb.moremomostories.common.world.entity.model.ModelSakura;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderZFP extends RenderBiped<EntityZFP>
{
    private static final ResourceLocation Entity_TEXTURES = new ResourceLocation("moremomostories:textures/entity/zfp/skin2.png");

    private static final ResourceLocation Entity_TEXTURES_BLACK = new ResourceLocation("moremomostories:textures/entity/zfp/skin3.png");

    public RenderZFP(RenderManager renderManagerIn)
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



    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call RenderLunaBless.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityZFP entity)
    {
        if ("black".equals(entity.getCustomNameTag()))return Entity_TEXTURES_BLACK;
        return Entity_TEXTURES;
    }
}