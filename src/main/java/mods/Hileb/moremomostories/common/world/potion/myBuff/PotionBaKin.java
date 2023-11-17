package mods.Hileb.moremomostories.common.world.potion.myBuff;

import mods.Hileb.moremomostories.MoreMoMoSrories;
import mods.Hileb.moremomostories.common.world.potion.ModPotions;
import mods.Hileb.moremomostories.common.world.potion.PotionBase;
import mods.Hileb.moremomostories.common.util.texture.Texture;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PotionBaKin extends PotionBase {
    public static final int MAX_TICK=200;
    public static final Texture TEXTURE_BAKIN=new Texture(new ResourceLocation(MoreMoMoSrories.MODID,"textures/misc/bakin.png"),0,0,640,480);
    public static final Texture TEXTURE_GAS=new Texture(new ResourceLocation(MoreMoMoSrories.MODID,"textures/misc/gas.png"),0,0,800,480);

    public PotionBaKin(String name){
        super(name,false,0,0);
        MinecraftForge.EVENT_BUS.register(this);
    }
    public ResourceLocation getTexture(){
        return null;
    }
    public static void putEffect(EntityLivingBase living,int level){
        for (PotionEffect effect:living.getActivePotionEffects()){
            if (effect.getPotion()==ModPotions.BAKIN){
                PotionEffect newEffect=new PotionEffect(ModPotions.BAKIN,MAX_TICK,effect.getAmplifier()+level,false,false);
                living.removePotionEffect(ModPotions.BAKIN);
                living.addPotionEffect(newEffect);
                return;
            }
        }
        PotionEffect newEffect=new PotionEffect(ModPotions.BAKIN,MAX_TICK,level-1,false,false);
        living.addPotionEffect(newEffect);
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
    private void renderBakin(float alpha){
        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();

        int dx=net.minecraft.client.Minecraft.getMinecraft().displayWidth;
        int dy=net.minecraft.client.Minecraft.getMinecraft().displayHeight;

        net.minecraft.client.renderer.GlStateManager.color(1.0F, 1.0F, 1.0F,alpha);


//        net.minecraft.client.Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(MoreMoMoSrories.MODID,"textures/misc/bakin.png"));
//        drawTexturedModalRect(0,0,0,0,64,64,dx,dy);

        TEXTURE_BAKIN.scaled(dx,dy);
        TEXTURE_BAKIN.render(0,0,1.0F);
        TEXTURE_BAKIN.disScaled(dx,dy);


        GlStateManager.disableBlend();
        GlStateManager.disableAlpha();

        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();

        //GlStateManager.scale(800f/dx,480f/dy,0f);
        net.minecraft.client.renderer.GlStateManager.color(1.0F, 1.0F, 1.0F,alpha+0.1f);
//        net.minecraft.client.Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(MoreMoMoSrories.MODID,"textures/misc/gas.png"));
//        drawTexturedModalRect(0,0,0,0,800,480,dx*3,dy*3);

        TEXTURE_GAS.scaled(dx,dy);
        TEXTURE_GAS.render(0,0,1.0F);
        TEXTURE_GAS.disScaled(dx,dy);

    }
//    public void drawTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height,int sx,int sy)
//    {
//        float xScale = 0.00390625F;
//        float yScale = 0.00390625F;
//        int zLevel=0;
//        net.minecraft.client.renderer.Tessellator tessellator = net.minecraft.client.renderer.Tessellator.getInstance();
//        net.minecraft.client.renderer.BufferBuilder bufferbuilder = tessellator.getBuffer();
//        bufferbuilder.begin(7, net.minecraft.client.renderer.vertex.DefaultVertexFormats.POSITION_TEX);
//        bufferbuilder.pos((double)(x + 0), (double)(y + sy), (double)zLevel).tex((double)((float)(textureX + 0) * xScale), (double)((float)(textureY + height) * yScale)).endVertex();
//        bufferbuilder.pos((double)(x + sx), (double)(y + sy), (double)zLevel).tex((double)((float)(textureX + width) *  xScale), (double)((float)(textureY + height) * yScale)).endVertex();
//        bufferbuilder.pos((double)(x + sx), (double)(y + 0), (double)zLevel).tex((double)((float)(textureX + width) *  xScale), (double)((float)(textureY + 0) *yScale)).endVertex();
//        bufferbuilder.pos((double)(x + 0), (double)(y + 0), (double)zLevel).tex((double)((float)(textureX + 0) *  xScale), (double)((float)(textureY + 0) *yScale)).endVertex();
//        tessellator.draw();
//    }
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onRender(net.minecraftforge.client.event.RenderGameOverlayEvent.Post event){
        if (event.isCanceled())return;
        else {
            for (PotionEffect effect: net.minecraft.client.Minecraft.getMinecraft().player.getActivePotionEffects()){
                if (effect.getPotion()== ModPotions.BAKIN){
                    float tick=((((float)effect.getDuration()+event.getPartialTicks())/MAX_TICK)*0.1f)*(effect.getAmplifier()+1);
                    renderBakin(tick);
                }
            }
        }
    }
}
