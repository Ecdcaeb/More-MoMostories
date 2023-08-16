package com.Hileb.moremomostories.mixin.momostories.event;

import com.Hileb.moremomostories.client.RandomManager;
import com.Hileb.moremomostories.common.common.ModEventReader;
import com.Hileb.moremomostories.common.util.math.VirtueSpace;
import com.Hileb.moremomostories.common.world.command.ModCommands;
import com.Hileb.moremomostories.common.world.item.myItems.ItemCardContainer;
import com.Hileb.moremomostories.common.world.potion.myBuff.PotionDayTime;
import com.Hileb.moremomostories.mods.momo.MoMoCards;
import com.gq2529.momostories.blocks.ModBlocks;
import com.gq2529.momostories.entity.ModEntity.Evil;
import com.gq2529.momostories.entity.ModEntity.Kindness;
import com.gq2529.momostories.entity.ModEntity.Reinforcements;
import com.gq2529.momostories.events.DamageSource1;
import com.gq2529.momostories.item.ModItemStoryboards.ModODF.IJumpBoost;
import com.gq2529.momostories.item.ModItems;
import com.gq2529.momostories.potion.effect.ModPotions;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.*;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketTitle;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AnvilRepairEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import static net.minecraft.item.ItemBow.getArrowVelocity;

public class CardFunction {
    public static class LunaBlessing {
        public static void doTwilightCloakCheck(LivingEvent event) {
            /** means that clear target when EntityPlayer has it and target is player,null it!**/

            if (event.getEntity() instanceof EntityLiving) {
                EntityLiving entityLiving = ((EntityLiving) event.getEntity());
                if (entityLiving.getAttackTarget() == null)
                    return;
                if (!(entityLiving.getAttackTarget() instanceof EntityPlayer))
                    return;
                EntityPlayer player = (EntityPlayer) entityLiving.getAttackTarget();
                for (int i = 0; i < player.inventory.getSizeInventory(); ++i) {
                    ItemStack itemStack = player.inventory.getStackInSlot(i);
                    if (itemStack.getItem() == ModItems.LUNA_BLESSING) {
                        World world = event.getEntityLiving().world;
                        if (!world.isDaytime()) {
                            if (event.getEntity() instanceof EntityLiving) {
                                //on this i post!
                                if (MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(player, itemStack, entityLiving).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.EVENT)))
                                    return;
                                //also
                                //end post
                                ((EntityLiving) event.getEntity()).setAttackTarget(null);
                            }
                        }
                    }
                    if (itemStack.getItem()== com.Hileb.moremomostories.common.world.item.ModItems.ITEM_CARD_CONTAINER){
                        ItemStackHandler stackHandler= ItemCardContainer.getItemStackHandler(itemStack);
                        for(int i_1=0;i_1<stackHandler.getSlots();i_1++){
                            ItemStack stack1=stackHandler.getStackInSlot(i_1);
                            if (stack1.getItem() == ModItems.LUNA_BLESSING) {
                                World world = event.getEntityLiving().world;
                                if (!world.isDaytime()) {
                                    if (event.getEntity() instanceof EntityLiving) {
                                        //on this i post!
                                        if (MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(player, stack1, entityLiving).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.EVENT)))
                                            return;
                                        //also
                                        //end post
                                        ((EntityLiving) event.getEntity()).setAttackTarget(null);
                                    }
                                }
                            }
                        }
                        ItemCardContainer.setItemStackHandler(itemStack,stackHandler);
                    }
                }
            }
        }
    }

    public static class AncientMirrors {

        public static ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand) {
            if (!world.isRemote) {
                if (player.getHeldItem(hand).getItem() == ModItems.ANCIENT_MIRRORS && !MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(player, player.getHeldItem(hand), null).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.RIGHT_CLICK))) {
                    ItemStack a = player.getHeldItemOffhand();
                    ItemStack item = player.getHeldItem(hand);
                    ItemStack B = a.copy();
                    world.spawnEntity(new EntityItem(world, player.posX, player.posY, player.posZ, B));
                    item.setCount(item.getCount() - 1);
                    return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
                }
            }
            return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
        }
    }

    public static class OrdinaryHammer{
        public static boolean onBlockDestroyed(ItemStack stack, World world, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
            if (entityLiving instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) entityLiving;
                if (MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(player,stack,null)))return false;
                RayTraceResult result = rayTrace(world, player, false);
                if (result.sideHit == null) return false;
                EnumFacing sideHit = result.sideHit;

                int xDist, yDist, zDist;
                int mineRadius = 1;
                yDist = xDist = zDist = mineRadius;

                int mineDepth = 0;
                switch (sideHit) {
                    case UP:
                    case DOWN: yDist = mineDepth; break;
                    case NORTH:
                    case SOUTH: zDist = mineDepth; break;
                    case EAST:
                    case WEST: xDist = mineDepth; break;
                }

                for (int x = pos.getX() - xDist; x <= pos.getX() + xDist; x++) {
                    for (int y = pos.getY() - yDist; y <= pos.getY() + yDist; y++) {
                        for (int z = pos.getZ() - zDist; z <= pos.getZ() + zDist; z++) {
                            BlockPos targetPos = new BlockPos(x, y, z);
                            IBlockState targetBlock = world.getBlockState(targetPos);
                            if (stack.getItem().canHarvestBlock(targetBlock, player.getHeldItem(EnumHand.MAIN_HAND))) {
                                if ((stack.getMaxDamage() - stack.getItemDamage()) >= 1 && targetBlock.getBlock() != Blocks.BEDROCK) {
                                    if (targetBlock.getBlock().getExpDrop(targetBlock, world, targetPos, 0) > 0) {
                                        if (!world.isRemote && world.getGameRules().getBoolean("doTileDrops")) {
                                            world.spawnEntity(new EntityXPOrb(world, (double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, world.getBlockState(pos).getBlock().getExpDrop(targetBlock, world, targetPos, 0)));
                                        }
                                    }
                                    world.destroyBlock(new BlockPos(x, y, z), true);
                                }
                                stack.damageItem(1, player);
                            }
                        }
                    }
                }
                stack.damageItem(1, player);
            }
            return false;
        }
        private  static RayTraceResult rayTrace(World worldIn, EntityPlayer playerIn, boolean useLiquids)
        {
            float f = playerIn.rotationPitch;
            float f1 = playerIn.rotationYaw;
            double d0 = playerIn.posX;
            double d1 = playerIn.posY + (double)playerIn.getEyeHeight();
            double d2 = playerIn.posZ;
            Vec3d vec3d = new Vec3d(d0, d1, d2);
            float f2 = MathHelper.cos(-f1 * 0.017453292F - (float)Math.PI);
            float f3 = MathHelper.sin(-f1 * 0.017453292F - (float)Math.PI);
            float f4 = -MathHelper.cos(-f * 0.017453292F);
            float f5 = MathHelper.sin(-f * 0.017453292F);
            float f6 = f3 * f4;
            float f7 = f2 * f4;
            double d3 = playerIn.getEntityAttribute(EntityPlayer.REACH_DISTANCE).getAttributeValue();
            Vec3d vec3d1 = vec3d.addVector((double)f6 * d3, (double)f5 * d3, (double)f7 * d3);
            return worldIn.rayTraceBlocks(vec3d, vec3d1, useLiquids, !useLiquids, false);
        }
    }

    public static class TheFruitofTheFlowingYears{
        public static ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand handIn)
        {
            if (!world.isRemote)
            {
                if (player.getHeldItem(handIn).getItem() == ModItems.THE_FRUIT_OF_THE_FLOWING_YEARS)
                {
                    if (MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(player,player.getHeldItem(handIn),null).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.RIGHT_CLICK)))return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(handIn));
                    int sum = 0;
                    for (int i = 0; i < player.inventory.getSizeInventory(); ++i)
                    {
                        ItemStack itemStack = player.inventory.getStackInSlot(i);
                        if (itemStack.getItem() == Items.APPLE) {
                            int num = itemStack.getCount();
                            if(sum < 16 && num <= 16 - sum)
                            {
                                itemStack.shrink(num);
                                world.spawnEntity(new EntityItem(world, player.posX, player.posY, player.posZ, new ItemStack(Items.GOLDEN_APPLE, num)));
                                sum += num;
                            }
                            else{
                                itemStack.shrink(16 - sum);
                                world.spawnEntity(new EntityItem(world, player.posX, player.posY, player.posZ, new ItemStack(Items.GOLDEN_APPLE, 16 - sum)));
                                return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(handIn));
                            }
                        }
                        if (itemStack.getItem() == Items.DRAGON_BREATH)
                        {
                            int num = itemStack.getCount();
                            if(sum < 16 && num <= 16 - sum)
                            {
                                itemStack.shrink(num);
                                world.spawnEntity(new EntityItem(world, player.posX, player.posY, player.posZ, new ItemStack(Items.EXPERIENCE_BOTTLE, num)));
                                sum += num;
                            }
                            else{
                                itemStack.shrink(16 - sum);
                                world.spawnEntity(new EntityItem(world, player.posX, player.posY, player.posZ, new ItemStack(Items.EXPERIENCE_BOTTLE, 16 - sum)));
                                return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(handIn));
                            }
                        }
                    }
                }
            }
            return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(handIn));
        }
        public static void onRightUse(PlayerInteractEvent.RightClickBlock event) {
            if(MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(event.getEntityPlayer(),event.getItemStack(),null).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.EVENT)))return;
            World world = event.getWorld();
            BlockPos pos = event.getPos();
            IBlockState state = world.getBlockState(pos);
            Block block = state.getBlock();
            if (!world.isRemote) {
                ItemStack itemstack = event.getEntityPlayer().getHeldItem(event.getHand());
                EntityPlayer player = event.getEntityPlayer();
                if (itemstack.getItem() == ModItems.THE_FRUIT_OF_THE_FLOWING_YEARS) {
                    if (block instanceof BlockCrops && ((BlockCrops) block).canGrow(world, pos, state, false)) {
                        for (int x0 = -1; x0 <= 1; x0++) {
                            for (int z0 = -1; z0 <= 1; z0++) {
                                int x = pos.getX() + x0;
                                int y = pos.getY();
                                int z = pos.getZ() + z0;
                                TheFruitofTheFlowingYears.getBlock(x, y, z, world);
                            }
                        }
                    }
                    //Sapling
                    else if (itemstack.getItem() == ModItems.THE_FRUIT_OF_THE_FLOWING_YEARS) {
                        if (block instanceof BlockSapling) {
                            player.swingArm(event.getHand());
                            for (int x0 = -2; x0 <= 2; x0++) {
                                for (int z0 = -2; z0 <= 2; z0++) {
                                    int x = pos.getX() + x0;
                                    int y = pos.getY();
                                    int z = pos.getZ() + z0;
                                    getBlock(x, y, z, world);
                                }
                            }
                        }
                    }
                }
            }
        }
        //next version of MoMoStories:
        private static void getBlock(int x,int y,int z, World world){
            BlockPos CropPos = new BlockPos(x, y, z); IBlockState CropState = world.getBlockState(CropPos); Block CropBlock = CropState.getBlock();
            if (CropBlock instanceof BlockSapling){ GrowthSapling (CropState, world, CropPos); }
            if (CropBlock instanceof BlockCrops) { Growth(CropState, world, CropPos, 7); }
        }

        private static void Growth(IBlockState CropState, World world, BlockPos CropPos, int loops){
            for (int i = 0; i < loops; i++) {
                IGrowable GrowableCrop = (IGrowable) CropState.getBlock();
                CropState = world.getBlockState(CropPos);
                if (GrowableCrop.canGrow(world, CropPos, CropState, false))
                {
                    GrowableCrop.grow(world, world.rand, CropPos, CropState);
                    ((net.minecraft.world.WorldServer) world).spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, false, CropPos.getX() + 0.5D, CropPos.getY() + 1.0D, CropPos.getZ() + 0.5D, 2, 0.15D, 0.15D, 0.15D, 0.0D);
                }
            }
        }

        private static void GrowthSapling(IBlockState SaplingState, World world, BlockPos SaplingPos){
            for (int i = 0; i < 4; i++) {
                IGrowable GrowableSapling = (IGrowable) SaplingState.getBlock();
                SaplingState = world.getBlockState(SaplingPos);
                if (SaplingState.getBlock() instanceof BlockSapling)
                {
                    if (GrowableSapling.canGrow(world, SaplingPos, SaplingState, false))
                    {
                        GrowableSapling.grow(world, world.rand, SaplingPos, SaplingState);
                        ((net.minecraft.world.WorldServer) world).spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, false, SaplingPos.getX() + 0.5D, SaplingPos.getY() + 1, SaplingPos.getZ() + 0.5D, 2, 0.15D, 0.15D, 0.15D, 0.0D);
                    }
                }
            }
        }

    }

    public static class BlueCalidan{
        public static void luna_hunting(LivingHurtEvent event) {
            World world = event.getEntity().world;
            if (!world.isRemote) {
                EntityLivingBase attacker = (EntityLivingBase) event.getSource().getTrueSource();
                if (attacker instanceof EntityPlayer) {
                    EntityPlayer Player = (EntityPlayer) attacker;
                    if (Player.getHeldItemMainhand().getItem() == ModItems.BLUE_CALIDAN) {
                        if (MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(Player,Player.getHeldItemMainhand(),event.getEntityLiving()).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.EVENT)))return;
                        event.setAmount(event.getAmount() + 20);
                    }
                    if (Player.getHeldItemMainhand().getItem()== com.Hileb.moremomostories.common.world.item.ModItems.ITEM_CARD_CONTAINER){
                        ItemStackHandler stackHandler= ItemCardContainer.getItemStackHandler(Player.getHeldItemMainhand());
                        for(int i1=0;i1<stackHandler.getSlots();i1++){
                            ItemStack stack1=stackHandler.getStackInSlot(i1);
                            if (stack1.getItem() == ModItems.BLUE_CALIDAN) {
                                if (MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(Player,stack1,event.getEntityLiving()).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.EVENT)))return;
                                event.setAmount(event.getAmount() + 20);
                            }
                        }
                        ItemCardContainer.setItemStackHandler(Player.getHeldItemMainhand(),stackHandler);
                    }
                }
            }
        }
    }

    public static class PalaudtheHolySword{
        public static ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand) {
            if (!world.isRemote)
            {
                if (player.getHeldItemMainhand().getItem() == ModItems.PALAUD_THE_HOLY_SWORD)
                {
                    if (player.isSneaking()) {
                        if (player.getHeldItemOffhand().getItem() == Items.SHIELD) {
                            if (MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(player,player.getHeldItemMainhand(),null).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.RIGHT_CLICK)))return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
                            ItemStack item = player.getHeldItemOffhand();
                            item.setCount(item.getCount() - 1);
                            player.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, new ItemStack(ModItems.Proof_of_glory_false));
                            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
                        }
                    }
                }
            }
            return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
        }
        public static void palaud_the_holy(LivingHurtEvent event) {
            World world = event.getEntity().world;
            if (!world.isRemote) {
                EntityLivingBase hurt = event.getEntityLiving();
                EntityLivingBase attacker = (EntityLivingBase) event.getSource().getTrueSource();
                if (attacker instanceof EntityPlayer) {
                    EntityPlayer Player = (EntityPlayer) attacker;
                    if (Player.getHeldItemMainhand().getItem() == ModItems.PALAUD_THE_HOLY_SWORD)
                    {
                        event.getSource().setMagicDamage();
                        if (world.isDaytime())
                        {
                            if (MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(Player,Player.getHeldItemMainhand(),hurt).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.EVENT)))return;
                            event.setAmount(event.getAmount() * 1.5F);
                            hurt.setFire(30);
                        }
                    }
                    if (Player.getHeldItemMainhand().getItem()== com.Hileb.moremomostories.common.world.item.ModItems.ITEM_CARD_CONTAINER){
                        ItemStackHandler stackHandler= ItemCardContainer.getItemStackHandler(Player.getHeldItemMainhand());
                        for(int i1=0;i1<stackHandler.getSlots();i1++){
                            ItemStack stack1=stackHandler.getStackInSlot(i1);
                            if (stack1.getItem() == ModItems.PALAUD_THE_HOLY_SWORD)
                            {
                                event.getSource().setMagicDamage();
                                if (world.isDaytime())
                                {
                                    if (MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(Player,stack1,hurt).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.EVENT)))return;
                                    event.setAmount(event.getAmount() * 1.5F);
                                    hurt.setFire(30);
                                }
                            }
                        }
                        ItemCardContainer.setItemStackHandler(Player.getHeldItemMainhand(),stackHandler);
                    }
                }
            }
        }
    }

    public static class ProofofGlory{
        public static void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
            if (!worldIn.isRemote)
            {
                EntityPlayer player = (EntityPlayer) entityIn;
                if (player.getHeldItemOffhand().getItem() == ModItems.ProofofGlory)
                {
                    if (worldIn.getWorldTime() % 20 == 0)
                    {
                        if (MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(player,stack,null).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.UPDATE)))return;
                        player.heal(1f);
                    }
                }
            }
        }
    }

    public static class Daylight{
        public static ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand)
        {
            if (!world.isRemote)
            {
                if (player.getHeldItem(hand).getItem() == ModItems.DAYLIGHT)
                {
                    for (int i = 0; i < player.inventory.getSizeInventory(); ++i) {
                        ItemStack itemStack = player.inventory.getStackInSlot(i);
                        if (itemStack.getItem() == ModItems.Proof_of_glory_false && MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(player,itemStack,null).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.RIGHT_CLICK)))
                        {
                            itemStack.shrink(1);
                            player.entityDropItem(new ItemStack(ModItems.ProofofGlory, 1), 0);
                            player.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, new ItemStack(ModItems.ProofofGlory));
                            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
                        }
                    }

                }
            }
            return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
        }
    }

    public static class OneType{
        public static void One_type(LivingHurtEvent event) {
            World world = event.getEntity().world;
            if (!world.isRemote) {
                EntityLivingBase attack = event.getEntityLiving();
                if (attack instanceof EntityPlayer) {
                    EntityPlayer Player = (EntityPlayer) attack;
                    for (int i = 0; i < Player.inventory.getSizeInventory(); ++i) {
                        ItemStack itemStack = Player.inventory.getStackInSlot(i);
                        if (itemStack.getItem() instanceof com.gq2529.momostories.item.tools.Bleeding.OneType) {
                            if (MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(Player,itemStack,attack).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.EVENT)))return;
                            event.setCanceled(true);
                        }
                        if (itemStack.getItem()== com.Hileb.moremomostories.common.world.item.ModItems.ITEM_CARD_CONTAINER){
                            ItemStackHandler stackHandler= ItemCardContainer.getItemStackHandler(itemStack);
                            for(int i1=0;i1<stackHandler.getSlots();i1++){
                                ItemStack stack1=stackHandler.getStackInSlot(i1);
                                if (stack1.getItem() instanceof com.gq2529.momostories.item.tools.Bleeding.OneType) {
                                    if (MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(Player,stack1,attack).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.EVENT)))return;
                                    event.setCanceled(true);
                                }
                            }
                            ItemCardContainer.setItemStackHandler(itemStack,stackHandler);
                        }
                    }
                }
            }
        }
    }

    public static class DecayPotion{
        public static EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ){
            if (!worldIn.isRemote)
            {
                if (worldIn.getBlockState(pos).getBlock() == Blocks.RED_FLOWER)
                {
                    if (MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(player,player.getHeldItem(hand),null).setItemUseType(new MoMoCardEffortEvent.OnItemUseType(player,worldIn,pos,hand,facing,hitX,hitY,hitZ)).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.USE)))return EnumActionResult.PASS;
                    worldIn.setBlockState(pos, ModBlocks.WITHER_ROSE.getDefaultState());
                    return EnumActionResult.SUCCESS;
                }

            }
            return EnumActionResult.PASS;
        }
    }
    public static class CompoundFertilizer{
        public static void onRightUse(PlayerInteractEvent.RightClickBlock event) {
            World world = event.getWorld();
            BlockPos pos = event.getPos();
            IBlockState state = world.getBlockState(pos);
            Block block = state.getBlock();
            if (!world.isRemote) {
                ItemStack itemstack = event.getEntityPlayer().getHeldItem(event.getHand());
                EntityPlayer player = event.getEntityPlayer();
                if (itemstack.getItem() == ModItems.COMPOUND_FERTILIZER) {
                    if (block instanceof BlockCrops && ((BlockCrops) block).canGrow(world, pos, state, false)) {
                        if(MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(player,itemstack,null).setMessage(MoMoCardEffortEvent.ExtraMessage.CompoundFertilizer.CROPS)))return;
                        itemstack.shrink(1);
                        for (int x0 = -1; x0 <= 1; x0++) {
                            for (int z0 = -1; z0 <= 1; z0++) {
                                int x = pos.getX() + x0;
                                int y = pos.getY();
                                int z = pos.getZ() + z0;
                                getBlock(x, y, z, world);
                            }
                        }
                    }
                    //Sapling
                    else if (itemstack.getItem() == ModItems.COMPOUND_FERTILIZER) {
                        if (block instanceof BlockSapling) {
                            if(MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(player,itemstack,null).setMessage(MoMoCardEffortEvent.ExtraMessage.CompoundFertilizer.SAPLING)))return;
                            player.swingArm(event.getHand());
                            itemstack.shrink(1);
                            for (int x0 = -2; x0 <= 2; x0++) {
                                for (int z0 = -2; z0 <= 2; z0++) {
                                    int x = pos.getX() + x0;
                                    int y = pos.getY();
                                    int z = pos.getZ() + z0;
                                    getBlock(x, y, z, world);
                                }
                            }
                        }
                    }
                }
            }

        }

        private static void getBlock(int x,int y,int z, World world){
            BlockPos CropPos = new BlockPos(x, y, z); IBlockState CropState = world.getBlockState(CropPos); Block CropBlock = CropState.getBlock();
            if (CropBlock instanceof BlockSapling){ GrowthSapling (CropState, world, CropPos); }
            if (CropBlock instanceof BlockCrops) { Growth(CropState, world, CropPos, 7); }
        }

        private static void Growth(IBlockState CropState, World world, BlockPos CropPos, int loops){
            for (int i = 0; i < loops; i++) {
                IGrowable GrowableCrop = (IGrowable) CropState.getBlock();
                CropState = world.getBlockState(CropPos);
                if (GrowableCrop.canGrow(world, CropPos, CropState, false))
                {
                    GrowableCrop.grow(world, world.rand, CropPos, CropState);
                    ((net.minecraft.world.WorldServer) world).spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, false, CropPos.getX() + 0.5D, CropPos.getY() + 1.0D, CropPos.getZ() + 0.5D, 2, 0.15D, 0.15D, 0.15D, 0.0D);
                }
            }
        }

        private static void GrowthSapling(IBlockState SaplingState, World world, BlockPos SaplingPos){
            for (int i = 0; i < 4; i++) {
                IGrowable GrowableSapling = (IGrowable) SaplingState.getBlock();
                SaplingState = world.getBlockState(SaplingPos);
                if (SaplingState.getBlock() instanceof BlockSapling)
                {
                    if (GrowableSapling.canGrow(world, SaplingPos, SaplingState, false))
                    {
                        GrowableSapling.grow(world, world.rand, SaplingPos, SaplingState);
                        ((net.minecraft.world.WorldServer) world).spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, false, SaplingPos.getX() + 0.5D, SaplingPos.getY() + 1, SaplingPos.getZ() + 0.5D, 2, 0.15D, 0.15D, 0.15D, 0.0D);
                    }
                }
            }
        }
    }
    //remove into FragmentsOfFalseGods

//    public static class UnknownCards{
//        public static ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand){
//            if (!world.isRemote){
//                if (MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(player,player.getHeldItem(hand),null).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.RIGHT_CLICK)))return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
//                Item card= MoMoCards.getCard(new Random().nextInt(MoMoCards.getCount()));
//                player.getHeldItem(hand).shrink(1);
//                ModCommands.give(player,new ItemStack(card));
//                return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
//
//            }
//            return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
//
//        }
//    }

    public static class TheSupremeMagicDeepLake{
        public static void Deep_lake(LivingHurtEvent event) {
            World world = event.getEntity().world;
            if (!world.isRemote) {
                EntityLivingBase attack = event.getEntityLiving();
                if (attack instanceof EntityPlayer) {
                    EntityPlayer Player = (EntityPlayer) attack;
                    for (int i = 0; i < Player.inventory.getSizeInventory(); ++i) {
                        ItemStack itemStack = Player.inventory.getStackInSlot(i);
                        if (itemStack.getItem() == ModItems.THE_SUPREME_MAGI_DEEPLAKE)
                        {

                            if (event.getAmount() > 5.0f) {
                                if (0.38 > Math.random()) {
                                    if(!MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(Player,itemStack,null).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.EVENT))) {
                                        attack.addPotionEffect(new PotionEffect(MobEffects.SPEED, 100, 3, false, false));
                                        attack.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 100, 2, false, false));
                                        attack.maxHurtResistantTime = 100;
                                    }
                                }
                            }
                        }



                        if (itemStack.getItem()== com.Hileb.moremomostories.common.world.item.ModItems.ITEM_CARD_CONTAINER){
                            ItemStackHandler stackHandler= ItemCardContainer.getItemStackHandler(itemStack);
                            for(int i1=0;i1<stackHandler.getSlots();i1++){
                                ItemStack stack1=stackHandler.getStackInSlot(i1);
                                if (stack1.getItem() == ModItems.THE_SUPREME_MAGI_DEEPLAKE)
                                {

                                    if (event.getAmount() > 5.0f) {
                                        if (0.38 > Math.random()) {
                                            if(!MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(Player,stack1,null).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.EVENT))) {
                                                attack.addPotionEffect(new PotionEffect(MobEffects.SPEED, 100, 3, false, false));
                                                attack.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 100, 2, false, false));
                                                attack.maxHurtResistantTime = 100;
                                            }
                                        }
                                    }
                                }
                            }
                            ItemCardContainer.setItemStackHandler(itemStack,stackHandler);
                        }
                    }
                }
            }
        }
    }
    public static class Freshman{
        public static void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
            if (!stack.hasTagCompound()) {
                stack.setTagCompound(new NBTTagCompound());
            }
            if (worldIn.isRemote || !(entityIn instanceof EntityPlayer)) {
                return;
            }
            repairAllItems(worldIn, (EntityPlayer) entityIn,stack);
        }

        private static void repairAllItems(World worldIn, EntityPlayer player,ItemStack stack) {
            if (MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(player,stack,null).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.UPDATE)))return;
            final IItemHandler inv = player.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
            assert inv != null;
            for (int i = 0; i < inv.getSlots(); i++) {
                final ItemStack invStack = inv.getStackInSlot(i);
                if (invStack.isEmpty() || !invStack.getItem().isRepairable()) {
                    continue;
                }
                if (worldIn.getWorldTime() % 20 == 1) {
                    if (invStack != player.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND) && invStack != player.getItemStackFromSlot(EntityEquipmentSlot.OFFHAND) || !player.isSwingInProgress) {
                        if (!invStack.getHasSubtypes() && invStack.getMaxDamage() != 0 && invStack.getItemDamage() > 0)
                            invStack.setItemDamage(invStack.getItemDamage() - 1);
                    }
                }
            }
        }
        public static boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged){
            if(!oldStack.isEmpty() || !newStack.isEmpty()){
                if(oldStack.getItem() == newStack.getItem() && !slotChanged)
                    return false;
            }
            return !oldStack.equals(newStack);
        }
    }

    public static class ConscriptionOrder{
        public static ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand) {
            if (!world.isRemote) {
                if (player.getHeldItem(hand).getItem() == ModItems.CONSCRIPTION_ORDER) {
                    EntityLivingBase reinforcements = new Reinforcements(world);
                    reinforcements.setPosition(player.posX, player.posY, player.posZ);
                    world.spawnEntity(reinforcements);
                    //hand change by hileb
                    reinforcements.setItemStackToSlot(hand==EnumHand.MAIN_HAND?EntityEquipmentSlot.MAINHAND:EntityEquipmentSlot.OFFHAND, new ItemStack(Items.IRON_SWORD));
                    reinforcements.setItemStackToSlot(hand!=EnumHand.MAIN_HAND?EntityEquipmentSlot.MAINHAND:EntityEquipmentSlot.OFFHAND, new ItemStack(Items.SHIELD));
                    player.getCooldownTracker().setCooldown(player.getHeldItem(hand).getItem(), 20 * 20);
                    return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
                }
            }
            return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
        }
        public static void conscriptionOrder(LivingHurtEvent event)
        {
            World world = event.getEntity().world;
            if (!world.isRemote)
            {
                EntityLivingBase hurt = event.getEntityLiving();
                EntityLivingBase attacker = (EntityLivingBase) event.getSource().getTrueSource();
                if (attacker instanceof EntityPlayer)
                {
                    EntityPlayer Player = (EntityPlayer) attacker;
                    for (int i = 0; i < Player.inventory.getSizeInventory(); ++i)
                    {
                        ItemStack itemStack = Player.inventory.getStackInSlot(i);
                        if (itemStack.getItem() == ModItems.CONSCRIPTION_ORDER)
                        {
                            if (!MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(Player,itemStack,hurt).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.EVENT)))
                            hurt.addPotionEffect(new PotionEffect(MobEffects.SPEED, 200, 4,false,false));
                        }


                        if (itemStack.getItem()== com.Hileb.moremomostories.common.world.item.ModItems.ITEM_CARD_CONTAINER){
                            ItemStackHandler stackHandler= ItemCardContainer.getItemStackHandler(itemStack);
                            for(int i1=0;i1<stackHandler.getSlots();i1++){
                                ItemStack stack1=stackHandler.getStackInSlot(i1);
                                if (stack1.getItem() == ModItems.CONSCRIPTION_ORDER)
                                {
                                    if (!MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(Player,stack1,hurt).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.EVENT)))
                                        hurt.addPotionEffect(new PotionEffect(MobEffects.SPEED, 200, 4,false,false));
                                }
                            }
                            ItemCardContainer.setItemStackHandler(itemStack,stackHandler);
                        }

                    }
                }
            }
        }
    }

    public static class Reed{
        public static void reed(LivingHurtEvent event) {
            World world = event.getEntity().world;
            if (!world.isRemote) {
                EntityLivingBase beAttackeder = event.getEntityLiving();
                if (beAttackeder instanceof EntityPlayer) {
                    EntityPlayer Player = (EntityPlayer) beAttackeder;
                    for (int i = 0; i < Player.inventory.getSizeInventory(); ++i) {
                        ItemStack itemStack = Player.inventory.getStackInSlot(i);
                        if (itemStack.getItem() == ModItems.REED) {
                            if (MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(Player,itemStack,null).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.EVENT)))return;
                            event.setAmount(event.getAmount() * 1.5F);
                        }
                        if (itemStack.getItem()== com.Hileb.moremomostories.common.world.item.ModItems.ITEM_CARD_CONTAINER){
                            ItemStackHandler stackHandler= ItemCardContainer.getItemStackHandler(itemStack);
                            for(int oi1=0;oi1<stackHandler.getSlots();oi1++){
                                ItemStack stack1=stackHandler.getStackInSlot(oi1);
                                if (stack1.getItem() == ModItems.REED) {
                                    if (MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(Player,stack1,null).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.EVENT)))return;
                                    event.setAmount(event.getAmount() * 1.5F);
                                }
                            }
                            ItemCardContainer.setItemStackHandler(itemStack,stackHandler);
                        }
                    }
                }
            }
        }
    }

    public static class WiseReed{
        public static void reed(LivingHurtEvent event) {
            World world = event.getEntity().world;
            if (!world.isRemote) {
                EntityLivingBase attack = event.getEntityLiving();
                if (attack instanceof EntityPlayer) {
                    EntityPlayer Player = (EntityPlayer) attack;
                    for (int i = 0; i < Player.inventory.getSizeInventory(); ++i) {
                        ItemStack itemStack = Player.inventory.getStackInSlot(i);
                        if (itemStack.getItem() == ModItems.WISEREED) {//fix bug from REED to WISEREED
                            if (MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(Player,itemStack,null).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.EVENT)))return;
                            event.setAmount(event.getAmount() * 1.5F);
                        }



                        if (itemStack.getItem()== com.Hileb.moremomostories.common.world.item.ModItems.ITEM_CARD_CONTAINER){
                            ItemStackHandler stackHandler= ItemCardContainer.getItemStackHandler(itemStack);
                            for(int js=0;js<stackHandler.getSlots();js++){
                                ItemStack stack1=stackHandler.getStackInSlot(js);
                                if (stack1.getItem() == ModItems.WISEREED) {//fix bug from REED to WISEREED
                                    if (MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(Player,stack1,null).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.EVENT)))return;
                                    event.setAmount(event.getAmount() * 1.5F);
                                }
                            }
                            ItemCardContainer.setItemStackHandler(itemStack,stackHandler);
                        }

                    }
                }
            }
        }

        public static void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
            EntityPlayer Player = (EntityPlayer) entityIn;
            if (!worldIn.isRemote)
            {
                if (stack.getItem() == ModItems.WISEREED)
                {
                    if (worldIn.getWorldTime() % 20 == 5)
                    {
                        if (MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(Player,stack,null).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.UPDATE)))return;
                        Player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION,5 * 20,0));
                        if (Player.getHealth() < 5)
                        {
                            Player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION,2 * 20,4));
                        }
                    }

                }
            }
        }
    }
    public static class FortSlim{
        public static void luna_hunting(LivingHurtEvent event) {
            World world = event.getEntity().world;
            if (!world.isRemote) {
                EntityLivingBase hurt = event.getEntityLiving();
                EntityLivingBase attacker = (EntityLivingBase) event.getSource().getTrueSource();
                if (attacker instanceof EntityPlayer) {
                    EntityPlayer Player = (EntityPlayer) attacker;
                    for (int i = 0; i < Player.inventory.getSizeInventory(); ++i) {
                        ItemStack itemStack = Player.inventory.getStackInSlot(i);
                        if (itemStack.getItem() == ModItems.FORT_SLIM) {
                            if (MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(Player,itemStack,hurt).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.EVENT)))return;
                            hurt.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 60, 255, false, false));
                        }


                        if (itemStack.getItem()== com.Hileb.moremomostories.common.world.item.ModItems.ITEM_CARD_CONTAINER){
                            ItemStackHandler stackHandler= ItemCardContainer.getItemStackHandler(itemStack);
                            for(int i1=0;i1<stackHandler.getSlots();i1++){
                                ItemStack stack1=stackHandler.getStackInSlot(i1);
                                if (stack1.getItem() == ModItems.FORT_SLIM) {
                                    if (MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(Player,stack1,hurt).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.EVENT)))return;
                                    hurt.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 60, 255, false, false));
                                }
                            }
                            ItemCardContainer.setItemStackHandler(itemStack,stackHandler);
                        }

                    }
                }
            }
        }
        public static ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand)
        {
            if (player.getHeldItem(hand).getItem() == ModItems.FORT_SLIM && !world.isRemote)
            {
                if (MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(player,player.getHeldItem(hand),null).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.RIGHT_CLICK)))new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
                int a = (int) player.getHealth();
                player.setHealth(a - 6);
                player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 200, 255,false,false));//缓慢
                player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 200, 14,false,false));//吸收
                player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 200, 4,false,false));//抗性
                player.getCooldownTracker().setCooldown(player.getHeldItem(hand).getItem(),60 * 20 );
                return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
            }
            return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
        }
    }
    public static class DayTime{

        public static ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand)
        {
            if (player.getHeldItem(hand).getItem() == ModItems.DAYTIME && !world.isRemote)
            {
                final long time = world.getWorldTime() + 24000L;

                if (time % 24000L > 11600L && time % 24000L < 23250L)
                {

                    if (MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(player,player.getHeldItem(hand),null).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.RIGHT_CLICK)))
                    world.setWorldTime(time - time % 24000L - 450);
                    if (false){//primer function
                        player.sendMessage(new TextComponentTranslation("DayTimeText"));
                        player.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 100, 3));
                        //primer end in this
                    }
                    //add by hileb:
                    player.addPotionEffect(PotionDayTime.getEffect());
                    if (player instanceof EntityPlayerMP){
                        EntityPlayerMP playerMP=(EntityPlayerMP)player;
                        playerMP.connection.sendPacket(new SPacketTitle(SPacketTitle.Type.ACTIONBAR,new TextComponentTranslation("DayTimeText"),20,40,20));
                    }
                    return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
                    //end add


                }
            }
            return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
        }
    }


    public static class LeydenJar{
        public static void luna_hunting(LivingHurtEvent event) {
            World world = event.getEntity().world;
            if (!world.isRemote) {
                EntityLivingBase hurt = event.getEntityLiving();
                EntityLivingBase attacker = (EntityLivingBase) event.getSource().getTrueSource();
                if (attacker instanceof EntityPlayer) {
                    EntityPlayer Player = (EntityPlayer) attacker;
                    for (int i = 0; i < Player.inventory.getSizeInventory(); ++i) {
                        ItemStack itemStack = Player.inventory.getStackInSlot(i);
                        if (itemStack.getItem() == ModItems.LEYDEN_JAR) {
                            if (!MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(Player,itemStack,hurt).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.EVENT))) {
                                if (world.isRaining()) {
                                    world.addWeatherEffect(new EntityLightningBolt(world, hurt.posX, hurt.posY, hurt.posZ, false));
                                }
                                if (hurt.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD) {
                                    event.setAmount(event.getAmount() * 2F);
                                }
                                break;
                            }
                        }



                        if (itemStack.getItem()== com.Hileb.moremomostories.common.world.item.ModItems.ITEM_CARD_CONTAINER){
                            ItemStackHandler stackHandler= ItemCardContainer.getItemStackHandler(itemStack);
                            for(int i5=0;i5<stackHandler.getSlots();i5++){
                                ItemStack stack1=stackHandler.getStackInSlot(i5);
                                if (stack1.getItem() == ModItems.LEYDEN_JAR) {
                                    if (!MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(Player,stack1,hurt).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.EVENT))) {
                                        if (world.isRaining()) {
                                            world.addWeatherEffect(new EntityLightningBolt(world, hurt.posX, hurt.posY, hurt.posZ, false));
                                        }
                                        if (hurt.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD) {
                                            event.setAmount(event.getAmount() * 2F);
                                        }
                                        break;
                                    }
                                }
                            }
                            ItemCardContainer.setItemStackHandler(itemStack,stackHandler);
                        }

                    }
                }
            }
        }
    }
    public static class DevilsBlood{
        public static void devilsblood(LivingHurtEvent event) {
            World world = event.getEntity().world;
            if (!world.isRemote) {
                EntityLivingBase hurt = event.getEntityLiving();
                EntityLivingBase attacker = (EntityLivingBase) event.getSource().getTrueSource();
                if (attacker instanceof EntityPlayer) {
                    EntityPlayer Player = (EntityPlayer) attacker;
                    for (int i = 0; i < Player.inventory.getSizeInventory(); ++i) {
                        ItemStack itemStack = Player.inventory.getStackInSlot(i);
                        if (itemStack.getItem() == ModItems.DEVILS_BLOOD) {
                            if (!MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(Player,itemStack,hurt).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.EVENT)))hurt.setFire(45);
                        }
                        if (itemStack.getItem()== com.Hileb.moremomostories.common.world.item.ModItems.ITEM_CARD_CONTAINER){
                            ItemStackHandler stackHandler= ItemCardContainer.getItemStackHandler(itemStack);
                            for(int s5=0;s5<stackHandler.getSlots();s5++){
                                ItemStack stack1=stackHandler.getStackInSlot(s5);
                                if (stack1.getItem() == ModItems.DEVILS_BLOOD) {
                                    if (!MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(Player,stack1,hurt).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.EVENT)))hurt.setFire(45);
                                }
                            }
                            ItemCardContainer.setItemStackHandler(itemStack,stackHandler);
                        }
                    }
                }
            }
        }
    }

    public static class AiLingWishes{
        public static void erin(LivingDeathEvent event) {
            World world = event.getEntity().world;
            if (!world.isRemote) {
                if (event.getEntityLiving() instanceof EntityPlayer) {
                    EntityPlayer Player = (EntityPlayer) event.getEntityLiving();
                    for (int i = 0; i < Player.inventory.getSizeInventory(); ++i) {
                        ItemStack itemStack = Player.inventory.getStackInSlot(i);
                        if (!Player.getCooldownTracker().hasCooldown(ModItems.AI_LING_WISHES)){//fix bug by Hileb
                            if (itemStack.getItem() == ModItems.AI_LING_WISHES && !MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(Player,itemStack,null).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.EVENT))) {
                                Player.setHealth(1);
                                event.setCanceled(true);
                                Player.hurtResistantTime = 30;
                                Player.world.playSound(null,Player.getPosition(), SoundEvents.ITEM_TOTEM_USE, SoundCategory.PLAYERS,2,2);
                                Player.getCooldownTracker().setCooldown(ModItems.AI_LING_WISHES, 6 * 20);
                            }
                        }
                        if (itemStack.getItem()== com.Hileb.moremomostories.common.world.item.ModItems.ITEM_CARD_CONTAINER){
                            ItemStackHandler stackHandler= ItemCardContainer.getItemStackHandler(itemStack);
                            for(int s8=0;s8<stackHandler.getSlots();s8++){
                                ItemStack stack1=stackHandler.getStackInSlot(s8);
                                if (!Player.getCooldownTracker().hasCooldown(ModItems.AI_LING_WISHES)){//fix bug by Hileb
                                    if (stack1.getItem() == ModItems.AI_LING_WISHES && !MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(Player,stack1,null).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.EVENT))) {
                                        Player.setHealth(1);
                                        event.setCanceled(true);
                                        Player.hurtResistantTime = 30;
                                        Player.world.playSound(null,Player.getPosition(), SoundEvents.ITEM_TOTEM_USE, SoundCategory.PLAYERS,2,2);
                                        Player.getCooldownTracker().setCooldown(ModItems.AI_LING_WISHES, 6 * 20);
                                    }
                                }
                            }
                            ItemCardContainer.setItemStackHandler(itemStack,stackHandler);
                        }
                    }
                }
            }
        }
    }

    public static class Night{
        public static ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand) {
            if (player.getHeldItem(hand).getItem()== ModItems.NIGHT && !world.isRemote) {
                final long time = world.getWorldTime() + 24000L;
                if (time % 24000L < 12750L || time % 24000L > 23250L) {
                    if (!MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(player, player.getHeldItem(hand), null).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.RIGHT_CLICK))){
                        world.setWorldTime(time - time % 24000L + 13750);
                        if (player instanceof EntityPlayerMP){
                            EntityPlayerMP playerMP=(EntityPlayerMP)player;
                            playerMP.connection.sendPacket(new SPacketTitle(SPacketTitle.Type.ACTIONBAR,new TextComponentTranslation("NightText"),20,40,20));
                        }
                        player.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 100, 3));
                        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
                    }
                }
            }
            return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
        }
    }

    public static class TheAngelProject{
        public static void onJump(LivingEvent.LivingJumpEvent event) {
            World world = event.getEntity().world;
            if (!world.isRemote) {
                if (event.getEntityLiving() instanceof EntityPlayer) {
                    EntityPlayer player = (EntityPlayer) event.getEntityLiving();
                    for (int i = 0; i < player.inventory.getSizeInventory(); ++i) {
                        ItemStack itemStack = player.inventory.getStackInSlot(i);
                        if (itemStack.getItem() instanceof IJumpBoost) {
                            if (MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(player,itemStack,null).setMessage(MoMoCardEffortEvent.ExtraMessage.TheAngelProject.JUMP_MESSAGE))){
                                IJumpBoost jumpBoost = (IJumpBoost) (itemStack.getItem());
                                player.motionY += jumpBoost.getJumpBoost();
                                break;
                            }
                        }
                        if (itemStack.getItem()== com.Hileb.moremomostories.common.world.item.ModItems.ITEM_CARD_CONTAINER){
                            ItemStackHandler stackHandler= ItemCardContainer.getItemStackHandler(itemStack);
                            for(int i7=0;i7<stackHandler.getSlots();i7++){
                                ItemStack stack1=stackHandler.getStackInSlot(i7);
                                if (stack1.getItem() instanceof IJumpBoost) {
                                    if (MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(player,stack1,null).setMessage(MoMoCardEffortEvent.ExtraMessage.TheAngelProject.JUMP_MESSAGE))){
                                        IJumpBoost jumpBoost = (IJumpBoost) (stack1.getItem());
                                        player.motionY += jumpBoost.getJumpBoost();
                                        break;
                                    }
                                }
                            }
                            ItemCardContainer.setItemStackHandler(itemStack,stackHandler);
                        }
                    }
                }
            }
        }
        public static void fist(LivingHurtEvent event) {
            World world = event.getEntity().world;
            if (!world.isRemote) {
                EntityLivingBase hurt = event.getEntityLiving();
                EntityLivingBase attacker = (EntityLivingBase) event.getSource().getTrueSource();
                if (attacker instanceof EntityPlayer) {
                    EntityPlayer Player = (EntityPlayer) attacker;
                    for (int i = 0; i < Player.inventory.getSizeInventory(); ++i) {
                        ItemStack itemStack = Player.inventory.getStackInSlot(i);
                        if (itemStack.getItem() == ModItems.THE_ANGERL_PROJECT) {
                            if (!MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(Player,Player.getHeldItemMainhand(),null).setMessage(MoMoCardEffortEvent.ExtraMessage.TheAngelProject.FIRST_MESSAGE))){
                                if (Player.getHeldItemMainhand().isEmpty()) {
                                    event.setAmount(event.getAmount() + 15);
                                }
                                Player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 2 * 20, 2, false, false));
                                if (0.25 > Math.random())
                                {
                                    Player.heal(2f);
                                }
                                break;
                            }
                        }


                        if (itemStack.getItem()== com.Hileb.moremomostories.common.world.item.ModItems.ITEM_CARD_CONTAINER){
                            ItemStackHandler stackHandler= ItemCardContainer.getItemStackHandler(itemStack);
                            for(int i8=0;i8<stackHandler.getSlots();i8++){
                                ItemStack stack1=stackHandler.getStackInSlot(i8);
                                if (stack1.getItem() == ModItems.THE_ANGERL_PROJECT) {
                                    if (!MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(Player,Player.getHeldItemMainhand(),null).setMessage(MoMoCardEffortEvent.ExtraMessage.TheAngelProject.FIRST_MESSAGE))){
                                        if (Player.getHeldItemMainhand().isEmpty()) {
                                            event.setAmount(event.getAmount() + 15);
                                        }
                                        Player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 2 * 20, 2, false, false));
                                        if (0.25 > Math.random())
                                        {
                                            Player.heal(2f);
                                        }
                                        break;
                                    }
                                }
                            }
                            ItemCardContainer.setItemStackHandler(itemStack,stackHandler);
                        }

                    }
                }
            }
        }
        public static void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
            if (!worldIn.isRemote) {
                EntityPlayer Player = (EntityPlayer) entityIn;
                if (stack.getItem() == ModItems.THE_ANGERL_PROJECT) {
                    if (worldIn.getWorldTime() % 800  == 0)
                        if (!MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(Player,Player.getHeldItemMainhand(),null).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.UPDATE)))
                            Player.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 8 * 20, 2, false, false));
                }
                for (int i = 0; i < Player.inventory.getSizeInventory(); ++i) {
                    ItemStack itemStack = Player.inventory.getStackInSlot(i);
                    if (itemStack.getItem() == ModItems.ESTES && (!MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(Player,Player.getHeldItemMainhand(),null).setMessage(MoMoCardEffortEvent.ExtraMessage.TheAngelProject.ESTES)))) {
                        itemStack.shrink(1);
                        Player.sendMessage(new TextComponentTranslation("EstesText1"));
                    }
                    if (itemStack.getItem() == ModItems.THE_SUPREME_MAGI_DEEPLAKE && (!MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(Player,Player.getHeldItemMainhand(),null).setMessage(MoMoCardEffortEvent.ExtraMessage.TheAngelProject.THE_SUPREME_MAGI_DEEPLAKE)))) {
                        itemStack.shrink(1);
                        Player.sendMessage(new TextComponentTranslation("TheSupremeMagiDeepLakeText1"));
                    }
                }
            }
        }
    }

    public static class OhamHeavyCavalryRegiment{
        public static void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
        {
            if (!worldIn.isRemote)
            {
                EntityPlayer Player = (EntityPlayer) entityIn;
                if (stack.getItem()== ModItems.OHAM_HEAVY_CAVALRY_REGIMENT) {
                    if (!MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(Player,stack,null).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.UPDATE)))
                    Player.addPotionEffect(new PotionEffect(ModPotions.HEAVY_ARMOR, 20));
                }
            }
        }
    }

    public static class AdjudicatorBalance{

        public static EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
            if (!world.isRemote) {
                ItemStack stack=player.getHeldItem(hand);
                if (stack.getItem() == ModItems.ADJUDICATOR_BALANCE) {
                    Block block = world.getBlockState(pos).getBlock();
                    MoMoCardEffortEvent effortEvent=new MoMoCardEffortEvent(player,stack,null).setItemUseType(new MoMoCardEffortEvent.OnItemUseType(player,world,pos,hand,facing,hitX,hitY,hitZ)).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.USE);
                    if ( block == Blocks.STONE)
                    {
                        return setBlock(world,pos,Blocks.IRON_ORE,effortEvent);
                    }
                    else if (block == Blocks.IRON_ORE)
                    {
                        return setBlock(world,pos,Blocks.REDSTONE_ORE,effortEvent);
                    }
                    else if (block == Blocks.GOLD_ORE)
                    {
                        return setBlock(world,pos,Blocks.LAPIS_ORE,effortEvent);
                    }
                    else if (block == Blocks.LAPIS_ORE)
                    {
                        EnumActionResult result=setBlock(world,pos,Blocks.STONE,effortEvent);
                        if (0.2 > Math.random())
                        {
                            result=setBlock(world,pos,Blocks.EMERALD_ORE,effortEvent);
                        }
                        return result;
                    }

                }
            }
            return EnumActionResult.PASS;
        }
        private static EnumActionResult setBlock (World worldIn, BlockPos pos, Block block,MoMoCardEffortEvent effortEvent){
            if (!MinecraftForge.EVENT_BUS.post(effortEvent)){
                worldIn.setBlockState(pos,block.getDefaultState());
                return EnumActionResult.SUCCESS;
            }else return EnumActionResult.PASS;
        }
    }
    public static class  PlagueDoctor{

        public static void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
            if (!worldIn.isRemote) {
                EntityPlayer Player = (EntityPlayer) entityIn;
                Collection<PotionEffect> activePotionEffects = Player.getActivePotionEffects();
                for (int i = 0; i < activePotionEffects.size(); i++) {
                    PotionEffect buff = (PotionEffect) activePotionEffects.toArray()[i];
                    if (buff.getPotion().isBadEffect()) {
                        if (worldIn.getWorldTime() % 20 == 2 && !MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(Player,stack,null).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.UPDATE))) {
                            if (0.25 > Math.random()) {
                                Player.removePotionEffect(buff.getPotion());
                            }
                            if (0.25 > Math.random()) {
                                Player.removePotionEffect(buff.getPotion());
                                Player.setHealth(5);
                            }
                            if (0.25 > Math.random()) {
                                Player.removePotionEffect(buff.getPotion());
                                Player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 20 * 5, 1));
                            }
                            if (0.25 > Math.random()) {
                                Player.removePotionEffect(buff.getPotion());
                                Player.dropItem(ModItems.FAKE_DIAMOND, 64);

                            }
                        }
                    }
                }
            }
        }
    }
    public static class FraudulentBottles{

        public static ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand)
        {
            if ( !world.isRemote) {
                ItemStack stack=player.getHeldItem(hand);
                if (stack.getItem() == ModItems.FRAUDULENT_BOTTLES) {
                    for (int i = 0; i < player.inventory.getSizeInventory(); ++i)
                    {
                        ItemStack itemStack = player.inventory.getStackInSlot(i);
                        if (itemStack.getItem() == Items.DIAMOND)
                        {
                            if (!MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(player,stack,null).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.RIGHT_CLICK))){
                                Random r = new Random();
                                int num = r.nextInt(2);
                                if(num > 0) {
                                    itemStack.shrink(1);
                                    if(!player.inventory.addItemStackToInventory(new ItemStack(Blocks.DEADBUSH, 1)))
                                    {
                                        player.entityDropItem(new ItemStack(Blocks.DEADBUSH, 1), 0);
                                    }
                                    player.sendMessage(new TextComponentTranslation("FraudulentBottlesText1"));
                                    return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
                                }
                                else
                                {
                                    itemStack.shrink(1);
                                    if(!player.inventory.addItemStackToInventory(new ItemStack(ModItems.FAKE_DIAMOND, 2)))
                                    {
                                        player.entityDropItem(new ItemStack(ModItems.FAKE_DIAMOND, 2), 0);
                                    }
                                    player.sendMessage(new TextComponentTranslation("FraudulentBottlesText2"));
                                    return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
                                }
                            }
                        }
                    }
                }
            }

            return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
        }
    }
    public static class Scavengers{
        public static void scavengers(LivingEvent.LivingUpdateEvent event)
        {
            World world = event.getEntity().world;
            if (!world.isRemote)
            {
                EntityLivingBase hurt = event.getEntityLiving();
                if (hurt instanceof EntityPlayer)
                {
                    EntityPlayer Player = (EntityPlayer) hurt;
                    for (int i = 0; i < Player.inventory.getSizeInventory(); ++i) {
                        ItemStack itemStack = Player.inventory.getStackInSlot(i);
                        if (itemStack.getItem() == ModItems.SCAVENGERS) {
                            if (Player.isPotionActive(MobEffects.HUNGER))
                            {
                                if (!MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent((EntityPlayer)hurt,itemStack,null).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.EVENT))){
                                    Player.removePotionEffect(MobEffects.HUNGER);
                                }
                            }
                        }
                        if (itemStack.getItem()== com.Hileb.moremomostories.common.world.item.ModItems.ITEM_CARD_CONTAINER){
                            ItemStackHandler stackHandler= ItemCardContainer.getItemStackHandler(itemStack);
                            for(int i9=0;i9<stackHandler.getSlots();i9++){
                                ItemStack stack1=stackHandler.getStackInSlot(i9);
                                if (stack1.getItem() == ModItems.SCAVENGERS) {
                                    if (Player.isPotionActive(MobEffects.HUNGER))
                                    {
                                        if (!MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent((EntityPlayer)hurt,stack1,null).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.EVENT))){
                                            Player.removePotionEffect(MobEffects.HUNGER);
                                        }
                                    }
                                }
                            }
                            ItemCardContainer.setItemStackHandler(itemStack,stackHandler);
                        }
                    }
                }
            }
        }
    }
    public static class Twist{
        public static void twist(LivingHurtEvent event) {
            World world = event.getEntity().world;
            if (!world.isRemote) {
                EntityLivingBase hurt = event.getEntityLiving();
                EntityLivingBase attacker = (EntityLivingBase) event.getSource().getTrueSource();
                if (attacker instanceof EntityPlayer) {
                    EntityPlayer Player = (EntityPlayer) attacker;
                    for (int i = 0; i < Player.inventory.getSizeInventory(); ++i) {
                        ItemStack itemStack = Player.inventory.getStackInSlot(i);
                        if (itemStack.getItem() == ModItems.TWIST) {
                            if (!MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(Player,itemStack,hurt).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.EVENT))){
                                if (0.5 >Math.random())
                                {
                                    hurt.setHealth(0);
                                }
                                else {
                                    attacker.attackEntityFrom(DamageSource1.TWIST, Float.MAX_VALUE);
                                }
                            }
                        }

                        if (itemStack.getItem()== com.Hileb.moremomostories.common.world.item.ModItems.ITEM_CARD_CONTAINER){
                            ItemStackHandler stackHandler= ItemCardContainer.getItemStackHandler(itemStack);
                            for(int i10=0;i10<stackHandler.getSlots();i10++){
                                ItemStack stack1=stackHandler.getStackInSlot(i10);
                                if (stack1.getItem() == ModItems.TWIST) {
                                    if (!MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(Player,stack1,hurt).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.EVENT))){
                                        if (0.5 >Math.random())
                                        {
                                            hurt.setHealth(0);
                                        }
                                        else {
                                            attacker.attackEntityFrom(DamageSource1.TWIST, Float.MAX_VALUE);
                                        }
                                    }
                                }
                            }
                            ItemCardContainer.setItemStackHandler(itemStack,stackHandler);
                        }


                    }
                }
            }
        }
    }

    public static class Fine{

        public static ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand)
        {
            ItemStack stack=player.getHeldItem(hand);
            if ( stack.getItem() == ModItems.FINE && !world.isRemote)
            {
                if (world.isRaining())
                {
                    if (!MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(player,stack,null).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.RIGHT_CLICK))){
                        world.getWorldInfo().setRaining(false);
                        player.sendMessage(new TextComponentTranslation("FineText"));
                        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
                    }
                }
            }
            return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
        }
    }
    public static class DarkClouds{

        public static ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand) {
            ItemStack stack=player.getHeldItem(hand);
            if (stack.getItem() == ModItems.DARK_CLOUDS && !world.isRemote) {
                if (!world.isRaining()) {
                    if (!MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(player,stack,null).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.RIGHT_CLICK))){
                        world.getWorldInfo().setRaining(true);
                        world.getWorldInfo().setThundering(false);
                        player.sendMessage(new TextComponentTranslation("DarkCloudsText"));
                        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
                    }
                }
            }
            return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
        }
    }
    public static class Estes{

        public static void onJump(LivingEvent.LivingJumpEvent event) {
            if (event.getEntityLiving() instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) event.getEntityLiving();
                for (int i = 0; i < player.inventory.getSizeInventory(); ++i) {
                    ItemStack itemStack = player.inventory.getStackInSlot(i);
                    if (itemStack.getItem() instanceof IJumpBoost) {
                        if (!MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(player,itemStack,null).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.JUMP))){
                            IJumpBoost jumpBoost = (IJumpBoost) (itemStack.getItem());
                            player.motionY += jumpBoost.getJumpBoost();
                            break;
                        }
                    }


                    if (itemStack.getItem()== com.Hileb.moremomostories.common.world.item.ModItems.ITEM_CARD_CONTAINER){
                        ItemStackHandler stackHandler= ItemCardContainer.getItemStackHandler(itemStack);
                        for(int i5=0;i5<stackHandler.getSlots();i5++){
                            ItemStack stack1=stackHandler.getStackInSlot(i5);
                            if (stack1.getItem() instanceof IJumpBoost) {
                                if (!MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(player,stack1,null).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.JUMP))){
                                    IJumpBoost jumpBoost = (IJumpBoost) (stack1.getItem());
                                    player.motionY += jumpBoost.getJumpBoost();
                                    break;
                                }
                            }
                        }
                        ItemCardContainer.setItemStackHandler(itemStack,stackHandler);
                    }

                }
            }
        }
        //BUFF
        public static void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
            if (!worldIn.isRemote) {
                EntityPlayer Player = (EntityPlayer) entityIn;
                if (stack.getItem() == ModItems.ESTES) {
                    if (!MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent((EntityPlayer)entityIn,stack,null).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.UPDATE))){
                        Player.addPotionEffect(new PotionEffect(ModPotions.DEMONESSENCE, 20));
                        if (!Player.onGround && Player.motionY < 0.0D)
                        {
                            Player.fallDistance = 0f;
                        }
                    }
                }
                for (int i = 0; i < Player.inventory.getSizeInventory(); ++i) {
                    ItemStack itemStack = Player.inventory.getStackInSlot(i);
                    if (itemStack.getItem() == ModItems.THE_ANGERL_PROJECT) {
                        if (!MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent((EntityPlayer)entityIn,stack,null).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.UPDATE))){
                            itemStack.shrink(1);
                            Player.sendMessage(new TextComponentTranslation("EstesText1"));
                        }
                    }
                }
            }
        }
    }
    public static class Sirin{
        public static void hurt(LivingHurtEvent event)
        {
            World world = event.getEntity().world;
            if (!world.isRemote) {
                EntityLivingBase attack = event.getEntityLiving();
                if (attack instanceof EntityPlayer) {
                    EntityPlayer Player = (EntityPlayer) attack;
                    for (int i = 0; i < Player.inventory.getSizeInventory(); ++i) {
                        ItemStack itemStack = Player.inventory.getStackInSlot(i);
                        if (itemStack.getItem() == ModItems.SIRIN)
                        {
                            int range = 20;
                            List<Entity> list = world.getEntitiesWithinAABB(EntityPlayer.class,new AxisAlignedBB(Player.posX - range, Player.posY - range, Player.posZ - range, Player.posX + range, Player.posY + range, Player.posZ + range));
                            for(Entity en : list) {
                                if (0.5 > Math.random()) {
                                    if (!MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent((EntityPlayer)en,itemStack,event.getEntityLiving()).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.EVENT))){
                                        EntityPlayer player = (EntityPlayer) en;
                                        player.heal(Player.getMaxHealth() / 2);
                                    }
                                }
                            }
                        }


                        if (itemStack.getItem()== com.Hileb.moremomostories.common.world.item.ModItems.ITEM_CARD_CONTAINER){
                            ItemStackHandler stackHandler= ItemCardContainer.getItemStackHandler(itemStack);
                            for(int i7=0;i7<stackHandler.getSlots();i7++){
                                ItemStack stack1=stackHandler.getStackInSlot(i7);
                                if (stack1.getItem() == ModItems.SIRIN)
                                {
                                    int range = 20;
                                    List<Entity> list = world.getEntitiesWithinAABB(EntityPlayer.class,new AxisAlignedBB(Player.posX - range, Player.posY - range, Player.posZ - range, Player.posX + range, Player.posY + range, Player.posZ + range));
                                    for(Entity en : list) {
                                        if (0.5 > Math.random()) {
                                            if (!MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent((EntityPlayer)en,stack1,event.getEntityLiving()).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.EVENT))){
                                                EntityPlayer player = (EntityPlayer) en;
                                                player.heal(Player.getMaxHealth() / 2);
                                            }
                                        }
                                    }
                                }
                            }
                            ItemCardContainer.setItemStackHandler(itemStack,stackHandler);
                        }

                    }
                }
            }
        }
    }

    public static class Split{

        public static ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand) {
            if (!world.isRemote)
                if (player.isSneaking())
                {
                    if (!MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(player,player.getHeldItem(hand),null).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.RIGHT_CLICK))){
                        player.attackEntityFrom(DamageSource1.INSANE, Float.MAX_VALUE);

                        if (0.01 >Math.random())
                        {
                            player.entityDropItem(new ItemStack(ModItems.SIRIN, 1), 0);

                        }
                        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
                    }
                }
            return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
        }

        public static boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
            World world = playerIn.world;
            if (!world.isRemote) {
                if (stack.getItem() == ModItems.SPLIT) {
                    if (!MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(playerIn,stack,target).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.USE))){
                        if (target instanceof EntityMob) {
                            Entity kindness = new Kindness(world);
                            kindness.setPosition(target.posX, target.motionY + 5, target.lastTickPosZ);
                            world.spawnEntity(kindness);
                            playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(hand).getItem(),20 * 20 );
                            return true;
                        } else {
                            Entity evil = new Evil(world);
                            evil.setPosition(target.posX, target.motionY + 5, target.lastTickPosZ);
                            world.spawnEntity(evil);
                            playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(hand).getItem(),20 * 20 );
                            return true;
                        }
                    }
                }
            }

            return false;
        }
    }
    public static class Miser{
        public static void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {


            //TODO : fixBug

            if (!worldIn.isRemote) {
                EntityPlayer player = (EntityPlayer) entityIn;
                if (worldIn.getWorldTime() % 20 == 0) {
                    int a = 0, b = 0, c = 0;
                    for (int i = 0; i < player.inventory.getSizeInventory(); ++i) {
                        ItemStack itemStack = player.inventory.getStackInSlot(i);
                        if (itemStack.getItem() == Items.GOLD_INGOT) {
                            a = a + itemStack.getCount();
                        }
                        if (itemStack.getItem() == Items.DIAMOND) {
                            b = b + itemStack.getCount();
                        }
                        if (itemStack.getItem() == Items.MAGMA_CREAM) {
                            c = c + itemStack.getCount();
                        }
                    }
                    //fix bug by Hileb
                    //gold
                    if (a <= 32 && a > 0)
                        if (!MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(player, stack, null).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.UPDATE)))
                            player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 20, 1, false, false));
                    if (a <= 64 && a > 32) {
                        if (!MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(player, stack, null).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.UPDATE)))
                            player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 20, 2, false, false));
                    }
                    if (a <= 128 && a > 64) {
                        if (!MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(player, stack, null).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.UPDATE)))
                            player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 20, 4, false, false));
                    }

                    //diamond
                    if (b <= 32 && b > 0){
                        if (!MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(player, stack, null).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.UPDATE)))
                            player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 20, 0, false, false));
                    }
                    if (b <= 64 && b > 32) {
                        if (!MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(player, stack, null).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.UPDATE)))
                            player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 20, 1, false, false));
                    }
                    if (b <= 128 && b > 64) {
                        if (!MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(player, stack, null).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.UPDATE)))
                            player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 20, 3, false, false));
                    }
                    if (c >= 3){
                        if (!MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(player, stack, null).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.UPDATE)))
                            player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 20, 2, false, false));
                    }
                }
            }
        }
    }
    public static class FourWayTrip{
        public static void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
            if (!worldIn.isRemote)
            {
                if (worldIn.getWorldTime() % 20 == 3) {
                    EntityPlayer Player = (EntityPlayer) entityIn;
                    if (Player.isSprinting()) {
                        if (!MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent((EntityPlayer)entityIn,stack,null).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.UPDATE))){
                            Player.getFoodStats().addStats(1, 1);
                        }
                    }
                }
            }
        }
    }
    public static class InternalStrife{
       public static void doTwilightCloakCheck(LivingEvent event) {
            if (event.getEntity() instanceof EntityLiving) {
                EntityLiving entityLiving = ((EntityLiving) event.getEntity());
                if (entityLiving.getAttackTarget() == null)
                    return;
                if (!(entityLiving.getAttackTarget() instanceof EntityPlayer))
                    return;
                if (entityLiving.getAttackTarget() instanceof EntityWither)
                    return;
                if (entityLiving.getAttackTarget() instanceof EntityDragon)
                    return;
                World world = event.getEntity().world;
                if (!world.isRemote) {
                    int range = 10;
                    EntityPlayer player = (EntityPlayer) entityLiving.getAttackTarget();
                    world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(player.posX - range, player.posY - range, player.posZ - range, player.posX + range, player.posY + range, player.posZ + range));
                    for (int i = 0; i < player.inventory.getSizeInventory(); ++i) {
                        ItemStack itemStack = player.inventory.getStackInSlot(i);
                        if (itemStack.getItem() == ModItems.INTERNAL_STRIIFE) {
                            if (event.getEntity() instanceof EntityLiving) {
                                if (!MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(player,itemStack,event.getEntityLiving()).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.EVENT))){
                                    ((EntityLiving) event.getEntity()).setAttackTarget(event.getEntityLiving());
                                }
                            }
                        }
                        if (itemStack.getItem()== com.Hileb.moremomostories.common.world.item.ModItems.ITEM_CARD_CONTAINER){
                            ItemStackHandler stackHandler= ItemCardContainer.getItemStackHandler(itemStack);
                            for(int i7=0;i7<stackHandler.getSlots();i7++){
                                ItemStack stack1=stackHandler.getStackInSlot(i7);
                                if (stack1.getItem() == ModItems.INTERNAL_STRIIFE) {
                                    if (event.getEntity() instanceof EntityLiving) {
                                        if (!MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(player,stack1,event.getEntityLiving()).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.EVENT))){
                                            ((EntityLiving) event.getEntity()).setAttackTarget(event.getEntityLiving());
                                        }
                                    }
                                }
                            }
                            ItemCardContainer.setItemStackHandler(itemStack,stackHandler);
                        }
                    }
                }
            }
        }
    }

    public static class WhiteFeathers{
        public static void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
            //null
        }
        public static void onJump(LivingEvent.LivingJumpEvent event){
            if (event.getEntityLiving() instanceof EntityPlayerMP){
                EntityPlayer player = (EntityPlayerMP)event.getEntityLiving();
                if (player.getHeldItemMainhand().getItem() ==ModItems.WHITE_FESTHERS)
                {
                    if (!MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(player,player.getHeldItemMainhand(),null).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.UPDATE))){
                        player.addPotionEffect(new PotionEffect(ModPotions.SLOWFALL, 20));
                    }
                }
                ItemStack itemStack=player.getHeldItemMainhand();
                if (itemStack.getItem()== com.Hileb.moremomostories.common.world.item.ModItems.ITEM_CARD_CONTAINER){
                    ItemStackHandler stackHandler= ItemCardContainer.getItemStackHandler(itemStack);
                    for(int i1=0;i1<stackHandler.getSlots();i1++){
                        ItemStack stack1=stackHandler.getStackInSlot(i1);
                        if (stack1.getItem() ==ModItems.WHITE_FESTHERS)
                        {
                            if (!MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(player,stack1,null).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.UPDATE))){
                                player.addPotionEffect(new PotionEffect(ModPotions.SLOWFALL, 20));
                            }
                        }
                    }
                    ItemCardContainer.setItemStackHandler(itemStack,stackHandler);
                }
            }
        }
    }
    public static class TheLampofTiamat_1{
        public static void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
            if (!worldIn.isRemote) {
                EntityPlayer player = (EntityPlayer) entityIn;
                int range = 20;
                List<Entity> list = worldIn.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(player.posX - range, player.posY - range, player.posZ - range, player.posX + range, player.posY + range, player.posZ + range));
                for (Entity en : list) {
                    if (worldIn.getWorldTime() % 20 == 1) {
                        if (player.getHeldItemOffhand().getItem() == ModItems.THE_LAMP_OF_TIAMAT_1 && player.getHeldItemOffhand().getItemDamage()==1) {
                           if (!MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(player,stack,(EntityPlayer)en).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.UPDATE))){
                               EntityPlayer player1 = (EntityPlayer) en;
                               player1.heal(1);
                           }
                        }
                    }
                }
            }
        }
    }
    public static class EternalKingship{
        public static void AnvilRepairEvent(AnvilRepairEvent event) {
            EntityPlayer player = event.getEntityPlayer();
            for (int i = 0; i < player.inventory.getSizeInventory(); ++i) {
                ItemStack itemStack = player.inventory.getStackInSlot(i);
                if (itemStack.getItem() == ModItems.ETERNA_KINGSHIP) {
                    if (!player.world.isRemote) {
                        {
                            if (!MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(player,itemStack,null).setMessage("craft")))
                            event.setBreakChance(0.0f);
                        }
                    }
                }
                if (itemStack.getItem()== com.Hileb.moremomostories.common.world.item.ModItems.ITEM_CARD_CONTAINER){
                    ItemStackHandler stackHandler= ItemCardContainer.getItemStackHandler(itemStack);
                    for(int i1=0;i1<stackHandler.getSlots();i1++){
                        ItemStack stack1=stackHandler.getStackInSlot(i1);
                        if (stack1.getItem() == ModItems.ETERNA_KINGSHIP) {
                            if (!player.world.isRemote) {
                                {
                                    if (!MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(player,stack1,null).setMessage("craft")))
                                        event.setBreakChance(0.0f);
                                }
                            }
                        }
                    }
                    ItemCardContainer.setItemStackHandler(itemStack,stackHandler);
                }
            }
        }
        public static void hurt(LivingHurtEvent event) {
            World world = event.getEntity().world;
            if (!world.isRemote) {
                EntityLivingBase attack = event.getEntityLiving();
                if (attack instanceof EntityPlayer) {
                    EntityPlayer Player = (EntityPlayer) attack;
                    for (int i = 0; i < Player.inventory.getSizeInventory(); ++i) {
                        ItemStack itemStack = Player.inventory.getStackInSlot(i);
                        if (itemStack.getItem() == ModItems.ETERNA_KINGSHIP) {
                            if (event.getSource().equals(DamageSource.FALL)) {
                                if (!MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(Player,itemStack,null).setMessage("hurt")));
                                event.setAmount(event.getAmount() * 1.5F);
                            }
                        }

                        if (itemStack.getItem()== com.Hileb.moremomostories.common.world.item.ModItems.ITEM_CARD_CONTAINER){
                            ItemStackHandler stackHandler= ItemCardContainer.getItemStackHandler(itemStack);
                            for(int i1=0;i1<stackHandler.getSlots();i1++){
                                ItemStack stack1=stackHandler.getStackInSlot(i1);
                                if (stack1.getItem() == ModItems.ETERNA_KINGSHIP) {
                                    if (event.getSource().equals(DamageSource.FALL)) {
                                        if (!MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(Player,stack1,null).setMessage("hurt")));
                                        event.setAmount(event.getAmount() * 1.5F);
                                    }
                                }
                            }
                            ItemCardContainer.setItemStackHandler(itemStack,stackHandler);
                        }


                    }
                }
            }
        }
    }
    public static class FragmentsOfFalseGods{
        public static ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand) {
            if (!world.isRemote){
                if (MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(player,player.getHeldItem(hand),null).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.RIGHT_CLICK)))return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
                Item card= MoMoCards.getCard(new Random().nextInt(MoMoCards.getCount()));
                player.getHeldItem(hand).shrink(1);
                ModCommands.give(player,new ItemStack(card));
                return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));

            }
            return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
        }
    }

    public static class NordBlacksmithWorkshop{
        public static EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
            ItemStack stack=player.getHeldItem(hand);
            if (!world.isRemote) {
                if (stack.getItem() == ModItems.NORD_BLACKSMITH_WORKSHOP) {
                    Block block = world.getBlockState(pos).getBlock();
                    if ( block == Blocks.ANVIL)
                    {
                        if (player.isSneaking()) {
                            if (!MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(player,stack,null).setItemUseType(new MoMoCardEffortEvent.OnItemUseType(player,world,pos,hand,facing,hitX,hitY,hitZ)).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.USE))){
                                setBlock(world, pos, ModBlocks.MOD_ENVIL);
                                return EnumActionResult.SUCCESS;
                            }
                        }
                    }
                }
            }
            return EnumActionResult.PASS;
        }
        private static void setBlock(World worldIn, BlockPos pos, Block block){
            worldIn.setBlockState(pos,block.getDefaultState());
        }
        public static void Cooldown (EntityPlayer player, EnumHand hand,int time){
            player.getCooldownTracker().setCooldown(player.getHeldItem(hand).getItem(), time );

        }
    }
    public static class LettertoKerry{
        public static ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand) {

            ItemStack item = player.getHeldItem(hand);
            if (!world.isRemote) {
                if (player.isSneaking()) {
                    if (player.getHeldItemMainhand().getItem() == ModItems.LETTER_TO_KERRY) {
                        if (!MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(player,item,null).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.RIGHT_CLICK))){
                            item.setCount(item.getCount() - 1);
                            player.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(ModItems.DEAR_Karen));
                            player.sendMessage(new TextComponentTranslation("LettertoKerryText"));
                            player.entityDropItem(new ItemStack(ModItems.DAYLIGHT, 1), 0);
                            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, item);
                        }
                    }
                }
            }
            return new ActionResult<ItemStack>(EnumActionResult.PASS, item);
        }
    }
    public static class LucyAxe{
        public static void onLogin(PlayerEvent.PlayerLoggedInEvent event)
        {
            EntityPlayer player = event.player;
            for (int i = 0; i < player.inventory.getSizeInventory(); ++i) {
                ItemStack itemStack = player.inventory.getStackInSlot(i);
                if (itemStack.getItem() == ModItems.LUCY_THE_AXE)
                {

                    if (!MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(player,itemStack,null).setMessage("login")))
                    itemStack.setItemDamage(0);
                }
                if (itemStack.getItem()== com.Hileb.moremomostories.common.world.item.ModItems.ITEM_CARD_CONTAINER){
                    ItemStackHandler stackHandler= ItemCardContainer.getItemStackHandler(itemStack);
                    for(int i1=0;i1<stackHandler.getSlots();i1++){
                        ItemStack stack1=stackHandler.getStackInSlot(i1);
                        if (stack1.getItem() == ModItems.LUCY_THE_AXE)
                        {

                            if (!MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(player,stack1,null).setMessage("login")))
                                itemStack.setItemDamage(0);
                        }
                    }
                    ItemCardContainer.setItemStackHandler(itemStack,stackHandler);
                }

            }
        }
    }
    public static class Month{
        public static ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand) {
            ItemStack itemStack=player.getHeldItemMainhand();
            if (!world.isRemote)
            {
                if (player.getHeldItemMainhand().getItem() == ModItems.MONTH)
                {
                    if (player.isSneaking()) {
                        if (player.getHeldItemOffhand().getItem() == Items.BOW) {
                            if (!MinecraftForge.EVENT_BUS.post(new MoMoCardEffortEvent(player,player.getHeldItemMainhand(),null).setMessage(MoMoCardEffortEvent.ExtraMessage.Common.RIGHT_CLICK))){
                               ItemStack item = player.getHeldItemOffhand();
                               item.setCount(item.getCount() - 1);
                                //player.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, new ItemStack(ModItems.LUNA_HUNTING));
                                //bug fix by hileb
                               ModCommands.give(player,new ItemStack(ModItems.LUNA_HUNTING));
                               return new ActionResult<ItemStack>(EnumActionResult.SUCCESS,itemStack);
                        }}
                    }
                }
            }
            return new ActionResult<ItemStack>(EnumActionResult.PASS,itemStack);
        }
    }
    /**
     * Particle: {@link ModEventReader#onUseBow(LivingEntityUseItemEvent.Tick)}
     */
    public static class LunaHunting{
        public static void luna_hunting(LivingHurtEvent event) {
            //因为Hileb使用幻影矢，这里不再需要攻击加成，且fixBug:
            /*
                Hileb 2023/8/11 16:19:23
                突然发现其实月神狩猎近战效果更好
                因为没有判断伤害类型

                煮饭婆 2023/8/11 16:20:06
                啊？

                Hileb 2023/8/11 16:20:09
                近战翻5倍
                [菜汪]请使用最新版手机QQ体验新功能

                煮饭婆 2023/8/11 16:20:27
                但也就5攻击吧

                Hileb 2023/8/11 16:21:05
                不知道为什么
                我这里一拳捶死僵尸
                等一下
                加成还不是看手
                煮饭婆 2023/8/11 16:21:56
                我记得似乎写了对亡灵加伤的

                Hileb 2023/8/11 16:21:57
                是检测背包
                背包有多少把就翻多少次5倍

                煮饭婆 2023/8/11 16:22:42
                艹，指数倍了

                Hileb 2023/8/11 16:23:27
                叠几个一拳捶死末影龙

                煮饭婆 2023/8/11 16:24:08
                写叉劈了
             */
//            World world = event.getEntity().world;
//            if (!world.isRemote) {
//                EntityLivingBase attacker = (EntityLivingBase)event.getSource().getTrueSource();
//                if (attacker instanceof EntityPlayer) {
//                    EntityPlayer player = (EntityPlayer)attacker;
//
////                    for(int i = 0; i < Player.field_71071_by.func_70302_i_(); ++i) {
////                        ItemStack itemStack = Player.field_71071_by.func_70301_a(i);
////                        if (itemStack.func_77973_b() == ModItems.LUNA_HUNTING && !world.func_72935_r()) {
////                            event.setAmount(event.getAmount() * 5.0F);
////                        }
////                    }
//                }
//            }

        }
        public static ItemStack findAmmo(EntityPlayer player)
        {
            if ((player.getHeldItem(EnumHand.OFF_HAND).getItem() instanceof ItemArrow))
            {
                return player.getHeldItem(EnumHand.OFF_HAND);
            }
            else if (player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemArrow)
            {
                return player.getHeldItem(EnumHand.MAIN_HAND);
            }
            else
            {
                for (int i = 0; i < player.inventory.getSizeInventory(); ++i)
                {
                    ItemStack itemstack = player.inventory.getStackInSlot(i);

                    if (itemstack.getItem() instanceof ItemArrow)
                    {
                        return itemstack;
                    }
                }

                return ItemStack.EMPTY;
            }
        }
        public static void onPlayerStoppedUsing(ItemBow item, ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
            if (entityLiving instanceof EntityPlayer)
            {
                EntityPlayer entityplayer = (EntityPlayer)entityLiving;
                final long time=worldIn.getWorldTime();
                boolean flagNight = time % 24000L > 13850L && time % 24000L < 23000L;
                boolean flag = entityplayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
                ItemStack itemstack = findAmmo(entityplayer);

                int i = item.getMaxItemUseDuration(stack) - timeLeft;
                i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, entityplayer, i, !itemstack.isEmpty() || flag);
                if (i < 0) return;

                if (flagNight && i>=1){

                    final int maxEffectLong=2048;

                    Vec3d playerShootPos=new Vec3d(entityplayer.posX, entityLiving.posY+entityplayer.getEyeHeight(), entityplayer.posZ);
                    Vec3d lookV=entityplayer.getLookVec();
                    double lookX=lookV.x;
                    double lookY=lookV.y;
                    double lookZ=lookV.z;

                    worldIn.playSound(null,playerShootPos.x, playerShootPos.y,playerShootPos.z,
                            SoundEvents.ENTITY_ENDERMEN_TELEPORT,
                            SoundCategory.HOSTILE,2.0F,1.0F);

                    Vec3d playerLook= new Vec3d(lookX*maxEffectLong,lookY*maxEffectLong,lookZ*maxEffectLong);
                    entityplayer.rayTrace(2048,0);
                    //List<RayTraceResult> results= com.Hileb.moremomostories.common.util.math.MathHelper.raytraceAll(worldIn,playerShootPos,playerShootPos.add(playerLook),false);
                    RayTraceResult result= com.Hileb.moremomostories.common.util.math.MathHelper.rayTraceShoot(entityplayer,worldIn,playerShootPos,playerShootPos.add(playerLook),0.5);



                    VirtueSpace virtueSpace=new VirtueSpace(playerShootPos);
                    for(int o=0;o<=maxEffectLong*4;o++){
                        virtueSpace.putPos(new Vec3d(0,o,0));
                        if (o%8==0){
                            for(int os=0;os<=32;os++){
                                float angle= (float) Math.PI/16*os;
                                float x=MathHelper.cos(angle);
                                float z=MathHelper.sin(angle);
                                virtueSpace.putPos(new Vec3d(x,o,z));
                            }
                        }
                    }
                    virtueSpace.scale(1,0.25d,1);
                    virtueSpace.rotation(-entityplayer.rotationPitch*0.017453292F-(float)Math.PI/2f,-entityplayer.rotationYaw* 0.017453292F);
                    for(Vec3d pos:virtueSpace.vec3dList){
                        //MoreMoMoSrories.logger.warn("{} {} {}",pos.x+playerShootPos.x,pos.y+playerShootPos.y,pos.z+playerShootPos.z);
                        worldIn.spawnParticle(EnumParticleTypes.END_ROD,
                                pos.x+playerShootPos.x,pos.y+playerShootPos.y,pos.z+playerShootPos.z,
                                0,0,0
                        );
                    }
                    if (!worldIn.isRemote && result!=null) {
                        boolean fire = EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0;
                        //MoreMoMoSrories.logger.warn(result.toString());
                        if (result.typeOfHit == RayTraceResult.Type.BLOCK) {
                            if (fire){
                                BlockPos pos = result.getBlockPos();
                                pos = pos.offset(result.sideHit);
                                if (worldIn.isAirBlock(pos)) {
                                    worldIn.setBlockState(pos, Blocks.FIRE.getDefaultState(), 11);
                                }
                            }
                        } else if (result.typeOfHit == RayTraceResult.Type.ENTITY) {
                            double speedCount=stack.getMaxItemUseDuration() - entityplayer.getItemInUseCount();
                            if (speedCount>=92)speedCount=92;
                            speedCount=speedCount*speedCount/256;
                            if (speedCount<=10)speedCount=10;

                            float damage = (float) speedCount;
                            int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);

                            if (j > 0) {
                                damage += (double) j * 0.5D + 0.5D;
                            }
                            Entity entity = result.entityHit;
                            entity.attackEntityFrom(DamageSource.causePlayerDamage(entityplayer), damage);
                            if (fire)entity.setFire(100);
                        }
                    }
                    entityplayer.addStat(StatList.getObjectUseStats(item));
                    return;
                } else if (!itemstack.isEmpty() || flag )
                {
                    if (itemstack.isEmpty())
                    {
                        itemstack = new ItemStack(Items.ARROW);
                    }

                    float f = getArrowVelocity(i);

                    if ((double)f >= 0.1D)
                    {
                        boolean flag1 = entityplayer.capabilities.isCreativeMode || (itemstack.getItem() instanceof ItemArrow && ((ItemArrow) itemstack.getItem()).isInfinite(itemstack, stack, entityplayer));

                        if (!worldIn.isRemote)
                        {
                            ItemArrow itemarrow = (ItemArrow)(itemstack.getItem() instanceof ItemArrow ? itemstack.getItem() : Items.ARROW);
                            EntityArrow entityarrow = itemarrow.createArrow(worldIn, itemstack, entityplayer);
                            entityarrow.shoot(entityplayer, entityplayer.rotationPitch, entityplayer.rotationYaw, 0.0F, f * 3.0F, 1.0F);

                            if (f == 1.0F)
                            {
                                entityarrow.setIsCritical(true);
                            }

                            int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);

                            if (j > 0)
                            {
                                entityarrow.setDamage(entityarrow.getDamage() + (double)j * 0.5D + 0.5D);
                            }

                            int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);

                            if (k > 0)
                            {
                                entityarrow.setKnockbackStrength(k);
                            }

                            if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0)
                            {
                                entityarrow.setFire(100);
                            }

                            stack.damageItem(1, entityplayer);

                            if (flag1 || entityplayer.capabilities.isCreativeMode && (itemstack.getItem() == Items.SPECTRAL_ARROW || itemstack.getItem() == Items.TIPPED_ARROW))
                            {
                                entityarrow.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;
                            }

                            worldIn.spawnEntity(entityarrow);
                        }

                        worldIn.playSound(null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (RandomManager.random.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

                        if (!flag1 && !entityplayer.capabilities.isCreativeMode)
                        {
                            itemstack.shrink(1);

                            if (itemstack.isEmpty())
                            {
                                entityplayer.inventory.deleteStack(itemstack);
                            }
                        }
                        entityplayer.addStat(StatList.getObjectUseStats(item));
                    }
                }
            }
        }
    }




}