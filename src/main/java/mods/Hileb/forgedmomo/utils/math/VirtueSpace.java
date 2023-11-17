package mods.Hileb.forgedmomo.utils.math;

import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.util.List;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/8/10 17:04
 **/
public class VirtueSpace {
    public List<Vec3d> vec3dList= NonNullList.create();
    public Vec3d centerPos;
    public VirtueSpace(Vec3d mainPos){
        centerPos=mainPos;
    }

    public void scale(double scaleX,double scaleY,double scaleZ){
        List<Vec3d> newVec3dList= NonNullList.create();
        for(Vec3d vec3d:vec3dList){
            newVec3dList.add(new Vec3d(vec3d.x*scaleX,vec3d.y*scaleY,vec3d.z*scaleZ));
        }
        vec3dList.clear();
        vec3dList=newVec3dList;
    }
    public void rotation(float pitch,float yaw){
        List<Vec3d> newVec3dList= NonNullList.create();
        for(Vec3d vec3d:vec3dList){
            newVec3dList.add(vec3d.rotatePitch(pitch).rotateYaw(yaw));
        }
        vec3dList.clear();
        vec3dList=newVec3dList;
    }
    public void putPos(Vec3d vec3d){
        vec3dList.add(vec3d);
    }
    public void clear(){
        centerPos=Vec3d.ZERO;
        vec3dList.clear();
    }
    public Vec3d getCenterPos(){
        return centerPos;
    }
    public void resetCenterPos(Vec3d newPos){
        centerPos=newPos;
    }
    public static BlockPos getCenterAsBlockPos(VirtueSpace space){
        Vec3d vec3d=space.centerPos;
        return new BlockPos((int)vec3d.x,(int)vec3d.y,(int)vec3d.z);
    }
    public List<Vec3d> getPoints(){
        List<Vec3d> newVec3dList= NonNullList.create();
        for(Vec3d vec3d:vec3dList){
            newVec3dList.add(vec3d.add(centerPos));
        }
        return newVec3dList;
    }
}
