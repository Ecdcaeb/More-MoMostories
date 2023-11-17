package mods.Hileb.moremomostories.common.world.gui.fakeChestGui;

import mods.Hileb.moremomostories.MoreMoMoSrories;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiContainerFakeChest extends GuiContainer
{
    private static final String TEXTURE_PATH = MoreMoMoSrories.MODID + ":" + "textures/gui/chest.png";
    private static final ResourceLocation TEXTURE = new ResourceLocation(TEXTURE_PATH);
    public GuiContainerFakeChest(ContainerFakeChest inventorySlotsIn)
    {
        super(inventorySlotsIn);
        this.xSize = 222;
        this.ySize = 114 + 3 * 18;
    }
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
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

        String title = I18n.format( "container.chest");
        this.fontRenderer.drawString(title, 8, 6, 4210752);
    }

}
