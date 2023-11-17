package com.Hileb.moremomostories.common.world.blocks.tile.bookshelf;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntityBookShelf extends TileEntityLockable implements ISidedInventory {
    private NonNullList<ItemStack> chestContents = NonNullList.<ItemStack>withSize(27, ItemStack.EMPTY);

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        saveAllItems(compound, this.chestContents);
        super.writeToNBT(compound);
        return compound;
    }
    public static NBTTagCompound saveAllItems(NBTTagCompound tag, NonNullList<ItemStack> list)
    {
        //this.chestContents = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.saveAllItems(tag, list);

        return tag;
    }
    public static void loadAllItems(NBTTagCompound tag, NonNullList<ItemStack> list)
    {
        ItemStackHelper.loadAllItems(tag, list);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        loadAllItems(compound, this.chestContents);
        super.readFromNBT(compound);
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
        return oldState.getBlock() != newState.getBlock();
    }
    public NBTTagCompound getUpdateTag()
    {
        return this.writeToNBT(new NBTTagCompound());
    }
    public TileEntityBookShelf(){

    }
    public int getBookCount(){
        int use=0;
        for (int i=0;i<getSizeInventory();i++){
            if (!getStackInSlot(i).isEmpty()){
                use++;
            }
        }
        return use;
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
        return true;
    }

    @Override
    public void closeInventory(EntityPlayer player) {
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }
    @Override
    public boolean isUsableByPlayer(EntityPlayer player)
    {
        if (this.world.getTileEntity(this.pos) != this)
        {
            return false;
        }
        else
        {
            return player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
        }
    }
    @Override
    public boolean isEmpty() {
        return getBookCount()==0;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return chestContents.get(index);
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
        chestContents.set(index,stack);
    }
    public static enum reLoadType{
        WRITE,
        READ
    }
    public void reLoad(reLoadType type){
        NBTTagCompound tag=this.getTileData();
        switch (type){
            case WRITE:
                writeToNBT(tag);
            case READ:
                readFromNBT(tag);
        }
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    @Override
    public int getSizeInventory() {
        return chestContents.size();
    }

    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        return new int[] {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26};
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        return true;
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        return true;
    }
}
