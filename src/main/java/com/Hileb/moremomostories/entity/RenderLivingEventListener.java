package com.Hileb.moremomostories.entity;

import com.Hileb.moremomostories.IdlFramework;
import com.Hileb.moremomostories.init.ModConfig;
import com.Hileb.moremomostories.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(modid = IdlFramework.MODID)
public class RenderLivingEventListener {

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onRenderModelBiped(RenderLivingEvent.Post event){
        if (ModConfig.entityElectricShakingConf.iscanEntityElectricShaking){
            if (isElectricShaking(event.getEntity())){
                if (event.getRenderer().getMainModel() instanceof ModelBiped){
                    ModelBiped modelBiped=(ModelBiped) event.getRenderer().getMainModel();
                    setRotationAnglesElectricShaking(modelBiped,event.getEntity(),event);
                }
            }
        }
    }
    @SideOnly(Side.CLIENT)
    public static void setRotationAnglesElectricShaking(ModelBiped modelBiped, Entity entityIn,RenderLivingEvent.Post event) {
        boolean flag =isElectricShaking(entityIn);
        float f2 = -(float) Math.PI / 0.5F;
        modelBiped.bipedRightArm.rotateAngleX = f2;
        modelBiped.bipedLeftArm.rotateAngleX = f2;
        if (flag) {
            f2 = -(float) Math.PI / 2.25F;
            modelBiped.bipedRightArm.rotateAngleX = f2;
            modelBiped.bipedLeftArm.rotateAngleX = f2;
            if (entityIn.world.getWorldTime() % 4 > 1) {
                modelBiped.bipedBody.rotateAngleX = 0.5F;
                modelBiped.bipedRightLeg.rotationPointZ = 4.0F;
                modelBiped.bipedLeftLeg.rotationPointZ = 4.0F;
                modelBiped.bipedRightLeg.rotationPointY = 9.0F;
                modelBiped.bipedLeftLeg.rotationPointY = 9.0F;
                modelBiped.bipedHead.rotationPointY = 1.0F;
                modelBiped.bipedHead.rotationPointZ = -2.0F;
                GlStateManager.pushMatrix();
                GlStateManager.translate(0.0F, 0.2F, 0.0F);
                GlStateManager.popMatrix();
            }
            else {
                modelBiped.bipedBody.rotateAngleX = 0.8F;
                modelBiped.bipedRightLeg.rotationPointZ = 8.0F;
                modelBiped.bipedLeftLeg.rotationPointZ = 8.0F;
                modelBiped.bipedRightLeg.rotationPointY = 18.0F;
                modelBiped.bipedLeftLeg.rotationPointY = 18.0F;
                modelBiped.bipedHead.rotationPointY = 3.0F;
                modelBiped.bipedHead.rotationPointZ = 2.0F;
                GlStateManager.pushMatrix();
                GlStateManager.translate(0.0F, 0.2F, 0.0F);
                GlStateManager.popMatrix();
            }
        }
        float scale=event.getRenderer().prepareScale((EntityLivingBase) Minecraft.getMinecraft().player,event.getPartialRenderTick());
        modelBiped.bipedHead.render(scale);
        modelBiped.bipedBody.render(scale);
        modelBiped.bipedRightArm.render(scale);
        modelBiped.bipedLeftArm.render(scale);
        modelBiped.bipedRightLeg.render(scale);
        modelBiped.bipedLeftLeg.render(scale);
        modelBiped.bipedHeadwear.render(scale);
    }
    @SubscribeEvent
    public static void ItemClickToRender(PlayerInteractEvent.EntityInteract event){
        World world=event.getWorld();
        if (!world.isRemote){
            if (event.getEntityPlayer()!=null){
                EntityPlayer player=event.getEntityPlayer();
                if (player.getHeldItemMainhand().getItem()== ModItems.ITEM_DO_FOREVER){
                    if (event.getEntity() instanceof EntityLivingBase){
                        if(!(event.getTarget() instanceof EntityZFP)){
                            event.getTarget().setCustomNameTag("EntityElectricShaking");
                        }
                    }
                }
            }
        }
    }
    public static boolean isElectricShaking(Entity entity){
        return entity.getCustomNameTag().equals("EntityElectricShaking");
    }
}
