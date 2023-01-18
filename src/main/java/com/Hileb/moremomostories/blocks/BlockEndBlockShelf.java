package com.Hileb.moremomostories.blocks;

import com.Hileb.moremomostories.IdlFramework;
import com.Hileb.moremomostories.command.ModCommands;
import com.Hileb.moremomostories.entity.EntityGoldenGuide;
import com.Hileb.moremomostories.item.ModItems;
import com.Hileb.moremomostories.item.myItems.ItemBookMod;
import com.Hileb.moremomostories.worldgen.WorldGenHelper;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

public class BlockEndBlockShelf extends BlockBase{
    public BlockEndBlockShelf(String name){
        super(name, Material.WOOD);
        setHardness(50.0F);
        setResistance(2000.0F);
        setHarvestLevel("null", 9);
        setLightLevel(0.4f);
        Blocks.FIRE.setFireInfo(this,60,200);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public boolean isWood(IBlockAccess world, BlockPos pos) {
        return true;
    }

    @SubscribeEvent
    public void onPlayerClickShelf(PlayerInteractEvent.RightClickBlock event){
        World world=event.getWorld();
        if (!world.isRemote){
            if (event.getWorld().getBlockState(event.getPos()).getBlock()==Blocks.BOOKSHELF){
                if (event.getEntityPlayer().getHeldItem(event.getHand()).getItem()== ModItems.ITEM_BOOK){
                    world.setBlockState(event.getPos(), this.getDefaultState(),3);
                }
            }
        }
    }
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote){
            if (playerIn.getHeldItem(hand).getItem() == ModItems.ITEM_BOOK){
                playerIn.getHeldItem(hand).shrink(1);
                return true;
            }
            else if (hand==EnumHand.MAIN_HAND && playerIn.getHeldItem(hand).isEmpty()){
                ModCommands.give(playerIn, ItemBookMod.getBook());
                worldIn.setBlockState(pos, Blocks.BOOKSHELF.getDefaultState(),3);
                return true;
            }
        }
        return false;
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!worldIn.isRemote){
            IdlFramework.LogWarning("sssss");
            spawnVan(worldIn,pos);
        }
    }
    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        if (state.getBlock()==this)
        {
            worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn) + worldIn.rand.nextInt(10));
        }
    }

    public int tickRate(World worldIn)
    {
        return 3000;
    }
    @Override
    public boolean requiresUpdates()
    {
        return true;
    }

    @Override
    public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face) {
        return 100;
    }

    @Override
    public boolean isFireSource(World world, BlockPos pos, EnumFacing side) {
        return true;
    }
    public static void spawnVan(World world, BlockPos posIn){
        EntityGoldenGuide goldenGuide=new EntityGoldenGuide(world);
        Random random=new Random(world.getSeed()+posIn.hashCode()+world.getTotalWorldTime());
        int toomore=0;
        while(true){
            BlockPos pos=posIn.add(random.nextInt(12)-6,random.nextInt(12)-6,random.nextInt(12)-6);
            AxisAlignedBB axisAlignedBB=new AxisAlignedBB(pos,pos.add(0,1,0));
            if (WorldGenHelper.isEmptyWithAABB(world,axisAlignedBB)){
                goldenGuide.setPosition(pos.getX(),pos.getY(),pos.getZ());
                world.spawnEntity(goldenGuide);
                return;
            }
            else if (toomore>=100){
                return;
            }
        }
    }
}
