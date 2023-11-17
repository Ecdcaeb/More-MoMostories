package mods.Hileb.forgedmomo.utils.math;

import net.minecraft.util.math.Vec3d;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/8/11 14:50
 **/
public class FlexibleVec3d {
    public double x;
    public double y;
    public double z;
    public FlexibleVec3d(){
        x=0;
        y=0;
        z=0;
    }
    public FlexibleVec3d(double xIn,double yIn,double zIn){
        x=xIn;
        y=yIn;
        z=zIn;
    }
    public FlexibleVec3d(Vec3d vec3d){
        x=vec3d.x;
        y=vec3d.y;
        z=vec3d.z;
    }
}
