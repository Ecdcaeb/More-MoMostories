package com.Hileb.moremomostories.common.world.entity.entity.living;

import com.Hileb.moremomostories.MoreMoMoSrories;
import com.Hileb.moremomostories.common.world.gui.ModGuiElementLoader;
import com.Hileb.moremomostories.common.world.level.worldgen.WorldGenHelper;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Mod.EventBusSubscriber(modid = MoreMoMoSrories.MODID)
public class EntityMobChest extends EntityZombie {
    private EntityAIBase aiAttack=new EntityAINearestAttackableTarget<>(this,EntityPlayer.class, true);
    public ResourceLocation lootTable;
    public ItemStackHandler itemStackHandler=new ItemStackHandler(27);
    public EntityMobChest(World world){
       this(world,null);
    }
    public EntityMobChest(World world,ResourceLocation lootTable){
        super(world);
        this.setSize(0.8F,0.8F);
        this.setLootTable(lootTable);
        this.close();
    }


    @Override
    protected boolean shouldBurnInDay() {
        return false;
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SPAWN_REINFORCEMENTS_CHANCE).setBaseValue(0.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.9);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(9.0D);
    }
    public void open(){
        this.setNoAI(false);
        targetTasks.addTask(9,aiAttack);
    }
    public void close(){
        this.setNoAI(true);
        targetTasks.removeTask(aiAttack);
    }
    public boolean isOpen(){
        return !this.isAIDisabled();
    }
    public EntityMobChest setLootTable(ResourceLocation loottableIn){
        lootTable=loottableIn;
        return this;
    }
    public void loot(ResourceLocation location,EntityPlayer player){
        if (!player.world.isRemote){
            LootTable loottable = player.world.getLootTableManager().getLootTableFromLocation(location);
            LootContext.Builder lootcontext$builder = new LootContext.Builder((WorldServer)player.world);
            Random random=new Random(player.world.rand.nextInt());
            lootcontext$builder.withLuck(player.getLuck()).withPlayer(player);
            InventoryBasic basic=new InventoryBasic("Items", false, 27);
            loottable.fillInventory(basic, new Random(this.hashCode()+loottable.hashCode()+player.hashCode()), lootcontext$builder.build());
            for(int i=0;i<basic.getSizeInventory();i++){
                itemStackHandler.setStackInSlot(i,basic.getStackInSlot(i).copy());
            }
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        if(lootTable!=null)compound.setString("loot", this.lootTable.toString());
        else compound.setString("loot", "null");

        compound.setTag("items",itemStackHandler.serializeNBT());
        return compound;
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);

        if (compound.hasKey("loot") && !compound.getString("loot").equals("null")){
            lootTable=new ResourceLocation(compound.getString("loot"));
        }
        else lootTable=null;

        itemStackHandler.deserializeNBT( compound.getCompoundTag("items"));
    }


    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.isOpen()){
            if (!world.isRemote){
                if (this.getAttackTarget()!=null){
                    if (WorldGenHelper.getLong(this.getPosition(),this.getAttackTarget().getPosition())>=50){
                        this.setAttackTarget(null);
                    }
                }else if (world.getEntitiesWithinAABB(EntityPlayer.class,new AxisAlignedBB(this.getPosition().add(-3,-2,-3),this.getPosition().add(+3,+2,+3))).size()==0){
                    this.close();
                }
            }
        }
    }

    @Override
    public EnumActionResult applyPlayerInteraction(EntityPlayer player, Vec3d vec, EnumHand hand) {
        if (!this.isOpen()){
            this.setAttackTarget(player);
            loot(lootTable,player);
            this.open();
            player.openGui(MoreMoMoSrories.instance, ModGuiElementLoader.GUI_CHEST_FAKE,player.world,(int)this.posX,(int)this.posY,(int)this.posZ);
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.PASS;
    }

    @Override
    protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier) {
        super.dropFewItems(wasRecentlyHit, lootingModifier);
        for(int i=0;i<itemStackHandler.getSlots();i++){
            ItemStack stack=itemStackHandler.getStackInSlot(i);
            if (!stack.isEmpty()){
                if (this.rand.nextFloat()<=0.085F)this.entityDropItem(stack, 0.0F);
            }
        }

    }
    @Override
    protected boolean canDropLoot() {
        return true;
    }
    @Nullable
    @Override
    public ResourceLocation getLootTable() {
        return lootTable;
    }



    @SubscribeEvent
    public static void onGenEvent(PopulateChunkEvent.Post event){
        Random random=new Random();
        int x=event.getChunkX();
        int z=event.getChunkZ();
        World world=event.getWorld();
        if (world==null || world.loadedTileEntityList==null || world.loadedTileEntityList.isEmpty())return;
        List<BlockPos> removeList=new ArrayList<>();
        for(TileEntity entity:world.loadedTileEntityList){
            if (entity instanceof TileEntityChest){
                if (random.nextInt(100)<25){
                    BlockPos pos=entity.getPos();
                    if (((x*16)<=pos.getX() && (x*16+16)>pos.getX()) && ((z*16)<=pos.getZ() && (z*16+16)>pos.getZ())){
                        TileEntityChest chest=(TileEntityChest) entity;
                        ResourceLocation loot=chest.getLootTable();
                        if (loot!=null){
                            EntityMobChest entityMobChest=new EntityMobChest(world,loot);
                            entityMobChest.setPosition((int)pos.getX(),(int)pos.getY(),(int)pos.getZ());
                            removeList.add(pos);
                            world.spawnEntity(entityMobChest);
                            MoreMoMoSrories.LogWarning("%d %d %d",pos.getX(),pos.getY(),pos.getZ());
                        }
                    }
                }
            }
        }
        for(BlockPos pos:removeList){
            world.setBlockState(pos,Blocks.AIR.getDefaultState(),3);
        }
    }

}
