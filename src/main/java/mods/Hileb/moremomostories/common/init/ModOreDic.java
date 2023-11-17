package mods.Hileb.moremomostories.common.init;

import com.gq2529.momostories.blocks.ModBlocks;
import com.gq2529.momostories.item.ModItems;
import net.minecraftforge.oredict.OreDictionary;

public class ModOreDic {
    public static void init(){
        OreDictionary.registerOre("oreId", ModBlocks.ID);
        OreDictionary.registerOre("dustId", ModItems.ID_SAND);
        OreDictionary.registerOre("dustBook", mods.Hileb.moremomostories.common.world.item.ModItems.ITEM_BOOK_DUST);

        OreDictionary.registerOre("blood",ModItems.DEVILS_BLOOD_BUCKET);
        OreDictionary.registerOre("bloodDevils",ModItems.DEVILS_BLOOD_BUCKET);
    }
}
