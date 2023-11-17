package mods.Hileb.moremomostories.common.world.entity;

import mods.Hileb.moremomostories.common.world.entity.entity.living.boss.EntityBossDisdescable;
import mods.Hileb.moremomostories.common.world.entity.entity.living.boss.EntityGoldenGuideBoss;
import mods.Hileb.moremomostories.common.world.entity.entity.projectile.EntityFire;
import mods.Hileb.moremomostories.common.world.entity.entity.projectile.EntityIce;
import mods.Hileb.moremomostories.common.world.entity.entity.projectile.EntityIceMother;
import mods.Hileb.moremomostories.common.world.entity.entity.projectile.EntityRain;
import mods.Hileb.moremomostories.common.world.entity.entity.living.*;
import mods.Hileb.moremomostories.common.world.entity.render.*;
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
