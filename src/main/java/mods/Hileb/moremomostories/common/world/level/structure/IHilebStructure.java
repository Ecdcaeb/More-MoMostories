package mods.Hileb.moremomostories.common.world.level.structure;

import net.minecraft.world.chunk.ChunkPrimer;

public interface IHilebStructure {
    String getName();
    boolean doStructure(int x,int z,ChunkPrimer primer);// true == build successful
}
