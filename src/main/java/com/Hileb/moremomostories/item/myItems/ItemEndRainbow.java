package com.Hileb.moremomostories.item.myItems;

import com.Hileb.moremomostories.IdlFramework;
import com.Hileb.moremomostories.item.IEntityItemX;
import com.Hileb.moremomostories.item.ItemSwordBase;
import com.Hileb.moremomostories.item.ModItems;
import com.Hileb.moremomostories.meta.MetaUtil;
import com.Hileb.moremomostories.otherMods.SlashBlade.SA.Entity.EntityFire;
import com.Hileb.moremomostories.otherMods.SlashBlade.SA.Entity.EntityRain;
import com.Hileb.moremomostories.potion.ModPotions;
import com.Hileb.moremomostories.potion.myBuff.PotionDayTime;
import com.Hileb.moremomostories.util.EntityUtil;
import com.Hileb.moremomostories.util.NBTStrDef.IDLNBTUtil;
import com.Hileb.moremomostories.util.named.NameTagHandler;
import ic2.api.item.ElectricItem;
import ic2.core.IC2;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

public class ItemEndRainbow extends ItemSwordBase implements IEntityItemX {
    public static final String NBT_ENERGY="com.hileb.momo.nbt.energy";
    public static final ToolMaterial toolMaterial= EnumHelper.addToolMaterial("rainbow",5,(int)Float.POSITIVE_INFINITY,10.0F,20.0F,30);
    public ItemEndRainbow(String name){
        super(name,toolMaterial);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        if (toRepair==null || repair==null){
            return false;
        }
        if (toRepair.isEmpty() || repair.isEmpty()){
            return false;
        }
        if (toRepair.getItemDamage()==0)return false;
        if (repair.getItem() instanceof ItemXe){
            if (ItemXe.getLevel(repair)==0)return true;
        }
        if (repair.getItem().getRegistryName().equals(new ResourceLocation("ic2:crafting")) && repair.getMetadata()==4)return true;
        if (repair.getItem().getRegistryName().equals(new ResourceLocation("itzmx:item_rainbow")))return true;
        if (repair.getItem().getRegistryName().equals(new ResourceLocation("itzmx:item_rainbowj")))return true;
        if (repair.getItem().getRegistryName().equals(new ResourceLocation("flammpfeil.slashblade:slashbladenamed")))return true;

        return false;
    }

    public boolean applySA(ItemStack stack, EntityPlayer player) {
        //return super.onLeftClickEntity(stack, player, entity);
        if (!player.getCooldownTracker().hasCooldown(stack.getItem())){
            if (getEnergy(stack)>=500d || player.isCreative()){
                if (!player.isCreative())addEnergy(stack,-500d);
                int amount=new Random((player.toString()+player.world.toString()+stack.toString()+player.getName()+player.getUniqueID().toString()+player.world.getTotalWorldTime()).hashCode()).nextInt(2);
                switch (amount){
                    case 0:{
                        SaRain.doSpacialAttack(stack,player);
                        return true;
                    }
                    case 1:{
                        SaFire.doSpacialAttack(stack,player);
                        return true;
                    }
                    default:{
                        addEnergy(stack,+500d);
                        return false;
                    }
                }
            }
        }
        return false;
    }

    public static void addEnergy(ItemStack stack,double amont){
        IDLNBTUtil.SetDouble(stack,NBT_ENERGY,IDLNBTUtil.GetDouble(stack,NBT_ENERGY,0)+amont);
    }
    public static double getEnergy(ItemStack stack){
        return IDLNBTUtil.GetDouble(stack,NBT_ENERGY,0);
    }
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        final ActionResult<ItemStack> FALSE=new ActionResult<ItemStack>(EnumActionResult.FAIL,playerIn.getHeldItem(handIn));
        final ActionResult<ItemStack> TRUE=new ActionResult<ItemStack>(EnumActionResult.SUCCESS,playerIn.getHeldItem(handIn));
        final ActionResult<ItemStack> PASS=new ActionResult<ItemStack>(EnumActionResult.PASS,playerIn.getHeldItem(handIn));
        if (!worldIn.isRemote && playerIn!=null && handIn==EnumHand.MAIN_HAND){
            return applySA(playerIn.getHeldItem(handIn),playerIn)?TRUE:FALSE;
        }
        return PASS;
    }

    @SubscribeEvent
    public void onLeftClick(PlayerInteractEvent.LeftClickEmpty event){
        if (!event.getEntityPlayer().world.isRemote && event.getEntityPlayer()!=null){
            ItemStack stack=event.getEntityPlayer().getHeldItem(event.getHand());
            if (stack.getItem()==this){
                SaBakin.doSpacialAttack(stack,event.getEntityPlayer());
            }
        }
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
    }



    //复刻SA
    private static class SaBakin{
        public static void doSpacialAttack(ItemStack var1, EntityPlayer var2){
            if (!var2.world.isRemote){
                for(PotionEffect effect:var2.getActivePotionEffects()){
                    if (effect.getPotion().isBadEffect()){var2.removeActivePotionEffect(effect.getPotion());}
                }

                var2.addPotionEffect(PotionDayTime.getEffectShort());
                removeEffect(var2, ModPotions.BAKIN);
                NameTagHandler.randomApply(var1);
            }
        }
        private static void removeEffect(EntityLivingBase livingBase, Potion potion){
            if (hasPotion(livingBase,potion))livingBase.removePotionEffect(potion);
        }
        private static boolean hasPotion(EntityLivingBase living, Potion potion){
            for (PotionEffect effect: living.getActivePotionEffects()){
                if (effect.getPotion()== potion){
                    return true;
                }
            }
            return false;
        }
    }
    private static class SaFire{
        public static void doSpacialAttack(ItemStack itemStack, EntityPlayer entityPlayer) {
            //TO DO
            //entityPlayer.sendMessage(new TextComponentString("this is sa speaking"));
            Random random=new Random(entityPlayer.world.getSeed()+entityPlayer.getUniqueID().hashCode()+(int)entityPlayer.posX);
            if (!entityPlayer.world.isRemote){
                //List<EntityRain> rains=new ArrayList<>();
                for(int y=0;y<=5;y++){//生成雨滴
                    for(int i=0;i<=9;i++){
                        for(int j=0;j<=9;j++){
                            EntityFire entity=new EntityFire(entityPlayer.world,itemStack,entityPlayer);
                            entity.setPosition(entityPlayer.posX-4+i+((double) (random.nextInt(10)/10)-0.5f),entityPlayer.posY+y+((double) (random.nextInt(10)/10)-0.5f),entityPlayer.posZ-4+j+((double) (random.nextInt(10)/10)-0.5f));
                            entityPlayer.world.spawnEntity(entity);
                            //rains.add(entity);
                        }
                    }
                }
                EntityUtil.ApplyBuff(entityPlayer, MobEffects.FIRE_RESISTANCE,5,20);
                EntityUtil.ApplyBuff(entityPlayer,MobEffects.HEALTH_BOOST,2,20);
                entityPlayer.world.createExplosion(entityPlayer, entityPlayer.posX, entityPlayer.posY,entityPlayer.posZ, 16.0F, false);
            }
        }
    }
    private static class SaRain{
        public static void doSpacialAttack(ItemStack itemStack, EntityPlayer entityPlayer) {
            //TO DO
            //entityPlayer.sendMessage(new TextComponentString("this is sa speaking"));
            Random random=new Random(entityPlayer.world.getSeed()+entityPlayer.getUniqueID().hashCode()+(int)entityPlayer.posX);
            if (!entityPlayer.world.isRemote){
                //List<EntityRain> rains=new ArrayList<>();
                for(int y=0;y<=5;y++){//生成雨滴
                    for(int i=0;i<=9;i++){
                        for(int j=0;j<=9;j++){
                            EntityRain entity=new EntityRain(entityPlayer.world,itemStack,entityPlayer);
                            entity.setPosition(entityPlayer.posX-4+i+((double) (random.nextInt(10)/10)-0.5f),entityPlayer.posY+y+((double) (random.nextInt(10)/10)-0.5f),entityPlayer.posZ-4+j+((double) (random.nextInt(10)/10)-0.5f));
                            entityPlayer.world.spawnEntity(entity);
                            //rains.add(entity);
                        }
                    }
                }
            }

        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
        super.addInformation(stack, world, tooltip, flag);
        tooltip.add(I18n.format("momo.energy.rainbow.damage.name")+String.format("%f",Float.POSITIVE_INFINITY));
        tooltip.add(((getEnergy(stack)>=500)?"§e":"")+((getEnergy(stack)>=10000)?"§f§r":"")+I18n.format("momo.energy.rainbow.en.name")+String.format(": %f",getEnergy(stack))+I18n.format("momo.energy.rainbow.en.end"));

        tooltip.add("    ");
        tooltip.add(I18n.format("item.item_rainbowj.name"));
        tooltip.add("    ");

        tooltip.add(I18n.format("skill.left.desc"));
        tooltip.add("§e"+I18n.format("item.moremomostories.blandTianki.name"));
        tooltip.add(I18n.format("flammpfeil.slashblade.specialattack.sa_bakin"));
        tooltip.add(I18n.format("skill.bakin.desc"));

        tooltip.add("    ");
        tooltip.add(I18n.format("skill.right.desc"));
        tooltip.add("§e"+I18n.format("com.hileb.momo.lang.skill.rain"));
        tooltip.add(I18n.format("skill.rain.desc"));
        tooltip.add("§e"+I18n.format("flammpfeil.slashblade.specialattack.sa_fire"));
        tooltip.add(I18n.format("skill.fire.desc"));

        tooltip.add("    ");
        tooltip.add(I18n.format("skill.pass.desc"));
        tooltip.add("    ");
        if (Loader.isModLoaded(IC2.MODID)){
            tooltip.add("§e"+I18n.format("skill.eu.desc"));
        }
        tooltip.add("§e"+I18n.format("com.hileb.momo.lang.skill.endmemory.name"));
        tooltip.add(I18n.format("com.hileb.momo.lang.skill.endmemory.desc"));

    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        return ModItems.ITEM_SWOOD_MEMORY_END.onLeftClickEntity(stack,player,entity);
    }
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onFinalAttack(LivingHurtEvent event){
        World world=event.getEntity().world;
        if (!world.isRemote){
            if (event.getEntityLiving()!=null && event.getSource().getTrueSource() instanceof EntityPlayer){
                EntityPlayer player=(EntityPlayer)event.getSource().getTrueSource();
                if (player.getHeldItemMainhand().getItem()==this){
                    addEnergy(player.getHeldItemMainhand(),(double) event.getAmount());
                    if (Loader.isModLoaded("ic2")){
                        ic2charge(event.getEntityLiving().getHeldItemMainhand(),world,event.getEntityLiving(),event.getAmount());
                    }
                }
            }
        }
    }
    public static ItemStack process(Random random){
        ItemStack stack=new ItemStack(ModItems.ITEM_SWOOD_SAKURA_END);
        NameTagHandler.randomApply(stack);
        return stack;
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        if (oldStack.getItem()==newStack.getItem())return false;
        return true;
    }

    @Override
    public boolean isDamaged(ItemStack stack) {
        return false;
    }

    @Override
    public boolean isDamageable() {
        return false;
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        super.onUpdate(stack,worldIn,entityIn,itemSlot,isSelected);
        stack.setItemDamage(0);
    }



    @Optional.Method(modid = IC2.MODID)
    private void ic2charge(ItemStack stack, World world, Entity entity, float amount) {//试图充电
        if (!entity.world.isRemote && entity instanceof EntityPlayer) {
            ElectricItem.manager.charge(stack, ElectricItem.manager.getMaxCharge(stack) - ElectricItem.manager.getCharge(stack),(int)amount, true, false);//进行充电
        }
    }
}
