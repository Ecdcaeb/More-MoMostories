package com.Hileb.moremomostories.potion.myBuff;

import com.Hileb.moremomostories.IdlFramework;
import com.Hileb.moremomostories.potion.ModPotions;
import com.Hileb.moremomostories.potion.PotionBase;
import com.gq2529.momostories.item.ModItems;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.network.play.server.SPacketTitle;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PotionDayTime extends PotionBase {
    public PotionDayTime(String name){
        super(name,false,0,0);
        MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public void removeEffect(LivingEvent.LivingUpdateEvent event){
        if (event.getEntityLiving() instanceof EntityPlayerMP && hasPotion(event.getEntityLiving(), MobEffects.BLINDNESS) && hasPotion(event.getEntityLiving(),this))event.getEntityLiving().removePotionEffect(MobEffects.BLINDNESS);
    }
    private boolean hasPotion(EntityLivingBase living, Potion potion){
        for (PotionEffect effect: living.getActivePotionEffects()){
            if (effect.getPotion()== potion){
                return true;
            }
        }
        return false;
    }
    private void renderBakin(float alpha){
        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();
        int dx=net.minecraft.client.Minecraft.getMinecraft().displayWidth;
        int dy=net.minecraft.client.Minecraft.getMinecraft().displayHeight;
        net.minecraft.client.renderer.GlStateManager.color(1.0F, 1.0F, 1.0F,alpha);
        net.minecraft.client.Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(IdlFramework.MODID,"textures/misc/white.png"));
        drawTexturedModalRect(0,0,0,0,dx,dy);

    }
    public void drawTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height)
    {
        float f = 0.00390625F;
        float f1 = 0.00390625F;
        int zLevel=0;
        net.minecraft.client.renderer.Tessellator tessellator = net.minecraft.client.renderer.Tessellator.getInstance();
        net.minecraft.client.renderer.BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, net.minecraft.client.renderer.vertex.DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos((double)(x + 0), (double)(y + height), (double)zLevel).tex((double)((float)(textureX + 0) * 0.00390625F), (double)((float)(textureY + height) * 0.00390625F)).endVertex();
        bufferbuilder.pos((double)(x + width), (double)(y + height), (double)zLevel).tex((double)((float)(textureX + width) * 0.00390625F), (double)((float)(textureY + height) * 0.00390625F)).endVertex();
        bufferbuilder.pos((double)(x + width), (double)(y + 0), (double)zLevel).tex((double)((float)(textureX + width) * 0.00390625F), (double)((float)(textureY + 0) * 0.00390625F)).endVertex();
        bufferbuilder.pos((double)(x + 0), (double)(y + 0), (double)zLevel).tex((double)((float)(textureX + 0) * 0.00390625F), (double)((float)(textureY + 0) * 0.00390625F)).endVertex();
        tessellator.draw();
    }
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onRenders(net.minecraftforge.client.event.RenderGameOverlayEvent.Pre event){
        if (event.isCanceled())return;
        else {
            for (PotionEffect effect: net.minecraft.client.Minecraft.getMinecraft().player.getActivePotionEffects()){
                if (effect.getPotion()== ModPotions.DAY_BLIND){
                    float tick=(((float)effect.getDuration()+event.getPartialTicks())/60);
                    //IdlFramework.LogWarning("type = %s",event.getType().toString());
                    if (event.getType()== RenderGameOverlayEvent.ElementType.TEXT)renderBakin(tick);
                }
            }
        }

    }
    public static PotionEffect getEffect(){
        return new PotionEffect(ModPotions.DAY_BLIND,60,0,false,false);
    }
    public static PotionEffect getEffectShort(){
        return new PotionEffect(ModPotions.DAY_BLIND,10,0,false,false);
    }
    @SubscribeEvent
    public void onDayTime(PlayerInteractEvent.RightClickItem event){
        if (!event.getWorld().isRemote && !event.isCanceled()){
            if (event.getItemStack().getItem()== ModItems.DAYTIME){
                if (simulatedUse(event)){
                    event.getEntityPlayer().addPotionEffect(getEffect());
                    event.setCancellationResult(EnumActionResult.SUCCESS);
                    final long time = event.getWorld().getWorldTime() + 24000L;
                    event.getWorld().setWorldTime(time - time % 24000L - 450);
                    //event.getEntityPlayer().sendMessage(new TextComponentTranslation("DayTimeText"));
                    if (event.getEntityPlayer() instanceof EntityPlayerMP){
                        EntityPlayerMP playerMP=(EntityPlayerMP)event.getEntityPlayer();
                        playerMP.connection.sendPacket(new SPacketTitle(SPacketTitle.Type.ACTIONBAR,new TextComponentTranslation("DayTimeText"),20,40,20));
                    }
                    EntityPlayer player=event.getEntityPlayer();
                    /*
                    3.1415926535 8979323846 2643383279 5028841971 6939937510 5820974944 5923078164 0628620899 8628034825 3421170679 8214808651 3282306647 0938446095 5058223172 5359408128 4811174502 8410270193 8521105559 6446229489 5493038196 4428810975 6659334461 2847564823 3786783165 2712019091 4564856692 3460348610 4543266482 1339360726 0249141273 7245870066 0631558817 4881520920 9628292540 9171536436 7892590360 0113305305 4882046652 1384146951 9415116094 3305727036 5759591953 0921861173 8193261179 3105118548 0744623799 6274956735 1885752724 8912279381 8301194912 9833673362 4406566430 8602139494 6395224737 1907021798 6094370277 0539217176 2931767523 8467481846 7669405132 0005681271 4526356082 7785771342 7577896091 7363717872 1468440901 2249534301 4654958537 1050792279 6892589235 4201995611 2129021960 8640344181 5981362977 4771309960 5187072113 4999999837 2978049951 0597317328 1609631859 5024459455 3469083026 4252230825 3344685035 2619311881 7101000313 7838752886 5875332083 8142061717 7669147303 598253
                     */
//                    player.rotationPitch=-(10f/180f*3.14159265358979323846f);
//                    player.rotationYaw=-(0.5f*3.14159265358979323846f);
//                    player.turn()
                    event.setCanceled(true);
                }
            }
        }
    }
    private boolean simulatedUse(PlayerInteractEvent.RightClickItem event){
        if (event.getEntityPlayer().getHeldItem(event.getHand()).getItem()==ModItems.DAYTIME && !event.getEntityPlayer().world.isRemote) {
            final long time = event.getEntityPlayer().world.getWorldTime() + 24000L;
            if (time % 24000L > 11600L && time % 24000L < 23250L)
            {
                return true;
            }
        }
        return false;
    }

    ///-------------------------------------------------------
    public ResourceLocation getTexture(){
        return null;
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
}
