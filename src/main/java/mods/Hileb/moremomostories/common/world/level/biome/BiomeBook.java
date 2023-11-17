package mods.Hileb.moremomostories.common.world.level.biome;

import mods.Hileb.moremomostories.common.world.entity.entity.living.EntityBookworm;
import mods.Hileb.moremomostories.common.world.entity.entity.living.EntityGoldenGuide;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.common.MinecraftForge;

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
