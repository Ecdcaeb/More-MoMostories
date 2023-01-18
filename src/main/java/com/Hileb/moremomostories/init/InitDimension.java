package com.Hileb.moremomostories.init;

import com.Hileb.moremomostories.world.dimension.DimensionEdge;
import com.Hileb.moremomostories.world.dimension.DimensionHileb;
import com.Hileb.moremomostories.world.dimension.DimensionOne;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class InitDimension {
    public static final DimensionType DIM_ONE = DimensionType.register("Dim_one", "_testdim",ModConfig.dimension.WORLD_GEN_CONF, DimensionOne.class, false);

    public static DimensionType DIM_UNIV = DimensionType.register("hileb_dimension", "_advmain", ModConfig.dimension.WORLD_GEN_CONF, DimensionHileb.class, false);
    public static void registerDimensions() {

        //DimensionManager.registerDimension(ModConfig.dimension.WORLD_GEN_CONF, DIM_UNIV);
        DimensionManager.registerDimension(ModConfig.dimension.WORLD_GEN_CONF, DIM_ONE);
        DimensionManager.registerDimension(DimensionEdge.ID, DimensionEdge.DIM_TYPE);
    }

    public static NBTTagCompound getDimensionData(World world) {
        return world.getWorldInfo().getDimensionData(ModConfig.dimension.WORLD_GEN_CONF);
    }

    public void init() {

    }
}
