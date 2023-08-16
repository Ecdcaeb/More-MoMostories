package com.Hileb.moremomostories.common.world.item.myItems;

import com.Hileb.moremomostories.common.world.item.ItemBase;
import com.Hileb.moremomostories.mods.momo.MoMoCards;

public class ItemNoCanHit extends ItemBase {
    public ItemNoCanHit(String name){
        super(name);
        setDesc("item.no_can_hit_it.tip","item.no_can_hit_it.tip");
        MoMoCards.registerCard(this,MoMoCards.CardType.HILEB);
    }
}
