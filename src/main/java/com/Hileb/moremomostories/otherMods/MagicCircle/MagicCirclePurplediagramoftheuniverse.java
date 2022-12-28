package com.Hileb.moremomostories.otherMods.MagicCircle;

import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class MagicCirclePurplediagramoftheuniverse {
    private List<Item> items;//NonNullList.withSize(6, ItemStack.EMPTY);
    public MagicCirclePurplediagramoftheuniverse(List<Item> list){
        items=list;
        MagicCirclesForMod.purplediagramoftheuniverse.add(this);
    }
    public MagicCirclePurplediagramoftheuniverse(){
        items= new ArrayList<Item>();
    }
    public MagicCirclePurplediagramoftheuniverse(Item i1, Item i2, Item i3, Item i4, Item i5, Item i6,Item i7, Item i8){
        items= new ArrayList<Item>();
        items.add(i1);
        items.add(i2);
        items.add(i3);
        items.add(i4);
        items.add(i5);
        items.add(i6);
        items.add(i7);
        items.add(i8);
        MagicCirclesForMod.purplediagramoftheuniverse.add(this);
    }
    public void add(Item item){
        items.add(item);
    }
    public Item get(int index){
        return items.get(index);
    }
    public boolean compare(MagicCirclePurplediagramoftheuniverse mag){
        for(int i=0;i<items.size();i++){
            if (mag.get(i)!=items.get(i))return false;
        }
        return true;
    }
    public static MagicCirclePurplediagramoftheuniverse getFromPurplediagramoftheuniverse(TileEntityLockableLoot inv){
        MagicCirclePurplediagramoftheuniverse six=new MagicCirclePurplediagramoftheuniverse();
        six.add(inv.getStackInSlot(0).getItem());
        six.add(inv.getStackInSlot(1).getItem());
        six.add(inv.getStackInSlot(2).getItem());
        six.add(inv.getStackInSlot(3).getItem());
        six.add(inv.getStackInSlot(4).getItem());
        six.add(inv.getStackInSlot(5).getItem());
        six.add(inv.getStackInSlot(6).getItem());
        six.add(inv.getStackInSlot(7).getItem());
        return six;
    }
    public void doCircle(World world, BlockPos pos){
        //override
    }
}
