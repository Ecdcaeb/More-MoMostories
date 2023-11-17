package mods.Hileb.moremomostories.common.world.item.myItems;

import mods.Hileb.moremomostories.common.world.item.ItemBase;
import mods.Hileb.forgedmomo.api.momostories.MoMoCards;

public class ItemNoCanHit extends ItemBase {
    public ItemNoCanHit(String name){
        super(name);
        setDesc("item.no_can_hit_it.tip","item.no_can_hit_it.tip");
        MoMoCards.registerCard(this,MoMoCards.CardType.HILEB);
    }
}
