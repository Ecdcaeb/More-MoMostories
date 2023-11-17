package com.Hileb.moremomostories.common.world.entity.model;// Made with Blockbench 4.6.4
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import com.Hileb.moremomostories.common.world.entity.entity.living.EntityBike;
import com.Hileb.forgedmomo.utils.math.MathHelper;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;

public class ModelEntityBycile extends ModelBase {
	private final ModelRenderer all;
	private final ModelRenderer moveable;
	private final ModelRenderer cube_r14_r1;
	private final ModelRenderer cube_r16_r1;
	private final ModelRenderer wheel_2;
	private final ModelRenderer cube_r6;
	private final ModelRenderer cube_r7;
	private final ModelRenderer cube_r8;
	private final ModelRenderer cube_r9;
	private final ModelRenderer cube_r10;
	private final ModelRenderer staticModel;
	private final ModelRenderer cube_r11_r1;
	private final ModelRenderer cube_r13_r1;
	private final ModelRenderer cube_r13_r2;
	private final ModelRenderer cube_r13_r3;
	private final ModelRenderer cube_r18_r1;
	private final ModelRenderer set;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r12;
	private final ModelRenderer loop;
	private final ModelRenderer wheel_3;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer cube_r4;
	private final ModelRenderer cube_r5;
	private final ModelRenderer cube_r11;

	public ModelEntityBycile() {
		textureWidth = 32;
		textureHeight = 32;

		all = new ModelRenderer(this);
		all.setRotationPoint(0.0F, 27.0F, -8.5F);
		setRotationAngle(all, 0.0F, 1.5708F, 0.0F);


		moveable = new ModelRenderer(this);
		moveable.setRotationPoint(-14.0F, -8.0F, 0.0F);
		all.addChild(moveable);
		moveable.cubeList.add(new ModelBox(moveable, 0, 8, -1.0F, -2.0F, -2.5F, 1, 1, 5, 0.0F, false));
		moveable.cubeList.add(new ModelBox(moveable, 6, 10, -3.5F, 1.5F, -0.5F, 3, 0, 1, 0.0F, false));

		cube_r14_r1 = new ModelRenderer(this);
		cube_r14_r1.setRotationPoint(6.0F, 5.0F, 0.5F);
		moveable.addChild(cube_r14_r1);
		setRotationAngle(cube_r14_r1, 0.0F, 0.0F, 0.2618F);
		cube_r14_r1.cubeList.add(new ModelBox(cube_r14_r1, 15, 5, -8.0F, -4.5F, 1.25F, 3, 1, 1, 0.0F, false));
		cube_r14_r1.cubeList.add(new ModelBox(cube_r14_r1, 4, 15, -8.0F, -4.5F, -3.25F, 3, 1, 1, 0.0F, false));
		cube_r14_r1.cubeList.add(new ModelBox(cube_r14_r1, 0, 14, -8.2F, -4.5F, -1.0F, 1, 7, 1, 0.0F, false));

		cube_r16_r1 = new ModelRenderer(this);
		cube_r16_r1.setRotationPoint(6.0F, 5.0F, 0.5F);
		moveable.addChild(cube_r16_r1);
		setRotationAngle(cube_r16_r1, 0.0F, 0.0F, -0.5236F);
		cube_r16_r1.cubeList.add(new ModelBox(cube_r16_r1, 0, 8, -8.5F, -7.7F, -1.0F, 2, 0, 1, 0.0F, false));

		wheel_2 = new ModelRenderer(this);
		wheel_2.setRotationPoint(-2.0F, 5.0F, 0.5F);
		moveable.addChild(wheel_2);
		wheel_2.cubeList.add(new ModelBox(wheel_2, 17, 18, 0.0F, -2.0F, -1.0F, 0, 4, 1, 0.0F, false));
		wheel_2.cubeList.add(new ModelBox(wheel_2, 4, 19, -1.0F, -3.0F, -1.0F, 2, 1, 1, 0.0F, false));
		wheel_2.cubeList.add(new ModelBox(wheel_2, 9, 18, -1.0F, 2.0F, -1.0F, 2, 1, 1, 0.0F, false));

		cube_r6 = new ModelRenderer(this);
		cube_r6.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel_2.addChild(cube_r6);
		setRotationAngle(cube_r6, 0.0F, 0.0F, 0.7854F);
		cube_r6.cubeList.add(new ModelBox(cube_r6, 18, 7, -1.0F, 2.0F, -1.0F, 2, 1, 1, 0.0F, false));
		cube_r6.cubeList.add(new ModelBox(cube_r6, 15, 18, 0.0F, -2.0F, -1.0F, 0, 4, 1, 0.0F, false));

		cube_r7 = new ModelRenderer(this);
		cube_r7.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel_2.addChild(cube_r7);
		setRotationAngle(cube_r7, 0.0F, 0.0F, -0.7854F);
		cube_r7.cubeList.add(new ModelBox(cube_r7, 15, 17, -1.0F, 2.0F, -1.0F, 2, 1, 1, 0.0F, false));

		cube_r8 = new ModelRenderer(this);
		cube_r8.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel_2.addChild(cube_r8);
		setRotationAngle(cube_r8, 0.0F, 0.0F, 1.5708F);
		cube_r8.cubeList.add(new ModelBox(cube_r8, 14, 15, -1.0F, 2.0F, -1.0F, 2, 1, 1, 0.0F, false));
		cube_r8.cubeList.add(new ModelBox(cube_r8, 4, 17, -1.0F, -3.0F, -1.0F, 2, 1, 1, 0.0F, false));
		cube_r8.cubeList.add(new ModelBox(cube_r8, 2, 8, 0.0F, -2.0F, -1.0F, 0, 4, 1, 0.0F, false));

		cube_r9 = new ModelRenderer(this);
		cube_r9.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel_2.addChild(cube_r9);
		setRotationAngle(cube_r9, 0.0F, 0.0F, 2.3562F);
		cube_r9.cubeList.add(new ModelBox(cube_r9, 17, 3, -1.0F, 2.0F, -1.0F, 2, 1, 1, 0.0F, false));
		cube_r9.cubeList.add(new ModelBox(cube_r9, 0, 8, 0.0F, -2.0F, -1.0F, 0, 4, 1, 0.0F, false));

		cube_r10 = new ModelRenderer(this);
		cube_r10.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel_2.addChild(cube_r10);
		setRotationAngle(cube_r10, 0.0F, 0.0F, -2.3562F);
		cube_r10.cubeList.add(new ModelBox(cube_r10, 17, 1, -1.0F, 2.0F, -1.0F, 2, 1, 1, 0.0F, false));

		staticModel = new ModelRenderer(this);
		staticModel.setRotationPoint(-14.0F, -8.0F, 0.0F);
		all.addChild(staticModel);


		cube_r11_r1 = new ModelRenderer(this);
		cube_r11_r1.setRotationPoint(6.0F, 5.0F, 0.5F);
		staticModel.addChild(cube_r11_r1);
		setRotationAngle(cube_r11_r1, 0.0F, 0.0F, 1.8326F);
		cube_r11_r1.cubeList.add(new ModelBox(cube_r11_r1, 7, 8, -5.0F, -1.0F, -1.0F, 5, 1, 1, 0.0F, false));

		cube_r13_r1 = new ModelRenderer(this);
		cube_r13_r1.setRotationPoint(6.0F, 5.0F, 0.5F);
		staticModel.addChild(cube_r13_r1);
		setRotationAngle(cube_r13_r1, 0.0F, 0.0F, 0.0873F);
		cube_r13_r1.cubeList.add(new ModelBox(cube_r13_r1, 0, 4, 1.0F, -1.2F, -1.0F, 7, 1, 1, 0.0F, false));

		cube_r13_r2 = new ModelRenderer(this);
		cube_r13_r2.setRotationPoint(6.0F, 5.0F, 0.5F);
		staticModel.addChild(cube_r13_r2);
		setRotationAngle(cube_r13_r2, 0.0F, 0.0F, 0.48F);
		cube_r13_r2.cubeList.add(new ModelBox(cube_r13_r2, 0, 0, -7.5F, -1.0F, -1.0F, 8, 1, 1, 0.0F, false));

		cube_r13_r3 = new ModelRenderer(this);
		cube_r13_r3.setRotationPoint(6.0F, 5.0F, 0.5F);
		staticModel.addChild(cube_r13_r3);
		setRotationAngle(cube_r13_r3, 0.0F, 0.0F, 0.1309F);
		cube_r13_r3.cubeList.add(new ModelBox(cube_r13_r3, 0, 2, -7.5F, -4.0F, -1.0F, 8, 1, 1, 0.0F, false));

		cube_r18_r1 = new ModelRenderer(this);
		cube_r18_r1.setRotationPoint(6.0F, 5.0F, 0.5F);
		staticModel.addChild(cube_r18_r1);
		setRotationAngle(cube_r18_r1, 0.0F, 0.0F, 0.4363F);
		cube_r18_r1.cubeList.add(new ModelBox(cube_r18_r1, 0, 6, 0.0F, -4.0F, -1.0F, 7, 1, 1, 0.0F, false));

		set = new ModelRenderer(this);
		set.setRotationPoint(8.0F, 6.0F, 0.5F);
		staticModel.addChild(set);
		set.cubeList.add(new ModelBox(set, 10, 15, 0.0F, -6.0F, -1.5F, 1, 1, 2, 0.0F, false));

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(8.0F, 2.0F, -1.0F);
		set.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, -0.1745F, 0.0F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 14, 12, -10.0F, -8.0F, 2.0F, 3, 1, 1, 0.0F, false));

		cube_r12 = new ModelRenderer(this);
		cube_r12.setRotationPoint(8.0F, 2.0F, -1.0F);
		set.addChild(cube_r12);
		setRotationAngle(cube_r12, 0.0F, 0.1745F, 0.0F);
		cube_r12.cubeList.add(new ModelBox(cube_r12, 14, 10, -10.0F, -8.0F, -2.0F, 3, 1, 1, 0.0F, false));

		loop = new ModelRenderer(this);
		loop.setRotationPoint(6.5F, 5.0F, 0.0F);
		staticModel.addChild(loop);
		loop.cubeList.add(new ModelBox(loop, 9, 11, -0.5F, -1.0F, -1.5F, 1, 1, 3, 0.0F, false));
		loop.cubeList.add(new ModelBox(loop, 9, 11, -0.5F, -2.0F, 1.5F, 1, 2, 0, 0.0F, false));
		loop.cubeList.add(new ModelBox(loop, 7, 11, -0.5F, -1.0F, -1.5F, 1, 2, 0, 0.0F, false));

		wheel_3 = new ModelRenderer(this);
		wheel_3.setRotationPoint(14.0F, 5.0F, 0.5F);
		staticModel.addChild(wheel_3);
		wheel_3.cubeList.add(new ModelBox(wheel_3, 17, 18, 0.0F, -2.0F, -1.0F, 0, 4, 1, 0.0F, false));
		wheel_3.cubeList.add(new ModelBox(wheel_3, 4, 19, -1.0F, -3.0F, -1.0F, 2, 1, 1, 0.0F, false));
		wheel_3.cubeList.add(new ModelBox(wheel_3, 9, 18, -1.0F, 2.0F, -1.0F, 2, 1, 1, 0.0F, false));

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(-16.0F, 0.0F, 0.0F);
		wheel_3.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, 0.0F, 0.7854F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 18, 7, 10.3137F, -9.3137F, -1.0F, 2, 1, 1, 0.0F, false));
		cube_r2.cubeList.add(new ModelBox(cube_r2, 15, 18, 11.3137F, -13.3137F, -1.0F, 0, 4, 1, 0.0F, false));

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(-16.0F, 0.0F, 0.0F);
		wheel_3.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, 0.0F, -0.7854F);
		cube_r3.cubeList.add(new ModelBox(cube_r3, 15, 17, 10.3137F, 13.3137F, -1.0F, 2, 1, 1, 0.0F, false));

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(-16.0F, 0.0F, 0.0F);
		wheel_3.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.0F, 0.0F, 1.5708F);
		cube_r4.cubeList.add(new ModelBox(cube_r4, 14, 15, -1.0001F, -14.0F, -1.0F, 2, 1, 1, 0.0F, false));
		cube_r4.cubeList.add(new ModelBox(cube_r4, 4, 17, -1.0001F, -19.0F, -1.0F, 2, 1, 1, 0.0F, false));
		cube_r4.cubeList.add(new ModelBox(cube_r4, 2, 8, -0.0001F, -18.0F, -1.0F, 0, 4, 1, 0.0F, false));

		cube_r5 = new ModelRenderer(this);
		cube_r5.setRotationPoint(-16.0F, 0.0F, 0.0F);
		wheel_3.addChild(cube_r5);
		setRotationAngle(cube_r5, 0.0F, 0.0F, 2.3562F);
		cube_r5.cubeList.add(new ModelBox(cube_r5, 17, 3, -12.3138F, -9.3136F, -1.0F, 2, 1, 1, 0.0F, false));
		cube_r5.cubeList.add(new ModelBox(cube_r5, 0, 8, -11.3138F, -13.3136F, -1.0F, 0, 4, 1, 0.0F, false));

		cube_r11 = new ModelRenderer(this);
		cube_r11.setRotationPoint(-16.0F, 0.0F, 0.0F);
		wheel_3.addChild(cube_r11);
		setRotationAngle(cube_r11, 0.0F, 0.0F, -2.3562F);
		cube_r11.cubeList.add(new ModelBox(cube_r11, 17, 1, -12.3138F, 13.3136F, -1.0F, 2, 1, 1, 0.0F, false));
	}




	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		this.setRotationAngles(limbSwing,limbSwingAmount,ageInTicks,netHeadYaw,headPitch,scale,entityIn);
		all.render(scale);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {

		all.showModel=true;
		staticModel.showModel=true;
		moveable.showModel=true;
		wheel_2.showModel=true;
		wheel_3.showModel=true;

		all.isHidden=false;
		staticModel.isHidden=false;
		moveable.isHidden=false;
		wheel_2.isHidden=false;
		wheel_3.isHidden=false;






		double vectoryV=Math.sqrt((entityIn.motionX*entityIn.motionX)+(entityIn.motionZ*entityIn.motionZ));

		//if (entityIn.onGround){
			double vectoryW=vectoryV*2/20;
			if (entityIn.onGround)
				this.wheel_2.rotateAngleZ=(float) vectoryW*ageInTicks;
			if (((EntityBike)entityIn).isKeySDown){
				this.wheel_3.rotateAngleZ=(float) vectoryW*ageInTicks;
				this.loop.rotateAngleZ=this.wheel_3.rotateAngleZ*4f;
			}

		//}
		all.offsetY=-0.2f;


		all.rotateAngleY=((float)3*(float) Math.PI/2);

		Vec3d preSpeedVec=((EntityBike)entityIn).forward;


		double selfLong=3;
		double angleMoving=MathHelper.getYawAngle(preSpeedVec,Vec3d.fromPitchYaw((float)entityIn.rotationPitch-  (float) (Math.PI / 2f),(float)entityIn.rotationYaw + (float) (Math.PI / 2f)));
		double r=(1/ net.minecraft.util.math.MathHelper.sin((float) angleMoving/2f))*(selfLong/2);
		r=Math.abs(r);

		double a=vectoryV*vectoryV/r;


		moveable.rotateAngleY=(float)-angleMoving;


		double tanValue=10/a;
		double angle=(angleMoving>=0?1:-1)*(Math.atan(tanValue));

		if (a<=0.1)angle=0;

		angle=angle/4f;

		all.rotateAngleX=(float)angle;


		all.offsetX=0f;
		all.offsetZ=1.3f;
	}
}