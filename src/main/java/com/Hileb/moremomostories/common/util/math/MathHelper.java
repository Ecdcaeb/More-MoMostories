package com.Hileb.moremomostories.common.util.math;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;


public class MathHelper {
    public static Vec3d getEnoughVec3d(int length,Vec3d forward){
        double scale=length/forward.lengthVector();
        double x=scale*forward.x;
        double y=scale*forward.y;
        double z=scale*forward.z;
        return new Vec3d(x,y,z);
    }
    public static double getYawAngle(Vec3d a,Vec3d b){
        boolean isPositive=false;

        isPositive=a.crossProduct(b).y>=0;
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
    public static @Nonnull List<RayTraceResult> raytraceAll(@Nonnull World world, @Nonnull Vec3d startVector, @Nonnull Vec3d endVec, boolean includeLiquids) {
        boolean ignoreBlockWithoutBoundingBox = true;
        Vec3d startVec = startVector;

        List<RayTraceResult> result = new ArrayList<>();

        if (!Double.isNaN(startVec.x) && !Double.isNaN(startVec.y) && !Double.isNaN(startVec.z)) {
            if (!Double.isNaN(endVec.x) && !Double.isNaN(endVec.y) && !Double.isNaN(endVec.z)) {
                int i = net.minecraft.util.math.MathHelper.floor(endVec.x);
                int j = net.minecraft.util.math.MathHelper.floor(endVec.y);
                int k = net.minecraft.util.math.MathHelper.floor(endVec.z);
                int l = net.minecraft.util.math.MathHelper.floor(startVec.x);
                int i1 = net.minecraft.util.math.MathHelper.floor(startVec.y);
                int j1 = net.minecraft.util.math.MathHelper.floor(startVec.z);
                BlockPos blockpos = new BlockPos(l, i1, j1);
                IBlockState iblockstate = world.getBlockState(blockpos);
                Block block = iblockstate.getBlock();

                if ((!ignoreBlockWithoutBoundingBox || iblockstate.getCollisionBoundingBox(world, blockpos) != Block.NULL_AABB)
                        && block.canCollideCheck(iblockstate, includeLiquids)) {
                    @Nonnull
                    RayTraceResult raytraceresult = iblockstate.collisionRayTrace(world, blockpos, startVec, endVec);
                    result.add(raytraceresult);
                }

                int k1 = 200;

                while (k1-- >= 0) {
                    if (Double.isNaN(startVec.x) || Double.isNaN(startVec.y) || Double.isNaN(startVec.z)) {
                        return new ArrayList<RayTraceResult>();
                    }

                    if (l == i && i1 == j && j1 == k) {
                        return result;
                    }

                    boolean flag2 = true;
                    boolean flag = true;
                    boolean flag1 = true;
                    double d0 = 999.0D;
                    double d1 = 999.0D;
                    double d2 = 999.0D;

                    if (i > l) {
                        d0 = l + 1.0D;
                    } else if (i < l) {
                        d0 = l + 0.0D;
                    } else {
                        flag2 = false;
                    }

                    if (j > i1) {
                        d1 = i1 + 1.0D;
                    } else if (j < i1) {
                        d1 = i1 + 0.0D;
                    } else {
                        flag = false;
                    }

                    if (k > j1) {
                        d2 = j1 + 1.0D;
                    } else if (k < j1) {
                        d2 = j1 + 0.0D;
                    } else {
                        flag1 = false;
                    }

                    double d3 = 999.0D;
                    double d4 = 999.0D;
                    double d5 = 999.0D;
                    double d6 = endVec.x - startVec.x;
                    double d7 = endVec.y - startVec.y;
                    double d8 = endVec.z - startVec.z;

                    if (flag2) {
                        d3 = (d0 - startVec.x) / d6;
                    }

                    if (flag) {
                        d4 = (d1 - startVec.y) / d7;
                    }

                    if (flag1) {
                        d5 = (d2 - startVec.z) / d8;
                    }

                    if (d3 == -0.0D) {
                        d3 = -1.0E-4D;
                    }

                    if (d4 == -0.0D) {
                        d4 = -1.0E-4D;
                    }

                    if (d5 == -0.0D) {
                        d5 = -1.0E-4D;
                    }

                    EnumFacing enumfacing;

                    if (d3 < d4 && d3 < d5) {
                        enumfacing = i > l ? EnumFacing.WEST : EnumFacing.EAST;
                        startVec = new Vec3d(d0, startVec.y + d7 * d3, startVec.z + d8 * d3);
                    } else if (d4 < d5) {
                        enumfacing = j > i1 ? EnumFacing.DOWN : EnumFacing.UP;
                        startVec = new Vec3d(startVec.x + d6 * d4, d1, startVec.z + d8 * d4);
                    } else {
                        enumfacing = k > j1 ? EnumFacing.NORTH : EnumFacing.SOUTH;
                        startVec = new Vec3d(startVec.x + d6 * d5, startVec.y + d7 * d5, d2);
                    }

                    l = net.minecraft.util.math.MathHelper.floor(startVec.x) - (enumfacing == EnumFacing.EAST ? 1 : 0);
                    i1 = net.minecraft.util.math.MathHelper.floor(startVec.y) - (enumfacing == EnumFacing.UP ? 1 : 0);
                    j1 = net.minecraft.util.math.MathHelper.floor(startVec.z) - (enumfacing == EnumFacing.SOUTH ? 1 : 0);
                    blockpos = new BlockPos(l, i1, j1);
                    IBlockState iblockstate1 = world.getBlockState(blockpos);
                    Block block1 = iblockstate1.getBlock();

                    if (!ignoreBlockWithoutBoundingBox || iblockstate1.getMaterial() == Material.PORTAL
                            || iblockstate1.getCollisionBoundingBox(world, blockpos) != Block.NULL_AABB) {
                        if (block1.canCollideCheck(iblockstate1, includeLiquids)) {
                            @Nonnull
                            RayTraceResult raytraceresult1 = iblockstate1.collisionRayTrace(world, blockpos, startVec, endVec);
                            result.add(raytraceresult1);
                        }
                    }
                }

                return result;
            } else {
                return result;
            }
        } else {
            return result;
        }
    }
    public static RayTraceResult rayTraceShoot(@Nullable Entity ignored, World world, Vec3d start, Vec3d end){
        RayTraceResult result=world.rayTraceBlocks(start,end, false, true, false);
        Vec3d trueEnd=end;
        if (result!=null){
            trueEnd = new Vec3d(result.hitVec.x, result.hitVec.y, result.hitVec.z);
        }
        Entity entity = null;
        //entity= EntityArrow.findEntityOnPath(vec3d1, vec3d);
        List<Entity> list = world.getEntitiesInAABBexcluding(ignored, new AxisAlignedBB(start,trueEnd), Entity::canBeCollidedWith);
        double d0 = 0.0D;

        for (int i = 0; i < list.size(); ++i)
        {
            Entity entity1 = list.get(i);

            if (entity1 != ignored)
            {
                AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().grow(0.30000001192092896D);
                RayTraceResult raytraceresult = axisalignedbb.calculateIntercept(start, end);

                if (raytraceresult != null)
                {
                    double d1 = start.squareDistanceTo(raytraceresult.hitVec);

                    if (d1 < d0 || d0 == 0.0D)
                    {
                        entity = entity1;
                        d0 = d1;
                    }
                }
            }
        }

        if (entity != null)
        {
            result = new RayTraceResult(entity);
        }
        return result;
    }
}
