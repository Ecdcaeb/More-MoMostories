package com.Hileb.moremomostories.item.armorMaterials;// Made with Blockbench 4.6.4
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

public class ModelShiningSilverBreastplate extends ModelBiped {

	public final ModelRenderer all;
	private final ModelRenderer cube_1;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer back;
	private final ModelRenderer ii2;
	private final ModelRenderer i_1_r1;
	private final ModelRenderer i_2_r1;
	private final ModelRenderer ii4;
	private final ModelRenderer i_1_r2;
	private final ModelRenderer i_2_r2;
	private final ModelRenderer right;
	private final ModelRenderer i_move_down;
	private final ModelRenderer i_1_r3;
	private final ModelRenderer i_2_r3;
	private final ModelRenderer left;
	private final ModelRenderer i_move_top;
	private final ModelRenderer i_1_r4;
	private final ModelRenderer i_2_r4;

	public ModelShiningSilverBreastplate() {
		textureWidth = 32;
		textureHeight = 32;

		all = new ModelRenderer(this);
		all.setRotationPoint(0.0F, 24.0F, 0.0F);


		cube_1 = new ModelRenderer(this);
		cube_1.setRotationPoint(0.0F, 0.0F, 0.0F);
		all.addChild(cube_1);


		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(3.0F, -9.0F, 2.0F);
		cube_1.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0873F, 0.4363F, -0.0873F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 19, 17, -3.0F, -1.0F, -0.25F, 3, 1, 1, 0.0F, false));

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(-3.0F, -9.0F, 2.0F);
		cube_1.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0873F, -0.4363F, 0.0873F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 0, 15, 0.0F, -1.0F, -0.25F, 3, 1, 1, 0.0F, false));

		back = new ModelRenderer(this);
		back.setRotationPoint(0.0F, 0.0F, 0.0F);
		all.addChild(back);
		back.cubeList.add(new ModelBox(back, 20, 0, -1.0F, -9.0F, -2.0F, 2, 6, 1, 0.0F, false));

		ii2 = new ModelRenderer(this);
		ii2.setRotationPoint(0.0F, -8.0F, 4.0F);
		back.addChild(ii2);


		i_1_r1 = new ModelRenderer(this);
		i_1_r1.setRotationPoint(0.0F, 0.0F, -5.0F);
		ii2.addChild(i_1_r1);
		setRotationAngle(i_1_r1, 0.0F, 0.0873F, 0.0F);
		i_1_r1.cubeList.add(new ModelBox(i_1_r1, 16, 9, -4.0F, -1.0F, -1.0F, 4, 3, 1, 0.0F, false));

		i_2_r1 = new ModelRenderer(this);
		i_2_r1.setRotationPoint(0.0F, 0.0F, -5.0F);
		ii2.addChild(i_2_r1);
		setRotationAngle(i_2_r1, 0.0F, -0.0873F, 0.0F);
		i_2_r1.cubeList.add(new ModelBox(i_2_r1, 0, 17, 0.0F, -1.0F, -1.0F, 4, 3, 1, 0.0F, false));

		ii4 = new ModelRenderer(this);
		ii4.setRotationPoint(0.0F, -8.0F, 4.0F);
		back.addChild(ii4);


		i_1_r2 = new ModelRenderer(this);
		i_1_r2.setRotationPoint(0.0F, 3.0F, -5.0F);
		ii4.addChild(i_1_r2);
		setRotationAngle(i_1_r2, 0.0F, 0.0873F, 0.0F);
		i_1_r2.cubeList.add(new ModelBox(i_1_r2, 10, 0, -4.0F, -1.0F, -1.0F, 4, 3, 1, 0.0F, false));

		i_2_r2 = new ModelRenderer(this);
		i_2_r2.setRotationPoint(0.0F, 3.0F, -5.0F);
		ii4.addChild(i_2_r2);
		setRotationAngle(i_2_r2, 0.0F, -0.0873F, 0.0F);
		i_2_r2.cubeList.add(new ModelBox(i_2_r2, 10, 5, 0.0F, -1.0F, -1.0F, 4, 3, 1, 0.0F, false));

		right = new ModelRenderer(this);
		right.setRotationPoint(0.0F, 0.0F, 0.0F);
		all.addChild(right);
		right.cubeList.add(new ModelBox(right, 0, 5, 2.0F, -10.0F, -1.0F, 3, 1, 4, 0.0F, false));
		right.cubeList.add(new ModelBox(right, 0, 10, 4.0F, -9.0F, -1.5F, 1, 3, 1, 0.0F, false));
		right.cubeList.add(new ModelBox(right, 0, 5, 3.9F, -9.0F, 2.2F, 1, 3, 1, 0.0F, false));
		right.cubeList.add(new ModelBox(right, 0, 10, 4.0F, -6.0F, -1.0F, 1, 1, 4, 0.0F, false));

		i_move_down = new ModelRenderer(this);
		i_move_down.setRotationPoint(0.0F, -8.0F, 4.0F);
		all.addChild(i_move_down);


		i_1_r3 = new ModelRenderer(this);
		i_1_r3.setRotationPoint(0.0F, 3.0F, 0.0F);
		i_move_down.addChild(i_1_r3);
		setRotationAngle(i_1_r3, -0.0873F, -0.2182F, 0.1309F);
		i_1_r3.cubeList.add(new ModelBox(i_1_r3, 6, 10, -4.0F, -1.0F, -1.0F, 4, 3, 1, 0.0F, false));

		i_2_r3 = new ModelRenderer(this);
		i_2_r3.setRotationPoint(0.0F, 3.0F, 0.0F);
		i_move_down.addChild(i_2_r3);
		setRotationAngle(i_2_r3, -0.0873F, 0.2182F, -0.1309F);
		i_2_r3.cubeList.add(new ModelBox(i_2_r3, 9, 14, 0.0F, -1.0F, -1.0F, 4, 3, 1, 0.0F, false));

		left = new ModelRenderer(this);
		left.setRotationPoint(0.0F, 0.0F, 0.0F);
		all.addChild(left);
		left.cubeList.add(new ModelBox(left, 0, 0, -5.0F, -10.0F, -1.0F, 3, 1, 4, 0.0F, false));
		left.cubeList.add(new ModelBox(left, 0, 10, -5.0F, -6.0F, -1.0F, 1, 1, 4, 0.0F, false));
		left.cubeList.add(new ModelBox(left, 20, 19, -5.0F, -9.0F, -1.5F, 1, 3, 1, 0.0F, false));
		left.cubeList.add(new ModelBox(left, 0, 0, -4.9F, -9.0F, 2.2F, 1, 3, 1, 0.0F, false));

		i_move_top = new ModelRenderer(this);
		i_move_top.setRotationPoint(0.0F, -8.0F, 4.0F);
		all.addChild(i_move_top);


		i_1_r4 = new ModelRenderer(this);
		i_1_r4.setRotationPoint(0.0F, 0.0F, 0.0F);
		i_move_top.addChild(i_1_r4);
		setRotationAngle(i_1_r4, 0.0436F, -0.2182F, 0.0F);
		i_1_r4.cubeList.add(new ModelBox(i_1_r4, 19, 13, -4.0F, -1.0F, -1.0F, 4, 3, 1, 0.0F, false));

		i_2_r4 = new ModelRenderer(this);
		i_2_r4.setRotationPoint(0.0F, 0.0F, 0.0F);
		i_move_top.addChild(i_2_r4);
		setRotationAngle(i_2_r4, 0.0436F, 0.2182F, 0.0F);
		i_2_r4.cubeList.add(new ModelBox(i_2_r4, 10, 18, 0.0F, -1.0F, -1.0F, 4, 3, 1, 0.0F, false));
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		float scaleC=scale*1.12f;
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleC, entityIn);
		all.render(scaleC);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);


		boolean flag = entityIn instanceof EntityLivingBase && ((EntityLivingBase)entityIn).getTicksElytraFlying() > 4;
		float f = 10.0F;

		if (flag)
		{
			f = (float)(entityIn.motionX * entityIn.motionX + entityIn.motionY * entityIn.motionY + entityIn.motionZ * entityIn.motionZ);
			f = f / 2F;
			f = f * f * f;
		}

		if (f < 10.0F)
		{
			f = 10.0F;
		}
		this.i_move_down.rotateAngleX = Math.abs(MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f);


		this.all.offsetY-=1.0f;
		this.all.offsetZ+=0.1f;
		this.all.rotateAngleY+=Math.PI;
	}
}