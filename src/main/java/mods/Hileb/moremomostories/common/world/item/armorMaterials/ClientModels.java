package mods.Hileb.moremomostories.common.world.item.armorMaterials;

import mods.Hileb.moremomostories.common.world.entity.model.ModelUndescable;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

@SideOnly(Side.CLIENT)
public class ClientModels {
    public static ModelAKGHeadset headset=new ModelAKGHeadset();
    public static QGXModel5 modelQGX=new QGXModel5();

    public static ModelShiningSilverBreastplate shiningSilverBreastplate=new ModelShiningSilverBreastplate();
    public static  ModelVanChest vanChest=new ModelVanChest();

    public static ModelBase modelDesc=new ModelUndescable();
    public static ModelRenderer[] modelUndescableList=new ModelRenderer[100];

    static {
        for(int i=0;i<100;i++){
            modelUndescableList[i]=(ModelUndescable.getModelRenders(modelDesc,new Random((int)(Math.random()*10086))));
        }
    }
}
