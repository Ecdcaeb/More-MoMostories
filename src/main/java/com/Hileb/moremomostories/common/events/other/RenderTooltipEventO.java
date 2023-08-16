package com.Hileb.moremomostories.common.events.other;

import com.Hileb.moremomostories.MoreMoMoSrories;
import com.Hileb.moremomostories.common.init.ModConfig;
import com.Hileb.moremomostories.common.world.item.myItems.RGBColor;
import com.Hileb.moremomostories.common.util.Reference;
import com.Hileb.moremomostories.common.util.texture.Texture;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;
@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid = MoreMoMoSrories.MODID)
public class RenderTooltipEventO {
    public static final Texture LeftTopAngleTexture=new Texture(new ResourceLocation(Reference.MOD_ID,"textures/misc/tooltip1.png"),0,0,25,38);
    public static final Texture MiddleTopTexture=new Texture(new ResourceLocation(Reference.MOD_ID,"textures/misc/tooltip1.png"),70,0,45,20);
    public static final Texture RightTopAngleTexture=new Texture(new ResourceLocation(Reference.MOD_ID,"textures/misc/tooltip1.png"),158,0,27,30);
    public static final Texture LeftBottomAngleTexture=new Texture(new ResourceLocation(Reference.MOD_ID,"textures/misc/tooltip1.png"),0,73,47,13);
    public static final Texture RightBottomAngleTexture=new Texture(new ResourceLocation(Reference.MOD_ID,"textures/misc/tooltip1.png"),173,60,9,23);

    public static final Texture EdgeHigh=new Texture(new ResourceLocation(Reference.MOD_ID,"textures/misc/tooltip1.png"),0,40,3,40);
    public static final Texture EdgeString=new Texture(new ResourceLocation(Reference.MOD_ID,"textures/misc/tooltip1.png"),40,10,5,3);



    public static final RGBColor colorBlack=new RGBColor(0x000000);
    @SideOnly(Side.CLIENT)
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onRenderTooltip(RenderTooltipEvent.Pre event){
        /**do nothing**/
        if (ModConfig.render.should_render_special_tooltip_for_ZFP){
            event.setCanceled(true);
            FontRenderer font=event.getFontRenderer();
            int width=event.getScreenWidth();
            int height=event.getScreenHeight();
            int mouseX=event.getX();
            int mouseY=event.getY();
            float zLevel=300F;
            renderToolTip(height,width,zLevel,event.getLines(),font,event.getStack(),mouseX,mouseY,event.getMaxWidth());
        }

    }
    private static void renderToolTip(int height,int width,float zLevel,List<String> tooltip,FontRenderer fontRenderer,ItemStack stack, int x, int y,int maxText)
    {
        FontRenderer font = stack.getItem().getFontRenderer(stack);
        net.minecraftforge.fml.client.config.GuiUtils.preItemToolTip(stack);
        drawHoveringText(height,width,zLevel,tooltip, x, y, (font == null ? fontRenderer : font),maxText);
        net.minecraftforge.fml.client.config.GuiUtils.postItemToolTip();
    }
    private static void drawHoveringText(int height,int width,float zLevel,List<String> textLines, int mouseX, int mouseY, FontRenderer font,int maxText)
    {
        //net.minecraftforge.fml.client.config.GuiUtils.drawHoveringText(textLines, x, y, width, height, -1, font);
        if (!textLines.isEmpty())
        {
            int screenWidth= Minecraft.getMinecraft().displayWidth;
            int screenHeight= Minecraft.getMinecraft().displayHeight;
            int maxTextWidth=maxText;
            GlStateManager.disableRescaleNormal();
            RenderHelper.disableStandardItemLighting();
            GlStateManager.disableLighting();
            GlStateManager.disableDepth();
            int tooltipTextWidth = 0;

            for (String textLine : textLines)
            {
                int textLineWidth = font.getStringWidth(textLine);

                if (textLineWidth > tooltipTextWidth)
                {
                    tooltipTextWidth = textLineWidth;
                }
            }

            boolean needsWrap = false;

            int titleLinesCount = 1;
            int tooltipX = mouseX + 12;
            if (tooltipX + tooltipTextWidth + 4 > screenWidth)
            {
                tooltipX = mouseX - 16 - tooltipTextWidth;
                if (tooltipX < 4) // if the tooltip doesn't fit on the screen
                {
                    if (mouseX > screenWidth / 2)
                    {
                        tooltipTextWidth = mouseX - 12 - 8;
                    }
                    else
                    {
                        tooltipTextWidth = screenWidth - 16 - mouseX;
                    }
                    needsWrap = true;
                }
            }

            if (maxTextWidth > 0 && tooltipTextWidth > maxTextWidth)
            {
                tooltipTextWidth = maxTextWidth;
                needsWrap = true;
            }

            if (needsWrap)
            {
                int wrappedTooltipWidth = 0;
                List<String> wrappedTextLines = new ArrayList<String>();
                for (int i = 0; i < textLines.size(); i++)
                {
                    String textLine = textLines.get(i);
                    List<String> wrappedLine = font.listFormattedStringToWidth(textLine, tooltipTextWidth);
                    if (i == 0)
                    {
                        titleLinesCount = wrappedLine.size();
                    }

                    for (String line : wrappedLine)
                    {
                        int lineWidth = font.getStringWidth(line);
                        if (lineWidth > wrappedTooltipWidth)
                        {
                            wrappedTooltipWidth = lineWidth;
                        }
                        wrappedTextLines.add(line);
                    }
                }
                tooltipTextWidth = wrappedTooltipWidth;
                textLines = wrappedTextLines;

                if (mouseX > screenWidth / 2)
                {
                    tooltipX = mouseX - 16 - tooltipTextWidth;
                }
                else
                {
                    tooltipX = mouseX + 12;
                }
            }

            int tooltipY = mouseY - 12;
            int tooltipHeight = 8;

            if (textLines.size() > 1)
            {
                tooltipHeight += (textLines.size() - 1) * 10;
                if (textLines.size() > titleLinesCount) {
                    tooltipHeight += 2; // gap between title lines and next lines
                }
            }

            if (tooltipY < 4)
            {
                tooltipY = 4;
            }
            else if (tooltipY + tooltipHeight + 4 > screenHeight)
            {
                tooltipY = screenHeight - tooltipHeight - 4;
            }

            int minX=tooltipX-3;
            int maxX=tooltipX+3+tooltipTextWidth;
            int minY=tooltipY-4;
            int maxY=tooltipY+3+tooltipHeight;
            //
            //renderIt



//
            drawGradientRect(zLevel,minX, minY, maxX, maxY+13, colorBlack,colorBlack);//上边框
            drawGradientRect(zLevel,minX, minY-10, maxX, maxY+13, colorBlack,colorBlack);//上边框
            drawGradientRect(zLevel,minX-10, minY, maxX+27, maxY+13, colorBlack,colorBlack);//上边框
            for(int p1=minX;p1<=maxX+18;p1=p1+5){
                if(p1<=((minX+maxX-45)/2) || p1>=((minX+maxX-45)/2)+45 && p1<=maxX){
                    EdgeString.render(p1,minY-10,1.0F);
                }
                EdgeString.render(p1,maxY+10,1.0F);
            }
            for(int p1=minY;p1<=maxY-20;p1=p1+5){
                EdgeHigh.render(maxX+24,p1,1.0F);
                EdgeHigh.render(minX-10,p1,1.0F);
            }


//            Texture allTexture=new Texture(new ResourceLocation(Reference.MOD_ID,"textures/misc/tooltip1.png"),0,0,187,88);
//            allTexture.render(minX,minY,1.0F);

            LeftTopAngleTexture.render(minX-10,minY-20,1.0f);
            RightTopAngleTexture.render(maxX,minY-20,1.0f);
            MiddleTopTexture.render((int)((minX+maxX-45)/2),minY-20,1.0F);
            RightBottomAngleTexture.render(maxX+15,maxY-15,1.0F);
            LeftBottomAngleTexture.render(minX-10,maxY,1.0F);






//            drawGradientRect(zLevel,tooltipX - 3, tooltipY - 4, tooltipX + i + 3, tooltipY - 3, colorYellow2, colorYellow2);//上边框
//            drawGradientRect(zLevel,tooltipX - 3, tooltipY + k + 3, tooltipX + i + 3, tooltipY + k + 4,colorYellow2, colorYellow2);//下边框
//            drawGradientRect(zLevel,tooltipX - 3, tooltipY - 3, tooltipX + i + 3, tooltipY + k + 3, colorYellow2, colorYellow2);//中心文字背景
//            drawGradientRect(zLevel,tooltipX - 4, tooltipY - 4, tooltipX + i + 2, tooltipY + k + 2, colorBlack, colorBlack);//中心文字背景
//            drawGradientRect(zLevel,tooltipX - 5, tooltipY - 5, tooltipX - 5, tooltipY + k + 5, colorYellow2, colorYellow2);//左边框
//            drawGradientRect(zLevel,tooltipX + i + 3, tooltipY - 3, tooltipX + i + 4, tooltipY + k + 3, colorYellow2, colorYellow2);//右边框
//            int i1 = 1347420415;
//            int j1 = 1344798847;
//            drawGradientRect(zLevel,tooltipX - 3, tooltipY - 3 + 1, tooltipX - 3 + 1, tooltipY + k + 3 - 1, colorYellow, colorYellow);//左内边框
//            drawGradientRect(zLevel,tooltipX + i + 2, tooltipY - 3 + 1, tooltipX + i + 3, tooltipY + k + 3 - 1, colorYellow, colorYellow);//右内边框
//            drawGradientRect(zLevel,tooltipX - 3, tooltipY - 3, tooltipX + i + 3, tooltipY - 3 + 1, colorYellow, colorYellow);//上内边框
//            drawGradientRect(zLevel,tooltipX - 3, tooltipY + k + 2, tooltipX + i + 3, tooltipY + k + 3, colorYellow, colorYellow);//下内边框

            for (int k1 = 0; k1 < textLines.size(); ++k1)
            {
                String s1 = textLines.get(k1);
                font.drawStringWithShadow(s1, (float)tooltipX, (float)tooltipY, -1);

                if (k1 == 0)
                {
                    tooltipY += 2;
                }

                tooltipY += 10;
            }

            zLevel=0;
            GlStateManager.enableLighting();
            GlStateManager.enableDepth();
            RenderHelper.enableStandardItemLighting();
            GlStateManager.enableRescaleNormal();
        }
    }
    private static void drawGradientRect(float zLevel, int left, int top, int right, int bottom, RGBColor startColor, RGBColor endColor)
    {

        int transparency=255;
        if (startColor.color==0xf000000)transparency=0;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos((double)right, (double)top, (double)zLevel).color(startColor.getRed(),startColor.getGreen(),startColor.getBlue(), transparency).endVertex();
        bufferbuilder.pos((double)left, (double)top, (double)zLevel).color(startColor.getRed(),startColor.getGreen(),startColor.getBlue(), transparency).endVertex();
        bufferbuilder.pos((double)left, (double)bottom, (double)zLevel).color(endColor.getRed(),endColor.getGreen(),endColor.getBlue(), transparency).endVertex();
        bufferbuilder.pos((double)right, (double)bottom, (double)zLevel).color(endColor.getRed(),endColor.getGreen(),endColor.getBlue(),transparency).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }
    public void renderTexture(int x, int y, PotionEffect effect, net.minecraft.client.Minecraft mc, float alpha, ResourceLocation texture)
    {
        mc.renderEngine.bindTexture(texture);
        GlStateManager.enableBlend();
        Gui.drawModalRectWithCustomSizedTexture(x+3, y+3, 0, 0, 18, 18, 18, 18);
    }
}
