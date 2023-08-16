package com.Hileb.moremomostories.common.world.item.paper;

import com.Hileb.moremomostories.common.world.advancements.Advancementkeys;
import com.Hileb.moremomostories.common.world.gui.ModGuiElementLoader;
import com.Hileb.moremomostories.common.world.item.ItemInformationAdder;

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
