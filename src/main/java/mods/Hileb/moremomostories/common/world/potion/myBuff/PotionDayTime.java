package mods.Hileb.moremomostories.common.world.potion.myBuff;

import mods.Hileb.moremomostories.MoreMoMoSrories;
import mods.Hileb.moremomostories.common.world.potion.ModPotions;
import mods.Hileb.moremomostories.common.world.potion.PotionBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PotionDayTime extends PotionBase {
    public static final ResourceLocation TEXTURE_EFFECT=new ResourceLocation(MoreMoMoSrories.MODID,"textures/misc/white.png");

    public PotionDayTime(String name){
        super(name,false,0,0);
        MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public void removeEffect(LivingEvent.LivingUpdateEvent event){
        if (event.getEntityLiving() instanceof EntityPlayerMP && hasPotion(event.getEntityLiving(), MobEffects.BLINDNESS) && hasPotion(event.getEntityLiving(),this))event.getEntityLiving().removePotionEffect(MobEffects.BLINDNESS);
    }
    private boolean hasPotion(EntityLivingBase living, Potion potion){
        for (PotionEffect effect: living.getActivePotionEffects()){
            if (effect.getPotion()== potion){
                return true;
            }
        }
        return false;
    }
    private void renderBakin(float alpha){
        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();
        int dx=net.minecraft.client.Minecraft.getMinecraft().displayWidth;
        int dy=net.minecraft.client.Minecraft.getMinecraft().displayHeight;
        net.minecraft.client.renderer.GlStateManager.color(1.0F, 1.0F, 1.0F,alpha);
        net.minecraft.client.Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE_EFFECT);
        drawTexturedModalRect(0,0,0,0,dx,dy);

    }
    public void drawTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height)
    {
        float f = 0.00390625F;
        float f1 = 0.00390625F;
        int zLevel=0;
        net.minecraft.client.renderer.Tessellator tessellator = net.minecraft.client.renderer.Tessellator.getInstance();
        net.minecraft.client.renderer.BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, net.minecraft.client.renderer.vertex.DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos((double)(x + 0), (double)(y + height), (double)zLevel).tex((double)((float)(textureX + 0) * 0.00390625F), (double)((float)(textureY + height) * 0.00390625F)).endVertex();
        bufferbuilder.pos((double)(x + width), (double)(y + height), (double)zLevel).tex((double)((float)(textureX + width) * 0.00390625F), (double)((float)(textureY + height) * 0.00390625F)).endVertex();
        bufferbuilder.pos((double)(x + width), (double)(y + 0), (double)zLevel).tex((double)((float)(textureX + width) * 0.00390625F), (double)((float)(textureY + 0) * 0.00390625F)).endVertex();
        bufferbuilder.pos((double)(x + 0), (double)(y + 0), (double)zLevel).tex((double)((float)(textureX + 0) * 0.00390625F), (double)((float)(textureY + 0) * 0.00390625F)).endVertex();
        tessellator.draw();
    }
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onRenders(net.minecraftforge.client.event.RenderGameOverlayEvent.Pre event){
        if (event.isCanceled())return;
        else {
            for (PotionEffect effect: net.minecraft.client.Minecraft.getMinecraft().player.getActivePotionEffects()){
                if (effect.getPotion()== ModPotions.DAY_BLIND){
                    float tick=(((float)effect.getDuration()+event.getPartialTicks())/60);
                    if (event.getType()== RenderGameOverlayEvent.ElementType.TEXT)renderBakin(tick);
                }
            }
        }
    }
    public static PotionEffect getEffect(){
        return new PotionEffect(ModPotions.DAY_BLIND,60,0,false,false);
    }
    public static PotionEffect getEffectShort(){
        return new PotionEffect(ModPotions.DAY_BLIND,10,0,false,false);
    }



    ///-------------------------------------------------------
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
}
