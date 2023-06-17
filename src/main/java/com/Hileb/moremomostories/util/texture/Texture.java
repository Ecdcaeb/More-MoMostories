package com.Hileb.moremomostories.util.texture;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Texture {
    public final ResourceLocation resourceLocation;
    public final int startX;
    public final int startY;
    public final int textureX;
    public final int textureY;

    @SideOnly(Side.CLIENT)
    public static void renderTexture(int sreenX, int sreenY, int textureStartX, int textureStartY, int x, int y, ResourceLocation texture, float alpha){


    }
    public void scaled(float x,float y){
        GlStateManager.scale(x/textureX,y/textureY,1.0F);
    }
    public void disScaled(float x,float y){
        GlStateManager.scale(textureX/x,textureY/y,1.0F);
    }
    public Texture(ResourceLocation location,int startXIn,int startYIn,int xIn,int yIn){
        resourceLocation=location;
        startX=startXIn;
        startY=startYIn;
        textureX=xIn;
        textureY=yIn;
    }
    @SideOnly(Side.CLIENT)
    public void render(int x,int y,float alpha){
        Minecraft.getMinecraft().renderEngine.bindTexture(resourceLocation);
        float f = 0.00390625F;
        float f1 = 0.00390625F;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos((double)(x + 0), (double)(y + textureY), 0).tex((double)((float)(startX + 0) * 0.00390625F), (double)((float)(startY + textureY) * 0.00390625F)).endVertex();
        bufferbuilder.pos((double)(x + textureX), (double)(y + textureY),0).tex((double)((float)(startX +textureX) * 0.00390625F), (double)((float)(startY + textureY) * 0.00390625F)).endVertex();
        bufferbuilder.pos((double)(x + textureX), (double)(y + 0), (double)0).tex((double)((float)(startX + textureX) * 0.00390625F), (double)((float)(startY + 0) * 0.00390625F)).endVertex();
        bufferbuilder.pos((double)(x + 0), (double)(y + 0), 0).tex((double)((float)(startX + 0) * 0.00390625F), (double)((float)(startY + 0) * 0.00390625F)).endVertex();
        tessellator.draw();
    }
}
