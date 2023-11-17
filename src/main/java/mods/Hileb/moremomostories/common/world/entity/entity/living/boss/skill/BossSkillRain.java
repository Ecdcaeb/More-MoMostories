package mods.Hileb.moremomostories.common.world.entity.entity.living.boss.skill;

import mods.Hileb.moremomostories.common.world.entity.entity.projectile.EntityRain;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketTitle;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;

import java.util.List;
import java.util.Random;

public class BossSkillRain extends BossSkill {
    public BossSkillRain(){
        super();
    }
    public void doSpacialAttack(EntityLivingBase entity) {
        //TO DO
        //entityPlayer.sendMessage(new TextComponentString("this is sa speaking"));
        Random random=new Random(entity.world.getSeed()+entity.getUniqueID().hashCode()+(int)entity.posX);
        if (!entity.world.isRemote){
            //List<EntityRain> rains=new ArrayList<>();
            for(int y=0;y<=5;y++){//生成雨滴
                for(int i=0;i<=9;i++){
                    for(int j=0;j<=9;j++){
                        EntityRain entityRain=new EntityRain(entity.world,entity);
                        entityRain.setPosition(entity.posX-4+i+((double) (random.nextInt(10)/10)-0.5f),entity.posY+y+((double) (random.nextInt(10)/10)-0.5f),entity.posZ-4+j+((double) (random.nextInt(10)/10)-0.5f));
                        entityRain.world.spawnEntity(entityRain);

                    }
                }
            }
            for(EntityPlayer player:getPlayer(entity)){
                if (player instanceof EntityPlayerMP){
                    ((EntityPlayerMP)player).connection.sendPacket(new SPacketTitle(SPacketTitle.Type.ACTIONBAR,new TextComponentTranslation("com.hileb.momo.lang.skill.rain"),20,40,20));
                }
            }
        }
    }
    public List<EntityPlayer> getPlayer(EntityLivingBase living){
        return living.world.getEntitiesWithinAABB(EntityPlayer.class,new AxisAlignedBB(new BlockPos(living.posX-8,living.posY-8,living.posZ-8),new BlockPos(living.posX+8,living.posY+8,living.posZ+8)));
    }

    @Override
    public boolean apply(EntityLivingBase var2) {
        Random random=new Random(var2.hashCode()+var2.world.hashCode()+var2.world.getTotalWorldTime()+var2.getHeldItemMainhand().hashCode());
        if (random.nextInt(100)<=5)return true;
        else return false;
    }
}
