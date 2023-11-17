package mods.Hileb.moremomostories.common.world.entity.entity.living.boss.skill;

import mods.Hileb.moremomostories.common.world.potion.myBuff.PotionBaKin;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketTitle;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class BossSkillBakin extends BossSkill {
    public BossSkillBakin(){
        super();
    }
    @Override
    public void doSpacialAttack(EntityLivingBase living) {
        World world=living.world;
        if (!world.isRemote){
            for(EntityLivingBase player:getLivings(living)){
                PotionBaKin.putEffect(player,1);
                if (player instanceof EntityPlayerMP){
                    ((EntityPlayerMP)player).connection.sendPacket(new SPacketTitle(SPacketTitle.Type.ACTIONBAR,new TextComponentTranslation("com.hileb.momo.lang.skill.bakin"),20,40,20));
                }
                if (player instanceof EntityAnimal){
                    EntityAnimal animal=(EntityAnimal) player;
                    animal.setInLove(null);
                }
            }
        }
    }
    public List<EntityLivingBase> getLivings(EntityLivingBase living){
        return living.world.getEntitiesWithinAABB(EntityLivingBase.class,new AxisAlignedBB(new BlockPos(living.posX-8,living.posY-8,living.posZ-8),new BlockPos(living.posX+8,living.posY+8,living.posZ+8)));
    }
    @Override
    public boolean apply(EntityLivingBase var2) {
        Random random=new Random(var2.hashCode()+var2.world.hashCode()+var2.world.getTotalWorldTime()+var2.getHeldItemMainhand().hashCode());
        if (random.nextInt(1000)<=5)return true;
        else return false;
    }
}
