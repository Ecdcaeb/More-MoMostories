package mods.Hileb.moremomostories.common.world.entity.render;

import mods.Hileb.moremomostories.common.world.entity.entity.living.EntityZQ;
import mods.Hileb.moremomostories.common.world.entity.model.ModelEntityZQ;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderZQ extends RenderLivingBase<EntityZQ>
{
    private static final ResourceLocation Entity_TEXTURES = new ResourceLocation("moremomostories:textures/entity/zq/entityzq.png");

    public RenderZQ(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelEntityZQ(), 0.5F);
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
    protected ResourceLocation getEntityTexture(EntityZQ entity)
    {
        return Entity_TEXTURES;
    }
}
