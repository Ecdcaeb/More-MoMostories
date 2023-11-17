package mods.Hileb.moremomostories.common.init;

import mods.Hileb.moremomostories.common.world.level.structure.StructurePrimerTree;
import mods.Hileb.moremomostories.common.world.level.structure.StructureTest;
import mods.Hileb.moremomostories.common.world.level.worldgen.ModWoldGenHilebRoom;
import mods.Hileb.moremomostories.common.world.level.worldgen.ModWoldGenOreXe;
import mods.Hileb.moremomostories.common.world.level.worldgen.ModWorldGenLogNoLeaf;
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
