package com.Hileb.moremomostories.entity;

import com.Hileb.moremomostories.IdlFramework;
import com.Hileb.moremomostories.entity.creatures.moroon.EntityMoroonUnitBase;
import com.Hileb.moremomostories.entity.creatures.render.RenderBullet;
import com.Hileb.moremomostories.entity.creatures.render.RenderMoroonHumanoid;
import com.Hileb.moremomostories.entity.creatures.render.RenderZFP;
import com.Hileb.moremomostories.entity.creatures.render.RenderZQ;
import com.Hileb.moremomostories.entity.projectiles.EntityIdlProjectile;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler {

    public static void registerEntityRenders() {
        RenderingRegistry.registerEntityRenderingHandler(EntityMoroonUnitBase.class, RenderMoroonHumanoid::new);


        RenderingRegistry.registerEntityRenderingHandler(EntityZFP.class, RenderZFP::new);


        RenderingRegistry.registerEntityRenderingHandler(EntityZQ.class, RenderZQ::new);


        RenderingRegistry.registerEntityRenderingHandler(EntityIdlProjectile.class, renderManager -> new RenderBullet<>(renderManager, new ResourceLocation(IdlFramework.MODID,
                "textures/entity/projectiles/bullet_norm.png")));

    }
}
