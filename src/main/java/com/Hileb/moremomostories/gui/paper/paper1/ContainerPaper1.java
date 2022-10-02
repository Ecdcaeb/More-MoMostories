package com.Hileb.moremomostories.gui.paper.paper1;

import com.Hileb.moremomostories.item.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

//https://fmltutor.ustc-zzzz.net/3.4.2-GUI%E7%95%8C%E9%9D%A2%E7%9A%84%E4%B8%AA%E6%80%A7%E5%8C%96%E4%B8%8E%E7%89%A9%E5%93%81%E6%A7%BD%E7%9A%84%E6%B7%BB%E5%8A%A0.html
public class ContainerPaper1 extends Container {

    public ContainerPaper1(EntityPlayer player)
    {
        super();
    }
    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

    @Override
    public void onContainerClosed(EntityPlayer playerIn)
    {
        //if the gui does not connects a tile entity, this must be manually rewritten
        super.onContainerClosed(playerIn);
    }
    
}
