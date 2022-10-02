package com.Hileb.moremomostories.item.paper;

import com.Hileb.moremomostories.Advancements.Advancementkeys;
import com.Hileb.moremomostories.gui.ModGuiElementLoader;
import com.Hileb.moremomostories.item.ItemInformationAdder;

public class ItemPaper1 extends ItemPaperBase {
    public final ItemInformationAdder item_WWWW__=new ItemInformationAdder("ttooltip.paper1.tooltip","tooltip.paper1.tooltip");
    public ItemPaper1(){
        super("item_paper_idonotwanttodie", ModGuiElementLoader.GUI_PAPER_1, Advancementkeys.AD_PAPER1);
    }

    @Override
    public ItemInformationAdder paperDesc() {
        return item_WWWW__;
    }
}
