package mods.Hileb.moremomostories.common.world.item.paper;

import mods.Hileb.moremomostories.common.world.advancements.Advancementkeys;
import mods.Hileb.moremomostories.common.world.gui.ModGuiElementLoader;
import mods.Hileb.moremomostories.common.world.item.ItemInformationAdder;

public class ItemPaper1 extends ItemPaperBase {
    @SuppressWarnings("dep-ann")
    public final ItemInformationAdder item_WWWW__=new ItemInformationAdder("ttooltip.paper1.tooltip","tooltip.paper1.tooltip");
    public ItemPaper1(){
        super("item_paper_idonotwanttodie", ModGuiElementLoader.GUI_PAPER_1, Advancementkeys.AD_PAPER1);
    }

    @Override
    public ItemInformationAdder paperDesc() {
        return item_WWWW__;
    }
}
