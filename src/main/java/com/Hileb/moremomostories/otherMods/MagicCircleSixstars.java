package com.Hileb.moremomostories.otherMods;

import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class MagicCircleSixstars {
    private List<Item> items;//NonNullList.withSize(6, ItemStack.EMPTY);
    public MagicCircleSixstars(List<Item> list){
        items=list;
    }
    public MagicCircleSixstars(){
        items= new ArrayList<Item>();
    }
    public MagicCircleSixstars(Item i1,Item i2,Item i3,Item i4,Item i5,Item i6){
        items= new ArrayList<Item>();
        items.add(i1);
        items.add(i2);
        items.add(i3);
        items.add(i4);
        items.add(i5);
        items.add(i6);
    }
    public void add(Item item){
        items.add(item);
    }
    public Item get(int index){
        return items.get(index);
    }
    public boolean compare(MagicCircleSixstars mag){
        for(int i=0;i<items.size();i++){
            if (mag.get(i)!=items.get(i))return false;
        }
        return true;
    }
    public static MagicCircleSixstars getFromSixstars(net.mcreator.magiccircle.block.BlockSixstars.TileEntityCustom inv){
        MagicCircleSixstars six=new MagicCircleSixstars();
        six.add(inv.func_70301_a(0).getItem());
        six.add(inv.func_70301_a(1).getItem());
        six.add(inv.func_70301_a(2).getItem());
        six.add(inv.func_70301_a(3).getItem());
        six.add(inv.func_70301_a(4).getItem());
        six.add(inv.func_70301_a(5).getItem());
        return six;
    }
}
