package com.Hileb.moremomostories.common.world.item.myItems;

import com.Hileb.moremomostories.MoreMoMoSrories;
import com.Hileb.moremomostories.common.world.gui.ModGuiElementLoader;
import com.Hileb.moremomostories.common.world.item.ItemBase;
import com.Hileb.moremomostories.mods.momo.MoMoCards;
import com.google.common.collect.Multimap;
import com.gq2529.momostories.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ItemCardContainer extends ItemBase {

    public static final String NBTTAG="hileb_container";
    public ItemCardContainer(String name){
        super(name);
        MoMoCards.registerCard(this, MoMoCards.CardType.HILEB);
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
        if (!stack.hasTagCompound()){
            stack.setTagCompound(new NBTTagCompound());
        }
        if (!stack.getTagCompound().hasKey(NBTTAG)){
            ItemStackHandler stackHandler=new ItemStackHandler(27);
            stack.getTagCompound().setTag(NBTTAG,stackHandler.serializeNBT());
        }

        return super.initCapabilities(stack, nbt);
    }

    @Override
    public void registerModels() {
        MoreMoMoSrories.proxy.registerItemRenderer(this, ModItems.THE_BOOK_OF_MANIFESTATION, 0, "inventory");
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand) {
        if (!world.isRemote){
            int handCount;
            if (hand==EnumHand.MAIN_HAND)handCount=0;
            else handCount=1;
            player.openGui(MoreMoMoSrories.instance, ModGuiElementLoader.GUI_CARD_CONTAINER,world,handCount,0,0);

            return ActionResult.newResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));
        }
        return ActionResult.newResult(EnumActionResult.PASS, player.getHeldItem(hand));
    }
    public static ItemStackHandler getItemStackHandler(ItemStack stack){
        ItemStackHandler items=new ItemStackHandler(27);
        items.deserializeNBT(stack.getTagCompound().getCompoundTag(ItemCardContainer.NBTTAG));
        return items;
    }
    public static void setItemStackHandler(ItemStack stack,ItemStackHandler stackHandler){
        stack.getTagCompound().setTag(ItemCardContainer.NBTTAG,stackHandler.serializeNBT());
    }
//    private void useItems(ItemStack stack, String funcName, Object... args){
//        Method method=null;
//
//        for (Method method1:this.getClass().getMethods()) {
//            if (method1.getName().equals(funcName)){
//                method = method1;
//                break;
//            }
//        }
//        ItemStackHandler itemStackHandler=getItemStackHandler(stack);
//        for(int i=0;i<itemStackHandler.getSlots();i++){
//            ItemStack stack1=itemStackHandler.getStackInSlot(i);
//            if (!stack1.isEmpty()){
//                try{
//                    if (method != null) {
//                        method.invoke(stack1.getItem(),args);
//                    }
//                } catch (InvocationTargetException | IllegalAccessException e) {
//                    MoreMoMoSrories.logger.error(e);
//                }
//            }
//        }
//    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
        ItemStackHandler itemStackHandler=getItemStackHandler(stack);
        for(int i=0;i<itemStackHandler.getSlots();i++){
            ItemStack stack1=itemStackHandler.getStackInSlot(i);
            if (!stack1.isEmpty()){
                stack1.getItem().onUpdate(stack1, worldIn, entityIn, itemSlot, isSelected);
            }
        }
        setItemStackHandler(stack,itemStackHandler);
    }
    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        EnumActionResult result=EnumActionResult.PASS;
        ItemStack stack=player.getHeldItem(hand);
        ItemStackHandler itemStackHandler=getItemStackHandler(stack);
        for(int i=0;i<itemStackHandler.getSlots();i++){
            ItemStack stack1=itemStackHandler.getStackInSlot(i);
            if (!stack1.isEmpty()){
                EnumActionResult result1=stack1.getItem().onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
                if (result1==EnumActionResult.SUCCESS)result=result1;
            }
        }
        setItemStackHandler(stack,itemStackHandler);
        return result;
    }


    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        ItemStackHandler itemStackHandler=getItemStackHandler(stack);
        for(int i=0;i<itemStackHandler.getSlots();i++){
            ItemStack stack1=itemStackHandler.getStackInSlot(i);
            if (!stack1.isEmpty()){
                stack1.getItem().onItemUseFinish(stack, worldIn, entityLiving);
            }
        }
        setItemStackHandler(stack,itemStackHandler);
        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }

    @Override
    public void onUsingTick(ItemStack stack, EntityLivingBase living, int count) {
        ItemStackHandler itemStackHandler=getItemStackHandler(stack);
        for(int i=0;i<itemStackHandler.getSlots();i++){
            ItemStack stack1=itemStackHandler.getStackInSlot(i);
            if (!stack1.isEmpty()){
                stack1.getItem().onUsingTick(stack, living, count);
            }
        }
        super.onUsingTick(stack, living, count);
        setItemStackHandler(stack,itemStackHandler);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        boolean result=false;
        ItemStackHandler itemStackHandler=getItemStackHandler(stack);
        for(int i=0;i<itemStackHandler.getSlots();i++){
            ItemStack stack1=itemStackHandler.getStackInSlot(i);
            if (!stack1.isEmpty()){
                if (stack1.getItem().onLeftClickEntity(stack, player, entity))result=true;
            }
        }
        setItemStackHandler(stack,itemStackHandler);
        return result;
    }

    @Override
    public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
        EnumActionResult result=EnumActionResult.PASS;
        ItemStack stack=player.getHeldItem(hand);
        ItemStackHandler itemStackHandler=getItemStackHandler(stack);
        for(int i=0;i<itemStackHandler.getSlots();i++){
            ItemStack stack1=itemStackHandler.getStackInSlot(i);
            if (!stack1.isEmpty()){
                EnumActionResult result1=stack1.getItem().onItemUseFirst(player, world, pos, side, hitX, hitY, hitZ, hand);
                if (result1==EnumActionResult.SUCCESS)result=result1;
            }
        }
        setItemStackHandler(stack,itemStackHandler);
        return result;
    }


    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
        Multimap<String, AttributeModifier> map= super.getAttributeModifiers(slot, stack);
        ItemStackHandler itemStackHandler=getItemStackHandler(stack);
        for(int i=0;i<itemStackHandler.getSlots();i++){
            ItemStack stack1=itemStackHandler.getStackInSlot(i);
            if (!stack1.isEmpty()){
                Multimap<String, AttributeModifier> map1=stack1.getItem().getAttributeModifiers(slot,stack1);
                for (String key:map1.keys()){
                    for(AttributeModifier attributeModifier:map1.get(key)){
                        map.put(key,attributeModifier);
                    }
                }
            }
        }
        setItemStackHandler(stack,itemStackHandler);
        return map;
    }
}
