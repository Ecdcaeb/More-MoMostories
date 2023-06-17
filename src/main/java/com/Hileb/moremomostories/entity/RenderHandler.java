package com.Hileb.moremomostories.entity;

import com.Hileb.moremomostories.IdlFramework;
import com.Hileb.moremomostories.entity.entity.EntityItemX;
import com.Hileb.moremomostories.entity.entity.living.boss.EntityBossDisdescable;
import com.Hileb.moremomostories.entity.entity.living.boss.EntityGoldenGuideBoss;
import com.Hileb.moremomostories.entity.entity.living.*;
import com.Hileb.moremomostories.entity.render.*;
import com.Hileb.moremomostories.otherMods.SlashBlade.SA.Entity.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler {

    public static void registerEntityRenders() {


        RenderingRegistry.registerEntityRenderingHandler(EntityZFP.class, RenderZFP::new);


        RenderingRegistry.registerEntityRenderingHandler(EntityZQ.class, RenderZQ::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityDeathMM.class, RenderDeath::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityGoldenGuide.class, RenderGuide::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityVan.class, RenderVan::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityRain.class, RenderRain::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityFire.class, RenderFire::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityIce.class, RenderIce::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityIceMother.class, RenderIceMother::new);


        RenderingRegistry.registerEntityRenderingHandler(EntityMobChest.class,RenderMobChest::new);



        RenderingRegistry.registerEntityRenderingHandler(EntityBossDisdescable.class, RenderUndescable::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGoldenGuideBoss.class, RenderGuideBoss::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityBike.class, RenderBike::new);


    }
}
