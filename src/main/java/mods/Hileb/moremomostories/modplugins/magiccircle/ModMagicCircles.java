package mods.Hileb.moremomostories.modplugins.magiccircle;

import mods.Hileb.forgedmomo.announces.ModPlugin;
import net.minecraftforge.fml.common.LoaderState;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/11/17 20:10
 **/
@ModPlugin(state = LoaderState.POSTINITIALIZATION,modid = net.mcreator.magiccircle.MagicCircle.MODID)
public class ModMagicCircles{
//    public static final MagicCircleSixstars Magic_Circle_of_the_fruit_of_the_flowing_years =new MagicCircleSixstars(
//            ModItems.THE_FRUIT_OF_THE_FLOWING_YEARS
//            ,ModItems.ID_SANDPAPER,ModItems.ID_SAND
//            ,ModItems.ID_SAND,ModItems.ID_SANDPAPER
//            ,ModItems.THE_FRUIT_OF_THE_FLOWING_YEARS){
//        @Override
//        public void doCircle(TileEntityLockableLoot block) {
//            //TileEntityLockableLoot block=(TileEntityLockableLoot)world.getTileEntity(pos);
//            for (EntityPlayer player : block.getWorld().getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(new BlockPos(block.getPos().getX() - 3, block.getPos().getY() - 3, block.getPos().getZ() - 3), new BlockPos(block.getPos().getX() + 3, block.getPos().getY() + 3, block.getPos().getZ() + 3)))) {
//                if (!player.isSneaking()) {
//                    player.attackEntityFrom(DamageSource1.TIME, 5);
//                    //岁月阵
//                }
//            }
//        }
//    };
//    public static final MagicCircleSixstars Magic_Circle_of_Sword =new MagicCircleSixstars(
//            ModItems.THE_FRUIT_OF_THE_FLOWING_YEARS
//            ,ModItems.ID_SANDPAPER,ModItems.ID_SAND
//            ,ModItems.ID_SAND,ModItems.ID_SANDPAPER
//            ,ModItems.THE_FRUIT_OF_THE_FLOWING_YEARS){
//        @Override
//        public boolean compare(MagicCircleSixstars mag) {
//            if (mag.get(0) instanceof ItemSword){
//                if (mag.get(1) instanceof ItemSword){
//                    if (mag.get(2) instanceof ItemSword){
//                        if (mag.get(3) instanceof ItemSword){
//                            if (mag.get(4) instanceof ItemSword){
//                                if (mag.get(5) instanceof ItemSword){
//                                    return true;
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//            return false;
//        }
//        public float getDamage(TileEntityLockableLoot block){
//            float damage=0f;
//            if (block.getStackInSlot(0).getItem() instanceof ItemSword){
//                if (block.getStackInSlot(1).getItem() instanceof ItemSword){
//                    if (block.getStackInSlot(2).getItem() instanceof ItemSword){
//                        if (block.getStackInSlot(3).getItem() instanceof ItemSword){
//                            if (block.getStackInSlot(4).getItem() instanceof ItemSword){
//                                if (block.getStackInSlot(5).getItem() instanceof ItemSword){
//                                    damage+=((ItemSword) block.getStackInSlot(0).getItem()).getAttackDamage();
//                                    damage+=((ItemSword) block.getStackInSlot(1).getItem()).getAttackDamage();
//                                    damage+=((ItemSword) block.getStackInSlot(2).getItem()).getAttackDamage();
//                                    damage+=((ItemSword) block.getStackInSlot(3).getItem()).getAttackDamage();
//                                    damage+=((ItemSword) block.getStackInSlot(4).getItem()).getAttackDamage();
//                                    damage+=((ItemSword) block.getStackInSlot(5).getItem()).getAttackDamage();
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//            return damage/6f/4f;
//        }
//
//        @Override
//        public void doCircle(TileEntityLockableLoot block) {
//            //TileEntityLockableLoot block=(TileEntityLockableLoot)world.getTileEntity(pos);
//            for (EntityLivingBase player : block.getWorld().getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(new BlockPos(block.getPos().getX() - 3, block.getPos().getY() - 3, block.getPos().getZ() - 3), new BlockPos(block.getPos().getX() + 3, block.getPos().getY() + 3, block.getPos().getZ() + 3)))) {
//                if (!player.isSneaking() && !(player instanceof EntityPlayer)) {
//                    player.attackEntityFrom(ModDamageSources.SWORD, getDamage(block));
//                    //剑阵
//                }
//            }
//        }
//    };
//
//    public static final MagicCircleSixstars Magic_Circle_of_Xe =new MagicCircleSixstars(
//            mods.Hileb.moremomostories.common.world.item.ModItems.ITEM_XE
//            , mods.Hileb.moremomostories.common.world.item.ModItems.ITEM_XE, mods.Hileb.moremomostories.common.world.item.ModItems.ITEM_XE
//            , mods.Hileb.moremomostories.common.world.item.ModItems.ITEM_XE, mods.Hileb.moremomostories.common.world.item.ModItems.ITEM_XE
//            , mods.Hileb.moremomostories.common.world.item.ModItems.ITEM_XE){
//        @Override
//        public void doCircle(TileEntityLockableLoot block) {
//            //TileEntityLockableLoot block=(TileEntityLockableLoot)world.getTileEntity(pos);
//            //氙石阵：红色风暴
//            if (ItemXe.isRed(block.getStackInSlot(0))){
//                if (ItemXe.isRed(block.getStackInSlot(1))){
//                    if (ItemXe.isRed(block.getStackInSlot(2))){
//                        if (ItemXe.isRed(block.getStackInSlot(3))){
//                            if (ItemXe.isRed(block.getStackInSlot(4))){
//                                if (ItemXe.isRed(block.getStackInSlot(5))){
//                                    for (EntityPlayer player : block.getWorld().getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(new BlockPos(block.getPos().getX() - 3, block.getPos().getY() - 3, block.getPos().getZ() - 3), new BlockPos(block.getPos().getX() + 3, block.getPos().getY() + 3, block.getPos().getZ() + 3)))) {
//                                        if (!player.isSneaking()) {
//                                            player.heal(1);
//                                            player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH,20,3));
//                                            //红色风暴
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//            //氙石阵：蓝色妖姬
//            if (ItemXe.isBlue(block.getStackInSlot(0))){
//                if (ItemXe.isBlue(block.getStackInSlot(1))){
//                    if (ItemXe.isBlue(block.getStackInSlot(2))){
//                        if (ItemXe.isBlue(block.getStackInSlot(3))){
//                            if (ItemXe.isBlue(block.getStackInSlot(4))){
//                                if (ItemXe.isBlue(block.getStackInSlot(5))){
//                                    for (EntityPlayer player : block.getWorld().getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(new BlockPos(block.getPos().getX() - 3, block.getPos().getY() - 3, block.getPos().getZ() - 3), new BlockPos(block.getPos().getX() + 3, block.getPos().getY() + 3, block.getPos().getZ() + 3)))) {
//                                        if (!player.isSneaking()) {
//                                            player.addPotionEffect(new PotionEffect(MobEffects.SPEED,20,5));
//                                            //蓝色妖姬
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    };
}
