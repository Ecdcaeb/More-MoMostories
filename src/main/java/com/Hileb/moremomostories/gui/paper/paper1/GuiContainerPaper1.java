package com.Hileb.moremomostories.gui.paper.paper1;

import com.Hileb.moremomostories.IdlFramework;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.Hileb.moremomostories.gui.ModGuiElementLoader.GUI_DEMO;
import static com.Hileb.moremomostories.gui.ModGuiElementLoader.GUI_PAPER_1;

@SideOnly(Side.CLIENT)
public class GuiContainerPaper1 extends GuiContainer
{
    private static final String TEXTURE_PATH = IdlFramework.MODID + ":" + "textures/gui/paper/paper.png";
    private static final ResourceLocation TEXTURE = new ResourceLocation(TEXTURE_PATH);
    public GuiContainerPaper1(ContainerPaper1 inventorySlotsIn)
    {
        super(inventorySlotsIn);
        this.xSize = 190;
        this.ySize = 180;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F);

        this.mc.getTextureManager().bindTexture(TEXTURE);
        int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;

        this.drawTexturedModalRect(offsetX, offsetY, 0, 0, this.xSize, this.ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        this.drawVerticalLine(30, 19, 36, 0xFF000000);
        this.drawHorizontalLine(8, 167, 43, 0xFF000000);

        for(int i=1;i<=12;i++){
            this.fontRenderer.drawString(I18n.format(String.format("gui.hileb.paper1.desc.%d.tip",i)), (this.xSize - this.fontRenderer.getStringWidth(I18n.format(String.format("gui.hileb.paper1.desc.%d.tip",i)))) / 2, 8*i+20, 0xF4A460);
        }
    }

    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        switch (ID)
        {
            case GUI_PAPER_1:
                return new ContainerPaper1(player);
            default:
                return null;
        }
    }

    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        switch (ID)
        {
            case GUI_PAPER_1:
                return new GuiContainerPaper1(new ContainerPaper1(player));
            default:
                return null;
        }
    }
}
