package mods.Hileb.moremomostories.common.world.damageSource;

import mods.Hileb.moremomostories.common.world.item.myItems.ItemDevilsSword;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nullable;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/8/29 14:32
 **/
@Mod.EventBusSubscriber
public class DamageSourceWither extends EntityDamageSource {
    public static final String NAME="momostories.wither";
    public DamageSourceWither(@Nullable Entity damageSourceEntityIn)
    {
        super(NAME,damageSourceEntityIn);
    }
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onHurt(LivingHurtEvent event){
        World world=event.getEntity().world;
        if (!world.isRemote){
            if (event.getSource().getTrueSource() instanceof EntityLivingBase){
                EntityLivingBase attacker= (EntityLivingBase)event.getSource().getTrueSource();
                if (event.getSource() instanceof  DamageSourceWither){
                    float addition=event.getAmount()*0.2f;
                    attacker.heal(addition);
                }else if (attacker.getHeldItemMainhand().getItem() instanceof ItemDevilsSword){
                    event.setCanceled(true);
                    event.getEntityLiving().attackEntityFrom(DamageSourceWither.of(attacker),event.getAmount());
                }
            }
        }
    }
    public static DamageSourceWither of(EntityLivingBase attacker){
        return new DamageSourceWither(attacker);
    }
}
