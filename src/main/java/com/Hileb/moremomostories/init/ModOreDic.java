package com.Hileb.moremomostories.init;

import com.Hileb.moremomostories.IdlFramework;
import com.gq2529.momostories.blocks.ModBlocks;
import com.gq2529.momostories.item.ModItems;
import net.minecraftforge.oredict.OreDictionary;

public class ModOreDic {
    public static void init(){
        OreDictionary.registerOre("oreId", ModBlocks.ID);
        OreDictionary.registerOre("dustId", ModItems.ID_SAND);
        IdlFramework.LogWarning("register 1 oreDic");
    }
}
