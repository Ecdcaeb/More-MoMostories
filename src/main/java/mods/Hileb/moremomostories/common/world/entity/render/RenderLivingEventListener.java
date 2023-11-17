package mods.Hileb.moremomostories.common.world.entity.render;

import mods.Hileb.moremomostories.MoreMoMoSrories;
import mods.Hileb.moremomostories.common.world.entity.entity.living.EntityZFP;
import mods.Hileb.moremomostories.common.world.item.ModItems;
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

@Mod.EventBusSubscriber(modid = MoreMoMoSrories.MODID)
public class RenderLivingEventListener {

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onRenderModelBiped(RenderLivingEvent.Post event){
//        if (ModConfig.entityElectricShakingConf.iscanEntityElectricShaking){
//            if (isElectricShaking(event.getEntity()) && (!(event.getEntity()instanceof EntityPlayer))){
//                if (event.getRenderer().getMainModel() instanceof ModelBiped){
//                    ModelBiped modelBiped=(ModelBiped) event.getRenderer().getMainModel();
//                    setRotationAnglesElectricShaking(modelBiped,event.getEntity(),event);
//                }
//            }
//        }
    }
    @SideOnly(Side.CLIENT)
    public static void setRotationAnglesElectricShaking(net.minecraft.client.model.ModelBiped modelBiped, Entity entityIn,RenderLivingEvent.Post event) {

        float scale=event.getRenderer().prepareScale((EntityLivingBase) net.minecraft.client.Minecraft.getMinecraft().player,event.getPartialRenderTick());
        render(modelBiped,entityIn,0,0,2,entityIn.rotationYaw,entityIn.height,scale);
        net.minecraft.client.renderer.GlStateManager.pushMatrix();
        net.minecraft.client.renderer.GlStateManager.translate(entityIn.posX,entityIn.posY,entityIn.posZ);
        net.minecraft.client.renderer.GlStateManager.popMatrix();
    }
    @SideOnly(Side.CLIENT)
    public static void render(net.minecraft.client.model.ModelBiped biped,Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        setRotationAngles(biped,limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
        net.minecraft.client.renderer.GlStateManager.pushMatrix();

        if (biped.isChild)
        {
            float f = 2.0F;
            net.minecraft.client.renderer.GlStateManager.scale(0.75F, 0.75F, 0.75F);
            //GlStateManager.translate(0.0F, 16.0F * scale, 0.0F);
            biped.bipedHead.render(scale);
            //GlStateManager.popMatrix();
            //GlStateManager.pushMatrix();
            net.minecraft.client.renderer.GlStateManager.scale(0.5F, 0.5F, 0.5F);
            //GlStateManager.translate(0.0F, 24.0F * scale, 0.0F);
            biped.bipedBody.render(scale);
            biped.bipedRightArm.render(scale);
            biped.bipedLeftArm.render(scale);
            biped.bipedRightLeg.render(scale);
            biped.bipedLeftLeg.render(scale);
            biped.bipedHeadwear.render(scale);
        }
        else
        {
            if (entityIn.isSneaking())
            {
                //GlStateManager.translate(0.0F, 0.2F, 0.0F);
            }

            biped.bipedHead.render(scale);
            biped.bipedBody.render(scale);
            biped.bipedRightArm.render(scale);
            biped.bipedLeftArm.render(scale);
            biped.bipedRightLeg.render(scale);
            biped.bipedLeftLeg.render(scale);
            biped.bipedHeadwear.render(scale);
        }

        net.minecraft.client.renderer.GlStateManager.popMatrix();
    }
    @SideOnly(Side.CLIENT)
    public static void setRotationAngles(net.minecraft.client.model.ModelBiped biped,float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        biped.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
        boolean flag = true;
        float f2 = -(float)Math.PI / 0.5F;
        biped.bipedRightArm.rotateAngleX = f2;
        biped.bipedLeftArm.rotateAngleX = f2;
        if (flag){
            f2 = -(float)Math.PI / 2.25F;
            biped.bipedRightArm.rotateAngleX = f2;
            biped.bipedLeftArm.rotateAngleX = f2;
            if (entityIn.world.getWorldTime()%3==0){
                biped.bipedBody.rotateAngleX = 0.5F;
                biped.bipedRightLeg.rotationPointZ = 4.0F;
                biped.bipedLeftLeg.rotationPointZ = 4.0F;
                biped.bipedRightLeg.rotationPointY = 9.0F;
                biped.bipedLeftLeg.rotationPointY = 9.0F;
                biped.bipedHead.rotationPointY = 1.0F;
                biped.bipedHead.rotationPointZ= -2.0F;
            }
            else
            {
                biped.bipedBody.rotateAngleX = 0.8F;
                biped.bipedRightLeg.rotationPointZ = 8.0F;
                biped.bipedLeftLeg.rotationPointZ = 8.0F;
                biped.bipedRightLeg.rotationPointY = 18.0F;
                biped.bipedLeftLeg.rotationPointY = 18.0F;
                biped.bipedHead.rotationPointY = 3.0F;
                biped.bipedHead.rotationPointZ= 2.0F;
            }
        }
    }
    @SideOnly(Side.CLIENT)
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
