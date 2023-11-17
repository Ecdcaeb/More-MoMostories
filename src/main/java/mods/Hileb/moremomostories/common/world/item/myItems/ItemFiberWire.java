package mods.Hileb.moremomostories.common.world.item.myItems;

import mods.Hileb.moremomostories.MoreMoMoSrories;
import mods.Hileb.moremomostories.common.world.advancements.Advancementkeys;
import mods.Hileb.moremomostories.common.world.advancements.ModAdvancementsInit;
import mods.Hileb.moremomostories.common.world.item.ItemBase;
import mods.Hileb.moremomostories.common.network.NetworkHandler;
import mods.Hileb.moremomostories.common.network.protocols.PacketEntityNBT;
import mods.Hileb.forgedmomo.utils.nbt.IDFBasicNBTUtil;
import mods.Hileb.forgedmomo.utils.nbt.IDLNBTUtil;
import net.minecraft.block.BlockChest;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.items.CapabilityItemHandler;

import java.util.ArrayList;
import java.util.List;

public class ItemFiberWire extends ItemBase {
    public static String NBT_IS_SLEEP="nbt_hileb.isSleep";
    public static String NBT_ENTITY_ID="nbt_hileb.entityId";
    public static String NBT_IS_CATCH="nbt_hileb.isCatching";
    public ItemFiberWire(String name){
        super(name);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {


        if (entity.canBeAttackedWithItem()){
            if (hasSameVec(entity.getLookVec(),player.getLookVec())){
                BlockPos pos=player.getPosition();
                if (entity.getPosition().getDistance(pos.getX(),pos.getY(),pos.getZ())<=1.5f){

                    NBTTagCompound tagCompound=new NBTTagCompound();
                    tagCompound.setBoolean(NBT_IS_SLEEP,true);
                    entity.getEntityData().setTag( PacketEntityNBT.TAG,tagCompound);

                    NetworkHandler.updateEntityData(entity);
                    if (entity instanceof EntityLiving){
                        EntityLiving living=((EntityLiving)entity);

                        List<EntityAIBase> remove=new ArrayList<>();
                        for(EntityAITasks.EntityAITaskEntry  entry:living.tasks.taskEntries){
                            remove.add(entry.action);
                        }
                        for(EntityAIBase aiBase:remove){
                            living.tasks.removeTask(aiBase);
                        }
                        remove.clear();

                        for(EntityAITasks.EntityAITaskEntry  entry:living.targetTasks.taskEntries){
                            remove.add(entry.action);
                        }
                        for(EntityAIBase aiBase:remove){
                            living.targetTasks.removeTask(aiBase);
                        }
                        remove.clear();

                    }
                    IDLNBTUtil.SetInt(stack,NBT_ENTITY_ID,entity.getEntityId());
                }
            }
        }
        IDLNBTUtil.SetBoolean(stack,NBT_IS_CATCH,false);
        return super.onLeftClickEntity(stack, player, entity);
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (!worldIn.isRemote){

            for(TileEntity te:worldIn.loadedTileEntityList){
                if (te instanceof TileEntityChest){
                    TileEntityChest teChest=(TileEntityChest) te;
                    if(teChest.getTileData().hasKey("isHidingZombie")){
                        long value=teChest.getTileData().getLong("isHidingZombie");
                        if (worldIn.getTotalWorldTime()-value>=20){
                            --teChest.numPlayersUsing;
                            worldIn.addBlockEvent(teChest.getPos(), teChest.getBlockType(), 1, teChest.numPlayersUsing);
                            worldIn.notifyNeighborsOfStateChange(teChest.getPos(), teChest.getBlockType(), false);

                            if (teChest.getChestType() == BlockChest.Type.TRAP)
                            {
                                worldIn.notifyNeighborsOfStateChange(teChest.getPos().down(), teChest.getBlockType(), false);
                            }


                            teChest.getTileData().removeTag("isHidingZombie");

                        }
                    }
                }
            }







            int id=IDLNBTUtil.GetInt(stack,NBT_ENTITY_ID,-1);
            Entity entity=worldIn.getEntityByID(id);
            if (id==-1)return;
            if(entity!=null){
                double dx=entity.posX-entityIn.posX;
                double dy=entity.posY-entityIn.posY;
                double dz=entity.posZ-entityIn.posZ;
                double distance=Math.sqrt(dx*dx+dy*dy+dz*dz);
                if(distance>1.0f){
                    dx=dx/distance;
                    dy=dy/distance;
                    dz=dz/distance;

                    entity.setPosition(entityIn.posX+dx,entityIn.posY+dy,entityIn.posZ+dz);
                }
            }
            else IDLNBTUtil.SetInt(stack,NBT_ENTITY_ID,-1);







        }
    }

    @SubscribeEvent
    public void onPreRenderEntity(RenderLivingEvent.Pre event){
        if (event.getEntity().getEntityData().hasKey(PacketEntityNBT.TAG)){
            NBTTagCompound data=event.getEntity().getEntityData().getCompoundTag(PacketEntityNBT.TAG);
            if (IDFBasicNBTUtil.getBoolean(data,NBT_IS_SLEEP,false)){
                event.getEntity().deathTime=19;
            }
        }
    }
    @SubscribeEvent
    public void onPostRenderEntity(RenderLivingEvent.Post event){
        if (event.getEntity().getEntityData().hasKey(PacketEntityNBT.TAG)){
            NBTTagCompound data=event.getEntity().getEntityData().getCompoundTag(PacketEntityNBT.TAG);
            if (IDFBasicNBTUtil.getBoolean(data,NBT_IS_SLEEP,false)){
                event.getEntity().deathTime=0;
            }
        }
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote){
            ItemStack stack=player.getHeldItem(hand);
            boolean flag=IDLNBTUtil.GetInt(stack,NBT_ENTITY_ID,-1)!=-1;
            if (flag    ){
                TileEntity te=worldIn.getTileEntity(pos);
                if (te!=null){
                    if (te instanceof IInventory){
                        onCatchFinish(worldIn,stack,pos);
                        ModAdvancementsInit.giveAdvancement(player, Advancementkeys.AD_ASS);
                        return EnumActionResult.SUCCESS;
                    }else{
                        for(EnumFacing facing1:EnumFacing.values()){
                            if (te.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,facing1)){
                                onCatchFinish(worldIn,stack,pos);
                                ModAdvancementsInit.giveAdvancement(player, Advancementkeys.AD_ASS);
                                return EnumActionResult.SUCCESS;
                            }
                        }
                    }
                }
            }

        }
        return EnumActionResult.PASS;
    }
    public void onCatchFinish(World world,ItemStack stack,BlockPos pos){
        int id=IDLNBTUtil.GetInt(stack,NBT_ENTITY_ID,-1);
        Entity entity=world.getEntityByID(id);
        if (entity!=null)entity.isDead=true;

        IDLNBTUtil.SetInt(stack,NBT_ENTITY_ID,-1);

        try {
            TileEntityChest tileEntityChest = (TileEntityChest) Blocks.CHEST.getLockableContainer(world, pos);
            if (tileEntityChest!=null){

                if (tileEntityChest.numPlayersUsing < 0)
                {
                    tileEntityChest.numPlayersUsing = 0;
                }

                ++tileEntityChest.numPlayersUsing;
                world.addBlockEvent(tileEntityChest.getPos(), tileEntityChest.getBlockType(), 1, tileEntityChest.numPlayersUsing);
                world.notifyNeighborsOfStateChange(tileEntityChest.getPos(), tileEntityChest.getBlockType(), false);

                if (tileEntityChest.getChestType() == BlockChest.Type.TRAP)
                {
                    world.notifyNeighborsOfStateChange(tileEntityChest.getPos().down(), tileEntityChest.getBlockType(), false);
                }




                world.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_CHEST_OPEN,
                        SoundCategory.BLOCKS, 1f, 1f, false);


                tileEntityChest.prevLidAngle=1.0F;
                tileEntityChest.lidAngle=1.0F;

                tileEntityChest.getTileData().setLong("isHidingZombie",world.getTotalWorldTime());


            }
        }catch (ClassCastException e){
            MoreMoMoSrories.LOGGER.error(e);
        }

    }

    public boolean hasSameVec(Vec3d v1, Vec3d v2){
        return v1.dotProduct(v2)>0;
    }
}
