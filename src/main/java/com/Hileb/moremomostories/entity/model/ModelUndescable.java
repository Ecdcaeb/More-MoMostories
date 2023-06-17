package com.Hileb.moremomostories.entity.model;

import com.Hileb.moremomostories.item.armorMaterials.ClientModels;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import java.util.Random;

public class ModelUndescable extends ModelBase {
    public ModelUndescable(){
    }
    public static ModelRenderer getModelRenders(ModelBase modelBase,Random random){
        ModelRenderer modelRenderer=new ModelRenderer(modelBase,0,0);
        int count=random.nextInt()%100+50;
        for(int i=0;i<count;i++){
            modelRenderer.addBox(((float)random.nextInt(64)-32f),((float)random.nextInt(64)-32f),((float)random.nextInt(64)-32f),((int)random.nextInt(64)-32),((int) random.nextInt(64)-32),((int)random.nextInt(64)-32));
        }
        return modelRenderer;
    }
    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        ClientModels.modelUndescableList[entityIn.world.rand.nextInt(100)].render(scale);
    }
}
