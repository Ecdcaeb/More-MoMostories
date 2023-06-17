package com.Hileb.moremomostories.gui.cardGUI.card;

import com.Hileb.moremomostories.item.ModItems;
import com.Hileb.moremomostories.item.myItems.ItemCardContainer;
import com.Hileb.moremomostories.util.MoMo.MoMoCards;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class ContainerCard extends Container
{
    private ItemStackHandler items ;
    private ItemStack itemContainer;
    private IInventory playerInv;
    private EntityPlayer playerIn;
    protected List<Slot> slots=new ArrayList<>();
    private final int numRows;
    public ContainerCard(@Nonnull IInventory playerInventory, int hand ,@Nonnull EntityPlayer player)
    {

        playerIn=player;
        playerInv=playerInventory;
        this.numRows = 3;
        int i = (this.numRows - 4) * 18;
        if (hand==0){
            itemContainer=playerIn.getHeldItem(EnumHand.MAIN_HAND);
        }else itemContainer=playerIn.getHeldItem(EnumHand.OFF_HAND);

        items=ItemCardContainer.getItemStackHandler(itemContainer);

        for (int j = 0; j < this.numRows; ++j)
        {
            for (int k = 0; k < 9; ++k)
            {
                Slot slot=new SlotItemHandler(items, k + j * 9, 8 + k * 18, 18 + j * 18){
                    public final int id=this.getSlotIndex();

                    @Override
                    public boolean canTakeStack(EntityPlayer playerIn)
                    {
                            return true;
                    }

                    @Override
                    public boolean isItemValid(@Nonnull ItemStack stack) {
                        return stack.getItem()!= ModItems.ITEM_CARD_CONTAINER && MoMoCards.isCard(stack.getItem());
                    }

                    @Override
                    public int getItemStackLimit(@Nonnull ItemStack stack) {
                        return  stack.getItem().getItemStackLimit(stack);
                    }

                    @Override
                    public void onSlotChanged() {
                        ItemCardContainer.setItemStackHandler(itemContainer,items);
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
        ItemCardContainer.setItemStackHandler(itemContainer,items);
    }


}