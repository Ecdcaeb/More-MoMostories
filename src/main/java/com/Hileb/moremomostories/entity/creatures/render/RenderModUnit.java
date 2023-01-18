package com.Hileb.moremomostories.entity.creatures.render;

import com.Hileb.moremomostories.entity.creatures.EntityModUnit;
import com.Hileb.moremomostories.entity.creatures.model.ModelSakura;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderModUnit extends net.minecraft.client.renderer.entity.RenderBiped<EntityModUnit> {
    private static final ResourceLocation DEFAULT_RES_LOC = new ResourceLocation("textures/entity/steve.png");

    public RenderModUnit(net.minecraft.client.renderer.entity.RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelSakura(), 0.5F);
    }


    public RenderModUnit(net.minecraft.client.renderer.entity.RenderManager renderManagerIn, net.minecraft.client.model.ModelBiped modelBipedIn, float shadowSize) {
        super(renderManagerIn, modelBipedIn, shadowSize);
//        LayerBipedArmor layerbipedarmor = new LayerBipedArmor(this)
//        {
//            protected void initArmor()
//            {
//                this.modelLeggings = new ModelZombie(0.5F, true);
//                this.modelArmor = new ModelZombie(1.0F, true);
//            }
//        };
//        this.addLayer(layerbipedarmor);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityModUnit entity)
    {
        return DEFAULT_RES_LOC;
    }
}
