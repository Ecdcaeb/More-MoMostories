package com.Hileb.moremomostories.world.biome;

import com.Hileb.moremomostories.IdlFramework;
import com.Hileb.moremomostories.entity.entity.living.EntityBookworm;
import com.Hileb.moremomostories.entity.entity.living.EntityGoldenGuide;
import com.Hileb.moremomostories.entity.entity.living.boss.EntityBossDisdescable;
import com.Hileb.moremomostories.entity.entity.living.boss.EntityGoldenGuideBoss;
import com.Hileb.moremomostories.init.ModConfig;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BiomeBook extends Biome {

    protected static final WorldGenAbstractTree TREE = new WorldGenBigTree(false);

    public BiomeBook() {
        super(new BiomeProperties("biome_book").setBaseHeight(-1.5f).setHeightVariation(1.2f).setTemperature(0.5f).setWaterColor(0xff3333));

        this.spawnableCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();



        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityGoldenGuide.class, 10, 10, 100));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityBookworm.class, 10, 10, 100));

        topBlock = Blocks.GRASS.getDefaultState();
        fillerBlock = Blocks.DIRT.getDefaultState();

        decorator.coalGen = new WorldGenMinable(Blocks.PLANKS.getDefaultState(), 10);

        decorator.treesPerChunk = 2;


        MinecraftForge.EVENT_BUS.register(this);
    }

    public BiomeBook(BiomeProperties properties) {
        super(properties);
    }


    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random random)
    {
        return TREE;
    }


    @Override
    public boolean canRain() {
        return true;
    }


}
