package com.Hileb.moremomostories.item.myItems;

import com.Hileb.moremomostories.IdlFramework;
import com.Hileb.moremomostories.init.ModCreativeTab;
import com.Hileb.moremomostories.item.ItemBase;
import com.Hileb.moremomostories.item.ItemInformationAdder;
import com.Hileb.moremomostories.util.CommonFunctions;
import com.Hileb.moremomostories.util.MoMo.MoMoCards;
import com.Hileb.moremomostories.util.ModSoundHandler;
import com.Hileb.moremomostories.util.NBTStrDef.IDLNBTUtil;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import java.util.List;

public class ItemCopier extends ItemBase {

    private ItemInformationAdder ItemDesc=new ItemInformationAdder("desc.item.copyer.desc","desc.item.copyer.desc2");
    private String key="desc.copyitem.desc";
    public ItemCopier(String name){
        super(name, ModCreativeTab.IDL_MISC,1);
        CommonFunctions.addToEventBus(this);
        MoMoCards.registerCard(this);
    }
    @SubscribeEvent
    public void onRightClick(PlayerInteractEvent.RightClickItem event){
        World world=event.getWorld();
        if(!event.getWorld().isRemote){
            if(event.getEntityPlayer()!=null){
                EntityPlayer player=event.getEntityPlayer();
                if(player.getHeldItemMainhand().getItem()==this){
                    if(!player.getHeldItem(EnumHand.OFF_HAND).isEmpty()){
                        ItemStack stack=player.getHeldItem(EnumHand.OFF_HAND);
                        if(!stack.isEmpty()){
                            ItemStack stack1=stack.copy();
                            player.setHeldItem(EnumHand.MAIN_HAND,stack1);
                            stack1.setTagInfo("CopyTime",new NBTTagInt(10*2));//10*20ticks(并不准确)
                            stack1.setTagInfo("Copypp",new NBTTagInt(1));
                            IdlFramework.Log("somebody copy a item %s by %s,time=%d",stack1.getUnlocalizedName(),new ItemStack(this).getUnlocalizedName(),10*2);
                            player.sendMessage( new TextComponentString(new TextComponentTranslation("tip.moremomostories.nothingonoffhand.tip").getFormattedText()));
                        }
                    }
                    else player.sendMessage( new TextComponentString(new TextComponentTranslation("tip.moremomostories.copysuccessful.tip").getFormattedText()));
                }
            }
        }
    }
    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        onUpdates(worldIn,entityIn);
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
    }

    public void onUpdates(World worldIn, Entity entityIn)
    {
        if(!worldIn.isRemote){
            if(entityIn instanceof  EntityPlayer){
                if(worldIn.getWorldTime()%20==0){
                    findAllItems(worldIn,(EntityPlayer) entityIn,1);
                }
                if(worldIn.getWorldTime()%20==1)findAllItems(worldIn,(EntityPlayer)entityIn,0);
            }
        }
    }
    private void findAllItems(World worldIn, EntityPlayer player,int booleancount) {
        if(!worldIn.isRemote){
            final IItemHandler inv = player.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
            assert inv != null;
            for (int i = 0; i < inv.getSlots(); i++) {
                final ItemStack invStack = inv.getStackInSlot(i);
                if (invStack.isEmpty() || !invStack.hasTagCompound()) {
                    continue;
                }
                else {
                    if(invStack.getTagCompound().hasKey("CopyTime") && invStack.getTagCompound().getInteger("CopyTime")>=0){
                        if(booleancount==1){
                            int a=invStack.getTagCompound().getInteger("CopyTime");
                            IdlFramework.Log(String.format("%d",a));
                            if(a==0){
                                IdlFramework.Log("the copy item %s is null!",invStack.getUnlocalizedName());
                                invStack.setCount(0);
                            }
                            else invStack.setTagInfo("CopyTime",new NBTTagInt(a-1));
                        }
                        else {
                            if(booleancount==0)invStack.setTagInfo("copypp",new NBTTagInt(1));
                        }
                    }
                }
            }
        }
    }
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void event_1_a(ItemTooltipEvent event){
        if(event.getEntityPlayer()!=null){
            if(event.getItemStack().hasTagCompound()){
                if(!event.getItemStack().getTagCompound().hasNoTags()){
                    if(IDLNBTUtil.StackHasKey(event.getItemStack(),"CopyTime")){
                        if(IDLNBTUtil.GetInt(event.getItemStack(),"CopyTime")>0){
                            if(event.getItemStack().getTagCompound().getInteger("CopyTime")>0){
                                List<String> string_1_a=event.getToolTip();
                                CopyItemaddInformation(event.getItemStack(),event.getEntityPlayer().world,string_1_a,event.getFlags(),event.getItemStack().getTagCompound().getInteger("CopyTime"));
                            }
                        }
                    }
                }
            }
        }
    }
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> str, ITooltipFlag flagIn){
        super.addInformation(stack,worldIn,str,flagIn);
        ItemDesc.func_addInformation_item_base(stack,worldIn,str,flagIn);
    }
    @SideOnly(Side.CLIENT)
    public void CopyItemaddInformation(ItemStack stack, World worldIn, List<String> str, ITooltipFlag flagIn,int a){
        for (String strline:I18n.format(key,a).split("\n")){
            str.add(strline);
        }
    }
    @SubscribeEvent
    public void playSoundEvent(LivingHurtEvent event_2){
        World world=event_2.getEntity().world;
        if (!world.isRemote){
            if (event_2.getSource().getTrueSource()!=null && event_2.getSource().getTrueSource() instanceof EntityPlayer){
                EntityPlayer player=(EntityPlayer) event_2.getSource().getTrueSource();
                if (player.getHeldItemMainhand().getItem() instanceof ItemCopier){
                    player.playSound(ModSoundHandler.SOUND_HURT_114514,2.0f,2.0f);
                    player.playSound(SoundEvents.ENTITY_COW_MILK, 1.0F, 1.0F);
                }
            }
        }
    }
}
