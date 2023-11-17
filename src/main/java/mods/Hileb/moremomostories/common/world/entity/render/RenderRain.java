package mods.Hileb.moremomostories.common.world.entity.render;

import mods.Hileb.moremomostories.common.world.entity.entity.projectile.EntityRain;
import mods.Hileb.moremomostories.common.world.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.util.ResourceLocation;

public class RenderRain extends RenderSnowball<EntityRain> {//投掷物
    private static final ResourceLocation Entity_TEXTURES = new ResourceLocation("moremomostories:textures/entity/rain.png");
    public RenderRain(RenderManager renderManagerIn)
    {
        super(renderManagerIn, ModItems.ITEM_RAIN, Minecraft.getMinecraft().getRenderItem());
    }
}
