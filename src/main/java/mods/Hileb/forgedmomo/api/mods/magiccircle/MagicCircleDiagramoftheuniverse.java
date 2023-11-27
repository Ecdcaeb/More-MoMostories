package mods.Hileb.forgedmomo.api.mods.magiccircle;

import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class MagicCircleDiagramoftheuniverse implements IMagicCircle {
    private List<Item> items;//NonNullList.withSize(6, ItemStack.EMPTY);


    public MagicCircleDiagramoftheuniverse(Item i1, Item i2, Item i3, Item i4, Item i5, Item i6,Item i7, Item i8){
        items= new ArrayList<Item>();
        items.add(i1);
        items.add(i2);
        items.add(i3);
        items.add(i4);
        items.add(i5);
        items.add(i6);
        items.add(i7);
        items.add(i8);
        MagicCirclesForMod.diagramoftheuniverse.add(this);
    }
    public void add(Item item){
        items.add(item);
    }
    public Item get(int index){
        return items.get(index);
    }
    @Override
    public void doCircle(World world, BlockPos pos,TileEntityLockableLoot tileEntityLockableLoot){
        //override
    }
    @Override
    public boolean check(TileEntityLockableLoot loot) {
        for(int i=0;i<loot.getSizeInventory();i++){
            if (loot.getStackInSlot(i).getItem()!=items.get(i))return false;
        }
        return true;
    }
}
