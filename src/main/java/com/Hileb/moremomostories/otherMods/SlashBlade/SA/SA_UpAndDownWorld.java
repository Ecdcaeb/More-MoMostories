package com.Hileb.moremomostories.otherMods.SlashBlade.SA;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.Random;

public class SA_UpAndDownWorld extends SABase {
    public SA_UpAndDownWorld(){
        super();
    }
    @Override
    public String toString() {
        return "up_and_down_world";
    }
    @Override
    public void doSpacialAttack(ItemStack itemStack, EntityPlayer entityPlayer) {
        if (entityPlayer!=null){
            if (entityPlayer.world.isRemote)saClient(itemStack,entityPlayer);
            else saService(itemStack,entityPlayer);
        }
    }
    //@SideOnly(Side.CLIENT)
    public void saClient(ItemStack itemStack, EntityPlayer entityPlayer){
        Random random=new Random(entityPlayer.getUniqueID().hashCode()+itemStack.hashCode());
        for (int i=0;i<=100;i++){
            add(new Random(random.nextLong()));
        }
    }
    //@SideOnly(Side.CLIENT)
    public void add(Random random){
        GlStateManager.pushMatrix();
        GlStateManager.disableLighting();
        GlStateManager.scale(0.75F, 0.75F, 0.75F);
        GlStateManager.translate(random.nextInt(100)-50, random.nextInt(100)-50, random.nextInt(100)-50);
        GlStateManager.disableRescaleNormal();



        GlStateManager.rotate(random.nextInt(5)-3, random.nextInt(5)-3, random.nextInt(5)-3, random.nextInt(5)-3);
        GlStateManager.rotate(random.nextInt(5)-3, random.nextInt(5)-3, random.nextInt(5)-3, random.nextInt(5)-3);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();

        GlStateManager.glNormal3f(random.nextInt(5)-3, random.nextInt(5)-3, random.nextInt(5)-3);
        bufferbuilder.begin(random.nextInt(5)-3, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos(-7.0D, random.nextInt(5)-3, random.nextInt(5)-3).tex(random.nextInt(5)-3, 0.15625D).endVertex();
        bufferbuilder.pos(random.nextInt(5)-3, random.nextInt(5)-3, random.nextInt(5)-3).tex(random.nextInt(5)-3, random.nextInt(5)-3).endVertex();
        bufferbuilder.pos(random.nextInt(5)-3, random.nextInt(5)-3, random.nextInt(5)-3).tex(random.nextInt(5)-3, random.nextInt(5)-3).endVertex();
        bufferbuilder.pos(random.nextInt(5)-3, random.nextInt(5)-3, random.nextInt(5)-3).tex(random.nextInt(5)-3, random.nextInt(5)-3).endVertex();
        tessellator.draw();


        GlStateManager.disableOutlineMode();
        GlStateManager.disableColorMaterial();
        GlStateManager.enableLighting();
        GlStateManager.popMatrix();
        GlStateManager.popMatrix();
    }
    //@SideOnly(Side.SERVER)
    public void saService(ItemStack itemStack, EntityPlayer entityPlayer){}
}
