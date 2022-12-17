package com.Hileb.moremomostories.item.armorMaterials;// Made with Blockbench 4.2.5
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class QGXModel2 extends ModelBiped {
	public final ModelRenderer bone_all;
	public final ModelRenderer bone;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer cube_r4;
	private final ModelRenderer cube_r5;
	private final ModelRenderer cube_r6;
	private final ModelRenderer cube_r7;
	public final ModelRenderer bone2;
	private final ModelRenderer cube_r8;
	private final ModelRenderer cube_r9;
	private final ModelRenderer cube_r10;
	private final ModelRenderer cube_r11;
	private final ModelRenderer cube_r12;
	private final ModelRenderer cube_r13;
	private final ModelRenderer cube_r14;

	public QGXModel2() {
		textureWidth = 128;
		textureHeight = 128;

		bone_all = new ModelRenderer(this);
		bone_all.setRotationPoint(0.0F, -2.0F, 0.0F);
		setRotationAngle(bone_all, 0.0F, 3.1416F, 0.0F);
		

		bone = new ModelRenderer(this);
		bone.setRotationPoint(2.0F, 25.0F, 0.0F);
		bone_all.addChild(bone);
		bone.cubeList.add(new ModelBox(bone, 0, 0, -1.0F, -2.0F, 1.0F, 2, 2, 4, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -2.0F, -2.0F, -3.0F, 4, 2, 4, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -2.0F, -3.0F, -3.0F, 4, 1, 4, 0.0F, false));

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(cube_r1);
		setRotationAngle(cube_r1, -0.0873F, 0.0F, 0.0F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 0, 0, -2.0F, -2.5F, 1.0F, 4, 1, 4, 0.0F, false));

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(0.0F, 0.0F, 5.0F);
		bone.addChild(cube_r2);
		setRotationAngle(cube_r2, -0.3927F, -1.6144F, 0.3927F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 0, 0, -5.0F, -2.0F, 1.0F, 3, 1, 1, 0.0F, false));

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(0.0F, 0.0F, 5.0F);
		bone.addChild(cube_r3);
		setRotationAngle(cube_r3, -0.5236F, 1.4399F, -0.5236F);
		cube_r3.cubeList.add(new ModelBox(cube_r3, 0, 0, 0.0F, -2.0F, 1.0F, 3, 1, 1, 0.0F, false));

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(0.0F, 0.0F, 5.0F);
		bone.addChild(cube_r4);
		setRotationAngle(cube_r4, -0.4363F, -0.6545F, 0.5236F);
		cube_r4.cubeList.add(new ModelBox(cube_r4, 0, 0, -2.0F, -1.0F, 0.0F, 3, 1, 1, 0.0F, false));

		cube_r5 = new ModelRenderer(this);
		cube_r5.setRotationPoint(0.0F, 0.0F, 5.0F);
		bone.addChild(cube_r5);
		setRotationAngle(cube_r5, -0.4363F, 0.6545F, -0.5236F);
		cube_r5.cubeList.add(new ModelBox(cube_r5, 0, 0, -1.0F, -1.0F, 0.0F, 3, 1, 1, 0.0F, false));

		cube_r6 = new ModelRenderer(this);
		cube_r6.setRotationPoint(0.0F, 0.0F, 5.0F);
		bone.addChild(cube_r6);
		setRotationAngle(cube_r6, -0.5236F, -1.4399F, 0.5236F);
		cube_r6.cubeList.add(new ModelBox(cube_r6, 0, 0, -3.0F, -2.0F, 1.0F, 3, 1, 1, 0.0F, false));

		cube_r7 = new ModelRenderer(this);
		cube_r7.setRotationPoint(0.0F, 0.0F, 5.0F);
		bone.addChild(cube_r7);
		setRotationAngle(cube_r7, -0.3927F, 1.6144F, -0.3927F);
		cube_r7.cubeList.add(new ModelBox(cube_r7, 0, 0, 2.0F, -2.0F, 1.0F, 3, 1, 1, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(6.0F, 25.0F, 0.0F);
		bone_all.addChild(bone2);
		bone2.cubeList.add(new ModelBox(bone2, 0, 0, -9.0F, -2.0F, 1.0F, 2, 2, 4, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 0, 0, -10.0F, -2.0F, -3.0F, 4, 2, 4, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 0, 0, -10.0F, -3.0F, -3.0F, 4, 1, 4, 0.0F, false));

		cube_r8 = new ModelRenderer(this);
		cube_r8.setRotationPoint(-8.0F, 0.0F, 0.0F);
		bone2.addChild(cube_r8);
		setRotationAngle(cube_r8, -0.0873F, 0.0F, 0.0F);
		cube_r8.cubeList.add(new ModelBox(cube_r8, 0, 0, -2.0F, -2.5F, 1.0F, 4, 1, 4, 0.0F, false));

		cube_r9 = new ModelRenderer(this);
		cube_r9.setRotationPoint(-8.0F, 0.0F, 5.0F);
		bone2.addChild(cube_r9);
		setRotationAngle(cube_r9, -0.3927F, -1.6144F, 0.3927F);
		cube_r9.cubeList.add(new ModelBox(cube_r9, 0, 0, -5.0F, -2.0F, 1.0F, 3, 1, 1, 0.0F, false));

		cube_r10 = new ModelRenderer(this);
		cube_r10.setRotationPoint(-8.0F, 0.0F, 5.0F);
		bone2.addChild(cube_r10);
		setRotationAngle(cube_r10, -0.5236F, 1.4399F, -0.5236F);
		cube_r10.cubeList.add(new ModelBox(cube_r10, 0, 0, 0.0F, -2.0F, 1.0F, 3, 1, 1, 0.0F, false));

		cube_r11 = new ModelRenderer(this);
		cube_r11.setRotationPoint(-8.0F, 0.0F, 5.0F);
		bone2.addChild(cube_r11);
		setRotationAngle(cube_r11, -0.4363F, -0.6545F, 0.5236F);
		cube_r11.cubeList.add(new ModelBox(cube_r11, 0, 0, -2.0F, -1.0F, 0.0F, 3, 1, 1, 0.0F, false));

		cube_r12 = new ModelRenderer(this);
		cube_r12.setRotationPoint(-8.0F, 0.0F, 5.0F);
		bone2.addChild(cube_r12);
		setRotationAngle(cube_r12, -0.4363F, 0.6545F, -0.5236F);
		cube_r12.cubeList.add(new ModelBox(cube_r12, 0, 0, -1.0F, -1.0F, 0.0F, 3, 1, 1, 0.0F, false));

		cube_r13 = new ModelRenderer(this);
		cube_r13.setRotationPoint(-8.0F, 0.0F, 5.0F);
		bone2.addChild(cube_r13);
		setRotationAngle(cube_r13, -0.5236F, -1.4399F, 0.5236F);
		cube_r13.cubeList.add(new ModelBox(cube_r13, 0, 0, -3.0F, -2.0F, 1.0F, 3, 1, 1, 0.0F, false));

		cube_r14 = new ModelRenderer(this);
		cube_r14.setRotationPoint(-8.0F, 0.0F, 5.0F);
		bone2.addChild(cube_r14);
		setRotationAngle(cube_r14, -0.3927F, 1.6144F, -0.3927F);
		cube_r14.cubeList.add(new ModelBox(cube_r14, 0, 0, 2.0F, -2.0F, 1.0F, 3, 1, 1, 0.0F, false));

		//this.bipedBody.addChild(bone_all);
		this.bipedLeftLeg.addChild(bone);
		this.bipedLeftLeg.addChild(bone2);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity,f,f1,f2,f3,f4,f5);
		bone.render(f5);
		bone2.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}