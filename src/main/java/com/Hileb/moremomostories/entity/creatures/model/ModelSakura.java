package com.Hileb.moremomostories.entity.creatures.model;

import com.Hileb.moremomostories.entity.EntityZFP;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;

public class ModelSakura extends ModelBiped {
    public ModelSakura(){ this(0.0F, false); }
    public ModelSakura(float modelSize, boolean p_i1168_2_)
    {
        super(modelSize, 0.0F, 64, p_i1168_2_ ? 32 : 64);
    }
    public ModelSakura(float modelSize, float p_i1149_2_, int textureWidthIn, int textureHeightIn){super(modelSize, p_i1149_2_, textureWidthIn, textureHeightIn);}

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
        boolean flag = entityIn instanceof EntityZFP && ((EntityZFP)entityIn).isElectricShaking();
        float f2 = -(float)Math.PI / 0.5F;
        this.bipedRightArm.rotateAngleX = f2;
        this.bipedLeftArm.rotateAngleX = f2;
        if (flag){
            f2 = -(float)Math.PI / 2.25F;
            this.bipedRightArm.rotateAngleX = f2;
            this.bipedLeftArm.rotateAngleX = f2;
            if (entityIn.world.getWorldTime()%3==0){
                    this.bipedBody.rotateAngleX = 0.5F;
                    this.bipedRightLeg.rotationPointZ = 4.0F;
                    this.bipedLeftLeg.rotationPointZ = 4.0F;
                    this.bipedRightLeg.rotationPointY = 9.0F;
                    this.bipedLeftLeg.rotationPointY = 9.0F;
                    this.bipedHead.rotationPointY = 1.0F;
                    this.bipedHead.rotationPointZ= -2.0F;
                }
                else
                {
                    this.bipedBody.rotateAngleX = 0.8F;
                    this.bipedRightLeg.rotationPointZ = 8.0F;
                    this.bipedLeftLeg.rotationPointZ = 8.0F;
                    this.bipedRightLeg.rotationPointY = 18.0F;
                    this.bipedLeftLeg.rotationPointY = 18.0F;
                    this.bipedHead.rotationPointY = 3.0F;
                    this.bipedHead.rotationPointZ= 2.0F;
                }
        }
    }
}
