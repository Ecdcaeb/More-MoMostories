package mods.Hileb.moremomostories.common.world.entity;

import mods.Hileb.moremomostories.MoreMoMoSrories;
import mods.Hileb.moremomostories.common.init.ModBiomes;
import mods.Hileb.moremomostories.common.world.entity.entity.EntityItemX;
import mods.Hileb.moremomostories.common.world.entity.entity.living.boss.EntityBossDisdescable;
import mods.Hileb.moremomostories.common.world.entity.entity.living.boss.EntityGoldenGuideBoss;
import mods.Hileb.moremomostories.common.world.entity.entity.living.EntityBookworm;
import mods.Hileb.moremomostories.common.world.entity.entity.projectile.EntityFire;
import mods.Hileb.moremomostories.common.world.entity.entity.projectile.EntityIce;
import mods.Hileb.moremomostories.common.world.entity.entity.projectile.EntityIceMother;
import mods.Hileb.moremomostories.common.world.entity.entity.projectile.EntityRain;
import mods.Hileb.moremomostories.common.util.Reference;
import mods.Hileb.moremomostories.common.world.entity.entity.living.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModEntityInit {
    private static int ENTITY_NEXT_ID = 1;
    public static final ResourceLocation GUIDE=new ResourceLocation(MoreMoMoSrories.MODID,"entity_golden_guide");
    public static final ResourceLocation GUIDE_BOSS=new ResourceLocation(MoreMoMoSrories.MODID,"entity_boss_golden_guide");

    public static final ResourceLocation BOSS_DESC=new ResourceLocation(MoreMoMoSrories.MODID,"entity_boss_disdescable");
    public static void registerEntities()
    {
        registerEntityEgg("entity_zfp", EntityZFP.class);
        registerEntityEgg("entity_zq", EntityZQ.class);
        registerEntityEggLess("entity_mm", EntityDeathMM.class);
        registerEntityEgg("entity_golden_guide", EntityGoldenGuide.class);
        registerEntityEgg("entity_van", EntityVan.class);

        registerEntityEgg("entity_bike",EntityBike.class);

        registerEntityEggLess("entity_rain", EntityRain.class);
        registerEntityEggLess("entity_fire", EntityFire.class);
        registerEntityEggLess("entity_ice", EntityIce.class);
        registerEntityEggLess("entity_icem", EntityIceMother.class);

        registerEntityEgg("entity_bookworm", EntityBookworm.class);
        registerEntityEgg("entity_boss_disdescable", EntityBossDisdescable.class);
        registerEntityEgg("entity_boss_golden_guide", EntityGoldenGuideBoss.class);

        registerEntityEgg("entity_mob_chest", EntityMobChest.class);
        registerEntityEgg("item", EntityItemX.class);


        //Examples
//        registerEntity("moroon_orbital_beacon", EntityMoroonBombBeacon.class);
//        registerEntity("moroon_tainter", EntityMoroonTainter.class,0xff00ff, 0x000033);
//        registerEntity("idealland_whitetower_core", EntityIDLWhiteTowerCore.class, ENTITY_NEXT_ID, 128, 0xeeee00, 0xffffff);

        //the bullet
        //registerEntity("bullet", EntityIdlProjectile.class);

        //Assign Dungeons
        //DungeonHooks.addDungeonMob(EntityList.getKey(EntityMoroonTainter.class), STANDARD_DUNGEON_MOB_RARITY);

        //registerEntitySpawn(EntityGoldenGuide.class,100,4,10,EnumCreatureType.CREATURE, ModBiomes.BIOME_BOOK);
        EntityRegistry.addSpawn(EntityGoldenGuide.class, 10, 10, 100,EnumCreatureType.CREATURE, ModBiomes.BIOME_BOOK);
        EntityRegistry.addSpawn(EntityBookworm.class, 10, 10, 100,EnumCreatureType.CREATURE, ModBiomes.BIOME_BOOK);
        EntityRegistry.addSpawn(EntityGoldenGuideBoss.class, 1, 0,1,EnumCreatureType.CREATURE, ModBiomes.BIOME_BOOK);
        EntityRegistry.addSpawn(EntityBossDisdescable.class, 1, 0,1,EnumCreatureType.CREATURE, ModBiomes.BIOME_BOOK);
    }

    private  static  void registerEntityEgg(String name, Class<? extends Entity> entity)
    {
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + name), entity, name, ENTITY_NEXT_ID, MoreMoMoSrories.instance, 50, 1, true,0xff00ff, 0x000000);
        ENTITY_NEXT_ID++;
    }
    private  static  void registerEntityEggLess(String name, Class<? extends Entity> entity)
    {
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + name), entity, name,ENTITY_NEXT_ID, MoreMoMoSrories.instance, 50, 1, true);
        ENTITY_NEXT_ID++;
    }
}
