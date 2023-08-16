package com.Hileb.moremomostories.common.world.gui.BookShelf;

import com.Hileb.moremomostories.MoreMoMoSrories;
import com.Hileb.moremomostories.common.world.blocks.tileEntity.TileEntityBookShelf;
import com.Hileb.moremomostories.common.world.item.myItems.ItemBookMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class ContainerBookShelf extends Container
{
    private ItemStackHandler items = new ItemStackHandler(27);
    private TileEntityBookShelf lowerChestInventory;
    //private IInventory chestInv;
    private IInventory playerInv;
    private EntityPlayer playerIn;
    protected List<Slot> slots=new ArrayList<>();
    private final int numRows;
    private final BlockPos pos;

    public ContainerBookShelf(@Nonnull IInventory playerInventory, @Nonnull TileEntityBookShelf chestInventory, @Nonnull EntityPlayer player,@Nonnull BlockPos posIn)
    {

        pos=posIn;
        playerIn=player;
        playerInv=playerInventory;
        this.lowerChestInventory = chestInventory;
        this.numRows = chestInventory.getSizeInventory() / 9;
        chestInventory.openInventory(player);
        int i = (this.numRows - 4) * 18;

        for (int p_114514_=0;p_114514_<27;p_114514_++){
            items.setStackInSlot(p_114514_,lowerChestInventory.getStackInSlot(p_114514_));
        }
        for (int j = 0; j < this.numRows; ++j)
        {
            for (int k = 0; k < 9; ++k)
            {
                Slot slot=new SlotItemHandler(items, k + j * 9, 8 + k * 18, 18 + j * 18){
                    public final int id=this.getSlotIndex();
                    {
                    }

                    @Override
                    public boolean canTakeStack(EntityPlayer playerIn)
                    {
                            return true;
                    }

                    @Override
                    public boolean isItemValid(@Nonnull ItemStack stack) {
                        return stack.getItem() instanceof ItemBookMod;
                    }

                    @Override
                    public int getItemStackLimit(@Nonnull ItemStack stack) {
                        return  stack.getItem().getItemStackLimit(stack);
                    }

                    @Override
                    public void onSlotChanged() {
                        lowerChestInventory.setInventorySlotContents(id,items.getStackInSlot(id));
                    }
                };
                this.addSlotToContainer(slot);
                slots.add(slot);
            }
        }
        //玩家背包

        for (int l = 0; l < 3; ++l)
        {
            for (int j1 = 0; j1 < 9; ++j1)
            {
                this.addSlotToContainer(new Slot(playerInventory, j1 + l * 9 + 9, 8 + j1 * 18, 103 + l * 18 + i));
            }
        }
        //玩家物品栏
        for (int i1 = 0; i1 < 9; ++i1)
        {
            this.addSlotToContainer(new Slot(playerInventory, i1, 8 + i1 * 18, 161 + i));
        }
        MoreMoMoSrories.LogWarning("bookCount:%d",((TileEntityBookShelf)chestInventory).getBookCount());
    }

    /**
     * Determines whether supplied player can use this container
     */
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return this.lowerChestInventory.isUsableByPlayer(playerIn);
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
            lowerChestInventory.setInventorySlotContents(i,items.getStackInSlot(i).copy());
        }
        lowerChestInventory.markDirty();
    }

    /**
     * Return this chest container's lower chest inventory.
     */
    public IInventory getLowerChestInventory()
    {
        return this.lowerChestInventory;
    }
}