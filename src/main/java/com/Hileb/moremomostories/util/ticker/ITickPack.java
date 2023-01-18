package com.Hileb.moremomostories.util.ticker;

import net.minecraft.world.World;

public interface ITickPack {
    boolean should(World worldIn);
    void apply(World worldIn);
    String toString();
    void fromString();
}
