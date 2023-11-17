package mods.Hileb.moremomostories.common.listener;

import mods.Hileb.moremomostories.MoreMoMoSrories;
import mods.Hileb.moremomostories.mods.ModLoadingPlugin;
import mods.Hileb.forgedmomo.utils.math.VirtueSpace;
import mods.Hileb.moremomostories.common.world.advancements.Advancementkeys;
import mods.Hileb.moremomostories.common.world.advancements.ModAdvancementsInit;
import mods.Hileb.moremomostories.common.world.entity.entity.living.EntityGoldenGuide;
import mods.Hileb.moremomostories.common.world.entity.entity.living.boss.EntityGoldenGuideBoss;
import com.gq2529.momostories.item.ModItems;
import com.gq2529.momostories.item.bows.LunaHunting;
import mods.Hileb.moremomostories.mixin.momostories.event.CardFunction;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod.EventBusSubscriber(modid= MoreMoMoSrories.MODID)
public class ModCommonEventListener {

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event){
        if (!event.player.world.isRemote){
            if (ModLoadingPlugin.isLoaded_SlashBlade || ModLoadingPlugin.isLoaded_MagicCircle || ModLoadingPlugin.isLoaded_AddPotion){
                ModAdvancementsInit.giveAdvancement(event.player, Advancementkeys.AD_OMOD_ROOT);
            }
        }
    }

    @SubscribeEvent
    public static void onBuildPumpkin(BlockEvent.PlaceEvent event){
        World world=event.getWorld();
        if (!world.isRemote){
            BlockPos pos=event.getPos();
            if (world.getBlockState(pos.down()).getBlock()== Blocks.GOLD_BLOCK){
                if (world.getBlockState(pos.down(2)).getBlock()== Blocks.GOLD_BLOCK){
                    if (world.getBlockState(pos).getBlock()==Blocks.PUMPKIN){
                        BlockPos pos1=pos.down(2);
                        EntityGoldenGuide entityGoldenGuide=new EntityGoldenGuide(world);
                        entityGoldenGuide.setPosition(pos1.getX(),pos1.getY(),pos1.getZ());
                        world.spawnEntity(entityGoldenGuide);

                        world.setBlockState(pos,Blocks.AIR.getDefaultState(),3);
                        world.setBlockState(pos.down(),Blocks.AIR.getDefaultState(),3);
                        world.setBlockState(pos.down(2),Blocks.AIR.getDefaultState(),3);
                    }
                    if (world.getBlockState(pos).getBlock()==Blocks.SKULL){
                        BlockPos pos1=pos.down(2);
                        EntityGoldenGuideBoss entityGoldenGuide=new EntityGoldenGuideBoss(world);
                        entityGoldenGuide.setPosition(pos1.getX(),pos1.getY(),pos1.getZ());
                        world.spawnEntity(entityGoldenGuide);

                        world.setBlockState(pos,Blocks.AIR.getDefaultState(),3);
                        world.setBlockState(pos.down(),Blocks.AIR.getDefaultState(),3);
                        world.setBlockState(pos.down(2),Blocks.AIR.getDefaultState(),3);
                    }
                }
            }
        }
    }
    
    /**Stop Using Real Effective :
     * {@link CardFunction.LunaHunting#onPlayerStoppedUsing(ItemBow, ItemStack, World, EntityLivingBase, int)}
    * */
    @SubscribeEvent
    public static void onUseBow(LivingEntityUseItemEvent.Tick event){
        if (event.getEntity()==null)return;
        if (event.getItem().getItem()== ModItems.LUNA_HUNTING){
            EntityLivingBase entity=event.getEntityLiving();
            final long time = entity.world.getWorldTime() + 24000L;
            boolean flagNight = (time % 24000L > 13850L && time % 24000L < 23000L);

            if (flagNight){
                final float rotationPitch=entity.rotationPitch* -0.017453292F-(float)Math.PI/2f;
                final float rotationYaw=-entity.rotationYaw* 0.017453292F;
                ItemStack stack=event.getItem();
                double bar=(float)(stack.getMaxItemUseDuration() - entity.getItemInUseCount()) / 20.0F;
                bar=1/(bar*bar)+0.3f;
                Vec3d lookVec=entity.getLookVec();
                double speedCount=stack.getMaxItemUseDuration() - entity.getItemInUseCount();
                if (speedCount>=92)speedCount=92;
                speedCount=10000-speedCount*speedCount;
                speedCount=Math.sqrt(speedCount)/10f;
                speedCount=(int)speedCount;
                if (speedCount<=4)speedCount=4;
                boolean speedFlag=false;

                if (entity.world.getTotalWorldTime()%speedCount==0)speedFlag=true;
                Vec3d shootCenter=new Vec3d(entity.posX,entity.posY+entity.getEyeHeight(),entity.posZ);
                //circle
                if (entity.world.getTotalWorldTime()%2==0){
                    VirtueSpace circleSpace=new VirtueSpace(shootCenter);
                    for(int i=0;i<256;i++){
                        float angle= (float) Math.PI/128*i;
                        float x=MathHelper.cos(angle);
                        float z=MathHelper.sin(angle);
                        circleSpace.putPos(new Vec3d(x,0,z));
                    }
                    circleSpace.scale(bar,bar,bar);
                    //MathHelper.nextFloat(RandomManager.random,0,(float) Math.PI)
                    circleSpace.rotation(rotationPitch,rotationYaw);
                    for(Vec3d point:circleSpace.vec3dList){
                        Vec3d pos=point.add(circleSpace.centerPos);
                        entity.world.spawnParticle(EnumParticleTypes.END_ROD,
                                pos.x,pos.y,pos.z,
                                point.x,point.y,point.z
                        );
                    }
                }
                //the cross
                if (entity.world.getTotalWorldTime()%2==0){
                    VirtueSpace crossSpace=new VirtueSpace(shootCenter);
                    Vec3d theCross=new Vec3d(0,1,0);
                    theCross=theCross.rotatePitch(entity.rotationPitch* -0.017453292F-(float)Math.PI/2f).rotateYaw(-entity.rotationYaw* 0.017453292F).add(shootCenter);
                    entity.world.spawnParticle(EnumParticleTypes.END_ROD,
                            theCross.x,theCross.y,theCross.z,
                            0,0,0
                    );
                }
                if (speedFlag){
                    VirtueSpace speedSpace=new VirtueSpace(shootCenter);

                    for(int j=0;j<16;j++){
                        float angle= (float) Math.PI/8*j;
                        float x=MathHelper.cos(angle);
                        float z=MathHelper.sin(angle);
                        speedSpace.putPos(new Vec3d(x,0,z));
                    }

                    speedSpace.scale(0.5f,0.25f,0.5f);
                    speedSpace.rotation(rotationPitch,rotationYaw);
                    for(Vec3d point:speedSpace.vec3dList){
                        Vec3d pos=point.add(speedSpace.centerPos);
                        entity.world.spawnParticle(EnumParticleTypes.END_ROD,
                                pos.x,pos.y,pos.z,
                                lookVec.x*0.125, lookVec.y*0.125,lookVec.z*0.125
                        );
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public static void onHoldBow(TickEvent.WorldTickEvent event){
        World world=event.world;
        if (!world.isRemote){
            if (!world.isDaytime()){
                if (world.getTotalWorldTime()%20==0){
                    for(EntityPlayer player:world.playerEntities){
                        for(int i=0;i<player.inventory.getSizeInventory();i++){
                            ItemStack stack=player.inventory.getStackInSlot(i);
                            if (stack.getItem() instanceof LunaHunting){
                                player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION,240));
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
}
