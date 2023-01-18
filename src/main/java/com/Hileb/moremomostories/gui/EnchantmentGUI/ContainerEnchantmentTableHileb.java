package com.Hileb.moremomostories.gui.EnchantmentGUI;

import com.Hileb.moremomostories.blocks.BlockEnchantmentTableHileb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerEnchantment;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerEnchantmentTableHileb extends ContainerEnchantment {
    private World world;
    private BlockPos pos;
    @SideOnly(Side.CLIENT)
    public ContainerEnchantmentTableHileb(InventoryPlayer playerInv, World worldIn)
    {
        super(playerInv,worldIn);
        world=worldIn;
        pos=BlockPos.ORIGIN;
    }

    public ContainerEnchantmentTableHileb(InventoryPlayer playerInv, World worldIn, BlockPos posIn){
        super(playerInv,worldIn,posIn);
        world=worldIn;
        pos=posIn;
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        if (!(this.world.getBlockState(pos).getBlock() instanceof BlockEnchantmentTableHileb))
        {
            return false;
        }
        else
        {
            return playerIn.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
        }
    }

    @Override
    public boolean enchantItem(EntityPlayer playerIn, int id) {
        //pre
        /*
         if(MinecraftForge.EVENT_BUS.post(new EnchantmentEvent.Pre(playerIn,id)))return false;
         */
        boolean b=super.enchantItem(playerIn, id);
        //post
        /*
        MinecraftForge.EVENT_BUS.post(new EnchantmentEvent.Post(playerIn,id))
         */
        return b;
    }
}
