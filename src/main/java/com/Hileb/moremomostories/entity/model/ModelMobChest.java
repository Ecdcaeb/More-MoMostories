package com.Hileb.moremomostories.entity.model;// Made with Blockbench 4.6.5
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import com.Hileb.moremomostories.entity.entity.living.EntityMobChest;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMobChest extends ModelBase {
	private final ModelRenderer all;
	private final ModelRenderer bottom;
	private final ModelRenderer top;

	public ModelMobChest() {
		textureWidth = 64;
		textureHeight = 64;

		all = new ModelRenderer(this);
		all.setRotationPoint(0.0F, 24.0F, 0.0F);


		bottom = new ModelRenderer(this);
		bottom.setRotationPoint(0.0F, 0.0F, 0.0F);
		all.addChild(bottom);
		bottom.cubeList.add(new ModelBox(bottom, 0, 19, -7.0F, -10.0F, -7.0F, 14, 10, 14, 0.0F, false));

		top = new ModelRenderer(this);
		top.setRotationPoint(0.0F, -11.0F, 8.0F);
		all.addChild(top);
		top.cubeList.add(new ModelBox(top, 0, 0, -7.0F, -4.0F, -15.0F, 14, 5, 14, 0.0F, false));
		top.cubeList.add(new ModelBox(top, 0, 0, -1.0F, -1.0F, -16.0F, 2, 4, 1, 0.0F, false));
	}


	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		all.render(scale);
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
		this.top.rotateAngleX=0;
		if (entityIn instanceof EntityMobChest){
			EntityMobChest entityMobChest=(EntityMobChest) entityIn;
			if(entityMobChest.isArmsRaised()){
				this.top.rotateAngleX=-(float) Math.PI/4f;
			}
		}
		all.rotationPointZ=(float) Math.PI;
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}