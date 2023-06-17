package com.Hileb.moremomostories.item.armorMaterials;// Made with Blockbench 4.2.5
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class ModelAKGHeadset extends ModelBiped {
	public final ModelRenderer akg;
	private final ModelRenderer side1;
	private final ModelRenderer top;
	private final ModelRenderer side2;

	public ModelAKGHeadset() {
		textureWidth = 32;
		textureHeight = 32;

		akg = new ModelRenderer(this);
		akg.setRotationPoint(0.0F, 27.0F, 0.0F);


		side1 = new ModelRenderer(this);
		side1.setRotationPoint(0.0F, 0.0F, 0.0F);
		akg.addChild(side1);
		side1.cubeList.add(new ModelBox(side1, 12, 5, 4.0F, -31.0F, -2.0F, 1, 4, 4, 0.0F, false));
		side1.cubeList.add(new ModelBox(side1, 18, 3, 4.0F, -32.0F, -1.0F, 1, 1, 2, 0.0F, false));
		side1.cubeList.add(new ModelBox(side1, 18, 6, 4.25F, -33.0F, 0.5F, 1, 3, 0, 0.0F, false));
		side1.cubeList.add(new ModelBox(side1, 12, 17, 4.25F, -33.0F, -0.5F, 1, 3, 0, 0.0F, false));
		side1.cubeList.add(new ModelBox(side1, 0, 13, 4.25F, -30.0F, -1.0F, 1, 2, 2, 0.0F, false));

		top = new ModelRenderer(this);
		top.setRotationPoint(0.0F, 0.0F, 0.0F);
		akg.addChild(top);
		top.cubeList.add(new ModelBox(top, 0, 0, -5.0F, -33.0F, -1.0F, 10, 1, 2, 0.0F, false));
		top.cubeList.add(new ModelBox(top, 0, 4, -5.0F, -33.25F, 0.5F, 10, 1, 0, 0.0F, false));
		top.cubeList.add(new ModelBox(top, 0, 3, -5.0F, -33.25F, -0.5F, 10, 1, 0, 0.0F, false));

		side2 = new ModelRenderer(this);
		side2.setRotationPoint(-9.0F, 0.0F, 0.0F);
		akg.addChild(side2);
		side2.cubeList.add(new ModelBox(side2, 0, 13, 3.75F, -30.0F, -1.0F, 1, 2, 2, 0.0F, false));
		side2.cubeList.add(new ModelBox(side2, 6, 9, 4.0F, -31.0F, -2.0F, 1, 4, 4, 0.0F, false));
		side2.cubeList.add(new ModelBox(side2, 6, 17, 4.0F, -32.0F, -1.0F, 1, 1, 2, 0.0F, false));
		side2.cubeList.add(new ModelBox(side2, 14, 5, 3.75F, -33.0F, 0.5F, 1, 3, 0, 0.0F, false));
		side2.cubeList.add(new ModelBox(side2, 12, 5, 3.75F, -33.0F, -0.5F, 1, 3, 0, 0.0F, false));


		bipedHead.addChild(akg);

	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

		GlStateManager.pushMatrix();

		if (this.isChild)
		{
			GlStateManager.scale(0.75F, 0.75F, 0.75F);
			GlStateManager.translate(0.0F, 16.0F * scale, 0.0F);
			renderAKG(akg,scale);
			GlStateManager.popMatrix();
			GlStateManager.pushMatrix();
			GlStateManager.scale(0.5F, 0.5F, 0.5F);
			GlStateManager.translate(0.0F, 24.0F * scale, 0.0F);
		}
		else
		{
			if (entityIn.isSneaking())
			{
				GlStateManager.translate(0.0F, 0.2F, 0.0F);
			}

			renderAKG(akg,scale);
		}

		GlStateManager.popMatrix();
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
		super.setRotationAngles(limbSwing,limbSwingAmount,ageInTicks,netHeadYaw,headPitch,scaleFactor,entityIn);
		this.akg.offsetY=-0.100f;
	}
	private void renderAKG(ModelRenderer source,float scale){
		GlStateManager.pushMatrix();
		GlStateManager.translate(source.offsetX, source.offsetY, source.offsetZ);
		bipedHead.postRender(scale);
		akg.render(scale);
		GlStateManager.popMatrix();
		GlStateManager.translate(-source.offsetX, -source.offsetY, -source.offsetZ);
	}
}