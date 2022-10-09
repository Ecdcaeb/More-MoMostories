package com.Hileb.moremomostories.item;

import com.Hileb.moremomostories.util.MoMo.MoMoCards;

public class ItemNoCanHit extends ItemBase {
    public ItemNoCanHit(String name){
        super(name,new ItemInformationAdder("item.no_can_hit_it.tip"));
        MoMoCards.registerCard(this);
    }
}
