package com.Hileb.moremomostories.item.myItems;

import com.Hileb.moremomostories.item.ItemBase;
import com.Hileb.moremomostories.util.NBTStrDef.IDLNBTUtil;
import com.Hileb.moremomostories.util.YTXSYSounds;
import net.minecraft.block.BlockJukebox;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemYTXSY extends ItemBase {
    public static final String NBT_SOUND_ID="com.hileb.momo.sound.id";
    public ItemYTXSY(String name){
        super(name);
        MinecraftForge.EVENT_BUS.register(this);
        YTXSYSounds.init();
    }

    @SubscribeEvent
    public void onPlayerClickBlockJukebox(PlayerInteractEvent.RightClickBlock event){
        World world=event.getWorld();
        if(event.getHand()== EnumHand.OFF_HAND || world.isRemote || event.isCanceled() || event.getEntityPlayer().getHeldItem(event.getHand()).getItem() instanceof ItemRecord){
            return;
        }
        //是唱片机，是本唱片
        //IdlFramework.LogWarning("I run event");
        ItemStack stack=event.getEntityPlayer().getHeldItem(event.getHand());
        if (stack.getItem()==this){//点击唱片机播放且储存
            //IdlFramework.LogWarning("2");
            if (world.getBlockState(event.getPos()).getBlock()== Blocks.JUKEBOX){
                net.minecraft.util.SoundEvent soundEvent=getSound(stack);
                //IdlFramework.LogWarning("3");
                TileEntity tileEntity =world.getTileEntity(event.getPos());
                if (tileEntity!=null && tileEntity instanceof  BlockJukebox.TileEntityJukebox){
                    //IdlFramework.LogWarning("4");
                    BlockJukebox.TileEntityJukebox jukebox=(BlockJukebox.TileEntityJukebox)tileEntity;
                    if (jukebox.getRecord().isEmpty()){
                        if (soundEvent!=null){
                            //IdlFramework.LogWarning("5");
                            ItemStack copy=stack.copy();
                            copy.setCount(1);
                            jukebox.setRecord(copy);
                            stack.shrink(1);

                            //IdlFramework.LogWarning("--%s",soundEvent.getSoundName().toString());
                            //
                            //event.getEntityPlayer().playSound(soundEvent,1.0F,1.0F);
                            //world.setBlockState(event.getPos(), world.getBlockState(event.getPos()).withProperty(BlockJukebox.HAS_RECORD, Boolean.valueOf(true)), 3);

                            //IdlFramework.LogWarning("send su %s",jukebox.getRecord().getUnlocalizedName());

                            world.playSound((EntityPlayer)null, event.getPos().getX(), event.getPos().getY(),event.getPos().getZ(), soundEvent, SoundCategory.RECORDS, 1.0F,1.0F);
                            //world.playRecord(event.getPos(),soundEvent);


                            event.setCanceled(true);//取消防止原版触发取出
                            return;
                        }

                    }
                }
            }
        }

        if (stack.isEmpty()){//点击唱片机取回
            if (world.getBlockState(event.getPos()).getBlock()== Blocks.JUKEBOX){
                //IdlFramework.LogWarning("I get record");
                TileEntity tileEntity =world.getTileEntity(event.getPos());
                if (tileEntity!=null && tileEntity instanceof  BlockJukebox.TileEntityJukebox){
                    //IdlFramework.LogWarning("8");
                    BlockJukebox.TileEntityJukebox jukebox=(BlockJukebox.TileEntityJukebox)tileEntity;
                    //IdlFramework.LogWarning("send su %s",jukebox.getRecord().getUnlocalizedName());
                    //IdlFramework.LogWarning("try get %s",jukebox.getRecord().getUnlocalizedName());
                    if (!jukebox.getRecord().isEmpty() && jukebox.getRecord().getItem()==this){
                        //IdlFramework.LogWarning("9");
                        if (jukebox.getRecord().getItem()==this){
                            EntityItem item=new EntityItem(world);
                            item.setItem(jukebox.getRecord().copy());
                            item.serverPosX=event.getPos().getX();
                            item.serverPosY=event.getPos().getY();
                            item.serverPosZ=event.getPos().getZ();

                            item.setPosition(event.getPos().getX(),event.getPos().getZ(),event.getPos().getZ());
                            item.motionY=0.002f;
                            world.spawnEntity(item);

                            //IdlFramework.LogWarning("10");
                            //ModCommands.give(event.getEntityPlayer(),jukebox.getRecord().copy());
                            jukebox.setRecord(ItemStack.EMPTY);


                            //world.setBlockState(event.getPos(), world.getBlockState(event.getPos()).withProperty(BlockJukebox.HAS_RECORD, Boolean.valueOf(false)), 3);
                            event.setCanceled(true);//取消防止原版触发取出
                            return;
                        }
                    }
                }
            }
        }
    }
    public static ItemStack getItem(World world,BlockPos pos){
        if (world.getTileEntity(pos) instanceof  BlockJukebox.TileEntityJukebox){
            BlockJukebox.TileEntityJukebox tile=(BlockJukebox.TileEntityJukebox)world.getTileEntity(pos);
            //IdlFramework.LogWarning("getItem su %s in line %d",tile.getRecord().getUnlocalizedName(),line());
            return tile.getRecord();
        }
        return null;
    }
//    public static ItemStack getItem(TileEntity entity){
//        if (entity instanceof  BlockJukebox.TileEntityJukebox){
//            BlockJukebox.TileEntityJukebox tile=(BlockJukebox.TileEntityJukebox)entity;
//            //IdlFramework.LogWarning("getItem su %s in line %d",tile.getRecord().getUnlocalizedName(),line());
//            return tile.getRecord();
//        }
//        return null;
//    }
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void stopPlay(net.minecraftforge.client.event.sound.PlaySoundEvent event){
        if (event.getSound() instanceof PositionedSoundRecord){
            PositionedSoundRecord record=(PositionedSoundRecord)event.getSound();
            BlockPos pos=new BlockPos(record.getXPosF(),record.getYPosF(),record.getZPosF());
            World world= Minecraft.getMinecraft().world;
            if (world!=null){
                IBlockState state=world.getBlockState(pos);
                if(state.getBlock()== Blocks.JUKEBOX && getItem(world,pos)!=null && getItem(world,pos).getItem()==this){
                    //IdlFramework.LogWarning("record");
                    if (getItem(world,pos).isEmpty()){
                        event.getManager().stopSound(event.getSound());
                    }
                }
            }
        }
    }
    public static SoundEvent getSound(ItemStack stack){
        if (stack.getItem() instanceof ItemYTXSY ){
            if (stack.hasTagCompound()){
                int soundId=IDLNBTUtil.GetInt(stack,NBT_SOUND_ID,-1);
                if (soundId!=-1){
                    return YTXSYSounds.get(soundId);
                }
            }
        }
        return null;
    }
    public void getItemYTXSY(ItemStack stack,int index){
        if (index!=-1) IDLNBTUtil.SetInt(stack,NBT_SOUND_ID,index);
    }
}
