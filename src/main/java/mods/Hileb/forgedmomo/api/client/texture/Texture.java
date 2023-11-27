package mods.Hileb.forgedmomo.api.client.texture;

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
        bufferbuilder.pos(x , y + textureY, 0).tex((float)(startX) * 0.00390625F, (float)(startY + textureY) * 0.00390625F).endVertex();
        bufferbuilder.pos(x + textureX, y + textureY,0).tex((float)(startX +textureX) * 0.00390625F, (float)(startY + textureY) * 0.00390625F).endVertex();
        bufferbuilder.pos(x + textureX, y, (double)0).tex((float)(startX + textureX) * 0.00390625F, (float)(startY) * 0.00390625F).endVertex();
        bufferbuilder.pos(x , y, 0).tex((double)((float)(startX) * 0.00390625F), (float)(startY) * 0.00390625F).endVertex();
        tessellator.draw();
    }
}
