package com.Hileb.moremomostories.item.armorMaterials;// Made with Blockbench 4.2.5
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class QGXModel5 extends ModelBiped {
	public final ModelRenderer bone_all;
	public final ModelRenderer bone_left;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer cube_r4;
	public final ModelRenderer bone_right;
	private final ModelRenderer cube_r5;
	private final ModelRenderer cube_r6;
	private final ModelRenderer cube_r7;
	private final ModelRenderer cube_r8;

	public QGXModel5() {
		textureWidth = 16;
		textureHeight = 16;

		bone_all = new ModelRenderer(this);
		bone_all.setRotationPoint(0.0F, 26.0F, 0.0F);


		bone_left = new ModelRenderer(this);
		bone_left.setRotationPoint(1.9F, 12.0F, 0.0F);
		bone_all.addChild(bone_left);
		bone_left.cubeList.add(new ModelBox(bone_left, 0, 0, -2.0F, -15.0F, -2.0F, 4, 1, 6, 0.0F, false));
		bone_left.cubeList.add(new ModelBox(bone_left, 0, 0, -2.0F, -16.5F, -2.0F, 4, 2, 4, 0.0F, false));
		bone_left.cubeList.add(new ModelBox(bone_left, 0, 0, -1.0F, -15.0F, 6.0F, 2, 1, 1, 0.0F, false));

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone_left.addChild(cube_r1);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 0, 0, -2.7F, -15.0F, 6.0F, 1, 1, -3, 0.0F, false));

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone_left.addChild(cube_r2);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 0, 0, 1.7F, -15.0F, 6.0F, 1, 1, -3, 0.0F, false));

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(0.0F, -1.0F, 2.0F);
		bone_left.addChild(cube_r3);
		cube_r3.cubeList.add(new ModelBox(cube_r3, 0, 0, -2.0F, -14.5F, -2.0F, 4, 1, 4, 0.0F, false));

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(0.0F, -1.0F, 2.0F);
		bone_left.addChild(cube_r4);
		cube_r4.cubeList.add(new ModelBox(cube_r4, 0, 0, -1.0F, -14.5F, 1.0F, 2, 1, 4, 0.0F, false));

		bone_right = new ModelRenderer(this);
		bone_right.setRotationPoint(-1.9F, 12.0F , 0.0F);
		bone_all.addChild(bone_right);
		bone_right.cubeList.add(new ModelBox(bone_right, 0, 0, -2.0F, -15.0F, -2.0F, 4, 1, 6, 0.0F, false));
		bone_right.cubeList.add(new ModelBox(bone_right, 0, 0, -2.0F, -16.5F, -2.0F, 4, 2, 4, 0.0F, false));
		bone_right.cubeList.add(new ModelBox(bone_right, 0, 0, -1.0F, -15.0F, 6.0F, 2, 1, 1, 0.0F, false));

		cube_r5 = new ModelRenderer(this);
		cube_r5.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone_right.addChild(cube_r5);
		cube_r5.cubeList.add(new ModelBox(cube_r5, 0, 0, -2.7F, -15.0F, 6.0F, 1, 1, -3, 0.0F, false));

		cube_r6 = new ModelRenderer(this);
		cube_r6.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone_right.addChild(cube_r6);
		cube_r6.cubeList.add(new ModelBox(cube_r6, 0, 0, 1.7F, -15.0F, 6.0F, 1, 1, -3, 0.0F, false));

		cube_r7 = new ModelRenderer(this);
		cube_r7.setRotationPoint(0.0F, -1.0F, 2.0F);
		bone_right.addChild(cube_r7);
		cube_r7.cubeList.add(new ModelBox(cube_r7, 0, 0, -2.0F, -14.5F, -2.0F, 4, 1, 4, 0.0F, false));

		cube_r8 = new ModelRenderer(this);
		cube_r8.setRotationPoint(0.0F, -1.0F, 2.0F);
		bone_right.addChild(cube_r8);
		cube_r8.cubeList.add(new ModelBox(cube_r8, 0, 0, -1.0F, -14.5F, 1.0F, 2, 1, 4, 0.0F, false));




		this.bipedLeftLeg.addChild(bone_left);
		this.bipedRightLeg.addChild(bone_right);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		GlStateManager.pushMatrix();

		if (this.isChild)
		{
			GlStateManager.scale(0.75F, 0.75F, 0.75F);
			GlStateManager.translate(0.0F, 16.0F * scale, 0.0F);
			GlStateManager.popMatrix();
			GlStateManager.pushMatrix();
			GlStateManager.scale(0.5F, 0.5F, 0.5F);
			GlStateManager.translate(0.0F, 24.0F * scale, 0.0F);
			renderChild(bipedRightLeg,bone_right,scale);
			renderChild(bipedLeftLeg,bone_left,scale);
		}
		else
		{
			if (entityIn.isSneaking())
			{
				GlStateManager.translate(0.0F, 0.2F, 0.0F);
			}

			renderChild(bipedRightLeg,bone_right,scale);
			renderChild(bipedLeftLeg,bone_left,scale);
		}

		GlStateManager.popMatrix();
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
		bone_left.offsetY+=0.9f;
		bone_right.offsetY+=0.9f;

		bone_left.offsetX-=0.1;
		bone_right.offsetX+=0.1;

		bone_right.rotateAngleY+=Math.PI;
		bone_left.rotateAngleY+=Math.PI;
	}
	public void renderChild(ModelRenderer mother,ModelRenderer child,float scale){
		GlStateManager.pushMatrix();
		GlStateManager.translate(mother.offsetX, mother.offsetY, mother.offsetZ);
		mother.postRender(scale);
		child.render(scale);
		GlStateManager.popMatrix();
		GlStateManager.translate(-mother.offsetX, -mother.offsetY, -mother.offsetZ);
	}
}