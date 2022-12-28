package com.Hileb.moremomostories.events.other;

import com.Hileb.moremomostories.item.myItems.RGBColor;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderTooltipEvent;

import java.util.List;

//@Mod.EventBusSubscriber(modid = IdlFramework.MODID)
public class RenderTooltipEventO {
    //public static float zLevel=0;
    //@SideOnly(Side.CLIENT)
    //@SubscribeEvent
    public static void onRenderTooltip(RenderTooltipEvent.Pre event){
        /**do nothing**/
//        event.setCanceled(true);
//        FontRenderer font=event.getFontRenderer();
//        int width=event.getScreenWidth();
//        int height=event.getScreenHeight();
//        int mouseX=event.getX();
//        int mouseY=event.getY();
//        float zLevel=300F;
        //renderToolTip(height,width,zLevel,event.getLines(),font,event.getStack(),mouseX,mouseY);

    }
    private static void renderToolTip(int height,int width,float zLevel,List<String> tooltip,FontRenderer fontRenderer,ItemStack stack, int x, int y)
    {
        FontRenderer font = stack.getItem().getFontRenderer(stack);
        net.minecraftforge.fml.client.config.GuiUtils.preItemToolTip(stack);
        drawHoveringText(height,width,zLevel,tooltip, x, y, (font == null ? fontRenderer : font));
        net.minecraftforge.fml.client.config.GuiUtils.postItemToolTip();
    }
    private static void drawHoveringText(int height,int width,float zLevel,List<String> textLines, int mouseX, int mouseY, FontRenderer font)
    {
        //net.minecraftforge.fml.client.config.GuiUtils.drawHoveringText(textLines, x, y, width, height, -1, font);
        if (!textLines.isEmpty())
        {
            GlStateManager.disableRescaleNormal();
            RenderHelper.disableStandardItemLighting();
            GlStateManager.disableLighting();
            GlStateManager.disableDepth();
            int i = 0;

            for (String s : textLines)
            {
                int j = font.getStringWidth(s);

                if (j > i)
                {
                    i = j;
                }
            }
            //获得字符串最长的长度

            int tooltipX = mouseX + 12;
            int tooltipY = mouseY - 12;
            //获取开始绘制的位置
            int k = 8;

            if (textLines.size() > 1)
            {
                k += 2 + (textLines.size() - 1) * 10;
            }

            if (tooltipX + i > width)
            {
                tooltipX -= 28 + i;
            }

            if (tooltipY + k + 6 > height)
            {
                tooltipY = height - k - 6;
            }

            zLevel = 300;
            int l = -267386864;
            RGBColor color=new RGBColor(0x0000ff);
            RGBColor colorNull=new RGBColor(0xf000000);
            RGBColor colorBlack=new RGBColor(0x000000);
            RGBColor colorYellow=new RGBColor(0xffff00);
            RGBColor colorYellow2=new RGBColor(0xaaaa00);
            RGBColor colorRed=new RGBColor(0xff0000);
            drawGradientRect(zLevel,tooltipX - 3, tooltipY - 4, tooltipX + i + 3, tooltipY - 3, colorYellow2, colorYellow2);//上边框
            drawGradientRect(zLevel,tooltipX - 3, tooltipY + k + 3, tooltipX + i + 3, tooltipY + k + 4,colorYellow2, colorYellow2);//下边框
            drawGradientRect(zLevel,tooltipX - 3, tooltipY - 3, tooltipX + i + 3, tooltipY + k + 3, colorYellow2, colorYellow2);//中心文字背景
            drawGradientRect(zLevel,tooltipX - 4, tooltipY - 4, tooltipX + i + 2, tooltipY + k + 2, colorBlack, colorBlack);//中心文字背景
            drawGradientRect(zLevel,tooltipX - 5, tooltipY - 5, tooltipX - 5, tooltipY + k + 5, colorYellow2, colorYellow2);//左边框
            drawGradientRect(zLevel,tooltipX + i + 3, tooltipY - 3, tooltipX + i + 4, tooltipY + k + 3, colorYellow2, colorYellow2);//右边框
            int i1 = 1347420415;
            int j1 = 1344798847;
            drawGradientRect(zLevel,tooltipX - 3, tooltipY - 3 + 1, tooltipX - 3 + 1, tooltipY + k + 3 - 1, colorYellow, colorYellow);//左内边框
            drawGradientRect(zLevel,tooltipX + i + 2, tooltipY - 3 + 1, tooltipX + i + 3, tooltipY + k + 3 - 1, colorYellow, colorYellow);//右内边框
            drawGradientRect(zLevel,tooltipX - 3, tooltipY - 3, tooltipX + i + 3, tooltipY - 3 + 1, colorYellow, colorYellow);//上内边框
            drawGradientRect(zLevel,tooltipX - 3, tooltipY + k + 2, tooltipX + i + 3, tooltipY + k + 3, colorYellow, colorYellow);//下内边框

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
}
