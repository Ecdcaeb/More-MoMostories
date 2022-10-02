package com.Hileb.moremomostories.common;

import net.minecraftforge.common.MinecraftForge;

public class ModEventReader {
    public ModEventReader(){
        MinecraftForge.EVENT_BUS.register(this);
    }

}
