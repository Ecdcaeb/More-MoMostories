package com.Hileb.moremomostories.entity;

import com.Hileb.moremomostories.IdlFramework;
import com.Hileb.moremomostories.otherMods.SlashBlade.SA.Entity.EntityFire;
import com.Hileb.moremomostories.otherMods.SlashBlade.SA.Entity.EntityIce;
import com.Hileb.moremomostories.otherMods.SlashBlade.SA.Entity.EntityIceMother;
import com.Hileb.moremomostories.otherMods.SlashBlade.SA.Entity.EntityRain;
import com.Hileb.moremomostories.util.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.datafix.DataFixer;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModEntityInit {
    private static int ENTITY_NEXT_ID = 1;
    public static void registerEntities()
    {
        registerEntity("entity_zfp",EntityZFP.class);
        registerEntity("entity_zq",EntityZQ.class);
        registerEntity("entity_mm",EntityDeathMM.class);
        registerEntity("entity_golden_guide",EntityGoldenGuide.class);
        registerEntity("entity_van",EntityVan.class);

        registerEntity("entity_rain", EntityRain.class);
        registerEntity("entity_fire", EntityFire.class);
        registerEntity("entity_ice", EntityIce.class);
        registerEntity("entity_icem", EntityIceMother.class);

        registerEntity("entity_bookworm", EntityBookworm.class);
        //Examples
//        registerEntity("moroon_orbital_beacon", EntityMoroonBombBeacon.class);
//        registerEntity("moroon_tainter", EntityMoroonTainter.class,0xff00ff, 0x000033);
//        registerEntity("idealland_whitetower_core", EntityIDLWhiteTowerCore.class, ENTITY_NEXT_ID, 128, 0xeeee00, 0xffffff);

        //the bullet
        //registerEntity("bullet", EntityIdlProjectile.class);

        //Assign Dungeons
        //DungeonHooks.addDungeonMob(EntityList.getKey(EntityMoroonTainter.class), STANDARD_DUNGEON_MOB_RARITY);

        //registerEntitySpawn(EntityGoldenGuide.class,100,4,10,EnumCreatureType.CREATURE, InitBiome.BIOME_BOOK);
        DataFixer datafixer = new DataFixer(1343);
    }

    private  static  void registerEntity(String name, Class<? extends Entity> entity)
    {
        registerEntity(name, entity, ENTITY_NEXT_ID, 50, 0xff00ff, 0x000000);
    }

    private  static  void registerEntity(String name, Class<? extends Entity> entity, int color1, int color2)
    {
        registerEntity(name, entity, ENTITY_NEXT_ID, 50, color1, color2);
    }

    private  static  void registerEntity(String name, Class<? extends Entity> entity, int id, int range, int color1, int color2){
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + name),
                entity,
                name,
                id,
                IdlFramework.instance,
                range,
                1,
                true,
                color1, color2
                );
        ENTITY_NEXT_ID++;
    }
}
