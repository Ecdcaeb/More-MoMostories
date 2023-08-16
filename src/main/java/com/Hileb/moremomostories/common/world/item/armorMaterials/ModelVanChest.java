package com.Hileb.moremomostories.common.world.item.armorMaterials;// Made with Blockbench 4.6.4
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class ModelVanChest extends ModelBiped {
	public final ModelRenderer all;
	private final ModelRenderer cube4;
	private final ModelRenderer cube3;
	private final ModelRenderer cube2;
	private final ModelRenderer main_cube;
	private final ModelRenderer x;
	private final ModelRenderer cube_r1;
	private final ModelRenderer x2;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube1;

	public ModelVanChest() {
		textureWidth = 32;
		textureHeight = 32;

		all = new ModelRenderer(this);
		all.setRotationPoint(0.0F, 24.0F, 0.0F);


		cube4 = new ModelRenderer(this);
		cube4.setRotationPoint(2.0F, -4.0F, 3.0F);
		all.addChild(cube4);
		cube4.cubeList.add(new ModelBox(cube4, 0, 6, -5.0F, -2.0F, -1.0F, 6, 1, 1, 0.0F, false));
		cube4.cubeList.add(new ModelBox(cube4, 0, 4, -5.0F, -2.0F, -6.0F, 6, 1, 1, 0.0F, false));
		cube4.cubeList.add(new ModelBox(cube4, 6, 9, 0.0F, -2.0F, -5.0F, 1, 1, 4, 0.0F, false));
		cube4.cubeList.add(new ModelBox(cube4, 0, 8, -5.0F, -2.0F, -5.0F, 1, 1, 4, 0.0F, false));
		cube4.cubeList.add(new ModelBox(cube4, 14, 15, 1.0F, -2.0F, 0.0F, 1, 1, 1, 0.0F, false));
		cube4.cubeList.add(new ModelBox(cube4, 10, 15, 1.0F, -2.0F, -2.0F, 1, 1, 1, 0.0F, false));
		cube4.cubeList.add(new ModelBox(cube4, 0, 15, 1.0F, -2.0F, -5.0F, 1, 1, 1, 0.0F, false));
		cube4.cubeList.add(new ModelBox(cube4, 7, 14, 1.0F, -2.0F, -7.0F, 1, 1, 1, 0.0F, false));
		cube4.cubeList.add(new ModelBox(cube4, 3, 14, -6.0F, -2.0F, -7.0F, 1, 1, 1, 0.0F, false));
		cube4.cubeList.add(new ModelBox(cube4, 0, 13, -4.0F, -2.0F, -7.0F, 1, 1, 1, 0.0F, false));
		cube4.cubeList.add(new ModelBox(cube4, 12, 11, -1.0F, -2.0F, -7.0F, 1, 1, 1, 0.0F, false));
		cube4.cubeList.add(new ModelBox(cube4, 12, 9, -6.0F, -2.0F, 0.0F, 1, 1, 1, 0.0F, false));
		cube4.cubeList.add(new ModelBox(cube4, 6, 10, -6.0F, -2.0F, -2.0F, 1, 1, 1, 0.0F, false));
		cube4.cubeList.add(new ModelBox(cube4, 0, 10, -6.0F, -2.0F, -5.0F, 1, 1, 1, 0.0F, false));
		cube4.cubeList.add(new ModelBox(cube4, 6, 8, -4.0F, -2.0F, 0.0F, 1, 1, 1, 0.0F, false));
		cube4.cubeList.add(new ModelBox(cube4, 0, 8, -1.0F, -2.0F, 0.0F, 1, 1, 1, 0.0F, false));

		cube3 = new ModelRenderer(this);
		cube3.setRotationPoint(2.0F, -4.0F, 3.0F);
		all.addChild(cube3);
		cube3.cubeList.add(new ModelBox(cube3, 0, 6, -5.0F, -1.0F, -1.0F, 6, 1, 1, 0.0F, false));
		cube3.cubeList.add(new ModelBox(cube3, 0, 4, -5.0F, -1.0F, -6.0F, 6, 1, 1, 0.0F, false));
		cube3.cubeList.add(new ModelBox(cube3, 6, 9, 0.0F, -1.0F, -5.0F, 1, 1, 4, 0.0F, false));
		cube3.cubeList.add(new ModelBox(cube3, 0, 8, -5.0F, -1.0F, -5.0F, 1, 1, 4, 0.0F, false));
		cube3.cubeList.add(new ModelBox(cube3, 14, 15, 1.0F, -1.0F, 0.0F, 1, 1, 1, 0.0F, false));
		cube3.cubeList.add(new ModelBox(cube3, 10, 15, 1.0F, -1.0F, -2.0F, 1, 1, 1, 0.0F, false));
		cube3.cubeList.add(new ModelBox(cube3, 0, 15, 1.0F, -1.0F, -5.0F, 1, 1, 1, 0.0F, false));
		cube3.cubeList.add(new ModelBox(cube3, 7, 14, 1.0F, -1.0F, -7.0F, 1, 1, 1, 0.0F, false));
		cube3.cubeList.add(new ModelBox(cube3, 3, 14, -6.0F, -1.0F, -7.0F, 1, 1, 1, 0.0F, false));
		cube3.cubeList.add(new ModelBox(cube3, 0, 13, -4.0F, -1.0F, -7.0F, 1, 1, 1, 0.0F, false));
		cube3.cubeList.add(new ModelBox(cube3, 12, 11, -1.0F, -1.0F, -7.0F, 1, 1, 1, 0.0F, false));
		cube3.cubeList.add(new ModelBox(cube3, 12, 9, -6.0F, -1.0F, 0.0F, 1, 1, 1, 0.0F, false));
		cube3.cubeList.add(new ModelBox(cube3, 6, 10, -6.0F, -1.0F, -2.0F, 1, 1, 1, 0.0F, false));
		cube3.cubeList.add(new ModelBox(cube3, 0, 10, -6.0F, -1.0F, -5.0F, 1, 1, 1, 0.0F, false));
		cube3.cubeList.add(new ModelBox(cube3, 6, 8, -4.0F, -1.0F, 0.0F, 1, 1, 1, 0.0F, false));
		cube3.cubeList.add(new ModelBox(cube3, 0, 8, -1.0F, -1.0F, 0.0F, 1, 1, 1, 0.0F, false));

		cube2 = new ModelRenderer(this);
		cube2.setRotationPoint(2.0F, -3.0F, 3.0F);
		all.addChild(cube2);
		cube2.cubeList.add(new ModelBox(cube2, 0, 6, -5.0F, -1.0F, -1.0F, 6, 1, 1, 0.0F, false));
		cube2.cubeList.add(new ModelBox(cube2, 0, 4, -5.0F, -1.0F, -6.0F, 6, 1, 1, 0.0F, false));
		cube2.cubeList.add(new ModelBox(cube2, 6, 9, 0.0F, -1.0F, -5.0F, 1, 1, 4, 0.0F, false));
		cube2.cubeList.add(new ModelBox(cube2, 0, 8, -5.0F, -1.0F, -5.0F, 1, 1, 4, 0.0F, false));
		cube2.cubeList.add(new ModelBox(cube2, 14, 15, 1.0F, -1.0F, 0.0F, 1, 1, 1, 0.0F, false));
		cube2.cubeList.add(new ModelBox(cube2, 10, 15, 1.0F, -1.0F, -2.0F, 1, 1, 1, 0.0F, false));
		cube2.cubeList.add(new ModelBox(cube2, 0, 15, 1.0F, -1.0F, -5.0F, 1, 1, 1, 0.0F, false));
		cube2.cubeList.add(new ModelBox(cube2, 7, 14, 1.0F, -1.0F, -7.0F, 1, 1, 1, 0.0F, false));
		cube2.cubeList.add(new ModelBox(cube2, 3, 14, -6.0F, -1.0F, -7.0F, 1, 1, 1, 0.0F, false));
		cube2.cubeList.add(new ModelBox(cube2, 0, 13, -4.0F, -1.0F, -7.0F, 1, 1, 1, 0.0F, false));
		cube2.cubeList.add(new ModelBox(cube2, 12, 11, -1.0F, -1.0F, -7.0F, 1, 1, 1, 0.0F, false));
		cube2.cubeList.add(new ModelBox(cube2, 12, 9, -6.0F, -1.0F, 0.0F, 1, 1, 1, 0.0F, false));
		cube2.cubeList.add(new ModelBox(cube2, 6, 10, -6.0F, -1.0F, -2.0F, 1, 1, 1, 0.0F, false));
		cube2.cubeList.add(new ModelBox(cube2, 0, 10, -6.0F, -1.0F, -5.0F, 1, 1, 1, 0.0F, false));
		cube2.cubeList.add(new ModelBox(cube2, 6, 8, -4.0F, -1.0F, 0.0F, 1, 1, 1, 0.0F, false));
		cube2.cubeList.add(new ModelBox(cube2, 0, 8, -1.0F, -1.0F, 0.0F, 1, 1, 1, 0.0F, false));

		main_cube = new ModelRenderer(this);
		main_cube.setRotationPoint(4.0F, 0.0F, -1.0F);
		all.addChild(main_cube);
		main_cube.cubeList.add(new ModelBox(main_cube, 12, 10, 0.0F, -1.0F, -1.0F, 1, 1, 4, 0.0F, false));
		main_cube.cubeList.add(new ModelBox(main_cube, 10, 4, -9.0F, -1.0F, -1.0F, 1, 1, 4, 0.0F, false));
		main_cube.cubeList.add(new ModelBox(main_cube, 0, 2, -9.0F, -1.0F, -2.0F, 10, 1, 1, 0.0F, false));
		main_cube.cubeList.add(new ModelBox(main_cube, 0, 0, -9.0F, -1.0F, 3.0F, 10, 1, 1, 0.0F, false));

		x = new ModelRenderer(this);
		x.setRotationPoint(0.0F, 0.0F, 0.0F);
		main_cube.addChild(x);


		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(-0.5F, -0.5F, -2.5F);
		x.addChild(cube_r1);
		setRotationAngle(cube_r1, -1.5708F, -1.5708F, 1.5708F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 3, 18, 5.5F, -0.5F, 8.5F, 1, 1, 1, 0.0F, false));
		cube_r1.cubeList.add(new ModelBox(cube_r1, 7, 18, 3.5F, -0.5F, 8.5F, 1, 1, 1, 0.0F, false));
		cube_r1.cubeList.add(new ModelBox(cube_r1, 18, 9, 1.5F, -0.5F, 8.5F, 1, 1, 1, 0.0F, false));
		cube_r1.cubeList.add(new ModelBox(cube_r1, 18, 11, -0.5F, -0.5F, 7.5F, 1, 1, 1, 0.0F, false));
		cube_r1.cubeList.add(new ModelBox(cube_r1, 0, 19, -0.5F, -0.5F, 3.5F, 1, 1, 1, 0.0F, false));
		cube_r1.cubeList.add(new ModelBox(cube_r1, 19, 5, -0.5F, -0.5F, 1.5F, 1, 1, 1, 0.0F, false));
		cube_r1.cubeList.add(new ModelBox(cube_r1, 17, 18, -0.5F, -0.5F, 5.5F, 1, 1, 1, 0.0F, false));
		cube_r1.cubeList.add(new ModelBox(cube_r1, 10, 19, -0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F, false));

		x2 = new ModelRenderer(this);
		x2.setRotationPoint(-8.0F, 0.0F, 2.0F);
		main_cube.addChild(x2);
		setRotationAngle(x2, 0.0F, 3.1416F, 0.0F);


		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(-0.5F, -0.5F, -2.5F);
		x2.addChild(cube_r2);
		setRotationAngle(cube_r2, -1.5708F, -1.5708F, 1.5708F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 3, 16, 5.5F, -0.5F, 8.5F, 1, 1, 1, 0.0F, false));
		cube_r2.cubeList.add(new ModelBox(cube_r2, 16, 4, 3.5F, -0.5F, 8.5F, 1, 1, 1, 0.0F, false));
		cube_r2.cubeList.add(new ModelBox(cube_r2, 16, 6, 1.5F, -0.5F, 8.5F, 1, 1, 1, 0.0F, false));
		cube_r2.cubeList.add(new ModelBox(cube_r2, 7, 16, -0.5F, -0.5F, 7.5F, 1, 1, 1, 0.0F, false));
		cube_r2.cubeList.add(new ModelBox(cube_r2, 0, 17, -0.5F, -0.5F, 3.5F, 1, 1, 1, 0.0F, false));
		cube_r2.cubeList.add(new ModelBox(cube_r2, 10, 17, -0.5F, -0.5F, 1.5F, 1, 1, 1, 0.0F, false));
		cube_r2.cubeList.add(new ModelBox(cube_r2, 14, 17, -0.5F, -0.5F, 5.5F, 1, 1, 1, 0.0F, false));
		cube_r2.cubeList.add(new ModelBox(cube_r2, 17, 16, -0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F, false));

		cube1 = new ModelRenderer(this);
		cube1.setRotationPoint(2.0F, -2.0F, 3.0F);
		all.addChild(cube1);
		cube1.cubeList.add(new ModelBox(cube1, 0, 6, -5.0F, -1.0F, -1.0F, 6, 1, 1, 0.0F, false));
		cube1.cubeList.add(new ModelBox(cube1, 0, 4, -5.0F, -1.0F, -6.0F, 6, 1, 1, 0.0F, false));
		cube1.cubeList.add(new ModelBox(cube1, 6, 9, 0.0F, -1.0F, -5.0F, 1, 1, 4, 0.0F, false));
		cube1.cubeList.add(new ModelBox(cube1, 0, 8, -5.0F, -1.0F, -5.0F, 1, 1, 4, 0.0F, false));
		cube1.cubeList.add(new ModelBox(cube1, 14, 15, 1.0F, -1.0F, 0.0F, 1, 1, 1, 0.0F, false));
		cube1.cubeList.add(new ModelBox(cube1, 10, 15, 1.0F, -1.0F, -2.0F, 1, 1, 1, 0.0F, false));
		cube1.cubeList.add(new ModelBox(cube1, 0, 15, 1.0F, -1.0F, -5.0F, 1, 1, 1, 0.0F, false));
		cube1.cubeList.add(new ModelBox(cube1, 7, 14, 1.0F, -1.0F, -7.0F, 1, 1, 1, 0.0F, false));
		cube1.cubeList.add(new ModelBox(cube1, 3, 14, -6.0F, -1.0F, -7.0F, 1, 1, 1, 0.0F, false));
		cube1.cubeList.add(new ModelBox(cube1, 0, 13, -4.0F, -1.0F, -7.0F, 1, 1, 1, 0.0F, false));
		cube1.cubeList.add(new ModelBox(cube1, 12, 11, -1.0F, -1.0F, -7.0F, 1, 1, 1, 0.0F, false));
		cube1.cubeList.add(new ModelBox(cube1, 12, 9, -6.0F, -1.0F, 0.0F, 1, 1, 1, 0.0F, false));
		cube1.cubeList.add(new ModelBox(cube1, 6, 10, -6.0F, -1.0F, -2.0F, 1, 1, 1, 0.0F, false));
		cube1.cubeList.add(new ModelBox(cube1, 0, 10, -6.0F, -1.0F, -5.0F, 1, 1, 1, 0.0F, false));
		cube1.cubeList.add(new ModelBox(cube1, 6, 8, -4.0F, -1.0F, 0.0F, 1, 1, 1, 0.0F, false));
		cube1.cubeList.add(new ModelBox(cube1, 0, 8, -1.0F, -1.0F, 0.0F, 1, 1, 1, 0.0F, false));
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		GlStateManager.pushMatrix();

		if (this.isChild)
		{
			float f = 2.0F;
			GlStateManager.scale(0.75F, 0.75F, 0.75F);
			GlStateManager.translate(0.0F, 16.0F * scale, 0.0F);
			GlStateManager.popMatrix();
			GlStateManager.pushMatrix();
			GlStateManager.scale(0.5F, 0.5F, 0.5F);
			GlStateManager.translate(0.0F, 24.0F * scale, 0.0F);
			this.renderChild(this.bipedBody,this.main_cube,scale);
			this.renderChild(this.bipedRightArm,this.cube1,scale);
			this.renderChild(this.bipedLeftArm,this.cube2,scale);
			this.renderChild(this.bipedLeftLeg,this.cube3,scale);
			this.renderChild(this.bipedRightLeg,this.cube4,scale);
		}
		else
		{
			if (entityIn.isSneaking())
			{
				GlStateManager.translate(0.0F, 0.2F, 0.0F);
			}

			this.renderChild(this.bipedBody,this.main_cube,scale);
			this.renderChild(this.bipedRightArm,this.cube1,scale);
			this.renderChild(this.bipedLeftArm,this.cube2,scale);
			this.renderChild(this.bipedLeftLeg,this.cube3,scale);
			this.renderChild(this.bipedRightLeg,this.cube4,scale);

		}

		GlStateManager.popMatrix();
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);


		this.main_cube.offsetY=0.65f;

		//leftArm
		this.cube2.offsetY=0.72f;
		//rightArm
		this.cube1.offsetY=0.65f;
		//leftLeg
		this.cube3.offsetY=0.82f;
		//rightLeg
		this.cube4.offsetY=0.89f;
	}

	public void renderChild(ModelRenderer mother, ModelRenderer child, float scale){
		mother.isHidden=false;
		mother.showModel=true;
		GlStateManager.pushMatrix();
		GlStateManager.translate(mother.offsetX, mother.offsetY, mother.offsetZ);
		mother.postRender(scale);
		child.render(scale);
		GlStateManager.popMatrix();
		GlStateManager.translate(-mother.offsetX, -mother.offsetY, -mother.offsetZ);
	}
}