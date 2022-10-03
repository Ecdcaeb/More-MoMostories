package com.Hileb.moremomostories.entity.model;// Made with Blockbench 4.2.5
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelDeath extends ModelBase {
	private final ModelRenderer all;
	private final ModelRenderer modelrenderer;
	private final ModelRenderer bipedLeftLeg;
	private final ModelRenderer bipedRightLeg;
	private final ModelRenderer bipedLeftArm;
	private final ModelRenderer bipedRightArm;
	private final ModelRenderer bipedBody;
	private final ModelRenderer bipedHeadwear;
	private final ModelRenderer bipedHead;

	public ModelDeath() {
		textureWidth = 16;
		textureHeight = 16;

		all = new ModelRenderer(this);
		all.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(all, 0.0F, 0.0F, 1.5708F);
		

		modelrenderer = new ModelRenderer(this);
		modelrenderer.setRotationPoint(0.0F, -24.0F, 0.0F);
		all.addChild(modelrenderer);
		

		bipedLeftLeg = new ModelRenderer(this);
		bipedLeftLeg.setRotationPoint(0.0F, -24.0F, 0.0F);
		all.addChild(bipedLeftLeg);
		bipedLeftLeg.cubeList.add(new ModelBox(bipedLeftLeg, 0, 16, -2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F, true));

		bipedRightLeg = new ModelRenderer(this);
		bipedRightLeg.setRotationPoint(0.0F, -24.0F, 0.0F);
		all.addChild(bipedRightLeg);
		bipedRightLeg.cubeList.add(new ModelBox(bipedRightLeg, 0, 16, -2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F, false));

		bipedLeftArm = new ModelRenderer(this);
		bipedLeftArm.setRotationPoint(0.0F, -24.0F, 0.0F);
		all.addChild(bipedLeftArm);
		bipedLeftArm.cubeList.add(new ModelBox(bipedLeftArm, 40, 16, -1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F, true));

		bipedRightArm = new ModelRenderer(this);
		bipedRightArm.setRotationPoint(0.0F, -24.0F, 0.0F);
		all.addChild(bipedRightArm);
		bipedRightArm.cubeList.add(new ModelBox(bipedRightArm, 40, 16, -3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F, false));

		bipedBody = new ModelRenderer(this);
		bipedBody.setRotationPoint(0.0F, -24.0F, 0.0F);
		all.addChild(bipedBody);
		bipedBody.cubeList.add(new ModelBox(bipedBody, 16, 16, -4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F, false));

		bipedHeadwear = new ModelRenderer(this);
		bipedHeadwear.setRotationPoint(0.0F, -24.0F, 0.0F);
		all.addChild(bipedHeadwear);
		

		bipedHead = new ModelRenderer(this);
		bipedHead.setRotationPoint(0.0F, -24.0F, 0.0F);
		all.addChild(bipedHead);
		bipedHead.cubeList.add(new ModelBox(bipedHead, 0, 0, -4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		all.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}