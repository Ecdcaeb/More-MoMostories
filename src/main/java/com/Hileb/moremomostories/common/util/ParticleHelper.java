package com.Hileb.moremomostories.common.util;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/8/10 15:29
 **/
public class ParticleHelper {

//    public static void spawnCircle(World world, EnumParticleTypes type, float pitch,float yaw, BlockPos centerPos, float range,int count,double centerSpeed){
//        for(int i=0;i<count;i++){
//            float angle=(float) Math.PI/count*i;
//            double x=MathHelper.cos(angle);
//            double y=0;
//            double z=MathHelper.sin(angle);
//
//            Vec3d tpVec=getSafeVec(new Vec3d(x,y,z));
//            tpVec.rotatePitch(pitch);
//            tpVec.rotateYaw(yaw);
//
//            double sX=-centerSpeed*tpVec.x;
//            double sY=-centerSpeed*tpVec.y;
//            double sZ=-centerSpeed*tpVec.z;
//
//            tpVec=getLongVec(range,tpVec);
//            world.spawnParticle(type,
//                    centerPos.getX()+tpVec.x,centerPos.getY()+tpVec.y,centerPos.getZ()+tpVec.z,
//                    sX,sY,sZ);
//        }
//    }

    public static Vec3d getLongVec(double length,Vec3d vec3d){
        double scale=length/vec3d.lengthVector();
        double x=vec3d.x*scale;
        double y=vec3d.y*scale;
        double z=vec3d.z*scale;
        return new Vec3d(x,y,z);
    }
    public static Vec3d getSafeVec(Vec3d vec3d){
        return getLongVec(1,vec3d);
    }
    public static float getPitch(Vec3d vec3d){
        float f3=(float)Math.asin(vec3d.y);
        return - f3 / 0.017453292F;
    }
    public static float getYaw(Vec3d vec3d){
        float pitch=getPitch(vec3d);
        float f2 = -MathHelper.cos(-pitch * 0.017453292F);
        double cosValue=vec3d.z/f2;
        double sinValue=vec3d.x/f2;
//        double d1=(Math.acos(cos)+Math.PI)/ -0.017453292F;
//        double d2=(Math.asin(f1)+Math.PI)/ -0.017453292F;
//        if (d1==d2)return (float) d1;
        double g1=Math.asin(sinValue);
        double g2=Math.acos(cosValue);
        if (g1==g2)return (float) g1;
        else {
            double ang=Math.abs(g1);
            if ((sinValue<0 && cosValue>=0) || (sinValue>=0 && cosValue<0))ang=-ang;
            double base=0;
            if (cosValue<0)base=Math.PI/2;
            return (float) (base+ang);
        }
    }

}
