package mods.Hileb.moremomostories.modplugins.slashblade.specialattack;

import mods.Hileb.moremomostories.common.world.entity.entity.projectile.EntityRain;
import mods.flammpfeil.slashblade.specialattack.SpecialAttackBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.Random;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/8/13 11:37
 **/
public class SARain extends SpecialAttackBase {
    @Override
    public String toString() {
        return "momo.sa_rain";
    }

    @Override
    public void doSpacialAttack(ItemStack itemStack, EntityPlayer entityPlayer) {
        Random random=new Random(entityPlayer.world.getSeed()+entityPlayer.getUniqueID().hashCode()+(int)entityPlayer.posX);
        if (!entityPlayer.world.isRemote){
            for(int y=0;y<=5;y++){//生成雨滴
                for(int i=0;i<=9;i++){
                    for(int j=0;j<=9;j++){
                        EntityRain entity=new EntityRain(entityPlayer.world,itemStack,entityPlayer);
                        entity.setPosition(entityPlayer.posX-4+i+((double) (random.nextInt(10)/10)-0.5f),entityPlayer.posY+y+((double) (random.nextInt(10)/10)-0.5f),entityPlayer.posZ-4+j+((double) (random.nextInt(10)/10)-0.5f));
                        entityPlayer.world.spawnEntity(entity);
                    }
                }
            }
        }
    }
}
