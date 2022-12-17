package com.Hileb.moremomostories.blocks.tileEntity.BlockXe;

import com.Hileb.moremomostories.blocks.ModBlocks;
import com.Hileb.moremomostories.item.ModItems;
import com.Hileb.moremomostories.item.myItems.ItemXe;
import com.Hileb.moremomostories.item.myItems.RGBColor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;

public class TileEntityXeMachen extends TileEntityLockable implements ITickable, ISidedInventory {
    private NonNullList<ItemStack> stacks = NonNullList.<ItemStack>withSize(2, ItemStack.EMPTY);
    private double red=0;
    private double green=0;
    private double blue=0;
    public TileEntityXeMachen(){
    }
    public void inAColor(RGBColor color){
        red=red+((double)(color.getRed()/256));
        green=green+((double)(color.getGreen()/256));
        blue=blue+((double)(color.getBlue()/256));
    }
    @Override
    public void update() {
        if (!world.isRemote){
            if (getStackInSlot(0).getItem()== ModItems.ITEM_XE){
                if (ItemXe.getLevel(getStackInSlot(0))==0){
                    inAColor(ItemXe.getRGBColor(getStackInSlot(0)));
                    setInventorySlotContents(0,ItemStack.EMPTY);
                }
            }
            if (getStackInSlot(0).getItem().getRegistryName()== ModBlocks.BLOCK_ORE_XE_BLACK.getRegistryName()){
                inAColor(ItemXe.COLOR_BLACK);
                setInventorySlotContents(0,ItemStack.EMPTY);
            }
            if (getStackInSlot(0).getItem().getRegistryName()== ModBlocks.BLOCK_ORE_XE_RED.getRegistryName()){
                inAColor(ItemXe.COLOR_RED);
                setInventorySlotContents(0,ItemStack.EMPTY);
            }
            if (getStackInSlot(0).getItem().getRegistryName()== ModBlocks.BLOCK_ORE_XE_BLUE.getRegistryName()){
                inAColor(ItemXe.COLOR_BLUE);
                setInventorySlotContents(0,ItemStack.EMPTY);
            }
        }
    }

    @Override
    public String getGuiID() {
        return null;
    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
        return null;
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return false;
    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return stacks.get(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        return null;
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        stacks.set(index,stack);
    }

    @Override
    public int getInventoryStackLimit() {
        return stacks.size();
    }

    @Override
    public int getSizeInventory() {
        return stacks.size();
    }

    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        int ints[]=new int[1];
        if (side==EnumFacing.DOWN)ints[0]=1;
        else ints[0]=0;
        return ints;
        //return new int[0];
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        return false;
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        return false;
    }
}
