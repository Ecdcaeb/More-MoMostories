package com.Hileb.moremomostories.init;

import com.gq2529.momostories.blocks.ModBlocks;
import com.gq2529.momostories.item.ModItems;
import net.minecraftforge.oredict.OreDictionary;

public class ModOreDic {
    public static void init(){
        OreDictionary.registerOre("oreId", ModBlocks.ID);
        OreDictionary.registerOre("dustId", ModItems.ID_SAND);
        OreDictionary.registerOre("dustBook", com.Hileb.moremomostories.item.ModItems.ITEM_BOOK_DUST);
    }
}
