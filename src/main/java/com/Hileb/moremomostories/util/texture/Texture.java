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
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buf = tessellator.getBuffer();
        buf.begin(7, DefaultVertexFormats.POSITION_TEX);
        GlStateManager.color(1, 1, 1, alpha);


        buf.pos(sreenX, y + sreenY, 0).tex(textureStartX * 0.00390625, (textureStartY + y) * 0.00390625).endVertex();
        buf.pos(sreenX+x, y + sreenY, 0).tex((textureStartX + x) * 0.00390625, (textureStartY +y) * 0.00390625).endVertex();
        buf.pos(sreenX+x, sreenY, 0).tex((textureStartX+ x) * 0.00390625, textureStartY * 0.00390625).endVertex();
        buf.pos(sreenX+x, sreenY, 0).tex(textureStartX * 0.00390625, textureStartY * 0.00390625).endVertex();

        tessellator.draw();
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
        renderTexture(x,y,this.startX,this.startY,this.textureX,this.textureY,this.resourceLocation,alpha);
    }
}
