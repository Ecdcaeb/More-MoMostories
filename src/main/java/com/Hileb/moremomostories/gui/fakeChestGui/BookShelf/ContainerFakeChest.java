package com.Hileb.moremomostories.gui.fakeChestGui.BookShelf;

import com.Hileb.moremomostories.IdlFramework;
import com.Hileb.moremomostories.entity.entity.living.EntityMobChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;

import java.util.ArrayList;
import java.util.List;

public class ContainerFakeChest extends Container
{
    private ItemStackHandler items = new ItemStackHandler(27);
    private EntityMobChest chest;
    private EntityPlayer player;
    protected List<SlotClient> slots=new ArrayList<>();
    private final int numRows;
    private final BlockPos pos;

    public ContainerFakeChest(EntityPlayer playerIn, World world, BlockPos posIn)
    {

        pos=posIn;
        player=playerIn;
        this.numRows = 3;
        int i = (this.numRows - 4) * 18;

        for (int j = 0; j < this.numRows; ++j)
        {
            for (int k = 0; k < 9; ++k)
            {
                SlotClient slot=new SlotClient(items, k + j * 9, 8 + k * 18, 18 + j * 18);
                this.addSlotToContainer(slot);
                slots.add(slot);
            }
        }
        //玩家背包

        for (int l = 0; l < 3; ++l)
        {
            for (int j1 = 0; j1 < 9; ++j1)
            {
                this.addSlotToContainer(new Slot(playerIn.inventory, j1 + l * 9 + 9, 8 + j1 * 18, 103 + l * 18 + i));
            }
        }
        //玩家物品栏
        for (int i1 = 0; i1 < 9; ++i1)
        {
            this.addSlotToContainer(new Slot(playerIn.inventory, i1, 8 + i1 * 18, 161 + i));
        }

        if (chest==null){
            List<EntityMobChest> mobChests=world.getEntitiesWithinAABB(EntityMobChest.class,new AxisAlignedBB(posIn.add(-1,-1,-1),posIn.add(1,1,1)));
            if (mobChests.isEmpty())chest=null;
            else {
                chest=mobChests.get(0);
                loot();

            }
        }
    }

    public void loot(){
        for(int i=0;i<27;i++){
            this.items.setStackInSlot(i,chest.itemStackHandler.getStackInSlot(i).copy());
        }
    }
    /**
     * Determines whether supplied player can use this container
     */
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return true;
    }

    /**
     * Handle when the stack in slot {@code index} is shift-clicked. Normally this moves the stack between the player
     * inventory and the other inventory(s).
     */
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index < this.numRows * 9)
            {
                if (!this.mergeItemStack(itemstack1, this.numRows * 9, this.inventorySlots.size(), true))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, this.numRows * 9, false))
            {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }

    @Override
    public void onContainerClosed(EntityPlayer playerIn) {
        super.onContainerClosed(playerIn);
        for(int i=0;i<27;i++){
            items.setStackInSlot(i, ItemStack.EMPTY);
        }
    }

}