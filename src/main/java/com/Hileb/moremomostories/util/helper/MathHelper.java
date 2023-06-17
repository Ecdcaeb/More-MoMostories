package com.Hileb.moremomostories.util.helper;

import net.minecraft.util.math.Vec3d;

public class MathHelper {
    public static double getYawAngle(Vec3d a,Vec3d b){
        boolean isPositive=false;

        isPositive=a.crossProduct(b).y>=0;
//        if (Double.compare((a.x/a.z),(b.x/b.z))==0){
//            if (a.x*b.x>=0){
//                return 0;
//            }
//            else return Math.PI;
//        }
//
//        boolean flag=false;
//
//        double aYaw1=Math.asin(a.z/a.lengthVector());
//        if (a.z>=0) flag=true;
//        double line=flag?(Math.PI/2f):(Math.PI*3/2);
//        double f2=Math.abs(aYaw1-line);
//
//        double trueAAngle=0;
//
//        if(Double.compare(a.rotateYaw((float)-(f2+line)).z,0)==0){
//            trueAAngle=(f2+line);
//        }
//        else trueAAngle=(line-f2);
//
//        Vec3d newB3d=b.rotateYaw(-(float) trueAAngle);
//
//        if (newB3d.z>=0)isPositive=true;


        double va_x=a.x;
        double va_y=a.z;
        double vb_x=b.x;
        double vb_y=b.z;
        double productValue = (va_x * vb_x) + (va_y * vb_y);  // 向量的乘积
		double va_val = Math.sqrt(va_x * va_x + va_y * va_y);  // 向量a的模
		double vb_val = Math.sqrt(vb_x * vb_x + vb_y * vb_y);  // 向量b的模
		double cosValue = productValue / (va_val * vb_val);      // 余弦公式

		if(cosValue < -1 && cosValue > -2)
			  cosValue = -1;
		else if(cosValue > 1 && cosValue < 2)
			  cosValue = 1;

		return Math.acos(cosValue)*((isPositive)?1f:-1f);

    }
}
