package mods.Hileb.forgedmomo.utils.math;

import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;

import java.util.LinkedList;
import java.util.List;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/11/18 18:32
 **/
public class LimitedVirtueSpace {
    private double[][] points;
    public final int size;
    private int pointer;
    public LimitedVirtueSpace(int count){
        points=new double[count][3];
        size=count;
        pointer=0;
    }
    public void resetPointer(){pointer=0;}
    public void scale(double scaleX,double scaleY,double scaleZ){
        for(int i=0;i<size;i++){
            points[i][0]*=scaleX;
            points[i][1]*=scaleY;
            points[i][2]*=scaleZ;
        }
    }
    public void rotation(float pitch,float yaw){
        rotatePitch(pitch);
        rotateYaw(yaw);
    }
    public void rotatePitch(float pitch)
    {
        float f = net.minecraft.util.math.MathHelper.cos(pitch);
        float f1 = net.minecraft.util.math.MathHelper.sin(pitch);
        for(int i=0;i<size;i++){
            double d1 = points[i][1] * (double)f + points[i][2] * (double)f1;
            double d2 = points[i][2] * (double)f - points[i][1] * (double)f1;
            points[i][1]=d1;
            points[i][2]=d2;
        }
    }

    public void rotateYaw(float yaw)
    {
        float f = net.minecraft.util.math.MathHelper.cos(yaw);
        float f1 = MathHelper.sin(yaw);
        for(int i=0;i<size;i++){
            double d0 = points[i][0] * (double)f + points[i][2] * (double)f1;
            double d2 = points[i][2] * (double)f - points[i][0] * (double)f1;
            points[i][0]=d0;
            points[i][2]=d2;
        }
    }

    @Override
    public String toString() {
        return points.toString();
    }

    @Override
    public int hashCode() {
        return points.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof LimitedVirtueSpace){
            return this.hashCode()==obj.hashCode();
        }else return false;
    }
    public void put(int index,double x,double y,double z){
        points[index][0]=x;
        points[index][1]=y;
        points[index][2]=z;
    }
    public void put(double x,double y,double z){
        put(pointer,x,y,z);
        pointer++;
    }
    public void putFromPitchYaw(float pitch, float yaw)
    {
        double f = MathHelper.cos(-yaw * 0.017453292F - (float)Math.PI);
        double f1 = MathHelper.sin(-yaw * 0.017453292F - (float)Math.PI);
        double f2 = -MathHelper.cos(-pitch * 0.017453292F);
        double f3 = MathHelper.sin(-pitch * 0.017453292F);
        put(f1 * f2, f3, f * f2);
    }
    public void putFromPitchYaw(Vec2f vec)
    {
        putFromPitchYaw(vec.x, vec.y);
    }
    public double[][] getPoints(){
        return points;
    }
}
