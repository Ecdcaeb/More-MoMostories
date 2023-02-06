package com.Hileb.moremomostories.init;

import com.Hileb.moremomostories.world.structure.StructurePrimerTree;
import com.Hileb.moremomostories.world.structure.StructureTest;
import com.Hileb.moremomostories.worldgen.ModWoldGenHilebRoom;
import com.Hileb.moremomostories.worldgen.ModWoldGenOreXe;
import com.Hileb.moremomostories.worldgen.ModWorldGenLogNoLeaf;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModGenInit {
    public static void init(FMLPreInitializationEvent event){
        registerStructure();
        registerWorldGenerator();
    }
    private static void registerWorldGenerator(){
        GameRegistry.registerWorldGenerator(new ModWoldGenOreXe(), 120);
        GameRegistry.registerWorldGenerator(new ModWoldGenHilebRoom(), 120);
        GameRegistry.registerWorldGenerator(new ModWorldGenLogNoLeaf(), 120);
    }
    private static void registerStructure(){
        StructurePrimerTree.register();
        StructureTest.register();
    }
}
