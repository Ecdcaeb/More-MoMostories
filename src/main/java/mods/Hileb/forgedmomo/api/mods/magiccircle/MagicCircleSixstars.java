package mods.Hileb.forgedmomo.api.mods.magiccircle;

import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class MagicCircleSixstars implements IMagicCircle {
    private List<Item> items;//NonNullList.withSize(6, ItemStack.EMPTY);

    public MagicCircleSixstars(Item i1,Item i2,Item i3,Item i4,Item i5,Item i6){
        items= new ArrayList<Item>();
        items.add(i1);
        items.add(i2);
        items.add(i3);
        items.add(i4);
        items.add(i5);
        items.add(i6);
        MagicCirclesForMod.sixStars.add(this);
    }
    public void add(Item item){
        items.add(item);
    }
    public Item get(int index){
        return items.get(index);
    }

    public void doCircle(TileEntityLockableLoot inv){
        //override
    }

    @Override
    public boolean check(TileEntityLockableLoot loot) {
        for(int i=0;i<loot.getSizeInventory();i++){
            if (loot.getStackInSlot(i).getItem()!=items.get(i))return false;
        }
        return true;
    }

    @Override
    public void doCircle(World world, BlockPos pos,TileEntityLockableLoot tileEntityLockableLoot) {
        doCircle(tileEntityLockableLoot);
    }
}
