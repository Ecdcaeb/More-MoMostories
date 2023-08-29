package com.Hileb.moremomostories.common.world.potion.myBuff;

import com.Hileb.moremomostories.MoreMoMoSrories;
import com.Hileb.moremomostories.common.world.damageSource.DamageSourceWither;
import com.Hileb.moremomostories.common.world.potion.ModPotions;
import com.Hileb.moremomostories.common.world.potion.PotionBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PotionBlood extends PotionBase {
    public static final ResourceLocation TEXTURE=new ResourceLocation(MoreMoMoSrories.MODID,"textures/misc/white.png");
    public static final int MAX_TICK=200;
    public PotionBlood(String name){
        super(name,true,0,0);
        MinecraftForge.EVENT_BUS.register(this);
    }
    public ResourceLocation getTexture(){
        return null;
    }
    @Override
    @SideOnly(Side.CLIENT)
    public void renderInventoryEffect(int x, int y, PotionEffect effect, net.minecraft.client.Minecraft mc)
    {
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void renderHUDEffect(int x, int y, PotionEffect effect, net.minecraft.client.Minecraft mc, float alpha)
    {
    }

    @Override
    public boolean shouldRender(PotionEffect effect) {
        return false;
    }

    @Override
    public boolean shouldRenderHUD(PotionEffect effect) {
        return false;
    }

    @Override
    public boolean shouldRenderInvText(PotionEffect effect) {
        return false;
    }
    //----------------------------------------------------------------------
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onRenders(RenderGameOverlayEvent.Post event){
        if (!(event.isCanceled() || event.getType() != RenderGameOverlayEvent.ElementType.HELMET) ){
            for (PotionEffect effect: net.minecraft.client.Minecraft.getMinecraft().player.getActivePotionEffects()){
                if (effect.getPotion()== ModPotions.DAY_BLIND){
                    float tick=(((float)effect.getDuration()+event.getPartialTicks())/60);
                    onOverlayRender(event,tick);
                }
            }
        }
    }
    @SideOnly(Side.CLIENT)
    public void onOverlayRender(final RenderGameOverlayEvent.Post event,float alpha) {



        Minecraft mc = Minecraft.getMinecraft();
        int high=mc.displayHeight;
        int width=mc.displayWidth;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();

        GlStateManager.disableDepth();
        GlStateManager.depthMask(false);
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();

        mc.getTextureManager().bindTexture(TEXTURE);
        GlStateManager.color(1, 0,0, alpha);//RGBA
        buffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        buffer.pos(0, high, -90).tex(0, 1).endVertex();
        buffer.pos(width,high, -90).tex(1, 1).endVertex();
        buffer.pos(width, 0, -90).tex(1, 0).endVertex();
        buffer.pos(0, 0, -90).tex(0, 0).endVertex();
        tessellator.draw();

        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableAlpha();
        GlStateManager.color(1, 1, 1, 1);
    } //作者：道家深湖 https://www.bilibili.com/read/cv24972434?spm_id_from=333.999.0.0 出处：bilibili
}
