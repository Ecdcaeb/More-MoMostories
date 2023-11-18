package mods.Hileb.moremomostories.client.resource;

import mods.Hileb.forgedmomo.api.resource.ResourceGenerateEvent;
import mods.Hileb.moremomostories.MoreMoMoSrories;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.commons.codec.binary.Base64;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.EXTFramebufferObject;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.util.glu.GLU;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.IntBuffer;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/11/15 12:17
 **/
//Resource Generator Glint
@Mod.EventBusSubscriber(modid= MoreMoMoSrories.MODID)
public class RCGlint {
    @SubscribeEvent
    public static void onRender(ResourceGenerateEvent event){
        Minecraft.getMinecraft().addScheduledTask(() -> generate(256));
    }

    //after post init:waiting for Minecraft instance.
    public static void generate(int count){
        //64x64
        RenderItem renderItem=Minecraft.getMinecraft().getRenderItem();
        FBOHelper fboHelper=new FBOHelper(64,64*count);
        float scale = 1.0F;
        final int timeAddition=14619000/count;
        //arg - end
        fboHelper.begin();

        GlStateManager.matrixMode(GL11.GL_PROJECTION);
        GlStateManager.pushMatrix();
        GlStateManager.loadIdentity();
        GlStateManager.ortho(0, 16, 0, 16, -150.0F, 150.0F);

        GlStateManager.matrixMode(GL11.GL_MODELVIEW);
        GlStateManager.enableRescaleNormal();

        GlStateManager.translate(8 * (1 - scale), 8 * (1 - scale), 0);

        renderItem.textureManager.bindTexture(RES_ITEM_GLINT);
        GlStateManager.scale(1.0f,2.5d/count,1.0f);

        //enable
        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        //
        renderItem.textureManager.bindTexture(RES_ITEM_GLINT);
        GlStateManager.color(0x80/256f,0x40/256f,0xCB/256f,0xAA/256f);
        //render
        {
            for (int i = 0; i < count*4; i++) {
                {
                    GlStateManager.pushMatrix();
                    {
                        GlStateManager.pushMatrix();
                        GlStateManager.scale(8.0F, 8.0F, 8.0F);
                        float offsetX = (float) ((long) i * timeAddition % 3000L) / 3000.0F / 8.0F;
                        GlStateManager.translate(offsetX, 0.0F, 0.0F);
                        GlStateManager.rotate(-50.0F, 0.0F, 0.0F, 1.0F);
                        {
                            Tessellator tessellator = Tessellator.getInstance();
                            BufferBuilder bufferbuilder = tessellator.getBuffer();
                            bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
                            bufferbuilder.pos(0,64+64*i, 0.0D).tex(0, 1).endVertex();
                            bufferbuilder.pos(64, 64+64*i, 0.0D).tex(1,1).endVertex();
                            bufferbuilder.pos(64, 0, 0.0D).tex(1,0).endVertex();
                            bufferbuilder.pos(0,0, 0.0D).tex(0,0).endVertex();
                            tessellator.draw();
                        }
                        GlStateManager.popMatrix();
                    }
                    {
                        GlStateManager.pushMatrix();
                        GlStateManager.scale(8.0F, 8.0F, 8.0F);
                        float f1 = (float) ((long) i * timeAddition % 4873L) / 4873.0F / 8.0F;
                        GlStateManager.translate(-f1, 0.0F, 0.0F);
                        GlStateManager.rotate(10.0F, 0.0F, 0.0F, 1.0F);
                        {
                            Tessellator tessellator = Tessellator.getInstance();
                            BufferBuilder bufferbuilder = tessellator.getBuffer();
                            bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
                            bufferbuilder.pos(0,64+64*i, 0.0D).tex(0, 1).endVertex();
                            bufferbuilder.pos(64, 64+64*i, 0.0D).tex(1,1).endVertex();
                            bufferbuilder.pos(64, 0, 0.0D).tex(1,0).endVertex();
                            bufferbuilder.pos(0,0, 0.0D).tex(0,0).endVertex();
                            tessellator.draw();
                        }
                        GlStateManager.popMatrix();
                    }
                    GlStateManager.popMatrix();
                }
            }
        }
        //render-end
        //disable
        GlStateManager.disableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.disableTexture2D();


        GlStateManager.matrixMode(GL11.GL_PROJECTION);
        GL11.glPopMatrix();

        fboHelper.end();
        File out=new File(Launch.minecraftHome,"glint_gen.png");
        fboHelper.saveToFile(out);
        fboHelper.restoreTexture();
    }
    private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
    public static class FBOHelper{
        private int ofsX;
        private int ofsY;
        private int framebufferID = -1;
        private int depthbufferID = -1;
        private int textureID = -1;

        private IntBuffer lastViewport;
        private int lastTexture;
        private int lastFramebuffer;
        public FBOHelper(int size){
            this(size,size);
        }

        public FBOHelper(int x,int y){
            ofsY=y;
            ofsX=x;
            createFramebuffer();
        }

        public void resize(int x,int y) {
            deleteFramebuffer();
            this.ofsY=y;
            this.ofsX=x;
            createFramebuffer();
        }

        public void begin() {
            checkGlErrors("FBO Begin Init");

            // Remember current framebuffer.
            lastFramebuffer = GL11.glGetInteger(EXTFramebufferObject.GL_FRAMEBUFFER_BINDING_EXT);

            // Render to our texture
            EXTFramebufferObject.glBindFramebufferEXT(EXTFramebufferObject.GL_FRAMEBUFFER_EXT, framebufferID);

            // Remember viewport info.
            lastViewport = GLAllocation.createDirectIntBuffer(16);
            GL11.glGetInteger(GL11.GL_VIEWPORT, lastViewport);
            GL11.glViewport(0, 0, ofsX,ofsY);

            GlStateManager.matrixMode(GL11.GL_MODELVIEW);
            GlStateManager.pushMatrix();
            GlStateManager.loadIdentity();

            // Remember current texture.
            lastTexture = GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D);

            GlStateManager.clearColor(0, 0, 0, 0);
            GlStateManager.clear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

            GlStateManager.cullFace(GlStateManager.CullFace.FRONT);
            GlStateManager.enableDepth();
            GlStateManager.enableLighting();
            GlStateManager.enableRescaleNormal();

            checkGlErrors("FBO Begin Final");
        }

        public void end() {
            checkGlErrors("FBO End Init");

            GlStateManager.cullFace(GlStateManager.CullFace.BACK);
            GlStateManager.disableDepth();
            GlStateManager.disableRescaleNormal();
            GlStateManager.disableLighting();

            GlStateManager.matrixMode(GL11.GL_MODELVIEW);
            GlStateManager.popMatrix();

            // Revert to last viewport
            GL11.glViewport(lastViewport.get(0), lastViewport.get(1), lastViewport.get(2), lastViewport.get(3));

            // Revert to default framebuffer
            EXTFramebufferObject.glBindFramebufferEXT(EXTFramebufferObject.GL_FRAMEBUFFER_EXT, lastFramebuffer);

            // Revert to last texture
            GlStateManager.bindTexture(lastTexture);

            checkGlErrors("FBO End Final");
        }

        public void bind() {
            GlStateManager.bindTexture(textureID);
        }

        // This is only a separate function because the texture gets messed with
        // after you're done rendering to read the FBO
        public void restoreTexture() {
            GlStateManager.bindTexture(lastTexture);
        }

        public void saveToFile(File file) {
            // Bind framebuffer texture
            GlStateManager.bindTexture(textureID);

            GL11.glPixelStorei(GL11.GL_PACK_ALIGNMENT, 1);
            GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);

            int width = GL11.glGetTexLevelParameteri(GL11.GL_TEXTURE_2D, 0, GL11.GL_TEXTURE_WIDTH);
            int height = GL11.glGetTexLevelParameteri(GL11.GL_TEXTURE_2D, 0, GL11.GL_TEXTURE_HEIGHT);

            IntBuffer texture = BufferUtils.createIntBuffer(width * height);
            GL11.glGetTexImage(GL11.GL_TEXTURE_2D, 0, GL12.GL_BGRA, GL12.GL_UNSIGNED_INT_8_8_8_8_REV, texture);

            int[] texture_array = new int[width * height];
            texture.get(texture_array);

            BufferedImage image = new BufferedImage(ofsX,ofsY, BufferedImage.TYPE_INT_ARGB);
            image.setRGB(0, 0, ofsX,ofsY, texture_array, 0, width);

            file.mkdirs();
            try {
                ImageIO.write(image, "png", file);
            } catch (Exception e) {
                // Do nothing
            }
        }

        public String getBase64() {
            // Bind framebuffer texture
            GlStateManager.bindTexture(textureID);

            GL11.glPixelStorei(GL11.GL_PACK_ALIGNMENT, 1);
            GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);

            int width = GL11.glGetTexLevelParameteri(GL11.GL_TEXTURE_2D, 0, GL11.GL_TEXTURE_WIDTH);
            int height = GL11.glGetTexLevelParameteri(GL11.GL_TEXTURE_2D, 0, GL11.GL_TEXTURE_HEIGHT);

            IntBuffer texture = BufferUtils.createIntBuffer(width * height);
            GL11.glGetTexImage(GL11.GL_TEXTURE_2D, 0, GL12.GL_BGRA, GL12.GL_UNSIGNED_INT_8_8_8_8_REV, texture);

            int[] texture_array = new int[width * height];
            texture.get(texture_array);

            BufferedImage image = new BufferedImage(ofsX,ofsY, BufferedImage.TYPE_INT_ARGB);
            image.setRGB(0, 0, ofsX,ofsY, texture_array, 0, width);

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            try {
                ImageIO.write(image, "PNG", out);
            } catch (IOException e) {
                // Do nothing
            }

            return Base64.encodeBase64String(out.toByteArray());
        }

        public void createFramebuffer() {
            framebufferID = EXTFramebufferObject.glGenFramebuffersEXT();
            textureID = GL11.glGenTextures();
            int currentFramebuffer = GL11.glGetInteger(EXTFramebufferObject.GL_FRAMEBUFFER_BINDING_EXT);
            int currentTexture = GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D);

            EXTFramebufferObject.glBindFramebufferEXT(EXTFramebufferObject.GL_FRAMEBUFFER_EXT, framebufferID);

            // Set our texture up, empty.
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, ofsX,ofsY, 0, GL12.GL_BGRA, GL11.GL_UNSIGNED_BYTE, (java.nio.ByteBuffer) null);

            // Restore old texture
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, currentTexture);

            // Create depth buffer
            depthbufferID = EXTFramebufferObject.glGenRenderbuffersEXT();
            EXTFramebufferObject.glBindRenderbufferEXT(EXTFramebufferObject.GL_RENDERBUFFER_EXT, depthbufferID);
            EXTFramebufferObject.glRenderbufferStorageEXT(EXTFramebufferObject.GL_RENDERBUFFER_EXT, GL11.GL_DEPTH_COMPONENT, ofsX,ofsY);

            // Bind depth buffer to the framebuffer
            EXTFramebufferObject.glFramebufferRenderbufferEXT(EXTFramebufferObject.GL_FRAMEBUFFER_EXT, EXTFramebufferObject.GL_DEPTH_ATTACHMENT_EXT, EXTFramebufferObject.GL_RENDERBUFFER_EXT, depthbufferID);

            // Bind our texture to the framebuffer
            EXTFramebufferObject.glFramebufferTexture2DEXT(EXTFramebufferObject.GL_FRAMEBUFFER_EXT, EXTFramebufferObject.GL_COLOR_ATTACHMENT0_EXT, GL11.GL_TEXTURE_2D, textureID, 0);

            // Revert to default framebuffer
            EXTFramebufferObject.glBindFramebufferEXT(EXTFramebufferObject.GL_FRAMEBUFFER_EXT, currentFramebuffer);
        }

        public void deleteFramebuffer() {
            EXTFramebufferObject.glDeleteFramebuffersEXT(framebufferID);
            GL11.glDeleteTextures(textureID);
            EXTFramebufferObject.glDeleteRenderbuffersEXT(depthbufferID);
        }

        public static void checkGlErrors(String message) {
            int error = GL11.glGetError();

            if (error != 0) {
                String error_name = GLU.gluErrorString(error);
                MoreMoMoSrories.LOGGER.error("########## GL ERROR ##########");
                MoreMoMoSrories.LOGGER.error("@ " + message);
                MoreMoMoSrories.LOGGER.error(error + ": " + error_name);
            }
        }
    }
}
