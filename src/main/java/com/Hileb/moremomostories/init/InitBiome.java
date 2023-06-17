package com.Hileb.moremomostories.init;

import com.Hileb.moremomostories.IdlFramework;
import com.Hileb.moremomostories.world.biome.BiomeBook;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class InitBiome {
    public  static  final Biome BIOME_BOOK = new BiomeBook();
    public static void registerBiomes()
    {
        initBiome(BIOME_BOOK, "biome_book", BiomeManager.BiomeType.WARM, Type.HILLS, Type.DENSE);
    }

    public static Biome initBiome(Biome biome, String name, BiomeManager.BiomeType biomeType, Type... type)
    {
        biome.setRegistryName(name);
        ForgeRegistries.BIOMES.register(biome);
        IdlFramework.Log("Biome registered:%s", name);
        BiomeDictionary.addTypes(biome, type);
        //BiomeManager.addBiome(biomeType, new BiomeEntry(biome, 100));
        BiomeManager.addSpawnBiome(biome);
        return biome;
    }

}
