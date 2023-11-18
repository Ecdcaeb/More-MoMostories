package mods.Hileb.moremomostories.common.world.item.myItems;

import mods.Hileb.moremomostories.MoreMoMoSrories;
import mods.Hileb.moremomostories.common.world.item.interfaces.IEntityItemX;
import mods.Hileb.moremomostories.common.world.item.ItemSwordBase;
import mods.Hileb.forgedmomo.utils.nbt.IDLNBTUtil;
import mods.Hileb.moremomostories.common.capabilities.nametag.named.skillTag.ItemSkillList;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public class ItemEndMemorySword extends ItemSwordBase implements IEntityItemX {
    public static Field attackTarget;
    public ItemEndMemorySword(String name){
        super(name, EnumHelper.addToolMaterial("toolmaterialEndMemory",0,2048,8.0f,1.0f,30));
        MinecraftForge.EVENT_BUS.register(this);
        for(Field f:EntityLiving.class.getDeclaredFields()){
            if (f.getType()==EntityLivingBase.class){
                attackTarget=f;
                attackTarget.setAccessible(true);
                break;
            }
        }
    }
    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        if (player instanceof EntityPlayerMP && entity instanceof EntityLiving){
            EntityLiving living=(EntityLiving)entity;
            if (living.getAttackTarget()!=null){
                living.setAttackTarget(null);
                IDLNBTUtil.SetInt(entity,"com.hileb.momo.nbt.hasNoTarget",25);
                if (entity instanceof EntityTameable){
                    EntityTameable tameable=(EntityTameable)entity;
                    tameable.setOwnerId(null);
                }
            }
        }
        return super.onLeftClickEntity(stack, player, entity);
    }
    @SubscribeEvent
    public void removeTarget(LivingSetAttackTargetEvent event){

        if (event.getEntityLiving() instanceof EntityLiving){
            if (!event.getEntityLiving().world.isRemote){
                int p=IDLNBTUtil.GetInt(event.getEntityLiving(),"com.hileb.momo.nbt.hasNoTarget",-1);
                if (p>=0){
                    IDLNBTUtil.SetInt(event.getEntityLiving(),"com.hileb.momo.nbt.hasNoTarget",p-1);

                    EntityLiving living=((EntityLiving)event.getEntityLiving());
                    try{
                        try{
                            attackTarget.set(living,null);
                        }catch (NullPointerException e){
                            MoreMoMoSrories.LOGGER.error(String.format("on setting living target by %s [entity:%s]",this.getUnlocalizedName(),living.getUniqueID().toString()),(Throwable) e);
                        }
                    }catch (IllegalAccessException e){
                        MoreMoMoSrories.LOGGER.error(String.format("on setting living target by %s [entity:%s]",this.getUnlocalizedName(),living.getUniqueID().toString()),(Throwable) e);
                    }
                }
            }
        }
    }

    @Override
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
        super.addInformation(stack, world, tooltip, flag);
        tooltip.add(I18n.format("tip.item.end.memory.sword.tip1.tip"));
        tooltip.add(I18n.format("tip.item.end.memory.sword.tip2.tip"));

        tooltip.add("");
        ItemSkillList.SKILL_END_MEMORY.onTooltip(tooltip);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {

    }


}
