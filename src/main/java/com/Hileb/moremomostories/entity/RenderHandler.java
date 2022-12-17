package com.Hileb.moremomostories.entity;

import com.Hileb.moremomostories.IdlFramework;
import com.Hileb.moremomostories.entity.creatures.moroon.EntityMoroonUnitBase;
import com.Hileb.moremomostories.entity.creatures.render.*;
import com.Hileb.moremomostories.entity.projectiles.EntityIdlProjectile;
import com.Hileb.moremomostories.otherMods.SlashBlade.SA.Entity.EntityRain;
import com.Hileb.moremomostories.otherMods.SlashBlade.SA.Entity.RenderRain;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler {

    public static void registerEntityRenders() {
        RenderingRegistry.registerEntityRenderingHandler(EntityMoroonUnitBase.class, RenderMoroonHumanoid::new);


        RenderingRegistry.registerEntityRenderingHandler(EntityZFP.class, RenderZFP::new);


        RenderingRegistry.registerEntityRenderingHandler(EntityZQ.class, RenderZQ::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityDeathMM.class, RenderDeath::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityGoldenGuide.class, RenderGuide::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityVan.class, RenderVan::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityRain.class, RenderRain::new);


        RenderingRegistry.registerEntityRenderingHandler(EntityIdlProjectile.class, renderManager -> new RenderBullet<>(renderManager, new ResourceLocation(IdlFramework.MODID,
                "textures/entity/projectiles/bullet_norm.png")));

    }
}
